package com.kc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kc.dao.StatsDAO;
import com.kc.vo.Stats;

@Service
public class StatsService {
	@Autowired
	private StatsDAO dao;
	
	
	public List<Stats> findAllBranchStats(List<String>branch_code_list, String tocharDate , String period) {
		
		List<Stats> all = new ArrayList<>();
		
		all = dao.selectAllBranchsStats(branch_code_list, tocharDate, period);
		
		return all;
	}

	
	public List<Stats> findStatsAllBranchPeriod(List<String>branch_code_list, String tocharDate, String startDate, String endDate) {
		//Controller에서 넘어올 test값 
		//String startDate = "18/11/01";
		//String endDate = startDate;	//WHERE u.order_date BETWEEN '18/11/01' and LAST_DAY('18/11/01')   

		return null;
	}

	
	public List<Stats> findBranchStatsPerMonth(String startDate) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Stats> findStatsPerTime(String date) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
