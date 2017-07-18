package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightsController.class)
public class FlightsControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testFlightEndpoint() throws Exception {
        this.mvc.perform(
            get("/flights/flight")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.departs", is("2017-04-21 14:34")))
            .andExpect(jsonPath("$.tickets[0].passenger.firstName", is("Steve")))
            .andExpect(jsonPath("$.tickets[0].passenger.lastName", is("Austin")))
            .andExpect(jsonPath("$.tickets[0].price", is(200)));
    }

}
