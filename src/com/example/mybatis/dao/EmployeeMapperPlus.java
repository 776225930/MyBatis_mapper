package com.example.mybatis.dao;

import com.example.mybatis.bean.Employee;

public interface EmployeeMapperPlus {
    public  Employee getEmpById(Integer id);
    public  Employee getEmpAndDept(Integer id);
}
