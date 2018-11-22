package com.kc.vo;

public class IngredientInfo {
	private int ingred_no;
	private String ingred_name;
	private int bonsa_receiving_price;
	private int commission;
	private int bonsa_forwarding_price;
	private IngredientCate ingredCate; //ingred_cate_no 사용
	private int quantity;
	private String measurement;
	public IngredientInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IngredientInfo(int ingred_no, String ingred_name, int bonsa_receiving_price, int commission,
			int bonsa_forwarding_price, IngredientCate ingredCate, int quantity, String measurement) {
		super();
		this.ingred_no = ingred_no;
		this.ingred_name = ingred_name;
		this.bonsa_receiving_price = bonsa_receiving_price;
		this.commission = commission;
		this.bonsa_forwarding_price = bonsa_forwarding_price;
		this.ingredCate = ingredCate;
		this.quantity = quantity;
		this.measurement = measurement;
	}
	@Override
	public String toString() {
		return "{\"ingred_no\":" + ingred_no + ", \"ingred_name\":" + ingred_name + ", \"bonsa_receiving_price\":"
				+ bonsa_receiving_price + ", \"commission\":" + commission + ", \"bonsa_forwarding_price\":"
				+ bonsa_forwarding_price + ", \"ingredCate\":" + ingredCate + ", \"quantity\":" + quantity + ", \"measurement\":"
				+ measurement + "}";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ingred_no;
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
		IngredientInfo other = (IngredientInfo) obj;
		if (ingred_no != other.ingred_no)
			return false;
		return true;
	}
	public int getIngred_no() {
		return ingred_no;
	}
	public void setIngred_no(int ingred_no) {
		this.ingred_no = ingred_no;
	}
	public String getIngred_name() {
		return ingred_name;
	}
	public void setIngred_name(String ingred_name) {
		this.ingred_name = ingred_name;
	}
	public int getBonsa_receiving_price() {
		return bonsa_receiving_price;
	}
	public void setBonsa_receiving_price(int bonsa_receiving_price) {
		this.bonsa_receiving_price = bonsa_receiving_price;
	}
	public int getCommission() {
		return commission;
	}
	public void setCommission(int commission) {
		this.commission = commission;
	}
	public int getBonsa_forwarding_price() {
		return bonsa_forwarding_price;
	}
	public void setBonsa_forwarding_price(int bonsa_forwarding_price) {
		this.bonsa_forwarding_price = bonsa_forwarding_price;
	}
	public IngredientCate getIngredCate() {
		return ingredCate;
	}
	public void setIngredCate(IngredientCate ingredCate) {
		this.ingredCate = ingredCate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	
}
