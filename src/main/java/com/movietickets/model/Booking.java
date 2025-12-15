package com.movietickets.model;

import java.util.List;

public class Booking {
  private final int id;
  private final Screening screening;
  private final List<String> seats;
  private final String customerEmail;

  public Booking(int id, Screening screening, List<String> seats, String customerEmail) {
    this.id = id;
    this.screening = screening;
    this.seats = seats;
    this.customerEmail = customerEmail;
  }

  public int getId() {
    return id;
  }

  public Screening getScreening() {
    return screening;
  }

  public List<String> getSeats() {
    return seats;
  }

  public String getCustomerEmail() {
    return customerEmail;
  }
  
}