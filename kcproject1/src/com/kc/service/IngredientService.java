package com.kc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kc.dao.IngredientDAO;
import com.kc.vo.IngredientInfo;
import com.kc.exception.NotFoundException;

@Service
public class IngredientService {
	@Autowired
	private IngredientDAO dao;
	
	public List<IngredientInfo> findAll(){
		return dao.selectAll();
	}
	public List<IngredientInfo> findByName(String ingred_name){
		return dao.selectByName(ingred_name);
	}
	
	
	
	
	//지점에서 쓸거(부분정보)
	public List<IngredientInfo> findAllingr(){
		return dao.selectAllbaljuingr();
	}
	//지점에서 쓸거(부분정보)
	public IngredientInfo findByNoingr(int ingred_no) throws NotFoundException {
		return dao.selectByNobaljuingr(ingred_no);
	}
	//지점에서 쓸거(부분정보)
	public List<IngredientInfo> findByNameingr(String ingred_name) {
		return dao.selectByNamebaljuingr(ingred_name);
	}
	
	//1210 본사에서쓸거(모든정보)
	public IngredientInfo findByNo(int ingred_no) throws NotFoundException {
		return dao.selectByNo(ingred_no);
	}

}
