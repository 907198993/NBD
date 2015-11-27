package cn.com.nbd.nbdmobile.base;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.hjh.async.framework.AppHandler;
import org.hjh.async.framework.LoadingDialog;
import org.hjh.image.display.ImageLoader;
import org.hjh.image.display.SyncImageLoader;
import org.hjh.image.display.SyncImageLoader.OnImageLoadListener;

import java.text.DecimalFormat;

import cn.com.nbd.nbdmobile.R;
import cn.com.nbd.nbdmobile.api.BaseConstants;
import cn.com.nbd.nbdmobile.bean.ResultObject;
import cn.com.nbd.nbdmobile.config.BaseConfig;


public abstract class BaseLinearLayout extends LinearLayout implements View.OnClickListener{

	protected Context mContext;
	protected DecimalFormat decimalFormat=new DecimalFormat("0.00");
	protected AppHandler mHandler;
	protected LayoutInflater inflater ;
	protected SyncImageLoader imageLoader;
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
			
			
		}

		@Override
		public void onProgress(long l, long l1, View view) {

		}
	};

	protected OnImageLoadListener bgListener = new OnImageLoadListener() {
		
		@Override
		public void onImageLoad(Drawable drawable, ImageView view) {
			view.setBackgroundDrawable(drawable);
		}
		
		@Override
		public void onError(int resid, ImageView view) {
			view.setBackgroundResource(resid);
		}
		
		public void callBackSize(int width, int height,View view) {
			
			
		}

		@Override
		public void onProgress(long l, long l1, View view) {

		}
	};
	
	public OnImageLoadListener roundImageListener = new OnImageLoadListener() {
		
		@Override
		public void onImageLoad(Drawable drawable, ImageView view) {
			try {
				Bitmap bm = ((BitmapDrawable)drawable).getBitmap();
				bm = Bitmap.createScaledBitmap(bm, 120,120, false);
				Drawable roundDrawable = new BitmapDrawable(ImageLoader.toRoundCorner(bm, 20));
//				Drawable roundDrawable =	new BitmapDrawable(ImageLoader.toRoundCorner(((BitmapDrawable)drawable).getBitmap(), 40));
				view.setBackgroundDrawable(roundDrawable);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void onError(int resid, ImageView view) {
			view.setBackgroundResource(resid);
		}
		
		public void callBackSize(int width, int height,View view) {
			
			
		}

		@Override
		public void onProgress(long l, long l1, View view) {

		}
	};
	
	public BaseLinearLayout(Context context){
		this(context, null);
	}
	
	public BaseLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		inflater = LayoutInflater.from(mContext);
		if(imageLoader == null){
			if(!isInEditMode()){

				//do something
				//造成 error code
				imageLoader = SyncImageLoader.getInstance(mContext, BaseConfig.PATH_IMAGE);
			}

		}
		
		mHandler = getAppHandler();
		if(mHandler.getConnectDialog() == null){
			mHandler.setConnectDialog(new LoadingDialog(mContext,R.style.LoadingDialog, getConnectView()));
			mHandler.setConnectDialogProperty(R.style.LoadingDialog,getConnectView());
		}
	}
	
	private View getConnectView(){
		return	LayoutInflater.from(mContext).inflate(R.layout.loading_dialog_layout, null);
	}

	protected AppHandler getAppHandler() {
		return new AppHandler(mContext);
	}
	
	/**设置连接提示
	 *
	 */
	protected void setConnetHint(String text){
		try {
			TextView textView =	(TextView) mHandler.getConnectDialog().getContentView().findViewById(R.id.connect_hint);
			if(textView != null){
				textView.setText(text);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void showToast(String content){
		Toast toast = Toast.makeText(mContext, content,Toast.LENGTH_SHORT);
		if(isPad()){
			toast.setGravity(Gravity.CENTER, 0, 0);
		}
		
		toast.show();
	}
	
	public void showToast(int strId){
		Toast toast = Toast.makeText(mContext, getResources().getString(strId),Toast.LENGTH_SHORT);
		if(isPad()){
			toast.setGravity(Gravity.CENTER, 0, 0);	
		}
		
		toast.show();
	}
	
	public void showToast(String content,int time){
		Toast toast  = Toast.makeText(mContext, content,time);
		if(isPad()){
			toast.setGravity(Gravity.CENTER, 0, 0);
		}
		
		toast.show();
	}
	protected boolean isPad(){
		 return (getResources().getConfiguration().screenLayout
	                & Configuration.SCREENLAYOUT_SIZE_MASK)
	                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}
	
	protected int getPixSize(int id){
		return getResources().getDimensionPixelSize(id);
	}
	
	protected int getColor(int id){
		return getResources().getColor(id);
	}

	@Override
	public void onClick(View arg0) {
		
	}
	
	public void onResume(){
		
	}
	
	public void onReload(){
		
	}
	
	public void onDestory(){
		
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
}
