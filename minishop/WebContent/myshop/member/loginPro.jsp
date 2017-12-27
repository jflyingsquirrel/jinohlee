<%@page import="my.*"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body> 
<%
	request.setCharacterEncoding("UTF-8") ;
	String id = request.getParameter("id") ;
	String password = request.getParameter("password") ; 
	String contextPath = request.getContextPath() ;	
	
	System.out.println("id:"+id);
%>

<%
	MemberDao dao =  MemberDao.getInstance(); 
	MemberBean member = dao.getMemberInfo(id, password) ;
	String viewPage = null ;
	if(member != null){
		
		System.out.println("getno:"+member.getNo());
		int no = member.getNo() ;
		id = member.getId();
		
		if( id.equals("admin")){			
			viewPage = contextPath + "/myshop/admin/main.jsp" ; //어드민페이지로가라
			
		}else{
			viewPage = contextPath + "/myshop/display/main.jsp?ccode=bag" ; //일반사용자페이지로가자
		}	
		session.setAttribute("memno", no) ; // memno는 결재하기할 때 사용함	//세션설정
		session.setAttribute("memid", id) ; 
		
	}else{
		viewPage = contextPath + "/main.jsp" ;
%>
	<script type="text/javascript">
		alert("가입하지 않은 회원입니다.");
	</script>
<%		
	}
%>
<script type="text/javascript">
	location.href="<%=viewPage%>";
</script>

</body>
</html>