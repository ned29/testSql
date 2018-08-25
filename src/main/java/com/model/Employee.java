package com.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Employees")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "empID")
    private long id;

    private String name;

    private String active;

    @ManyToOne
    @JoinColumn(name = "dpID")
    private Department department;
}