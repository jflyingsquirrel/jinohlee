package product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

	private static final String command ="/product.pd";
	
	@Autowired
	ProductDao productdao;

	//Product.jsp	//��ǰ�߰�
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doActionPost(				
			@ModelAttribute("product") @Valid ProductBean product,	
			BindingResult result,
			HttpServletResponse response) throws IOException {
		
		ModelAndView mav = new ModelAndView();
		
		PrintWriter writer;
		response.setContentType("text/html;charset=UTF-8");				
		writer =response.getWriter();	
		
		if (result.hasErrors()) {	//��ȿ���˻�
			
			writer.println("<script type='text/javascript'>");
			writer.println("alert('��ǰ��� �ݾ��� ��Ȯ�� �Է����ּ���')");
			writer.println("history.back()");
			writer.println("</script>");				
			writer.flush();			
			return mav;
		} 
		int categoryid = 0;		//ī�װ�id ����
		if(product.getCategoryname().equals("����")) {	categoryid = 1;}
		else if(product.getCategoryname().equals("���")) { categoryid = 2;}
		else if(product.getCategoryname().equals("�нķ�")) { categoryid = 3;}
		else if(product.getCategoryname().equals("�����")) { categoryid = 4;}
		else if(product.getCategoryname().equals("���")) { categoryid = 5;}
		else if(product.getCategoryname().equals("��Ÿ")) { categoryid = 6;}
		product.setCategoryid(categoryid);
				
		productdao.InsertProduct(product);	//��ǰ�߰�
		
		writer.println("<script type='text/javascript'>");	//â�ݰ� �θ�â�̵�
		writer.println("opener.parent.location='productlist.pd'");
		writer.println("self.close()");
		writer.println("</script>");
		writer.flush();	

		mav.setViewName("ProductInput");
		return mav;
	}
	
		

}
