package com.movietickets.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

  @Test
  void testBookingCreation() {
    // Setup dependencies
    Movie movie = new Movie(1, "Inception", 148, "Sci-Fi");
    Theater theater = new Theater(1, "Screen 1", 100);
    LocalDateTime showTime = LocalDateTime.now().plusDays(1);
    Screening screening = new Screening(1, movie, theater, showTime);

    List<String> seats = Arrays.asList("A1", "A2", "A3");

    Booking booking = new Booking(1, screening, seats, "john@email.com");

    assertEquals(1, booking.getId());
    assertEquals(screening, booking.getScreening());
    assertEquals(seats, booking.getSeats());
    assertEquals("john@email.com", booking.getCustomerEmail());
  }

  @Test
  void testBookingWithNullScreeningThrowsException() {
    Screening screening = null;
    List<String> seats = Arrays.asList("A1", "A2");

    assertThrows(IllegalArgumentException.class, () -> {
      new Booking(1, screening, seats, "john@email.com");
    });
  }

  @Test
  void testBookingWithEmptySeatsThrowsException() {
    Movie movie = new Movie(1, "Inception", 148, "Sci-Fi");
    Theater theater = new Theater(1, "Screen 1", 100);
    LocalDateTime showTime = LocalDateTime.now().plusDays(1);
    Screening screening = new Screening(1, movie, theater, showTime);

    List<String> emptySeats = Arrays.asList();

    assertThrows(IllegalArgumentException.class, () -> {
      new Booking(1, screening, emptySeats, "john@email.com");
    });
  }
}