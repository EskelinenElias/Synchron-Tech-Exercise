package tests;
import src.*;
import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReservationTest {

    @Test
    void testConstructorAndGetters() {
        // Create a room
        Room room = new Room("Room A", 10, "Description of Room A");

        // Create a reservation
        String userId = UUID.randomUUID().toString(); 
        Instant startTime = Instant.parse("2024-02-22T10:00:00Z");
        Instant endTime = Instant.parse("2024-02-22T12:00:00Z");
        Reservation reservation = new Reservation(userId, room, startTime, endTime);

        // Check constructor and getter methods
        assertEquals(userId, reservation.getUserId());
        assertEquals(room, reservation.getRoom());
        assertEquals(startTime, reservation.getStartTime());
        assertEquals(endTime, reservation.getEndTime());
        assertNotNull(reservation.getReservationId());
    }

    @Test
    void testSecondaryConstructor() {
        // Create a room
        Room room = new Room("Room A", 10, "Description of Room A");

        // Create a reservation using secondary constructor
        String userId = UUID.randomUUID().toString(); 
        String startTime = "2024-02-22T10:00:00Z";
        String endTime = "2024-02-22T12:00:00Z";
        Reservation reservation = new Reservation(userId, room, startTime, endTime);

        // Check constructor and getter methods
        assertEquals(userId, reservation.getUserId());
        assertEquals(room, reservation.getRoom());
        assertEquals(Instant.parse("2024-02-22T10:00:00Z"), reservation.getStartTime());
        assertEquals(Instant.parse("2024-02-22T12:00:00Z"), reservation.getEndTime());
        assertNotNull(reservation.getReservationId());
    }

    @Test
    void testInvalidUserId() {
        // Create a room
        Room room = new Room("Room A", 10, "Description of Room A");

        // Try creating a reservation with an invalid user ID
        assertThrows(IllegalArgumentException.class, () -> {
            new Reservation("", room, Instant.parse("2024-02-22T10:00:00Z"), Instant.parse("2024-02-22T12:00:00Z"));
        });
    }

    @Test
    void testEndTimeBeforeStartTime() {
        // Create a room
        Room room = new Room("Room A", 10, "Description of Room A");

        // Try creating a reservation with end time before start time
        assertThrows(IllegalArgumentException.class, () -> {
            new Reservation("user123", room, Instant.parse("2024-02-22T12:00:00Z"), Instant.parse("2024-02-22T10:00:00Z"));
        });
    }

    @Test
    void testEndTimeEqualsStartTime() {
        // Create a room
        Room room = new Room("Room A", 10, "Description of Room A");

        // Try creating a reservation with end time equal to start time
        assertThrows(IllegalArgumentException.class, () -> {
            new Reservation("user123", room, Instant.parse("2024-02-22T10:00:00Z"), Instant.parse("2024-02-22T10:00:00Z"));
        });
    }
}
