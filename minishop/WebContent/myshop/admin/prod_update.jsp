<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="my.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  

<%@ include file="admin_top.jsp"%>

<%
	String pnum = request.getParameter("pnum");
	System.out.println("update: "+pnum);//test

	ProdDao dao = ProdDao.getInstance();	
	ProdBean bean = dao.selectByNum(pnum);
	
	System.out.println("test:"+bean.getPname());

%>

<table border=1px bordercolor=#80bfff width=800px height=600px>
<tr>
<td>


<form name="f" action="prod_updatePro.jsp" method="POST" enctype="multipart/form-data" onsubmit="return writeSave()">
<table align=center id="ta">
<CAPTION ALIGN="TOP">상품수정카테고리</CAPTION>

	<tr>
		<th class="m2">상품번호</th>
		<td align=left><b><%=bean.getPnum()%></b>
		<input type=hidden name="pnum" value="<%=bean.getPnum()%>">
		</td>
	</tr>
	<tr>
	
	<tr>
		<th class="m2">상품명</th>
		<td align=left>
		<input type=text name="pname" value=<%=bean.getPname() %>>
		</td>
	</tr>
	
	<tr>
		<th class="m2">카테고리</th>
		<td align=left>
			<select name="pcategory">
			<option value="bag" <%if((bean.getPcategory()).equals("bag")){%> selected <%}%>>bag</option>
			<option value="shoes" <%if((bean.getPcategory()).equals("shoes")){%> selected <%}%>>shoes</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<th class="m2">카테고리2</th>
		<td align=left>
			<select name="pcate2">
			<option value="크로스백" <%if((bean.getPcategory()).equals("크로스백")){%> selected <%}%>>shoes</option>
			<option value="토트백" <%if((bean.getPcategory()).equals("토트백")){%> selected <%}%>>shoes</option>
			<option value="백팩" <%if((bean.getPcategory()).equals("백팩")){%> selected <%}%>>shoes</option>
			<option value="미니백" <%if((bean.getPcategory()).equals("미니백")){%> selected <%}%>>shoes</option>
			<option value="하이힐" <%if((bean.getPcategory()).equals("하이힐")){%> selected <%}%>>bag</option>
			<option value="스니커즈" <%if((bean.getPcategory()).equals("스니커즈")){%> selected <%}%>>shoes</option>
			<option value="부츠" <%if((bean.getPcategory()).equals("부츠")){%> selected <%}%>>shoes</option>
						</select>
		</td>
	</tr>
	
	<tr>
		<th class="m2">제조회사</th>
		<td align=left>
		<input type=text name="pcompany" value="<%=bean.getPcompany()%>">
		</td>
	</tr>
	<tr>
		<th class="m2">상품이미지</th>
		<td align=left>
		
		<%
			String pimage =request.getContextPath()+"/myshop/images";
		
		%>
		<img src="<%=pimage %>/<%=bean.getPimage()%>" width=100 height=100>
		<input type=file name="pimage">
		<input type=hidden name="piamge2" value="<%=bean.getPimage()%>">
		</td>
	</tr>
	<tr>
		<th class="m2">상품 수량</th>
		<td align=left>
		<input type=text name="pqty" maxlength=8 value="<%=bean.getPqty()%>">
		</td>
	</tr>
	<tr>
		<th class="m2">상품 가격</th>
		<td align=left>
		<input type=text name="price" maxlength=8 value="<%=bean.getPrice()%>">
		</td>
	</tr>
	<tr>
		<th class="m2">상품 스펙</th>
		<td align=left>
			<select name="pspec">
			<option value="NEW" <%if((bean.getPspec()).equals("NEW")){%> selected <% } %>>NEW</option>
			<option value="HIT" <%if((bean.getPspec()).equals("HIT")){%> selected <% } %>>NEW</option>
			<option value="BEST" <%if((bean.getPspec()).equals("BEST")){%> selected <% } %>>NEW</option>
			</select>
		</td>
	</tr>
	<tr>
		<th class="m2">상품 소개</th>
		<td align=left>
		<textarea name="pcontents" rows=5 cols=50 style="resize: none;"><%=bean.getPcontents() %></textarea>
		</td>
	</tr>
	<tr>
		<th class="m2">상품 포인트</th>
		<td align=left><input type=text name="point" value="<%=bean.getPoint()%>"></td>
	</tr>
	<tr>	
		<td colspan=2 class="m1" align="center">
		<input type=submit value="상품 수정">
		<input type=reset value="취 소">
		</td>
	</tr>
</table>
</form>
</td>
</tr>

</table>
<%@ include file="admin_bottom.jsp"%>




