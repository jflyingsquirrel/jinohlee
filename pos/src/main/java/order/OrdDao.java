package order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import product.ProductBean;

@Component("MyOrderDao")
public class OrdDao {
	
	
	private static final String namespace ="ord.Order";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public OrdDao() {}
	

	//OrderDetailControllder	//��ǰ+�ֹ����� join����Ʈ�������� //PayMentController //����Ʈ ��������
	public List<HashMap<String, Object>> ShopDetailView(String oid) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("oid", oid);
		List<HashMap<String, Object>> maplists = null;
		maplists = sqlSessionTemplate.selectList(namespace+".ShopDetailView", map);
	
		return maplists;
	}
	
	//OrderDetailController	//��ǰ����Ʈ��������
	public List<ProductBean> selectProdList(String categoryid) {
		List<ProductBean> list = new ArrayList<ProductBean>() ;		
		list = sqlSessionTemplate.selectList(namespace+".selectProdList", categoryid);
		return list;		
}
	
	//ProdController	//��ǰ ������ +1 	//OrderCancleController +�߰�
	public Integer UpdateProd(Map<String, String> map) {
		int cnt = -1;				
		cnt = sqlSessionTemplate.update(namespace+".UpdateProd", map);
		return cnt;
	}
	
	//ProdController	//��ǰ������ insert +1
	public Integer InsertProd(Map<String, String> map) {
		int cnt = -1;				
		cnt = sqlSessionTemplate.insert(namespace+".InsertProd", map);		
		return cnt;
	}

	//OrderCancleController	//��ü���
	public Integer deleteOrder(String oid) {
		int cnt = -1;
		cnt = sqlSessionTemplate.delete(namespace+".deleteOrder", oid);
		return cnt;
	}
	
	//OrderCancleController -����
	public Integer UpdateQtyMinus(Map<String, String> map) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace+".UpdateQtyMinus", map);
		return cnt;
	}
	
	//OrderCancleController	//��ǰ������������
	public OrdBean selectOrderOne(Map<String, String> map) {
		OrdBean list = new OrdBean();
		list = sqlSessionTemplate.selectOne(namespace+".selectByOid",map);
		return list;
	}
	
	//OrderCancleController	//����1���������� ��ǰ�����ϱ�
	public Integer deleteByProd(Map<String, String> map){
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace+".deleteByProd", map);
		return cnt;
	}	
	
	//OrderMoveController	//�ڸ��̵�
	public Integer OrderMove(Map<String, Integer> map) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace+".orderMove", map);
		return cnt;
	}

	
}
