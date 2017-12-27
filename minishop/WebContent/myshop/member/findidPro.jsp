<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="my.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	request.setCharacterEncoding("UTF-8");
	String name= request.getParameter("name");
	String email = request.getParameter("email");
	
	System.out.println("findid: "+email);

%>
<jsp:useBean id="member" class="my.MemberBean"></jsp:useBean>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	int cnt = -1;
	MemberDao dao = MemberDao.getInstance();
	member = dao.getMemberfindid(name, email);
	
	String msg="", url="";
	
	if(member != null){
		msg = "비밀번호 변경 메일이 발송되었습니다";
		
	}else{		
		msg = "없는 회원입니다";
	}
	url=request.getContextPath()+"/myshop/member/login.jsp";

%>

<script type="text/javascript">
	alert("<%=msg%>");
	location.href="<%=url%>";
 </script>
 
 