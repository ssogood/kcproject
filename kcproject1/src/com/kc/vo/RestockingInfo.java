package com.kc.vo;

import java.util.Date;
import java.util.List;

public class RestockingInfo {
	private int restocking_no;
	private Date restocking_date;
	private BranchInfo branch; //branch_code사용
	private int return_no;
	private List<RestockingLine> restocking_lines;
	
	public RestockingInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public RestockingInfo(int restocking_no, Date restocking_date, BranchInfo branch, int return_no,
			List<RestockingLine> restocking_lines) {
		super();
		this.restocking_no = restocking_no;
		this.restocking_date = restocking_date;
		this.branch = branch;
		this.return_no = return_no;
		this.restocking_lines = restocking_lines;
	}


	@Override
	public String toString() {
		return "{\"restocking_no\":" + restocking_no + ", \"restocking_date\":" + restocking_date + ", \"branch\":"
				+ branch + ", \"return_no\":" + return_no + ", \"restocking_lines\":" + restocking_lines + "}";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + restocking_no;
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
		RestockingInfo other = (RestockingInfo) obj;
		if (restocking_no != other.restocking_no)
			return false;
		return true;
	}
	public int getRestocking_no() {
		return restocking_no;
	}
	public void setRestocking_no(int restocking_no) {
		this.restocking_no = restocking_no;
	}
	public Date getRestocking_date() {
		return restocking_date;
	}
	public void setRestocking_date(Date restocking_date) {
		this.restocking_date = restocking_date;
	}
	public BranchInfo getBranch() {
		return branch;
	}
	public void setBranch(BranchInfo branch) {
		this.branch = branch;
	}
	public int getReturn_no() {
		return return_no;
	}
	public void setReturn_no(int return_no) {
		this.return_no = return_no;
	}


	public List<RestockingLine> getRestocking_lines() {
		return restocking_lines;
	}


	public void setRestocking_lines(List<RestockingLine> restocking_lines) {
		this.restocking_lines = restocking_lines;
	}
	
	

}
