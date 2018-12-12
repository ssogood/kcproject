package com.kc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kc.dao.BranchInfoDAO;
import com.kc.vo.BranchInfo;

@Service
public class BranchInfoService {
	
	@Autowired
	private BranchInfoDAO dao;
	
	
	public List<BranchInfo> findAll(){
		List<BranchInfo> list = new ArrayList<>();
		list = dao.selectAll();
		return list;
	}
	
	public BranchInfo findByBC(String branch_code){
		BranchInfo brc = new BranchInfo();
		brc = dao.selectByBC(branch_code);
		return brc;
	}
	
	
	public void addBF(BranchInfo branchinfo) {
		dao.createBF(branchinfo);
	};
	
}
