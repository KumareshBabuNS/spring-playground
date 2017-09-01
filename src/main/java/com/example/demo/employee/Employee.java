package com.example.demo.employee;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @JsonView(Views.UserView.class)
    private Long id;

    @Getter
    @Setter
    @JsonView(Views.UserView.class)
    private String name;

    @Getter
    @Setter
    @JsonView(Views.AdminView.class)
    private int salary;

    @Getter
    @Setter
    @JsonView(Views.UserView.class)
    private Long managerId;

}
