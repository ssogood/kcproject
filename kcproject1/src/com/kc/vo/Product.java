package com.kc.vo;

public class Product {
	private String prod_no;
	private String prod_name;
	private int prod_price;
	private int prod_cost;
	private ProdCate cate; //prod_cate_no 사용
	private String prod_info;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String prod_no, String prod_name, int prod_price, int prod_cost, ProdCate cate, String prod_info) {
		super();
		this.prod_no = prod_no;
		this.prod_name = prod_name;
		this.prod_price = prod_price;
		this.prod_cost = prod_cost;
		this.cate = cate;
		this.prod_info = prod_info;
	}
	@Override
	public String toString() {
		return "{\"prod_no\":" + prod_no + ", \"prod_name\":" + prod_name + ", \"prod_price\":" + prod_price
				+ ", \"prod_cost\":" + prod_cost + ", \"cate\":" + cate + ", \"prod_info\":" + prod_info + "}";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prod_no == null) ? 0 : prod_no.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (prod_no == null) {
			if (other.prod_no != null)
				return false;
		} else if (!prod_no.equals(other.prod_no))
			return false;
		return true;
	}
	public String getProd_no() {
		return prod_no;
	}
	public void setProd_no(String prod_no) {
		this.prod_no = prod_no;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public int getProd_price() {
		return prod_price;
	}
	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}
	public int getProd_cost() {
		return prod_cost;
	}
	public void setProd_cost(int prod_cost) {
		this.prod_cost = prod_cost;
	}
	public ProdCate getCate() {
		return cate;
	}
	public void setCate(ProdCate cate) {
		this.cate = cate;
	}
	public String getProd_info() {
		return prod_info;
	}
	public void setProd_info(String prod_info) {
		this.prod_info = prod_info;
	}
	
	
}
