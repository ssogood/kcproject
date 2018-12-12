package com.kc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kc.vo.Discard;

@Repository("discardDAO")
public class DiscardDAOOracle implements DiscardDAO {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Discard> selectAll() {
		List<Discard> all = new ArrayList<>();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			all = sqlSession.selectList("DiscardMapper.selectAll");
		}finally {
			sqlSession.close();
		}
		return all;
	}

	@Override
	public List<Discard> selectByProdFlag(String dc_prod_state_flag) {
		List<Discard> list = new ArrayList<>();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			list = sqlSession.selectList("DiscardMapper.selectByProdFlag",dc_prod_state_flag);					
		}finally {
			sqlSession.close();
		}
		
		return list;
	}
	
	@Override
	public Discard selectByNo(int discard_no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Discard discard = new Discard();
		try {
			discard = sqlSession.selectOne("DiscardMapper.selectByNo",discard_no);
		}finally {
			sqlSession.close();
		}
		return discard;
	}

	@Override
	public void insertDc(Discard dc) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			sqlSession.insert("DiscardMapper.insertDc",dc);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}

	@Override
	public void deleteDc(int discard_no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			sqlSession.delete("DiscardMapper.deleteDc",discard_no);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}
	@Override
	public void updateDc(Discard dc) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			sqlSession.update("DiscardMapper.updateDc",dc);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}
	}

	
	

}
