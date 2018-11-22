package com.kc.dao;

import java.util.List;

import com.kc.vo.RestockingInfo;
import com.kc.vo.RestockingLine;

public interface RestockingDAO {
	List<RestockingInfo> selectAll();
	List<RestockingInfo> selectByProdFlag(String rsl_prod_state_flag);
	void insertRst(RestockingInfo info);
	//void insertInfo(RestockingInfo info);
	//void insertLine(RestockingInfo info);
	void updateFlagDc(RestockingLine line);

}
