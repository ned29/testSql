package com.service;

import com.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    void remove(long id);

    void update(String active, String name, long depID, long id);

    Page<Employee> findAll(Pageable pageable);

    Employee findByID(long id);

    List<Employee> search(String param);
}