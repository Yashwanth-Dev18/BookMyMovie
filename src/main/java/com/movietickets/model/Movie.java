package com.movietickets.model;

public class Movie {

  private final int id;
  private final String title;
  private final int duration; // in minutes
  private final String genre;

  public Movie(int id, String title, int duration, String genre) {
    this.id = id;
    this.title = title;
    this.duration = duration;
    this.genre = genre;
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public int getDuration() {
    return duration;
  }

  public String getGenre() {
    return genre;
  }
  
}
