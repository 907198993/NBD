package cn.com.nbd.nbdmobile.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
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
import cn.com.nbd.nbdmobile.api.AppConstants;
import cn.com.nbd.nbdmobile.api.HomeComponent;
import cn.com.nbd.nbdmobile.base.AppPublicAdapter;
import cn.com.nbd.nbdmobile.bean.Article;
import cn.com.nbd.nbdmobile.bean.ArticleDetail;
import cn.com.nbd.nbdmobile.bean.StockDetail;
import cn.com.nbd.nbdmobile.config.AppPresences;
import cn.com.nbd.nbdmobile.holder.ArticleHolder;
import cn.com.nbd.nbdmobile.tool.BaseTools;
import cn.com.nbd.nbdmobile.view.PullToRefreshListView;


@InjectLayout(layout = R.layout.tab_client_layout)
public class InformationFragment1 extends BaseFragment implements AppPublicAdapter.IFillValue,
		OnItemClickListener {

	@InjectView(id = R.id.pullview)
	private PullToRefreshListView refreshView;
	private Context context;

	private ListView listView;
	private AppPublicAdapter adapter;

	private ViewPager guidePages;
	private ScheduledExecutorService scheduledExecutorService;
	private int currentItem;
	private ArrayList<View> viewList = new ArrayList<View>();
	private ImageView[] imageViews;
	private LinearLayout viewGroup;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			guidePages.setCurrentItem(currentItem);
		}

		;
	};

	ViewPager viewPager;
	private StockDetail detail;
	private LayoutInflater inflater;
	private View header;
	private List<ArticleDetail> list = new ArrayList<ArticleDetail>();
	private View rootLayout;
	private int currentPage = 1;
	private int pageSize = 10;
	private int index = 0;

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
		return rootLayout;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);


