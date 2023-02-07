package com.sist.vo;
/*
 *  DNO   NOT NULL NUMBER        
    RDATE NOT NULL NUMBER        
    TIME  NOT NULL VARCHAR2(100) 
 */
public class ReserveDateVO {
    private int dno,rdate;
    private String time;
	public int getDno() {
		return dno;
	}
	public void setDno(int dno) {
		this.dno = dno;
	}
	public int getRdate() {
		return rdate;
	}
	public void setRdate(int rdate) {
		this.rdate = rdate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
    
} 