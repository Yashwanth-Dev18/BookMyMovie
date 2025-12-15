package com.movietickets.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TheaterTest {
  
  @Test
  void testTheatreCreation() {
    Theater theatre = new Theater(1, "Screen 1", 100);
    assertEquals(1, theatre.getId());
    assertEquals("Screen 1", theatre.getName());
    assertEquals(100, theatre.getCapacity());
  }

  @Test
  void testInvalidCapacityThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Theater(1, "Screen 1", 0);
    });
  }

}
