package com.kc.vo;

public class RestockingLine {
	private int restocking_no;
	private IngredientInfo ingredient;
	private String rsl_prod_state_flag;
	private int restocking_quantity;
	private String dc_flag;
	public RestockingLine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RestockingLine(int restocking_no, IngredientInfo ingredient, String rsl_prod_state_flag,
			int restocking_quantity,String dc_flag) {
		super();
		this.restocking_no = restocking_no;
		this.ingredient = ingredient;
		this.rsl_prod_state_flag = rsl_prod_state_flag;
		this.restocking_quantity = restocking_quantity;
		this.dc_flag = dc_flag;
	}
	@Override
	public String toString() {
		return "{\"restocking_no\":" + restocking_no + ", \"ingredient\":" + ingredient
				+ ", \"rsl_prod_state_flag\":" + "\""+rsl_prod_state_flag+"\"" 
				+ ", \"restocking_quantity\":" + restocking_quantity 
				+ ", \"dc_flag\":"+"\""+ dc_flag +"\""+ "}";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ingredient == null) ? 0 : ingredient.hashCode());
		result = prime * result + restocking_no;
		result = prime * result + ((rsl_prod_state_flag == null) ? 0 : rsl_prod_state_flag.hashCode());
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
		RestockingLine other = (RestockingLine) obj;
		if (ingredient == null) {
			if (other.ingredient != null)
				return false;
		} else if (!ingredient.equals(other.ingredient))
			return false;
		if (restocking_no != other.restocking_no)
			return false;
		if (rsl_prod_state_flag == null) {
			if (other.rsl_prod_state_flag != null)
				return false;
		} else if (!rsl_prod_state_flag.equals(other.rsl_prod_state_flag))
			return false;
		return true;
	}
	public int getRestocking_no() {
		return restocking_no;
	}
	public void setRestocking_no(int restocking_no) {
		this.restocking_no = restocking_no;
	}
	public IngredientInfo getIngredient() {
		return ingredient;
	}
	public void setIngredient(IngredientInfo ingredient) {
		this.ingredient = ingredient;
	}
	public String getRsl_prod_state_flag() {
		return rsl_prod_state_flag;
	}
	public void setRsl_prod_state_flag(String rsl_prod_state_flag) {
		this.rsl_prod_state_flag = rsl_prod_state_flag;
	}
	public int getRestocking_quantity() {
		return restocking_quantity;
	}
	public void setRestocking_quantity(int restocking_quantity) {
		this.restocking_quantity = restocking_quantity;
	}
	public String getDc_flag() {
		return dc_flag;
	}
	public void setDc_flag(String dc_flag) {
		this.dc_flag = dc_flag;
	}
	
	

}
