package com.kc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kc.dao.NoticeDAO;
import com.kc.vo.Notice;

@Service
public class NoticeService {

	@Autowired
	private NoticeDAO dao;
	
	public List<Notice> findAll() {
		return dao.selectAll();
	}
	
	public List<Notice> findByPage(int currentPage,int cntPerPage){
		return dao.selectAll(currentPage, cntPerPage);
	}
	
	public Notice findByNo(int text_no) {
		return dao.selectByNo(text_no);
	}
	
	public void add(Notice notice) {
		dao.insert(notice);
	}
	
	public void modify(Notice notice) {
		dao.update(notice);
	}
	
	public void remove(int text_no) {
		dao.delete(text_no);
	}
}
