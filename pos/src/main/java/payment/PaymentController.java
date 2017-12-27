package payment;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;




//ī�����,���ݰ��� Ŭ����	//Payment.jsp
@Controller
public class PaymentController {
	
private static final String command = "/payment.pm";
	
	@Autowired
	PaymentDao paydao;
	
	//Payment.jsp	//����
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doActionPost(
			@RequestParam(value="oid") String oid,
			@RequestParam(value="payment", required=false) int pay,
			@RequestParam(value="cal", required=false) String cal,
			@RequestParam(value="totalcount", required=false) int total,
			@RequestParam(value="paymenttype", required=false) int paymenttype,
			Model model,
			HttpServletResponse response) throws IOException {
		
		
		System.out.println("PaymentController");
		ModelAndView mav = new ModelAndView();
				
		int totalcount = 0;
		java.util.Date date = new java.util.Date();	//���ó�¥��������
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yy-MM-dd");
		String today = sdf.format(date);
		
		String paydate = today;
		System.out.println(today);	
		
		List<PaymentBean> lists = this.paydao.selectOrder(oid);	//�ֹ�����Ʈ ��������	
				
		if(total == pay) {	//�κа����� ��������Ʈ �� �߰������ʵ��� ����
				
		for(int i=0; i<lists.size(); i++) {
			
			String pname = lists.get(i).getPname();
			int qty = lists.get(i).getQty();
			int price = lists.get(i).getPrice();
			int amount = lists.get(i).getAmount();
							
			PaymentBean payment = new PaymentBean(pname, qty, price, amount, paydate);	//��������Ʈ�� �ֱ�
			
			int cnt = -1;			//��ǰ�� ������ �������̺� update
			cnt = paydao.updatePayment(payment);
			System.out.println("cnt:"+cnt);
			
			if(cnt == 0) {			//��ǰ�� ������ �������̺� insert
				paydao.insertPayment(payment);
		}	
			
			totalcount += amount;			
			
		}	
		}else if(total>pay) {		//�κа����� �ݾ��� �� ������ totalcount �����ݾ����� ����
			System.out.println("total>pay");
			cal = "";
			totalcount = pay;			
		}
			
		
		
		
		PrintWriter writer;
		response.setContentType("text/html;charset=UTF-8");				
		writer =response.getWriter();	
				
				
		if(cal!="") {//�κа���// �ݾ��Է½�
			System.out.println("cal!=null");			
					
			int calculator = Integer.parseInt(cal);		
			int payment= total-Integer.parseInt(cal);
			totalcount = total;
					
			mav.addObject("cal", null);
			mav.addObject("payment", payment);
			mav.addObject("totalcount", totalcount);
			mav.addObject("oid", oid);
			mav.setViewName("./../order/Payment");
					
			//paymentday �� ��Ż�ݾ�, ���ó�¥ �ֱ�		
			PaymentdayBean payday = new PaymentdayBean(calculator, paymenttype, paydate);
					
			int cnt2 = -1;
			cnt2 = paydao.Updatepaymentday(payday);	//���ó�¥ �ֹ����������� update
			System.out.println("cnt2:"+cnt2);
			if(cnt2 == 0) {
				paydao.insertPaymentDay(payday);		
			}				
			
			
			if(payment<0) {	//�����ݾ��� �� ũ�� �Է�������
			
						writer.println("<script type='text/javascript'>");
						writer.println("alert('�����Ͻ� �ݾ��� Ȯ�����ּ���')");
						writer.println("history.back()");
						writer.println("</script>");				
						writer.flush();	
			}			
					
					return mav;
					
			}
		
		//paymentday �� ��Ż�ݾ�, ���ó�¥ �ֱ�
		PaymentdayBean payday = new PaymentdayBean(totalcount, paymenttype, paydate);
		
		int cnt2 = -1;
		cnt2 = paydao.Updatepaymentday(payday);	//���ó�¥ �ֹ����������� update
		System.out.println("cnt2:"+cnt2);
		
		if(cnt2 == 0) {
			paydao.insertPaymentDay(payday);	//������ insert	
		}		
		
		mav.addObject("lists",lists);
		mav.addObject("totalcount", totalcount);
		mav.addObject("paymenttype", paymenttype);
		mav.addObject("oid",oid);
		mav.addObject("today",today);
		mav.setViewName("Receive");
		return mav;	//Receive.jsp�ΰ���.		
	}

	
	
	
}
