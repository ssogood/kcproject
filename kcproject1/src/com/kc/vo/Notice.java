package com.kc.vo;

import java.util.Date;

public class Notice {
	private int text_no;
	private String text_title;
	private Date text_date;
	private Employees employee; //employee_id 사용
	private String text_content;
	private String text_pwd;
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Notice(int text_no, String text_title, Date text_date, Employees employee, String text_content,
			String text_pwd) {
		super();
		this.text_no = text_no;
		this.text_title = text_title;
		this.text_date = text_date;
		this.employee = employee;
		this.text_content = text_content;
		this.text_pwd = text_pwd;
	}
	@Override
	public String toString() {
		return "{\"text_no\":" + text_no + ", \"text_title\":" + text_title + ", \"text_date\":" + text_date + ", \"employee\":"
				+ employee + ", \"text_content\":" + text_content + ", \"text_pwd\":" + text_pwd + "}";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + text_no;
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
		Notice other = (Notice) obj;
		if (text_no != other.text_no)
			return false;
		return true;
	}
	public int getText_no() {
		return text_no;
	}
	public void setText_no(int text_no) {
		this.text_no = text_no;
	}
	public String getText_title() {
		return text_title;
	}
	public void setText_title(String text_title) {
		this.text_title = text_title;
	}
	public Date getText_date() {
		return text_date;
	}
	public void setText_date(Date text_date) {
		this.text_date = text_date;
	}
	public Employees getEmployee() {
		return employee;
	}
	public void setEmployee(Employees employee) {
		this.employee = employee;
	}
	public String getText_content() {
		return text_content;
	}
	public void setText_content(String text_content) {
		this.text_content = text_content;
	}
	public String getText_pwd() {
		return text_pwd;
	}
	public void setText_pwd(String text_pwd) {
		this.text_pwd = text_pwd;
	}
	
	

}