//		viewPager = (ViewPager) header.findViewById(R.id.viewpager);
//		mAdapter = new TuanViewPagerAdapter(mList, this);
		//	HomeComponent.getInstance().queryStockInfo(mHandler);
		HomeComponent.getInstance().queryArticle(currentPage, pageSize,
				mHandler);
	}

	public class MyViewPagerAdapter extends PagerAdapter {
		private List<View> mListViews;

		public MyViewPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(mListViews.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mListViews.get(position), 0);
			return mListViews.get(position);
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	}

	public void fillGuanggao(ArrayList arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			ImageView iv = new ImageView(mActivity);
			iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));

			iv.setBackgroundResource(R.drawable.ic_launcher);
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			viewList.add(iv);
		}
		guidePages.setAdapter(new MyViewPagerAdapter(viewList));
		imageViews = new ImageView[arrayList.size()];
		for (int i = 0; i < arrayList.size(); i++) {
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

	class NavigationPageChangeListener implements ViewPager.OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			currentItem = arg0;
			for (int i = 0; i < imageViews.length; i++) {
				if (arg0 == i) {
					imageViews[i].setImageDrawable(getResources().getDrawable(
							R.drawable.page_focused));
				} else {
					imageViews[i].setImageDrawable(getResources().getDrawable(
							R.drawable.page_unfocused));
				}
			}
		}

	}

	private void init() {

	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		// mPosterPager.startAutoScroll();
		if (list.isEmpty()) {

			// HelperComponent.getInstance().queryClients("", "", "", "",
			// currentPage, pageSize, mHandler);
		}
	}

	private void initPullView(List<ArticleDetail> Article) {
		LayoutParams params = (LayoutParams) refreshView
				.getLayoutParams();
		int tabhight = AppPresences.getInstance().getInt("tabhight");
		int titlehight = AppPresences.getInstance().getInt("titlehight");
		params.height = BaseTools.getWindowHight(mActivity) - titlehight - tabhight - getStatusBarHeight();
		refreshView.setPullLoadEnabled(false);
		refreshView.setScrollLoadEnabled(true);
		listView = refreshView.getRefreshableView();

		header = LayoutInflater.from(context).inflate(R.layout.article_top_list_item,
				null);
		guidePages = (ViewPager) header.findViewById(R.id.guidePages);
		viewGroup = (LinearLayout) header.findViewById(R.id.viewGroup);
		guidePages.setOnPageChangeListener(new NavigationPageChangeListener());
		ArrayList arrayList = new ArrayList();
		for (int i = 0; i < 10; i++) {
			arrayList.add("测试" + i);
		}
		fillGuanggao(arrayList);
		/**
		 * 创建多个item （每一条viewPager都是一个item）
		 * 从服务器获取完数据（如文章标题、url地址） 后，再设置适配器
		 */
//        for (int i = 0; i < 7; i++) {
//            item = inflater.inflate(R.layout.item, null);
//            ((TextView) item.findViewById(R.id.text_view)).setText("第 " + i+ " 个 viewPager");
//            list.add(item);
//        }

//        List<View> list = new ArrayList<View>();
//        TextView shangId = (TextView) header.findViewById(R.id.shang_id);
//        TextView shenId = (TextView) header.findViewById(R.id.shen_id);
//        TextView hengId = (TextView) header.findViewById(R.id.heng_id);
//
//        TextView shangRate = (TextView) header.findViewById(R.id.shang_rate);
//        TextView shenRate = (TextView) header.findViewById(R.id.shen_rate);
//        TextView hengRate = (TextView) header.findViewById(R.id.heng_rate);
//
//        shangId.setText(msg.getRetData().getMarket().getShanghai().getCurdot()
//                + "");
//        shenId.setText(msg.getRetData().getMarket().getShenzhen().getCurdot()
//                + "");
//        hengId.setText(msg.getRetData().getMarket().getHSI().getCurdot() + "");
//
//        shangRate.setText(msg.getRetData().getMarket().getShanghai()
//                .getCurprice()
//                + " "
//                + msg.getRetData().getMarket().getShanghai().getRate()
//                + "%");
//        shenRate.setText(msg.getRetData().getMarket().getShenzhen()
//                .getCurprice()
//                + " "
//                + msg.getRetData().getMarket().getShenzhen().getRate()
//                + "%");
//        hengRate.setText(msg.getRetData().getMarket().getHSI().getCurprice()
//                + " " + msg.getRetData().getMarket().getHSI().getRate() + "%");
//
//        if (msg.getRetData().getMarket().getShanghai().getCurprice() < 0) {
//            shangId.setTextColor(getResources().getColor(R.color.green));
//            shangRate.setTextColor(getResources().getColor(R.color.green));
//        }
//
//        if (msg.getRetData().getMarket().getShenzhen().getCurprice() < 0) {
//            shenId.setTextColor(getResources().getColor(R.color.green));
//            shenRate.setTextColor(getResources().getColor(R.color.green));
//        }
//
//        if (msg.getRetData().getMarket().getHSI().getCurprice() < 0) {
//            hengId.setTextColor(getResources().getColor(R.color.green));
//            hengRate.setTextColor(getResources().getColor(R.color.green));
//        }
		listView.addHeaderView(header);
		//loadAdvertisement(Article);

		adapter = new AppPublicAdapter(mActivity, list, ArticleHolder.class,
				this);
		listView.setAdapter(adapter);
		listView.setDivider(getResources().getDrawable(R.color.gray));
		listView.setCacheColorHint(Color.TRANSPARENT);

		refreshView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				adapter.clear();//
				currentPage = 1;
				mHandler.showDialog(false);
				HomeComponent.getInstance().queryArticle(currentPage, pageSize,
						mHandler);
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				mHandler.showDialog(false);
				HomeComponent.getInstance().queryArticle(currentPage, pageSize,
						mHandler);
			}
		});

		setLastUpdateTime(refreshView);
		listView.setOnItemClickListener(this);
	}

	private void loadArticle(List<ArticleDetail> list) {

		adapter.getList().addAll(list);
		adapter.notifyDataSetChanged();
	}

	@Override
	protected AppHandler getAppHandler() {
		return new AppHandler(mActivity) {

			@Override
			public void disposeMessage(Message msg) {
				switch (msg.what) {
					// case HelperConstants.RESULT_QUERY_CUSTOMER_LIST_FAILED:
					// if(!isConnectTimeOut((ResultObject) msg.obj, "��ȡ�ͻ��б�")){}
					// refreshView.onPullDownRefreshComplete();
					// refreshView.onPullUpRefreshComplete();
					// refreshView.setHasMoreData(false);
					// setLastUpdateTime(refreshView);
					// break;
					case AppConstants.RESULT_QUERY_ARTICLE_SUCCESS:
						currentPage++;
						refreshView.onPullDownRefreshComplete();
						refreshView.onPullUpRefreshComplete();
						refreshView.setHasMoreData(true);
						setLastUpdateTime(refreshView);
						Article article = (Article) msg.obj;
						// initPullView(article.getArticles());
						loadArticle(article.getArticles());
						//loadAdvertisement(article.getArticles());
						initPullView(article.getArticles());
						break;
//					case AppConstants.RESULT_QUERY_STOCK_CONTENT_SUCCESS:
//						//   detail = (StockDetail) msg.obj;
//						ArticleForAd articles = (ArticleForAd) msg.obj;
//
//
//						break;
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
		// holder.oneImageView.setImageDrawable(getResources().getDrawable(R.drawable.phone_icon));
		holder.description.setText(articleListDetail.getTitle());
		holder.readnum.setText(articleListDetail.getMobile_click_count());
//        mImageLoader.loadImage(0, articleListDetail.getImage(), listener,
//                holder.image, R.drawable.all_circle, 300, 300);
		mImageLoader.loadImage(position, articleListDetail.getImage(), holder.image);
		// holder.threeView.setText(customer.getDescription());
		// holder.fourImageView.setImageDrawable(getResources().getDrawable(R.drawable.client_phone));

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
							long arg3) {
		Intent intent = new Intent();
		// intent.setClass(mActivity, CustomerActivity.class);
		// HelperCustomer customer = (HelperCustomer)
		// adapter.getList().get(position);
		// Bundle bundle = new Bundle();
		// bundle.putSerializable("customer", customer);
		// intent.putExtras(bundle);
		// this.startActivity(intent);

	}

	@Override
	protected void lazyLoad() {

	}

//	@Override
//	public void loadImage(HomeGalleryView view) {
//	//	mImageLoader.loadImage(0,view.getArticleDetail().getImage(),view);
//	}

//	private void loadAdvertisement(List<ArticleDetail> list) {
//		for (final ArticleDetail info : list) {
//			HomeGalleryView view = new HomeGalleryView(mActivity);
//			view.setOnClickListener(new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
////                    Intent intent  = new Intent();
////                    intent.putExtra("url", info.getAd_link());
////                    intent.setClass(mActivity, ModuleWebActivity.class);
////                    startActivityWithAnim(mActivity, intent);
//				}
//			});
//			view.setArticleDetail(info);
//			mImageLoader.cleanSpecialUrl(info.getImage());//每次清除缓存
//			mList.add(view);
//		}
//
//        points = new ImageView[mList.size()];
//       // addPoint(group,points);
//		mAdapter.setmList(mList);
//		viewPager.setAdapter(mAdapter);
//		viewPager.setCurrentItem(mList.size() * 100);
//	}


}
