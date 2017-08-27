package com.example.demo.flight;

import com.example.demo.helpers.JsonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightsController.class)
@AutoConfigureMockMvc(secure = false)
public class FlightsControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testAllFlightsEndpoint() throws Exception {
        this.mvc.perform(
                get("/flights")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.FirstName", is("Steve")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.LastName", is("Austin")))
                .andExpect(jsonPath("$[0].Tickets[0].Price", is(200)))
                .andExpect(jsonPath("$[1].Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[1].Tickets[0].Passenger.FirstName", is("Dwayne")))
                .andExpect(jsonPath("$[1].Tickets[0].Price", is(400)));
    }

    @Test
    public void testIndividualFlightEndpoint() throws Exception {
        this.mvc.perform(
                get("/flights/flight")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.Tickets[0].Passenger.FirstName", is("Steve")))
                .andExpect(jsonPath("$.Tickets[0].Passenger.LastName", is("Austin")))
                .andExpect(jsonPath("$.Tickets[0].Price", is(200)));
    }

    @Test
    public void testTotalEndpointStringLiteral() throws Exception {
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"tickets\": [ { \"passenger\": { \"firstName\": \"Steve\", \"lastName\": \"Austin\" }, \"price\": 330 }, { \"passenger\": { \"firstName\": \"Dwayne\", \"lastName\": \"Johnson\" }, \"price\": 225 } ] }");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(555)));
    }

    @Test
    public void testTotalEndpointMapper() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        HashMap<String, ArrayList<Ticket>> flight = new HashMap<String, ArrayList<Ticket>>(){
            {
                put("tickets", new ArrayList<Ticket> (Arrays.asList(
                        new Ticket(new Person("Steve", "Austin"), 330), new Ticket(new Person("Dwayne", "Johnson"), 225)
                )));
            }
        };

        String json = mapper.writeValueAsString(flight);

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(555)));
    }

    @Test
    public void testTotalEndpointFileFixture() throws Exception {
        JsonService jsonService = new JsonService();
        String json = jsonService.getJSON("/data.json");

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(555)));
    }

}
