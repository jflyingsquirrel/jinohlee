<%
	String memid = (String)session.getAttribute("memid") ; 	
	System.out.println("memid:"+memid); 
	
	System.out.println();
	
	if(memid==null){
		response.sendRedirect("../member/admin.jsp");		
	}else if(memid.equals("admin")){	
		
		response.sendRedirect("../admin/main.jsp");	
		
	}else{		
		response.sendRedirect("../member/admin.jsp");	
	}

%> 