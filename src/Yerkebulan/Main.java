package Yerkebulan;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<String> MOVIES = List.of(
            "Dune: Part Two", "Inside Out 2", "The Batman");
    private static final List<String> SEATS = List.of(
            "A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4");

    private final CinemaFacade cinema = new CinemaFacade();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("\n=== Cinema Ticket Reservation ==="
                        + "\n1. Browse movies and seats"
                        + "\n2. Make reservation"
                        + "\n3. List reservations"
                        + "\n0. Exit");

                switch (readInt(scanner, "Choose: ")) {
                    case 1 -> showMovies();
                    case 2 -> makeReservation(scanner);
                    case 3 -> showBookings();
                    case 0 -> running = false;
                    default -> System.out.println("Please choose 0, 1, 2, or 3.");
                }
            }
        }
        System.out.println("Goodbye!");
    }

    private void showMovies() {
        for (int i = 0; i < MOVIES.size(); i++) {
            String movie = MOVIES.get(i);
            List<String> availableSeats = cinema.availableSeats(movie, SEATS);
            System.out.println((i + 1) + ". " + movie + " | Available: " + availableSeats);
        }
    }

    private void makeReservation(Scanner scanner) {
        showMovies();
        int movieIndex = readInt(scanner, "Movie number: ") - 1;
        if (movieIndex < 0 || movieIndex >= MOVIES.size()) {
            System.out.println("Invalid movie.");
            return;
        }

        String movie = MOVIES.get(movieIndex);
        System.out.print("Your name: ");
        String name = scanner.nextLine().trim();
        if (name.isBlank()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.println("Seats: " + SEATS + " (comma separated)");
        System.out.print("Seat(s): ");
        List<String> seats = Arrays.stream(scanner.nextLine().toUpperCase().split(","))
                .map(String::trim)
                .filter(seat -> !seat.isEmpty())
                .distinct()
                .toList();
        if (seats.isEmpty() || !SEATS.containsAll(seats)) {
            System.out.println("Choose seats from the list.");
            return;
        }

        System.out.println("Ticket: 1 Standard (2500), 2 Student (1800), 3 VIP (4000)");
        int ticketChoice = readInt(scanner, "Ticket type: ");
        if (ticketChoice < 1 || ticketChoice > 3) {
            System.out.println("Invalid ticket type.");
            return;
        }

        System.out.println("Discount: 0 None, 1 Student 10%, 2 Promo 20%");
        int discountChoice = readInt(scanner, "Discount: ");
        if (discountChoice < 0 || discountChoice > 2) {
            System.out.println("Invalid discount.");
            return;
        }

        System.out.print("Add popcorn for 1200? (y/n): ");
        boolean addPopcorn = scanner.nextLine().trim().equalsIgnoreCase("y");

        Booking booking;
        try {
            booking = cinema.reserve(name, movie, seats, ticketChoice, discountChoice, addPopcorn);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return;
        }

        printBooking(booking, addPopcorn);
    }

    private void printBooking(Booking booking, boolean addPopcorn) {
        System.out.println("Booking " + booking.getId() + " | " + booking.getMovie()
                + " | seats " + booking.getSeats());
        System.out.println("Ticket: " + booking.getTicket().getType()
                + " | " + booking.getDiscount().getName());
        if (addPopcorn) {
            System.out.println("Add-on: Popcorn");
        }
        System.out.println("Total: " + booking.getTotal() + " KZT");
    }

    private void showBookings() {
        List<Booking> bookings = cinema.getBookings();
        if (bookings.isEmpty()) {
            System.out.println("No reservations yet.");
            return;
        }
        for (Booking booking : bookings) {
            System.out.println(booking.getId() + " | " + booking.getCustomerName()
                    + " | " + booking.getMovie() + " | " + booking.getSeats()
                    + " | " + booking.getTotal() + " KZT");
        }
    }

    private int readInt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException exception) {
            return -1;
        }
    }
}



