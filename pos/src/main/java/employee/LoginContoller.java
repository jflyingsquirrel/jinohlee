package employee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginContoller {
	
	private static final String command= "/login.ep";
	private static final String getPage = "redirect:/list.od";
	
	@Autowired
	@Qualifier("myEmpDao")
	EmpDao empdao;
	
	//Login.jsp���� ȣ��
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doActionGet(HttpSession session,
			HttpServletResponse response,
			@RequestParam(value="id") String id,
			@RequestParam(value="password") String password
			) throws IOException {
			
		System.out.println("LoginConctoller");
		System.out.println("id:"+id+"password:"+password);		
		
		ModelAndView mav = new ModelAndView();
		
		
		PrintWriter writer;
		response.setContentType("text/html;charset=UTF-8");
				
		writer =response.getWriter();
		
		
		EmpBean login = empdao.EmpList(id);	//����Ʈ��������		
		
		
		if(login==null) {	//���̵� �������
			
			System.out.println("null");
							
				writer.println("<script type='text/javascript'>");
				writer.println("alert('���� ���̵��Դϴ�')");
				writer.println("history.back()");
				writer.println("</script>");				
				writer.flush();
			
				return new ModelAndView(getPage);
		}else {							//��й�ȣ�� �������
			System.out.println("test");	
			System.out.println(login.getPassword());
			if(!password.equals(login.getPassword())){
				
				System.out.println("password.equals(bean.getPassword()");
				writer.println("<script type='text/javascript'>");
				writer.println("alert('��й�ȣ�� Ʋ�Ƚ��ϴ�')");
				writer.println("history.back()");
				writer.println("</script>");				
				writer.flush();
				
				return new ModelAndView(getPage);
				
			}else {	
				session.setAttribute("login", login);	//�α��μ���
				mav.setViewName(getPage);
		}
					
		}	
		
		return mav;	//OrderListController
	}

}
