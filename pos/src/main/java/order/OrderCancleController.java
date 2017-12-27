package order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//OrderDetail.jsp	//��ü���, +-
@Controller
public class OrderCancleController {
	
	private static final String command = "/ordercancle.od";
	private static final String getPage = "redirect:/order.od";
	
	@Autowired
	OrdDao orderdao;
	
	//OrderDetail.jsp	//�������� ���ϱ�
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String doActionGet(
			@RequestParam(value="oid") String oid,
			@RequestParam(value="pnum") String pnum,
			@RequestParam(value="qty") String qty,
			Model model) {
		
				
		Map<String, String> map = new HashMap<String, String>();
		map.put("oid", oid);
		map.put("pnum", pnum);
		
		if(qty.equals("plus")) {	//�÷����ΰ�� �ϳ��߰�, ���̳ʽ��ΰ�� �ϳ�����			
			orderdao.UpdateProd(map);	
		}else {	
			
			System.out.println("���̳ʽ�");
			OrdBean list = orderdao.selectOrderOne(map);
			System.out.println("getqty:"+list.getQty());
			
			if(list.getQty()==1) {
				orderdao.deleteByProd(map);
			}			
			else {
			orderdao.UpdateQtyMinus(map);
			}
		}		
		
		
		model.addAttribute("oid",oid);
		return getPage;
	}
	
	//OrderDetail.jsp	//��ü���
	@RequestMapping(value=command, method=RequestMethod.POST)
	public String doActionPost(
			@RequestParam(value="oid") String oid,
			Model model) {
		
		orderdao.deleteOrder(oid);
		model.addAttribute("oid", oid);
		
		return getPage;
	}
	
	
	

}
