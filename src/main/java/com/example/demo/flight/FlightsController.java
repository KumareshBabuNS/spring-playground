package com.example.demo.flight;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class FlightsController {
    List<Flight> flights = new ArrayList<>(Arrays.asList(
        new Flight(LocalDateTime.of(2017, 4, 21, 14, 34), new ArrayList<>(Arrays.asList(
                new Ticket(new Person("Steve", "Austin"), 200))
        )),
        new Flight(LocalDateTime.of(2017, 4, 21, 14, 34), new ArrayList<>(Arrays.asList(
                new Ticket(new Person("Dwayne", null), 400))
        ))
    ));

    @GetMapping(value={"/flights", "/flights/"})
    public List<Flight> getFlights() {
        return flights;
    }

    @GetMapping(value={"/flights/flight", "/flights/flight/"})
    public Flight getIndividualFlight() {
        return flights.get(0);
    }

    @PostMapping(value={"/flights/tickets/total", "/flights/tickets/total/"})
    public String postTicketTotal(@RequestBody Flight flight) {
        return FlightsService.calcTicketTotal(flight);
    }

}