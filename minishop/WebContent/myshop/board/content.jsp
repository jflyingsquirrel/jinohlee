<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*"%>
<%@include file="../display/display_top.jsp"%>

    <%
    request.setCharacterEncoding("UTF-8");    
    %>
    
    <%
    int num =  Integer.parseInt(request.getParameter("num"));
    String pageNum =  request.getParameter("pageNum");
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    BoardDao dbPro = BoardDao.getInstance();
    BoardBean article = dbPro.getArticle(num);
 
    
    int ref=article.getRef();
    int re_step=article.getRe_step();
    int re_level=article.getRe_level();
    System.out.println(num+","+ref+","+re_step+","+re_level);     
    %>


<h1 align=center>게시판</h1>
<table width="600px"><tr><td align="right">
<a href="writeForm.jsp">
<font color="black" size="3px"><b>글쓰기</b></font></a>
</td></tr></table>
<table width="800px" id="tata2" align="center">

<tr height="30" bgcolor="#D5D5D5" align="center" height="20px">
<td width="125">글번호</td>
<td><%= article.getNum() %></td>
<td width="125">조회수</td>
<td><%= article.getReadcount() %></td>
</tr>

<tr bgcolor="#D5D5D5" align="center" height="30px">
<td width="125">작성자</td>
<td><%= article.getWriter() %></td>
<td width="125">작성일</td>
<td><%= sdf.format(article.getReg_date()) %></td>
</tr>

<tr bgcolor="#D5D5D5" align="center" height="30px">
<td width="125">글제목</td>
<td colspan="5" width="375"><b>[<%= article.getKind() %>]<%= article.getSubject() %></b></td>
</tr>

<tr bgcolor="#D5D5D5" align="center" height="300px">
<td width="125">글내용</td>
<td colspan="5" align="left" colspan="3"><%= article.getContent() %></td>
</tr>

<tr>
<td colspan="4" align="right" id="sub">
<input type="button" value="글수정" 
	onclick="document.location.href=
	'updateinfo.jsp?num=<%=article.getNum()%>&pageNum=<%=pageNum%>'">
	&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="글삭제" 
	onclick="document.location.href=
	'deleteInfo.jsp?num=<%=article.getNum()%>&pageNum=<%=pageNum%>'">
		&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="답글쓰기" 
	onclick="document.location.href=
	'replyForm.jsp?num=<%=num%>&ref=<%=ref%>&re_step=<%=re_step%>&re_level=<%=re_level%>'">
		&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="글목록" 
	onclick="document.location.href=
	'list.jsp?num=<%=pageNum%>'">
	
</td>
</tr>
</table>

	<%@include file="../display/display_bottom.jsp"%>