<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="my.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="mallCart" class="my.CartBean" scope="session"></jsp:useBean>

<%@include file="display_top.jsp"%>


<h1 align=center>장바구니</h1>

<table width="800px" id="tata2" align="center" >
<tr>
<td>
<table id="tata3">
		<tr>
		<th>번호</th>
		<th>상품명</th>
		<th>수량</th>
		<th>단가</th>
		<th width="15%">삭제</th>
	</tr>
	

<%
	DecimalFormat df=new DecimalFormat("###,###");
	int cartTotalPrice=0; //장바구니총액
	int cartTotalPoint=0; //누적포인트
	
	//카트빈으로브터 장바구니 모든 상품 가져오기
	Vector<ProdBean> cv= mallCart.getAllProducts();	
	
	System.out.println("mall_CartList cv갯수 : "+cv.size());

	
	if(cv.size()==0){
		out.println("<tr><td colspan=6 align=center><b>");
		out.println("선택된 상품이 없습니다");
		out.println("</b></td></tr></table>");
	}else{
		for(ProdBean pd: cv){
			String pimage = pd.getPimage();
			String imgpath = request.getContextPath()+"/myshop/images/"+pimage;
%>
	<tr>
		<td align=center><%=pd.getPnum() %></td>
		<td align="center">
			<img src="<%=imgpath %>" width=40 height=40>
			<br><b><%=pd.getPname() %></b>
			</td>
			
			<td align=center>
					<input type="text" size="1" name="oqty" value="<%=pd.getPqty() %>">개
					<a href="mall_cartEdit.jsp?oqty=<%=pd.getPqty() %>&pnum=<%=pd.getPnum() %>&edit=plus">
					<font color=black>+</font></a>
					<a href="mall_cartEdit.jsp?oqty=<%=pd.getPqty() %>&pnum=<%=pd.getPnum() %>&edit=ma">
					<font size="5.5px" color=black>-</font></a>
			</td>
			
			<td align=right>
			<font color=red>
				<b>
					<%=df.format(pd.getTotalPrice()) %>원<br>
					[<%=df.format(pd.getTotalPoint()) %>] point
				</b>
			</font>
			</td>
			
			<td align="center">
				<a href="mall_cartDel.jsp?pnum=<%=pd.getPnum() %>"><font color="black" size="3px">삭제</font></a>
			</td>
			
			<%
				//장바구니의 총 금액 추가와 포인트 누적
				cartTotalPrice += pd.getTotalPrice();
				cartTotalPoint += pd.getTotalPoint();
		} //for---------------------------------------
			%>
<!-- --------------------------------------------------------- -->

		<tr class=m1>
			<td colspan=3><b>장바구니 총액</b> : 
			<font color=red>
				<%=df.format(cartTotalPrice) %> 원<br>
			</font>
			</td>
			
			<td colspan=2 align=right>
			<a href="mall_order.jsp?pnum=00&oqty=00">
			<!-- 장바구니에서 보낼때는 필요없지만 pnum과 oqty를 order에서 받아야하기때문에 아무거나 넣어준다. -->
				<font color="black" size="3px">[주문하기]</font>	
			</a>
			<a href="javascript:history.go(-2);">
				<font color="black" size="3px">[계속쇼핑]	</font>			
			</a>
			</td>
			
			</tr>
	</table>
<%} %>
</td>
</tr>
</table>
<%@include file="display_bottom.jsp"%>