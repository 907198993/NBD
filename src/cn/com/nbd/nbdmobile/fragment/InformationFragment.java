package cn.com.nbd.nbdmobile.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import org.hjh.async.framework.AppHandler;
import org.hjh.inject.InjectCore;
import org.hjh.inject.InjectLayout;
import org.hjh.inject.InjectView;
import org.hjh.refresh.PullToRefreshBase;
import org.hjh.refresh.PullToRefreshBase.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import cn.com.nbd.nbdmobile.R;
import cn.com.nbd.nbdmobile.adapter.CycleViewPager;
import cn.com.nbd.nbdmobile.adapter.MyViewPagerAdapter;
import cn.com.nbd.nbdmobile.api.AppConstants;
import cn.com.nbd.nbdmobile.api.HomeComponent;
import cn.com.nbd.nbdmobile.base.AppPublicAdapter;
import cn.com.nbd.nbdmobile.bean.ADArticle;
import cn.com.nbd.nbdmobile.bean.Article;
import cn.com.nbd.nbdmobile.bean.ArticleDetail;
import cn.com.nbd.nbdmobile.bean.ResultObject;
import cn.com.nbd.nbdmobile.config.AppPresences;
import cn.com.nbd.nbdmobile.holder.ArticleHolder;
import cn.com.nbd.nbdmobile.tool.BaseTools;
import cn.com.nbd.nbdmobile.view.PullToRefreshListView;

@InjectLayout(layout = R.layout.tab_client_layout)
public class InformationFragment extends BaseFragment implements AppPublicAdapter.IFillValue,
		OnItemClickListener {

	@InjectView(id = R.id.pullview)
	private PullToRefreshListView refreshView;

	private ListView listView;
	private AppPublicAdapter adapter;

	private View rootLayout;
	private int currentPage = 1;
	private int pageSize = 10;
	Context Context;
	private boolean loadMore = false;// 当前是否为加载更多
	boolean flag;
	private List<ArticleDetail> list = new ArrayList<ArticleDetail>();
	private List<ADArticle> adArticles = new ArrayList<ADArticle>();

	private CycleViewPager guidePages;
	private ScheduledExecutorService scheduledExecutorService;
	private int currentItem;
	private ArrayList<View> viewList = new ArrayList<View>();
	private ImageView[] imageViews;
	private LinearLayout viewGroup;
	private View header;
	TextView titleTextView;//轮播文字描述

	// 标志位，标志已经初始化完成。
	private boolean isPrepared;
	private boolean isRunning;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		if (rootLayout == null) {
			rootLayout = InjectCore.injectObject(this, getActivity(), true);
		}

		if (rootLayout.getParent() != null) {

			ViewGroup parent = (ViewGroup) rootLayout.getParent();

			if (parent != null) {
				parent.removeView(rootLayout);
			}
		}
		isPrepared = true;

		lazyLoad();
		return rootLayout;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initPullView();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	protected void init() {
		// TODO Auto-generated method stub
		header = LayoutInflater.from(mActivity).inflate(R.layout.article_top_list_item,
				null);
		//guidePages = (ViewPager) header.findViewById(R.id.guidePages);
		titleTextView = (TextView) header.findViewById(R.id.title);
		guidePages = (CycleViewPager) header.findViewById(R.id.guidePages);
		guidePages.setCurrentItem(currentItem);

		viewGroup = (LinearLayout) header.findViewById(R.id.viewGroup);
		guidePages.setOnPageChangeListener(new NavigationPageChangeListener());
		HomeComponent.getInstance().queryAdInfo(mHandler);
	}


	class NavigationPageChangeListener implements ViewPager.OnPageChangeListener {


		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
			titleTextView.setText(adArticles.get(position).getDigest());
			for (int i = 0; i < imageViews.length; i++) {
				if (position == i) {
					imageViews[i].setImageDrawable(getResources().getDrawable(
							R.drawable.page_focused));
				} else {
					imageViews[i].setImageDrawable(getResources().getDrawable(
							R.drawable.page_unfocused));
				}
			}
		}

	}

	//添加轮播图
	public void fillTitleAd(List<ADArticle> ADArticle) {
		for (int i = 0; i < ADArticle.size(); i++) {
			ImageView iv = new ImageView(mActivity);
			iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));

			titleTextView.setText(ADArticle.get(i).getDigest());
			mImageLoader.loadImage(0, ADArticle.get(i).getImage(), iv);

			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			viewList.add(iv);
		}
		guidePages.setAdapter(new MyViewPagerAdapter(viewList));
		imageViews = new ImageView[ADArticle.size()];
		for (int i = 0; i < ADArticle.size(); i++) {
			ImageView imageView = new ImageView(mActivity);
			imageView = new ImageView(mActivity);
			imageView.setLayoutParams(new LayoutParams(20, 20));
			imageView.setPadding(5, 0, 5, 0);
			imageViews[i] = imageView;
			if (i == 0)
				imageViews[i].setImageDrawable(getResources().getDrawable(
						R.drawable.page_focused));
			else
				imageViews[i].setImageDrawable(getResources().getDrawable(
						R.drawable.page_unfocused));

			viewGroup.addView(imageViews[i]);
		}
	}


	// =============================
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
//		// 用一个定时器 来完成图片切换
//		// Timer 与 ScheduledExecutorService 实现定时器的效果
//
//		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//		// 通过定时器 来完成 每2秒钟切换一个图片
//		// 经过指定的时间后，执行所指定的任务
//		// scheduleAtFixedRate(command, initialDelay, period, unit)
//		// command 所要执行的任务
//		// initialDelay 第一次启动时 延迟启动时间
//		// period 每间隔多次时间来重新启动任务
//		// unit 时间单位
//		scheduledExecutorService.scheduleAtFixedRate(new ViewPagerTask(), 1, 5,
//				TimeUnit.SECONDS);

		super.onStart();

	}

