package com.kc.dao;

import java.util.List;

import com.kc.exception.NotFoundException;
import com.kc.vo.BranchInfo;

public interface BranchInfoDAO {
	List<BranchInfo> selectAll();
	BranchInfo selectByCode(String branch_code) throws NotFoundException;

}
