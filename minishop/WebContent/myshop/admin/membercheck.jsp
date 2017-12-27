<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="my.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@include file="admin_top.jsp"%>


<table>
<tr>
<td>



<h1 align=center>회원관리</h1>
<table id="ta">
	<tr><td>
	<table>
	<tr bgcolor="#D5D5D5" >
	<th>회원번호</th>
	<th>회원이름</th>
	<th>회원아이디</th>
	<th>이메일주소</th>
	<th>전화번호</th>
	<th>주소</th>
	</tr>
	
	
	<%
	MemberDao dao = MemberDao.getInstance();
	ArrayList<MemberBean> list = dao.Allselect();
	
	if( list.size()==0 ){
		out.println("<tr><td colspan=5>");
		out.println("데이터가 없습니다.");
		out.println("</td></tr>");
	}

	for(MemberBean member : list){
		%><tr bgcolor="#D5D5D5" >
			<td align=center><%=member.getNo() %></td>
			<td><%=member.getName() %></td>
			<td><%=member.getId()%></td>
			<td>
			<%if(member.getEmail()==null){%>
					입력된 값이 없습니다
					<%}else{%>
					<%=member.getEmail() %>					
			<%}%>
			</td>
			<td>
			<%if(member.getHp1()==null || member.getHp2()==null || member.getHp3()==null){%>
					입력된 값이 없습니다
					<%}else{%>
					<%=member.getHp1() %>-<%=member.getHp2() %>-<%=member.getHp3() %>					
					<%}%>
			</td>
			<td>
				<%if(member.getAddress()==null){%>
				입력된 값이 없습니다
				<%}else{%>
				<%=member.getAddress() %>					
			<%}%>
			
			</td>
			</tr>	
			<%} %>	
	</table>
		</td>
	</tr>
	</table>
</table>
