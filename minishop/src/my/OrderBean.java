package my;

public class OrderBean {
	/*
		create table ord(
	orderId number, -- 주문번호(orderseq)
	memno number, -- 회원번호
	pnum number, -- 상품번호
	qty number, -- 수량
	amount number, -- 전체가격
	regdate date default sysdate
);
	*/
	
	// 쇼핑내역에 보여줄 내용을 객체로 만든다.
	
	
	private int orderId;
	private String mname ; //고객명
	private String mid ; //고객 아이디
	private String pname ; //상품명
	private int qty ; //판매 수량
	private int amount ; //판매 금액
	
	public String getMname() {
		return mname;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}	
}