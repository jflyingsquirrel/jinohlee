package my;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;





public class MemberDao {
	
Connection conn = null ;
	
	private static MemberDao dao = new MemberDao();
	
	private MemberDao() {
		
	}
	
	public static MemberDao getInstance(){
		return dao;
	}
	
	public Connection getConnection() {
		try{
			
		
		Context initContext = new InitialContext();
        Context envContext  = (Context)initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource)envContext.lookup("jdbc/OracleDB");
        conn = ds.getConnection();
		}catch(Exception ex){
			
		}
        return conn;
        
	}
	
	public int insertData(MemberBean member) throws Exception {
		PreparedStatement pstmt=null;
		System.out.println("insertData: "+member.getEmail());
		
		String sql = "insert into member(no, name, id, password, email, hp1, hp2, hp3, address) ";
		sql += "values(meseq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)" ;
		
		int cnt = -1 ;
		conn = getConnection();
		pstmt = conn.prepareStatement(sql) ;
		conn.setAutoCommit( false ) ;
		
		pstmt.setString(1, member.getName()) ;
		pstmt.setString(2, member.getId()) ;
		pstmt.setString(3, member.getPassword()) ;
		pstmt.setString(4, member.getEmail()) ;
		pstmt.setString(5, member.getHp1()) ;
		pstmt.setString(6, member.getHp2()) ;
		pstmt.setString(7, member.getHp3()) ;
		pstmt.setString(8, member.getAddress()) ;

		
		cnt = pstmt.executeUpdate() ;
		conn.commit() ;   
						
		return cnt  ;
		
	}

	public MemberBean getMemberInfo(String id, String password) throws Exception{
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select * from member where id=? and password=? "; 
		
		conn = getConnection();
		pstmt = conn.prepareStatement(sql) ;
		
		pstmt.setString(1, id) ;
		pstmt.setString(2, password) ;
		
		rs = pstmt.executeQuery() ;
		
		MemberBean member = null ;
		
		if( rs.next() ){
			member = getMemberBean( rs ) ;
		}
		
		return member ;
	}
	
	public ArrayList<MemberBean> Allselect()  throws Exception{
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select * from member"; 
		
		conn = getConnection();
		pstmt = conn.prepareStatement(sql) ;
		
		rs = pstmt.executeQuery() ;
		
		ArrayList<MemberBean> member = null ;
		
		if( rs.next() ){
			member = makeArrayList(rs) ;
		}
		
		return member ;		
		
	}
	
	
	public ArrayList<MemberBean> makeArrayList(ResultSet rs) throws SQLException{
		
		ArrayList<MemberBean> arr = new ArrayList<MemberBean>();

		while(rs.next()){
			int no=rs.getInt(1);
			String name=rs.getString(2);
			String id=rs.getString(3);
			String password=rs.getString(4);
			String email=rs.getString(5);
			String hp1=rs.getString(6);
			String hp2=rs.getString(7); 
			String hp3=rs.getString(8); 
			String address=rs.getString(9); 
			
			MemberBean mb = new MemberBean(no, name, id, password, email, hp1, hp2, hp3, address);
			
			arr.add(mb);
			
		} // while---------
		
		return arr;
		
	} // makeArrayList()-----------	
	
	public MemberBean getMemberfindid(String name, String email) throws Exception {
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select * from member where name=? and email=?"; 
		
		conn = getConnection();
		pstmt = conn.prepareStatement(sql) ;
		
		pstmt.setString(1, name) ;
		pstmt.setString(2, email) ;
				
		rs = pstmt.executeQuery() ;
		
		MemberBean member = null ;
		
		if( rs.next() ){
			member = getMemberBean( rs ) ;			
		}
		
		return member ;
		
	}
	
	public MemberBean getMemberfinpwd(String id, String name, String email) throws Exception{
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select * from member where name=? and email=? and id=?"; 
		
		conn = getConnection();
		pstmt = conn.prepareStatement(sql) ;
		
		pstmt.setString(1, name) ;
		pstmt.setString(2, email) ;
		pstmt.setString(3, id) ;
				
		rs = pstmt.executeQuery() ;
		
		MemberBean member = null ;
		
		if( rs.next() ){
			member = getMemberBean( rs ) ;			
		}
		
		return member ;		
	}
	
	
	private MemberBean getMemberBean(ResultSet rs) throws SQLException { // throws Exception도 가능
		
		MemberBean member = new MemberBean() ;
		
		member.setEmail(rs.getString("email")) ;
		member.setHp1(rs.getString("hp1")) ;
		member.setHp2(rs.getString("hp2"));
		member.setHp3(rs.getString("hp3"));
		member.setId(rs.getString("id"));
		member.setName(rs.getString("name")) ;
		member.setNo(rs.getInt("no")) ;
		member.setPassword(rs.getString("password"));
		member.setAddress(rs.getString("address")) ;
	
		
		return member;
	}
	
	public boolean searchID(String id) throws Exception{
		
		String sql = "select id from member where id=?";
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		boolean flag=false;
		
		conn = getConnection() ;
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,id);
		rs = pstmt.executeQuery();
		
		while(rs.next()){ 
			flag = true; 
		}
		
		System.out.println("flag:"+flag);
		return flag;			
		
	}
	
	//총 회원수 //main.jsp
	public int countMember() throws SQLException{
		int count= -1;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		
		String sql = "select count(*) from member";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		 if (rs.next()) {
			 count = rs.getInt(1);
      } 
		
		return count;
	}

}
