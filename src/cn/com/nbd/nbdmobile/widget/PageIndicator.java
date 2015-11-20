package cn.com.nbd.nbdmobile.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

public class PageIndicator extends View {
	private final static int DEFUALT_DOT_SPACING = 5;

	private float mScreenScale;
	private int mDotSpacing;

	private int mActiveDotIndex;
	private int mDotNumber;

	private Drawable mDot;
	private Drawable mActiveDot;

	public PageIndicator(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize();
	}

	public PageIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}

	public PageIndicator(Context context) {
		super(context);
		initialize();
	}

	private void initialize() {
		mScreenScale = getResources().getDisplayMetrics().density;
		mDotSpacing = (int) (DEFUALT_DOT_SPACING * mScreenScale);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (mDot == null || mActiveDot == null) {
			return;
		}

		int width = getWidth();
		int height = getHeight();
		int dotWidth = mDot.getIntrinsicWidth();
		int dotHeight = mDot.getIntrinsicHeight();
		int dotNumber = mDotNumber;
		int dotSpacing = mDotSpacing;
		int paddingLeftAndRight = width - dotNumber * dotWidth
				- (dotNumber - 1) * dotSpacing;
		int paddingTopAndBottom = (height - dotHeight) / 2;

		Drawable dot = null;
		for (int index = 0; index < dotNumber; index++) {
			if (index == mActiveDotIndex) {
				dot = mActiveDot;
			} else {
				dot = mDot;
			}
			dot.setBounds(
					paddingLeftAndRight + index * (dotWidth + dotSpacing),
					paddingTopAndBottom, paddingLeftAndRight + index
							* (dotWidth + dotSpacing) + dotWidth,
					paddingTopAndBottom + dotHeight);
			dot.draw(canvas);
		}

		super.onDraw(canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (mDot == null || mActiveDot == null) {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			return;
		}

		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);

		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);

		int measuredWidth = width;
		int measuredHeight = height;
		if (widthMode == MeasureSpec.AT_MOST) {
			int dotWidth = mDot.getIntrinsicWidth();
			measuredWidth = mDotNumber * dotWidth + (mDotNumber - 1)
					* mDotSpacing;
		}
		if (heightMode == MeasureSpec.AT_MOST) {
			measuredHeight = mDot.getIntrinsicHeight();
		}

		setMeasuredDimension(measuredWidth, measuredHeight);
	}

	public Drawable getDot() {
		return mDot;
	}

	public void setDot(Drawable dot) {
		this.mDot = dot;
		invalidate();
	}

	public Drawable getActiveDot() {
		return mActiveDot;
	}

	public void setActiveDot(Drawable activeDot) {
		this.mActiveDot = activeDot;
		invalidate();
	}

	public void setActivePage(int index) {
		this.mActiveDotIndex = Math.max(0, Math.min(index, mDotNumber));
		invalidate();
	}

	public void setPageNumber(int number) {
		this.mDotNumber = number;
		this.mActiveDotIndex = 0;
		requestLayout();
		invalidate();
	}

	public int getActivePageIndex() {
		return mActiveDotIndex;
	}

	static class SavedState extends BaseSavedState {
		int currentActiveIndicatorIndex;

		public SavedState(Parcel source) {
			super(source);
			currentActiveIndicatorIndex = source.readInt();
		}

		public SavedState(Parcelable superState) {
			super(superState);
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			super.writeToParcel(dest, flags);
			dest.writeInt(currentActiveIndicatorIndex);
		}

		public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {

			@Override
			public SavedState[] newArray(int size) {
				return new SavedState[size];
			}

			@Override
			public SavedState createFromParcel(Parcel source) {
				return new SavedState(source);
			}
		};
	}

	@Override
	protected Parcelable onSaveInstanceState() {
		SavedState savedState = new SavedState(super.onSaveInstanceState());
		savedState.currentActiveIndicatorIndex = mActiveDotIndex;
		return savedState;
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		SavedState savedState = (SavedState) state;
		super.onRestoreInstanceState(savedState.getSuperState());

		mActiveDotIndex = savedState.currentActiveIndicatorIndex;
	}
}
