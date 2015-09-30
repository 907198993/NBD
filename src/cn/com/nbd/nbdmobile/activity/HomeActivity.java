package cn.com.nbd.nbdmobile.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import cn.com.nbd.nbdmobile.R;
import cn.com.nbd.nbdmobile.config.AppPresences;
import cn.com.nbd.nbdmobile.config.BaseConfig;

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
		setContentView(R.layout.activity_main);
		initViews();
		// 有米广告配置 初始化应用的发布ID和密钥，以及设置测试模式
		// AdManager.getInstance(this).init(appContext.YOUMI_ID,
		// appContext.YOUMI_KEY, false);
		// // 请务必调用以下代码，告诉SDK应用启动，可以让SDK进行一些初始化操作。该接口务必在SDK的初始化接口之后调用。
		// OffersManager.getInstance(this).onAppLaunch();
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
				.setContent(new Intent(this, VideoListActivity.class)));
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
			case R.id.radio_user:
				tabHost.setCurrentTabByTag("user");
				break;
			case R.id.radio_setting:
				tabHost.setCurrentTabByTag("setting");
				break;
			default:
				break;
			}
		}
	};

}
