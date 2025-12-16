package com.movietickets;

import com.movietickets.repository.BookingRepository;
import com.movietickets.repository.InMemoryBookingRepository;
import com.movietickets.service.BookingService;
import com.movietickets.service.PaymentService;
import com.movietickets.ui.ConsoleUI;

public class App {
    public static void main(String[] args) {
        startApplication();
    }
    
    public static void startApplication() {
        System.out.println("Starting Movie Ticket Booking System...");
        
        // Dependency Injection setup
        BookingRepository bookingRepository = new InMemoryBookingRepository();
        PaymentService paymentService = new PaymentService();
        BookingService bookingService = new BookingService(bookingRepository, paymentService);
        
        // Create and start UI
        ConsoleUI ui = new ConsoleUI(bookingService);
        ui.start();
    }
    
    // Test helper method - doesn't start UI
    public static String getStartupMessage() {
        return "Starting Movie Ticket Booking System...";
    }
}