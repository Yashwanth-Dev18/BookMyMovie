package com.movietickets.repository;

import com.movietickets.model.Booking;
import com.movietickets.model.Movie;
import com.movietickets.model.Screening;
import com.movietickets.model.Theater;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class InMemoryBookingRepositoryTest {
    
    private InMemoryBookingRepository repository;
    private Booking testBooking;
    
    @BeforeEach
    void setUp() {
        repository = new InMemoryBookingRepository();
        
        Movie movie = new Movie(1, "Inception", 148, "Sci-Fi");
        Theater theater = new Theater(1, "Screen 1", 100);
        LocalDateTime showTime = LocalDateTime.now().plusDays(1);
        Screening screening = new Screening(1, movie, theater, showTime);
        
        List<String> seats = Arrays.asList("A1", "A2", "A3");
        testBooking = new Booking(1, screening, seats, "john@email.com");
    }