<%@page import="my.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.io.*,com.oreilly.servlet.*,
     com.oreilly.servlet.multipart.*"%>


<%      
	MultipartRequest mr=null;
	String uploadDir = config.getServletContext().getRealPath("/myshop/images");
	int result=0;
	String msg=""; String url="";
	
	try{
		mr=new MultipartRequest(request,uploadDir,10*1024*1024,"UTF-8",
				new DefaultFileRenamePolicy());
		
		String pnum =  mr.getParameter("pnum");
		String pimage = mr.getFilesystemName("pimage");
		String pimage2 = mr.getParameter("pimage2");
		
		System.out.println("pnum"+pnum);
		
		ProdDao pdao = ProdDao.getInstance();
		result=pdao.updateProduct(mr);	
		 
			
		if(result>0){
			msg="수정 성공";
			url="prod_list.jsp";
		}else{
			msg="수정 실패";
			url="prod_update.jsp?pnum="+mr.getParameter("pnum");
		}
		
	}catch(IOException e){
		msg="파일 업로드 실패";
		url="prod_update.jsp?pnum="+mr.getParameter("pnum");
		
	}catch(NumberFormatException e){  
		msg="상품 수량,가격,포인트는 숫자로 입력하세요";
		url="prod_update.jsp?pnum="+mr.getParameter("pnum");
	}
%>

<script type="text/javascript">
	alert("<%=msg%>");
	location.href="<%=url%>";
</script>





