<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- login.jsp -->
<link rel="stylesheet" type="text/css" href="../../style.css">
<br><br><br><br><br>
<form action="loginPro.jsp" method="post">
	<table height=120 align=center cellpadding=5 bgcolor=pink>
	<tr><td></td></tr>
	<tr><td>
	<font color=#993366>아이디: &nbsp; </font> <input type="text" name="id" >
	</td></tr>
	<tr><td>
	<font color=#993366>비밀번호: </font><input type="password" name="password">
	</td></tr>
	<tr><td colspan=2 align=center><input type="submit" value="로그인">
	<tr><td align=right>
	<font size="2px"><a href="findid.jsp">아이디 찾기</a>/<a href="findpwd.jsp">비번찾기</a></font>
	</td></tr>
	</table>
</form>
