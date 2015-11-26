package cn.com.nbd.nbdmobile.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

import org.hjh.async.framework.AppHandler;
import org.hjh.inject.InjectCore;
import org.hjh.inject.InjectLayout;
import org.hjh.inject.InjectView;
import org.hjh.refresh.PullToRefreshBase;
import org.hjh.refresh.PullToRefreshBase.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.nbd.nbdmobile.R;
import cn.com.nbd.nbdmobile.api.AppConstants;
import cn.com.nbd.nbdmobile.api.HomeComponent;
import cn.com.nbd.nbdmobile.base.AppPublicAdapter;
import cn.com.nbd.nbdmobile.bean.ActivityArticleRollList;
import cn.com.nbd.nbdmobile.bean.ArticleDetailForRoll;
import cn.com.nbd.nbdmobile.bean.ResultObject;
import cn.com.nbd.nbdmobile.config.AppPresences;
import cn.com.nbd.nbdmobile.holder.RollHolder;
import cn.com.nbd.nbdmobile.tool.BaseTools;
import cn.com.nbd.nbdmobile.view.PullToRefreshListView;

@InjectLayout(layout = R.layout.client_layout)
public class RollFragment extends BaseFragment implements AppPublicAdapter.IFillValue,
		OnItemClickListener {

	@InjectView(id = R.id.pullview)
	private PullToRefreshListView refreshView;

	private ListView listView;
	private AppPublicAdapter adapter;
	protected Resources mResources;
	Context context;
	private View rootLayout;
	private int currentPage = 1;
	private int pageSize = 10;
	Context Context;
	private boolean loadMore = false;// 当前是否为加载更多

	private List<ArticleDetailForRoll> list = new ArrayList<ArticleDetailForRoll>();


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
		int StatusBarHeight = getStatusBarHeight();

		params.height = BaseTools.getWindowHight(mActivity)
				- tabhight - titlehight
				- StatusBarHeight;
		refreshView.setPullLoadEnabled(false);
		refreshView.setScrollLoadEnabled(true);
		listView = refreshView.getRefreshableView();
		adapter = new AppPublicAdapter(mActivity, list, RollHolder.class,
				this);
		listView.setAdapter(adapter);
		listView.setDivider(new ColorDrawable(Color.GRAY));
		listView.setDividerHeight(1);
		listView.setCacheColorHint(Color.TRANSPARENT);


		refreshView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				currentPage = 1;
				loadMore = false;
				mHandler.showDialog(false);
				HomeComponent.getInstance().queryRollArticle(currentPage, pageSize,
						mHandler);
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				mHandler.showDialog(false);
				loadMore = true;
				HomeComponent.getInstance().queryRollArticle(currentPage, pageSize,
						mHandler);
			}
		});

		setLastUpdateTime(refreshView);
		listView.setOnItemClickListener(this);
	}

	private void loadArticle(List<ArticleDetailForRoll> list1) {
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
					case AppConstants.RESULT_QUERY_ROLL_FAILED:
						if (!isConnectTimeOut((ResultObject) msg.obj, "x")) {
						}
						refreshView.onPullDownRefreshComplete();
						refreshView.onPullUpRefreshComplete();
						refreshView.setHasMoreData(false);
						setLastUpdateTime(refreshView);
						break;
					case AppConstants.RESULT_QUERY_ROLL_SUCCESS:
						currentPage++;
						refreshView.onPullDownRefreshComplete();
						refreshView.onPullUpRefreshComplete();
						refreshView.setHasMoreData(true);
						setLastUpdateTime(refreshView);
						ActivityArticleRollList ActivityArticleRollList = (ActivityArticleRollList) msg.obj;
						loadArticle(ActivityArticleRollList.getArticles());
						break;
					default:
						break;
				}
			}

		};
	}

	@Override
	public void fillData(int position, Object object) {
		final RollHolder holder = (RollHolder) object;
		final ArticleDetailForRoll article = (ArticleDetailForRoll) adapter
				.getList().get(position);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"HH:mm");
		java.util.Date dt = new Date(article.getCreated_at());
		String sDateTime = simpleDateFormat.format(dt);
		if (position == 0) {
			holder.view_1.setVisibility(View.INVISIBLE);
		}
		holder.readText.setText(article.getMobile_click_count() + "");
		holder.description.setText(sDateTime);
		holder.description
				.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (article.isCheck() == false) {
							holder.description
									.setMaxLines(Integer.MAX_VALUE);
							article.setCheck(true);
						} else {
							holder.description.setMaxLines(3);
							article.setCheck(false);
						}
//						if (onItemAddClick != null) {
//							onItemAddClick.onItemClick(v,
//									article.serverId);
//						}

					}
				});
		String stringColor = sDateTime + " " + article.getDigest();
		SpannableString stringColorSpan = new SpannableString(
				stringColor);

		// specialw为7时字体为
		if (7 == (article.getSpecial())) {
			stringColorSpan.setSpan(
					new ForegroundColorSpan(Color.rgb(204, 51, 0)),
					0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			stringColorSpan.setSpan(
					new ForegroundColorSpan(Color.rgb(255, 0, 0)), 5,
					stringColorSpan.length(),
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			holder.description.setText(stringColorSpan);
		} else if (0 == (article.getSpecial())) {
			stringColorSpan.setSpan(
					new ForegroundColorSpan(Color.rgb(204, 51, 0)),
					0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			stringColorSpan.setSpan(
					new ForegroundColorSpan(Color.rgb(0, 0, 0)), 5,
					stringColorSpan.length(),
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			holder.description.setText(stringColorSpan);
		}
		if ((holder.description.getText().length() > 60)
				&& (holder.description.getMaxLines() > 3)) {
			holder.description.setMaxLines(3);
		}

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

			HomeComponent.getInstance().queryRollArticle(currentPage, pageSize,
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
