package com.payday.kdogruer.view.components.piechart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;

import java.util.ArrayList;

public class PieChart extends View implements AnimatorUpdateListener{

	private ArrayList<PieSlice> slices = new ArrayList<PieSlice>();
	private Paint paint = new Paint();
	private Path path = new Path();
	private ValueAnimator mAnimator;
	private int expandedThickness;
	private int nextSlice = -1;
	private boolean animating = false;
	private static final double minPercantageThreshold = 3;
	
	public PieChart(Context context) {
		super(context);
	}
	
	public PieChart(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
	    int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
	    expandedThickness = parentWidth/18;
	    openNextWithAnimate();
	    calculatePercantages();
	    this.setMeasuredDimension(parentWidth, parentHeight);
	    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@SuppressLint("DrawAllocation")
	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.TRANSPARENT);
		paint.reset();
		paint.setAntiAlias(true);
		float horizontalCenter, verticalCenter, radius;
		path.reset();
		
		float currentAngle = 0;
		float currentSweep = 0;
		
		float padding = 2;
		
		horizontalCenter = getWidth()/2;
		verticalCenter = getHeight()/2;
		if (horizontalCenter < verticalCenter){
			radius = horizontalCenter;
		} else {
			radius = verticalCenter;
		}
		radius -= padding;
		radius = radius - expandedThickness;
		
		for (PieSlice slice : slices){
			Path p = new Path();
			paint.setColor(slice.getColor());
			paint.setStyle(Style.FILL_AND_STROKE);
		    paint.setStrokeWidth(0);
		    paint.setShadowLayer(1, 0, 0, Color.GRAY);
			currentSweep = (float) (slice.getCalculatedPercantage() / 100 * 360);

			if(currentSweep == 360)
				currentSweep = (float) 359.99;
			
			p.arcTo(new RectF(horizontalCenter-radius-slice.getThickness(), verticalCenter-radius-slice.getThickness(), horizontalCenter+radius+slice.getThickness(), verticalCenter+radius+slice.getThickness()), currentAngle, currentSweep);
			p.arcTo(new RectF(horizontalCenter, verticalCenter, horizontalCenter, verticalCenter), (currentAngle+padding) + (currentSweep - padding), - (currentSweep-padding));
			p.close();
			slice.setPath(p);
			slice.setRegion(new Region((int)(horizontalCenter-radius), (int)(verticalCenter-radius), (int)(horizontalCenter+radius), (int)(verticalCenter+radius)));
			canvas.drawPath(p, paint);
			currentAngle = currentAngle+currentSweep;	
		}
	}

	@Override
	public void onAnimationUpdate(ValueAnimator animator) {
		
		int animValue = (Integer) animator.getAnimatedValue();
		
		for(int i = 0; i<slices.size(); i++){
			if(i==nextSlice)
				slices.get(i).setThickness(animValue);
			else if(nextSlice==-1 && i==0)
				slices.get(i).setThickness(animValue);
			else if(nextSlice==-1 && i!=0)
				slices.get(i).setThickness(0);
			else if(slices.get(i).getThickness()>0){
				int thickness = slices.get(i).getThickness();
				slices.get(i).setThickness(thickness-1);
			}
		}
		
		if(animValue == expandedThickness){
			if(nextSlice==-1) nextSlice++;
			nextSlice = (nextSlice + 1) % slices.size();
			animating = false;
		}
			
		
		postInvalidate();
	}

	public void openNextWithAnimate() {
		if(!animating){
			mAnimator = ValueAnimator.ofInt(0, expandedThickness);
			mAnimator.setDuration(300);
			mAnimator.addUpdateListener(this);
			animating = true;
			mAnimator.start();
		}
	}

	public int getActiveSlice() {
		return nextSlice;
	}

	public void setActiveSlice(int nextSlice) {
		this.nextSlice = nextSlice;
	}
	
	public ArrayList<PieSlice> getSlices() {
		return slices;
	}
	public void setSlices(ArrayList<PieSlice> slices) {
		this.slices = slices;
		postInvalidate();
	}
	public PieSlice getSlice(int index) {
		return slices.get(index);
	}
	public void addSlice(PieSlice slice) {
		this.slices.add(slice);
	}
	
	public void calculatePercantages(){
		double totalValue = 0;
		for (PieSlice tempSlice : slices){
			totalValue += tempSlice.getValue();
		}
		
		double minus = 0;
		for(PieSlice tempSlice : slices){
			if(tempSlice.getValue()/totalValue*100 < minPercantageThreshold){
				tempSlice.setCalculatedPercantage(minPercantageThreshold);
				minus = minus + (float) (minPercantageThreshold - tempSlice.getValue() / totalValue * 100);
			}
		}
		
		int overThresholdCount = 0;
		for (PieSlice tempSlice : slices){
			if(tempSlice.getValue()/totalValue*100 > minPercantageThreshold * 2)
				overThresholdCount++;
		}
		
		for (PieSlice tempSlice : slices){
			if(tempSlice.getValue() / totalValue * 100 > minPercantageThreshold * 2)
				tempSlice.setCalculatedPercantage(tempSlice.getValue() / totalValue * 100 - (minus/overThresholdCount));	
			else if (tempSlice.getValue() / totalValue * 100 >= minPercantageThreshold)
				tempSlice.setCalculatedPercantage(tempSlice.getValue() / totalValue * 100);
		}
	}
}
