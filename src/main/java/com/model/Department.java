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
@Table(name = "tblDepartments")
public class Department {

    @Id
    @GeneratedValue
    @Column(name = "dpID")
    private long id;

    @Column(name = "dpName")
    private String name;
}