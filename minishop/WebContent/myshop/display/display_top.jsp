<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.*,my.cate.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- bag -->
<%
	String contextPath = request.getContextPath();
	String memid = (String)session.getAttribute("memid") ; 	
	System.out.println("memid:"+memid); // hong
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

#tr1{
		bgcolor: #D5D5D5;
		align:center;;
		height:20px; 
		font-family: 굴림;
		color: black;	
}

#aa{
		font-family: 돋움;
		color: black;
		font-size: 20px;	

}

.ca{
	font-size: 30px;
}
div{
	width: 200px;
	text-align: center;
}


#tata{
	text-align: center;
	background: pink;
	padding: 10px;
	font-family: 굴림;
	font-color: #660033;
	width: 500px;
	height: 500px;
	}
#tata2{
	align: center;
	background: pink;
	padding: 10px;
	font-family: 굴림;
	font-color: #660033;
	width: 600px;
	height: 400px;
	}	
	
img{
	padding: 10px;	
}

#tata3{
	border:1px dashed;
	bordercolor:black;
	width:600px;
	align:center;
	}
	
table,td,td{
	align:center;
}

</style>
</head>
<body>

<table align=center width=600px cellpadding=10px>
<tr height=50>
		<td colspan=2 align=center class=sel>
			<a href="<%=contextPath%>/main.jsp">HOME</a><font color="#80bfff"> |</font>
			<a href="<%=contextPath%>/myshop/display/main.jsp?ccode=bag">BAG</a><font color="#80bfff"> |</font>
			<a href="<%=contextPath%>/myshop/display/main.jsp?ccode=shoes">SHOES</a><font color="#80bfff"> |</font>
			<a href="<%=contextPath%>/myshop/display/mall_cartList.jsp">장바구니</a><font color="#80bfff"> |</font>
			<a href="<%=contextPath%>/myshop/display/mall_orderlist.jsp">주문내역</a><font color="#80bfff"> |</font>
			<a href="<%=contextPath%>/myshop/board/list.jsp">게시판</a><font color="#80bfff"> |</font>
			<a href="<%=contextPath%>/myshop/display/company.jsp">회사소개</a>
			</td>
			</tr>
			<tr>
			<td align=right>
			<span class=logout>
			<%if(memid==null) {	%>
			<a href="<%=contextPath%>/myshop/member/login.jsp">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<font color="#ff4d4d" size="2px">로그인</font></a>
			<%}else{%>
			<%=memid %>님/ 			
			<a class=logout href="<%=contextPath%>/logout.jsp">
				<font size="2px">로그아웃</font>
			</a>		
			<%}%>
			</span> 
		</td>
	</tr>

</table>
<br><br><br><br>
<table border=1px bordercolor=#80bfff width=800px height=600px cellpadding=10px align=center>
<tr>
	<td align=center>