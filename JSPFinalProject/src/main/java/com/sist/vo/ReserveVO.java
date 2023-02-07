package com.sist.vo;
/*
 * 	RNO       NOT NULL NUMBER      
	FNO                NUMBER      
	ID                 VARCHAR2(20) 
	RDATE     NOT NULL VARCHAR2(20) 
	RTIME     NOT NULL VARCHAR2(20) 
	INWON              NUMBER      
	RESERVE_NO NOT NULL VARCHAR2(20) 
	OK                 CHAR(1)     
	RDGDATE            DATE
 */
import java.util.*;
public class ReserveVO {
	private int rno,fno,inwon;
	private String id,rdate,rtime,reserve_no,ok;
	private Date regdate;
	private FoodVO fvo=new FoodVO();
	
	public FoodVO getFvo() {
		return fvo;
	}
	public void setFvo(FoodVO fvo) {
		this.fvo = fvo;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public int getInwon() {
		return inwon;
	}
	public void setInwon(int inwon) {
		this.inwon = inwon;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public String getRtime() {
		return rtime;
	}
	public void setRtime(String rtime) {
		this.rtime = rtime;
	}
	public String getReserve_no() {
		return reserve_no;
	}
	public void setReserve_no(String reserve_no) {
		this.reserve_no = reserve_no;
	}
	public String getOk() {
		return ok;
	}
	public void setOk(String ok) {
		this.ok = ok;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
