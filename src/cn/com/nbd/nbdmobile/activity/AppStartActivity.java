package cn.com.nbd.nbdmobile.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import cn.com.nbd.nbdmobile.R;

public class AppStartActivity extends Activity implements OnClickListener {

	private ImageView firstImage = null;
	private ImageView secondImage = null;
	private int runCount = 0;// 线程执行的次数
	private Animation inAnimation;// 显示图片的动画
	private static final int SHOW_TOAST = 1;
	final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_TOAST:
				Toast.makeText(AppStartActivity.this, "点击广告",Toast.LENGTH_SHORT).show();
				 Intent intent = new Intent(AppStartActivity.this,
						 HomeActivity.class);
				 startActivity(intent);
				 finish();
				break;

			default:
				break;
			}
		}
	};

	private Boolean flag = false;// 是否点击广告

	private Timer mTimer = null;
	private TimerTask mTimerTask = null;
	private long delay = 4000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_start);

		firstImage = (ImageView) findViewById(R.id.first_img);// 显示的第一张图
		secondImage = (ImageView) findViewById(R.id.second_img);// 渐入的第二张图

		firstImage.setOnClickListener(this);
		secondImage.setOnClickListener(this);

		// inAnimation = AnimationUtils.loadAnimation(this, R.anim.fade);//
		// 定义一个渐入的动画

		// 初始化
		final Animation inAnimation = new AlphaAnimation(0.1f, 1.0f);
		// 设置动画时间
		inAnimation.setDuration(3000);

		adCanClick();// 这样设置是为了在动画播放完毕之后可以点击广告图片，否则可能会出现异常

		runCount = 0;// 全局变量，用于判断是否是第一次执行
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (runCount == 1) {// 第一次执行则关闭定时执行操作
					// 在此处添加执行的代码
					secondImage.setVisibility(View.VISIBLE);
					secondImage.startAnimation(inAnimation);

					startTimer();

					handler.removeCallbacks(this);// 停止计时器:删除指定的Runnable对象，使线程对象停止运行
				}

				if (runCount == 0) {// 只让该线程执行一次
					handler.postDelayed(this, 100);// 而Runnable中的150毫秒表示每隔150毫秒秒执行一次你的功能模块；
				}
				runCount++;
			}

		};

		handler.postDelayed(runnable, 1000);// 打开定时器，执行操作:postDelay中的1秒表示程序运行到此处延迟一秒开启这个handler
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.first_img:
			flag = true;// 说明在广告展示的三秒之内点击了查看广告
			stopTimer();// 先关闭
			startTimer();// 再打开
			break;

		case R.id.second_img:
			flag = true;// 说明在广告展示的三秒之内点击了查看广告
			stopTimer();
			startTimer();
			break;
		default:
			break;
		}
	}

	/**
	 * 打开timer
	 */
	private void startTimer() {
		if (mTimer == null) {
			mTimer = new Timer();
		}

		if (mTimerTask == null) {
			mTimerTask = new TimerTask() {
				@Override
				public void run() {
					if (flag) {
						// Toast.makeText(AppStartActivity.this, "点击广告",
						// 1).show();
						// Intent intent = new Intent(AppStartActivity.this,
						// HomeActivity.class);
						// startActivity(intent);
						// 执行数据操作，不涉及到UI
						Message msg = new Message();
						msg.what = SHOW_TOAST;
						// 这三句可以传递数据

						handler.sendMessage(msg); // 向Handler发送消息,更新UI
						stopTimer();
					} else {
						Intent intent = new Intent(AppStartActivity.this,
								HomeActivity.class);
						startActivity(intent);
						finish();
						stopTimer();
					}
				}
			};
		}

		if (flag) {// 如果点击按钮了，则重设delay的时间，从而使立即执行该操作
			delay = 100;
		}

		if (mTimer != null && mTimerTask != null)
			mTimer.schedule(mTimerTask, delay);
	}

	/**
	 * 关闭timer
	 */
	private void stopTimer() {

		if (mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}

		if (mTimerTask != null) {
			mTimerTask.cancel();
			mTimerTask = null;
		}
	}

	/**
	 * 广告可以点击
	 */
	private void adCanClick() {
		firstImage.setClickable(false);
		secondImage.setClickable(false);
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				firstImage.setClickable(true);
				secondImage.setClickable(true);
			}
		}, 2100);// 2100这个时间是启动到动画播放完毕的时间
	}

}
