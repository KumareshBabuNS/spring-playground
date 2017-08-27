package com.example.demo.flight;

import com.fasterxml.jackson.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Flight {
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

    @JsonSetter("departs")
    public void setDeparts(LocalDateTime departs) {
        this.departs = departs;
    }

    @JsonProperty("Tickets")
    public List<Ticket> getTickets() {
        return tickets;
    }

    @JsonSetter("tickets")
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

}
