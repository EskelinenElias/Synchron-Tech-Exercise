package src;

import java.util.*; 
import java.time.Instant;

public class Main {

    public static void main(String[] args) {

        // Get an instance of the reservation system and add rooms
        ReservationSystem reservationSystem = ReservationSystem.getInstance(); 
        reservationSystem.addRoom("Norppa", 5, "Has a smart screen and a jabra.");
        reservationSystem.addRoom("Saimaa", 6, "A conference room with a nice lakeside view.");
        reservationSystem.addRoom("Villimies", 10, "A sligthly bigger conference room.");
        reservationSystem.addRoom("Veera", 4, "");
        reservationSystem.addRoom("Rakuuna", 8, "");
        reservationSystem.addRoom("Linnoitus", 50, "Large conference room.");
        reservationSystem.addRoom("Vety", 2, "A smaller room.");
        reservationSystem.addRoom("Atomi", 4, "");

        // Generate an user ID to mimic an user logged in to the app
        String userId = UUID.randomUUID().toString(); 

        // Check available rooms within a time window
        Instant windowStartTime = Instant.parse("2024-02-28T11:00:00Z"); 
        Instant windowEndTime = Instant.parse("2024-02-28T12:00:00Z"); 
        ArrayList<Room> availableRooms = reservationSystem.getAvailableRooms(windowStartTime, windowEndTime);
        System.out.println("Available rooms:");
        for (Room room : availableRooms) {
            System.out.println("Room " + room.getName() + ": capacity " + room.getCapacity() 
            + ", description: " + room.getDescription()); 
        }
        System.out.println();

        // Make a reservation
        Room room = availableRooms.get(0); 
        Instant reservationStartTime = Instant.parse("2024-02-28T11:00:00Z"); 
        Instant reservationEndTime = Instant.parse("2024-02-28T11:30:00Z"); 
        reservationSystem.makeReservation(userId, room, reservationStartTime, reservationEndTime); 

        // Check user reservations 
        SortedReservationList userReservations = reservationSystem.getReservationsByUser(userId); 
        System.out.println("Reservations by the user:");
        for (Reservation reservation : userReservations) {
            String roomName = reservation.getRoom().getName(); 
            String startTime = reservation.getStartTime().toString(); 
            String endTime = reservation.getEndTime().toString(); 
            System.out.println("Room " + roomName + " from " + startTime + " to " + endTime + ".");
        }
        System.out.println();

        // Delete the reservation
        Reservation usersReservation = userReservations.get(0);
        String reservationId = usersReservation.getReservationId(); 
        String roomName = usersReservation.getRoom().getName(); 
        reservationSystem.deleteReservation(reservationId);
        System.out.println("Reservation to room " + roomName + " deleted.\n");

        // Check user reservations again
        userReservations = reservationSystem.getReservationsByUser(userId); 
        System.out.println("Reservations by the user " + userId + ":");
        for (Reservation reservation : userReservations) {
            roomName = reservation.getRoom().getName(); 
            String startTime = reservation.getStartTime().toString(); 
            String endTime = reservation.getEndTime().toString(); 
            System.out.println("Room " + roomName + " from " + startTime + " to " + endTime + ".");
        }
        System.out.println();
    }
}
