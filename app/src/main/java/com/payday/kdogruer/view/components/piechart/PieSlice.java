package com.payday.kdogruer.view.components.piechart;

import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Region;

public class PieSlice {
	private int color = Color.BLACK;
	private double value;
	private String title;
	private Path path;
	private Region region;
	private int thickness;
	private double calculatedPercantage;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public Path getPath() {
		return path;
	}
	public void setPath(Path path) {
		this.path = path;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public int getThickness() {
		return thickness;
	}
	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double getCalculatedPercantage() {
		return calculatedPercantage;
	}
	public void setCalculatedPercantage(double calculatedPercantage) {
		this.calculatedPercantage = calculatedPercantage;
	}
}
