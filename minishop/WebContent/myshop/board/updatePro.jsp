<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setCharacterEncoding("UTF-8");
	System.out.println("updatepro");
%>

<jsp:useBean id="article" class="board.BoardBean">
<jsp:setProperty property="*" name="article"/>
</jsp:useBean>

<%
	String pageNum = request.getParameter("pageNum");
	

	BoardDao dbPro = BoardDao.getInstance();
	
	int check = dbPro.updateArticle(article);
	System.out.println(check);
	if(check==1){
		response.sendRedirect("list.jsp?pageNum="+pageNum);
	}
%>
