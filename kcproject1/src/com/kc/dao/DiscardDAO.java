package com.kc.dao;

import java.util.List;

import com.kc.vo.Discard;

public interface DiscardDAO {
	List<Discard> selectAll();
	List<Discard> selectByProdFlag(String dc_prod_state_flag);
	Discard selectByNo(int discard_no);
	
	void insertDc(Discard dc);
	void deleteDc(int discard_no);
	void updateDc(Discard dc);
	
}
