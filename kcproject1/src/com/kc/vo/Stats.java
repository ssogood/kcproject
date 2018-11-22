package com.kc.vo;


public class Stats {

	private String branch_code;	//branch_code
	private String order_date;		//order_date : to_char로 넘어 오므로 
	private String prod_cate_name;		//prod_cate_name
	private String prod_name;		//prod_no
	private int prod_sum;
	private int count;
	
	
	
	public String getBranch_code() {
		return branch_code;
	}
	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	
	
	
	public String getProd_cate_name() {
		return prod_cate_name;
	}
	public void setProd_cate_name(String prod_cate_name) {
		this.prod_cate_name = prod_cate_name;
	}
	
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public int getProd_sum() {
		return prod_sum;
	}
	public void setProd_sum(int prod_sum) {
		this.prod_sum = prod_sum;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "{\"branch_code\":\"" + branch_code + "\", \"order_date\":\"" + order_date + "\", \"prod_cate_name\":\""
				+ prod_cate_name+ "\", \"prod_name\":\"" + prod_name + "\", \"prod_sum\":\""
				+ prod_sum + "\", \"count\":\"" + count + "\"}";
	}
	public Stats(String branch_code, String order_date, String prod_cate_name, String prod_name, int prod_sum, int count) {
		super();
		this.branch_code = branch_code;
		this.order_date = order_date;
		this.prod_cate_name = prod_cate_name;
		this.prod_name = prod_name;
		this.prod_sum = prod_sum;
		this.count = count;
	}
	
	
	public Stats() {
		super();
		// TODO Auto-generated constructor stub
	}

}
