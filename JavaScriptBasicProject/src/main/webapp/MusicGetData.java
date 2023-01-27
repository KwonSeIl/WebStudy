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
{"singer":"À±ÇÏ (YOUNHA)","album":"YOUNHA 6th Album Repackage 'END THEORY : Final Edition'","title":"»ç°ÇÀÇ ÁöÆò¼±","poster":"","key":"mnpQsM-tqQU"},{"singer":"LE SSERAFIM (¸£¼¼¶óÇË)","album":"ANTIFRAGILE","title":"ANTIFRAGILE","poster":"","key":"pyf8cbqyfPs"},{"singer":"NewJeans","album":"NewJeans 1st EP 'New Jeans'","title":"Attention","poster":"","key":"CHp0Kaidr14"},{"singer":"IVE (¾ÆÀÌºê)","album":"After LIKE","title":"After LIKE","poster":"","key":"F0B7HDiY-10"},{"singer":"ÀÓ¿µ¿õ","album":"½Å»ç¿Í ¾Æ°¡¾¾ OST Part.2","title":"»ç¶ûÀº ´Ã µµ¸Á°¡","poster":"","key":"pBEAzM2TRmE"},{"singer":"ÅÂ¾ç","album":"VIBE","title":"VIBE (Feat. Jimin of BTS)","poster":"","key":"cXCBiF67jLM"},{"singer":"ÀÓ¿µ¿õ","album":"IM HERO","title":"¿ì¸®µéÀÇ ºí·ç½º","poster":"","key":"YKenCC4OAyk"},{"singer":"ÀÌ¿µÁö","album":"¼î¹Ì´õ¸Ó´Ï 11 Episode 3","title":"NOT SORRY (Feat. pH-1) (Prod. by Slom)","poster":"","key":"jvqbOU3gSzw"},{"singer":"ÀÓ¿µ¿õ","album":"IM HERO","title":"´Ù½Ã ¸¸³¯ ¼ö ÀÖÀ»±î","poster":"","key":"sHqFqWDviBg"},{"singer":"(¿©ÀÚ)¾ÆÀÌµé","album":"I love","title":"Nxde","poster":"","key":"fCO7f0SmrDc"},{"singer":"IVE (¾ÆÀÌºê)","album":"LOVE DIVE","title":"LOVE DIVE","poster":"","key":"Y8JFxS1HlDo"},{"singer":"NewJeans","album":"NewJeans 1st EP 'New Jeans'","title":"Cookie","poster":"","key":"VOmIplFAGeg"},{"singer":"ÀÓ¿µ¿õ","album":"³»ÀÏÀº ¹Ì½ºÅÍÆ®·Ô ¿ì½ÂÀÚ Æ¯Àü°î","title":"ÀÌÁ¦ ³ª¸¸ ¹Ï¾î¿ä","poster":"","key":"kSzraUekkNE"},{"singer":"ÀÓ¿µ¿õ","album":"Polaroid","title":"London Boy","poster":"","key":"TMfvkhkALbU"},{"singer":"ÁöÄÚ (ZICO)","album":"½ºÆ®¸´ ¸Ç ÆÄÀÌÅÍ(SMF) Original Vol.3 (°è±Þ¹Ì¼Ç)","title":"»õ»æ (Prod. by ZICO) (Feat. È£¹Ìµé)","poster":"","key":"azaZt7eccnc"},{"singer":"NCT DREAM","album":"Candy - Winter Special Mini Album","title":"Candy","poster":"","key":"zuoSn3ObMz4"},{"singer":"ÀÓ¿µ¿õ","album":"Polaroid","title":"Polaroid","poster":"","key":"I82pbW2y_cc"},{"singer":"ÀÓ¿µ¿õ","album":"IM HERO","title":"¹«Áö°³","poster":"","key":"jP2J0qnFtV4"},{"singer":"ÀÓ¿µ¿õ","album":"IM HERO","title":"¾Æ¹öÁö","poster":"","key":"r7B_9-rj9bI"},{"singer":"Charlie Puth","album":"CHARLIE","title":"I Don't Think That I Like Her","poster":"","key":"bOVzVuB5DpY"},{"singer":"ÀÓ¿µ¿õ","album":"IM HERO","title":"A bientot","poster":"","key":"zLFypmn42NE"},{"singer":"ÀÓ¿µ¿õ","album":"IM HERO","title":"¼ÕÀÌ Âü °ö´ø ±×´ë","poster":"","key":"OpZIaI-J0uk"},{"singer":"ÀÓ¿µ¿õ","album":"IM HERO","title":"»ç¶ûÇØ ÁøÂ¥","poster":"","key":"CbhiBN5wpCc"},{"singer":"Å×ÀÌ (Tei)","album":"Monologue","title":"Monologue","poster":"","key":"i-u2TTV22UE"},{"singer":"¸á·Î¸Á½º (MeloMance)","album":"»ç¶ûÀÎ°¡ ºÁ (»ç³»¸Â¼± OST ½ºÆä¼È Æ®·¢)","title":"»ç¶ûÀÎ°¡ ºÁ","poster":"","key":"McidaTgrQB0"},{"singer":"ÀÓ¿µ¿õ","album":"IM HERO","title":"ÀÎ»ýÂù°¡","poster":"","key":"7EC6RGJ8BOE"},{"singer":"ÀÓ¿µ¿õ","album":"IM HERO","title":"¿¬¾ÖÆíÁö","poster":"","key":"P2FcQvCbDWg"},{"singer":"BLACKPINK","album":"BORN PINK","title":"Shut Down","poster":"","key":"POe9SOEKotk"},{"singer":"ÀÓ¿µ¿õ","album":"IM HERO","title":"º¸±ÝÀÚ¸®","poster":"","key":"Znpnf1HUmQ4"},{"singer":"WSG¿ö³Êºñ (°¡¾ßG)","album":"WSG¿ö³Êºñ 1Áý","title":"±×¶§ ±× ¼ø°£ ±×´ë·Î (±×±×±×)","poster":"","key":"aH-IdX8U6jc"},{"singer":"Charlie Puth","album":"CHARLIE","title":"That's Hilarious","poster":"","key":"MPP2Q9JBvbg"},{"singer":"(¿©ÀÚ)¾ÆÀÌµé","album":"I NEVER DIE","title":"TOMBOY","poster":"","key":"Jh4QFaPmdss"},{"singer":"WSG¿ö³Êºñ (4FIRE)","album":"WSG¿ö³Êºñ 1Áý","title":"º¸°í½Í¾ú¾î","poster":"","key":"B69HTHBsHCQ"},{"singer":"BLACKPINK","album":"BORN PINK","title":"Pink Venom","poster":"","key":"gQlMMD8auMs"},{"singer":"The Kid LAROI & Justin Bieber","album":"Stay","title":"Stay","poster":"","key":"Ec7TN_11az8"},{"singer":"Crush","album":"Rush Hour","title":"Rush Hour (Feat. j-hope of BTS)","poster":"","key":"PS0qkO5qty0"},{"singer":"¼º½Ã°æ","album":"º°¿¡¼­ ¿Â ±×´ë OST Part.7 (SBS ¼ö¸ñµå¶ó¸¶)","title":"³ÊÀÇ ¸ðµç ¼ø°£","poster":"","key":"1qzbXDsUd2E"},{"singer":"Àú½ºµð½º (JUSTHIS) & R.Tee & ´ø¸»¸¯ (DON MALIK) & Çã¼ºÇö (Huh) & KHAN & ¸Æ´ëµð (Mckdaddy) & Los","album":"¼î¹Ì´õ¸Ó´Ï 11 Episode 1","title":"¸¶ÀÌ¿þÀÌ (MY WAY) (Prod. by R.Tee)","poster":"","key":"y4Ok11V6fz8"},{"singer":"#¾È³ç","album":"ÇØ¿ä (2022)","title":"ÇØ¿ä (2022)","poster":"","key":"P6gV_t70KAk"},{"singer":"±è¹Î¼® (¸á·Î¸Á½º)","album":"ÃëÁß°í¹é","title":"ÃëÁß°í¹é","poster":"","key":"FCrMKhrFH7A"},{"singer":"10CM","album":"5.3","title":"±×¶óµ¥ÀÌ¼Ç","poster":"","key":"kQuxJbP6s8Y"},{"singer":"°æ¼­¿¹Áö & Àü°ÇÈ£","album":"´ÙÁ¤È÷ ³» ÀÌ¸§À» ºÎ¸£¸é","title":"´ÙÁ¤È÷ ³» ÀÌ¸§À» ºÎ¸£¸é","poster":"","key":"b_6EfFZyBxY"},{"singer":"¿ìµð (Woody)","album":"Say I Love You (Re : WIND 4MEN Vol.04)","title":"Say I Love You","poster":"","key":"yo-cqIHBJ2U"},{"singer":"ÁÖÈ£","album":"³»°¡ ¾Æ´Ï¶óµµ","title":"³»°¡ ¾Æ´Ï¶óµµ","poster":"","key":"lAq9l8o6UXU"},{"singer":"Á¤±¹ & ¹æÅº¼Ò³â´Ü","album":"Dreamers (Music from the FIFA World Cup Qatar 2022 Official Soundtrack)","title":"Dreamers (Music from the FIFA World Cup Qatar 2022 Official Soundtrack) (Feat. FIFA Sound)","poster":"","key":"IwzkfMmNMpM"},{"singer":"À±ÇÏ (YOUNHA)","album":"YOUNHA 6th Album 'END THEORY'","title":"¿À¸£Æ®±¸¸§","poster":"","key":"cFgk2PMgPJ4"},{"singer":"°æ¼­","album":"³ªÀÇ X¿¡°Ô","title":"³ªÀÇ X¿¡°Ô","poster":"","key":"iAfxyHOmHrM"}]
	
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
			Pattern p=Pattern.compile("/watch\\?v=[^°¡-ÆR]+");
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
