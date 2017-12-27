<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="my.*"%>

<jsp:useBean id="mallCart" class="my.CartBean" scope="session" />

<%
	request.setCharacterEncoding("UTF-8");

	String pnum= request.getParameter("pnum");
	String oqty = request.getParameter("oqty");
	
	System.out.println("pnum: "+pnum+"/oqty: "+oqty);

 		
	mallCart.addProduct(pnum, oqty);
	response.sendRedirect("mall_cartList.jsp");
%>	