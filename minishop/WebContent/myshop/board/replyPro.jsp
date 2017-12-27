<%@page import="java.sql.Timestamp"%>
<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="article" class="board.BoardBean">
	<jsp:setProperty name="article" property="*" />
</jsp:useBean>


<%
	article.setReg_date(new Timestamp(System.currentTimeMillis()) );

 	BoardDao dbPro = BoardDao.getInstance(); 
    dbPro.replyArticle(article); 
        
    response.sendRedirect("list.jsp");
 %>