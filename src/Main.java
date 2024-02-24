package src;
import java.time.Instant;
import java.util.*; 

public class Main {

    public static void main(String[] args) {

        // Get an instance of the reservation system
        ReservationSystem reservationSystem = ReservationSystem.getInstance(); 

        // Add conference rooms
        reservationSystem.addRoom("Norppa", 5, "Has a smart screen and a jabra.");
        reservationSystem.addRoom("Saimaa", 6, "A conference room with a nice lakeside view.");
        reservationSystem.addRoom("Villimies", 10, "A sligthly bigger conference room.");
        reservationSystem.addRoom("Veera", 4, "");
        reservationSystem.addRoom("Rakuuna", 8, "");
        reservationSystem.addRoom("Linnoitus", 50, "Large conference room.");
        reservationSystem.addRoom("Vety", 2, "A smaller room.");
        reservationSystem.addRoom("Atomi", 4, "");

        // List available rooms
        Instant startTime = Instant.parse("2024-02-22T09:00:00Z");
        Instant endTime = Instant.parse("2024-02-22T11:00:00Z");        
        ArrayList<Room> availableRooms = reservationSystem.getAvailableRooms(startTime, endTime);
        System.out.println("Available rooms:");
        for (Room room : availableRooms) {
            System.out.println(room.getName()); 
        }

        // Make a reservation
        String userId = UUID.randomUUID().toString(); 
        Room room = availableRooms.get(0); 
        if (reservationSystem.makeReservation(userId, room, startTime, endTime)) {
            System.out.println("Reservation made to room " + room.getName() + ".");
        } else {
            System.out.println("Failed to make reservation.");
        }
    }
}
