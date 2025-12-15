package com.movietickets.model;

import java.util.List;

public class Booking {
  private final int id;
  private final Screening screening;
  private final List<String> seats;
  private final String customerEmail;

  public Booking(int id, Screening screening, List<String> seats, String customerEmail) {

    validateInput(screening, seats, customerEmail);

    this.id = id;
    this.screening = screening;
    this.seats = seats;
    this.customerEmail = customerEmail;
  }

  private void validateInput(Screening screening, List<String> seats, String customerEmail) {
    if (screening == null) {
      throw new IllegalArgumentException("Screening cannot be null");
    }

    if (seats == null || seats.isEmpty()) {
      throw new IllegalArgumentException("Seats cannot be empty");
    }

    if (customerEmail == null || customerEmail.trim().isEmpty()) {
      throw new IllegalArgumentException("Customer email cannot be empty");
    }
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

  public double calculateTotalPrice() {
    double pricePerSeat = 100.0; // 100 SEK per seat
    return seats.size() * pricePerSeat;
  }

  @Override
  public String toString() {
    return String.format("Booking #%d: %s - Seats: %s - Customer: %s - Total: %.2f SEK",
        id,
        screening.getMovie().getTitle(),
        String.join(", ", seats),
        customerEmail,
        calculateTotalPrice());
  }

}