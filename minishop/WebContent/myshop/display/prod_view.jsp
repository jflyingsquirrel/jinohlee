<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="my.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<%
	request.setCharacterEncoding("UTF-8");
	String cname= request.getParameter("cname");
	String pnum= request.getParameter("pnum");
	
	
	System.out.println("cname: "+cname +"/pnum"+pnum);

	if(cname.equals("스니커즈") || cname.equals("부츠") || cname.equals("하이힐") ){
	%>
	<%@include file="display_topshoes.jsp"%>		
<% }else{	
%>
	<%@include file="display_topbag.jsp"%>
<%}%>

<script type="text/javascript">

	function goCart(pnum){
		document.f.action="<%=request.getContextPath()%>/myshop/display/mall_cartAdd.jsp?pnum="+pnum;
		document.f.submit();
	}
	function goOrder(pnum){
		document.f.action
		="<%=request.getContextPath()%>/myshop/display/mall_order.jsp?pnum="+pnum;
		document.f.submit();
	}
</script>


<table id="tata" align=center>
	<%
	
	ProdDao dao = ProdDao.getInstance();
	ProdBean bean = dao.selectByNum(pnum);
	
	DecimalFormat df=new DecimalFormat("###, ###");
	
	if(bean==null){
		out.println("<b>["+pnum+"]해당 상품 정보가 없습니다</b>");
	}
	
	String pimage=bean.getPimage();	
	String imgpath=request.getContextPath()+"/myshop/images/"+pimage;
	%>
	<tr>
	<td>
	<img src="<%=imgpath%>" width=250 height=300>
	</td>
		<td>
			<form name="f" method="POST">
			<br><br><br>
			<b>상품번호:</b> <%=pnum %><br><br>
			<b>상품이름:</b> <%=bean.getPname() %><br><br>
			<b>상품가격:</b> <%=df.format(bean.getPrice()) %>원
			<br><br>
			<b>상품포인트:</b>
					[<%=bean.getPoint() %>]
					point<br><br>
			<b>상품갯수:</b>
			<input type=text name="oqty" size=2 value="1">개<br>
			<br><br>
			
			<table border=0 width=90% align=center> 
				<tr>
					<td>
						<input type="button" value="장바구니담기" onclick="goCart('<%=pnum%>');"/>
					</td>
					<td>
						<input type="button" value="주문하기" onclick="goOrder('<%=pnum%>');">
					</td>
				</tr>
				</table>			
			</form>		
		</td>
	</tr>
	<tr height=100 valign=top>
		<td colspan=2 align=left style="padding:20px;">
			<b>상품 상세 설명</b><br>
			<%=bean.getPcontents() %>
		</td>
	</tr>
</table>
<%@include file="display_bottom.jsp"%>

