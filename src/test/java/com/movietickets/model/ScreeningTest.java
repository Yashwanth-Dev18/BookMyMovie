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
    assertEquals(movie, screening.getMovie());
    assertEquals(theater, screening.getTheater());
    assertEquals(showTime, screening.getShowTime());
  }

  @Test
  void testScreeningWithNullMovieThrowsException() {
    Movie movie = null;
    Theater theater = new Theater(1, "Screen 1", 100);
    LocalDateTime showTime = LocalDateTime.now().plusDays(1);

    assertThrows(IllegalArgumentException.class, () -> {
      new Screening(1, movie, theater, showTime);
    });
  }

  @Test
  void testScreeningWithNullTheaterThrowsException() {
    Movie movie = new Movie(1, "Inception", 148, "Sci-Fi");
    Theater theater = null;
    LocalDateTime showTime = LocalDateTime.now().plusDays(1);

    assertThrows(IllegalArgumentException.class, () -> {
      new Screening(1, movie, theater, showTime);
    });
  }

  @Test
  void testScreeningWithNullShowTimeThrowsException() {
    Movie movie = new Movie(1, "Inception", 148, "Sci-Fi");
    Theater theater = new Theater(1, "Screen 1", 100);
    LocalDateTime showTime = null;

    // Should throw exception
    assertThrows(IllegalArgumentException.class, () -> {
      new Screening(1, movie, theater, showTime);
    });
  }

  @Test
  void testScreeningToString() {
    Movie movie = new Movie(1, "Inception", 148, "Sci-Fi");
    Theater theater = new Theater(1, "Screen 1", 100);
    LocalDateTime showTime = LocalDateTime.of(2024, 1, 15, 18, 30);

    Screening screening = new Screening(1, movie, theater, showTime);
    String result = screening.toString();

    // Should contain movie title
    assertTrue(result.contains("Inception"));
    // Should contain theater name
    assertTrue(result.contains("Screen 1"));
    // Should contain time
    assertTrue(result.contains("18:30"));
  }

  @Test
  void testScreeningEqualsAndHashCode() {
    Movie movie1 = new Movie(1, "Movie 1", 120, "Action");
    Movie movie2 = new Movie(2, "Movie 2", 90, "Comedy");
    Theater theater1 = new Theater(1, "Screen 1", 100);
    Theater theater2 = new Theater(2, "Screen 2", 50);
    LocalDateTime time = LocalDateTime.now().plusDays(1);

    Screening screening1 = new Screening(1, movie1, theater1, time);
    Screening screening2 = new Screening(1, movie2, theater2, time.plusDays(1));
    Screening screening3 = new Screening(2, movie1, theater1, time);

    // Same ID should be equal
    assertEquals(screening1, screening2);
    // Different ID should not be equal
    assertNotEquals(screening1, screening3);
    // Same ID should have same hash code
    assertEquals(screening1.hashCode(), screening2.hashCode());
  }

  @Test
  void testCreateScreeningCopy() {
    Movie movie = new Movie(1, "Inception", 148, "Sci-Fi");
    Theater theater = new Theater(1, "Screen 1", 100);
    LocalDateTime originalTime = LocalDateTime.of(2024, 1, 15, 18, 30);
    LocalDateTime newTime = LocalDateTime.of(2024, 1, 16, 20, 0);

    Screening original = new Screening(1, movie, theater, originalTime);

    // Create a copy with different time
    Screening copy = original.createCopyWithNewTime(newTime);

    assertNotEquals(original.getId(), copy.getId());
    assertEquals(original.getMovie(), copy.getMovie());
    assertEquals(original.getTheater(), copy.getTheater());
    assertEquals(newTime, copy.getShowTime());
  }

}