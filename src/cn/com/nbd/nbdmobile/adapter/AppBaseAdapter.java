package cn.com.nbd.nbdmobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import org.hjh.image.display.SyncImageLoader;
import org.hjh.image.display.SyncImageLoader.OnImageLoadListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 适配基类
 * @author hjh
 * 2014-12-24下午3:29:41
 * @param <T>
 */
public abstract class AppBaseAdapter<T> extends BaseAdapter {

	protected Context mContext;
	protected Activity mActivity;
	protected List<T> mList = new ArrayList<T>();
	protected SyncImageLoader mImageLoader;
	protected DecimalFormat decimalFormat=new DecimalFormat("0.00");
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
	
	public AppBaseAdapter(Context context){
		mContext = context;
		if(context instanceof Activity){
			mActivity = (Activity) context;
		}
		
		if(mImageLoader == null){
			mImageLoader = SyncImageLoader.getInstance(mContext, "image/");
		}
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public T getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/** 清除当前Adapter 的数据 */
	public void clear(){
		mList.clear();
		notifyDataSetChanged();
	}

	public List<T> getList() {
		return mList;
	}

	public void setList(List<T> list) {
		this.mList = list;
		notifyDataSetChanged();
	}
	
	protected void showHint(String text){
		try {
			Toast toast = Toast.makeText(mContext, text,Toast.LENGTH_SHORT);
			if(isPad()){
				toast.setGravity(Gravity.CENTER, 0, 0);
			}
			
			toast.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	protected int getDimen(int id){
		return mContext.getResources().getDimensionPixelSize(id);
	}
	
	/**
	 * 判断是否为pad
	 * @return
	 */
	protected boolean isPad(){
		 return (mContext.getResources().getConfiguration().screenLayout
	                & Configuration.SCREENLAYOUT_SIZE_MASK)
	                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

}
