package com.movietickets.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {
  
  @Test
  void testMovieCreation() {
    Movie movie = new Movie(1, "Inception", 148, "Sci-Fi");
    assertEquals(1, movie.getId());
    assertEquals("Inception", movie.getTitle());
    assertEquals(148, movie.getDuration());
    assertEquals("Sci-Fi", movie.getGenre());
  }

  @Test
  void testEmptyTitleThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> new Movie(3, "", 120, "Action"));
  }

  @Test
  void testNegativeDurationThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> new Movie(4, "The Dark Knight", -60, "Action"));
  }


}
