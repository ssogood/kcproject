package com.kc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import com.kc.vo.BaljuInfo;
import com.kc.vo.BaljuLine;
import com.kc.vo.ForwardingInfo;
import com.kc.vo.ForwardingLine;


@Repository("forwardingDAO")
public class ForwardingDAOOracle implements ForwardingDAO {
	@Autowired
	private BaljuDAO baljuDAO;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSource dataSource;
	
	
	@Override
	public List<ForwardingInfo> selectAll() {
		List<ForwardingInfo> all = new ArrayList<>();
		SqlSession ss = sqlSessionFactory.openSession();
		try {
			all = ss.selectList("ForwardingMapper.selectAll");
			return all;
		}finally {
			ss.close();
		}
	}
	//done
	@Override
	public List<ForwardingInfo> selectBybaljuNo(int balju_no) {
		List<ForwardingInfo> list = new ArrayList<>();
		SqlSession ss = sqlSessionFactory.openSession();
		try {
			list = ss.selectList("ForwardingMapper.selectBybaljuNo", balju_no);
			return list;
		}finally {
			ss.close();
		}				
	}
	//done
	@Override
	public List<ForwardingInfo> selectByforwardingNo(int forwarding_no) {
		List<ForwardingInfo> list = new ArrayList<>();
		SqlSession ss = sqlSessionFactory.openSession();
		try {
			list = ss.selectList("ForwardingMapper.selectByforwardingNo", forwarding_no);
			return list;
		}finally {
			ss.close();
		}				
	}

	//todo
	@Override
	public List<ForwardingInfo> selectByDate(Date forwarding_date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ForwardingInfo> selectByBranch(String branch_code) {
		List<ForwardingInfo> list = new ArrayList<>();
		SqlSession ss = sqlSessionFactory.openSession();
		try {
			list = ss.selectList("ForwardingMapper.selectByBranch", branch_code);
			return list;
		}finally {
			ss.close();
		}
	
	}

	@Override
	public List<ForwardingInfo> selectByState(String forwarding_state_flag) {
		List<ForwardingInfo> list = new ArrayList<>();
		SqlSession ss = sqlSessionFactory.openSession();
		try {
			list = ss.selectList("ForwardingMapper.selectByState", forwarding_state_flag);
			return list;
		}finally {
			ss.close();
		}
	}

	@Override
	public void insertForwarding(ForwardingInfo info) {
		System.out.println("test: insertForwarding, info.balju_no=" + info.getBalju_no() + ", info.branch_code=" + info.getBranch().getBranch_code());
		SqlSession ss = sqlSessionFactory.openSession(ExecutorType.BATCH);		
		try {
			insertInfo(ss, info);
			insertLine(ss, info);
			ss.commit();
		}catch(Exception e) {
			ss.rollback();
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());		
		}finally{
			ss.commit();
			ss.close();
		}
	}

	public void insertInfo(SqlSession ss, ForwardingInfo info) throws SQLException  {
	
		String branch_code = info.getBranch().getBranch_code();
		int balju_no = info.getBalju_no();			
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("branch_code", branch_code);
		map.put("balju_no", balju_no);
		ss.insert("ForwardingMapper.insertForwardingInfo",map);
		
	}

	public void insertLine(SqlSession ss, ForwardingInfo info) throws SQLException{
		int baljuno= info.getBalju_no();
		String branch_code = info.getBranch().getBranch_code();
		BaljuInfo  baljuInfo = baljuDAO.selectByNo2(branch_code, baljuno);
		List<ForwardingLine> forwardLines= new ArrayList<>();//info.getForwardingLines();
	
		
		List<BaljuLine> baljuLines = baljuInfo.getBaljuLines();
		for(BaljuLine line: baljuLines) {				
			//Map<String, Object> map = new HashMap<String, Object>();
			//map.put("forwarding_quantity", line.getBalju_quantity());
			//map.put("forwarding_ingredient", line.getIngredient());
			//ss.insert("ForwardingMapper.insertForwardingLine", map);
			ss.insert("ForwardingMapper.insertForwardingLine", line);
		
		}
//		List<ForwardingLine> lines = info.getForwardingLines();		
//		try {
//			for(ForwardingLine fl : lines) {
//				int ingred_no = fl.getIngredient().getIngred_no();
//				int forwarding_quantity = fl.getForwarding_quantity();
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("ingred_no", ingred_no);
//				map.put("forwarding_quantity", forwarding_quantity);
//				ss.insert("ForwardingMapper.insertForwardingLine",map);
//			}
//		}catch(Exception e) {
//			ss.rollback();
//			System.out.println(e.getMessage());				
//		}finally{				
//			ss.commit();
//			ss.close();				
//		}
	}
	
	@Override
	public void updateForwarding(int forwarding_no, String newflag){
		//List<ForwardingInfo> list = new ArrayList<>();
		SqlSession ss = sqlSessionFactory.openSession();
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("forwarding_state_flag", newflag);
			map.put("forwarding_no", forwarding_no);
			ss.update("ForwardingMapper.updateForwarding", map);			
		}catch(Exception e) {
			ss.rollback();
			System.out.println(e.getMessage());
		}finally {
			ss.commit();
			ss.close();
		}

		
	}
	
	@Override
	public int selectfno(String branch_code,int balju_no) {
		SqlSession ss = sqlSessionFactory.openSession();
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("branch_code", branch_code);
			map.put("balju_no", balju_no);
			int fno = ss.selectOne("ForwardingMapper.selectfno", map);
			return fno;
		}finally {
			ss.close();
		}
	}

}
