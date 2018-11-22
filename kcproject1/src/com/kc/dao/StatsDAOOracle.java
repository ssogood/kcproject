package com.kc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kc.vo.BranchInfo;
import com.kc.vo.Stats;

@Repository("statsDAO")
public class StatsDAOOracle implements StatsDAO {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSource dataSource;

	@Override
	public List<Stats> selectAllBranchsStats(List<String>branch_code_list, String tocharDate , String period) {
		
		
		
		Map map = new HashMap();
		map.put("branch_code_list", branch_code_list);
		map.put("tocharDate", tocharDate);
		map.put("period", period);
		
		System.out.println("selectAllBrancsStats : "+map);
		
		//map.put("startDate", startDate);
		//map.put("endDate", endDate);
		
		
		List<Stats> all = new ArrayList<>();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
		
			all = sqlSession.selectList("StatsMapper.selectAllBranchsStats", map);
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		finally {
			sqlSession.close();
		}
		
		return all;
	}

	@Override
	public List<Stats> selectStatsAllBranchsPeriod(List<String>branch_code_list,String tocharDate, String startDate, String endDate) {
		
		
		
		
		
		return null;
	}

	@Override
	public List<Stats> selectBranchStatsByMonth(String startDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stats>	selectBranchStatsPerTime(String date){
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
