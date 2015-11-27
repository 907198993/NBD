package cn.com.nbd.nbdmobile.fragment;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.hjh.async.framework.AppHandler;
import org.hjh.async.framework.LoadingDialog;
import org.hjh.image.display.LoadImageOptions;
import org.hjh.image.display.SyncImageLoader;
import org.hjh.image.display.SyncImageLoader.OnImageLoadListener;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.com.nbd.nbdmobile.R;
import cn.com.nbd.nbdmobile.api.BaseConstants;
import cn.com.nbd.nbdmobile.bean.ResultObject;
import cn.com.nbd.nbdmobile.config.BaseConfig;
import cn.com.nbd.nbdmobile.view.PullToRefreshListView;

public abstract class BaseFragment extends Fragment implements
		View.OnClickListener {
	protected boolean isVisible;
	protected Activity mActivity;

	protected AppHandler mHandler;
	protected SyncImageLoader mImageLoader;
	protected Gson gson = new Gson();
	protected SyncImageLoader.OnImageLoadListener listener = new OnImageLoadListener() {

		@SuppressWarnings("deprecation")
		@Override
		public void onImageLoad(Drawable drawable, ImageView view) {
			view.setBackgroundDrawable(drawable);
		}

		@Override
		public void onError(int resid, ImageView view) {
			view.setBackgroundResource(resid);
		}

		@Override
		public void callBackSize(int width, int height, View arg2) {

		}

		@Override
		public void onProgress(long l, long l1, View view) {

		}
	};
	   protected OnImageLoadListener mCallBack = new OnImageLoadListener() {

		@Override
		public void onImageLoad(Drawable drawable, ImageView view) {
			view.setImageDrawable(drawable);
		}

		@Override
		public void onError(int resid, ImageView view) {
			view.setImageResource(resid);
		}

		public void callBackSize(int width, int height, View view) {
			int size = width + height;

		}

		   @Override
		   public void onProgress(long l, long l1, View view) {

		   }
	   };

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (mImageLoader == null) {
			mImageLoader = SyncImageLoader.getInstance(mActivity,
					BaseConfig.PATH_IMAGE);
			mImageLoader.setDefaultImageOptions(new LoadImageOptions().setListener(mCallBack).setResId(R.drawable.nbd_logo));
		}

		mHandler = getAppHandler();
		if (mHandler.getConnectDialog() == null) {
			mHandler.setConnectDialog(new LoadingDialog(mActivity, getIdByName(
					"style", "LoadingDialog"), getConnectView()));
		}
	}

	protected AppHandler getAppHandler() {
		return new AppHandler(getActivity().getApplicationContext());
	}

	private View getConnectView() {
		return LayoutInflater.from(mActivity).inflate(
				getIdByName("layout", "loading_dialog_layout"), null);
	}

	public int getIdByName(String className, String name) {
		String packageName = mActivity.getPackageName();
		Class r = null;
		int id = 0;
		try {
			r = Class.forName(packageName + ".R");

			Class[] classes = r.getClasses();
			Class desireClass = null;

			for (int i = 0; i < classes.length; ++i) {
				if (classes[i].getName().split("\\$")[1].equals(className)) {
					desireClass = classes[i];
					break;
				}
			}

			if (desireClass != null)
				id = desireClass.getField(name).getInt(desireClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		return id;
	}

	public void showToast(String content) {
		Toast toast = Toast.makeText(mActivity, content, Toast.LENGTH_SHORT);
		if (isPad()) {
			toast.setGravity(Gravity.CENTER, 0, 0);
		}

		toast.show();
	}

	public void showToast(int strId) {
		Toast toast = Toast.makeText(mActivity,
				getResources().getString(strId), Toast.LENGTH_SHORT);
		if (isPad()) {
			toast.setGravity(Gravity.CENTER, 0, 0);
		}

		toast.show();
	}

	public void showToast(String content, int time) {
		Toast toast = Toast.makeText(mActivity, content, time);
		if (isPad()) {
			toast.setGravity(Gravity.CENTER, 0, 0);
		}

		toast.show();
	}

	/**
	 * �Ƿ���pad
	 * 
	 * @return
	 */
	protected boolean isPad() {
		return (mActivity.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

	/**
	 * ��ȡ����
	 */
	protected int getPixel(int id) {
		return getResources().getDimensionPixelSize(id);
	}

	/**
	 * ��ȡ��ɫ
	 * 
	 * @param id
	 * @return
	 */
	protected int getColor(int id) {
		return getResources().getColor(id);
	}

	@Override
	public void onClick(View v) {

	}

	protected boolean isConnectTimeOut(ResultObject result, String text) {
		if (result.getStatus_code() == BaseConstants.ERROR_HTTP_EXECUTE) {
			showToast(text + " :连接超时");
			return true;
		} else if (result.getStatus_code() == BaseConstants.ERROR_API_PARSER_JSON) {
			showToast(text + " :json 解析错误");
			return true;
		} else if (result.getStatus_code() == BaseConstants.ERROR_INPUT_PARAMETER) {
			showToast(text + " :" + result.getMsg());
			return true;
		} else if (result.getStatus_code() == -1) {
			showToast(text + " :暂无更多数据");
			return true;
		}

		return false;
	}

	/**
	 * ȡ��״̬���ĸ�
	 * 
	 * @return
	 */
	protected int getStatusBarHeight() {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		BaseConfig.STATUS_BAR_HEIGHT = statusBarHeight;
		return statusBarHeight;
	}

	protected void setLastUpdateTime(PullToRefreshListView refreshListView) {
		String text = formatDateTime(System.currentTimeMillis());
		refreshListView.setLastUpdatedLabel(text);
	}

	public SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");

	public String formatDateTime(long time) {
		if (0 == time) {
			return "";
		}

		return mDateFormat.format(new Date(time));
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (getUserVisibleHint()) {
			isVisible = true;
			onVisible();
		} else {
			isVisible = false;
			onInvisible();
		}
	}

	protected void onVisible() {
		lazyLoad();
	}

	protected abstract void lazyLoad();

	protected void onInvisible() {
	}

}
