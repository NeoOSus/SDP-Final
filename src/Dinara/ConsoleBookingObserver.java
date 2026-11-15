package Dinara;

import Yerkebulan.Booking;
public class ConsoleBookingObserver implements BookingObserver {
    public void onBookingCreated(Booking booking) {
        System.out.println("[Notification] Reservation " + booking.getId()
                + " confirmed for " + booking.getCustomerName() + ".");
    }
}



