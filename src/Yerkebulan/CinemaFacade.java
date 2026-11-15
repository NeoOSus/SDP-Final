package Yerkebulan;

import Dinara.BookingSubject;
import Dinara.ConsoleBookingObserver;
import Dinara.PopcornAddon;
import Marzhan.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CinemaFacade {
    private final Map<String, List<String>> occupiedSeats = new HashMap<>();
    private final List<Booking> bookings = new ArrayList<>();
    private final TicketFactory ticketFactory = new TicketFactory();
    private final BookingSubject bookingSubject = new BookingSubject();

    public CinemaFacade() {
        bookingSubject.addObserver(new ConsoleBookingObserver());
    }

    public List<String> availableSeats(String movie, List<String> allSeats) {
        List<String> available = new ArrayList<>(allSeats);
        available.removeAll(occupiedSeats.getOrDefault(movie, List.of()));
        return available;
    }

    public Booking reserve(String name, String movie, List<String> seats,
                           int ticketChoice, int discountChoice, boolean popcorn) {
        List<String> occupied = occupiedSeats.computeIfAbsent(movie, key -> new ArrayList<>());
        for (String seat : seats) {
            if (occupied.contains(seat)) {
                throw new IllegalArgumentException("Seat " + seat + " is already booked.");
            }
        }

        Ticket ticket = ticketFactory.create(ticketChoice);
        DiscountStrategy discount = createDiscount(discountChoice);
        Booking.Builder builder = new Booking.Builder()
                .customerName(name)
                .movie(movie)
                .seats(seats)
                .ticket(ticket)
                .discount(discount);
        if (popcorn) {
            builder.addAddon(new PopcornAddon());
        }

        Booking booking = builder.build();
        occupied.addAll(seats);
        bookings.add(booking);
        bookingSubject.notifyObservers(booking);
        return booking;
    }

    public List<Booking> getBookings() {
        return List.copyOf(bookings);
    }

    private DiscountStrategy createDiscount(int choice) {
        return switch (choice) {
            case 1 -> new StudentDiscount();
            case 2 -> new PromoDiscount();
            default -> new NoDiscount();
        };
    }
}




