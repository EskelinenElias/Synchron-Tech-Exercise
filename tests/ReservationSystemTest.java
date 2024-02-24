package tests;
import src.*;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationSystemTest {

    @Test
    void testIsReserved() {
        ReservationSystem reservationSystem = ReservationSystem.getInstance();
        reservationSystem.addRoom("Room A", 10, "Description of Room A");
        Instant startTime = Instant.parse("2024-02-22T10:00:00Z");
        Instant endTime = Instant.parse("2024-02-22T12:00:00Z");
        assertFalse(reservationSystem.isReserved(reservationSystem.getRooms().get(0), startTime, endTime));
    }

    @Test
    void testMakeReservation() {
        ReservationSystem reservationSystem = ReservationSystem.getInstance();
        reservationSystem.addRoom("Room A", 10, "Description of Room A");
        String userId = UUID.randomUUID().toString();
        Instant startTime = Instant.parse("2024-07-22T10:00:00Z");
        Instant endTime = Instant.parse("2024-07-22T12:00:00Z");
        assertTrue(reservationSystem.makeReservation(userId, reservationSystem.getRooms().get(0), startTime, endTime));
    }

    @Test
    void testSetOpeningTime() {
        ReservationSystem reservationSystem = ReservationSystem.getInstance();
        LocalTime openingTime = LocalTime.of(9, 0);
        reservationSystem.setOpeningTime(openingTime);
        assertEquals(openingTime, reservationSystem.getOpeningTime());
    }

    @Test
    void testSetClosingTime() {
        ReservationSystem reservationSystem = ReservationSystem.getInstance();
        LocalTime closingTime = LocalTime.of(17, 0);
        reservationSystem.setClosingTime(closingTime);
        assertEquals(closingTime, reservationSystem.getClosingTime());
    }

    @Test
    void testSetMaxDuration() {
        ReservationSystem reservationSystem = ReservationSystem.getInstance();
        Duration maxDuration = Duration.ofHours(3);
        reservationSystem.setMaxDuration(maxDuration);
        assertEquals(maxDuration, reservationSystem.getMaxDuration(maxDuration));
    }
}
