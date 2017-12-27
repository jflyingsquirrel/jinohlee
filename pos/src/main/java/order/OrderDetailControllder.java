package order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderDetailControllder {
	
	private static final String command="/order.od";
	private static final String getPage="OrderDetail";
	
	@Autowired
	OrdDao orderdao;
	
	
	//List.jsp get ȣ��
	@RequestMapping(command)
	public String doActionGet(
			@RequestParam(value="oid") String oid,
			@RequestParam(value="categoryid", required = false) String categoryid,
			Model model) {
		
		
		List<ShoppingInfo> shoplists = new ArrayList<ShoppingInfo>();	
		
		List<HashMap<String, Object>> lists = this.orderdao.ShopDetailView(oid); //��ǰ+�ֹ����� ����Ʈ

		int totalcount = 0;
		
		for(HashMap<String, Object> map: lists) {	//����Ʈ���� �����ͼ� shopinfo�� �ֱ�
			BigDecimal pnum= (BigDecimal)map.get("PNUM");	//BigDecimal �ٿ�ĳ����
			String pname =(String)map.get("PNAME");
			BigDecimal qty = (BigDecimal)map.get("QTY");
			BigDecimal price = (BigDecimal)map.get("PRICE");
			BigDecimal amount = (BigDecimal)map.get("AMOUNT");
						
			ShoppingInfo shopinfo = new ShoppingInfo();
			shopinfo.setPnum(pnum.intValue());
			shopinfo.setPname(pname);
			shopinfo.setQty(qty.intValue());
			shopinfo.setPrice(price.intValue());
			shopinfo.setAmount(amount.intValue());
				
			shoplists.add(shopinfo);	
			
			totalcount += shopinfo.getAmount();				
		}			
		
		
		List<product.ProductBean> prodlists = null;
		
		if(categoryid !=null) {		//OrderDetail.jsp���� ī�װ�Ŭ���� getȣ��	
			prodlists = orderdao.selectProdList(categoryid);	//ī�װ�id�� ��ǰ������������
			model.addAttribute("prodlists", prodlists);		
		}	
		
		model.addAttribute("oid", oid);		//�ֹ���ȣ
		model.addAttribute("shoplists", shoplists);		//ī�װ���ǰ����
		model.addAttribute("totalcount", totalcount);	//�ѱݾ�
		
		
		return getPage;	//OrderDetail.jsp
	}
	
	
	
	
	
	

}
