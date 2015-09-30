package cn.com.nbd.nbdmobile.activity;

import org.hjh.async.framework.AppHandler;
import org.hjh.async.framework.LoadingDialog;
import org.hjh.image.display.SyncImageLoader;
import org.hjh.image.display.SyncImageLoader.OnImageLoadListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.nbd.nbdmobile.R;
import cn.com.nbd.nbdmobile.config.AppPresences;

import com.dpt.base.AppActivityManager;
import com.dpt.bean.ResultObject;
import com.dpt.config.BaseConfig;
import com.dpt.config.BaseConstants;
/**
 *  activity基类 
 * @author Dell
 *
 */
public abstract class AppBaseActivity extends FragmentActivity implements View.OnClickListener{

	private long loadTheme = R.style.LoadingDialog;//风格
	private long loadResId   = R.layout.loading_dialog_layout;//布局
	protected AppBaseActivity instance;
	protected LayoutInflater mInflater;
	protected AppHandler mHandler;
	protected Context mContext;
	protected Activity mActivity;
	protected SyncImageLoader mImageLoader;
	private BroadcastReceiver receiver;
	private DialogInterface.OnClickListener sureClick;
	private DialogInterface.OnClickListener cancleClick;
	private AlertDialog.Builder builder;
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
		
		public void callBackSize(int width, int height,View view) {
			int size = width + height;
			
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppActivityManager.getInstance().addActivity(this);
		mInflater = LayoutInflater.from(this);
		mContext = getApplicationContext();
		mActivity = this;
		getScreenSize();
		if(mImageLoader == null){
			mImageLoader = SyncImageLoader.getInstance(this,BaseConfig.PATH_IMAGE);
			mImageLoader.setDefaultWidth(BaseConfig.SCREEN_WIDTH);
			mImageLoader.setDefaultHeight(BaseConfig.SCREEN_HEIGHT);
		}
		
		
		
		mHandler = getAppHandler();
		if(mHandler.getConnectDialog() == null){
			mHandler.setConnectDialog(new LoadingDialog(mActivity, getIdByName("style", "LoadingDialog"), getConnectView()));
			mHandler.setConnectDialogProperty(getIdByName("style", "LoadingDialog"),getConnectView());
		}
		
		if(receiver == null){
			receiver = new BroadcastReceiver() {
				
				@Override
				public void onReceive(Context arg0, Intent intent) {
					if(BaseConstants.ACTION_SHARE_EVEVT.equals(intent.getAction())){
						String platform =  intent.getStringExtra("_platform");
						String status = intent.getStringExtra("_status");
						if("0".equals(status)){
							if("wechat".equals(platform)){
								showToast("分享成功");
							}else if("wechat_moments".equals(platform)){
								showToast("分享成功");
							}
						}else if ("-1".equals(status)) {
							showToast("分享失败");
						}
					}else if (BaseConfig.ACTION_RECEIVE_WIFI.equals(intent.getAction())) {
						synchronized (this) {
						 if(builder == null){
							   builder = new AlertDialog.Builder(mContext);
								builder.setTitle("更改网络连接");
								builder.setMessage("检测到新的wifi信息，是否去查看?");
								if(sureClick == null){
									sureClick = new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
											 startActivity(new Intent("android.settings.WIFI_SETTINGS"));   
										}
									};
								}
								
								if(cancleClick == null){
									cancleClick = new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
											
										}
									};
								}
								
								builder.setPositiveButton("确定", sureClick);
								builder.setNegativeButton("取消", cancleClick);
								builder.show(); 
						 }
						}
					 
					}
				}
			};
			
			IntentFilter filter = new IntentFilter();
			filter.addAction(BaseConfig.ACTION_RECEIVE_WIFI);
			filter.addAction(BaseConstants.ACTION_SHARE_EVEVT);
			registerReceiver(receiver, filter);
		}
		
	}
	
	protected View getConnectView(){
		return	LayoutInflater.from(mContext).inflate(getIdByName("layout", "loading_dialog_layout"), null);
	}
	
	private void getScreenSize(){
		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		BaseConfig.SCREEN_WIDTH = outMetrics.widthPixels;
		BaseConfig.SCREEN_HEIGHT = outMetrics.heightPixels;
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		try {
			super.onPause();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(receiver);
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		
	}

	@Override
	public void onBackPressed() {
		
		back();
		super.onBackPressed();
	}
	
	protected void back(){
		finish();
		AppActivityManager.getInstance().removeActivity(this);
		if(AppActivityManager.getInstance().getActivityStack().size() != 0){
			overridePendingTransition(getIdByName("anim", "push_not_move"),getIdByName("anim", "push_left_out"));
		}
	}

	protected AppHandler getAppHandler(){
		return new AppHandler(getApplicationContext());
	}
	
	public void showToast(String content){
		Toast toast = Toast.makeText(this, content,Toast.LENGTH_SHORT);
		toast.show();
	}
	
	public void showToast(int strId){
		Toast toast = Toast.makeText(this, getResources().getString(strId),Toast.LENGTH_SHORT);
		toast.show();
	}
	
	public void showToast(String content,int time){
		Toast toast  = Toast.makeText(this, content,time);
		toast.show();
	}
	

	/**
	 * 获取像素
	 */
	protected int getPixel(int id) {
		return	getResources().getDimensionPixelSize(id);
	}
	
	/**
	 * 获取颜色
	 * @param id
	 * @return
	 */
	protected int getColor(int id){
		return getResources().getColor(id);
	}

	public long getLoadTheme() {
		return loadTheme;
	}

	public void setLoadTheme(long loadTheme) {
		this.loadTheme = loadTheme;
	}

	public long getLoadResId() {
		return loadResId;
	}

	public void setLoadResId(long loadResId) {
		this.loadResId = loadResId;
	}
	
	/** start activity with left in anim */
	public  void startActivityWithAnim(Activity old,Intent intent){
		
		if(old == null || intent == null){
			return ;
		}
		
		// start activity
		old.startActivity(intent) ;
		// set adnim
		old.overridePendingTransition(getIdByName("anim", "push_left_in"),getIdByName("anim", "push_not_move"));
	}
	
	public  void startActivityWithAnim(Activity old,Intent intent,int requestCode){
		
		if(old == null || intent == null){
			return ;
		}
		
		// start activity
		old.startActivityForResult(intent, requestCode);
		// set adnim
		old.overridePendingTransition(getIdByName("anim", "push_left_in"),getIdByName("anim", "push_not_move"));
	}
	
	/** finish activity with left out anim */
	public  void finishWithAnim(Activity old){
		old.finish() ;
		old.overridePendingTransition(getIdByName("anim", "push_not_move"),getIdByName("anim", "push_left_out"));
	}
	
	public  void finishWithAnim(Activity old,int resultCode){
		old.setResult(resultCode);
		old.finish() ;
		old.overridePendingTransition(getIdByName("anim", "push_not_move"),getIdByName("anim", "push_left_out"));
	}
	
	public  int getIdByName(String className, String name) {  
        String packageName = getPackageName();  
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
	
	
	
	/**
	 * 是否登录
	 * @return
	 */
	protected boolean isLogin(){
		String name = AppPresences.getInstance().getString(BaseConfig.KEY_LOGIN_NAME);
		if (name == null || "".equals(name)) {
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 是否连接超时
	 * @param msg
	 * @param text 当前连接块
	 * @return
	 */
	protected boolean isConnectTimeOut(ResultObject result,String text){
		if(result.getCode() == BaseConstants.ERROR_HTTP_EXECUTE){
			showToast(text+" :连接超时");
			return true;
		}else if(result.getCode() == BaseConstants.ERROR_API_PARSER_JSON){
			showToast(text+" :json 解析错误");
			return true;
		}else if (result.getCode() == BaseConstants.ERROR_INPUT_PARAMETER) {
			showToast(text+" :"+result.getError());
			return true;
		}else if (result.getCode() == -1) {
			showToast(text+" :暂无更多数据");
			return true;
		}
		
		return false;
	}
	
	/**
	 * 设置连接提示
	 * @param text
	 */
	protected void setConnectHint(String text){
		try {
			TextView textView =	(TextView) mHandler.getConnectDialog().getContentView().findViewById(R.id.connect_hint);
			if(textView != null){
				textView.setText(text);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected String stringToJson(String string) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < string.length(); i++) {

			char c = string.charAt(i);
			switch (c) {
			case '\"':
				buffer.append("\\\"");
				break;
			case '\\':
				buffer.append("\\\\");
				break;
			case '/':
				buffer.append("\\/");
				break;
			case '\b':
				buffer.append("\\b");
				break;
			case '\f':
				buffer.append("\\f");
				break;
			case '\n':
				buffer.append("\\n");
				break;
			case '\r':
				buffer.append("\\r");
				break;
			case '\t':
				buffer.append("\\t");
				break;
			case ' ':
				break;
			default:
				buffer.append(c);
			}
		}

		return buffer.toString();
	}
	
}
