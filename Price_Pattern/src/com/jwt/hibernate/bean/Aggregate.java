package com.jwt.hibernate.bean;

public class Aggregate {
	
	private String DATE;
	private double GAIN;
	private double LOSS;
	private double DIFF_GAIN;
	private double DIFF_LOSS;
	
	private int max_count;
	private int min_count;
	
	public double getDIFF_GAIN() {
		return DIFF_GAIN;
	}
	public void setDIFF_GAIN(double dIFF_GAIN) {
		DIFF_GAIN = dIFF_GAIN;
	}
	public double getDIFF_LOSS() {
		return DIFF_LOSS;
	}
	public void setDIFF_LOSS(double dIFF_LOSS) {
		DIFF_LOSS = dIFF_LOSS;
	}
	public int getMax_count() {
		return max_count;
	}
	public void setMax_count(int max_count) {
		this.max_count = max_count;
	}
	public int getMin_count() {
		return min_count;
	}
	public void setMin_count(int min_count) {
		this.min_count = min_count;
	}
	public String getDATE() {
		return DATE;
	}
	public void setDATE(String dATE) {
		DATE = dATE;
	}
	public double getGAIN() {
		return GAIN;
	}
	public void setGAIN(double gAIN) {
		GAIN = gAIN;
	}
	public double getLOSS() {
		return LOSS;
	}
	public void setLOSS(double lOSS) {
		LOSS = lOSS;
	}

}
