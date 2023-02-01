package com.sist.vo;
/*
 * RNO    NOT NULL NUMBER      
CATE_NO          NUMBER      
NO              NUMBER      
ID              VARCHAR2(20) 
NAME   NOT NULL VARCHAR2(34) 
MSG    NOT NULL CLOB        
REGDATE          DATE 
 */
import java.util.*;
public class AllReplyVO {
	private int rno,cate_no,no;
	private String id,name,msg,dbday;
	private Date regdate;
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getCate_no() {
		return cate_no;
	}
	public void setCate_no(int cate_no) {
		this.cate_no = cate_no;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDbday() {
		return dbday;
	}
	public void setDbday(String dbday) {
		this.dbday = dbday;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
