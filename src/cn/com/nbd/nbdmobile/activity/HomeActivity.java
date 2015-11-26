package cn.com.nbd.nbdmobile.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TabHost;

import cn.com.nbd.nbdmobile.R;
import cn.com.nbd.nbdmobile.config.AppPresences;
import cn.com.nbd.nbdmobile.tool.SystemBarTintManager;

@SuppressWarnings("deprecation")
public class HomeActivity extends TabActivity {

	TabHost tabHost;
	TabHost.TabSpec tabSpec;
	RadioGroup radioGroup;
	RelativeLayout layout;
	boolean measure;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	//	setTranslucentStatus();
		setContentView(R.layout.activity_main);
		initViews();

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
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void initViews() {
		layout = (RelativeLayout) findViewById(R.id.layout_bottom);
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		layout.measure(w, h);
		int height = layout.getMeasuredHeight();
		AppPresences.getInstance().putInt("tabhight", height);
		tabHost = getTabHost();
		tabHost.addTab(tabHost.newTabSpec("news").setIndicator("News")
				.setContent(new Intent(this, MainActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("video").setIndicator("video")
				.setContent(new Intent(this, PlayerActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("newsPaper").setIndicator("newsPaper")
				.setContent(new Intent(this, NewsPaperActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("activity").setIndicator("activity")
				.setContent(new Intent(this, NewsActivityActivity.class)));
		radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
		radioGroup.setOnCheckedChangeListener(checkedChangeListener);

	}

	private final OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.radio_news:
				tabHost.setCurrentTabByTag("news");
				break;
			case R.id.radio_video:
				tabHost.setCurrentTabByTag("video");
				break;
			case R.id.radio_newspaper:
				tabHost.setCurrentTabByTag("newsPaper");
				break;
			case R.id.radio_setting:
				tabHost.setCurrentTabByTag("activity");
				break;
			default:
				break;
			}
		}
	};

}
