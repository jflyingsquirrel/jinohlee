package product;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import payment.PaymentBean;

@Component("MyProductDao")
public class ProductDao {
	
	private static final String namespace ="pro.product"; 
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//ProductController	//��ǰ����Ʈ ��������
	public List<ProductBean> selectProductList(){
		List<ProductBean> lists = new ArrayList<ProductBean>();
		lists = sqlSessionTemplate.selectList(namespace+".selectProductList");	
		return lists;
	}
	
	//ProductListController	//ī�װ��� ��ǰ����Ʈ
	public List<ProductBean> ProductListBycate(String categoryid){
		List<ProductBean> lists = new ArrayList<ProductBean>();
		lists = sqlSessionTemplate.selectList(namespace+".ProductListBycate", categoryid);	
		return lists;
	}
	
	//ProducnController	//��ǰ�߰�
	public Integer InsertProduct(ProductBean product) {
		int cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace+".InsertProduct",product);
		return cnt;
	}
	
	//ProductUpdateController	//��ǰ����
	public Integer UpdateProd(ProductBean product) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace+".UpdateProd",product);
		return cnt;
	}
	
	//ProductDeleteController	//��ǰ����
	public Integer deleteProduct(int num) {
		int cnt = -1;
		cnt = sqlSessionTemplate.delete(namespace+".DeleteProd",num);
		return cnt;
	}
	
	//ProductUpdateController	//��ǰ�� �ֹ����� ��������
	public List<PaymentBean> selectProdsales(String productname) {
		List<PaymentBean> lists = new ArrayList<PaymentBean>();
		lists = sqlSessionTemplate.selectList(namespace+".selectProdsales", productname);
		return lists;
	}



}
