<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="my.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%
	String pnum= request.getParameter("pnum");
	
	ProdDao pdao = ProdDao.getInstance();
	int result=pdao.deleteProduct(pnum.trim());
 
	String msg="",url="prod_list.jsp";
	if(result>0){
		msg="상품 삭제 성공!";
	}else{
		msg="상품 삭제 실패!";
	}
%> 

<script type="text/javascript">
	alert("<%=msg%>");
	location.href="<%=url%>";
</script>