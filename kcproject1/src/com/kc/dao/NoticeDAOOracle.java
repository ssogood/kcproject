package com.kc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kc.vo.Notice;


@Repository("noticeDAO")
public class NoticeDAOOracle implements NoticeDAO {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Notice> selectAll() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Notice> all = new ArrayList<>();
		try {
			all = sqlSession.selectList("NoticeMapper.selectAll");
			
		}finally {
			sqlSession.close();
		}
		return all;
	}

	@Override
	public List<Notice> selectAll(int currentPage, int cntPerPage) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Notice> allPage = new ArrayList<>();
		try {
			Map<String, Integer> map = new HashMap<>();
			map.put("start", (currentPage-1)*cntPerPage+1);
			map.put("end", currentPage*cntPerPage);
			
			allPage = sqlSession.selectList("NoticeMapper.selectPage",map);
			
		}finally {
			sqlSession.close();
		}
		return allPage;
	}
	
	@Override
	public Notice selectByNo(int text_no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Notice notice = new Notice();
		try {
			notice = sqlSession.selectOne("NoticeMapper.selectByNo",text_no);
		}finally {
			sqlSession.close();
		}
		
		return notice;
	}

	@Override
	public int getTotalCount() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int totalCount;
		try {
			totalCount = sqlSession.selectOne("NoticeMapper.getTotalCount");
		}finally {
			sqlSession.close();
		}
		return 0;
	}

	@Override
	public void insert(Notice notice) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			sqlSession.update("NoticeMapper.insert", notice);
			sqlSession.commit();
		}catch(Exception e){
			sqlSession.rollback();
		}finally{
			sqlSession.close();
		}
		
	}
	
	@Override
	public void update(Notice notice) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			System.out.println(notice);
			sqlSession.update("NoticeMapper.update", notice);
			sqlSession.commit();
		}catch(Exception e){
			sqlSession.rollback();
			System.out.println("에러가 떴다면");
		}finally{
			sqlSession.close();
		}

	}

	@Override
	public void delete(int text_no) {
SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			sqlSession.update("NoticeMapper.delete", text_no);
			sqlSession.commit();
		}catch(Exception e){
			sqlSession.rollback();
		}finally{
			sqlSession.close();
		}

	}

}
