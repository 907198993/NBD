package cn.com.nbd.nbdmobile.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.hjh.inject.InjectCore;
import org.hjh.inject.InjectLayout;
import org.hjh.inject.InjectView;

import cn.com.nbd.nbdmobile.R;
import cn.com.nbd.nbdmobile.base.BaseLinearLayout;
import cn.com.nbd.nbdmobile.listener.ITitleClickListener;



@InjectLayout(layout = R.layout.title_layout_two)
public final class TitleLayout extends BaseLinearLayout {

	@InjectView(id = R.id.head_top_bar_left,onClick = "this")
	private TextView leftView;
	
	@InjectView(id = R.id.head_top_bar_right,onClick = "this")
	private TextView rightView;
	
	@InjectView(id = R.id.head_top_bar_title)
	private TextView titleView;
	
	@InjectView(id = R.id.head_top_bar_layout)
	private RelativeLayout layout;
	
	@InjectView(id = R.id.head_top_bar_nearright,onClick = "this")
	private TextView nearrightView;
	
	private Context mContext;
	private ITitleClickListener titleClickListener;
	
	public TitleLayout(Context context){
		this(context, null);
	}
	
	public TitleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		addView(InjectCore.injectObject(this, context, true));
	}
	
	/**
	 * 设置标题背景
	 */
	public void setBackground(int resid){
		layout.setBackgroundResource(resid);
	}
	
	/**
	 * 设置标题
	 * @param text
	 */
	public void setTitleText(String text){
		
		if(titleView != null){
			titleView.setText(text);
		}
	}
	
	public void setTitleText(int id){
		if(titleView != null){
			titleView.setText(getResources().getString(id));
		}
	}
	
	/**
	 * 设置标题颜色
	 * @param color
	 */
	public void setTitleTextColor(int color){
		
		if(titleView != null){
			titleView.setTextColor(color);
		}
	}
	
	/**
	 * 左按钮不可见
	 */
	public final void disableLeftButton(){
		
		if(leftView != null){
			leftView.setVisibility(View.INVISIBLE) ;
		}
	}
	
	public final void enableLeftButton(){
		
		if(leftView != null){
			leftView.setText(null) ;
		}
	}
	
	public final void enableLeftButtonImage(int rid){
	
		if(leftView != null){
			leftView.setVisibility(View.VISIBLE);
			leftView.setBackgroundResource(rid) ;
		}
	}
	
	/**
	 * 右按钮仅为文本
	 * @param text
	 */
	public final void enableLeftButtonText(String text){
		
		if(leftView != null){
			leftView.setVisibility(View.VISIBLE);
			leftView.setBackgroundDrawable(null);
			leftView.setText(text) ;
		}
		
	}
	
	public final void setLeftButtonPaading(int left, int top, int right, int bottom){
		
		if(leftView != null){
			leftView.setPadding(left, top, right, bottom);
		}
	}
	
	public final void setLeftButtonMargin(int marginLeft){
		
		if(leftView != null){
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) leftView.getLayoutParams();
			params.leftMargin = marginLeft;
			leftView.setLayoutParams(params);
		}
	}
	
	public final void setRightButtonPadding(int left, int top, int right, int bottom) {
		
		if(rightView != null){
			rightView.setPadding(left, top, right, bottom);
		}
	}
	
	public final void setRightButtonMargin(int marginRight){
		
		if(rightView != null){
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rightView.getLayoutParams();
			params.rightMargin = marginRight;
			rightView.setLayoutParams(params);
		}
	}
	
	
	public final void disableRightButton(){
		endableRightButton(View.INVISIBLE) ;
	}
	
	public final void enableRightButton(){
		endableRightButton(View.VISIBLE) ;
	}
	
	private final void endableRightButton(int show){
		
		if(rightView != null){
			rightView.setVisibility(show) ;
		}
		
	}
	
	public final void enableRightButtonText(int rid){
		enableRightButtonText(mContext.getString(rid)) ;
	}
	
	/**
	 * 右按钮仅为文本
	 * @param text
	 */
	public final void enableRightButtonText(String text){
		
		if(rightView != null){
			rightView.setVisibility(View.VISIBLE);
			rightView.setBackgroundDrawable(null);
			rightView.setText(text) ;
		}
		
	}
	
	/**
	 * 设置右按钮字体颜色
	 * @param color
	 */
	public final void setRightButtonTextColor(int color){
		if(rightView != null){
			rightView.setTextColor(color);
		}
	}
	
	/**
	 * 右按钮为图片
	 * @param rid
	 */
	public final void enableRightButtonImage(int rid){
		
		if(rightView != null){
			rightView.setVisibility(View.VISIBLE);
			rightView.setBackgroundResource(rid) ;
			rightView.setText("");
		}
	}

	public final void setNearRightButtonPadding(int left, int top, int right, int bottom) {
		
		if(rightView != null){
			rightView.setPadding(left, top, right, bottom);
		}
	}
	
	public final void setNearRightButtonMargin(int marginRight){
		
		if(rightView != null){
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rightView.getLayoutParams();
			params.rightMargin = marginRight;
			rightView.setLayoutParams(params);
		}
	}
	
	
	public final void disableNearRightButton(){
		endableNearRightButton(View.INVISIBLE) ;
	}
	
	public final void enableNearRightButton(){
		endableNearRightButton(View.VISIBLE) ;
	}
	
	private final void endableNearRightButton(int show){
		
		if(nearrightView != null){
			nearrightView.setVisibility(show) ;
		}
		
	}
	
	public final void enableNearRightButtonText(int rid){
		enableRightButtonText(mContext.getString(rid)) ;
	}
	
	/**
	 * 靠右按钮仅为文本
	 * @param text
	 */
	public final void enableNearRightButtonText(String text){
		
		if(nearrightView != null){
			nearrightView.setVisibility(View.VISIBLE);
			nearrightView.setBackgroundDrawable(null);
			nearrightView.setText(text) ;
		}
		
	}
	
	/**
	 * 设置靠右按钮字体颜色
	 * @param color
	 */
	public final void setNearRightButtonTextColor(int color){
		if(nearrightView != null){
			nearrightView.setTextColor(color);
		}
	}
	
	/**
	 * 靠右按钮为图片
	 * @param rid
	 */
	public final void enableNearRightButtonImage(int rid){
		
		if(nearrightView != null){
			nearrightView.setVisibility(View.VISIBLE);
			nearrightView.setBackgroundResource(rid) ;
			nearrightView.setText("");
		}
	}
	
	
	@Override
	public void onClick(View view) {
		if(null == titleClickListener){
			return;
		}
		
		if(view.getId() == R.id.head_top_bar_left){
			titleClickListener.onClickLeftView();
		}else if (view.getId() == R.id.head_top_bar_right) {
			titleClickListener.onClickRightView();
		}else if (view.getId() == R.id.head_top_bar_nearright) {
			titleClickListener.onClickNearRightView();
		}
	}

	public ITitleClickListener getTitleClickListener() {
		return titleClickListener;
	}

	public void setTitleClickListener(ITitleClickListener titleClickListener) {
		this.titleClickListener = titleClickListener;
	} 

}
