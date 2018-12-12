package com.kc.dao;

import java.util.List;

import com.kc.vo.BranchInfo;

public interface BranchInfoDAO {
	
	public List<BranchInfo> selectAll();
	
	public BranchInfo selectByBC(String branch_code);
	
	public void createBF(BranchInfo branchinfo);
	
}
