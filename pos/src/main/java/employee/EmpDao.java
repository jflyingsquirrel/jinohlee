package employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("myEmpDao")
public class EmpDao {
	
	private static final String namespace = "empl.employee";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public EmpDao() {}
	
	
	//LoginConctoller �α���������������
	public EmpBean EmpList(String id) {
		System.out.println("dao.EmpList");	
		System.out.println("id:"+id);
		EmpBean bean = sqlSessionTemplate.selectOne(namespace+".selectEmp", id);
		return bean;		
	}
	
	List<MemoBean> lists = new ArrayList<MemoBean>();
	
	//MemoController 1,2,3,4 ������ ����Ʈ��������
	public List<MemoBean> selectMemo1(){		
		lists = sqlSessionTemplate.selectList(namespace+".selectMemo1");
		return lists;
	}
	
	public List<MemoBean> selectMemo2(){
		lists = sqlSessionTemplate.selectList(namespace+".selectMemo2");
		return lists;
	}
	
	public List<MemoBean> selectMemo3(){
		lists = sqlSessionTemplate.selectList(namespace+".selectMemo3");
		return lists;
	}
	
	public List<MemoBean> selectMemo4(){
		lists = sqlSessionTemplate.selectList(namespace+".selectMemo4");
		return lists;
	}

	//MemoUpdateController	//�޸��߰��ϱ�
	public Integer insertMemo(Map<String, String> map) {
		int cnt = -1;
		cnt = sqlSessionTemplate.insert(namespace+".insertMemo", map);
		return cnt;		
	}
	
	//MemoUpdateController	//�޸�����ϱ�
	public Integer deleteMemo(String num) {
		int cnt = -1;
		cnt = sqlSessionTemplate.delete(namespace+".deleteMemo", num);
		return cnt;		
	}
	

}
