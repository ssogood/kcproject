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

import com.kc.exception.NotFoundException;
import com.kc.vo.BranchInfo;

@Repository("branchDAO")
public class BranchInfoDAOOracle implements BranchInfoDAO {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<BranchInfo> selectAll() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<BranchInfo> branchList = new ArrayList<>();
		try {
			branchList = sqlSession.selectList("BranchInfoMapper.selectAll");
		}finally {
			sqlSession.close();
		}
		return branchList;
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
	
	@Override
	public BranchInfo selectByCode(String branch_code) throws NotFoundException {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			BranchInfo branch = sqlSession.selectOne("BranchInfoMapper.selectByCode",branch_code);
			if(branch==null) {
				throw new NotFoundException("해당 고객을 찾을 수 없습니다.");
			}else {
				return branch;
			}
		}catch(Exception e) {
			throw new NotFoundException(e.getMessage());
		}finally {
			if(sqlSession !=null) {
				sqlSession.close();
			}
		}
	}

}
