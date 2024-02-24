package src;
import java.util.*; 
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime; 
import java.time.ZoneId;

public class ReservationSystem {
    private static ReservationSystem instance;
    private ArrayList<Room> rooms; // List of all available rooms
    private SortedReservationList reservations; // List of all reservations
    private Duration maxDuration = Duration.ofHours(2); // Maximum allowed duration for a reservation
    private LocalTime openingTime = LocalTime.of(8, 0); // Opening time of the reservation system
    private LocalTime closingTime = LocalTime.of(18, 0); // Closing time of the reservation system

    // Private constructor to prevent instantiation from outside
    private ReservationSystem() {
        this.rooms = new ArrayList<>();
        this.reservations = new SortedReservationList();
    }

    // Static method to get the singleton instance
    public static ReservationSystem getInstance() {
        if (instance == null) {
            instance = new ReservationSystem();
        }
        return instance;
    }

    // Getter and setter methods for openingTime, closingTime, and maxDuration

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime; 
    }

    public LocalTime getOpeningTime() {
        return this.openingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime; 
    }

    public LocalTime getClosingTime() {
        return this.closingTime;
    }

    public void setMaxDuration(Duration maxDuration) {
        this.maxDuration = maxDuration; 
    }

    public Duration getMaxDuration(Duration maxDuration) {
        return this.maxDuration; 
    }

    // Method to add a new room to the system
    public void addRoom(String name, int capacity, String description) {
        Room room = new Room(name, capacity, description);
        rooms.add(room); 
    }

    // Getter for the list of all rooms
    public ArrayList<Room> getRooms() {
        return rooms; 
    }

    // Method to get all available rooms within a specified time window
    public ArrayList<Room> getAvailableRooms(Instant startTime, Instant endTime) {
        Set<Room> availableRooms = new HashSet<>(this.rooms);
        Set<Room> reservedRooms = new HashSet<>(); 
        SortedReservationList reservationsInWindow =  
            reservations.getReservationsWithinTimeWindow(startTime, endTime); 
        for (Reservation reservation : reservationsInWindow) {
            reservedRooms.add(reservation.getRoom()); 
        }
        availableRooms.removeAll(reservedRooms); 
        ArrayList<Room> filteredRooms = new ArrayList<>(availableRooms); 
        return filteredRooms;
    }

    // Method to check if a room is reserved within a specified time window
    public boolean isReserved(Room room, Instant windowStart, Instant windowEnd) {
        SortedReservationList reservationsInWindow = 
            reservations.getReservationsWithinTimeWindow(windowStart, windowEnd); 
        SortedReservationList reservationsForRoom = 
            reservationsInWindow.getReservationsByRoom(room);
        return reservationsForRoom.size() > 0;  
    }

    // Method for listing reservations by user
    public SortedReservationList getReservationsByUser(String userId) {
        return reservations.getReservationsByUserId(userId); 
    }

    // Method to make a new reservation
    public synchronized boolean makeReservation(String userId, Room room, Instant startTime, Instant endTime) {
        // Validate input parameters
        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time cannot be before start time");
        } 
        LocalDateTime startDateTime = LocalDateTime.ofInstant(startTime, ZoneId.systemDefault());
        LocalDateTime endDateTime = LocalDateTime.ofInstant(endTime, ZoneId.systemDefault());
        LocalTime startLocalTime = startDateTime.toLocalTime();
        LocalTime endLocalTime = endDateTime.toLocalTime();

        // Check that the reservation is within the valid time window
        if (startLocalTime.isBefore(openingTime) || endLocalTime.isAfter(closingTime)) {
            throw new IllegalArgumentException("Reservation must be between opening and closing times.");
        }

        // Check that reservation duration is not too long
        Duration reservationDuration = Duration.between(startTime, endTime);
        if (reservationDuration.compareTo(maxDuration) > 0) {
            throw new IllegalArgumentException("Duration cannot be longer than the maximum allowed duration");
        }

        // Check room availability within the time window
        if (!isReserved(room, startTime, endTime)) {
            // Add a new reservation
            Reservation reservation = new Reservation(userId, room, startTime, endTime);
            reservations.addReservation(reservation);
            return true;
        }
        // Room is already booked for that time
        System.out.println("Room is already booked for that time.");
        return false;
    }

    // Method to remove a reservation
    public void deleteReservation(String reservationId) {
        reservations.deleteReservation(reservationId);
    }
}
