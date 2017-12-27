<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[아이디 찾기]</title>
<link rel="stylesheet" type="text/css" href="../../style.css">
</head>
<body>
<form action="findpwdPro.jsp" method="post">
<br><br><br><br><br>
<table align="center" cellpadding=5 bgcolor=pink>
<tr><td></td></tr>
	<tr>
		<td align="center"><font color=#993366>아이디: </font></td>
		<td><input type="text" name="id">    	
	</tr>
	<tr>
		<td align="center"><font color=#993366>이름: </font></td>
		<td><input type="text" name="name">    	
	</tr>
	<tr>
		<td align="center"><font color=#993366>이메일: </font></td>
		<td>
			<input type="text" name="email">			
		</td>				
	</tr>
	
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="비밀번호 찾기">
			</td></tr>
			<tr><td align="right">
			<font size="2px"><a href="login.jsp">돌아가기</a></font>
			</td></tr>		
</table>	
</form>
</body>
</html>