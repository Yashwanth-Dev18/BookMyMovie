package com.movietickets.model;

public class Theater {
  
  private final int id;
  private final String name;
  private final int capacity;
  
  public Theater(int id, String name, int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be positive");
    }

    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    
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
