<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../display/display_top.jsp"%>
<%
	int num, ref, re_step , re_level;

	num = Integer.parseInt(request.getParameter("num"));
	ref = Integer.parseInt(request.getParameter("ref"));
	re_step = Integer.parseInt(request.getParameter("re_step"));
	re_level = Integer.parseInt(request.getParameter("re_level"));
	System.out.println(num +"/" + ref +"/"+ re_step +"/"+ re_level);
	
%>


<h1 align=center>답글쓰기</h1>
<table width="800px" id="tata2" align="center">
	<tr>
	<td>
		<form method="post" name="writeform" action="replyPro.jsp" > 
		
		<input type="hidden" name="num" value="<%=num%>"> 
			<input type="hidden" name="ref" value="<%=ref%>"> 
			<input type="hidden" name="re_step" value="<%=re_step%>"> 
			<input type="hidden" name="re_level" value="<%=re_level%>"> 
		
			<table width="600" cellspacing="0" cellpadding="0" align="center">
				<tr height="30px">
					<td width="100" align="center">문의유형</td>
					<td width="330">
					<select name="kind">
						<option>상품문의</option>
						<option>배송문의</option>
						<option>교환문의</option>
						<option>반품문의</option>
				</select>
				</tr>

				<tr>
					<td width="100" align="center">이 름</td>
					<td width="330" align="left">
					<input type="text" size="30" maxlength="10"	name="writer"></td>
				</tr>
				
				<tr>
					<td width="100" align="center">제 목</td>
					<td width="330" align="left">					
						<input type="text" size="50" maxlength="50" name="subject" 	value="[답변]">
					</td>
				</tr>
				
				<tr>
					<td width="100" align="center">Email</td>
					<td width="330" align="left">
					<input type="text" size="50" maxlength="30"	name="email"></td>
				</tr>
				
				<tr>
					<td width="100" align="center">내 용</td>
					<td width="330" align="left">
						<textarea name="content" id="abc" rows="13" cols="50"></textarea>
					</td>
				</tr>
				<tr>
					<td width="100" align="center">비밀번호</td>
					<td width="330" align="left">
						<input type="password" size="10" maxlength="12"	name="passwd">
					</td>
				</tr>
				<tr>
					<td colspan=2 align="center" height="30">
						<input type="submit" value="글쓰기">  
						<input type="reset"	value="다시작성"> 
						<input type="button" value="목록보기"	
								OnClick="window.location='list.jsp'"><!-- OnClick="location.href='list.jsp'" 도 가능-->
					</td>
				</tr>
			</table>	
		</form>
		</td>
		</tr>
		</table>

<%@include file="../display/display_bottom.jsp"%>
