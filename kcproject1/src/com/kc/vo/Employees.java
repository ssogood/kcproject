package com.kc.vo;

public class Employees {
	private String employee_id;
	private String employee_pwd;
	private String employee_name;
	private String department_name;
	public Employees() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employees(String employee_id, String employee_pwd, String employee_name, String department_name) {
		super();
		this.employee_id = employee_id;
		this.employee_pwd = employee_pwd;
		this.employee_name = employee_name;
		this.department_name = department_name;
	}
	@Override
	public String toString() {
		return "{\"employee_id\":" + employee_id + ", \"employee_pwd\":" + employee_pwd + ", \"employee_name\":"
				+ employee_name + ", \"department_name\":" + department_name + "}";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee_id == null) ? 0 : employee_id.hashCode());
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
		Employees other = (Employees) obj;
		if (employee_id == null) {
			if (other.employee_id != null)
				return false;
		} else if (!employee_id.equals(other.employee_id))
			return false;
		return true;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getEmployee_pwd() {
		return employee_pwd;
	}
	public void setEmployee_pwd(String employee_pwd) {
		this.employee_pwd = employee_pwd;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	
	

}
