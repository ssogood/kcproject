package com.kc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kc.vo.IngredientInfo;

@Repository("ingredientDAO")
public class IngredientDAOOracle implements IngredientDAO {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<IngredientInfo> selectAll() {
		List<IngredientInfo> all = new ArrayList<>();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try{
			all = sqlSession.selectList("IngredientMapper.selectAll");
		}finally {
			sqlSession.close();
		}
		
		return all;
	}

	@Override
	public List<IngredientInfo> selectByName(String ingred_name) {
		List<IngredientInfo> namelist = new ArrayList<>();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			namelist = sqlSession.selectList("IngredientMapper.selectByName",ingred_name);
			
		}finally {
			sqlSession.close();
		}
		return namelist;
	}
	
	@Override
	public List<IngredientInfo> selectByNo(int ingred_no){
		List<IngredientInfo> namelist = new ArrayList<>();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			namelist = sqlSession.selectList("IngredientMapper.selectByNo",ingred_no);
			
		}finally {
			sqlSession.close();
		}
		return namelist;
	}
	
	@Override
	public List<IngredientInfo> selectByNoName(IngredientInfo ingredient){
		List<IngredientInfo> nonamelist = new ArrayList<>();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			nonamelist = sqlSession.selectList("IngredientMapper.selectByNoName",ingredient);
			
		}finally {
			sqlSession.close();
		}
		return nonamelist;
	}

}
