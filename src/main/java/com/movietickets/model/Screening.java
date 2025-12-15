package com.movietickets.model;

import java.time.LocalDateTime;

public class Screening {
  private final int id;
  private final Movie movie;
  private final Theater theater;
  private final LocalDateTime showTime;

  public Screening(int id, Movie movie, Theater theater, LocalDateTime showTime) {

    validateInput(movie, theater, showTime);

    this.id = id;
    this.movie = movie;
    this.theater = theater;
    this.showTime = showTime;
  }

  private void validateInput(Movie movie, Theater theater, LocalDateTime showTime) {
    if (movie == null) {
      throw new IllegalArgumentException("Movie cannot be null");
    }

    if (theater == null) {
      throw new IllegalArgumentException("Theater cannot be null");
    }

    if (showTime == null) {
      throw new IllegalArgumentException("Show time cannot be null");
    }
  }

  public int getId() {
    return id;
  }

  public Movie getMovie() {
    return movie;
  }

  public Theater getTheater() {
    return theater;
  }

  public LocalDateTime getShowTime() {
    return showTime;
  }
}