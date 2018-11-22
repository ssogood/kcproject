package com.kc.vo;

public class BaljuLine {
	private int balju_no;
	private IngredientInfo ingredient; //ingred_no사용
	private int balju_quantity;
	public BaljuLine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BaljuLine(int balju_no, IngredientInfo ingredient, int balju_quantity) {
		super();
		this.balju_no = balju_no;
		this.ingredient = ingredient;
		this.balju_quantity = balju_quantity;
	}
	@Override
	public String toString() {
		return "{\"balju_no\":" + balju_no + ", \"ingredient\":" + ingredient + ", \"balju_quantity\":" + balju_quantity
				+ "}";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + balju_no;
		result = prime * result + ((ingredient == null) ? 0 : ingredient.hashCode());
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
		BaljuLine other = (BaljuLine) obj;
		if (balju_no != other.balju_no)
			return false;
		if (ingredient == null) {
			if (other.ingredient != null)
				return false;
		} else if (!ingredient.equals(other.ingredient))
			return false;
		return true;
	}
	public int getBalju_no() {
		return balju_no;
	}
	public void setBalju_no(int balju_no) {
		this.balju_no = balju_no;
	}
	public IngredientInfo getIngredient() {
		return ingredient;
	}
	public void setIngredient(IngredientInfo ingredient) {
		this.ingredient = ingredient;
	}
	public int getBalju_quantity() {
		return balju_quantity;
	}
	public void setBalju_quantity(int balju_quantity) {
		this.balju_quantity = balju_quantity;
	}
	
	
	
	

}
