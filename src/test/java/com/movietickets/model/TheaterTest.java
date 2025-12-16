package com.movietickets.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

  @Test
  void testEmptyNameThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Theater(1, "", 100);
    });
  }

  @Test
  void testEquals() {
    Theater theater1 = new Theater(1, "Screen 1", 100);
    Theater theater2 = new Theater(1, "Screen 2", 200);
    Theater theater3 = new Theater(2, "Screen 1", 100);
    Theater theater4 = theater1;

    // Same object
    assertTrue(theater1.equals(theater4));

    // Same ID, different details
    assertTrue(theater1.equals(theater2));

    // Different ID
    assertFalse(theater1.equals(theater3));

    // Null comparison
    assertFalse(theater1.equals(null));

    // Different class
    assertFalse(theater1.equals("not a theater"));
  }

}
