package com.example.mybatis.dao;

import org.apache.ibatis.annotations.Param;

import com.example.mybatis.bean.Employee;

public interface EmployeeMapper {
	public Employee getEmpByIdAndName(@Param("id") Integer id, @Param("lastName") String lastName);

	public Employee getEmpById(Integer id);

	public void addEmp(Employee employee);

	public void updateEmp(Employee employee);

	public Long deleteEmp(Integer id);
}
