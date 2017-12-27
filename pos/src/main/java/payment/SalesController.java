package payment;

import java.util.Calendar;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SalesController {
	
	
	private static final String command = "/dailey.pm";
	
	@Autowired
	PaymentDao paydao;
	
	//���� controller	//List.jsp	//SaileDailey.jsp, End, Month
	@RequestMapping(value=command)
	public ModelAndView doActionGet(
			@RequestParam(value="sales") String sales,
			@RequestParam(value="dayday", required=false) String dayday,
			@RequestParam(value="mon", required=false) String mon,
			@RequestParam(value="totalprice2", required=false) String editPrice){		
		
		ModelAndView mav = new ModelAndView();
		
		String today = paydao.getFormatDate("yyyy-MM-dd","NOW", 0);	//���ó�¥
		String month = "%"+paydao.getFormatDate("yy/MM","NOW", 0)+"%";	//�̹���
		String month2 = paydao.getFormatDate("yyyy�� MM��","NOW", 0);	//�̹���//��¿�
		
	
		if(mon != null) {	//������ Ŭ����	
			
			if(mon.equals("minus1")) {	//1������
				month = "%"+paydao.getFormatDate("yy/MM","M",-1)+"%";
				month2 = paydao.getFormatDate("yyyy�� MM��","M",-1);
			}else if(mon.equals("minus2")) {//2������
				month = "%"+paydao.getFormatDate("yy/MM","M",-2)+"%";
				month2 = paydao.getFormatDate("yyyy�� MM��","M",-2);
			}else {//3������
				month = "%"+paydao.getFormatDate("yy/MM","M",-3)+"%";
				month2 = paydao.getFormatDate("yyyy�� MM��","M",-3);
			}
		}
	
				
		if(dayday != null) {		//SaileDailey.jsp���� ��¥������ dayday	
			//String day = dayday.substring(2, 10);
			today = dayday;
		}
		
		
		List<PaymentBean> payment = paydao.selectPayList(today);	//��¥�� �ֹ����� payment
		List<PaymentdayBean> payday = paydao.selectPaydayList(month);	//���� �������� paymentday
		List<PaymentdayBean> payday2 = paydao.selectPaydayList2(today);	//��¥�� �������� paymentday
		
		System.out.println("payday.size: "+ payday.size());
		
		int totalprice = 0;
		for(int i=0; i<payday2.size(); i++) {			//����ġ �ѱݾ�
			totalprice += payday2.get(i).getTotalprice();			
		}		

		
		if(sales.equals("dailey")) {		//���ϰ��			
			mav.setViewName("SalesDailey");
			mav.addObject("today",today);			//���ó�¥
			mav.addObject("payment",payment);	//payment����Ʈ
			mav.addObject("totalprice",totalprice);	//�ѱݾ�
			
		}else if(sales.equals("month")) {	//�����	
			String str ="[";
			str +="['��¥' , '�Ǹűݾ�'] ,";
			int num =0;			
			
			for(int i=0; i<payday.size()-1; i++) {//�ſ�ī��,���ݱݾ� ���ļ� �迭�θ���� �׷��������				
				payday.get(i).getPaydate();
				
				str +="['";
				
				if(payday.get(i).getPaydate().equals(payday.get(i+1).getPaydate())) {
										
					str += payday.get(i).getPaydate().substring(0, 10);
					str +="' , ";
					str += payday.get(i).getTotalprice()+payday.get(i+1).getTotalprice();
					i++;
				}else {
					str += payday.get(i).getPaydate().substring(0, 10);
					str +="' , ";
					str += payday.get(i).getTotalprice();
				}
				str +=" ]";
				
				num ++;
				if(num<payday.size()){
					str +=",";
				}		
			}
			str += "]";
			
			mav.addObject("size",payday.size());			//���������� ������
			mav.addObject("str", str);	
			mav.addObject("sales",sales);
			mav.addObject("month2",month2);				//2017�� 12��
			mav.setViewName("SalesMonth");
		}else {								//��������			
			mav.setViewName("SalesEnd");
			mav.addObject("sales",sales);
			mav.addObject("today",today);			//���ó�¥
			mav.addObject("payday2",payday2);		//paymentday ����Ʈ
		}
		
		mav.addObject("sales",sales);		//�������
			
		
		if(editPrice!=null) {	//��������//SalesEnd.jsp���� ���ݱݾ� ������
			paydao.EditPaymentday(editPrice);	
			mav.setViewName("redirect:/list.od");
		}
		
		return mav;	//List�� ����
	}

}
