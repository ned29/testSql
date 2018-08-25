package com.repository;

import com.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Transactional
    @Modifying
    @Query("update Employee set active = :active, name = :name, dpID = :depID where empID = :id")
    void update(@Param("active") String active, @Param("name") String name, @Param("depID") long depID, @Param("id") long id);

    List<Employee> findByNameStartingWith(String param);
}