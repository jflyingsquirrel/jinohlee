<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="my.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  

<%@ include file="admin_top.jsp"%>

<table id="mainta">
<tr>
<td>

<h1 align=center>상품등록</h1>
<form name="f" action="prod_inputPro.jsp" method="POST" enctype="multipart/form-data" onsubmit="return writeSave()">
<table align=center id="ta">

	
	<tr>
		<th class="m2">상품명</th>
		<td align=left>
		<input type=text name="pname">
		</td>
	</tr>
	
	<tr>
		<th class="m2">카테고리</th>
		<td align=left>
			<select name="pcategory">
			<option value="bag" SELECTED>bag</option>
			<option value="shoes">shoes</option>
			</select>
		</td>
	</tr>
	
		<tr>
		<th class="m2">카테고리2</th>
		<td align=left>
			<select name="pcate2">
				<option value="크로스백" SELECTED>크로스백</option>
				<option value="토트백">토트백</option>
				<option value="백팩">백팩</option>
				<option value="미니백">미니백</option>
				<option value="하이힐">하이힐</option>
				<option value="스니커즈">스니커즈</option>
				<option value="부츠">부츠</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<th class="m2">제조회사</th>
		<td align=left>
		<input type=text name="pcompany">
		</td>
	</tr>
	<tr>
		<th class="m2">상품이미지</th>
		<td align=left>
		<input type=file name="pimage">
		</td>
	</tr>
	<tr>
		<th class="m2">상품 수량</th>
		<td align=left>
		<input type=text name="pqty" maxlength=8>
		</td>
	</tr>
	<tr>
		<th class="m2">상품 가격</th>
		<td align=left>
		<input type=text name="price" maxlength=8>
		</td>
	</tr>
	<tr>
		<th class="m2">상품 스펙</th>
		<td align=left>
			<select name="pspec">
			<option value="NEW" SELECTED>NEW</option>
			<option value="HIT">HIT</option>
			<option value="BEST">BEST</option>
			</select>
		</td>
	</tr>
	<tr>
		<th class="m2">상품 소개</th>
		<td align=left>
		<textarea name="pcontents" rows=5 cols=50 style="resize: none;"></textarea>
		</td>
	</tr>
	<tr>
		<th class="m2">상품 포인트</th>
		<td align=left><input type=text name="point"></td>
	</tr>
	<tr>	
		<td colspan=2 class="m1" align="center">
		<input type=submit value="상품 등록">
		<input type=reset value="취 소">
		</td>
	</tr>
</table>
</form>
</td>
</tr>

</table>
<%@ include file="admin_bottom.jsp"%>




