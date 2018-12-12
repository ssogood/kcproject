package com.kc.dao;

import java.util.List;

import com.kc.exception.NotFoundException;
import com.kc.vo.ReturnInfo;

public interface ReturnDAO {
	List<ReturnInfo> selectAll(String branch_code);
	ReturnInfo selectRtnNo(String branch_code,int return_no);
	void insertReturn(String branch_code,ReturnInfo info) ;
	//void insertInfo(ReturnInfo info);
	//void insertLine(ReturnInfo info);
	void updateRtnFlag(String branch_code,String newFlag, int return_no);
	List<ReturnInfo> selectRtnFlag(String branch_code,String return_flag);

}
