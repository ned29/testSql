package com.service.impl;

import com.model.Department;
import com.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

//    @Autowired
//    private CRUDRepository<Department> departmentRepository;

    @Override
    public List<Department> findAll() {
        return null;
    }
}
