package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.*;
import java.io.*;
public class CartDAO {
   private static SqlSessionFactory ssf;// CreateConnection
   static
   {
	   // XML 파싱 
	   try
	   {
		   Reader reader=Resources.getResourceAsReader("Config.xml");
		   ssf=new SqlSessionFactoryBuilder().build(reader);
	   }catch(Exception ex) {ex.printStackTrace();}
	   
   }
   
   // insert
   /*
    *   <insert id="goodsCartInsert" parameterType="CartVO">
         INSERT INTO project_goods_buy VALUES(pgb_bno_seq.nextval,#{gno},#{id},#{account},#{total_price},'n',SYSDATE)
        </insert>
    */
   public static void goodsCartInsert(CartVO vo)
   {
	   SqlSession session=ssf.openSession(true);
	   ssf.openSession(true).insert("goodsCartInsert",vo);//true => commit()
	   session.close();
   }
   // select
   /*
    * <select id="goodsCartListData" resultType="CartVO" parameterType="string">
	    SELECT bno,gno,id,account,total_price,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,
	    buy_ok,goods_poster,goods_name,goods_price
	    FROM project_goods_buy pgb,goods_all ga
	    WHERE pgb.gno=ga.no
	    AND id=#{id}
	  </select>
    */
   public static List<CartVO>  goodsCartListData(String id)
   {
	   List<CartVO> list=null;
	   try
	   {
	     SqlSession session=ssf.openSession();
	     list=session.selectList("goodsCartListData",id);
	     session.close();
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   return list;// commit을 사용하지 않는다 
   }
   // delete
   /*
    *   <delete id="goodsCartDelete" parameterType="int">
    DELETE FROM project_goods_buy
    WHERE bno=#{bno}
  </delete>
    */
   public static void goodsCartDelete(int bno)
   {
	   SqlSession session=ssf.openSession(true);
	   ssf.openSession(true).delete("goodsCartDelete",bno);// delete(int bno)
	   session.close();
   }
   
}