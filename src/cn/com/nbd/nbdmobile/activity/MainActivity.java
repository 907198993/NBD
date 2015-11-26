package cn.com.nbd.nbdmobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.com.nbd.nbdmobile.R;
import cn.com.nbd.nbdmobile.adapter.NewsFragmentPagerAdapter;
import cn.com.nbd.nbdmobile.base.AppActivityManager;
import cn.com.nbd.nbdmobile.bean.TitleItem;
import cn.com.nbd.nbdmobile.config.AppPresences;
import cn.com.nbd.nbdmobile.fragment.InformationFragment;
import cn.com.nbd.nbdmobile.fragment.RollFragment;
import cn.com.nbd.nbdmobile.tool.BaseTools;
import cn.com.nbd.nbdmobile.view.ColumnHorizontalScrollView;

/**
 * 新闻页 包含4个栏目
 *
 * @author Dell
 */

public class MainActivity extends BaseActivity {
	private ColumnHorizontalScrollView mColumnHorizontalScrollView;
	LinearLayout mRadioGroup_content;
	LinearLayout ll_more_columns;
	RelativeLayout rl_column;
	private ViewPager mViewPager;
	private ImageView button_more_columns;
	LinearLayout linear;
	private ArrayList<TitleItem> userChannelList = new ArrayList<TitleItem>();
	private int columnSelectIndex = 0;
	public ImageView shade_left;
	public ImageView shade_right;
	private int mScreenWidth = 0;
	private int mItemWidth = 0;
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	private FragmentPagerAdapter adapter;
	// protected SlidingMenu side_drawer;

