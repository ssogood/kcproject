package com.kc.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kc.exception.NotFoundException;
import com.kc.vo.BranchInfo;
import com.kc.vo.Employees;

@Repository("employeesDAO")
public class EmployeesDAOOracle implements EmployeesDAO {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	
	
	@Override
	public Employees selectById(String employee_id) throws NotFoundException {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			Employees employee = sqlSession.selectOne("EmployeesMapper.selectById",employee_id);
			if(employee==null) {
				throw new NotFoundException("해당 고객을 찾을 수 없습니다.");
			}else {
				return employee;
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
