package com.sist.vo;
import java.util.*;
/*
 * 	�����͸� ��Ƽ� ������ ����
 *  -------------------
 *   => JSP: Bean(�ٽɱ��)
 *   => Spring: Value Object(VO)
 *   => MyBatis: Data Transfor Object(DTO)
 *   -------------------------------------- ������ ����
 *   1) ����: private (������ ��ȣ)
 *   2) ������ �Ϲ� ��� ����
 *   	�б�(����� ������ �б�) / ����(�޸𸮿� ����)
 *   	----------------    -------------
 *   		getter				setter
 *   3) ���� ����
 *   	set������() ==>��) setName(),getName()..
 *   	boolean	  ==> set������, is������() ==> ���翩�� Ȯ��
 */
public class DataBoardVO {
	private int no,hit,filesize;
	private String name,subject,content,pwd,filename,dbday;
	private Date regdate;
	
	public String getDbday() {
		return dbday;
	}
	public void setDbday(String dbday) {
		this.dbday = dbday;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
