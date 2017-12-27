package my;

import java.sql.SQLException;
import java.util.Vector;




public class CartBean {
	
	private Vector<ProdBean> clist;
	
	public CartBean() {
		clist = new Vector<ProdBean>();
	}
	
	
	public void addProduct(String pnum, String oqty) throws SQLException{
		
		int oqty_new=Integer.parseInt(oqty.trim());
		int pnum_new=Integer.parseInt(pnum.trim());

		ProdBean newPd =null; 
		ProdDao pdao = ProdDao.getInstance();
		newPd = pdao.selectByNum(pnum);
		
		for(int i=0;i<clist.size();i++){
			
			int cPnum = clist.get(i).getPnum();
			
			if(cPnum == pnum_new){  
				System.out.println(cPnum +":번호 같음:");
				
				int cPqty = clist.get(i).getPqty();
				clist.get(i).setPqty(cPqty+oqty_new);  
				clist.get(i).setTotalPrice(clist.get(i).getPrice()*(cPqty+oqty_new));
				clist.get(i).setTotalPoint(clist.get(i).getPoint()*(cPqty+oqty_new));
				return; 						 
			}
		} // for
		
		newPd.setPqty( oqty_new ) ; 
		int totalPrice = oqty_new * newPd.getPrice() ;
		int totalPoint = oqty_new * newPd.getPoint();
		newPd.setTotalPrice(totalPrice) ;
		newPd.setTotalPoint(totalPoint) ;
		
		clist.add(newPd); //장바구니에 추가
		
		for(int j=0;j<clist.size();j++){
			System.out.println(clist.get(j).getPnum()+" : " + clist.get(j).getPname());	
		}
		
	}
	
	//mall_cartlist.jsp
	public Vector<ProdBean> getAllProducts(){			
		return clist;
	}
	
	public void setEdit(String oqty, String pnum, String edit) {
		
		int qty=Integer.parseInt(oqty.trim());
		
		System.out.println("oqty:"+oqty+"pnum:"+pnum+"edit:"+edit);
		
		for(ProdBean pd: clist) {
			
			if(Integer.parseInt(pnum)==pd.getPnum()) {
				if(edit.equals("plus")) {
					pd.setPqty(qty+1);
					int Price = pd.getPqty() * pd.getPrice() ;
					int Point = pd.getPqty()  * pd.getPoint();
					pd.setTotalPrice(Price) ;
					pd.setTotalPoint(Point) ;					
				}else {
					pd.setPqty(qty-1);
					int Price = pd.getPqty() * pd.getPrice() ;
					int Point = pd.getPqty()  * pd.getPoint();
					pd.setTotalPrice(Price) ;
					pd.setTotalPoint(Point) ;
					
					if(qty-1==0) {
						clist.removeElement(pd);
					}
				}				
			}
		}//for--	
		
	}
	
	public void setDelete(String pnum) {
		
		int num = Integer.parseInt(pnum); 
		
		for(ProdBean pd : clist) {
			
			if(num==pd.getPnum())
				clist.removeElement(pd);
				break;
		}		
	}
	
	//mall_calculate.jsp
	public void removeAllProduct() {
		clist.removeAllElements();
	}

}
