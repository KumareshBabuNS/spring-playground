package com.example.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {
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

    @JsonSetter("passenger")
    public void setPassenger(Person passenger) {
        this.passenger = passenger;
    }

    @JsonProperty("Price")
    public int getPrice() {
        return price;
    }

    @JsonSetter("price")
    public void setPrice(int price) {
        this.price = price;
    }

}