	private ProgressBar top_progress;
	private ImageView top_refresh;
	private ImageView top_head;
	private ImageView personCenter;//个人中心
	public final static int CHANNELREQUEST = 1;
	public final static int CHANNELRESULT = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mScreenWidth = BaseTools.getWindowsWidth(this);
		mItemWidth = mScreenWidth / 6;//     1/6
		userChannelList.add(new TitleItem(1, "滚动", 1, 1));
		userChannelList.add(new TitleItem(2, "资讯", 2, 1));
		userChannelList.add(new TitleItem(3, "理财", 3, 1));
		userChannelList.add(new TitleItem(4, "证券", 4, 1));
		initView();

	}

	/**
	 * ?????layout???
	 */
	private void initView() {
		mColumnHorizontalScrollView = (ColumnHorizontalScrollView) findViewById(R.id.mColumnHorizontalScrollView);
		mRadioGroup_content = (LinearLayout) findViewById(R.id.mRadioGroup_content);
		linear = (LinearLayout) findViewById(R.id.linear);
		// ll_more_columns = (LinearLayout) findViewById(R.id.ll_more_columns);
		rl_column = (RelativeLayout) findViewById(R.id.rl_column);
		// button_more_columns = (ImageView)
		// findViewById(R.id.button_more_columns);
		mViewPager = (ViewPager) findViewById(R.id.mViewPager);
		// shade_left = (ImageView) findViewById(R.id.shade_left);
		// shade_right = (ImageView) findViewById(R.id.shade_right);
		// top_head = (ImageView) findViewById(R.id.top_head);
		personCenter = (ImageView) findViewById(R.id.person_center);
		personCenter.setOnClickListener(this);
		// top_refresh = (ImageView) findViewById(R.id.top_refresh);
		// top_progress = (ProgressBar) findViewById(R.id.top_progress);
		// initColumnData();
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		linear.measure(w, h);
		int height = linear.getMeasuredHeight();
		//存储title的个高度值
		AppPresences.getInstance().putInt("titlehight", height);
		initTabColumn();
		initFragment();

		// button_more_columns.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// // Intent intent_channel = new Intent(getApplicationContext(),
		// ChannelActivity.class);
		// // startActivityForResult(intent_channel, CHANNELREQUEST);
		// // overridePendingTransition(R.anim.slide_in_right,
		// R.anim.slide_out_left);
		// }
		// });
		// top_head.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// if(side_drawer.isMenuShowing()){
		// side_drawer.showContent();
		// }else{
		// side_drawer.showMenu();
		// }
		// }
		// });
		// top_more.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// if(side_drawer.isSecondaryMenuShowing()){
		// side_drawer.showContent();
		// }else{
		// side_drawer.showSecondaryMenu();
		// }
		// }
		// });
		// setChangelView();
	}

	/**
	 * ?????????仯??????
	 */
	// private void setChangelView() {
	// initColumnData();
	// initTabColumn();
	// //initFragment();
	// }
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	/**
	 * ?????Column?????
	 */
	private void initTabColumn() {


		mRadioGroup_content.removeAllViews();
		int count = userChannelList.size();
		mColumnHorizontalScrollView.setParam(this, mScreenWidth,
				mRadioGroup_content, shade_left, shade_right, ll_more_columns,
				rl_column);
		for (int i = 0; i < count; i++) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					mItemWidth, LayoutParams.WRAP_CONTENT);
			params.leftMargin = 5;
			params.rightMargin = 5;


			// TextView localTextView = (TextView)
			// mInflater.inflate(R.layout.column_radio_item, null);
			TextView columnTextView = new TextView(this);
			columnTextView.setTextAppearance(this,
					R.style.top_category_scroll_view_item_text);
			// localTextView.setBackground(getResources().getDrawable(R.drawable.top_category_scroll_text_view_bg));
			//columnTextView.setBackgroundResource(R.drawable.radio_buttong_bg);
			columnTextView.setGravity(Gravity.CENTER);
			columnTextView.setPadding(5, 5, 5, 5);
			columnTextView.setId(i);
			columnTextView.setText(userChannelList.get(i).getName());
			columnTextView.setTextColor(getResources().getColorStateList(
					R.color.top_category_scroll_text_color_day));
			if (columnSelectIndex == i) {
				columnTextView.setSelected(true);
			}
			columnTextView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
						View localView = mRadioGroup_content.getChildAt(i);
						if (localView != v)
							localView.setSelected(false);
						else {
							localView.setSelected(true);
							mViewPager.setCurrentItem(i);
						}
					}
					Toast.makeText(getApplicationContext(),
							userChannelList.get(v.getId()).getName(),
							Toast.LENGTH_SHORT).show();
				}
			});
			mRadioGroup_content.addView(columnTextView, i, params);
		}
	}

	/**
	 * ????Column?????Tab
	 */
	private void selectTab(int tab_postion) {
		columnSelectIndex = tab_postion;
		for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
			View checkView = mRadioGroup_content.getChildAt(tab_postion);
			int k = checkView.getMeasuredWidth();
			int l = checkView.getLeft();
			int i2 = l + k / 2 - mScreenWidth / 2;
			// rg_nav_content.getParent()).smoothScrollTo(i2, 0);
			mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
			// mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
			// mItemWidth , 0);
		}
		// ?ж???????
		for (int j = 0; j < mRadioGroup_content.getChildCount(); j++) {
			View checkView = mRadioGroup_content.getChildAt(j);
			boolean ischeck;
			if (j == tab_postion) {
				ischeck = true;
			} else {
				ischeck = false;
			}
			checkView.setSelected(ischeck);
		}
	}

	/**
	 * ?????Fragment
	 */
	private void initFragment() {
		fragments.clear();// ???
		int count = userChannelList.size();
		for (int i = 0; i < count; i++) {
			Bundle data = new Bundle();
			data.putString("text", userChannelList.get(i).getName());
			data.putInt("id", userChannelList.get(i).getId());

			RollFragment RollFragment = new RollFragment();
			InformationFragment InformationFragment = new InformationFragment();
			RollFragment.setArguments(data);

			fragments.add(RollFragment);
			fragments.add(InformationFragment);
		}

		NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(
				getSupportFragmentManager(), fragments);

		mViewPager.setOffscreenPageLimit(0);
		mViewPager.setAdapter(mAdapetr);
		mViewPager.setOnPageChangeListener(pageListener);
	}


	/**
	 * ViewPager?л?????
	 */
	public OnPageChangeListener pageListener = new OnPageChangeListener() {

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int position) {
			mViewPager.setCurrentItem(position);
			selectTab(position);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private long mExitTime;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				AppActivityManager.getInstance().AppExit();
			}

			return true;
		}

		if (keyCode == KeyEvent.KEYCODE_MENU) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
			case CHANNELREQUEST:
				if (resultCode == CHANNELRESULT) {
					// setChangelView();
				}
				break;

			default:
				break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
			case R.id.person_center:
				Intent intent = new Intent(mActivity, CenterActivity.class);
				startActivityWithAnim(mActivity, intent);
				break;

		}
	}
}
