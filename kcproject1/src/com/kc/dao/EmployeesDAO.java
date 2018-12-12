package com.kc.dao;

import com.kc.exception.NotFoundException;
import com.kc.vo.Employees;

public interface EmployeesDAO {
	Employees selectById(String employee_id) throws NotFoundException;
}
