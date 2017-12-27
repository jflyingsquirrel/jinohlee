<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="mallCart" class="my.CartBean" scope="session"></jsp:useBean>

<%
	String pnum = request.getParameter("pnum");
	
	mallCart.setDelete(pnum);
	response.sendRedirect("mall_cartList.jsp");

%>