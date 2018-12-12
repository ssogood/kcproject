package com.kc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kc.dao.BranchInfoDAO;
import com.kc.exception.NotFoundException;
import com.kc.vo.BranchInfo;

@Service
public class BranchInfoService {

	@Autowired
	private BranchInfoDAO dao;
	
	public String login(String branch_code, String branch_pwd) {
		String result = null;
		
		try {
			if(dao.selectByCode(branch_code).getBranch_pwd().equals(branch_pwd)) {
				result = "ok";
			}else {
				result = "error";
			}
		} catch (NotFoundException e) {
			result = "error";
		}
		return result;
	}
	
	public BranchInfo findByBC(String branch_code){
		BranchInfo brc = new BranchInfo();
		brc = dao.selectByBC(branch_code);
		return brc;
	}
	
	
	public void addBF(BranchInfo branchinfo) {
		dao.createBF(branchinfo);
	};
	
	public List<BranchInfo> findAll(){
		return dao.selectAll();
	}
}
