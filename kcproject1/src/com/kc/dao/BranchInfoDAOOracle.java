package com.kc.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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

	
	
	
	
	
	
	
	//1210
	@Override
	public BranchInfo selectByBC(String branch_code) {
		SqlSession ss = sqlSessionFactory.openSession();
		try {
			BranchInfo brc = new BranchInfo();
			brc = ss.selectOne("BranchInfoMapper.selectByBC", branch_code);
			return brc;
		}finally {
			ss.close();
		}
	}
	
	@Override
	public void createBF(BranchInfo branchinfo) {
		SqlSession ss = sqlSessionFactory.openSession();
		try {
			ss.update("BranchInfoMapper.createBF", branchinfo);
		}finally {
			ss.close();
		}
	}
	
	
	
	
	/*public static void main(String[] args) {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession ss = sqlSessionFactory.openSession();
			
			String branch_code = "S0001";
			BranchInfo brc = new BranchInfo();
			brc = ss.selectOne("BranchInfoMapper.selectByBC", branch_code);
			System.out.println(brc);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}
