package com.payday.kdogruer.view.components.animation;

import android.view.View;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.widget.RelativeLayout.LayoutParams;

public class ScaleAnimToShowRelative extends ScaleAnimation {
	private View mView;
	private LayoutParams mLayoutParams;
	private int mMarginBottomFromY, mMarginBottomToY;

	public ScaleAnimToShowRelative(float toX, float fromX, float toY, float fromY,
			int duration, View view) {
		super(fromX, toX, fromY, toY);
		setDuration(duration);
		mView = view;
		mLayoutParams = (LayoutParams) view.getLayoutParams();
		mView.setVisibility(View.VISIBLE);
		int height = mView.getHeight();
		mMarginBottomFromY = 0;
		mMarginBottomToY = height;
	}

	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		super.applyTransformation(interpolatedTime, t);
		if (interpolatedTime < 1.0f) {
			int newMarginBottom = (int) ((mMarginBottomToY - mMarginBottomFromY) * interpolatedTime)
					- mMarginBottomToY;
			mLayoutParams.setMargins(mLayoutParams.leftMargin,
					mLayoutParams.topMargin, mLayoutParams.rightMargin,
					newMarginBottom);
			mView.getParent().requestLayout();
		}
	}
}