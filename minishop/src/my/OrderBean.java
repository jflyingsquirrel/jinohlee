package my;

public class OrderBean {
	/*
		create table ord(
	orderId number, -- �ֹ���ȣ(orderseq)
	memno number, -- ȸ����ȣ
	pnum number, -- ��ǰ��ȣ
	qty number, -- ����
	amount number, -- ��ü����
	regdate date default sysdate
);
	*/
	
	// ���γ����� ������ ������ ��ü�� �����.
	
	
	private int orderId;
	private String mname ; //����
	private String mid ; //�� ���̵�
	private String pname ; //��ǰ��
	private int qty ; //�Ǹ� ����
	private int amount ; //�Ǹ� �ݾ�
	
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