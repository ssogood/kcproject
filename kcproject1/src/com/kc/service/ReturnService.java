package com.kc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kc.dao.ReturnDAO;
import com.kc.exception.NotFoundException;
import com.kc.vo.ReturnInfo;

@Service
public class ReturnService {
	@Autowired
	private ReturnDAO dao;
	
	public List<ReturnInfo> findAll(String branch_code){
		return dao.selectAll(branch_code);
	}
	
	public List<ReturnInfo> findRtnFlag(String branch_code,String return_flag) {
		return dao.selectRtnFlag(branch_code, return_flag);
	}
	
	public ReturnInfo findRtnNo(String branch_code,int return_no) {
		return dao.selectRtnNo(branch_code, return_no);
	}
	
	public void modifyRtnFlag(String branch_code,String newFlag, int return_no){
		dao.updateRtnFlag(branch_code, newFlag, return_no);
	}
	
	public void addReturn(String branch_code,ReturnInfo info){
		dao.insertReturn(branch_code, info);
	}
}
