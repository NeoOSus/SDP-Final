package Yerkebulan;

import Dinara.BookingAddon;
import Marzhan.DiscountStrategy;
import Marzhan.NoDiscount;
import Marzhan.Ticket;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Booking {
    private final String id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    private final String customerName;
    private final String movie;
    private final List<String> seats;
    private final Ticket ticket;
    private final DiscountStrategy discount;
    private final List<BookingAddon> addons;

    private Booking(Builder builder) {
        customerName = builder.customerName;
        movie = builder.movie;
        seats = List.copyOf(builder.seats);
        ticket = builder.ticket;
        discount = builder.discount;
        addons = List.copyOf(builder.addons);
    }

    public static class Builder {
        private String customerName;
        private String movie;
        private List<String> seats = new ArrayList<>();
        private Ticket ticket;
        private DiscountStrategy discount = new NoDiscount();
        private final List<BookingAddon> addons = new ArrayList<>();

        public Builder customerName(String value) { customerName = value; return this; }
        public Builder movie(String value) { movie = value; return this; }
        public Builder seats(List<String> value) { seats = new ArrayList<>(value); return this; }
        public Builder ticket(Ticket value) { ticket = value; return this; }
        public Builder discount(DiscountStrategy value) { discount = value; return this; }
        public Builder addAddon(BookingAddon addon) { addons.add(addon); return this; }

        public Booking build() {
            if (customerName == null || customerName.isBlank() || movie == null
                    || ticket == null || seats.isEmpty()) {
                throw new IllegalStateException(
                        "Customer, movie, ticket, and at least one seat are required.");
            }
            return new Booking(this);
        }
    }

    public BigDecimal getTotal() {
        BigDecimal total = discount.apply(
                ticket.getPrice().multiply(BigDecimal.valueOf(seats.size())));
        for (BookingAddon addon : addons) {
            total = total.add(addon.getPrice());
        }
        return total;
    }

    public String getId() { return id; }
    public String getCustomerName() { return customerName; }
    public String getMovie() { return movie; }
    public List<String> getSeats() { return seats; }
    public Ticket getTicket() { return ticket; }
    public DiscountStrategy getDiscount() { return discount; }
    public List<BookingAddon> getAddons() { return addons; }
}



