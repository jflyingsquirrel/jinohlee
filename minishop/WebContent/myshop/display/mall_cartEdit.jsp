<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="mallCart" class="my.CartBean" scope="session"></jsp:useBean>


<%
	String oqty = request.getParameter("oqty");
	String pnum = request.getParameter("pnum");
	String edit = request.getParameter("edit"); 
	
	System.out.println("oqty:"+oqty+"pnum:"+pnum+"edit:"+edit);
	
	mallCart.setEdit(oqty, pnum, edit);
	response.sendRedirect("mall_cartList.jsp");

%>