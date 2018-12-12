package com.kc.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kc.exception.DataAccessException;
import com.kc.sql.MyConnection;
import com.kc.vo.BaljuInfo;
import com.kc.vo.BaljuLine;
import com.kc.vo.BranchInfo;
import com.kc.vo.IngredientCate;
import com.kc.vo.IngredientInfo;

@Repository("BaljuDAO")
public class BaljuDAOOracle implements BaljuDAO {


	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Autowired
	private DataSource dataSource;//???

	//done
	@Override
	public List<Map<String,Object>> selectGrandAll() {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			//duck
			List<BranchInfo> list_branchInfo = new ArrayList<>();
			BranchInfo bi = new BranchInfo();
			bi.setBranch_code("S0000");
			list_branchInfo.add(bi);
			bi = new BranchInfo();
			bi.setBranch_code("S0001");
			list_branchInfo.add(bi);
			bi = new BranchInfo();
			bi.setBranch_code("S0002");
			list_branchInfo.add(bi);
			bi = new BranchInfo();
			bi.setBranch_code("S0003");
			list_branchInfo.add(bi);
			bi = new BranchInfo();
			bi.setBranch_code("S0004");
			list_branchInfo.add(bi);

			//mybatis foreach에 필요한 key, value를 설정 
			//<foreach collection="list_branchInfo"
			HashMap<String, List<BranchInfo>> map = new HashMap<String, List<BranchInfo>>();
			map.put("list_branchInfo", list_branchInfo);//mybatis에서 반복하기 위해 맵에 넣어줌
			List<Map<String,Object>> list_result = sqlSession.selectList("BaljuMapper.tutorSelect4", map);
			return list_result;			
		} finally {
			sqlSession.close();
		}
	}

	//todo LinkedHashMap으로 해보기
	@Override
	public List<BaljuInfo> selectGrandAll2(List<BranchInfo> branchlist){
		return null;
	}
	
	//done
	@Override
	public List<BaljuInfo> selectAll(String branch_code) {
		SqlSession ss = sqlSessionFactory.openSession();
		try{
			Map<String, String> map = new HashMap<>();
			map.put("branch_code", branch_code);
			List<BaljuInfo> all = ss.selectList("BaljuMapper.selectAll",map);
			return all;
		}finally {
			ss.close();
		}
	}
	
	//done
	@Override
	public List<BaljuInfo> selectByNo(String branch_code, int baljuno) {
		List<BaljuInfo> list = new ArrayList<>();
		SqlSession ss = sqlSessionFactory.openSession();
		Map<String, Object> map = new HashMap<>();
		map.put("branch_code", branch_code);
		map.put("baljuno", baljuno);
		
		try{
			list = ss.selectList("BaljuMapper.selectByNo", map);
			return list;		
		}finally {
			ss.close();
		}
	}
	
	@Override
	public BaljuInfo selectByNo2(String branch_code, int baljuno) {
		BaljuInfo balinfo = new BaljuInfo();
		SqlSession ss = sqlSessionFactory.openSession();
		Map<String, Object> map = new HashMap<>();
		map.put("branch_code", branch_code);
		map.put("balju_no", baljuno);		
		try{
			balinfo = ss.selectOne("BaljuMapper.selectByNo2", map);
			return balinfo;		
		}finally {
			ss.close();
		}
	}	

	//done
	@Override
	public List<BaljuInfo> selectByState(String branch_code, String stateflag) {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			Map<String, String> map = new HashMap<>();
			map.put("branch_code", branch_code);
			map.put("balju_state_flag", stateflag);			
			List<BaljuInfo> selectbyState = sqlSession.selectList("BaljuMapper.selectByState",map);
			return selectbyState;
		} finally {
			sqlSession.close();
		}
	}

	//TODO:
	@Override
	public List<BaljuInfo> selectByDate(String branch_code, Date date) {
		SqlSession session = sqlSessionFactory.openSession();		
		try {
			List<BaljuInfo> result =
            session.getMapper(BaljuDAOOracle.class).selectByDate(branch_code, date);
			return result;
        } 
        finally{
            session.close();
        }
	}
		
	//TODO:
	@Override
	public List<BaljuInfo> selectByIngred(String branch_code, int ingredno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertBalju(String branch_code, BaljuInfo baljuinfo) {
		SqlSession ss = sqlSessionFactory.openSession(ExecutorType.BATCH);
		//TODO: branch_code 받아오는걸로
		
		try {
			insertInfo(ss, branch_code);
			insertLine(ss, branch_code, baljuinfo);
			ss.commit();
		}catch(Exception e) {
			ss.rollback();
			throw new DataAccessException(e.getMessage());
		}finally{
			ss.close();
		}
	}

	private void insertInfo(SqlSession sqlSession, String branch_code) throws SQLException {
		System.out.println("branch_code:"+branch_code);
		Map<String,Object> map = new HashMap<>();
		map.put("branch_code", branch_code);
		sqlSession.insert("BaljuMapper.insertBaljuInfo", map);	
		System.out.println("info");
	}

	private void insertLine(SqlSession sqlSession, String branch_code, BaljuInfo info) throws SQLException {
		List<BaljuLine>lines = info.getBaljuLines();
		Map<String, Object> map = new HashMap<>();		

		map.put("branch_code", branch_code);
		System.out.println("oracle들어옴");
		for(BaljuLine line : lines) {
			//int balju_no = line.getBalju_no();
			int ingred_no = line.getIngredient().getIngred_no();
			int balju_quantity = line.getBalju_quantity();
			//map.put("balju_no", balju_no);
			map.put("ingred_no", ingred_no);			
			map.put("balju_quantity", balju_quantity);
			sqlSession.insert("BaljuMapper.insertBaljuLine", map);
			System.out.println("for들어옴");
		}
	}

	//done
	@Override
	public void updateFlag(String branch_code, int balju_no, String newFlag) {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			Map<String, String> map = new HashMap<>();
			map.put("branch_code", branch_code);
			map.put("balju_state_flag", newFlag);
			map.put("balju_no", String.valueOf(balju_no));
			sqlSession.update("BaljuMapper.updateFlag",map);
			sqlSession.commit();
		}finally {
			sqlSession.close();
		}

	}


}














