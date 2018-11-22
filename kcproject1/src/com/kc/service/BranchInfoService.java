package com.kc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kc.dao.BranchInfoDAO;
import com.kc.vo.BranchInfo;
import com.kc.vo.Stats;

@Service
public class BranchInfoService {
	
	@Autowired
	private BranchInfoDAO dao;
	
	
	public List<BranchInfo> findAll(){
		
		List<BranchInfo> list = new ArrayList<>();
		list = dao.selectAll();
		
		
		
		return list;
	}
	

}
