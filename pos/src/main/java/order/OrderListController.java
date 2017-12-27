package order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class OrderListController {
	
	private static final String command="/list.od";
	private static final String getPage="List";
	
	@Autowired
	OrdDao orddao;

	
	//LoginContoller ȣ��
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String doActionGet(
			@RequestParam(value="oid", required=false) String oid
			) {		
		
		if(oid !=null) {		//�����ϱ��� �Ѿ�ö� oid�� ������ �Ѿ�´�.		
			orddao.deleteOrder(oid);			
		}
		return getPage;	//List.jsp
	}
	
	
	

}
