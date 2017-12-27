<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.text.*, my.*" import="my.shop.mall.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- shopping_list.jsp -->
<jsp:useBean id="order" class="my.OrdersDao" scope="session"/>
<jsp:useBean id="bean" class="my.OrderBean"/>

<%

	DecimalFormat df = new DecimalFormat("###,###");
	String memid = request.getParameter("memid");
	System.out.println("memid:"+memid);
	
	Vector<OrderBean> lists = null;
	if(memid != null){
		lists = order.getOrderList(memid);
	}else{
		lists = order.getOrderListAll();		
	}
	int list_total=0;
%>

<%@ include file="admin_top.jsp"%> 
<table>
<tr>
<td>
<h1 align=center>총 주문내역</h1>
<table id="ta">
	<tr>
	<td>
	<table width=600px align=center>
	<tr height=100px>
		<td colspan=5>
				<form action="shopping_list.jsp" method="post">
					<b>조회할 회원 아이디 입력:</b>
					<input type="text" name="memid">
					<input type="submit" value="내역 조회">
				</form>
			</td>
		</tr>
		
		<tr bgcolor="#D5D5D5" align="center" height=20px>		
			<th>고객명</th>
			<th>아이디</th>
			<th>상품명</th>
			<th>수량</th>
			<th>금액</th>
		</tr>
		<%
			if(lists != null){
				for(OrderBean ob : lists){
					
		%>
			<tr height=40px bgcolor="#D5D5D5" >
				<td align="center"><%=ob.getMname() %></td>
				<td align="center"><%=ob.getMid() %></td>
				<td align="center"><%=ob.getPname() %></td>
				<td align="center"><%=ob.getQty() %></td>
				<td align="right">\<%=df.format(ob.getAmount()) %></td>
			</tr>
			
		<% 
				list_total += ob.getAmount();
				}
			}
		
		%>	
		<tr  height=40px bgcolor="#D5D5D5" >
			<td colspan=4 align="center">총 합</td>
			<td align="right">\<%=df.format(list_total) %></td>
		</tr>
		</table>
	</td>
	</tr>	
	</table>
	
</td>
</tr>
</table>
	
<%@ include file="admin_bottom.jsp"%>
