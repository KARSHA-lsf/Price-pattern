
package com.jwt.hibernate.bean;

public class companydetails {
	private int PERMNO;
	private String Date;
	private double PRC;
	private double Pseudo_PRC;
	private double Turnover;
	
	public companydetails(int PERMNO, String Date, double PRC, double Pseudo_PRC, double Turnover){
		this.PERMNO = PERMNO;
		this.Date = Date;
		this.PRC = PRC;
		this.Pseudo_PRC = Pseudo_PRC;
		this.Turnover = Turnover;
	}
	
	public companydetails() {

	}

	public int getPERMNO() {
		return PERMNO;
	}
	public void setPERMNO(int pERMNO) {
		PERMNO = pERMNO;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public double getPRC() {
		return PRC;
	}
	public void setPRC(double pRC) {
		PRC = pRC;
	}
	public double getPseudo_PRC() {
		return Pseudo_PRC;
	}
	public void setPseudo_PRC(double pseudo_PRC) {
		Pseudo_PRC = pseudo_PRC;
	}
	public double getTurnover() {
		return Turnover;
	}
	public void setTurnover(double turnover) {
		Turnover = turnover;
	}
	

}
