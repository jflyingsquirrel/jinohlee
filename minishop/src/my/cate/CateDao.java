package my.cate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class CateDao {
	
	private DataSource ds;
	
	Connection conn;
	
	private static CateDao instance;

	public static CateDao getInstance() {
		if(instance == null){
			instance = new CateDao();
		}
		return instance;
	}    
	
	private CateDao() { 		
		
		try{		
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/OracleDB");
			conn = ds.getConnection() ;
			
			System.out.println("Catedao");//test

		}catch( Exception e){
			e.printStackTrace();
		}
	}	

	//display_top2.jsp
	
	public ArrayList<CateBean> categoryBag() throws SQLException{
		
		System.out.println("categorybag");//test
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select * from cate where ccode='bag'";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		ArrayList<CateBean> arr = new ArrayList<CateBean>();
		
		while(rs.next()){
			
			System.out.println(rs.getInt("cnum"));
			
			CateBean record = new CateBean();
			record.setCnum(rs.getInt("cnum"));
			record.setCcode(rs.getString("ccode"));
			record.setCname(rs.getString("cname")) ;
		
			arr.add(record);
		}
		
		rs.close() ;
		pstmt.close() ;
		return arr;
	}
	
//displaytop
public ArrayList<CateBean> categoryShoes() throws SQLException{
		
		System.out.println("categoryshoes");//test
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select * from cate where ccode='shoes'";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		ArrayList<CateBean> arr = new ArrayList<CateBean>();
		
		while(rs.next()){
			
			System.out.println(rs.getInt("cnum"));
			
			CateBean record = new CateBean();
			record.setCnum(rs.getInt("cnum"));
			record.setCcode(rs.getString("ccode"));
			record.setCname(rs.getString("cname")) ;
		
			arr.add(record);
		}
		
		rs.close() ;
		pstmt.close() ;
		return arr;
	}
}
