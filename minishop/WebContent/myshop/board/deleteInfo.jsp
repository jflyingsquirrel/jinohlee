<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@include file="../display/display_top.jsp"%>
    
    
    <%
    request.setCharacterEncoding("UTF-8");
       
    int num = Integer.parseInt(request.getParameter("num"));
  	int pageNum = Integer.parseInt(request.getParameter("pageNum"));

    %>
    
    <table width="800px" id="tata2" align="center">
    	<tr>
    	<td align=center>
    	<form action="deleteForm.jsp">
    		<table>
    			<tr><td>비밀번호를 입력해주세요</td></tr>
    			<tr><td>
    			<input type="text" name="passwd">
    			<input type="hidden" name=pageNum value=<%=pageNum %>>
    			<input type="hidden" name=num value=<%=num %>>    			
    			</td>
    			<td><input type="submit" value="확인">
    			</td>
    			</tr>
    			
    		</table>
    	</form>
    	</td>
    	</tr>
    
    </table>
    
    
   <%@include file="../display/display_bottom.jsp"%>