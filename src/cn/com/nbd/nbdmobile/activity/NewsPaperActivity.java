package cn.com.nbd.nbdmobile.activity;

/**
 * Created by Dell on 2015/10/15.
 */

import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import org.hjh.async.framework.AppHandler;
import org.hjh.inject.InjectCore;
import org.hjh.inject.InjectLayout;
import org.hjh.inject.InjectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.com.nbd.nbdmobile.R;
import cn.com.nbd.nbdmobile.api.AppConstants;
import cn.com.nbd.nbdmobile.api.HomeComponent;
import cn.com.nbd.nbdmobile.bean.NewsPaper;
import cn.com.nbd.nbdmobile.bean.NewsPaperArticle;
import cn.com.nbd.nbdmobile.tool.SystemBarTintManager;
import cn.com.nbd.nbdmobile.view.TitleLayout;


@InjectLayout(layout = R.layout.news_paper_list_layout)
public class NewsPaperActivity extends BaseActivity {
	private static final String TAG = "NewsPaperActivity";

	@InjectView(id = R.id.title_bar)
	private TitleLayout titleLayout;

	@InjectView(id = R.id.expandable_list)
	private ExpandableListView expandableList;

	private SimpleExpandableListAdapter mSimpleExpandableListAdapter=null;
	private ArrayList<HashMap<String, String>> groupData = new ArrayList<HashMap<String, String>>();
	private ArrayList<ArrayList<HashMap<String, String>>> childData = new ArrayList<ArrayList<HashMap<String, String>>>();
	private final String CONTENT_TAG = "content tag";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		InjectCore.injectUI(this);
		//setTranslucentStatus();
		init();
//     // 创建状态栏的管理实例
//		SystemBarTintManager tintManager = new SystemBarTintManager(this);
//		// 激活状态栏设置
//		tintManager.setStatusBarTintEnabled(true);
//		// 激活导航栏设置
//		tintManager.setNavigationBarTintEnabled(true);
//		// 设置一个样式背景给导航栏
//		tintManager.setNavigationBarTintResource(R.drawable.center_top);



	}


	/**
	 * 设置状态栏背景状态
	 */
	private void setTranslucentStatus()
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
		{
			Window win = getWindow();
			WindowManager.LayoutParams winParams = win.getAttributes();
			final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
			winParams.flags |= bits;
			win.setAttributes(winParams);

		}
		SystemBarTintManager tintManager = new SystemBarTintManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintResource(0);//状态栏无背景
	}

	private void init() {

		titleLayout.setTitleText("今日报纸");
		titleLayout.enableLeftButtonImage(R.drawable.category);
		titleLayout.enableRightButtonImage(R.drawable.user);
		titleLayout.setTitleClickListener(this);
		HomeComponent.getInstance().queryNewsPaperList(mHandler);


		expandableList.setGroupIndicator(null);
		mSimpleExpandableListAdapter = new SimpleExpandableListAdapter(
				mActivity,
				groupData,
				R.layout.group,
				new String[] { CONTENT_TAG },
				new int[] { R.id.groupLabel },

				childData,
				R.layout.group_child,
				new String[] { CONTENT_TAG },
				new int[] { R.id.childLabel }
		);
		expandableList.setAdapter(mSimpleExpandableListAdapter);
		//expandableList.setOnChildClickListener(new ExpandableListViewChildClickListener());
//		ExpandableAdapter expandableAdapter = new ExpandableAdapter(this);
//		expandableList.setAdapter(expandableAdapter);
//
//		int groupCount = expandableList.getCount();
//
//		for (int i = 0; i < groupCount; i++) {
//
//			expandableList.expandGroup(i);
//
//		}
//		;ExpandableAdapter expandableAdapter = new ExpandableAdapter(this);
//		expandableList.setAdapter(expandableAdapter);
//
//		int groupCount = expandableList.getCount();
//
//		for (int i = 0; i < groupCount; i++) {
//
//			expandableList.expandGroup(i);
//
//		}
//		;ExpandableAdapter expandableAdapter = new ExpandableAdapter(this);
//		expandableList.setAdapter(expandableAdapter);
//
//		int groupCount = expandableList.getCount();
//
//		for (int i = 0; i < groupCount; i++) {
//
//			expandableList.expandGroup(i);
//
//		}
//		;
		//设置一级栏目前的小图标为null就是去掉默认的小图标

//		expandableList.setOnChildClickListener(new OnChildClickListener() {
//			public boolean onChildClick(ExpandableListView parent, View v,
//										int groupPosition, int childPosition, long id) {
//				Log.i(TAG, "ExpandableActivity----------------------groupPosition=" + groupPosition);
//				Log.i(TAG, "ExpandableActivity----------------------childPosition=" + childPosition);
////    Intent intent = new Intent(ExpandableActivity.this,ChildActivity.class);
////    startActivity(intent);
//				return false;
//			}
//
//		});
//		groupArray.add("第一行");
//		groupArray.add("第二行");
//		List<String> tempArray = new ArrayList<String>();
//		tempArray.add("第一条");
//		tempArray.add("第二条");
//		tempArray.add("第三条");
//		for (int index = 0; index < groupArray.size(); ++index) {
//			childArray.add(tempArray);
//		}
	}


