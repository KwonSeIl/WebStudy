package com.sist.service;
import com.sist.dao.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class DataCollectionService {

	public static void main(String[] args) {
	   DataCollectionService ds=new DataCollectionService();
	      //ds.foodCategoryGetData();
	      //ds.foodDetailData();
	   	  ds.goodsAllData();
	   }
	    public void foodCategoryGetData()
	    {
	       FoodDAO dao=new FoodDAO();
	       try
	       {
	          // ����Ʈ ���� => HTML�±� �б� 
	          Document doc=Jsoup.connect("https://www.mangoplate.com/").get();
	          //System.out.println(doc.toString());
	          Elements title=doc.select("div.top_list_slide div.info_inner_wrap span.title");
	          Elements subject=doc.select("div.top_list_slide div.info_inner_wrap p.desc");
	          Elements poster=doc.select("div.top_list_slide img.center-croping");
	          Elements link=doc.select("div.top_list_slide a");
	          //System.out.println(link.toString());
	          /*
	           *   text()  <�±׸�>������</�±׸�>
	           *   attr("�Ӽ���")  <�±׸� �Ӽ�="��">
	           *   data()  <script></script>
	           *   html()  <�±׸�>
	           *             <�±׸�>
	           *               ������
	           *             </�±׸�>
	           *           </�±׸�>
	           *           
	           *     <div class="a">
	           *       <ul>
	           *        <li>
	           *          ���� , ....
	           *        </li>
	           *        <li>
	           *          ����...
	           *        </li>
	           *      </ul>
	           *     </div>
	           */
	          for(int i=0;i<title.size();i++)
	          {
	             System.out.println(i+1);
	             System.out.println(title.get(i).text());
	             System.out.println(subject.get(i).text());
	             System.out.println(poster.get(i).attr("data-lazy"));
	             System.out.println(link.get(i).attr("href"));
	             System.out.println("=========================================================================");
	             CategoryVO vo=new CategoryVO();
	             vo.setTitle(title.get(i).text());
	             vo.setSubject(subject.get(i).text());
	             vo.setPoster(poster.get(i).attr("data-lazy"));
	             vo.setLink(link.get(i).attr("href"));
	             dao.foodCategoryInsert(vo);
	          }
	       }catch(Exception ex){}
	    }
	    /*
	     *   https://mp-seoul-image-production-s3.mangoplate.com/15964/652152_1632445289291_19886
	     *   ?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80
	     *   ^https://mp-seoul-image-production-s3.mangoplate.com/15964/2221854_1656027051275_6948?
	     *   fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80^
	     *   https://mp-seoul-image-production-s3.mangoplate.com/15964/2221854_1656027051275_6995?
	     *   fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80^
	     *   https://mp-seoul-image-production-s3.mangoplate.com/15964/2221854_1656027051275_7005?
	     *   fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80^
	     *   https://mp-seoul-image-production-s3.mangoplate.com/707184_1648003735535360.jpg?
	     *   fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80
	     */
	    public void foodDetailData()
	    {
	       FoodDAO dao=new FoodDAO();
	       try
	       {
	          ArrayList<CategoryVO> list=dao.foodCategoryInfoData();
	          for(CategoryVO vo:list)
	          {
	             //System.out.println(vo.getCno()+" "+vo.getTitle()+" "+vo.getLink());
	             FoodVO fvo=new FoodVO();
	             fvo.setCno(vo.getCno());
	             System.out.println(vo.getCno()+"."+vo.getTitle());
	             Document doc=Jsoup.connect(vo.getLink()).get();
	             Elements link=doc.select("section#contents_list span.title a");
	             for(int k=0;k<link.size();k++)
	             {
	                //System.out.println(link.get(i).attr("href"));
	                Document doc2=Jsoup.connect("https://www.mangoplate.com"+link.get(k).attr("href")).get();
	                // 1. �̹��� 
	                Elements image=doc2.select("div.list-photo_wrap img.center-croping");
	                String poster="";
	                for(int j=0;j<image.size();j++)
	                {
	                   String s=image.get(j).attr("src");
	                   poster+=s+"^";
	                }
	                poster=poster.substring(0,poster.lastIndexOf("^"));
	                
	                poster=poster.replace("&", "#");
	                fvo.setPoster(poster);
	                
	                //System.out.println(poster);
	                // 2. ������
	                /*
	                 *   <span class="title">
	                           <h1 class="restaurant_name">�����</h1>
	                             <strong class="rate-point ">
	                               <span>4.4</span>
	                             </strong>
	                 */
	                Element name=doc2.selectFirst("span.title h1.restaurant_name");
	                Element score=doc2.selectFirst("strong.rate-point span");
	                System.out.println(name.text()+" "+score.text());
	                fvo.setName(name.text());
	                fvo.setScore(Double.parseDouble(score.text()));
	                // 3. ���� 
	                // 4. �ּ� , ��ȭ , �������� , ���ݴ� , ���� , �ð� , �޴� , ���ƿ� , ������ , ���� 
	                String address="no",tel="no",type="no",price="no",time="no",menu="no",parking="no";
	                Elements detail=doc2.select("table.info tbody tr th");
	  
	                for(int i=0;i<detail.size();i++)
	                 {
	                    //System.out.println(detail.get(i).text());
	                    /*
	                     *   �ּ�
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
	                       Element a=doc2.select("table.info tbody tr td").get(i);
	                       address=a.text();
	                    }
	                    else if(label.equals("��ȭ��ȣ"))
	                    {
	                       Element a=doc2.select("table.info tbody tr td").get(i);
	                       tel=a.text();
	                    }
	                    else if(label.equals("���� ����"))
	                    {
	                       Element a=doc2.select("table.info tbody tr td").get(i);
	                       type=a.text();
	                    }
	                    else if(label.equals("���ݴ�"))
	                    {
	                       Element a=doc2.select("table.info tbody tr td").get(i);
	                       price=a.text();
	                    }
	                    else if(label.equals("����"))
	                    {
	                       Element a=doc2.select("table.info tbody tr td").get(i);
	                       parking=a.text();
	                    }
	                    else if(label.equals("�����ð�"))
	                    {
	                       Element a=doc2.select("table.info tbody tr td").get(i);
	                       time=a.text();
	                    }
	                    else if(label.equals("�޴�"))
	                    {
	                       Element a=doc2.select("table.info tbody tr td").get(i);
	                       menu=a.text();
	                       
	                    }
	                 }
	                 String addr1=address.substring(0,address.indexOf("��"));
	                 String addr2=address.substring(address.indexOf("��")+3);
	                 
	                 System.out.println("�ּ�:"+addr1);
	                 System.out.println("����:"+addr2);
	                 
	                 System.out.println("��ȭ:"+tel);
	                 System.out.println("��������:"+type);
	                 System.out.println("���ݴ�:"+price);
	                 System.out.println("�ð�:"+time);
	                 System.out.println("����:"+parking);
	                 System.out.println("�޴�:"+menu);
	                 
	                 fvo.setAddress(address);
	                 fvo.setTel(tel);
	                 fvo.setTime(time);
	                 fvo.setType(type);
	                 fvo.setParking(parking);
	                 fvo.setPrice(price);
	                 fvo.setMenu(menu);
	                 
	                 
	                 Element script=doc2.selectFirst("script#reviewCountInfo");
	                 System.out.println(script.data());// script�ȿ� �ִ� ������ �б� => data()
	                 // [{"action_value":1,"count":3},{"action_value":2,"count":12},{"action_value":3,"count":129}] => JSON
	                 String s=script.data();
	                 JSONParser jp=new JSONParser();
	                 /*
	                  *   1. HTML ������ ���� => HTMLParser => Jsoup
	                  *   2. JSON ������ ���� => JSONParser (Ajax , Vue , React)
	                  *      => ArrayList => []
	                  *      => ~VO       => {}
	                  *   3. XML ������ ���� => DocumentBulider (Spring,Mybatis)
	                  *   ----------------------- 3�� 
	                  *   
	                  *   [] => JSONArray
	                  *   {} => JSONObject
	                  */
	                 // ==> RestFul ==> Spring 
	                 JSONArray arr=(JSONArray)jp.parse(s);
	                 System.out.println(arr.toString());
	                 for(int i=0;i<arr.size();i++)
	                 {
	                    JSONObject obj=(JSONObject)arr.get(i);
	                    if(i==0)
	                    {
	                       System.out.print("����("+obj.get("count")+") ");
	                       String ss=String.valueOf(obj.get("count"));
	                       fvo.setBad(Integer.parseInt(ss));
	                       
	                       
	                    }
	                    else if(i==1)
	                    {
	                       System.out.print("������("+obj.get("count")+") ");
	                       String ss=String.valueOf(obj.get("count"));
	                       fvo.setSoso(Integer.parseInt(ss));
	                    }
	                    else if(i==2)
	                    {
	                       System.out.print("���ִ�("+obj.get("count")+") ");
	                       String ss=String.valueOf(obj.get("count"));
	                       fvo.setGood(Integer.parseInt(ss));
	                    }
	                 }
	                 System.out.println();
	                 System.out.println("=======================================================================");
	                 dao.foodDetailInsert(fvo);
	             }
	          }
	       }catch(Exception ex){}
	    }
	    /*
	     * <li class="common2_sp_list_li"><a href="https://shop.10000recipe.com/goods/goods_view.php?goodsNo=1000032512&utm_source=10k_web&utm_medium=category_list&utm_campaign=g1000032512" class="common2_sp_link"></a><div class="common2_sp_thumb"><img src="https://recipe1.ezmember.co.kr/cache/data/goods/22/12/51/1000032512/1000032512_detail_056.jpg"></div>
                  <div class="common2_sp_caption">
                      <div class="common2_sp_caption_tit line2">[ڸģƯ��] ���̽�Ƽ���� t9 ������������ ������ũ&���� Ư��/���ްŽ� 1+1 Ŭ����� ����!</div>
                      <div class="common2_sp_caption_price_box"><span class="common2_sp_caption_per"><b>72</b>%</span> <strong class="common2_sp_caption_price"><span>4,900</span><small>��</small></strong></div><div class="common2_sp_caption_rv2">
                            <span class="caption_rv2_img"></span><b class="caption_rv2_pt">4.3</b><span class="caption_rv2_ea">(21)</span>
                        </div><div class="common2_sp_caption_icon"><span class="icon_free">������</span> </div></div></li>
	     */
	    /*
	     * <div class="item_photo_info_sec s_contents">
        <div class="s_view">
            <!--��� �����//-->
            <div class="s_view_pic">
                <div class="s_view_pic_zoom"><a href="#lyZoom" id="mainImage" >
                        <img src="https://recipe1.ezmember.co.kr/cache/data/goods/22/12/51/1000032512/1000032512_detail_056.jpg" width="1000" height="1000" alt="[ڸģƯ��] ���̽�Ƽ���� T9 ������������ ������ũ&amp;���� Ư��/���ްŽ� 1+1 Ŭ����� ����!" title="[ڸģƯ��] ���̽�Ƽ���� T9 ������������ ������ũ&amp;���� Ư��/���ްŽ� 1+1 Ŭ����� ����!" class="middle"  />
                    </a>
                </div>
                <div class="s_view_pic_thumb item_photo_slide">
                    <div class="s_view_pic_thumb_arrow"><button type="button" class="slick_goods_prev"><img src="https://recipe1.ezmember.co.kr/img/store/icon_arrow2_pre.png"></button></div>
                    <div class="s_view_pic_thumb_pic">
                        <ul class="slider_wrap slider_goods_nav">
                            <li style="padding: 0;"><a href="javascript:gd_change_image('0', 'detail');"><img src="https://recipe1.ezmember.co.kr/cache/data/goods/22/12/51/1000032512/1000032512_detail_056.jpg" width="1000" height="1000" alt="[ڸģƯ��] ���̽�Ƽ���� T9 ������������ ������ũ&amp;���� Ư��/���ްŽ� 1+1 Ŭ����� ����!" title="[ڸģƯ��] ���̽�Ƽ���� T9 ������������ ������ũ&amp;���� Ư��/���ްŽ� 1+1 Ŭ����� ����!" class="middle"  /></a></li>
                        </ul>
                    </div>
                    <div class="s_view_pic_thumb_arrow"><button type="button" class="slick_goods_next"><img src="https://recipe1.ezmember.co.kr/img/store/icon_arrow2_next.png"></button></div>
                </div>
            </div>
            <div class="s_view_info btn_layer">
                <h3>
                    <p>[ڸģƯ��] ���̽�Ƽ���� T9 ������������ ������ũ&���� Ư��/���ްŽ� 1+1 Ŭ����� ����!</p>
                </h3>



                <div id="lySns" class="layer_area" style="display: none">
                    <div class="ly_wrap sns_layer">
                        <div class="ly_cont">
                            <div class="sns_list">
                                <div class="ly_share_noti" style="display: none"><span>���ͱ� ���� �Ұ� ��ǰ�Դϴ�.</span></div>
                                <ul>
                                    <li><a href="javascript:;" id="shareKakaoLinkBtn" data-sns="kakaolink"><img src="//recipe1.ezmember.co.kr/img/img_share_k.png" alt="īī���� ����"><br><span>īī����</span></a></li>
                                    <li><a href="javascript:;" id="shareFacebookBtn" data-width="750" data-height="300" data-sns="facebook" class="btn-social-popup"><img src="//recipe1.ezmember.co.kr/img/img_share_f.png" alt="���̽��� ����"><br><span>���̽���</span></a></li>
                                    <li><a href="javascript:;" id="shareTwitterBtn" data-width="500" data-height="250" data-sns="twitter" class="btn-social-popup"><img src="//recipe1.ezmember.co.kr/img/img_share_t.png" alt="Ʈ���� ����"><br><span>Ʈ����</span></a></li>
                                    <li><a href="javascript:;" id="shareKakaoStoryBtn" data-sns="kakaostory"><img src="//recipe1.ezmember.co.kr/img/img_share_ks.png" alt="īī�����丮 ����"><br><span>īī�����丮</span></a></li>
                                    <li><a href="javascript:;" id="shareCopyBtn" data-sns="" class="gd_clipboard" data-clipboard-text="" title="��ǰ�ּ�"><img src="//recipe1.ezmember.co.kr/img/img_share_l.png" alt="��ũ����"><br><span>��ũ����</span></a></li>
                                </ul>
                                <div class="ly_share_btn ly_share_btn_not_login" style="display: none">
                                    <a href="//www.10000recipe.com/user/login.html?q_path=https%3A%2F%2Fshop.10000recipe.com%2Fgoods%2Fgoods_view.php%3FgoodsNo%3D1000032512" class="ly_share_btn_st1" style="width: 100%;">�����ϰ� ���ͱ� �ޱ�</a>
                                    <div class="ly_share_link"><a href="https://partners.10000recipe.com/customer/intro.html" target="_blank">������ ���񽺶�?</a></div>
                                </div>
                                <div class="ly_share_btn_login">
                                    <div class="ly_share_btn">
                                        <a href="https://pf.kakao.com/_EkLnK/chat" target="_blank" class="ly_share_btn_st2" style="width: 48%; margin-right: 5px;">1:1 ī�幮��</a>
                                        <a href="https://partners.10000recipe.com/dashboard/index.html" target="_blank" class="ly_share_btn_st3" style="width: 48%">���ͱ� ����</a>
                                    </div>
                                    <div class="ly_share_link"><a href="https://partners.10000recipe.com/customer/intro.html" target="_blank">������ ���񽺶�?</a></div>
                                    <ul class="ly_share_noti2">
                                        <li class="ly_share_available" style="display: none">������ ��ũ�� ���� Ÿ���� ���Ű� ���� <b id="ly_share_profit"></b>���� �����˴ϴ�.</li>
                                        <li class="ly_share_available" style="display: none">������ ��ũ�� ���� Ÿ���� �ٸ� ��ǰ ���� �ÿ��� �ֹ���ǰ�� ���ͱ��� �����˴ϴ�.</li>
                                        <li>��ũ�� ���� ���� ���� ���� ���ͱ��� �������� �ʽ��ϴ�.</li>
                                        <li>���ͱ� ���� Ȯ���� MY�޴� > ���ͱ� �������� �����մϴ�.</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <a href="#lySns" class="ly_close"><img src="http://recipe1.ezmember.co.kr/img/mobile/2022/icon_close2.png" alt="�ݱ�" width="26px"></a>
                    </div>
                </div>




                <div class="sub_info">���̽�Ƽ���� Ư��! �����!</div>
                <div class="price_box">
                        <p class="dc">72<small>%</small></p>
                        <p class="del">17,800��</p>
                    <p class="price" style="display:none;">4,900<small id="won" style="display:none;">��</small></p>
                            <div class="price_sale">
                                <p class="dc_tit">ù�������ΰ�</p>
                                <p class="price_sale_p">4,655<small style="">��</small></p>
                                <p class="btn_c"><a href="https://shop.10000recipe.com/mypage/coupon_event.php" target="_blank">���� Ȯ���ϱ�</a></p>
                            </div>
                    <div class="info_price_rv">
                        <div class="info_price_rv_star" style="display: inline-block;cursor:pointer;"></div>
                        <span class="info_price_rv_pt" style="cursor:pointer;"></span>
                        <span class="info_price_rv_ea" style="cursor:pointer;">(21)</span>
                    </div>
                    <div class="price_box_info_btn">
                        <a href="javascript:void(0);" class="info_btn_wish"><img src="//recipe1.ezmember.co.kr/img/mobile/icon_zzim2.png" alt="��"></a>
                        <div style="position: relative; display: inline-block;">
                            <a href="javascript:;" class="info_btn_share"><img src="//recipe1.ezmember.co.kr/img/mobile/icon_share10.png" alt="����"></a>
                        </div>
                    </div>
                    <div class="info_delivery_area">
                        <dl class="info_delivery">
                            <dt><img src="//recipe1.ezmember.co.kr/img/mobile/icon_delivery3.png" alt="��۾�����">���</dt>
                            <dd>
                                ������
                                <div class="delivery-detail" style="display:none;">


                                    <div class="js-deliveryMethodVisitArea dn">
                                        �湮 ������ : ����Ư���� ��õ��  ���������1�� 145 (���̽����̿���Ÿ��3��) 1106ȣ
                                    </div>
                                </div>
                                </dd>
                                </dl>
                                <dl class="info_delivery">
                                    <dt><img src="//recipe1.ezmember.co.kr/img/mobile/icon_point.png" alt="���ž�����">����</dt>
                                    <dd><b class="color_point1">25��</b> ����<span> (��� ȸ�� ���ž��� 0.5% ����)</span></dd>
                                </dl>
                            </div>
	     */
	    /////////GOODS
	    public void goodsAllData()
	    {
	    	try
	    	{
	    		for(int i=1;i<=238;i++)
	    		{
	    			Document doc=Jsoup.connect("https://shop.10000recipe.com/index.php?path=category&sort=popular&page="+i).get();
	    			Elements link=doc.select("div.s_list_wrap_vt li.common2_sp_list_li a.common2_sp_link"); //<a>
	    			for(int j=0;j<link.size();j++)
	    			{
	    				System.out.println(link.get(j).attr("href"));
	    				Document doc2=Jsoup.connect(link.get(j).attr("href")).get();
	    				Element poster=doc2.selectFirst("div.s_view_pic div.s_view_pic_zoom");
	    				//Element poster=doc2.select("").get(0)
	    				System.out.println(poster.attr("src"));
	    				Element title=doc2.selectFirst("div.s_view_info h3 p");
	    				System.out.println(title.text());
	    			}
	    				
	    		}
	    		
	    	}catch(Exception ex) {}
	    }
	    ////////RECIPE
	    ////////SEOUL
	}