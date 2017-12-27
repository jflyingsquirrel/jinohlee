<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.*,my.cate.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String contextPath = request.getContextPath(); // 
%> 
<html>
<head>
<style>
a:link, a:visited, a:hover{
	text-decoration:none;
	color: #80bfff;
	font-size: 20px;
	font-family: verdana;
	
}

.ca{
	font-size: 30px;
	color: #004d66;
}


#ta{
	
	padding: 20px;		
	align:center; 
	width: 700px;
	height:600px;
	background: pink;
}

.tata{
	
	font-family: 돋움;

}


</style>
</head>
<body>

<table align=center width=800px cellpadding=10px>
<tr height=50>
		<td colspan=2 align=center>
			<a href="<%=contextPath%>/main.jsp">HOME</a><font color="#80bfff"> |</font>
			<a href="<%=contextPath%>/myshop/admin/main.jsp">관리자홈</a><font color="#80bfff"> |</font>
			<a href="<%=request.getContextPath()%>/logout.jsp">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<font color="#ff4d4d" size="2px">로그아웃</font></a>
	</tr>
<tr>
<td>
</td>
<tr>
<td width=180px align=center valign=top> 
		<table width=100% align=left>
		
		<tr class="ca">
	
		<td align=center>
			<a href="<%=request.getContextPath()%>/myshop/admin/prod_input.jsp">상품 등록</a>
		</td>
		
		<td align=center>
			<a href="<%=request.getContextPath()%>/myshop/admin/prod_list.jsp">상품 목록</a>
		</td>
		
		<td align=center>
			<a href="<%=request.getContextPath()%>/myshop/admin/membercheck.jsp">회원 관리</a>
		</td>	
		
		<td align=center>
			<a href="<%=request.getContextPath()%>/myshop/admin/shopping_list.jsp">쇼핑 내역</a>
		</td>		
		
		<td align=center>
			<a href="<%=request.getContextPath()%>/myshop/board/list.jsp">게시판</a>
		</td>
</tr>
</table>
<br><br><br><br><br>