//	public List<String> getGroupArray() {
//		return groupArray;
//	}
//
//	public List<List<String>> getChildArray() {
//		return childArray;
//	}
//private class ExpandableListViewChildClickListener	implements OnChildClickListener
//{
//	public boolean onChildClick(ExpandableListView parent,View v,int groupPosition,int childPosition,long id)
//	{
//		//在这里要计算position的位置
//		int len=0;
//		ArrayList al=null;
//		for(int i=0;i<groupPosition;i++)
//		{
//			al=childData.get(i);
//			len=len+al.size();
//		}
//
//		ARTICLE_POSITION=len+childPosition;
//		//进入报纸详情
//		Intent intent = new Intent(mActivity,ArticleDetailActivityForNewspaper.class);
//		startActivity(intent);
//
//		return	true;
//	}
//}

	@Override
	protected AppHandler getAppHandler() {
		return new AppHandler(mActivity) {

			@Override
			public void disposeMessage(Message msg) {
				switch (msg.what) {
					case AppConstants.RESULT_QUERY_NEWSPAPER_SUCCESS:

						NewsPaper NewsPaper = (NewsPaper) msg.obj;
						fillDataS(NewsPaper.getNewspaper().getArticles());
						updateExpandableListViewStatus();
						break;
					case AppConstants.RESULT_QUERY_NEWSPAPER_FAILED:

						break;
					default:
						break;
				}
			}

		};
	}

	private	void	updateExpandableListViewStatus()
	{
		expandableList.setVisibility(View.VISIBLE);
		mSimpleExpandableListAdapter.notifyDataSetChanged();

		for(int i = 0; i < mSimpleExpandableListAdapter.getGroupCount(); i++){
			expandableList.expandGroup(i);
		}
	}

	private void fillDataS(List<NewsPaperArticle> newsPaper) {
		//版面
		String page_tmp = "";
		String section_tmp = "";
		for (int i = 0; i < newsPaper.size(); i++) {
			NewsPaperArticle NewsPaperArticle = newsPaper.get(i);

			page_tmp = NewsPaperArticle.getPage();
			section_tmp = NewsPaperArticle.getSection();

			HashMap<String, String> groupMap = new HashMap<String, String>();
			groupMap.put(CONTENT_TAG, "第" + page_tmp + "版 " + section_tmp);
			groupData.add(groupMap);

			ArrayList<HashMap<String, String>> aChild = new ArrayList<HashMap<String, String>>();

			for (int j = i; j < newsPaper.size(); j++) {
				NewsPaperArticle ga = newsPaper.get(j);
				if (ga.getPage().equals(page_tmp)) {
					HashMap<String, String> childMap = new HashMap<String, String>();
					childMap.put(CONTENT_TAG, ga.getTitle());
					aChild.add(childMap);

					i = j;
				}
			}

			childData.add(aChild);
		}



	}

}