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
}