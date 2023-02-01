package com.sist.vo;
/*
 * NO      NOT NULL NUMBER         
TYPE             NUMBER         
NAME    NOT NULL VARCHAR2(34)   
SUBJECT NOT NULL VARCHAR2(1000) 
CONTENT NOT NULL CLOB           
REGDATE          DATE           
HIT              NUMBER         
 * 
 */
import java.util.*;
public class NoticeVO {
   private int no, hit,type;
   private String name,subject, content,dbday,prefix;
   private Date regdate;
   
   public String getPrefix() {
	return prefix;
   }
   public void setPrefix(String prefix) {
	this.prefix = prefix;
   }
   public int getType() {
	return type;
   }
   public void setType(int type) {
	this.type = type;
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