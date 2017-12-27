<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.sql.*"%>
<%@ page import="my.*"%>

<%
	String userid = request.getParameter("userid");
	System.out.print("userid:"+userid);
	String str = "";
	
	try {
		MemberDao dao = MemberDao.getInstance(); 
		boolean isCheck = dao.searchID(userid); 
		System.out.println("isCheck:" + isCheck);
		
		if (isCheck) {
			str = "NO";
			out.print(str);
 											  
		} else {
			str = "YES";
			out.print(str);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>



