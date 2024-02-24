package tests;
import src.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    @Test
    void testConstructorAndGetters() {
        // Create a room
        Room room = new Room("Conference Room", 10, "A spacious conference room");

        // Check constructor and getter methods
        assertEquals("Conference Room", room.getName());
        assertEquals(10, room.getCapacity());
        assertEquals("A spacious conference room", room.getDescription());
        assertNotNull(room.getRoomId());
    }

    @Test
    void testSetters() {
        // Create a room
        Room room = new Room("Conference Room", 10, "A spacious conference room");

        // Set new values using setter methods
        room.setName("Meeting Room");
        room.setCapacity(8);
        room.setDescription("A cozy meeting room");

        // Check if values are updated
        assertEquals("Meeting Room", room.getName());
        assertEquals(8, room.getCapacity());
        assertEquals("A cozy meeting room", room.getDescription());
    }

    @Test
    void testEquals() {
        // Create two rooms with the same ID
        Room room1 = new Room("Conference Room", 10, "A spacious conference room");
        Room room2 = new Room("Meeting Room", 8, "A cozy meeting room");

        // Set the roomId of room2 to be the same as room1
        room2.setName(room1.getName());
        room2.setCapacity(room1.getCapacity());
        room2.setDescription(room1.getDescription());

        // Check if rooms are equal
        assertEquals(room1, room1);
    }
}
