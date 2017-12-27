<%@page import="board.BoardDao"%>
<%@page import="board.BoardBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="../display/display_top.jsp"%>


<%
	int pageSize = 10; //한페이지에 10개
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	String pageNum = request.getParameter("pageNum");
	if(pageNum == null){		//넘어온 페이지값이 없을경우 1페이지로
		pageNum = "1";
	}
	
	int currentPage = Integer.parseInt(pageNum);
	int startRow = (currentPage -1 ) * pageSize + 1;
	int endRow = currentPage * pageSize;
	int count = 0;
	int number = 0;
	
	List<BoardBean> articleList = null;
	BoardDao dbPro = BoardDao.getInstance();
		
	count = dbPro.getArticleCount(); // 전체 레코드 수 리턴
	
	System.out.println(count);

	if (count > 0) {
		 articleList = dbPro.getArticles(startRow, endRow);
    }
	
    number=count-(currentPage-1)*pageSize;

%>
<h1 align=center>게시판</h1>
<table width="600px"><tr><td align="right">
<a href="writeForm.jsp">
<font color="black" size="3px"><b>글쓰기</b></font></a>
</td></tr></table>
<table width="800px" id="tata2" align="center">
	<tr>
		<td align="right">(전체 글:<%=count%>)</td>
	</tr>
	<tr><td>
		<% if(count == 0) {%>
		<table>		
			<tr>
				<td align="center">
				게시판에 저장된 글이 없습니다
				</td>
			</tr>		
		</table>
		<% } else { %>
		<table width=600px align=center>
		<tr bgcolor="#D5D5D5" align="center" height=20px>
			<td align ="center" width="50">번 호</td>
			<td align="center"  width="250" >제  목</td> 
     		<td align="center"  width="100" >작성자</td>
     		<td align="center"  width="150" >작성일</td> 
      		<td align="center"  width="50"  >조 회</td>
     	</tr>			
		<%
			for(int i=0;  i < articleList.size() ; i++){
				BoardBean article = (BoardBean)articleList.get(i);
			
		%>
			<tr id="tr1" bgcolor="#D5D5D5" >
				<td align="center" width="50"><%=number-- %></td>
	 <td align="left" width="250" id="tr1">
		<%
	      int wid=0; 
	      if(article.getRe_level()>0){
	          wid=20*(article.getRe_level()); // 답글:1=>wid(20), 2=>wid(40), 3=>wid(60)
		%>
			  <img src="images/level.gif" width="<%=wid%>" height="16">
			  <img src="images/re.gif">
			  
		<%}else{%>
		  <img src="images/level.gif" width="<%=wid%>" height="16">
		<%}%>
           
      	<a href="content.jsp?num=<%=article.getNum()%>&pageNum=<%=currentPage%>" id="aa">
          	<%=article.getSubject()%></a> 
          	<% if(article.getReadcount()>=10){%>
         		<img src="images/hot.gif" border="0"  height="16"><%}%> </td>
         
         
         
    <td align="center"  width="100" id="tr1"> 
       <a href="mailto:<%=article.getEmail()%>" id="aa"><%=article.getWriter()%></a></td>
       
    <td align="center"  width="150" id="tr1"><%= sdf.format(article.getReg_date())%></td>
    <td align="center"  width="50" id="tr1"><%=article.getReadcount()%></td>    
  </tr>
     <%}%> <!-- for문 끝 -->
</table>
<%}%>


<%
    if (count > 0) {
        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
		 
		int pageBlock=10; 
      	int startPage = ( (currentPage-1) / pageBlock * pageBlock ) + 1; 
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) endPage = pageCount;
        
        if (startPage > 10) {    %>
        <a href="list.jsp?pageNum=<%= startPage - 10 %>">[이전]</a>
<%      }
        for (int i = startPage ; i <= endPage ; i++) {  %>
        <a href="list.jsp?pageNum=<%= i %>">[<%= i %>]</a>
<%
        }
        if (endPage < pageCount) {  %>
        <a href="list.jsp?pageNum=<%= startPage + 10 %>">[다음]</a>

        <%
        }
    }
%>
</td>
</tr>
</table>
<%@include file="../display/display_bottom.jsp"%>