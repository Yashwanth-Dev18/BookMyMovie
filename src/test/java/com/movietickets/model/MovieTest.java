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

}
