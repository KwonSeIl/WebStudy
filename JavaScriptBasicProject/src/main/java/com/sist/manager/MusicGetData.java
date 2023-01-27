package com.sist.manager;
import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
/*
 	[{"singer":"NewJeans","album":"NewJeans 'OMG'","title":"Ditto","poster":"","key":"qaO0Wq3Acfs"},
 {"singer":"NewJeans","album":"NewJeans 'OMG'","title":"OMG","poster":"","key":"hc32lb0po9U"},
 {"singer":"NewJeans","album":"NewJeans 1st EP 'New Jeans'","title":"Hype boy","poster":"","key":"11cta61wi0g"},
{"singer":"���� (YOUNHA)","album":"YOUNHA 6th Album Repackage 'END THEORY : Final Edition'","title":"����� ����","poster":"","key":"mnpQsM-tqQU"},{"singer":"LE SSERAFIM (��������)","album":"ANTIFRAGILE","title":"ANTIFRAGILE","poster":"","key":"pyf8cbqyfPs"},{"singer":"NewJeans","album":"NewJeans 1st EP 'New Jeans'","title":"Attention","poster":"","key":"CHp0Kaidr14"},{"singer":"IVE (���̺�)","album":"After LIKE","title":"After LIKE","poster":"","key":"F0B7HDiY-10"},{"singer":"�ӿ���","album":"�Ż�� �ư��� OST Part.2","title":"����� �� ������","poster":"","key":"pBEAzM2TRmE"},{"singer":"�¾�","album":"VIBE","title":"VIBE (Feat. Jimin of BTS)","poster":"","key":"cXCBiF67jLM"},{"singer":"�ӿ���","album":"IM HERO","title":"�츮���� ��罺","poster":"","key":"YKenCC4OAyk"},{"singer":"�̿���","album":"��̴��Ӵ� 11 Episode 3","title":"NOT SORRY (Feat. pH-1) (Prod. by Slom)","poster":"","key":"jvqbOU3gSzw"},{"singer":"�ӿ���","album":"IM HERO","title":"�ٽ� ���� �� ������","poster":"","key":"sHqFqWDviBg"},{"singer":"(����)���̵�","album":"I love","title":"Nxde","poster":"","key":"fCO7f0SmrDc"},{"singer":"IVE (���̺�)","album":"LOVE DIVE","title":"LOVE DIVE","poster":"","key":"Y8JFxS1HlDo"},{"singer":"NewJeans","album":"NewJeans 1st EP 'New Jeans'","title":"Cookie","poster":"","key":"VOmIplFAGeg"},{"singer":"�ӿ���","album":"������ �̽���Ʈ�� ����� Ư����","title":"���� ���� �Ͼ��","poster":"","key":"kSzraUekkNE"},{"singer":"�ӿ���","album":"Polaroid","title":"London Boy","poster":"","key":"TMfvkhkALbU"},{"singer":"���� (ZICO)","album":"��Ʈ�� �� ������(SMF) Original Vol.3 (��޹̼�)","title":"���� (Prod. by ZICO) (Feat. ȣ�̵�)","poster":"","key":"azaZt7eccnc"},{"singer":"NCT DREAM","album":"Candy - Winter Special Mini Album","title":"Candy","poster":"","key":"zuoSn3ObMz4"},{"singer":"�ӿ���","album":"Polaroid","title":"Polaroid","poster":"","key":"I82pbW2y_cc"},{"singer":"�ӿ���","album":"IM HERO","title":"������","poster":"","key":"jP2J0qnFtV4"},{"singer":"�ӿ���","album":"IM HERO","title":"�ƹ���","poster":"","key":"r7B_9-rj9bI"},{"singer":"Charlie Puth","album":"CHARLIE","title":"I Don't Think That I Like Her","poster":"","key":"bOVzVuB5DpY"},{"singer":"�ӿ���","album":"IM HERO","title":"A bientot","poster":"","key":"zLFypmn42NE"},{"singer":"�ӿ���","album":"IM HERO","title":"���� �� ���� �״�","poster":"","key":"OpZIaI-J0uk"},{"singer":"�ӿ���","album":"IM HERO","title":"����� ��¥","poster":"","key":"CbhiBN5wpCc"},{"singer":"���� (Tei)","album":"Monologue","title":"Monologue","poster":"","key":"i-u2TTV22UE"},{"singer":"��θ��� (MeloMance)","album":"����ΰ� �� (�系�¼� OST ����� Ʈ��)","title":"����ΰ� ��","poster":"","key":"McidaTgrQB0"},{"singer":"�ӿ���","album":"IM HERO","title":"�λ�����","poster":"","key":"7EC6RGJ8BOE"},{"singer":"�ӿ���","album":"IM HERO","title":"��������","poster":"","key":"P2FcQvCbDWg"},{"singer":"BLACKPINK","album":"BORN PINK","title":"Shut Down","poster":"","key":"POe9SOEKotk"},{"singer":"�ӿ���","album":"IM HERO","title":"�����ڸ�","poster":"","key":"Znpnf1HUmQ4"},{"singer":"WSG���ʺ� (����G)","album":"WSG���ʺ� 1��","title":"�׶� �� ���� �״�� (�ױױ�)","poster":"","key":"aH-IdX8U6jc"},{"singer":"Charlie Puth","album":"CHARLIE","title":"That's Hilarious","poster":"","key":"MPP2Q9JBvbg"},{"singer":"(����)���̵�","album":"I NEVER DIE","title":"TOMBOY","poster":"","key":"Jh4QFaPmdss"},{"singer":"WSG���ʺ� (4FIRE)","album":"WSG���ʺ� 1��","title":"����;���","poster":"","key":"B69HTHBsHCQ"},{"singer":"BLACKPINK","album":"BORN PINK","title":"Pink Venom","poster":"","key":"gQlMMD8auMs"},{"singer":"The Kid LAROI & Justin Bieber","album":"Stay","title":"Stay","poster":"","key":"Ec7TN_11az8"},{"singer":"Crush","album":"Rush Hour","title":"Rush Hour (Feat. j-hope of BTS)","poster":"","key":"PS0qkO5qty0"},{"singer":"���ð�","album":"������ �� �״� OST Part.7 (SBS ������)","title":"���� ��� ����","poster":"","key":"1qzbXDsUd2E"},{"singer":"������ (JUSTHIS) & R.Tee & ������ (DON MALIK) & �㼺�� (Huh) & KHAN & �ƴ�� (Mckdaddy) & Los","album":"��̴��Ӵ� 11 Episode 1","title":"���̿��� (MY WAY) (Prod. by R.Tee)","poster":"","key":"y4Ok11V6fz8"},{"singer":"#�ȳ�","album":"�ؿ� (2022)","title":"�ؿ� (2022)","poster":"","key":"P6gV_t70KAk"},{"singer":"��μ� (��θ���)","album":"���߰��","title":"���߰��","poster":"","key":"FCrMKhrFH7A"},{"singer":"10CM","album":"5.3","title":"�׶��̼�","poster":"","key":"kQuxJbP6s8Y"},{"singer":"�漭���� & ����ȣ","album":"������ �� �̸��� �θ���","title":"������ �� �̸��� �θ���","poster":"","key":"b_6EfFZyBxY"},{"singer":"��� (Woody)","album":"Say I Love You (Re : WIND 4MEN Vol.04)","title":"Say I Love You","poster":"","key":"yo-cqIHBJ2U"},{"singer":"��ȣ","album":"���� �ƴ϶�","title":"���� �ƴ϶�","poster":"","key":"lAq9l8o6UXU"},{"singer":"���� & ��ź�ҳ��","album":"Dreamers (Music from the FIFA World Cup Qatar 2022 Official Soundtrack)","title":"Dreamers (Music from the FIFA World Cup Qatar 2022 Official Soundtrack) (Feat. FIFA Sound)","poster":"","key":"IwzkfMmNMpM"},{"singer":"���� (YOUNHA)","album":"YOUNHA 6th Album 'END THEORY'","title":"����Ʈ����","poster":"","key":"cFgk2PMgPJ4"},{"singer":"�漭","album":"���� X����","title":"���� X����","poster":"","key":"iAfxyHOmHrM"}]
	
 */
