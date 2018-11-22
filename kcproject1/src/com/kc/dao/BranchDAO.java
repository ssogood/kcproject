package com.kc.dao;

import java.util.List;

import com.kc.exception.NotFoundException;
import com.kc.vo.BranchInfo;

public interface BranchDAO {
	List<BranchInfo> selectAll();
	BranchInfo selectByCode(String branch_code) throws NotFoundException;

}
