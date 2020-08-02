package com.jwt.hibernate.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Company implements Serializable{
	@Id
	private int PERMNO;
	
	@Id
	private String COMNAM;
	
	@Id
	private String TSYMBOL;
	
	@Id
	private int NAICS;
	
	public int getPERMNO() {
		return PERMNO;
	}
	public void setPERMNO(int pERMNO) {
		PERMNO = pERMNO;
	}
	public String getCOMNAM() {
		return COMNAM;
	}
	public void setCOMNAM(String cOMNAM) {
		COMNAM = cOMNAM;
	}
	public String getTSYMBOL() {
		return TSYMBOL;
	}
	public void setTSYMBOL(String tSYMBOL) {
		TSYMBOL = tSYMBOL;
	}
	public int getNAICS() {
		return NAICS;
	}
	public void setNAICS(int nAICS) {
		NAICS = nAICS;
	}
}
