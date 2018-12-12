package com.kc.vo;

public class IngredientCate {
	private int ingred_cate_no;
	private String ingred_cate_name;
	public IngredientCate() {
		super();
	}
	public IngredientCate(int ingred_cate_no, String ingred_cate_name) {
		super();
		this.ingred_cate_no = ingred_cate_no;
		this.ingred_cate_name = ingred_cate_name;
	}
	@Override
	public String toString() {
		return "{\"ingred_cate_no\":" + ingred_cate_no + ", \"ingred_cate_name\":\"" + ingred_cate_name + "\"}";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ingred_cate_no;
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
		IngredientCate other = (IngredientCate) obj;
		if (ingred_cate_no != other.ingred_cate_no)
			return false;
		return true;
	}
	public int getIngred_cate_no() {
		return ingred_cate_no;
	}
	public void setIngred_cate_no(int ingred_cate_no) {
		this.ingred_cate_no = ingred_cate_no;
	}
	public String getIngred_cate_name() {
		return ingred_cate_name;
	}
	public void setIngred_cate_name(String ingred_cate_name) {
		this.ingred_cate_name = ingred_cate_name;
	}
	
	
	
	

}
