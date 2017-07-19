package com.example.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
public class FlightsController {
    // Instantiate passengers
    Flight.Ticket.Person passenger1 = new Flight.Ticket.Person("Steve", "Austin");
    Flight.Ticket.Person passenger2 = new Flight.Ticket.Person("Dwayne", null);

    // Instantiate tickets
    Flight.Ticket ticket1 = new Flight.Ticket(passenger1, 200);
    Flight.Ticket ticket2 = new Flight.Ticket(passenger2, 400);

    // Instantiate arrays for tickets
    Flight.Ticket[] ticketsArr1 = new Flight.Ticket[]{ticket1};
    Flight.Ticket[] ticketsArr2 = new Flight.Ticket[]{ticket2};

    //Instantiate flights
    Flight flight1 = new Flight(LocalDateTime.of(2017, 4, 21, 14, 34), Arrays.asList(ticketsArr1));
    Flight flight2 = new Flight(LocalDateTime.of(2017, 4, 21, 14, 34), Arrays.asList(ticketsArr2));

    //List of flights
    List<Flight> flights = Arrays.asList(flight1, flight2);

    @GetMapping(value={"/flights", "/flights/"})
    public List<Flight> getFlights() {
        return flights;
    }

    @GetMapping(value={"/flights/flight", "/flights/flight/"})
    public Flight getIndividualFlight() {
        return flights.get(0);
    }

    static class Flight {
        private LocalDateTime departs;
        private List<Ticket> tickets;

        @JsonCreator
        Flight(
                @JsonProperty("Departs") LocalDateTime departs,
                @JsonProperty("Tickets") List<Ticket> tickets
        ) {
            this.departs = departs;
            this.tickets = tickets;
        }

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        @JsonProperty("Departs")
        public LocalDateTime getDeparts() {
            return departs;
        }

        public void setDeparts(LocalDateTime departs) {
            this.departs = departs;
        }

        @JsonProperty("Tickets")
        public List<Ticket> getTickets() {
            return tickets;
        }

        public void setTickets(List<Ticket> tickets) {
            this.tickets = tickets;
        }

        static class Ticket {
            private Person passenger;
            private int price;

            @JsonCreator
            Ticket(
                    @JsonProperty("Passenger") Person passenger,
                    @JsonProperty("Price") int price
            ) {
                this.passenger = passenger;
                this.price = price;
            }

            @JsonProperty("Passenger")
            public Person getPassenger() {
                return passenger;
            }

            public void setPassenger(Person passenger) {
                this.passenger = passenger;
            }

            @JsonProperty("Price")
            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            @JsonInclude(JsonInclude.Include.NON_NULL)
            static class Person {
                private String firstName;
                private String lastName;

                @JsonCreator
                Person(
                        @JsonProperty("FirstName") String firstName,
                        @JsonProperty("LastName") String lastName
                ) {
                    this.firstName = firstName;
                    this.lastName = lastName;
                }

                @JsonProperty("FirstName")
                public String getFirstName() {
                    return firstName;
                }

                public void setFirstName(String firstName) {
                    this.firstName = firstName;
                }

                @JsonProperty("LastName")
                public String getLastName() {
                    return lastName;
                }

                public void setLastName(String lastName) {
                    this.lastName = lastName;
                }

            }

        }

    }

}