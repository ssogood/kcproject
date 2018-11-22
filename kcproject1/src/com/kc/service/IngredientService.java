package com.kc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kc.dao.IngredientDAO;
import com.kc.vo.IngredientInfo;

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

}
