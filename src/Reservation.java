package src;
import java.time.Instant; 
import java.util.UUID;

public class Reservation {
    private String reservationId; 
    private String userId; 
    private Room room;
    private Instant startTime;
    private Instant endTime;

    // Constructor for creating a reservation with Instant start and end times
    public Reservation(String userId, Room room, Instant startTime, Instant endTime) {
        // Validate inputs
        if (userId.isEmpty()) {
            throw new IllegalArgumentException("Invalid user ID.");
        } else if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("Reservation end time cannot be before start time.");
        } else if (endTime.equals(startTime)) {
            throw new IllegalArgumentException("Reservation end time cannot be equal to start time.");
        } else if (room == null) {
            throw new IllegalArgumentException("Reservation must include a room.");
        }
        // Construct instance
        this.reservationId = UUID.randomUUID().toString(); // Generate unique reservation ID
        this.userId = userId; 
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Secondary constructor that can construct an instance from String format start and end time
    public Reservation(String userId, Room room, String startTime, String endTime) {
        // Parse the reservation start time and end time
        this.startTime = Instant.parse(startTime);
        this.endTime = Instant.parse(endTime);
        // Validate inputs
        if (userId.isEmpty()) {
            throw new IllegalArgumentException("Invalid user ID.");
        } else if (room == null) {
            throw new IllegalArgumentException("Reservation must include a room.");
        } else if (this.endTime.isBefore(this.startTime)) {
            throw new IllegalArgumentException("Reservation end time cannot be before start time.");
        } else if (this.endTime.equals(this.startTime)) {
            throw new IllegalArgumentException("Reservation end time cannot be equal to start time.");
        }
        // Construct instance
        this.reservationId = UUID.randomUUID().toString(); // Generate unique reservation ID
        this.userId = userId; 
        this.room = room;
    }

    // Getter for reservation ID
    public String getReservationId() {
        return this.reservationId; 
    }

    // Getter for user ID
    public String getUserId() {
        return this.userId; 
    }

    // Getter for room
    public Room getRoom() {
        return room;
    }

    // Getter for start time
    public Instant getStartTime() {
        return startTime;
    }

    // Getter for end time
    public Instant getEndTime() {
        return endTime;
    }
}
