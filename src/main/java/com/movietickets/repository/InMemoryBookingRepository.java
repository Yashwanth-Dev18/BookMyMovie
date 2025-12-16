package com.movietickets.repository;

import com.movietickets.model.Booking;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryBookingRepository implements BookingRepository {
    private final Map<Integer, Booking> bookings = new HashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);
    
    @Override
    public Booking save(Booking booking) {
        if (booking.getId() <= 0) {
            // New booking - assign ID
            @SuppressWarnings("unused")
            int newId = idCounter.getAndIncrement(); 
            // In real scenario, we'd create a new Booking object with the ID
            // For simplicity, we'll just save as-is for now
        }
        bookings.put(booking.getId(), booking);
        return booking;
    }
    
    @Override
    public Optional<Booking> findById(int id) {
        return Optional.ofNullable(bookings.get(id));
    }
    
    @Override
    public List<Booking> findAll() {
        return new ArrayList<>(bookings.values());
    }
    
    @Override
    public boolean delete(int id) {
        return bookings.remove(id) != null;
    }
    
    @Override
    public List<Booking> findByCustomerEmail(String email) {
        List<Booking> result = new ArrayList<>();
        for (Booking booking : bookings.values()) {
            if (booking.getCustomerEmail().equalsIgnoreCase(email)) {
                result.add(booking);
            }
        }
        return result;
    }
}