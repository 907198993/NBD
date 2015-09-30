package cn.com.nbd.nbdmobile.fragment;

import java.util.ArrayList;
import java.util.List;

import org.hjh.async.framework.AppHandler;
import org.hjh.inject.InjectCore;
import org.hjh.inject.InjectLayout;
import org.hjh.inject.InjectView;
import org.hjh.refresh.PullToRefreshBase;
import org.hjh.refresh.PullToRefreshBase.OnRefreshListener;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import cn.com.nbd.nbdmobile.R;
import cn.com.nbd.nbdmobile.api.AppConstants;
import cn.com.nbd.nbdmobile.api.HomeComponent;
import cn.com.nbd.nbdmobile.bean.ArticleDetail;
import cn.com.nbd.nbdmobile.bean.ArticleListDetail;
import cn.com.nbd.nbdmobile.bean.ResultObject;
import cn.com.nbd.nbdmobile.config.AppPresences;
import cn.com.nbd.nbdmobile.holder.ArticleHolder;
import cn.com.nbd.nbdmobile.tool.BaseTools;
import cn.com.nbd.nbdmobile.view.PullToRefreshListView;

import com.dpt.base.AppPublicAdapter;
import com.dpt.base.AppPublicAdapter.IFillValue;
import com.dpt.config.BaseConfig;

@InjectLayout(layout = R.layout.client_layout)
public class ClientManageFragment extends BaseFragment implements IFillValue,
		OnItemClickListener {

	@InjectView(id = R.id.pullview)
	private PullToRefreshListView refreshView;

	private ListView listView;
	private AppPublicAdapter adapter;

	private View rootLayout;
	private int currentPage = 1;
	private int pageSize = 10;

	private boolean loadMore = false;// 当前是否为加载更多

	private List<ArticleListDetail> list = new ArrayList<ArticleListDetail>();
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
		int StatusBarHeight=  getStatusBarHeight();
		
		params.height = BaseTools.getWindowHight(mActivity)
				 -tabhight-titlehight
				-StatusBarHeight;
		refreshView.setPullLoadEnabled(false);
		refreshView.setScrollLoadEnabled(true);
		listView = refreshView.getRefreshableView();
		adapter = new AppPublicAdapter(mActivity, list, ArticleHolder.class,
				this);
		listView.setAdapter(adapter);

		listView.setCacheColorHint(Color.TRANSPARENT);

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

	private void loadArticle(List<ArticleListDetail> list1) {
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
					if (!isConnectTimeOut((ResultObject) msg.obj, "x")) {
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
					ArticleDetail articleDetail = (ArticleDetail) msg.obj;
					loadArticle(articleDetail.getArticles());
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
		ArticleListDetail articleListDetail = (ArticleListDetail) adapter
				.getList().get(position);
		holder.description.setText(articleListDetail.getTitle());
		holder.readnum.setText(articleListDetail.getMobile_click_count());
		mImageLoader.loadImage(0, articleListDetail.getImage(), listener,
				holder.image, R.drawable.all_circle, 300, 300);

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