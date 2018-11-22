package com.kc.vo;

public class ForwardingLine {
	private int forwarding_no;
	private IngredientInfo ingredient;//ingred_no사용
	private int forwarding_quantity;
	public ForwardingLine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ForwardingLine(int forwarding_no, IngredientInfo ingredient, int forwarding_quantity) {
		super();
		this.forwarding_no = forwarding_no;
		this.ingredient = ingredient;
		this.forwarding_quantity = forwarding_quantity;
	}
	@Override
	public String toString() {
		return "{\"forwarding_no\":" + forwarding_no + ", \"ingredient\":" + ingredient
				+ ", \"forwarding_quantity\":" + forwarding_quantity + "}";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + forwarding_no;
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
		ForwardingLine other = (ForwardingLine) obj;
		if (forwarding_no != other.forwarding_no)
			return false;
		if (ingredient == null) {
			if (other.ingredient != null)
				return false;
		} else if (!ingredient.equals(other.ingredient))
			return false;
		return true;
	}
	public int getForwarding_no() {
		return forwarding_no;
	}
	public void setForwarding_no(int forwarding_no) {
		this.forwarding_no = forwarding_no;
	}
	public IngredientInfo getIngredient() {
		return ingredient;
	}
	public void setIngredient(IngredientInfo ingredient) {
		this.ingredient = ingredient;
	}
	public int getForwarding_quantity() {
		return forwarding_quantity;
	}
	public void setForwarding_quantity(int forwarding_quantity) {
		this.forwarding_quantity = forwarding_quantity;
	}
	

}
