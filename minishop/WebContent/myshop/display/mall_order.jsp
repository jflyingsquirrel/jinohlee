<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="my.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="mallCart" class="my.CartBean" scope="session"></jsp:useBean>

<%@include file="display_top.jsp"%>
<h1 align=center>주문리스트</h1>
<table width="800px" id="tata2" align="center">
<tr>
<td>
<table id="tata3">
	<tr>
		<td align=center></td>
		<td align=center>상품명</td>
		<td align=center>수량</td>
		<td align=center>금액</td>
		
	</tr>

<%
	String oqty = request.getParameter("oqty");
	String pnum = request.getParameter("pnum");
	
	System.out.println("상품번호/수량:"+pnum+"/"+oqty);
	
	
	if(!pnum.equals("00") && !oqty.equals("00")){ 
		
		mallCart.addProduct(pnum,oqty); 	//즉시보기에서 넘어온경우 장바구니에 추가
	}
	
	DecimalFormat df= new DecimalFormat("###,###");
	int totalPrice=0;
	Vector<ProdBean> cv = mallCart.getAllProducts(); //장바구니의 모든 상품을 반환  
	
	for(ProdBean pd : cv){	
			String pimage = pd.getPimage();
			String imgpath = request.getContextPath()+"/myshop/images/"+pimage;
			%>
		<tr>
			<td align="center"><img src="<%=imgpath%>" width=30 height=30></td>
			<td align="center"><%=pd.getPname()%></td>
			<td align="right"><%=pd.getPqty()%>개</td>
			<td align="right"><%=df.format(pd.getTotalPrice())%>원</td>
		</tr>	
<%		totalPrice += pd.getTotalPrice();
	} %>
	
	<tr>
		<td colspan=3 align=center><b>결재하실 총액은</b>:
				<font color=red><%=df.format(totalPrice)%> 원<br></font>		
	</td>
	<td align=right colspan=4>	
<input type="button" value="결재하기" onclick=
	"location.href='<%=request.getContextPath()%>/myshop/display/mall_calculate.jsp'">
</td>
	</tr>
</table>
	
</td>
</tr>
</table>

<%@include file="display_bottom.jsp"%>