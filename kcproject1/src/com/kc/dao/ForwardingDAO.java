package com.kc.dao;

import java.util.Date;
import java.util.List;
import com.kc.vo.ForwardingInfo;

public interface ForwardingDAO {
	List<ForwardingInfo> selectAll();
	
	List<ForwardingInfo> selectBybaljuNo(int balju_no);
	List<ForwardingInfo> selectByforwardingNo(int forwarding_no);
	List<ForwardingInfo> selectByBranch(String branch_code);	
	List<ForwardingInfo> selectByState(String forwarding_state_flag);
	List<ForwardingInfo> selectByDate(Date forwarding_date);	
	
	void updateForwarding(int forwarding_no, String newflag);
	
	void insertForwarding(ForwardingInfo info);
	int selectfno(String branch_code,int balju_no);
	//void insertInfo(ForwardingInfo info);
	//void insertLine(ForwardingInfo info);

}
