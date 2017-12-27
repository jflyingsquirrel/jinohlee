<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="my.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<jsp:useBean id="order" class="my.OrdersDao" scope="session" /> 
<jsp:useBean id="mallCart" class="my.CartBean" scope="session" />
  
<%
	String msg;
	String url;
	if(session.getAttribute("memno")==null){
		msg= "로그인을 해주세요";
		%>
		<script>
		alert("<%=msg%>");
		</script>
		<%
		response.sendRedirect("../member/login.jsp");
	}else{
	
	int no = (Integer)(session.getAttribute("memno"));//세션불러오기
	String id = (String)session.getAttribute("memid");
	
	
	System.out.println("mall_calculate.jsp의 세션no:"+no+"세션id:"+id);
	
	Vector<ProdBean> cv = mallCart.getAllProducts();	//장바구니의 모든 정보가져온다.
	int cnt = -1;
	cnt = order.insertOrders(no, cv);
	//no:회원번호 cv:장바구니에 담긴 상품목록, cnt:orders테이블에 삽입된 레코드 갯수
	
	
	if(cnt != -1){
		msg= "주문 완료했습니다";
		url = request.getContextPath() + "/myshop/display/mall.jsp";
		
		mallCart.removeAllProduct();//vector안의 데이터를 지운다
		
		%>	
		

	<script type="text/javascript">
		alert("<%=msg%>");	
		
		var resp= confirm('계속하시겠습니까?');	//확인을 누르면 true
		
		if(resp){
			location.href="<%=request.getContextPath()%>/main.jsp";
		}else{
			location.href="<%=request.getContextPath()%>/logout.jsp";
		}
	
	</script>
	
<% }else{
	
	msg ="주문 실패";
	url= request.getContextPath() + "myshop/display/main.jsp";
	//response.sendRedirect(url);
}
	}
%>