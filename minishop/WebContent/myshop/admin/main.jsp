<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="my.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="admin_top.jsp"%>
<jsp:useBean id="order" class="my.OrdersDao" scope="session"/>
<table>
<tr>
<td>
<h1 align=center>관리자홈</h1>
<table id="ta">
<tr><td align=center>
<table width=300px height=300px border=1px bordercolor=black>
<% 
	java.util.Date date = new java.util.Date();
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	String today = sdf.format(date);
	
	
	ProdDao dao = ProdDao.getInstance();
	MemberDao dao2 = MemberDao.getInstance();
	
	int cnt = dao.countProd();	
	int cnt2 = order.countOrder();
	int cnt3 = dao2.countMember();
	
	int cnt4 = cnt3-1;
%>
	<tr>
		<td align=center><h1><%=today %></h1></td>
	</tr> 
	<tr>
		<td align=center>총 상품건수: <%=cnt %>건</td>
	</tr> 
	<tr>
		<td align=center>총 주문건수: <%=cnt2 %>건</td>
	</tr> 
	<tr>
		<td align=center>총 회원수: <%=cnt4 %>명</td>
	</tr> 
	
	</table>
	</td>
	</tr>
</table>
</td>
</tr>
</table>
<%@include file="admin_bottom.jsp"%>