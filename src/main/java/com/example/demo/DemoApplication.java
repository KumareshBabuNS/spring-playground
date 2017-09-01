package com.example.demo;

import com.example.demo.employee.Employee;
import com.example.demo.employee.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	@Profile("dev")
	public CommandLineRunner seedData(EmployeeRepository repo, PasswordEncoder passwordEncoder) {

		return (args) -> {

			repo.deleteAll();

			Employee employee = new Employee();
			employee.setName("Bo Diddly");
			employee.setSalary(24000);
			employee.setUsername("bodiddly");
			employee.setPassword(passwordEncoder.encode("bodiddles"));
			employee.setRole("EMPLOYEE");
			repo.save(employee);

			Employee boss = new Employee();
			boss.setName("Boss Hog");
			boss.setSalary(48000);
			boss.setUsername("bosshog");
			boss.setPassword(passwordEncoder.encode("hogboss"));
			boss.setRole("MANAGER");
			repo.save(boss);

		};

	}

}
