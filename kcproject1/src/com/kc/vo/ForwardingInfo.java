package com.kc.vo;

import java.util.Date;
import java.util.List;

public class ForwardingInfo {
	private int forwarding_no;
	private Date forwarding_date;
	private BranchInfo branch; //branch_no사용
	private String forwarding_state_flag;
	private List<ForwardingLine> forwardingLines;
	public ForwardingInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ForwardingInfo(int forwarding_no, Date forwarding_date, BranchInfo branch, String forwarding_state_flag,
			List<ForwardingLine> forwardingLines) {
		super();
		this.forwarding_no = forwarding_no;
		this.forwarding_date = forwarding_date;
		this.branch = branch;
		this.forwarding_state_flag = forwarding_state_flag;
		this.forwardingLines = forwardingLines;
	}

	@Override
	public String toString() {
		return "{\"forwarding_no\":" + forwarding_no + ", \"forwarding_date\":" + forwarding_date + ", \"branch\":"
				+ branch + ", \"forwarding_state_flag\":" + forwarding_state_flag + ", \"forwardingLines\":" + forwardingLines +"}";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + forwarding_no;
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
		ForwardingInfo other = (ForwardingInfo) obj;
		if (forwarding_no != other.forwarding_no)
			return false;
		return true;
	}
	public int getForwarding_no() {
		return forwarding_no;
	}
	public void setForwarding_no(int forwarding_no) {
		this.forwarding_no = forwarding_no;
	}
	public Date getForwarding_date() {
		return forwarding_date;
	}
	public void setForwarding_date(Date forwarding_date) {
		this.forwarding_date = forwarding_date;
	}
	public BranchInfo getBranch() {
		return branch;
	}
	public void setBranch(BranchInfo branch) {
		this.branch = branch;
	}
	public String getForwarding_state_flag() {
		return forwarding_state_flag;
	}
	public void setForwarding_state_flag(String forwarding_state_flag) {
		this.forwarding_state_flag = forwarding_state_flag;
	}
	public List<ForwardingLine> getForwardingLines() {
		return forwardingLines;
	}
	public void setForwardingLines(List<ForwardingLine> forwardingLines) {
		this.forwardingLines = forwardingLines;
	}
	
	
}
