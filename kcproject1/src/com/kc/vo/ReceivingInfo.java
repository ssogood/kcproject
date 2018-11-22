package com.kc.vo;

import java.util.Date;
import java.util.List;

public class ReceivingInfo {
	private int receiving_no;
	private Date receiving_date;
	private String receiving_state_flag;
	private List<ReceivingLine> receivingLines;
	public ReceivingInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReceivingInfo(int receiving_no, Date receiving_date, String receiving_state_flag,
			List<ReceivingLine> receivingLines) {
		super();
		this.receiving_no = receiving_no;
		this.receiving_date = receiving_date;
		this.receiving_state_flag = receiving_state_flag;
		this.receivingLines = receivingLines;
	}
	@Override
	public String toString() {
		return "{\"receiving_no\":" + receiving_no + ", \"receiving_date\":" + receiving_date
				+ ", \"receiving_state_flag\":" + receiving_state_flag + ", \"receivingLines\":" + receivingLines + "}";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		ReceivingInfo other = (ReceivingInfo) obj;
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
	public Date getReceiving_date() {
		return receiving_date;
	}
	public void setReceiving_date(Date receiving_date) {
		this.receiving_date = receiving_date;
	}
	public String getReceiving_state_flag() {
		return receiving_state_flag;
	}
	public void setReceiving_state_flag(String receiving_state_flag) {
		this.receiving_state_flag = receiving_state_flag;
	}
	public List<ReceivingLine> getReceivingLines() {
		return receivingLines;
	}
	public void setReceivingLines(List<ReceivingLine> receivingLines) {
		this.receivingLines = receivingLines;
	}
	

}
