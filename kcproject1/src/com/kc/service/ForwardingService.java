package com.kc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kc.dao.ForwardingDAO;
import com.kc.vo.ForwardingInfo;

@Service
public class ForwardingService {
	@Autowired
	private ForwardingDAO dao;

	public List<ForwardingInfo> findAll() {
		return dao.selectAll();
	}

	public List<ForwardingInfo> findByBaljuNo(int balju_no) {
		return dao.selectBybaljuNo(balju_no);
	}
	
	public List<ForwardingInfo> findByForwardingNo(int forwarding_no) {
		return dao.selectByforwardingNo(forwarding_no);
	}

	public List<ForwardingInfo> findByDate(Date forwarding_date) {
		return dao.selectByDate(forwarding_date);
	}

	public List<ForwardingInfo> findByBranch(String branch_code) {
		return dao.selectByBranch(branch_code);
	}

	public List<ForwardingInfo> findByState(String forwarding_state_flag) {
		return dao.selectByState(forwarding_state_flag);
	}

	public void addForwarding(ForwardingInfo info) {
		dao.insertForwarding(info);
	}

	public void changeState(int forwarding_no, String newflag) {
		dao.updateForwarding(forwarding_no, newflag);
	}
	
	public int findNo(String branch_code,int balju_no) {
		return dao.selectfno(branch_code, balju_no);
	}
	
}
