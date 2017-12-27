package payment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("myPaymentDao")
public class PaymentDao {

	private static final String namespace ="pay.payMent";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public List<PaymentBean> selectOrder(String oid) {		
		List<PaymentBean> lists = new ArrayList<PaymentBean>();
		lists = sqlSessionTemplate.selectList(namespace+".selectOrder", oid);
		return lists;
	}

	//PaymentController	//��ǰ�� ������ �������� update
	public Integer updatePayment(PaymentBean payment) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace+".updatePayment", payment);		
		return cnt;
	}
	
	//PaymentController	//��ǰ�� ������ �������� insert
	public Integer insertPayment(PaymentBean payment) {
		int cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace+".insertPayment", payment);		
		return cnt;
	}
	
	//PaymentController //���ó�¥ ������ �ݾ� update
	public Integer Updatepaymentday(PaymentdayBean payday) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace+".Updatepaymentday", payday);		
		return cnt;		
	}
	
	//PaymentController //���ó�¥ ������ �ݾ� insert
	public Integer insertPaymentDay(PaymentdayBean payday) {
		int cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace+".insertPaymentDay", payday);		
		return cnt;
	}
	
	//SlesControllder	//payment ����Ʈ ��������	//��¥��
	public List<PaymentBean> selectPayList(String today){
		List<PaymentBean> lists = new ArrayList<PaymentBean>();
		lists = sqlSessionTemplate.selectList(namespace+".selectPayList", today);
		return lists;
	}
	
	//SlesControllder	//paymentday ����Ʈ ��������	//����
	public List<PaymentdayBean> selectPaydayList(String month){
		List<PaymentdayBean> lists = new ArrayList<PaymentdayBean>();
		lists = sqlSessionTemplate.selectList(namespace+".selectPaydayList", month);
		return lists;
	}
	
	//SlesControllder	//paymentday ����Ʈ ��������	//��¥��
	public List<PaymentdayBean> selectPaydayList2(String today){
		List<PaymentdayBean> lists = new ArrayList<PaymentdayBean>();
		lists = sqlSessionTemplate.selectList(namespace+".selectPaydayList2", today);
		return lists;
	}
	
	//SalesController	//��Ż�ݾ� �����ϱ�
	public Integer EditPaymentday(String editPrice) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace+".editPrice", editPrice);
		return cnt;
	}
	
	//SalesController //��¥���ϱ�
	public static String getFormatDate(String format, String term_gubun,int term) {
		String ret_date = "";
		Calendar cur_date = Calendar.getInstance(); // ���� ��¥
		SimpleDateFormat formatter; // ��¥ ����

		term_gubun = term_gubun.toUpperCase();
		if ("".equals(term_gubun))
			term_gubun = "NOW";
		try {
			if (term_gubun.equals("Y") || term_gubun.equals("M")
					|| term_gubun.equals("D") || term_gubun.equals("NOW")) {
				formatter = new SimpleDateFormat(format);

				if (term_gubun.equals("Y")) {
					cur_date.add(Calendar.YEAR, term);
				} else if (term_gubun.equals("M")) {
					cur_date.add(Calendar.MONTH, term);
				} else if (term_gubun.equals("D")) {
					cur_date.add(Calendar.DAY_OF_MONTH, term);
				}
				ret_date = formatter.format(cur_date.getTime());
			}
			return ret_date.trim();
		} catch (Exception e) {
			System.out.println("[MessageUtil::getDateFormt] Error = " + e);
		}
		return "";
	}

}
	

