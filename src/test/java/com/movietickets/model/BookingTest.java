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

  @Test
  void testBookingWithInvalidEmailThrowsException() {
    Movie movie = new Movie(1, "Inception", 148, "Sci-Fi");
    Theater theater = new Theater(1, "Screen 1", 100);
    LocalDateTime showTime = LocalDateTime.now().plusDays(1);
    Screening screening = new Screening(1, movie, theater, showTime);

    List<String> seats = Arrays.asList("A1", "A2");

    // Test empty email
    assertThrows(IllegalArgumentException.class, () -> {
      new Booking(1, screening, seats, "");
    });

    // Test null email
    assertThrows(IllegalArgumentException.class, () -> {
      new Booking(1, screening, seats, null);
    });
  }

  @Test
  void testCalculateTotalPrice() {
    Movie movie = new Movie(1, "Inception", 148, "Sci-Fi");
    Theater theater = new Theater(1, "Screen 1", 100);
    LocalDateTime showTime = LocalDateTime.now().plusDays(1);
    Screening screening = new Screening(1, movie, theater, showTime);

    List<String> seats = Arrays.asList("A1", "A2", "A3"); // 3 seats

    Booking booking = new Booking(1, screening, seats, "john@email.com");

    // Price should be: 3 seats * 100 SEK each = 300 SEK
    assertEquals(300, booking.calculateTotalPrice());
  }

  @Test
  void testBookingEqualsAndHashCode() {
    Movie movie = new Movie(1, "Inception", 148, "Sci-Fi");
    Theater theater = new Theater(1, "Screen 1", 100);
    LocalDateTime showTime = LocalDateTime.now().plusDays(1);
    Screening screening1 = new Screening(1, movie, theater, showTime);
    Screening screening2 = new Screening(2, movie, theater, showTime.plusDays(1));

    List<String> seats = Arrays.asList("A1", "A2");

    Booking booking1 = new Booking(1, screening1, seats, "john@email.com");
    Booking booking2 = new Booking(1, screening2, seats, "different@email.com");
    Booking booking3 = new Booking(2, screening1, seats, "john@email.com");

    assertEquals(booking1, booking2); // Same ID
    assertNotEquals(booking1, booking3); // Different ID
    assertEquals(booking1.hashCode(), booking2.hashCode());
  }

}