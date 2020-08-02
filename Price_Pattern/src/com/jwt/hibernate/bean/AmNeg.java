package com.jwt.hibernate.bean;

public class AmNeg {
	private int id;
	private int PERMNO;
	private String dateMax;
	private String dateMin;
	private int pattern;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPERMNO() {
		return PERMNO;
	}
	public void setPERMNO(int pERMNO) {
		PERMNO = pERMNO;
	}
	public String getDateMax() {
		return dateMax;
	}
	public void setDateMax(String dateMax) {
		this.dateMax = dateMax;
	}
	public String getDateMin() {
		return dateMin;
	}
	public void setDateMin(String dateMin) {
		this.dateMin = dateMin;
	}
	public int getPattern() {
		return pattern;
	}
	public void setPattern(int pattern) {
		this.pattern = pattern;
	}
	
	
	
}
