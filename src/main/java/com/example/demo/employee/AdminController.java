package com.example.demo.employee;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final EmployeeRepository repo;

    public AdminController(EmployeeRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/employees")
    @JsonView(Views.AdminView.class)
    public Iterable<Employee> list() {
        return repo.findAll();
    }

}
