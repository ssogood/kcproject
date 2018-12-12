package com.kc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kc.dao.BaljuDAO;
import com.kc.vo.BaljuInfo;

@Service
public class BaljuService {
	@Autowired
	private BaljuDAO dao;	
	
	public List<Map<String,Object>> findGrandAll() {
		return dao.selectGrandAll();
	}
	
	public List<BaljuInfo> findAll(String branch_code) {
		return dao.selectAll(branch_code);
	}

	public List<BaljuInfo> findByNo(String branch_code, int baljuno) {
		return dao.selectByNo(branch_code, baljuno);
	}
	
	public BaljuInfo findByNo2(String branch_code, int baljuno) {
		return dao.selectByNo2(branch_code, baljuno);
	}
	
	public List<BaljuInfo> findByDate(String branch_code, Date date) {
		return dao.selectByDate(branch_code, date);
	}

	public List<BaljuInfo> findByState(String branch_code, String stateflag) {
		return dao.selectByState(branch_code, stateflag);
	}

	public List<BaljuInfo> findByIngred(String branch_code, int ingredno) {
		return dao.selectByIngred(branch_code, ingredno);
	}

	public void changeState(String branch_code, int balju_no, String newFlag) {
		dao.updateFlag(branch_code, balju_no, newFlag);
	}
	
	public void addBalju(String branch_code, BaljuInfo baljuinfo) {
		dao.insertBalju(branch_code, baljuinfo);
	}
	
	
}
