package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import com.sist.vo.SeoulVO;
public class SeoulDAO {
   //���� ��ü => �̸� ������ ��ü�� �ּҸ� ������ �´�
   private Connection conn;
   //����Ŭ�� �ۼ��� (SQL ���� == ����� ���)
   private PreparedStatement ps;
   // DBCP ==> ��û �ø��� ����(connection�� ���� ����) OR �̸� ����� ��ü�� ���(��ȯ)
   private static SeoulDAO dao; //�̱��� ���� (����ڸ��� 1���� DAO�� ����� �����ϰ� �����)
   //DBCP ���� => �������� ����� ���� (��Ĺ)
   //1. �̸� ������ Connection �ּ� ���
   public void getConnection()
   {
      try
      {
         //POOL �ȿ� ���� (JNDI) => �̸����� ���丮�� ����� �ִ�
         Context init=new InitialContext(); //Ž���� ����
         // ��Ʈ ��� �̿�
         Context c=(Context)init.lookup("java://comp/env"); //c����̺꿡 ����
         //Connection ��ü�� ���´�
         DataSource ds=(DataSource)c.lookup("jdbc/oracle");//�ش� ���� ã��
         //�ּ�
         conn=ds.getConnection();
      }catch(Exception ex) {}
   }
   //2. ��� �� ��ȯ
   public void disConnetion()
   {
      try
      {
         if(ps!=null) ps.close();
         if(conn!=null) conn.close();
         //�ݰ� => �ٽ� ������ �� �� �ְ� ��ȯ
      }catch(Exception ex) {}
   }
   //3. ���
   public ArrayList<SeoulVO> seoulListData(int page,String tab)
   {
      ArrayList<SeoulVO> list=new ArrayList<SeoulVO>();
      try
      {
         //1.�̸� ������ ��ü�� ������ �´�
         getConnection();
         //2. sql ���� ����
         String sql="SELECT no,title,poster,num "
                  +"FROM (SELECT no,title,poster,rownum as num "
                  +"FROM (SELECT no,title,poster "
                  +"FROM seoul_"+tab+" ORDER BY no ASC)) " //tab�� ? �ȵǰ� tab���� �ڵ��ؾ���
                  +"WHERE num BETWEEN ? AND ? ";
         //MyBatis ==> #{} �Ϲ� ������, ${} �÷���, ���̺��
         //3.����Ŭ ����
         ps=conn.prepareStatement(sql);
         //4.?�� ���� ä���
         int rowSize=20;
         int start=(rowSize*page)-(rowSize-1);
         int end=rowSize*page;
         ps.setInt(1, start);
         ps.setInt(2, end);
         //5.����� �ޱ�
         ResultSet rs=ps.executeQuery();
         //6. ArrayList ���� ä���
         while(rs.next())
         {
            SeoulVO vo=new SeoulVO();
            vo.setNo(rs.getInt(1));
            vo.setTitle(rs.getString(2));
            vo.setPoster(rs.getString(3));
            list.add(vo);
         }
         rs.close();
      }catch(Exception ex)
      {
         ex.printStackTrace();
      }
      finally
      {
         disConnetion(); //�ݱ�x ��ȯo(������ ����) ==> Connection ��ü ������ ����
      }
      return list;
   }
   /*
    *  �������
    *  ------
    *     1. POOL�ȿ��� �̸� ������ Connection ��ü�� ���´�
    *        ��Ĺ�� ���ؼ� Connection�� 10(maxIdle)�� �����ؼ� ==> POOL�̶�� �޸� ������ ������ �ص�
    *     2. ���� Connection�� �̿��ؼ� ����Ŭ �����͸� ����(�˻�,�߰�,����,����)
    *     3. ���� Connection�� POOL ������ ��ȯ (�ٸ� ����ڰ� �ٽ� ������ �Ѵ�) ==> 10���� ������ ���
    *        => Connection ������ ���� => �޸� ����, �����ϴ� �ð��� ���� �� �ִ�
    *        => ���� ����ڰ� ������ �Ѵٰ� �ص� ���� �ٿ��� ���� �ʴ´�
    *        => ���� : �����ð� ��ٸ� ���� �ִ�
    *        => �� ���α׷����� �����ͺ��̽� ��� �Ϲ�ȭ
    *  
    *     �����ͺ��̽�
    *        seoul_location
    *        seoul_nature
    *        seoul_shop
    */
   //��������
   public int seoulTotalPage(String tab)
   {
      int total=0;
      try
      {
         getConnection();
         String sql="SELECT CEIL(COUNT(*)/20.0) "
                  +"FROM seoul_"+tab;
         ps=conn.prepareStatement(sql);
         ResultSet rs=ps.executeQuery();
         rs.next();
         total=rs.getInt(1);
         rs.close();
      }catch(Exception ex)
      {
         ex.printStackTrace();
      }
      finally
      {
         disConnetion();
      }
      return total;
   }
}