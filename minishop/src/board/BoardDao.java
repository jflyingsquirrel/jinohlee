package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class BoardDao {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url =" jdbc:oracle:thin:@localhost:1521:xe";
	String dbid ="jspid";
	String dbpw = "jsppw";
	Connection conn = null;
	PreparedStatement pstmt= null;
	ResultSet rs = null;
	
	private static BoardDao instance= new BoardDao();
	
	public static BoardDao getInstance() {
		return instance;
	}
	
	private BoardDao() {
		System.out.println("객체생성");
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, dbid, dbpw);
			
			System.out.println("접속");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public int getArticleCount() {
		
		System.out.println("getArticleCount");
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		String sql = "select count(*) as cnt from board2";
		int x = -1;
		
		try {
			
			pstmt= conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x= rs.getInt("cnt");	//cnt별칭에 들어가있는 값을 가져와라
			}
			
			rs.close();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		}		
		return x;		
	}
	
	public List<BoardBean> getArticles(int start, int end){
		
		System.out.println("start: "+start+"/end: "+ end);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardBean> articleList = null;
		
		String sql = "select num, writer, kind, email, subject, passwd, reg_date, readcount, ref, re_step, re_level, content ";
		sql += "from(select rownum as rank, num, writer, kind, email, subject, passwd, reg_date, readcount, ref, re_step, re_level, content ";
		sql += "from(select num, writer, kind, email, subject, passwd, reg_date, readcount, ref, re_step, re_level, content ";
		sql += "from board2 ";
		sql += "order by ref desc, re_step asc)) ";
		sql += "where rank between ? and ?";
		
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				articleList = new ArrayList<BoardBean>();
				
				do {
					BoardBean article = new BoardBean();
					int num = rs.getInt("num");
					String writer =rs.getString("writer");
					String kind =rs.getString("kind");
					String email =rs.getString("email");
					String subject =rs.getString("subject");
					String passwd =rs.getString("passwd");
					Timestamp Reg_date=rs.getTimestamp("Reg_date");
					int readcount =rs.getInt("readcount");
					int ref =rs.getInt("ref");
					int re_step =rs.getInt("re_step");
					int re_level=rs.getInt("re_level");
					String content =rs.getString("content");
					
					article.setNum(num);
					article.setWriter(writer);
					article.setKind(kind);
					article.setEmail(email);
					article.setSubject(subject);
					article.setPasswd(passwd);
					article.setReg_date(Reg_date);
					article.setReadcount(readcount);
					article.setRef(ref);
					article.setRe_step(re_step);
					article.setRe_level(re_level);
					article.setContent(content);
					
					articleList.add(article);
					
				}while(rs.next());				
			}
			rs.close() ;
			pstmt.close() ;
	}catch (SQLException e) {
		e.printStackTrace();
	}

	return articleList;
	
}

	public BoardBean getArticle(int num) {
		
		System.out.println("num="+num);//test
		
		String sql= "update board2 set readcount = readcount+1 where num=?";
		//조회수
		
		String sql2= "select * from board2 where num=?";
		
		PreparedStatement pstmt= null;
		BoardBean article = null;
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);			
			rs= pstmt.executeQuery();
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, num);			
			rs= pstmt.executeQuery();
		
		while(rs.next()) {	
			
			article = new BoardBean();
			article.setNum(rs.getInt("num"));
			article.setWriter(rs.getString("writer"));
			article.setKind(rs.getString("kind")); 
			article.setEmail(rs.getString("email"));
			article.setSubject(rs.getString("subject"));
			article.setPasswd(rs.getString("passwd"));
			article.setReg_date(rs.getTimestamp("reg_date"));
			article.setReadcount(rs.getInt("readcount"));
			article.setRef(rs.getInt("ref"));
			article.setRe_step(rs.getInt("re_step"));
			article.setRe_level(rs.getInt("re_level"));
			article.setContent(rs.getString("content"));
			
		}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return article;
	}
	
	public void insertArticle(BoardBean bean) {	//원글쓰기
	
		String sql = "insert into board2(num,writer,kind, email,subject,passwd,reg_date,ref,re_step,re_level,content) ";
		sql+="values(board2_seq.nextval,?,?,?,?,?,?,board2_seq.nextval,?,?,?)";
	
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getWriter());
			pstmt.setString(2, bean.getKind());
			pstmt.setString(3, bean.getEmail());			
			pstmt.setString(4, bean.getSubject());
			pstmt.setString(5, bean.getPasswd());
			pstmt.setTimestamp(6, bean.getReg_date());
			pstmt.setInt(7, 0);	//re_step//원글의댓글순서
			pstmt.setInt(8, 0);	//re_level//원글0//하위/댓글이면1 댓댓글이면2
			pstmt.setString(9, bean.getContent());
			
			pstmt.executeUpdate();	
			
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
	}
	
	public BoardBean update(int num) {
		System.out.println("num="+num);//test		
		
		String sql= "select * from board2 where num=?";
		
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		BoardBean bean = null;
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);			
			rs= pstmt.executeQuery();
		
		while(rs.next()) {
			
			int num2 = rs.getInt("num");
			String writer =rs.getString("writer");
			String subject =rs.getString("subject");
			String email= rs.getString("email");
			String content =rs.getString("content");
			String passwd =rs.getString("passwd");
			
			
			bean = new BoardBean();
			bean.setNum(num2);
			bean.setWriter(writer);
			bean.setSubject(subject);
			bean.setEmail(email);
			bean.setContent(content);
			bean.setPasswd(passwd);
			

		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return bean;
		
		
	}
	
	//updatePro.jsp
	public int updateArticle(BoardBean article) {			
		
		String sql = "update board2 set subject=?, email=?, content=?, passwd=? where num=?";
		PreparedStatement pstmt = null;
		
		int cnt =-1;
				
		try {
			
			System.out.println(article.getNum());//test
			
			pstmt = conn.prepareStatement(sql);		
		
			pstmt.setString(1, article.getSubject());
			pstmt.setString(2, article.getEmail());
			pstmt.setString(3, article.getContent());
			pstmt.setString(4, article.getPasswd());
			pstmt.setInt(5, article.getNum());	
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return cnt;		
	}
	
	
	//deleteForm.jsp
	public int delete(int num) {
		
		String sql = "delete from board2 where num="+num;
		PreparedStatement pstmt = null;
		
		int cnt = -1;
		
		try {
			pstmt = conn.prepareStatement(sql);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return cnt;
	}
	
	
	//댓글쓰기
	public void replyArticle(BoardBean article) {		
		System.out.println("replyarticle");
		
		int ref=article.getRef();
		int re_step=article.getRe_step();
		int re_level=article.getRe_level();
		
		String sql2 = "update board2 set re_step = re_step+1 where ref= ? and re_step > ?" ;

		try {
			pstmt = conn.prepareStatement(sql2);
		
		pstmt.setInt(1, ref);
		pstmt.setInt(2, re_step);
		pstmt.executeUpdate();
		re_step=re_step+1;
		re_level=re_level+1;

		String sql = "insert into board2(num,writer,kind, email,subject,passwd,reg_date,ref,re_step,re_level,content) ";
		sql+="values(board2_seq.nextval,?,?,?,?,?,?,?,?,?,?)";

		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, article.getWriter());
		pstmt.setString(2, article.getKind());
		pstmt.setString(3, article.getEmail());			
		pstmt.setString(4, article.getSubject());
		pstmt.setString(5, article.getPasswd());
		pstmt.setTimestamp(6, article.getReg_date());
		pstmt.setInt(7, ref);
		pstmt.setInt(8, re_step);
		pstmt.setInt(9, re_level);
		pstmt.setString(10, article.getContent());
		
		pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
}
