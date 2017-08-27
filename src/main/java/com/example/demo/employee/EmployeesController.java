package com.example.demo.employee;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @GetMapping("")
    public String getEmployees() {
        return "This is a phony list of employees for now";
    }

}
