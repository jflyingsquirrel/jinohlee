<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.text.*, my.*" import="my.shop.mall.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<jsp:useBean id="order" class="my.OrdersDao" scope="session"/>
<jsp:useBean id="bean" class="my.OrderBean"/>



<%@ include file="display_top.jsp"%> 

<%

	DecimalFormat df = new DecimalFormat("###,###");
	System.out.println("mall_order: "+ memid);
	
	Vector<OrderBean> lists = null;
	if(memid != null){
		lists = order.getOrderList(memid);
	}else{
		%>
		<script type="text/javascript">
		alert("회원으로 로그인을 해주세요");
		location.href="<%=request.getContextPath()%>/myshop/member/login.jsp";
		</script>
		<%		
	}
	int list_total=0;
%>


<table>
<tr>
<td>
<h1 align=center>총 주문내역</h1>
<table id="ta">
	<tr>
	<td>
	<table width=600px align=center>
		<tr bgcolor="#D5D5D5" align="center" height=20px>		
			<th>상품명</th>
			<th>수량</th>
			<th>금액</th>
		</tr>
		<%
			if(lists != null){
				for(OrderBean ob : lists){
					
		%>
			<tr height=40px bgcolor="#D5D5D5" >
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
			<td colspan=2 align="center">총 합</td>
			<td align="right">\<%=df.format(list_total) %></td>
		</tr>
		</table>
	</td>
	</tr>	
	</table>
	
</td>
</tr>
</table>
	
<%@ include file="display_bottom.jsp"%>
