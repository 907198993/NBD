package cn.com.nbd.nbdmobile.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import org.hjh.inject.InjectCore;
import org.hjh.inject.InjectLayout;

import cn.com.nbd.nbdmobile.R;
import cn.com.nbd.nbdmobile.tool.SystemBarTintManager;

/**
 * Created by Dell on 2015/10/26.
 */

@InjectLayout(layout = R.layout.center_layout)
public class CenterActivity extends  BaseActivity {




	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTranslucentStatus();
		InjectCore.injectUI(this);

//		// 创建状态栏的管理实例
//		SystemBarTintManager tintManager = new SystemBarTintManager(this);
//		// 激活状态栏设置
//		tintManager.setStatusBarTintEnabled(true);
//		// 激活导航栏设置
//		tintManager.setNavigationBarTintEnabled(true);
//		// 设置一个颜色给系统栏
//		tintManager.setTintColor(Color.parseColor("#99000FF"));
//         /* 设置一个样式背景给导航栏 */
//		tintManager.setNavigationBarTintResource(R.drawable.my_tint);
//       // 设置一个状态栏资源
//		tintManager.setStatusBarTintDrawable(MyDrawable);




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

}
