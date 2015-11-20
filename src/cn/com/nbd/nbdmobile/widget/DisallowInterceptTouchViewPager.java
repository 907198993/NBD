package cn.com.nbd.nbdmobile.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;


public class DisallowInterceptTouchViewPager extends ViewPager {
	private float mPressedX;
	private float mPreesedY;

	private boolean mDragable;

	private int mTouchSlop;

	public DisallowInterceptTouchViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public DisallowInterceptTouchViewPager(Context context) {
		super(context);
		init();
	}

	public void init() {
		ViewConfiguration configuration = ViewConfiguration.get(getContext());

		// Determined user touch intent
		mTouchSlop = configuration.getScaledTouchSlop();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		int touchSlop = mTouchSlop;

		if (action == MotionEvent.ACTION_DOWN) {
			mPreesedY = event.getY();
			mPressedX = event.getX();
		}

		float xDiff = Math.abs(mPressedX - event.getX());
		float yDiff = Math.abs(mPreesedY - event.getY());

		mDragable = action == MotionEvent.ACTION_MOVE && xDiff > touchSlop
				&& yDiff < touchSlop;

		if (mDragable) {
			// Page scroll began, request parent stop to steal my touch event.
			// Just for the parent which will intercept the touch event, such as
			// ScrollView, ListView.
			requestDisallowInterceptTouchEvent(true);
		}

		if (action == MotionEvent.ACTION_CANCEL
				|| action == MotionEvent.ACTION_UP) {
			requestDisallowInterceptTouchEvent(false);
			mDragable = false;
		}

		if (xDiff < touchSlop && yDiff < touchSlop
				&& action == MotionEvent.ACTION_UP) {
			performClick();
		}

		return super.onTouchEvent(event);
	}
}