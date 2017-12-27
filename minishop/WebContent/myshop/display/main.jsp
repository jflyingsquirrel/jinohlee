<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="my.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%

	String ccode= request.getParameter("ccode");
	System.out.println("ccode:"+ccode);

if(ccode.equals("bag")){
	%>
	<%@include file="display_topbag.jsp"%>
<% }else{
	
%>
	<%@include file="display_topshoes.jsp"%>	
<%}%>
<table>
<tr>
<%
	ProdDao dao = ProdDao.getInstance();
	ArrayList<ProdBean> list = dao.productbyCategory(ccode);
	
	int count = 0;
	for(ProdBean prod : list ){
		
		String pimage = prod.getPimage();
		String imgpath=request.getContextPath()+"/myshop/images/"+pimage;
		count ++;
		%>
		
			<td align=center>
			<a href="<%=request.getContextPath()%>/myshop/display/prod_view.jsp?pnum=<%=prod.getPnum()%>&cname=<%=prod.getPcate2()%>">
			<img src="<%=imgpath%>" width=100px height=100px>
			</a>
			<div align=center><%=prod.getPname() %></div>
			</td>
		
		<%	
	if(count%4==0){
		%></tr><tr><%
		}
	}
	
%>
</tr>
</table>


<%@include file="display_bottom.jsp"%> 