package com.kc.vo;

import java.util.Date;
import java.util.List;

public class BaljuInfo {
	private int balju_no;
	private Date balju_date;
	private String balju_state_flag;
	private Date receiving_date;
	private List<BaljuLine> baljuLines;
	public BaljuInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BaljuInfo(int balju_no, Date balju_date, String balju_state_flag, Date receiving_date,
			List<BaljuLine> baljuLines) {
		super();
		this.balju_no = balju_no;
		this.balju_date = balju_date;
		this.balju_state_flag = balju_state_flag;
		this.receiving_date = receiving_date;
		this.baljuLines = baljuLines;
	}
	@Override
	public String toString() {
		return "{\"balju_no\":" + balju_no + ", \"balju_date\":" + balju_date + ", \"balju_state_flag\":"
				+ balju_state_flag + ", \"receiving_date\":" + receiving_date + ", \"baljuLines\":" + baljuLines + "}";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + balju_no;
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
		BaljuInfo other = (BaljuInfo) obj;
		if (balju_no != other.balju_no)
			return false;
		return true;
	}
	public int getBalju_no() {
		return balju_no;
	}
	public void setBalju_no(int balju_no) {
		this.balju_no = balju_no;
	}
	public Date getBalju_date() {
		return balju_date;
	}
	public void setBalju_date(Date balju_date) {
		this.balju_date = balju_date;
	}
	public String getBalju_state_flag() {
		return balju_state_flag;
	}
	public void setBalju_state_flag(String balju_state_flag) {
		this.balju_state_flag = balju_state_flag;
	}
	public Date getReceiving_date() {
		return receiving_date;
	}
	public void setReceiving_date(Date receiving_date) {
		this.receiving_date = receiving_date;
	}
	public List<BaljuLine> getBaljuLines() {
		return baljuLines;
	}
	public void setBaljuLines(List<BaljuLine> baljuLines) {
		this.baljuLines = baljuLines;
	}
	
	

}
