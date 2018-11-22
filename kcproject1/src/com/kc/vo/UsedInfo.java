package com.kc.vo;

import java.util.Date;

public class UsedInfo {
	
	private IngredientInfo ingredient; //inred_no사용
	private Date record_date;
	private String stock_flag;
	private int change_quantity;
	public UsedInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UsedInfo(IngredientInfo ingredient, Date record_date, String stock_flag, int change_quantity) {
		super();
		this.ingredient = ingredient;
		this.record_date = record_date;
		this.stock_flag = stock_flag;
		this.change_quantity = change_quantity;
	}
	@Override
	public String toString() {
		return "{\"ingredient\":" + ingredient + ", \"record_date\":" + record_date + ", \"stock_flag\":" + stock_flag
				+ ", \"change_quantity\":" + change_quantity + "}";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ingredient == null) ? 0 : ingredient.hashCode());
		result = prime * result + ((record_date == null) ? 0 : record_date.hashCode());
		result = prime * result + ((stock_flag == null) ? 0 : stock_flag.hashCode());
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
		UsedInfo other = (UsedInfo) obj;
		if (ingredient == null) {
			if (other.ingredient != null)
				return false;
		} else if (!ingredient.equals(other.ingredient))
			return false;
		if (record_date == null) {
			if (other.record_date != null)
				return false;
		} else if (!record_date.equals(other.record_date))
			return false;
		if (stock_flag == null) {
			if (other.stock_flag != null)
				return false;
		} else if (!stock_flag.equals(other.stock_flag))
			return false;
		return true;
	}
	public IngredientInfo getIngredient() {
		return ingredient;
	}
	public void setIngredient(IngredientInfo ingredient) {
		this.ingredient = ingredient;
	}
	public Date getRecord_date() {
		return record_date;
	}
	public void setRecord_date(Date record_date) {
		this.record_date = record_date;
	}
	public String getStock_flag() {
		return stock_flag;
	}
	public void setStock_flag(String stock_flag) {
		this.stock_flag = stock_flag;
	}
	public int getChange_quantity() {
		return change_quantity;
	}
	public void setChange_quantity(int change_quantity) {
		this.change_quantity = change_quantity;
	}
	
	
}
