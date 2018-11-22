package com.kc.vo;

import java.util.Date;
import java.util.List;

public class OrderInfo {
	private int order_no;
	private Date order_date;
	private String order_flag;
	private List<OrderLine> orderLines;
	public OrderInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderInfo(int order_no, Date order_date, String order_flag, List<OrderLine> orderLines) {
		super();
		this.order_no = order_no;
		this.order_date = order_date;
		this.order_flag = order_flag;
		this.orderLines = orderLines;
	}
	@Override
	public String toString() {
		return "{\"order_no\":" + order_no + ", \"order_date\":" + order_date + ", \"order_flag\":" + order_flag
				+ ", \"orderLines\":" + orderLines + "}";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order_date == null) ? 0 : order_date.hashCode());
		result = prime * result + order_no;
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
		OrderInfo other = (OrderInfo) obj;
		if (order_date == null) {
			if (other.order_date != null)
				return false;
		} else if (!order_date.equals(other.order_date))
			return false;
		if (order_no != other.order_no)
			return false;
		return true;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getOrder_flag() {
		return order_flag;
	}
	public void setOrder_flag(String order_flag) {
		this.order_flag = order_flag;
	}
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}
	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	
	
}
