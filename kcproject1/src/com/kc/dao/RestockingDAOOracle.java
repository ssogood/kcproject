package com.kc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kc.exception.DataAccessException;
import com.kc.vo.RestockingInfo;
import com.kc.vo.RestockingLine;
@Repository("restockingDAO")
public class RestockingDAOOracle implements RestockingDAO {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<RestockingInfo> selectAll() {
		List<RestockingInfo> all = new ArrayList<>();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			all = sqlSession.selectList("RestockingMapper.selectAll");
		}finally {
			sqlSession.close();
		}
		return all;
	}

	@Override
	public List<RestockingInfo> selectByProdFlag(String rsl_prod_state_flag) {
		List<RestockingInfo> list = new ArrayList<>();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			list = sqlSession.selectList("RestockingMapper.selectByProdFlag",rsl_prod_state_flag);
		}finally {
			sqlSession.close();
		}
		
		return list;
	}

	@Override
	public void insertRst(RestockingInfo info) {
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		
		try {
			insertInfo(sqlSession,info);
			insertLine(sqlSession,info);
			sqlSession.commit();
		}catch(SQLException e) {
			sqlSession.rollback();
			throw new DataAccessException(e.getMessage());
		}finally {
			sqlSession.close();
		}


	}

	public void insertInfo(SqlSession sqlSession,RestockingInfo info) throws SQLException  {
		sqlSession.insert("RestockingMapper.insertInfo",info);
	}
	public void insertLine(SqlSession sqlSession, RestockingInfo info) throws SQLException{
		//sqlSession.insert("RestockingMapper.insertLine",info);
		List<RestockingLine> lines = info.getRestocking_lines();
		for(RestockingLine line : lines) {
			sqlSession.insert("RestockingMapper.insertLine",line);
		}
		
	}
}
