<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*" import="java.sql.Timestamp"%>
   
<%
	request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="article" class="board.BoardBean">
<jsp:setProperty name="article" property="*" />
</jsp:useBean>

<%

	System.out.println("이메일:"+ article.getEmail());//test
	BoardDao dbPro = BoardDao.getInstance();

	article.setReg_date(new Timestamp(System.currentTimeMillis()));
	dbPro.insertArticle(article);
	
	response.sendRedirect("list.jsp");

%>
