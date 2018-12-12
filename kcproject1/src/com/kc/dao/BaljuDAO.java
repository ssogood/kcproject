package com.kc.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kc.vo.BaljuInfo;
import com.kc.vo.BranchInfo;
import com.kc.vo.IngredientInfo;

public interface BaljuDAO {
	
	List<Map<String,Object>> selectGrandAll();//본사grandall	
	List<BaljuInfo> selectGrandAll2(List<BranchInfo> branchlist);//
	
	List<BaljuInfo> selectAll(String branch_code);//	
	List<BaljuInfo> selectByNo(String branch_code, int baljuno);
	BaljuInfo selectByNo2(String branch_code, int baljuno);//1211
	List<BaljuInfo> selectByDate(String branch_code, Date date);
	List<BaljuInfo> selectByState(String branch_code, String stateflag);		
	List<BaljuInfo> selectByIngred(String branch_code, int ingredno);
	void insertBalju(String branch_code, BaljuInfo baljuinfo);
	void updateFlag(String branch_code, int balju_no, String newFlag);
	
	
}
