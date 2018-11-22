package com.kc.vo;

public class ReceivingLine {
	private int receiving_no;
	private IngredientInfo ingredient; //ingred_no사용
	private int receiving_quantity;
	public ReceivingLine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReceivingLine(int receiving_no, IngredientInfo ingredient, int receiving_quantity) {
		super();
		this.receiving_no = receiving_no;
		this.ingredient = ingredient;
		this.receiving_quantity = receiving_quantity;
	}
	@Override
	public String toString() {
		return "{\"receiving_no\":" + receiving_no + ", \"ingredient\":" + ingredient + ", \"receiving_quantity\":"
				+ receiving_quantity + "}";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ingredient == null) ? 0 : ingredient.hashCode());
		result = prime * result + receiving_no;
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
		ReceivingLine other = (ReceivingLine) obj;
		if (ingredient == null) {
			if (other.ingredient != null)
				return false;
		} else if (!ingredient.equals(other.ingredient))
			return false;
		if (receiving_no != other.receiving_no)
			return false;
		return true;
	}
	public int getReceiving_no() {
		return receiving_no;
	}
	public void setReceiving_no(int receiving_no) {
		this.receiving_no = receiving_no;
	}
	public IngredientInfo getIngredient() {
		return ingredient;
	}
	public void setIngredient(IngredientInfo ingredient) {
		this.ingredient = ingredient;
	}
	public int getReceiving_quantity() {
		return receiving_quantity;
	}
	public void setReceiving_quantity(int receiving_quantity) {
		this.receiving_quantity = receiving_quantity;
	}
	
	

}
