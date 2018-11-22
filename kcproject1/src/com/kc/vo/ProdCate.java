package com.kc.vo;

public class ProdCate {
	private int prod_cate_no;
	private String prod_cate_name;
	public ProdCate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProdCate(int prod_cate_no, String prod_cate_name) {
		super();
		this.prod_cate_no = prod_cate_no;
		this.prod_cate_name = prod_cate_name;
	}
	@Override
	public String toString() {
		return "{\"prod_cate_no\":" + prod_cate_no + ", \"prod_cate_name\":" + prod_cate_name + "}";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + prod_cate_no;
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
		ProdCate other = (ProdCate) obj;
		if (prod_cate_no != other.prod_cate_no)
			return false;
		return true;
	}
	public int getProd_cate_no() {
		return prod_cate_no;
	}
	public void setProd_cate_no(int prod_cate_no) {
		this.prod_cate_no = prod_cate_no;
	}
	public String getProd_cate_name() {
		return prod_cate_name;
	}
	public void setProd_cate_name(String prod_cate_name) {
		this.prod_cate_name = prod_cate_name;
	}
	
	
}
