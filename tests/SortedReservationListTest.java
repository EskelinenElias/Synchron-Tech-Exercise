package tests;
import src.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;
import java.util.UUID; 

public class SortedReservationListTest {

    @Test
    void testAddReservation() {
        SortedReservationList reservationList = new SortedReservationList();

        // Create a reservation
        String userId = UUID.randomUUID().toString();
        Room room = new Room("Room A", 10, "Description of Room A");
        Instant startTime = Instant.parse("2024-02-22T10:00:00Z"); 
        Instant endTime = Instant.parse("2024-02-22T11:00:00Z"); 
        Reservation reservation = new Reservation(userId, room, startTime, endTime); 

        // Add reservation
        reservationList.addReservation(reservation);
        assertEquals(1, reservationList.size());
    }

    @Test
    void testDeleteReservation() {
        SortedReservationList reservationList = new SortedReservationList();

        // Create reservation 1
        String userId = UUID.randomUUID().toString();
        Room room = new Room("Room A", 10, "Description of Room A");
        Instant startTime = Instant.parse("2024-02-22T10:00:00Z"); 
        Instant endTime = Instant.parse("2024-02-22T11:00:00Z"); 
        Reservation reservation = new Reservation(userId, room, startTime, endTime); 

        // Add reservations
        reservationList.addReservation(reservation); 

        // Delete a reservation
        reservationList.deleteReservation(reservationList.get(0).getReservationId());
        assertEquals(0, reservationList.size());
    }

    @Test
    void testGetReservationsWithinTimeWindow() {
        SortedReservationList reservationList = new SortedReservationList();

        // Create reservation 1
        String userId1 = UUID.randomUUID().toString();
        Room room1 = new Room("Room A", 10, "Description of Room A");
        Instant startTime1 = Instant.parse("2024-02-22T10:00:00Z"); 
        Instant endTime1 = Instant.parse("2024-02-22T11:00:00Z"); 
        Reservation reservation1 = new Reservation(userId1, room1, startTime1, endTime1); 

        // Create reservation 2
        String userId2 = UUID.randomUUID().toString();
        Room room2 = new Room("Room B", 10, "Description of Room B");
        Instant startTime2 = Instant.parse("2024-02-22T12:00:00Z"); 
        Instant endTime2 = Instant.parse("2024-02-22T13:00:00Z"); 
        Reservation reservation2 = new Reservation(userId2, room2, startTime2, endTime2); 

        // Add reservations
        reservationList.addReservation(reservation1);
        reservationList.addReservation(reservation2);
        assertEquals(2, reservationList.size());

        // Get reservations within a time window
        Instant windowStart = Instant.parse("2024-02-22T09:30:00Z"); 
        Instant windowEnd = Instant.parse("2024-02-22T11:30:00Z");
        SortedReservationList filteredReservations = reservationList.getReservationsWithinTimeWindow(
                windowStart, windowEnd);
        assertEquals(1, filteredReservations.size());
    }
}
