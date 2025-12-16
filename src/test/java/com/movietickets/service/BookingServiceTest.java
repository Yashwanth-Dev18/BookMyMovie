package com.movietickets.service;

import com.movietickets.model.Booking;
import com.movietickets.model.Movie;
import com.movietickets.model.Screening;
import com.movietickets.model.Theater;
import com.movietickets.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

  @Mock
  private BookingRepository bookingRepository;

  @Mock
  private PaymentService paymentService;

  private BookingService bookingService;
  private Screening testScreening;

  @BeforeEach
  void setUp() {
    bookingService = new BookingService(bookingRepository, paymentService);

    Movie movie = new Movie(1, "Inception", 148, "Sci-Fi");
    Theater theater = new Theater(1, "Screen 1", 100);
    LocalDateTime showTime = LocalDateTime.now().plusDays(1);
    testScreening = new Screening(1, movie, theater, showTime);
  }

  @Test
  void testCreateBookingSuccess() {
    List<String> seats = Arrays.asList("A1", "A2");
    String customerEmail = "john@email.com";

    // Mock payment service
    when(paymentService.processPayment(200.0, customerEmail))
        .thenReturn(true);

    // Mock repository save
    Booking expectedBooking = new Booking(1, testScreening, seats, customerEmail);
    when(bookingRepository.save(any(Booking.class)))
        .thenReturn(expectedBooking);

    Booking result = bookingService.createBooking(testScreening, seats, customerEmail);

    assertNotNull(result);
    assertEquals(customerEmail, result.getCustomerEmail());
    assertEquals(2, result.getSeats().size());

    // Verify interactions
    verify(paymentService).processPayment(200.0, customerEmail);
    verify(bookingRepository).save(any(Booking.class));
  }

  @Test
  void testCreateBookingWithPaymentFailure() {
    List<String> seats = Arrays.asList("A1", "A2");
    String customerEmail = "john@email.com";

    // Mock payment service to FAIL
    when(paymentService.processPayment(200.0, customerEmail))
        .thenReturn(false);

    // Should throw exception when payment fails
    RuntimeException exception = assertThrows(RuntimeException.class, () -> {
      bookingService.createBooking(testScreening, seats, customerEmail);
    });

    assertEquals("Payment failed", exception.getMessage());

    // Verify repository was NOT called
    verify(bookingRepository, never()).save(any(Booking.class));
  }

  @Test
  void testCreateBookingWithInvalidInput() {
    // Test null screening
    assertThrows(IllegalArgumentException.class, () -> {
      bookingService.createBooking(null, Arrays.asList("A1"), "john@email.com");
    });

    // Test empty seats
    assertThrows(IllegalArgumentException.class, () -> {
      bookingService.createBooking(testScreening, Arrays.asList(), "john@email.com");
    });

    // Test empty email
    assertThrows(IllegalArgumentException.class, () -> {
      bookingService.createBooking(testScreening, Arrays.asList("A1"), "");
    });
  }

  @Test
  void testGetBookingsByCustomer() {
    String customerEmail = "john@email.com";
    List<Booking> expectedBookings = Arrays.asList(
        new Booking(1, testScreening, Arrays.asList("A1"), customerEmail),
        new Booking(2, testScreening, Arrays.asList("B1"), customerEmail));

    // Mock repository method
    when(bookingRepository.findByCustomerEmail(customerEmail))
        .thenReturn(expectedBookings);

    List<Booking> result = bookingService.getBookingsByCustomer(customerEmail);

    assertEquals(2, result.size());
    verify(bookingRepository).findByCustomerEmail(customerEmail);
  }

  @Test
  void testCancelBookingSuccess() {
    int bookingId = 1;

    // Mock repository delete to return true
    when(bookingRepository.delete(bookingId))
        .thenReturn(true);

    boolean result = bookingService.cancelBooking(bookingId);

    assertTrue(result);
    verify(bookingRepository).delete(bookingId);
  }

  @Test
  void testCancelBookingNotFound() {
    int bookingId = 999;

    // Mock repository delete to return false
    when(bookingRepository.delete(bookingId))
        .thenReturn(false);

    boolean result = bookingService.cancelBooking(bookingId);

    assertFalse(result);
    verify(bookingRepository).delete(bookingId);
  }

}