//	// 用来完成图片切换的任务
//	private class ViewPagerTask implements Runnable {
//
//		public void run() {
//			// 实现我们的操作
//			// 改变当前页面
//			currentItem = (currentItem + 1) % imageViews.length;
//			// Handler来实现图片切换
//			handler.obtainMessage().sendToTarget();
//		}
//
//	}

	@Override
	public void onStop() {
		// 停止图片切换
		//scheduledExecutorService.shutdown();

		super.onStop();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void initPullView() {
		LinearLayout.LayoutParams params = (LayoutParams) refreshView
				.getLayoutParams();
		int tabhight = AppPresences.getInstance().getInt("tabhight");
		int titlehight = AppPresences.getInstance().getInt("titlehight");
		int StatusBarHeight = getStatusBarHeight();

		params.height = BaseTools.getWindowHight(mActivity)
				- tabhight - titlehight
				- StatusBarHeight;
		refreshView.setPullLoadEnabled(false);
		refreshView.setScrollLoadEnabled(true);
		listView = refreshView.getRefreshableView();
		adapter = new AppPublicAdapter(mActivity, list, ArticleHolder.class,
				this);
		listView.setAdapter(adapter);
		listView.setDivider(new ColorDrawable(Color.GRAY));
		listView.setDividerHeight(1);
		listView.setCacheColorHint(Color.TRANSPARENT);

		listView.addHeaderView(header);
		refreshView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				currentPage = 1;
				loadMore = false;
				mHandler.showDialog(false);

				HomeComponent.getInstance().queryArticle(currentPage, pageSize,
						mHandler);
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				mHandler.showDialog(false);
				loadMore = true;
				HomeComponent.getInstance().queryArticle(currentPage, pageSize,
						mHandler);
			}
		});

		setLastUpdateTime(refreshView);
		listView.setOnItemClickListener(this);
	}

	private void loadArticle(List<ArticleDetail> list1) {
		if (list1 == null || list1.isEmpty()) {
			return;
		}
		if (!loadMore) {
			adapter.clear();
			list.addAll(list1);
			adapter.setList(list1);
		} else {
			adapter.getList().addAll(list1);
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	protected AppHandler getAppHandler() {
		return new AppHandler(mActivity) {

			@Override
			public void disposeMessage(Message msg) {
				switch (msg.what) {
					case AppConstants.RESULT_QUERY_ARTICLE_FAILED:
						if (!isConnectTimeOut((ResultObject) msg.obj, "资讯")) {
						}
						refreshView.onPullDownRefreshComplete();
						refreshView.onPullUpRefreshComplete();
						refreshView.setHasMoreData(false);
						setLastUpdateTime(refreshView);
						break;
					case AppConstants.RESULT_QUERY_ARTICLE_SUCCESS:
						currentPage++;
						refreshView.onPullDownRefreshComplete();
						refreshView.onPullUpRefreshComplete();
						refreshView.setHasMoreData(true);
						setLastUpdateTime(refreshView);
						Article articleDetail = (Article) msg.obj;
						loadArticle(articleDetail.getArticles());
						break;
					case AppConstants.RESULT_QUERY_AD_ARTICLE_SUCCESS:
						adArticles = (List<ADArticle>) msg.obj;
						fillTitleAd(adArticles);
						break;
					default:
						break;
				}
			}

		};
	}

	@Override
	public void fillData(int position, Object object) {
		ArticleHolder holder = (ArticleHolder) object;
		ArticleDetail articleListDetail = (ArticleDetail) adapter
				.getList().get(position);
		holder.description.setText(articleListDetail.getTitle());
		holder.readnum.setText(articleListDetail.getMobile_click_count());
		mImageLoader.loadImage(position, articleListDetail.getImage(), holder.image);


	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

	}

	@Override
	protected void lazyLoad() {
		if (!isPrepared || !isVisible) {
			return;
		}
		//在运行的话则不再重新加载
		if (!isRunning) {

			HomeComponent.getInstance().queryArticle(currentPage, pageSize,
					mHandler);


			System.out
					.println("client  queryArticle------------------------------------------------------------------------------------------------------");
		}

		isRunning = true;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		isRunning = false;
	}

}
