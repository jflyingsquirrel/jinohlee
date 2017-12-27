package my;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;



public class ProdDao {
	
	private DataSource ds;
	Connection conn;	
	
	private static ProdDao instance;
	
	public static ProdDao getInstance() {
		if(instance == null){
			instance = new ProdDao();
		}
		
		return instance;
	}    
	
	private ProdDao() { 

		try{		
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/OracleDB");
			conn = ds.getConnection() ;

		}catch( Exception e){
			e.printStackTrace();
		}
	}
	
	public int insertProduct(MultipartRequest mr) throws SQLException{
		
		System.out.println("insertproduct");
		
		PreparedStatement pstmt = null;
		
		conn.setAutoCommit(false);
		
		String sql = "insert into prod (pnum, pname, pcategory, pcompany, pimage, pqty, price, pspec, pcontents, point, pcate2, pinputdate) "
				+ "values(prodseq.nextval, ?,?,?,?,?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		
		String pname= mr.getParameter("pname");
		String pcategory= mr.getParameter("pcategory");
		String pimage=mr.getFilesystemName("pimage");
		String pcompany= mr.getParameter("pcompany");
		String pqty= mr.getParameter("pqty");
		String price= mr.getParameter("price");
		String pspec= mr.getParameter("pspec");
		String pcontents= mr.getParameter("pcontents");
		String point= mr.getParameter("point");
		String pcate2= mr.getParameter("pcate2");
		
		pstmt.setString(1, pname);
		pstmt.setString(2, pcategory);
		pstmt.setString(3, pcompany);
		pstmt.setString(4, pimage);		
		pstmt.setString(5, pqty);
		pstmt.setString(6, price);
		pstmt.setString(7, pspec);
		pstmt.setString(8, pcontents);
		pstmt.setString(9, point);
		pstmt.setString(10, pcate2);
		
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		pstmt.setString(11, today); // 2017-10-24
		int cnt = -1 ;
		cnt = pstmt.executeUpdate();
		
		conn.commit() ;
		pstmt.close() ;
		
		return cnt;
	}
	

	//prod_list.jsp 
	public ArrayList<ProdBean> productAll() throws SQLException{
		System.out.println("productAll()");
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql = "select * from prod order by pnum";
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		ArrayList<ProdBean> arr = this.makeArrayList(rs);
	 
		rs.close() ;
		pstmt.close() ;
		//conn.close() ;
		return arr ;		
	}
	
	//prod_update.jsp	//prod_view.jsp
	public ProdBean selectByNum(String pnum) throws SQLException{
		
		PreparedStatement pstmt =null;
		ResultSet rs= null;		
		String sql = "select * from prod where pnum="+pnum;
		pstmt= conn.prepareStatement(sql);
		rs= pstmt.executeQuery();
		
		ArrayList<ProdBean> arr = this.makeArrayList(rs);
		
		ProdBean product = null;
		
		if(arr.size()!=0) {
			product = arr.get(0);
		}
		
		rs.close();
		pstmt.close();
		
		return product;
		
	}
	//mall_cglist.jsp 
	public ArrayList<ProdBean> productByCate(String cname) throws SQLException{
		
		System.out.println("bycate:" +cname);
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql = "select * from prod where pcate2=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, cname);
		rs = pstmt.executeQuery();
		
		ArrayList<ProdBean> arr = this.makeArrayList(rs);
	 
		rs.close() ;
		pstmt.close() ;
		//conn.close() ;
		return arr ;	
		
	}
	
	//main.jsp 
	public ArrayList<ProdBean> productbyCategory(String cname) throws SQLException{
		
		System.out.println("bycate:" +cname);
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql = "select * from prod where pcategory=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, cname);
		rs = pstmt.executeQuery();
		
		ArrayList<ProdBean> arr = this.makeArrayList(rs);
	 
		rs.close() ;
		pstmt.close() ;
		//conn.close() ;
		return arr ;	
		
	}
	
	
	//prod_delete.jsp
	public int deleteProduct(String pnum) throws SQLException{
		int cnt=-1;
		
		PreparedStatement pstmt=null;
		String sql = "delete from prod where pnum="+pnum;
		pstmt = conn.prepareStatement(sql);
		
		cnt = pstmt.executeUpdate();
		
		conn.commit();
		pstmt.close();		
		
		return cnt;		
		
	}
	
	//prod_updatepro.jsp
	public int updateProduct(MultipartRequest mr) throws SQLException {
		
		PreparedStatement pstmt = null ;

		String sql ="update prod set "
				+ "pname=?, pcategory=?, pcompany=?, pimage=?, pqty=?, price=?, pspec=?, pcontents=?, point=?, pcate2=?"
				+ "where pnum=?";								
		 
		pstmt = conn.prepareStatement(sql);
		
		String pname = mr.getParameter("pname");
		String pcategory = mr.getParameter("pcategory");
		String pcompany = mr.getParameter("pcompany");
		
		String pimage = mr.getFilesystemName("pimage"); // 새로 입력한 이미지
		if(pimage==null){
			pimage=mr.getParameter("pimage2"); // 기존 이미지
		}
		
		String pqty=mr.getParameter("pqty");
		String price=mr.getParameter("price");
		String pspec=mr.getParameter("pspec");
		String pcontents=mr.getParameter("pcontents");
		String point=mr.getParameter("point");	
		String pcate2=mr.getParameter("pcate2");	
		String pnum=mr.getParameter("pnum");
		
		
		pstmt.setString(1,pname);
		pstmt.setString(2,pcategory);
		pstmt.setString(3,pcompany);
		pstmt.setString(4,pimage);
		pstmt.setInt(5, Integer.parseInt(pqty)); // Integer.parseInt("a")
		pstmt.setInt(6, Integer.parseInt(price));
		pstmt.setString(7,pspec);
		pstmt.setString(8,pcontents);
		pstmt.setInt(9, Integer.parseInt(point));
		pstmt.setString(10, pcate2);
		pstmt.setInt(11, Integer.parseInt(pnum));
		
		int cnt = -1;
		cnt = pstmt.executeUpdate();
		
		pstmt.close() ;
		//conn.close() ;	
		
		return cnt ;
		
	}
	
	
	
	//productAll(); //selectByNum();
	public ArrayList<ProdBean> makeArrayList(ResultSet rs) throws SQLException{
		ArrayList<ProdBean> arr = new ArrayList<ProdBean>();

		while(rs.next()){
			int pnum=rs.getInt(1);
			String pname=rs.getString(2);
			String pcategory=rs.getString(3);
			String pcompany=rs.getString(4);
			String pimage=rs.getString(5);
			int pqty=rs.getInt(6);
			int price=rs.getInt(7);
			String pspec=rs.getString(8);
			String pcontents=rs.getString(9); 
			int point=rs.getInt(10);
			String pinputdate = rs.getString(11);
			String pcate2 = rs.getString(12);
			ProdBean dto = new ProdBean(pnum,pname,pcategory,pcompany,pimage,pqty,price,pspec,pcontents,point,pinputdate, pcate2);
			
			arr.add(dto);
			
		} // while---------
		
		return arr;		
		
	}
	
	public int countProd() throws SQLException{
		int count= -1;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		
		String sql = "select count(*) from prod";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		 if (rs.next()) {
			 count = rs.getInt(1);
      } 
		
		return count;
	}
	
}