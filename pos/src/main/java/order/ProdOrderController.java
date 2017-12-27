package order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProdOrderController {

	private static final String command = "/prod.od";
	private static final String getPage = "redirect:/order.od";
	
	
	@Autowired
	OrdDao orderdao;
	
	//OrderDetail.jsp ȣ��	//��ǰŬ���� �߰�
	@RequestMapping(command)
	public String doActionGet(
			@RequestParam(value="oid") String oid,
			@RequestParam(value="num") String pnum,
			@RequestParam(value="categoryid") String categoryid,
			Model model){
			
				
		Map<String, String> map = new HashMap<String, String>();
		map.put("oid", oid);
		map.put("pnum", pnum);		
		
		int cnt = -1;
		cnt = orderdao.UpdateProd(map);	//��ǰ�� ������� update
		
		if(cnt==0) {				//��ǰ�� ������� insert			
			orderdao.InsertProd(map);
		}	
		
		model.addAttribute("oid", oid);
		model.addAttribute("categoryid", categoryid);
		
		
		return getPage;	//OrderDetailController
		
	}	
	
}
