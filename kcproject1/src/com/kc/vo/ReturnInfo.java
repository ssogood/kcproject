package com.kc.vo;

import java.util.Date;
import java.util.List;

public class ReturnInfo {
	private int return_no;
	private Date return_date;
	private String return_state_flag;
	private List<ReturnLine> return_lines;
	public ReturnInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReturnInfo(int return_no, Date return_date, String return_state_flag, List<ReturnLine> return_lines) {
		super();
		this.return_no = return_no;
		this.return_date = return_date;
		this.return_state_flag = return_state_flag;
		this.return_lines = return_lines;
	}
	@Override
	public String toString() {
		return "\"return_no\":" + return_no + ", \"return_date\":" + return_date + ", \"return_state_flag\":"
				+ return_state_flag + ", \"return_lines\":" + return_lines + "}";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + return_no;
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
		ReturnInfo other = (ReturnInfo) obj;
		if (return_no != other.return_no)
			return false;
		return true;
	}
	public int getReturn_no() {
		return return_no;
	}
	public void setReturn_no(int return_no) {
		this.return_no = return_no;
	}
	public Date getReturn_date() {
		return return_date;
	}
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	public String getReturn_state_flag() {
		return return_state_flag;
	}
	public void setReturn_state_flag(String return_state_flag) {
		this.return_state_flag = return_state_flag;
	}
	public List<ReturnLine> getReturn_lines() {
		return return_lines;
	}
	public void setReturn_lines(List<ReturnLine> return_lines) {
		this.return_lines = return_lines;
	}
	
	
	
}
