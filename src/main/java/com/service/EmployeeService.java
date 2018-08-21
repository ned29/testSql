package com.service;

import com.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    void save(Employee employee);

    void remove(Employee employee);

    List<Employee> search(String name);
}