package com.service.impl;

import com.model.Employee;
import com.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
//
//    @Autowired
//    private CRUDRepository<Employee> employeeRepository;

    @Override
    public void save(Employee employee) {

    }

    @Override
    public void remove(Employee employee) {

    }

    @Override
    public List<Employee> search(String name) {
        return null;
    }
}