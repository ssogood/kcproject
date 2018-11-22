package com.kc.vo;

public class BranchInfo {
	private String branch_code;
	private String branch_name;
	private String branch_address;
	private String branch_pwd;
	private String branch_phone;
	private String owner_name;
	private String owner_phone;
	private String closed_day;
	public BranchInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BranchInfo(String branch_code, String branch_name, String branch_address, String branch_pwd,
			String branch_phone, String owner_name, String owner_phone, String closed_day) {
		super();
		this.branch_code = branch_code;
		this.branch_name = branch_name;
		this.branch_address = branch_address;
		this.branch_pwd = branch_pwd;
		this.branch_phone = branch_phone;
		this.owner_name = owner_name;
		this.owner_phone = owner_phone;
		this.closed_day = closed_day;
	}
	@Override
	public String toString() {
		return "{\"branch_code\":" + branch_code + ", \"branch_name\":" + branch_name + ", \"branch_address\":"
				+ branch_address + ", \"branch_pwd\":" + branch_pwd + ", \"branch_phone\":" + branch_phone + ", \"owner_name\":"
				+ owner_name + ", \"owner_phone\":" + owner_phone + ", \"closed_day\":" + closed_day + "}";
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branch_code == null) ? 0 : branch_code.hashCode());
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
		BranchInfo other = (BranchInfo) obj;
		if (branch_code == null) {
			if (other.branch_code != null)
				return false;
		} else if (!branch_code.equals(other.branch_code))
			return false;
		return true;
	}
	public String getBranch_code() {
		return branch_code;
	}
	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getBranch_address() {
		return branch_address;
	}
	public void setBranch_address(String branch_address) {
		this.branch_address = branch_address;
	}
	public String getBranch_pwd() {
		return branch_pwd;
	}
	public void setBranch_pwd(String branch_pwd) {
		this.branch_pwd = branch_pwd;
	}
	public String getBranch_phone() {
		return branch_phone;
	}
	public void setBranch_phone(String branch_phone) {
		this.branch_phone = branch_phone;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public String getOwner_phone() {
		return owner_phone;
	}
	public void setOwner_phone(String owner_phone) {
		this.owner_phone = owner_phone;
	}
	public String getClosed_day() {
		return closed_day;
	}
	public void setClosed_day(String closed_day) {
		this.closed_day = closed_day;
	}
	
	

}
