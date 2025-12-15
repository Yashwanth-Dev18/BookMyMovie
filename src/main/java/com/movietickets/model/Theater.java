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

  @Override
  public String toString() {
    return String.format("%s (Capacity: %d)", name, capacity);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Theater theater = (Theater) o;
    return id == theater.id;
  }

}
