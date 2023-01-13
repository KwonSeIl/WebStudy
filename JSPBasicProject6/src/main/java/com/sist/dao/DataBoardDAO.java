package com.sist.dao;
/*
 * 	�����ͺ��̽�: JDBC
 *  JDBC
 *    ����
 *     1. �����ͺ��̽� ���� ����̹� ����(thin:���Ḹ �ϴ� ����) => ����̹��� �����ͺ��̽� �� ��ü���� ���� ojdbc8.jar
 *     	  Class.forName("����̹���")
 *     					---------- oracle.jdbc.driver.OracleDriver
 *     								com.mysql.cj.jdbc.Driver(MYSQL) => MariaDB
 *     2. ���� ����Ŭ ����
 *     	  Connection conn=Drivermanager.getConnectionn(URL,username,password)
 *     3. SQL ���� ����
 *     	  PreparedStatement ps=conn.preparedStatement("SELECT ~")
 *     4. ?�� ���� ä���
 *        = ps.setInt()
 *        = ps.setString()
 *        = ps.setDouble()
 *        = ps.setDate() ==> SYSDATE, 'YY/MM/DD'
 *     5. ps.executeQuery() => SELECT(�����Ͱ��� ������ �´�): ����
 *     	  ps.executeUpdate() => INSERT, UPDATE, DELETE ==> COMMIT
 *     6. ������� �޴� ���
 *     	  ResultSet rs=ps.executeQuery();
 *     	  = ROW�� 1���� ���
 *     		rs.next(); �޸𸮿� ��µ� ������ ù��° �ٿ� Ŀ�� ��ġ ���� 
 *     	  = ROW�� �������� ���
 *     		while(rs.next())
 *     7. ó�� ����
 *     	  rs.close()
 *     	  ps.close()
 *     	  conn.close()
 *     	  --------------------------------- �Ϲ� JDBC
 *     	  DBCP => Connection�� �̸� ����
 *     			  ���ῡ �ҿ�Ǵ� �ð��� ���� �� �ִ�(�ӵ��� ������), Connection�� ���� ������ ������ �� �ִ�
 *     			  ������ ����(��� => ��ȯ)
 *     	  => Setting: server.xml
 *     			<Resource
 *     				auth="Container" => ������(��Ĺ)
 *                  driverClassName="" => ����̹� ���
 *                  -------------
 *                  url=""
 *                  username=""
 *                  password=""
 *                  ------------- Connection ����
 *                  name="jdbc/oracle" => Connection ��ü�� ã�� ���� ������ => lookup(): �̸����� ��ü �ּ� ���
 *                  type="javax.sql.DataSource" => ���� �ּҰ��� � Ŭ������ ������ ���� 
 *                  ----------
 *                  maxActibe="" : ���� Connection ���� ���� (�ʰ�)
 *                  maxIdle="" : ���� POOL�� ����� ����
 *                  axWait="-1" : Connection�� ���� ���ÿ��� ����ڰ� ��ȯ�� ������ ��� (���ð�)
 *                  ---------- ��, ������ ������
 *              />
 *              => XML: ��ҹ��� ����, �Ӽ��� ���� "", ���� �ݱ� ==> ���� �߻��ÿ��� run on server�� ��������
 *              => web.xml, properties
 *              
 *       = �ڵ� ����
 *        1) �̸� ����(��Ĺ)�� Connection�� �ּҰ��� ���´�
 *        	1. Connection ��ü �ּҰ� ����� ��ġ�� ����
 *        		java://comp/env => ����Ǵ� ��ġ
 *        		jdbc/oracle
 *        
 *              Context init=new InitalContext(); => ����(Ž���⸦ ����)
 *              Context c=(Context)init.lookup("java://comp/env");
 *              DataSource ds=(DataSource)c.lookup("jdbc/oracle")
 *              conn=ds.getConnection()
 *        --------------
 *        2) SQL���� ����
 *        3) ?�� ���� ä���
 *        4) ����� �б�
 *        ---------------> 2,3,4 => JDBC�� ����
 *        5) ��ȯ    
 *        	ps.close()
 *        	conn.close()  
 *        
 *        JDBC / DBCP ==> Connection: �Ź� ���� / �̸� ���� 
 */
import java.util.*; // ArrayList
import java.sql.*;  // Connection, PrepareStatement, ResultSet
import javax.sql.*; // DataSource (DB�� ���� ��� ���� ����)

import com.sist.vo.DataBoardVO;

