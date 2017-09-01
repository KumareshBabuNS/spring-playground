package com.example.demo;

import com.example.demo.employee.Employee;
import com.example.demo.employee.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	@Profile("dev")
	public CommandLineRunner seedData(EmployeeRepository employeeRepository) {
		return (args) -> {
			employeeRepository.deleteAll();
			Employee employee = new Employee();
			employee.setName("Bo Diddly");
			employee.setSalary(24000);
			employeeRepository.save(employee);
		};
	}

}
