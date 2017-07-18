package com.example.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightsController {

    @GetMapping(value={"/flight", "/flight/"})
    public Flight getIndividualFlight() {
        Flight.Ticket.Person passenger1 = new Flight.Ticket.Person("Steve", "Austin");

        Flight.Ticket ticket1 = new Flight.Ticket(passenger1, 200);

        Flight.Ticket[] ticketsArr = new Flight.Ticket[]{ticket1};
        Flight flight = new Flight(LocalDateTime.of(2017, 4, 21, 14, 34), Arrays.asList(ticketsArr));

        return flight;
    }

    static class Flight {
        private LocalDateTime departs;
        private List<Ticket> tickets;

        @JsonCreator
        Flight(
                @JsonProperty("departs") LocalDateTime departs,
                @JsonProperty("tickets") List<Ticket> tickets
        ) {
            this.departs = departs;
            this.tickets = tickets;
        }

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        public LocalDateTime getDeparts() {
            return departs;
        }

        public void setDeparts(LocalDateTime departs) {
            this.departs = departs;
        }

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
                    @JsonProperty("passenger") Person passenger,
                    @JsonProperty("price") int price
            ) {
                this.passenger = passenger;
                this.price = price;
            }

            public Person getPassenger() {
                return passenger;
            }

            public void setPassenger(Person passenger) {
                this.passenger = passenger;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            static class Person {
                private String firstName;
                private String lastName;

                @JsonCreator
                Person(
                        @JsonProperty("firstName") String firstName,
                        @JsonProperty("lastName") String lastName
                ) {
                    this.firstName = firstName;
                    this.lastName = lastName;
                }

                public String getFirstName() {
                    return firstName;
                }

                public void setFirstName(String firstName) {
                    this.firstName = firstName;
                }

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