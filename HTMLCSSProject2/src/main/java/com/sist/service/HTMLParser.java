package com.sist.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// Document => HTML ������ ����ȴ�
import java.io.*;
import java.util.StringTokenizer;
public class HTMLParser {
	// ������ �ִ� ��� ��� 
	public void htmlGetData()
	{
		try
		{
			/*
			 * 	connect(): URL �̿�
			 * 	parse(): File�� �Ǿ����� �� �̿�
			 * ----------------------------- open Api
			 */
			Document doc=Jsoup.parse(new File("C:\\webDev\\webStudy\\HTMLCSSProject2\\src\\main\\webapp\\css\\css_2.html"));
			//System.out.println(doc.toString());
			/*Elements h1=doc.select("div.b h1"); // 1�� => Element, ������ => Elements
			//												�󼼺���			��� �б�
			System.out.println(h1.toString());
			System.out.println(h1.size());
			for(int i=0;i<h1.size();i++)
			{
				System.out.println(h1.get(i).text()); //<h1 class="f5">��</h1> =><h1></h1>���̿� �ִ� �����͸� ������ �´�
			}*/
			Element name=doc.select("div.a h1").get(2); //0������ ����
			System.out.println(name.text());
			Element f=doc.select("div.b h1").get(3); //0������ ����
			System.out.println(f.text());
		}catch(Exception ex) {}
	}
	// ������ ���� ��� ��� => <table> �� <td> �±� ������ �ȵǾ� ����. <ul> �� <li>�±� ����X, <dl> �� <dd>�±� ����X
	public void foodDetailData()
	{
		try
		{
			Document doc=Jsoup.parse(new File("C:\\webDev\\webStudy\\HTMLCSSProject2\\src\\main\\webapp\\css\\css_3.html"));
			//System.out.println(doc.toString());
			Elements detail=doc.select("table.info tbody tr th");
			String address="",tel="",type="",price="",parking="",time="",menu="";
			
			for(int i=0;i<detail.size();i++)
			{
				//System.out.println(detail.get(i).text());
				/*
				 * 	�ּ�
					��ȭ��ȣ
					���� ����
					���ݴ�
					����
					�����ð�
					����
					�� ����Ʈ
					�޴�
				 */
				String label=detail.get(i).text();
				if(label.equals("�ּ�"))
				{
					Element a=doc.select("table.info tbody tr td").get(i);
					address=a.text();
				}
				else if(label.equals("��ȭ��ȣ"))
				{
					Element a=doc.select("table.info tbody tr td").get(i);
					tel=a.text();
				}
				else if(label.equals("���� ����"))
				{
					Element a=doc.select("table.info tbody tr td").get(i);
					type=a.text();
				}
				else if(label.equals("���ݴ�"))
				{
					Element a=doc.select("table.info tbody tr td").get(i);
					price=a.text();
				}
				else if(label.equals("����"))
				{
					Element a=doc.select("table.info tbody tr td").get(i);
					parking=a.text();
				}
				else if(label.equals("�����ð�"))
				{
					Element a=doc.select("table.info tbody tr td").get(i);
					time=a.text();
				}
				else if(label.equals("�޴�"))
				{
					Element a=doc.select("table.info tbody tr td").get(i);
					menu=a.text();
				}
			}
			String addr1=address.substring(0,address.indexOf("��"));
			String addr2=address.substring(address.indexOf("��")+3);
			
			System.out.println("�ּ�:"+addr1);
			System.out.println("����:"+addr2);
			
			System.out.println("��ȭ:"+tel);
			System.out.println("���� ����:"+type);
			System.out.println("���ݴ�:"+price);
			System.out.println("�ð�:"+time);
			System.out.println("����:"+parking);
			StringTokenizer st=new StringTokenizer(menu,"��");
			while(st.hasMoreTokens())
			{
				System.out.println(st.nextToken()+"��");
			}
		}catch(Exception ex) {}
	}
	/*
	 * 	<td>�����Ͱ�</td> ==> doc.select("~~ td") => text()
	 */
	// �Ӽ��� �д� ��� <img src="�̹�����"> doc.select("~~ img") => attr("src")
	public void foodAttributeData()
	{
		try
		{
			Document doc=Jsoup.parse(new File("C:\\webDev\\webStudy\\HTMLCSSProject2\\src\\main\\webapp\\css\\css_4.html"));
			Elements image=doc.select("div.list-photo_wrap img.center-croping");
			System.out.println(image.toString());
			for(int i=0;i<image.size();i++)
			{
				System.out.println(image.get(i).attr("src"));
			}
		}catch(Exception ex) {}
	}
	// HTML �д� ���
	/*
	 * 	<div class="a">
	 *    <span>Hello</span>
	 *    <span>
	 *      <p>HTML/CSS</p>
	 *    </span>
	 *  </div>
	 *  
	 *  doc.select("div.a")�� ���� text()��û -> Hello HTML/CSS
	 *  doc.select("div.a")�� ���� html() ��û -> <span>Hello</span>
	 *  									   <span>
	 *  										  <p>HTML/CSS</p>
	 *  									   </span>
	 */
	public void htmlData()
	{
		try
		{
			Document doc=Jsoup.parse(new File("C:\\webDev\\webStudy\\HTMLCSSProject2\\src\\main\\webapp\\css\\css_5.html"));
			Element div=doc.selectFirst("div.a");
			System.out.println(div.text());
			System.out.println(div.html());
		}catch(Exception ex) {}
	}
	// �ڹٽ�ũ��Ʈ �д� ���
	public void scriptData()
	{
		try
		{
			Document doc=Jsoup.parse(new File("C:\\webDev\\webStudy\\HTMLCSSProject2\\src\\main\\webapp\\css\\css_6.html"));
			Element script=doc.selectFirst("script#reviewCountInfo");
			System.out.println(script.data()); // script�ȿ� �ִ� ������ �б� => data() �޼ҵ� �̿�
			//[{"action_value":1,"count":3},{"action_value":2,"count":12},{"action_value":3,"count":129}] => JSON
			String s=script.data();
			JSONParser jp=new JSONParser();
			/*
			 * 	1. HTML ������ ����: HTMLParser => Jsoup
			 *  2. JSON ������ ����: JSONParser(Ajax,Vue,React)
			 *  	=> ArrayList: []
			 *  	=> ~VO		: {}
			 *  3. XML ������ ����: DocumentBulider(Spring,Mybatis)
			 *  ------------------------------------- 3�� 
			 *  
			 *  [] => JSONArray
			 *  {} => JSONObject
			 */
			// => RestFul ==> Spring
			JSONArray arr=(JSONArray)jp.parse(s);
			//System.out.println(arr.toString());
			for(int i=0;i<arr.size();i++)
			{
				JSONObject obj=(JSONObject)arr.get(i);
				if(i==0)
				{
					System.out.print("����("+obj.get("count")+")");
				}
				else if(i==1)
				{
					System.out.print("������("+obj.get("count")+")");
				}
				else if(i==2)
				{
					System.out.print("���ִ�("+obj.get("count")+")");
				}
			}
		}catch(Exception ex) {}
	}
	/*
	 * 	text()
	 *  attr()
	 *  html()
	 *  data()
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HTMLParser hp=new HTMLParser();
		//hp.htmlGetData();
		//hp.foodDetailData();
		//hp.foodAttributeData();
		//hp.htmlData();
		hp.scriptData();

	}

}
