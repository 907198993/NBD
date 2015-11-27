package cn.com.nbd.nbdmobile.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.hjh.async.framework.AppHandler;
import org.hjh.inject.InjectCore;
import org.hjh.inject.InjectLayout;
import org.hjh.inject.InjectView;
import org.hjh.refresh.PullToRefreshBase;
import org.hjh.refresh.PullToRefreshBase.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import cn.com.nbd.nbdmobile.R;
import cn.com.nbd.nbdmobile.api.AppConstants;
import cn.com.nbd.nbdmobile.api.HomeComponent;
import cn.com.nbd.nbdmobile.base.AppPublicAdapter;
import cn.com.nbd.nbdmobile.bean.ActivityArticle;
import cn.com.nbd.nbdmobile.config.AppPresences;
import cn.com.nbd.nbdmobile.holder.ActivityArticleHolder;
import cn.com.nbd.nbdmobile.tool.BaseTools;
import cn.com.nbd.nbdmobile.view.PullToRefreshListView;
import cn.com.nbd.nbdmobile.view.TitleLayout;


/**
 * 活动列表
 */
@InjectLayout(layout = R.layout.news_activity_layout)
public final class

		NewsActivityDetailActivity extends BaseActivity implements
		OnItemClickListener, AppPublicAdapter.IFillValue {

	@InjectView(id = R.id.title_bar)
	private TitleLayout titleLayout;

	@InjectView(id = R.id.pullview)
	private PullToRefreshListView mPullListView;

	private ListView mListView;
	private AppPublicAdapter adapter;
	private List<ActivityArticle> list = new ArrayList<ActivityArticle>();

	private int currentPage = 2;
	private boolean loadMore = false;// 当前是否为加载更多

	private String keywords = "";
	private String ArticleType;

	@Override
	protected void onDestroy() {
		list.clear();
		super.onDestroy();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		InjectCore.injectUI(this);
		init();
	}

	private void init() {
		titleLayout.setTitleText("活动专题");
		titleLayout.enableLeftButtonImage(R.drawable.category);
		titleLayout.enableRightButtonImage(R.drawable.user);
		titleLayout.setTitleClickListener(this);
		initPullView();
		HomeComponent.getInstance().queryNewsActivityList(currentPage, 10, mHandler);
	}

	private void initPullView() {
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mPullListView
				.getLayoutParams();
		int tabhight = AppPresences.getInstance().getInt("tabhight");
		int titlehight = AppPresences.getInstance().getInt("titlehight");
		int StatusBarHeight = getStatusBarHeight();

		params.height = BaseTools.getWindowHight(mActivity)
				- tabhight - titlehight
				- StatusBarHeight;
		mPullListView.setPullLoadEnabled(false);
		mPullListView.setScrollLoadEnabled(true);
		mListView = mPullListView.getRefreshableView();
		adapter = new AppPublicAdapter(mContext, list,
				ActivityArticleHolder.class, this);
		mListView.setAdapter(adapter);
		mListView.setDivider(null);
		mListView.setCacheColorHint(Color.TRANSPARENT);
		mPullListView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				adapter.clear();//
				currentPage = 1;
				loadMore = false;
				HomeComponent.getInstance().queryNewsActivityList(currentPage, 10, mHandler);
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				mHandler.showDialog(false);// 上拉加载不显示连接框
				loadMore = true;
				HomeComponent.getInstance().queryNewsActivityList(currentPage, 10, mHandler);
			}
		});

		setLastUpdateTime(mPullListView);
		mListView.setOnItemClickListener(this);
	}

	@Override
	public void onClickLeftView() {
		super.onClickLeftView();
		back();
	}

	@Override
	protected AppHandler getAppHandler() {
		return new AppHandler(mContext) {

			@Override
			public void disposeMessage(Message msg) {
				switch (msg.what) {
//					case AppConstants.HOSPITAL_RESULT_QUERY_LIST_FAILED:
//						endPull(mPullListView, false);
//						if (!isConnectTimeOut((ResultObject) msg.obj, "获取医院")) {
//						}
//
//						break;
					case AppConstants.RESULT_QUERY_ACTIVITY_ARTICLE_SUCCESS:
						endPull(mPullListView, true);
						currentPage++;
						List<ActivityArticle> lists = (List<ActivityArticle>) msg.obj;
						if (lists.size() != 0) {
							loadView(lists);
						} else {
							showToast("暂无更多数据");
						}
						break;
					default:
						break;
				}

			}

		};
	}

	protected void loadView(List<ActivityArticle> data) {
		if (data == null || data.isEmpty()) {
			return;
		}

		if (!loadMore) {
			list.addAll(data);
			adapter.setList(list);
		} else {
			adapter.getList().addAll(data);
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {

	}

	@Override
	public void fillData(int position, Object object) {
		if (list.isEmpty()) {
			return;
		}
		ActivityArticleHolder holder = (ActivityArticleHolder) object;
		holder.description.setText(list.get(position).getTitle());
		mImageLoader.loadImage(position, list.get(position).getAvatar(), holder.image);

	}

}
