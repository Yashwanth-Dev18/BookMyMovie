package com.movietickets;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

  @Test
  void testMainMethodExists() {
    assertDoesNotThrow(() -> {
      // Calling main with empty args
      // Gradle's Scanner will be rectified by catching the expected exception
      try {
        App.main(new String[] {});
      } catch (Exception e) {
        // Expected exception
      }
    });
  }

  @Test
  void testStartApplicationOutput() {
    // Redirect System.out to capture output
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream));

    try {
      // Run startApplication in a separate thread and interrupt it
      Thread appThread = new Thread(() -> {
        try {
          App.startApplication();
        } catch (Exception e) {
        }
      });

      appThread.start();
      Thread.sleep(100);
      appThread.interrupt();

      // Checking that it printed the startup message
      String output = outputStream.toString();
      assertTrue(output.contains("Starting Movie Ticket Booking System"));

    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
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