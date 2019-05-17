package com.inhatc.vo;

public class PurchaseVO{
    private int order_no;
    private String id;
    private String order_status;
    private String order_date;
    private String del_add;
    private String del_name;
    private String del_phone;
    
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getDel_add() {
		return del_add;
	}
	public void setDel_add(String del_add) {
		this.del_add = del_add;
	}
	public String getDel_name() {
		return del_name;
	}
	public void setDel_name(String del_name) {
		this.del_name = del_name;
	}
	public String getDel_phone() {
		return del_phone;
	}
	public void setDel_phone(String del_phone) {
		this.del_phone = del_phone;
	}
}