package com.kc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kc.dao.RestockingDAO;
import com.kc.vo.RestockingInfo;
import com.kc.vo.RestockingLine;

@Service
public class RestockingService {
	@Autowired
	private RestockingDAO dao;
	
	public List<RestockingInfo> findAll(){
		return dao.selectAll();
	}
	
	public List<RestockingInfo> findByProdFlag(String rsl_prod_state_flag){
		return dao.selectByProdFlag(rsl_prod_state_flag);
	}
	
	public void addRestocking(RestockingInfo info) {
		dao.insertRst(info);
	}
	
	public void modifyFalgDc(RestockingLine line) {
		dao.updateFlagDc(line);
	}
}
