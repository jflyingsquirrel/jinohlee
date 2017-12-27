<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[회원 가입]</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/script.js"></script>  
<style>
table{
	background: pink;
	padding: 20px;	
}


</style>

   
</head>
<body>
<br><br><br><br><br>
<form action="registerPro.jsp" method="post" onsubmit="return writeSave()">

<table align=center>

	<tr>
		<td colspan="2" align="center">
			<font size="3" color="#5D1900"><b> 회원 가입 </b></font>
		</td>
	</tr>
	<tr>
		<th align="center"> * 회원 아이디 </th>
		<td><input type="text" name="id">
    	
    	<button type="button" id="id_check" onclick="return duplicate()">중복체크</button>
				<span id="idmessage" style="display:none;"></span>
		</td>
	</tr>
	<tr>
		<th align="center"> * 비밀 번호</th>
		<td><input type="password" name="password" onBlur="pwcheck()"> <font size="2px" color=#ff471a>영문 소문자/숫자 조합 3~8자</font></td> 
	</tr>
	<tr>
		<th align="center"> * 비밀 번호 확인</th>
		<td><input type="password" name="repassword" id="repassword" onkeyup="passwd_keyup()">
			<span id="pwmessage" style="display:none;"></span></td>
	</tr>
	<tr>
		<th align="center"> E-Mail </th>
		<td>
			<input type="text" name="email"> 
		</td>
	</tr>
	<tr>
		<th align="center"> * 한글 이름</th>
		<td><input type="text" name="name"></td>
	</tr>
	
	<tr>
		<th align="center">휴대 전화 번호</th>
		<td>
			<select name="hp1">
				<option value="010">010</option>
				<option value="011">011</option>
			</select>
			- 
			<input type="text" name="hp2" size="4" maxlength="4"> - 
			<input type="text" name="hp3" size="4" maxlength="4"> <font size="2px" color=#ff471a>예) 011-000-0000</font>
		</td>
	</tr>
	
	<tr>
		<th align="center">주소</th>
		<td><input type="text" name="address"></td>
	</tr>
		
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="가입 하기" id="sub">&nbsp;&nbsp;
			<input type="reset" value="취소">
		</td>
	</tr>	
</table>
</form>
</body>
</html>

