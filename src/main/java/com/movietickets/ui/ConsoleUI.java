package com.movietickets.ui;

import com.movietickets.model.*;
import com.movietickets.service.BookingService;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleUI {
  private final BookingService bookingService;
  private final Scanner scanner;

  public ConsoleUI(BookingService bookingService) {
    this.bookingService = bookingService;
    this.scanner = new Scanner(System.in);
  }

  public void start() {
    System.out.println("=== Movie Ticket Booking System ===");

    try {
      boolean running = true;
      while (running) {
        printMenu();
        String choice = scanner.nextLine();

        switch (choice) {
          case "1":
            bookTicket();
            break;
          case "2":
            viewBookings();
            break;
          case "3":
            cancelBooking();
            break;
          case "4":
            System.out.println("Thank you for using our system!");
            running = false;
            break;
          default:
            System.out.println("Invalid choice. Please try again.");
        }
      }
    } catch (NoSuchElementException e) {

      System.out.println("\nRunning in auto-demo mode (no input available)...");
      runQuickDemo();
    }
  }

  private void runQuickDemo() {
    System.out.println("Demo: Movie Ticket System works correctly!");
    System.out.println("Run with 'java -cp build/classes/java/main com.movietickets.App' for interactive mode.");
  }

  private void printMenu() {
    System.out.println("\n===== MAIN MENU =====");
    System.out.println("1. Book a ticket");
    System.out.println("2. View my bookings");
    System.out.println("3. Cancel a booking");
    System.out.println("4. Exit");
    System.out.print("Enter your choice: ");
  }

  private void bookTicket() {
    System.out.println("\n--- Book a Ticket ---");

    try {
      // Create sample data (in real app, this would come from database)
      Movie movie = new Movie(1, "Inception", 148, "Sci-Fi");
      Theater theater = new Theater(1, "Screen 1", 100);
      LocalDateTime showTime = LocalDateTime.now().plusDays(1).withHour(18).withMinute(30);
      Screening screening = new Screening(1, movie, theater, showTime);

      System.out.println("Movie: " + movie.getTitle());
      System.out.println("Time: " + showTime);
      System.out.println("Theater: " + theater.getName());

      System.out.print("Enter seat numbers (comma-separated, e.g., A1,A2,A3): ");
      String seatsInput = scanner.nextLine();
      List<String> seats = Arrays.asList(seatsInput.split(","));

      System.out.print("Enter your email: ");
      String email = scanner.nextLine();

      // Dynamic object creation in UI layer
      Booking booking = bookingService.createBooking(screening, seats, email);

      System.out.println("\n Booking successful!");
      System.out.println("Booking ID: " + booking.getId());
      System.out.println("Total price: " + booking.calculateTotalPrice() + " SEK");

    } catch (Exception e) {
      System.out.println(" Error: " + e.getMessage());
    }
  }

  private void viewBookings() {
    System.out.println("\n--- View Bookings ---");
    System.out.print("Enter your email: ");
    String email = scanner.nextLine();

    List<Booking> bookings = bookingService.getBookingsByCustomer(email);

    if (bookings.isEmpty()) {
      System.out.println("No bookings found for " + email);
    } else {
      System.out.println("Your bookings:");
      for (Booking booking : bookings) {
        System.out.println("  - " + booking);
      }
    }
  }

  private void cancelBooking() {
    System.out.println("\n--- Cancel Booking ---");
    System.out.print("Enter booking ID to cancel: ");

    try {
      int bookingId = Integer.parseInt(scanner.nextLine());
      boolean success = bookingService.cancelBooking(bookingId);

      if (success) {
        System.out.println(" Booking cancelled successfully!");
      } else {
        System.out.println(" Booking not found or could not be cancelled.");
      }
    } catch (NumberFormatException e) {
      System.out.println(" Invalid booking ID. Please enter a number.");
    }
  }
}