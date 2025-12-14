package com.movietickets.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TheatreTest {
  
  @Test
  void testTheatreCreation() {
    Theatre theatre = new Theatre(1, "Screen 1", 100);
    assertEquals(1, theatre.getId());
    assertEquals("Screen 1", theatre.getName());
    assertEquals(100, theatre.getCapacity());
  }

}
