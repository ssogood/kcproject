package com.kc.dao;

import java.util.List;

import com.kc.vo.IngredientInfo;
import com.kc.exception.NotFoundException;

public interface IngredientDAO {
	List<IngredientInfo> selectAll();
	List<IngredientInfo> selectByName(String ingred_name);

	//balju
	List<IngredientInfo> selectAllbaljuingr();
	IngredientInfo selectByNobaljuingr(int ingred_no) throws NotFoundException ;
	List<IngredientInfo> selectByNamebaljuingr(String ingred_name);
	IngredientInfo selectByNo(int ingred_no);
}
