package com.kc.vo;

import java.util.Date;

public class Discard {
	private int discard_no;
	private IngredientInfo ingredient; //ingred_no 사용
	private int discard_quantity;
	private Date discard_date;
	private String dc_prod_state_flag;
	public Discard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Discard(int discard_no, IngredientInfo ingredient, int discard_quantity, Date discard_date,
			String dc_prod_state_flag) {
		super();
		this.discard_no = discard_no;
		this.ingredient = ingredient;
		this.discard_quantity = discard_quantity;
		this.discard_date = discard_date;
		this.dc_prod_state_flag = dc_prod_state_flag;
	}
	@Override
	public String toString() {
		return "{\"discard_no\":" + discard_no + ", \"ingredient\":" + ingredient + ", \"discard_quantity\":"
				+ discard_quantity + ", \"discard_date\":" + discard_date + ", \"dc_prod_state_flag\":" + dc_prod_state_flag
				+ "}";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + discard_no;
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
		Discard other = (Discard) obj;
		if (discard_no != other.discard_no)
			return false;
		return true;
	}
	public int getDiscard_no() {
		return discard_no;
	}
	public void setDiscard_no(int discard_no) {
		this.discard_no = discard_no;
	}
	public IngredientInfo getIngredient() {
		return ingredient;
	}
	public void setIngredient(IngredientInfo ingredient) {
		this.ingredient = ingredient;
	}
	public int getDiscard_quantity() {
		return discard_quantity;
	}
	public void setDiscard_quantity(int discard_quantity) {
		this.discard_quantity = discard_quantity;
	}
	public Date getDiscard_date() {
		return discard_date;
	}
	public void setDiscard_date(Date discard_date) {
		this.discard_date = discard_date;
	}
	public String getDc_prod_state_flag() {
		return dc_prod_state_flag;
	}
	public void setDc_prod_state_flag(String dc_prod_state_flag) {
		this.dc_prod_state_flag = dc_prod_state_flag;
	}
	
	

}
