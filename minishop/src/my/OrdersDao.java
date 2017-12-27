package my;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class OrdersDao {
	
	private DataSource dataFactory;
	Connection conn;

	public OrdersDao() {   
		try{		
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/OracleDB");      
			conn = dataFactory.getConnection();
		}catch( Exception e){
			e.printStackTrace();
		}	
	}
	
	//mall_calculate.jsp
	public int insertOrders(int no, Vector<ProdBean> cv) throws SQLException{
	
		PreparedStatement pstmt = null ;
		String sql = "" ;
		int cnt  = -1;
		
		for (int i = 0; i < cv.size() ; i++) {

			conn.setAutoCommit( false );
			cnt = -1 ;

			sql="insert into ord(orderId, memno, pnum, qty, amount) "; 
			sql += "values(orderseq.nextval, ?, ?, ?, ?) " ;
			pstmt=conn.prepareStatement(sql);
		
			pstmt.setInt(1, no ); // 회원 번호 
			pstmt.setInt(2, cv.get(i).getPnum() ); // 상품번호 
			pstmt.setInt(3, cv.get(i).getPqty() ); // 상품수량
			pstmt.setInt(4, cv.get(i).getTotalPrice() ); // 전체가격

			cnt = pstmt.executeUpdate(); 
			conn.commit() ;
		}		

		pstmt.close() ;

		return cnt  ;
		
		
	}
	//shopping_list.jsp
	public Vector<OrderBean> getOrderListAll() throws SQLException{
		
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		Vector<OrderBean> lists = null;
		
		String sql = "select m.name mname, m.id mid, p.pname, o.qty, o.amount ";
		sql+= "from (member m join ord o on m.no = o.memno) join prod p on o.pnum=p.pnum";
		
		
		pstmt = conn.prepareStatement(sql);		
		rs=pstmt.executeQuery();
		
		lists = this.makeVector(rs);
		
		rs.close();
		pstmt.close();		
		
		return lists;
		
	}
	
	//main.jsp
	public int countOrder() throws SQLException{
		int count= -1;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		
		String sql = "select count(*) from ord";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		 if (rs.next()) {
			 count = rs.getInt(1);
      } 
		
		return count;
	}
	
	//shopping_list.jsp
		public Vector<OrderBean> getOrderList(String memid) throws SQLException{
			
			PreparedStatement pstmt =null;
			ResultSet rs = null;
			Vector<OrderBean> lists = null;
			
			String sql = "select m.name mname, m.id mid, p.pname, o.qty, o.amount ";
			sql+= "from (member m join ord o on m.no = o.memno) join prod p on o.pnum=p.pnum ";
			sql+= "where m.id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memid);
			rs=pstmt.executeQuery();
			
			lists = this.makeVector(rs);
			
			rs.close();
			pstmt.close();		
			
			return lists;
			
		}
		
		public Vector<OrderBean> makeVector(ResultSet rs) throws SQLException {
				
			Vector<OrderBean> lists = new Vector<OrderBean>();
			
			while(rs.next()) {
				OrderBean bean = new OrderBean();
				bean.setMname(rs.getString("mname"));
				bean.setMid(rs.getString("mid"));
				bean.setPname(rs.getString("pname"));
				bean.setQty(rs.getInt("qty"));
				bean.setAmount(rs.getInt("amount"));
				
				
				lists.add(bean);
				
			}
					
			return lists;
		}//makeVector
	
	
	
}
