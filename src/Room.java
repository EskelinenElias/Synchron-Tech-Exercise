package src;
import java.util.UUID;

public class Room {
    private String roomId; 
    private String name;
    private int capacity; 
    private String description; 

    // Constructor to initialize a room with a unique ID
    public Room(String name, int capacity, String description) {
        // Generate unique room ID
        this.roomId = UUID.randomUUID().toString();
        this.name = name;
        this.capacity = capacity; 
        this.description = description; 
    }

    // Override equals method to compare rooms by ID
    @Override
    public boolean equals(Object obj) {
        // Check if object is null or of different class
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // Cast object to Room class
        Room room = (Room) obj;
        // Compare room IDs
        return roomId.equals(room.roomId);
    }

    // Getter for room ID
    public String getRoomId() {
        return roomId; 
    }

    // Getter for room name
    public String getName() {
        return name; 
    }

    // Setter for room name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for room capacity
    public int getCapacity() {
        return capacity;
    }

    // Setter for room capacity
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // Getter for room description
    public String getDescription() {
        return description;
    }

    // Setter for room description
    public void setDescription(String description) {
        this.description = description;
    }
}
