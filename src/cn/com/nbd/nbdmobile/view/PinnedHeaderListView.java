package cn.com.nbd.nbdmobile.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

public class PinnedHeaderListView extends ListView {

	/**
	 * 更改标题的接口
	 * 
	 */
	public interface PinnedHeaderConfig {
		void configurePinnedHeader(View header, int position);
	}

	private PinnedHeaderConfig mAdapter;
	private View mHeaderView;
	private boolean mHeaderViewVisible;
	private int mHeaderViewWidth;
	private int mHeaderViewHeight;

	public PinnedHeaderListView(Context context) {
		super(context);
	}

	public PinnedHeaderListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PinnedHeaderListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * @param view 添加标题View
	 */
	public void setPinnedHeaderView(View view) {
		mHeaderView = view;
		if (mHeaderView != null) {
			setFadingEdgeLength(0);
		}
		showLog("setPinnedHeaderView");
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		super.setAdapter(adapter);
		mAdapter = (PinnedHeaderConfig) adapter;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// 测量长和宽
		if (mHeaderView != null) {
			showLog("onMeasure");
			measureChild(mHeaderView, widthMeasureSpec, heightMeasureSpec);
			mHeaderViewWidth = mHeaderView.getMeasuredWidth();
			mHeaderViewHeight = mHeaderView.getMeasuredHeight();
		}
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		if (mHeaderView != null) {
			showLog("onLayout");
			mHeaderView.layout(0, 0, mHeaderViewWidth, mHeaderViewHeight);
			moveTitle(getFirstVisiblePosition());
		}
	}

	/**
	 * 处理标题的移动
	 * 
	 * @param position
	 */
	public void moveTitle(int position) {
		if (mHeaderView == null) {
			return;
		}
		View firstView = getChildAt(0);
		int bottom = firstView.getBottom();
		int headerHeight = mHeaderView.getHeight();
		int y;
		if (bottom < headerHeight) {
			y = (bottom - headerHeight);
		} else {
			y = 0;
		}
		// 设置标题显示的内容
		mAdapter.configurePinnedHeader(mHeaderView, position);
		if (mHeaderView.getTop() != y) {
			mHeaderView.layout(0, y, mHeaderViewWidth, mHeaderViewHeight + y);
		}
		mHeaderViewVisible = true;
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if (mHeaderViewVisible) {
			showLog("dispatchDraw");
			drawChild(canvas, mHeaderView, getDrawingTime());
		}
	}

	public void showLog(String message) {
		Log.d("MESSAGE", message);
	}
}