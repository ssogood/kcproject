package com.kc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kc.exception.NotFoundException;
import com.kc.vo.BranchInfo;
import com.kc.vo.IngredientInfo;

@Repository("branchDAO")
public class BranchInfoDAOOracle implements BranchInfoDAO {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<BranchInfo> selectAll() {
		// TODO Auto-generated method stub
		return null;
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