public class MusicGetData {
	// list-wrap
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			Document doc=Jsoup.connect("https://www.genie.co.kr/chart/top200").get();
			Elements title=doc.select("table.list-wrap a.title");
			Elements singer=doc.select("table.list-wrap a.artist");
			Elements album=doc.select("table.list-wrap a.albumtitle");
			Elements poster=doc.select("div.newest-list table.list-wrap a.cover img");
			JSONArray arr=new JSONArray();
			for(int i=0;i<title.size();i++)
			{
				/*System.out.println(title.get(i).text()+" "
						+singer.get(i).text()+" "
						+album.get(i).text()+" "
						+poster.get(i).attr("src")+" "
						+youtubeGetKey(title.get(i).text()));*/
				JSONObject obj=new JSONObject();
				obj.put("no", i+1);
				obj.put("title", title.get(i).text());
				obj.put("singer", singer.get(i).text());
				obj.put("poster", poster.get(i).attr("src"));
				obj.put("album", album.get(i).text());
				obj.put("key", youtubeGetKey(title.get(i).text()));
				arr.add(obj);				
			}
			System.out.println(arr.toJSONString());
		}catch(Exception ex) {}

	}
	public static String youtubeGetKey(String title)
	{
		String key="";
		try
		{
			Document doc=Jsoup.connect("https://www.youtube.com/results?search_query="+URLEncoder.encode(title,"UTF-8")).get();
			String data=doc.toString();
			//System.out.println(data);
			Pattern p=Pattern.compile("/watch\\?v=[^��-�R]+");
			Matcher m=p.matcher(data);
			while(m.find())
			{
				String s=m.group();
				System.out.println(s);
				key=s.substring(s.indexOf("=")+1,s.indexOf("\""));
				break;
			}
		}catch(Exception ex) {}
		return key;
	}

}
