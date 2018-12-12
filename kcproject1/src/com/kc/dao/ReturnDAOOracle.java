package com.kc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kc.exception.DataAccessException;
import com.kc.exception.NotFoundException;
import com.kc.vo.ReturnInfo;
import com.kc.vo.ReturnLine;

@Repository("returnDAO")
public class ReturnDAOOracle implements ReturnDAO {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<ReturnInfo> selectAll(String branch_code){
		List<ReturnInfo> all = new ArrayList<>();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("branch_code", branch_code);
			all = sqlSession.selectList("ReturnMapper.selectAll",map);
		}finally {
			sqlSession.close();
		}
		return all;
	}
	@Override
	public ReturnInfo selectRtnNo(String branch_code,int ireturn_no){
		ReturnInfo info = new ReturnInfo();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
		Map<String, Object> map = new HashMap<>();
		map.put("branch_code", branch_code);
		String return_no =  String.valueOf(ireturn_no);
		map.put("return_no", return_no);
		info = sqlSession.selectOne("ReturnMapper.selectRtnNo",map);
		}finally {
			sqlSession.close();
		}
		return info;
	}
	
	@Override
	public void insertReturn(String branch_code,ReturnInfo info){
		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
		try {
			insertInfo(sqlSession,branch_code);
			insertLine(sqlSession,branch_code,info);
			sqlSession.commit();
		} catch(SQLException e) {
			sqlSession.rollback();
			throw new DataAccessException(e.getMessage());
		}finally {
			sqlSession.close();
		}
	}
	
	public void insertInfo(SqlSession sqlSession,String branch_code)throws SQLException {
		Map<String, Object> map = new HashMap<>();
		map.put("branch_code", branch_code);
		sqlSession.insert("ReturnMapper.insertInfo",map);
	}
	
	public void insertLine(SqlSession sqlSession,String branch_code,ReturnInfo info)throws SQLException {
		List<ReturnLine> lines = info.getReturn_lines();
		Map<String, Object> map = new HashMap<>();
		System.out.println(branch_code);
		map.put("branch_code", branch_code);
		for(ReturnLine line : lines) {
			//return_quantity,ingred_no,rtl_prod_state_flag
			
			String return_quantity = String.valueOf(line.getReturn_quantity());
			String ingred_no = String.valueOf(line.getIngredient().getIngred_no());
			String rtl_prod_state_flag = line.getRtl_prod_state_flag();
			map.put("return_quantity", return_quantity);
			map.put("ingred_no", ingred_no);
			map.put("rtl_prod_state_flag", rtl_prod_state_flag);
			sqlSession.insert("ReturnMapper.insertLine",map);
		}
	}
	

	@Override
	public void updateRtnFlag(String branch_code,String newFlag, int return_no){
		SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
			Map<String, Object> map = new HashMap<>();
			map.put("branch_code",branch_code);
			map.put("return_state_flag", newFlag);
			
			map.put("return_no", String.valueOf(return_no));
			
			sqlSession.update("ReturnMapper.updateRtnFlag",map);
			
			sqlSession.commit();
			}finally {
				sqlSession.close();
			}
	}

	@Override
	public List<ReturnInfo> selectRtnFlag(String branch_code,String return_state_flag){
		List<ReturnInfo> flagList = new ArrayList<>();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
		Map<String, Object> map = new HashMap<>();
		map.put("branch_code", branch_code);
		map.put("return_state_flag", return_state_flag);
		flagList = sqlSession.selectList("ReturnMapper.selectRtnFlag",map);
		}finally {
			sqlSession.close();
		}
		return flagList;
	}



}
