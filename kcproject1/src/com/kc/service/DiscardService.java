package com.kc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kc.dao.DiscardDAO;
import com.kc.vo.Discard;

@Service
public class DiscardService {
	@Autowired
	private DiscardDAO dao;
	
	public List<Discard> findAll(){
		return dao.selectAll();		
	}
	
	public List<Discard> findByProdFlag(String dc_prod_state_flag){
		return dao.selectByProdFlag(dc_prod_state_flag);
	}
	
	public void addDiscard(Discard dc) {
		dao.insertDc(dc);
	}
	
}
