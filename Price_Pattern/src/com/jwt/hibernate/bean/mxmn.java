package com.jwt.hibernate.bean;

public class mxmn implements java.io.Serializable{
	
	private int PERMNO;
	private String date;
	private int NAICS;
	private String extremaType;
	
	

	public int getPERMNO() {
		return PERMNO;
	}
	public void setPERMNO(int pERMNO) {
		PERMNO = pERMNO;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getNAICS() {
		return NAICS;
	}
	public void setNAICS(int nAICS) {
		NAICS = nAICS;
	}
	public String getExtremaType() {
		return extremaType;
	}
	public void setExtremaType(String extremaType) {
		this.extremaType = extremaType;
	}
	
	
	

}
