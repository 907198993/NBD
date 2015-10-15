package cn.com.nbd.nbdmobile.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TabHost;

import cn.com.nbd.nbdmobile.R;
import cn.com.nbd.nbdmobile.config.AppPresences;

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
		tabHost.addTab(tabHost.newTabSpec("newsPaper").setIndicator("newsPaper")
				.setContent(new Intent(this, NewsPaperActivity.class)));
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
				tabHost.setCurrentTabByTag("setting");
				break;
			default:
				break;
			}
		}
	};

}
