package com.movietickets.ui;

import com.movietickets.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ConsoleUITest {
    
    @Mock
    private BookingService bookingService;
    
    @Test
    void testConsoleUIStart() throws Exception {
        // Simple test to verify UI can be instantiated
        ConsoleUI ui = new ConsoleUI(bookingService);
        assertNotNull(ui);
    }
}