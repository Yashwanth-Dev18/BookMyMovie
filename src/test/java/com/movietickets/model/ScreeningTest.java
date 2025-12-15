package com.movietickets.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class ScreeningTest {

  @Test
  void testScreeningCreation() {

    Movie movie = new Movie(1, "Inception", 148, "Sci-Fi");
    Theater theater = new Theater(1, "Screen 1", 100);
    LocalDateTime showTime = LocalDateTime.now().plusDays(1);

    Screening screening = new Screening(1, movie, theater, showTime);

    assertEquals(1, screening.getId());
  }
}