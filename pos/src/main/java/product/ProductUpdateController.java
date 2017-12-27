package product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

import payment.PaymentBean;

@Controller
public class ProductUpdateController {

	private static final String command = "/prodUpdate.pd";
	
	
	@Autowired
	ProductDao productdao;
	
	
	//Product.jsp	//��ǰ�� Ŭ����
		@RequestMapping(value=command, method=RequestMethod.GET)
		public String doActionGet(
				@RequestParam(value="pname") String productname,
				@RequestParam(value="price") int price,
				@RequestParam(value="num") int num,
				Model model) {
			
				List<PaymentBean> lists = productdao.selectProdsales(productname);
					
				model.addAttribute("productname",productname);
				model.addAttribute("price",price);
				model.addAttribute("num",num);
				model.addAttribute("lists",lists);				
					
				return "ProductUpdate";	//ProductUpdate�� ����.
		}
		
	
	//ProductUpdate.jsp	 //����
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
			
			productdao.UpdateProd(product);
			
			mav.setViewName("redirect:/productlist.pd");				

			return mav;
		}
		
		
		
	
	
}
