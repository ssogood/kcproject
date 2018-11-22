package com.kc.dao;

import java.util.List;

import com.kc.vo.Discard;

public interface DiscardDAO {
	List<Discard> selectAll();
	List<Discard> selectByProdFlag(String dc_prod_state_flag);
	void insertDc(Discard dc);

}
