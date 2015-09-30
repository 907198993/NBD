package cn.com.nbd.nbdmobile.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
/**
 * 滚动加载
 * @author hjh
 *	2015-2-4下午11:17:31
 *
 */
public class LoadScrollView extends ScrollView{

	private View parentView;
	private boolean first = false;
	private LoadNext loadNext;
	
	public LoadScrollView(Context context){
		this(context, null);
	}
	
	public LoadScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		if(getChildCount() > 0){
			parentView = getChildAt(0);
		}
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if(t + getHeight() >=  computeVerticalScrollRange() && !first){
			first = true;
		}
		
		if(t + getHeight() >=  computeVerticalScrollRange() && first){
			if(loadNext != null){
				try {
					loadNext.callBack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
//		if(parentView.getScrollY() > getMeasuredHeight() - parentView.getHeight() && !first){
//			first = true;
//			return true;
//		}
//		
//		if(parentView.getScrollY() > getMeasuredHeight() - parentView.getHeight() && first){
//			if(loadNext != null){
//				loadNext.callBack();
//			}
//			
//			return false;
//		}
		
		return super.onInterceptTouchEvent(ev);
	}
	
	public LoadNext getLoadNext() {
		return loadNext;
	}

	public void setLoadNext(LoadNext loadNext) {
		this.loadNext = loadNext;
	}

	public interface LoadNext{
		void callBack();
	}

}
