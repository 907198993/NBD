package cn.com.nbd.nbdmobile.adapter;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CycleViewPager extends ViewPager {

	public CycleViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CycleViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private float xDown;// 记录手指按下时的横坐标。
	private float xMove;// 记录手指移动时的横坐标。
	private float yDown;// 记录手指按下时的纵坐标。
	private float yMove;// 记录手指移动时的纵坐标。
	private boolean viewPagerScrolling = false;
	private boolean fatherScrolling = false;

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (this.getChildCount() < 2)
			return false;
		return super.onTouchEvent(ev);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		if (ev.getPointerCount() >= 2)
			return false;

		switch (ev.getAction() & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_POINTER_DOWN:
				xDown = ev.getRawX();
				yDown = ev.getRawY();
				fatherScrolling = false;
				break;
			case MotionEvent.ACTION_MOVE:
				xMove = ev.getRawX();
				yMove = ev.getRawY();
				if (fatherScrolling) {
					return false;
				}
				if (viewPagerScrolling) {
					return super.dispatchTouchEvent(ev);
				}

				if (Math.abs(yMove - yDown) < 10 && Math.abs(xMove - xDown) > 3) {
					this.getParent().requestDisallowInterceptTouchEvent(true);
					viewPagerScrolling = true;
				} else if (Math.abs(yMove - yDown) >= 10) {
					fatherScrolling = true;
					return false;
				} else
					return false;
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
				viewPagerScrolling = false;
				if (ev.getPointerCount() == 1)
					this.getParent().requestDisallowInterceptTouchEvent(false);
				break;
		}
		return super.dispatchTouchEvent(ev);
	}
}