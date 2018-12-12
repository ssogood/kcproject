package com.kc.vo;

public class ReturnLine {
	private int return_no;
	//private int ingred_no;
	private IngredientInfo ingredient; //ingred_no 사용
	private String rtl_prod_state_flag;
	private int return_quantity;
	public ReturnLine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReturnLine(int return_no, IngredientInfo ingredient, String rtl_prod_state_flag, int return_quantity) {
		super();
		this.return_no = return_no;
		this.ingredient = ingredient;
		this.rtl_prod_state_flag = rtl_prod_state_flag;
		this.return_quantity = return_quantity;
	}
	@Override
	public String toString() {
		return "{\"return_no\":" + return_no + ", \"ingredient\":" + ingredient + ", \"rtl_prod_state_flag\":"
				+ rtl_prod_state_flag + ", \"return_quantity\":" + return_quantity + "}";
	}
	
	
	
	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ingredient == null) ? 0 : ingredient.hashCode());
		result = prime * result + return_no;
		result = prime * result + ((rtl_prod_state_flag == null) ? 0 : rtl_prod_state_flag.hashCode());
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
		ReturnLine other = (ReturnLine) obj;
		if (ingredient == null) {
			if (other.ingredient != null)
				return false;
		} else if (!ingredient.equals(other.ingredient))
			return false;
		if (return_no != other.return_no)
			return false;
		if (rtl_prod_state_flag == null) {
			if (other.rtl_prod_state_flag != null)
				return false;
		} else if (!rtl_prod_state_flag.equals(other.rtl_prod_state_flag))
			return false;
		return true;
	}*/
	
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ingredient == null) ? 0 : ingredient.hashCode());
		result = prime * result + ((rtl_prod_state_flag == null) ? 0 : rtl_prod_state_flag.hashCode());
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
		ReturnLine other = (ReturnLine) obj;
		if (ingredient == null) {
			if (other.ingredient != null)
				return false;
		} else if (!ingredient.equals(other.ingredient))
			return false;
		if (rtl_prod_state_flag == null) {
			if (other.rtl_prod_state_flag != null)
				return false;
		} else if (!rtl_prod_state_flag.equals(other.rtl_prod_state_flag))
			return false;
		return true;
	}
	
	public int getReturn_no() {
		return return_no;
	}
	public void setReturn_no(int return_no) {
		this.return_no = return_no;
	}
	public IngredientInfo getIngredient() {
		return ingredient;
	}
	public void setIngredient(IngredientInfo ingredient) {
		this.ingredient = ingredient;
	}
	public String getRtl_prod_state_flag() {
		return rtl_prod_state_flag;
	}
	public void setRtl_prod_state_flag(String rtl_prod_state_flag) {
		this.rtl_prod_state_flag = rtl_prod_state_flag;
	}
	public int getReturn_quantity() {
		return return_quantity;
	}
	public void setReturn_quantity(int return_quantity) {
		this.return_quantity = return_quantity;
	}
	
	

}
