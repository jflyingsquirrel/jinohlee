<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="my.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String cname= request.getParameter("cname");
	request.setCharacterEncoding("UTF-8");
	
	System.out.println("cglist: "+cname);

if(cname.equals("스니커즈") || cname.equals("부츠") || cname.equals("하이힐") ){
	%>
	<%@include file="display_topshoes.jsp"%>		
<% }else{	
%>
	<%@include file="display_topbag.jsp"%>
<%}%>
<table>
<tr>
<%
	ProdDao dao = ProdDao.getInstance();
	ArrayList<ProdBean> list = dao.productByCate(cname);

	if(list.size()==0){
		%><td align=center>등록된 상품이 없습니다</td><%		
	}
	else{
	int count=0;
	for(ProdBean prod : list){
		count++;
%>		
		<td align="left" width=300px height=300px> 
 			<%
				String imgpath=request.getContextPath()+"/myshop/images/"+prod.getPimage();  
				System.out.println("imgpath:"+imgpath); 
			%> 
			<a href="prod_view.jsp?pnum=<%=prod.getPnum()%>&cname=<%=cname%>">
				<img src="<%=imgpath%>" border=1 width=200 height=200>
			</a>
			<br>
			<div align=left>
				<%=prod.getPname()%>			
			</div>
			</td> 			
<%			
	if(count%3==0){
	%></tr><tr><%
	}
}
}%>
	
</tr>
</table>
</td>
</tr>
<%@include file="display_bottom.jsp"%>