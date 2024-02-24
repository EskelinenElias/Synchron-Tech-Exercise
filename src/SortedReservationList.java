package src;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;

public class SortedReservationList implements Iterable<Reservation> {
    private ArrayList<Reservation> reservations;

    // Constructor to initialize the list of reservations
    public SortedReservationList() {
        reservations = new ArrayList<>(); 
    }

    // Add a new reservation; adds reservations in sorted order according to start time; 
    // Starts the search from the tail of the list 
    public void addReservation(Reservation reservation) {
        // Iterate through the list backwards to find the correct position
        for (int i = reservations.size() - 1; i >= 0; i--) {
            // Compare start times to determine the position
            if (reservations.get(i).getStartTime().isBefore(reservation.getStartTime())) {
                reservations.add(i + 1, reservation); // Insert the reservation at the correct position
                return; // Exit the loop after insertion
            }
        }
        // If the loop completes without insertion, add the reservation at the beginning
        reservations.add(0, reservation);
    }

    // Delete a reservation by its ID
    public void deleteReservation(String reservationId) {
        for (int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i); 
            if (reservation.getReservationId().equals(reservationId)) {
                reservations.remove(i);
                break; 
            }
        }
    }

    // Get a list of reservations within a given time window, sorted by reservation start time
    // Search includes reservations with start time / end time equal to window start / end
    public SortedReservationList getReservationsWithinTimeWindow(Instant windowStart, Instant windowEnd) {
        SortedReservationList filteredReservations = new SortedReservationList();
        for (Reservation reservation : reservations) {
            // Check if reservation overlaps with the specified time window
            Instant reservationStart = reservation.getStartTime(); 
            Instant reservationEnd = reservation.getEndTime(); 
            if ((reservationStart.isAfter(windowStart) || reservationStart.equals(windowStart))
            && (reservationEnd.isBefore(windowEnd) || reservationEnd.equals(windowEnd))) {
                filteredReservations.addReservation(reservation);
            } else if (reservationStart.isAfter(windowEnd)) {
                break;
            }
        }
        return filteredReservations;
    }

    // Get a list of reservations after given time, sorted by reservation start time
    public SortedReservationList getReservationsAfter(Instant time) {
        ArrayList<Reservation> filteredReservationsReversed = new ArrayList<>();
        for (int i = reservations.size() - 1; i >= 0; i--) {
            Reservation reservation = reservations.get(i); 
            if (reservation.getStartTime().isAfter(time)) {
                filteredReservationsReversed.add(reservation);
            } else {
                break; 
            }
        } 
        SortedReservationList filteredReservations = new SortedReservationList();
        for (int i = filteredReservationsReversed.size() - 1; i >= 0; i--) {
            Reservation reservation = filteredReservationsReversed.get(i);
            filteredReservations.addReservation(reservation);
        }
        return filteredReservations;
    }

    // Get a list of reservations before given time, sorted by reservation start time
    public SortedReservationList getReservationsBefore(Instant time) {
        SortedReservationList filteredReservations = new SortedReservationList();
        for (Reservation reservation : reservations) {
            if (reservation.getStartTime().isBefore(time)) {
                filteredReservations.addReservation(reservation);
            } else {
                break;
            }
        }
        return filteredReservations;
    }

    // Get a list of reservations by a given user, sorted by reservation start time   
    public SortedReservationList getReservationsByUserId(String userId) {
        SortedReservationList filteredReservations = new SortedReservationList();
        for (Reservation reservation : reservations) {
            if (reservation.getUserId().equals(userId)) {
                filteredReservations.addReservation(reservation);
            }
        }
        return filteredReservations;
    }

    // Get a list of reservations to a given room, sorted by reservation start time   
    public SortedReservationList getReservationsByRoom(Room room) {
        SortedReservationList filteredReservations = new SortedReservationList();
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().equals(room)) {
                filteredReservations.addReservation(reservation);
            }
        }
        return filteredReservations;
    }

    // Return the size of the reservations list
    public int size() {
        return reservations.size();
    }

    // Get reservation by index
    public Reservation get(int i) {
        if (i < 0 || i >= reservations.size()) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + reservations.size());
        }
        return reservations.get(i);
    }

    // Delete reservation by index
    public void delete(int i) {
        reservations.remove(i);
    }

    // Implementing the Iterable interface
    @Override
    public Iterator<Reservation> iterator() {
        return reservations.iterator();
    }
}
