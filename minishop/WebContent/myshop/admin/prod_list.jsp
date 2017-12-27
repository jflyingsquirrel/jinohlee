<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,my.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
 	function checkDel(pnum, pimage){
		var isdel=window.confirm("삭제하시겠습니까?");  
		if (isdel) {
			location.href="prod_delete.jsp?pnum="+pnum;
		}
	}
</script>
<%@include file="admin_top.jsp"%>
<table>
<tr>
<td>
<h1 align=center>상품목록</h1>
<table id="ta">
<tr bgcolor="#D5D5D5" >
	<th>번호</th>
	<th>카테고리</th>
	<th>상품명</th>
	<th>이미지</th>
	<th>가격</th>
	<th>제조사</th>
	<th>수량</th>
	<th>수정|삭제</th>  
</tr>

<%
	ProdDao pdao = ProdDao.getInstance();
	ArrayList<ProdBean> list = pdao.productAll();
	
	if( list.size()==0 ){
		out.println("<tr><td colspan=8>");
		out.println("데이터가 없습니다.");
	}//if---------
	
	for(ProdBean prod : list ){
	%>
		<tr bgcolor="#D5D5D5" >
			<td><%=prod.getPnum()%></td> 
			<td align="center"><%=prod.getPcategory()%>/<%=prod.getPcate2()%></td> 
			<td align="center"><%=prod.getPname()%></td>
			<td align="center"> 
 			<%
				String imgpath=request.getContextPath()+"/myshop/images/"+prod.getPimage();  
				System.out.println("imgpath:"+imgpath); 
			%> 
			<a href="prod_view.jsp?pnum=<%=prod.getPnum()%>">
				<img src="<%=imgpath%>" border=0 width=40 height=40>
			</a>			
			</td> 
		
			<td align=right>
			<%if(prod.getPrice()==0){%>
				금액미정<%}else{ %>			
			<%=prod.getPrice()%>
			<%} %></td>
			
			<td align=center>
			<%if(prod.getPcompany()==null){%>
				제조사알수없음<%}else{ %>			
			<%=prod.getPcompany()%>
			<%} %></td>
			
			<td align=right>
			<%if(prod.getPqty()==0){%>
				수량미정<%}else{ %>			
			<%=prod.getPqty()%>
			<%} %></td>

			<td align=center>
			<a href="prod_update.jsp?pnum=<%=prod.getPnum()%>">수정</a>|
			<a href="javascript:checkDel('<%=prod.getPnum()%>','<%=prod.getPimage()%>');">삭제</a>
			</td>
		</tr> 
<% }//for-----------------%>

</table>
</td>
</tr>
</table>
<%@ include file="admin_bottom.jsp" %>


