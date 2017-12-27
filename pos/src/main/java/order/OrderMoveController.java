package order;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderMoveController {
	
	private static final String command = "/move.od";
	
	@Autowired
	OrdDao orderdao;
	
	//OrderMove.jsp
	@RequestMapping(value=command)
	public ModelAndView doActionPost(
			@RequestParam(value="movebefore") int movebefore,
			@RequestParam(value="moveafter") int moveafter,
			HttpServletResponse response) throws IOException {
		
		System.out.println("OrderMoveController");
		
		ModelAndView mav = new ModelAndView();
		
		PrintWriter writer;
		response.setContentType("text/html;charset=UTF-8");				
		writer =response.getWriter();
		
		if(movebefore==moveafter) {		//������ ���̺��� ������ ���
			writer.println("<script type='text/javascript'>");
			writer.println("alert('�̵��� ���̺��� �����մϴ�')");
			writer.println("history.back()");
			writer.println("</script>");				
			writer.flush();			
			return mav;		
		}
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("before", movebefore);
		map.put("after", moveafter);
		 
		int cnt = -1;
		cnt = orderdao.OrderMove(map);

		if(cnt == 0) {				//�̵��� ���̺� �ֹ��� ���°��
			writer.println("<script type='text/javascript'>");
			writer.println("alert('�̵��� �ֹ��� �����ϴ�')");			
			writer.println("self.close()");
			writer.println("</script>");				
			writer.flush();				
		}
		
		
		writer.println("<script type='text/javascript'>");
		writer.println("alert('�̵��Ǿ����ϴ�')");	
		writer.println("self.close()");				//�ݱ�
		writer.println("</script>");				
		writer.flush();	
		
		return mav;
	}
	

}
