<%@page import="board.BoardBean"%>
<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../display/display_top.jsp"%>
    <%
    request.setCharacterEncoding("UTF-8");
       
    int num = Integer.parseInt(request.getParameter("num"));
  	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
  	String passwd = request.getParameter("passwd");
  	
  	BoardDao dbPro = BoardDao.getInstance();
  	BoardBean bean = dbPro.getArticle(num);
  	
	System.out.println("passwd:"+passwd+"/bean.getPasswd():"+bean.getPasswd());
	
  	if(passwd.equals(bean.getPasswd())){      
   		BoardBean article = dbPro.update(num);   
   		
       
    %>
    
<h1 align=center>수정하기</h1>
	<table width="800px" id="tata2" align="center">
	<tr>
	<td align=center width=600px>

	<form method="post" name="writeform" action="updatePro.jsp?pageNum=<%=pageNum %>">
		
	<table>
	<tr height="30px">
		<td width="100" align="center">이름</td>
		<td>
		<input type="text" name="writer" value="<%=article.getWriter() %>" readonly="readonly">
		<input type="hidden" name="num" value="<%=article.getNum() %>">
		</td>
		</tr>
	<tr>
	<td width="100" align="center">제목</td>
	<td><input type="text" name="subject" value="<%=article.getSubject() %>"></td>
	</tr>
	<tr>
	<td width="100" align="center">Email</td>
	<td><input type="text" name="email" value="<%=article.getEmail() %>"></td>
	</tr>
	<tr>
	<td width="100" align="center">내용</td>
	<td><input type="text" name="content" value="<%=article.getContent() %>"></td>
	</tr>

	<tr>
	<td width="100">비밀번호</td>
	<td><input type="text" name="passwd" value="<%=article.getPasswd() %>"></td>
	<td></td>
	</tr>
	</table>
	<table>
	<tr>
	<td colspan="2" align="center">
	<input type="submit" value="글수정">
	<input type="reset" value="다시작성">
	<input type="button" value="목록보기" onclick="document.location.href='list.jsp?pageNum=<%=pageNum %>'">
	</td>
	</tr>
	</table>
	</form>
<%
  	}else{
  		
  	%>
  	<script type="text/javascript">
  		alert("비밀번호가 틀렸습니다.")
  		history.back();
  	</script>  	
  	<%
  		
  	}

%>
</td>
</tr>
</table>

<%@include file="../display/display_bottom.jsp"%>