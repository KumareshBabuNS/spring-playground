package com.example.demo;

public class FlightsService {

    public static String calcTicketTotal(Flight flight) {
        int total = 0;

        for(Ticket ticket : flight.getTickets()) {
            total += ticket.getPrice();
        }

        return "{ \"result\": " + total + " }";
    }

}
