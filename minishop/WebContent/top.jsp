<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String contextPath = request.getContextPath();
	String memid = (String)session.getAttribute("memid") ; 	
	System.out.println("memid:"+memid); // hong
%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<table width=800px align=center>
<tr>
	<td id=subject width=500px; height=300px;>
		<a href="main.jsp"><font color="#a64dff">shose/bag</font></a>
	</td>
	<td>
	 	<a href="myshop/admin/mainok.jsp">관리자page</a><br>
	 	</td>
	 	<td>
	 	<input type="button" value="회원 가입" onclick="location.href='<%=request.getContextPath()%>/myshop/member/register.jsp'">&nbsp;&nbsp;&nbsp;
		<br>
		<% if(memid==null){ %>
		<input type="button" value="로그인" onclick="location.href='<%=request.getContextPath()%>/myshop/member/login.jsp'">
	<%}else{
		%>
		<input type="button" value="로그아웃" onclick="location.href='<%=request.getContextPath()%>/logout.jsp'">
		<%		
	} %>
	
	</td>
</tr>
<tr>
<td>

