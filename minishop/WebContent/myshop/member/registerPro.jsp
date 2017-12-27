<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="my.*"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="member" class="my.MemberBean"/>
<jsp:setProperty property="*" name="member"/>


<%

	System.out.println(member.getName());

	String name = request.getParameter("name");
	System.out.println(name);

	int cnt = -1;
	MemberDao dao = MemberDao.getInstance();
	cnt = dao.insertData(member);
	System.out.println("cnt:"+cnt);
	
	String msg="", url="";
	if(cnt==1){
		msg="가입 성공";
	}else{
		msg="가입 실패";
	}
	url = request.getContextPath()+"/main.jsp";
	
%>

<html>
<head>
</head>
<body>
<script type="text/javascript">
	alert("<%=msg%>"+"했습니다.");
	location.href="<%=url%>";
</script>
</body>

</html>