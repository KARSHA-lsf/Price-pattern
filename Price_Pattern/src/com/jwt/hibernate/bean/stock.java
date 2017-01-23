package com.jwt.hibernate.bean;

public class stock implements java.io.Serializable {
	
	private int PERMNO;
	private String 	date;
	private double PRC;
	private double RET;
	private int VOL;
	private double PseudoPRC;
	private double PseudoPRCn;
	private int RawVol;
	private double Turnover;
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
	public double getPRC() {
		return PRC;
	}
	public void setPRC(double pRC) {
		PRC = pRC;
	}
	public double getRET() {
		return RET;
	}
	public void setRET(double rET) {
		RET = rET;
	}
	public int getVOL() {
		return VOL;
	}
	public void setVOL(int vOL) {
		VOL = vOL;
	}
	public double getPseudoPRC() {
		return PseudoPRC;
	}
	public void setPseudoPRC(double pseudoPRC) {
		PseudoPRC = pseudoPRC;
	}
	public double getPseudoPRCn() {
		return PseudoPRCn;
	}
	public void setPseudoPRCn(double pseudoPRCn) {
		PseudoPRCn = pseudoPRCn;
	}
	public int getRawVol() {
		return RawVol;
	}
	public void setRawVol(int rawVol) {
		RawVol = rawVol;
	}
	public double getTurnover() {
		return Turnover;
	}
	public void setTurnover(double turnover) {
		Turnover = turnover;
	}
	
	
	
	
	
	

}