import javax.naming.*; // Context => �޸𸮿� ����� ������ 
public class DataBoardDAO {
	private Connection conn; //����Ŭ ���� ��ü
	private PreparedStatement ps; //����Ŭ�� SQL ���� ���� -> ���� ������ �´�
	//1. �̸� ������ Connection ��ü �ּҸ� �о�´�
	public void getConnection()
	{
		try
		{
			Context init=new InitialContext(); //Ž���� �������� ���� => JNDI => DORBA, RMI
			Context c=(Context)init.lookup("java://comp/env");
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
		}catch(Exception ex) {}
	}
	//2. ��ȯ ==> Connection�� �����ϴ� ����(POOL)
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	//3. ��� => JDBC�� ����
	//3-1. ��� ��� ==> ����¡(�ζ��κ�)
	/*
	 *  �޼ҵ�
	 *  	1) ����ڷκ��� � ���� ���� �� => �Ű�����
	 *      2) ������� ��� => ������ 
	 *      	= ���(ArrayList)
	 *      	= ��(~VO) => �Խù� �Ѱ��� ���� ��� �����ͺ����� ������ �ִ�
	 *      	= �Ϲ�(��������, �˻�����, �α��� ó��)
	 *      		����� ��
	 *      		------- ������ (String,int) => ID, PWD, OK
	 *      		------- �Ѱ�: �ش� ������ ���
	 *      3) �޼ҵ带 ����
	 *      
	 *      ==> ���: �ڹ�, �ڹٽ�ũ��Ʈ(Jquery, Ajax, VueJS, ReactJS)
	 *      		1. ����
	 *      		2. ó��(�޼ҵ�)
	 *      		-----------
	 *      4) ��
	 *      	����� ���� => �Ű�����
	 *      		request
	 *      			|
	 *      		�������� ��û ó��(������,ũ�Ѹ�,���ó��) =========> ������� ����ڿ��� ����
	 *      									  response
	 *      5) ��Ʈ��ũ / �� / AI
	 *      		   -- ���� ���� ���α׷�(�ο��� ���� ���� ������) => ����� 
	 *      			| �θ� => ���� ���� ������ ����
	 */
	public ArrayList<DataBoardVO> databoardListData(int page)
	{
		ArrayList<DataBoardVO> list=new ArrayList<DataBoardVO>();
		try
		{
			//1. Connection �ּ� ���
			getConnection();
			//2. SQL���� ==> index => UNIQUE,PRIMARY KEY => �ڵ����� �ε����� ���������
			String sql="SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD'),hit,filesize,num "
					+"FROM (SELECT no,subject,name,regdate,hit,filesize,rownum as num "
					+"FROM (SELECT /*+ INDEX_DESC(databoard db_no_pk)*/ no,subject,name,regdate,hit,filesize "
					+"FROM databoard)) "
					+"WHERE num BETWEEN ? AND ?";
					//***rownum�� 1������ ���� 
			//3. ����
			ps=conn.prepareStatement(sql);
			//4. ?�� �� ä���(���� ��쵵 ����)
			int rowSize=10;
			int start=(rowSize*page)-(rowSize-1); //1,11,21...
			int end=rowSize*page; //10,20,30...
			ps.setInt(1, start);
			ps.setInt(2, end);
			//5. ���� ��û
			ResultSet rs=ps.executeQuery();
			//6. �о�� ������ -> ArrayList�� ���
			while(rs.next())
			{
				DataBoardVO vo=new DataBoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4)); //TO_CHAR�� �� 
				vo.setHit(rs.getInt(5));
				vo.setFilesize(rs.getInt(6));
				//��Ƽ� ������ �غ�
				list.add(vo);
			}
			rs.close();
			//==================================> ���̺귯��ȭ -> �Ѱ��� �޼ҵ�� ������ ����: selectList(SQL) => MyBatis
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		
		return list;
	}
	//3-2. ���� => ���ε�
	public void databoardInsert(DataBoardVO vo)
	{
		try
		{
			getConnection();
			//SQL���� 
			String sql="INSERT INTO databoard(no,name,subject,content,pwd,filename,filesize) "
					+ "VALUES(db_no_seq.nextval,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			//?�� ä���
			ps.setString(1, vo.getName());
    		ps.setString(2, vo.getSubject());
    		ps.setString(3, vo.getContent());
    		ps.setString(4, vo.getPwd());
    		ps.setString(5, vo.getFilename());
    		ps.setInt(6, vo.getFilesize());
    		ps.executeUpdate();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
	//3-3. �󼼺��� => ��ȸ�� ����, �ٿ�ε� => response.setHeader()
	public DataBoardVO databoardDetailData(int no)
	{
		DataBoardVO vo=new DataBoardVO();
		try
		{
			//1. Connection �ּҰ� ���
			getConnection();
			//2.SQL ����
			//2-1. ��ȸ�� ����
			String sql="UPDATE databoard SET "
					+"hit=hit+1 "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			////////////////////////// ��ȸ�� ����
			//�󼼺� ������ �б�
			sql="SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD'),hit,filename,filesize "
					+"FROM databoard "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			/*
			 *   1) ȸ������ => �α���, ȸ������, ȸ������, ȸ��Ż��, ���̵� ã��, ��й�ȣ ã��
			 *   		     ----------------------------------------------
			 *   �Խ��� => ������ ���, ���� ���ϱ�
			 *   ����, ����, ��õ
			 */
			ps.setInt(1, no);
			//����� �б�
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setDbday(rs.getString(5));
			vo.setHit(rs.getInt(6));
			vo.setFilename(rs.getString(7));
			vo.setFilesize(rs.getInt(8));
			rs.close();
			/*
			 * 	getInt(), getString(), getDouble(), getDate()
			 */
		}catch(Exception ex)
		{
			ex.printStackTrace(); //����ó��
		}
		finally
		{
			disConnection();
			//��ȯ
		}
		return vo;
	}
	//��������
	public int databoardRowCount() 
	{
		int count=0;
		try
		{
			//1. �ּ� �б�
			getConnection();
			//2.SQL���� 
			String sql="SELECT COUNT(*) FROM databoard";
			//3. SQL���� ����
			ps=conn.prepareStatement(sql);
			//4. ���� ��� �ޱ�
			ResultSet rs=ps.executeQuery();
			//5. ������ ����
			rs.next(); //�����Ͱ� ��µ� ��ġ�� Ŀ�� �̵�
			count=rs.getInt(1);
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return count;
	}
	//3-4. ���� => ���ϸ��� ���� ���� �� ���� �� ���� -> ���ο� ���� �߰� 
	//3-5. ���� => ���ϰ� ���ÿ� ���� 
	//������ ���� ���� ���� ������ ���� �Ѵ� => �����ͺ��̽� ����
	public DataBoardVO databoardInfoData(int no)
	{
		DataBoardVO vo=new DataBoardVO();
		try
		{
			getConnection();
			String sql="SELECT filename,filesize FROM databoard "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setFilename(rs.getString(1));
			vo.setFilesize(rs.getInt(2));
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return vo;
	}
	public boolean databoardDelete(int no,String pwd)
	{
		boolean bCheck=false;
		try
		{
			getConnection();
			String sql="SELECT pwd FROM databoard "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			
			if(db_pwd.equals(pwd)) //���� (���ο���) ==> �ڵ����� ����
			{
				bCheck=true;
				//���� �����ͺ��̽����� ����
				sql="DELETE FROM databoard "
						+"WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				ps.executeUpdate();
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return bCheck;
	}
	//���� ������
	public DataBoardVO databoardUpdateData(int no)
	{
		DataBoardVO vo=new DataBoardVO();
		try
		{
			//1. Connection �ּҰ� ���
			getConnection();
			//2.SQL ����
			//2-1. ��ȸ�� ����
			String sql="SELECT no,name,subject,content "
					+"FROM databoard "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			/*
			 *   1) ȸ������ => �α���, ȸ������, ȸ������, ȸ��Ż��, ���̵� ã��, ��й�ȣ ã��
			 *   		     ----------------------------------------------
			 *   �Խ��� => ������ ���, ���� ���ϱ�
			 *   ����, ����, ��õ
			 */
			ps.setInt(1, no);
			//����� �б�
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			rs.close();
			/*
			 * 	getInt(), getString(), getDouble(), getDate()
			 */
		}catch(Exception ex)
		{
			ex.printStackTrace(); //����ó��
		}
		finally
		{
			disConnection();
			//��ȯ
		}
		return vo;
	}
	//���� ����
	public boolean databoardUpdate(DataBoardVO vo)
	{
		boolean bCheck=false;
		try
		{
			getConnection();
			//��й�ȣ �˻�
			String sql="SELECT pwd FROM databoard "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getNo());
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			
			if(db_pwd.equals(vo.getPwd()))
			{
				bCheck=true;
				sql="UPDATE databoard SET "
						+"name=?,subject=?,content=? "
						+"WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setInt(4, vo.getNo());
				ps.executeUpdate();
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return bCheck;
	}
}
