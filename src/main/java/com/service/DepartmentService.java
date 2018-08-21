package com.service;

import com.model.Department;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {

    Iterable<Department> findAll();
}