package com.kc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kc.vo.BranchInfo;

@Repository("branchinfoDAO")
public class BranchInfoDAOOracle implements BranchInfoDAO{
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<BranchInfo> selectAll() {
		
		List<BranchInfo> all = new ArrayList<>();
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
		
			all = sqlSession.selectList("BranchInfoMapper.selectAll");
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		finally {
			sqlSession.close();
		}
		
		return all;
		
	}

}
