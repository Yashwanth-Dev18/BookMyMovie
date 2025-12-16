package com.movietickets.service;

import com.movietickets.model.Booking;
import com.movietickets.model.Screening;
import com.movietickets.repository.BookingRepository;
import java.util.List;

public class BookingService {
  private final BookingRepository bookingRepository;
  private final PaymentService paymentService;

  public BookingService(BookingRepository bookingRepository, PaymentService paymentService) {
    this.bookingRepository = bookingRepository;
    this.paymentService = paymentService;
  }



  public Booking createBooking(Screening screening, List<String> seats, String customerEmail) {
    // Validate input
    if (screening == null) {
      throw new IllegalArgumentException("Screening cannot be null");
    }
    if (seats == null || seats.isEmpty()) {
      throw new IllegalArgumentException("At least one seat must be selected");
    }
    if (customerEmail == null || customerEmail.trim().isEmpty()) {
      throw new IllegalArgumentException("Customer email cannot be empty");
    }

    // Create booking object - this is where I'm doing Dynamic Object Creation!
    int nextId = 1; // In real app, get from repository
    Booking booking = new Booking(nextId, screening, seats, customerEmail);

    // Process payment
    double totalAmount = booking.calculateTotalPrice();
    boolean paymentSuccess = paymentService.processPayment(totalAmount, customerEmail);

    if (!paymentSuccess) {
      throw new RuntimeException("Payment failed");
    }

    // Save booking
    return bookingRepository.save(booking);
  }


  public List<Booking> getBookingsByCustomer(String email) {
    return bookingRepository.findByCustomerEmail(email);
  }


  public boolean cancelBooking(int bookingId) {
    return bookingRepository.delete(bookingId);
  }
}