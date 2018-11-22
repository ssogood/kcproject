package com.kc.dao;

import java.util.List;

import com.kc.vo.IngredientInfo;

public interface IngredientDAO {
	List<IngredientInfo> selectAll();
	List<IngredientInfo> selectByName(String ingred_name);

}
