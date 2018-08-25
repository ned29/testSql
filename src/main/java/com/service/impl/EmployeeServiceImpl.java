package com.service.impl;

import com.model.Employee;
import com.repository.EmployeeRepository;
import com.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public void remove(long id) {
        employeeRepository.delete(id);
    }

    @Override
    public void update(String active, String name, long depID, long id) {
        employeeRepository.update(active, name, depID, id);
    }

    @Override
    public Employee findByID(long id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public List<Employee> search(String param) {
        return employeeRepository.findByNameStartingWith(param);
    }
}