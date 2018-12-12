package com.kc.dao;

import java.util.List;

import com.kc.vo.Notice;

public interface NoticeDAO {
	List<Notice> selectAll();
	Notice selectByNo(int text_no);
	List<Notice> selectAll(int currentPage, int cntPerPage);
	int getTotalCount();
	void insert(Notice notice);
	void update(Notice notice);
	void delete(int text_no);
	

}
