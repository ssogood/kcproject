package com.kc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kc.dao.EmployeesDAO;
import com.kc.exception.NotFoundException;

@Service
public class EmployeesService {

	@Autowired
	private EmployeesDAO dao;
	
	public String login(String employee_id, String employee_pwd) {
		String result = null;
		
		try {
			
			
			if(dao.selectById(employee_id).getEmployee_pwd().equals(employee_pwd)) {
				result = "ok";
				
			}else {
				result = "error";
			}
		} catch (NotFoundException e) {
			
			result = "error";
		}
		
		return result;
	}
}
