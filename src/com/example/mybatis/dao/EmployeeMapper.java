package com.example.mybatis.dao;

import com.example.mybatis.bean.Employee;

public interface EmployeeMapper {
	public Employee getEmpById(Integer id);

	public void addEmp(Employee employee);

	public void updateEmp(Employee employee);

	public Long deleteEmp(Integer id);
}
