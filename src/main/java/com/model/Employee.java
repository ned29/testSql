package com.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tblEmployees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "empID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "empName")
    private String name;

    @Column(name = "empActive")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "dpID")
    private Department department;
}