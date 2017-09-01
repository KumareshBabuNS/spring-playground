package com.example.demo.employee;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdminTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeRepository repo;

    @Before
    public void setup() {

        Employee employee = new Employee();
        employee.setName("Test Employee");
        employee.setSalary(45000);
        Employee[] employees = new Employee[]{employee};

        when(repo.findAll()).thenReturn(Arrays.asList(employees));

    }

    @Test
    @WithMockUser(roles = "MANAGER")
    public void testAdminEmployeesVisibilityAsManager() throws Exception {

        MockHttpServletRequestBuilder request = get("/admin/employees")
                .accept(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Test Employee")))
                .andExpect(jsonPath("$[0].salary", is(45000)));

    }

    @Test
    @WithMockUser(roles = "USER")
    public void testAdminEmployeesVisibilityAsUser() throws Exception {

        MockHttpServletRequestBuilder request = get("/admin/employees")
                .accept(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isForbidden());

    }

    @Test
    public void testAdminEmployeesVisibilityAsAnonymous() throws Exception {

        MockHttpServletRequestBuilder request = get("/admin/employees")
                .accept(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isUnauthorized());

    }

}
