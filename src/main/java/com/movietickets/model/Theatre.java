package com.movietickets.model;

public class Theatre {
  
  private final int id;
  private final String name;
  private final int capacity;
  
  public Theatre(int id, String name, int capacity) {
    this.id = id;
    this.name = name;
    this.capacity = capacity;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getCapacity() {
    return capacity;
  }
}
