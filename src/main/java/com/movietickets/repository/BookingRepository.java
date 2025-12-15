package com.movietickets.repository;

import com.movietickets.model.Booking;
import java.util.List;
import java.util.Optional;

public interface BookingRepository {

  Booking save(Booking booking);
  Optional<Booking> findById(int id);
  List<Booking> findAll();
  boolean delete(int id);
  List<Booking> findByCustomerEmail(String email);

}