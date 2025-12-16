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
    
    @Test
    void testSaveBooking() {
        Booking saved = repository.save(testBooking);
        
        assertNotNull(saved);
        assertEquals(testBooking, saved);
    }
    
    @Test
    void testFindById() {
        repository.save(testBooking);
        
        Optional<Booking> found = repository.findById(1);
        
        assertTrue(found.isPresent());
        assertEquals(testBooking, found.get());
    }
    
    @Test
    void testFindByIdNotFound() {
        Optional<Booking> found = repository.findById(999);
        
        assertFalse(found.isPresent());
    }
    
    @Test
    void testFindAll() {
        repository.save(testBooking);
        
        List<Booking> allBookings = repository.findAll();
        
        assertEquals(1, allBookings.size());
        assertTrue(allBookings.contains(testBooking));
    }
    
    @Test
    void testDelete() {
        repository.save(testBooking);
        
        boolean deleted = repository.delete(1);
        
        assertTrue(deleted);
        assertFalse(repository.findById(1).isPresent());
    }
    
    @Test
    void testDeleteNotFound() {
        boolean deleted = repository.delete(999);
        
        assertFalse(deleted);
    }
    
    @Test
    void testFindByCustomerEmail() {
        repository.save(testBooking);
        
        List<Booking> found = repository.findByCustomerEmail("john@email.com");
        
        assertEquals(1, found.size());
        assertEquals(testBooking, found.get(0));
    }
}