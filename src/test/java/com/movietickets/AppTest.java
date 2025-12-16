package com.movietickets;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

  @Test
  void testMainMethodExists() {
    // Test that main method can be called without errors
    assertDoesNotThrow(() -> {
      App.main(new String[] {});
    });
  }

  @Test
  void testStartApplicationOutput() {
    // Redirect System.out to capture output
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream));

    try {
      App.startApplication();

      // Check that it printed the startup message
      String output = outputStream.toString();
      assertTrue(output.contains("Starting Movie Ticket Booking System"));

    } finally {
      // Restore System.out
      System.setOut(originalOut);
    }
  }

  @Test
  void testAppClassCanBeInstantiated() {
    App app = new App();
    assertNotNull(app);
  }
}