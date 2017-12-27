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
   		dbPro.delete(num);      		
       
   	response.sendRedirect("list.jsp?pageNum="+pageNum);
    %>

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