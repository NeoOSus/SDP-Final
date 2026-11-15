package Dinara;

import Yerkebulan.Booking;
import java.util.ArrayList;
import java.util.List;
public class BookingSubject {
    private final List<BookingObserver> observers = new ArrayList<>();
    public void addObserver(BookingObserver observer) { observers.add(observer); }
    public void notifyObservers(Booking booking) {
        for (BookingObserver observer : observers) {
            observer.onBookingCreated(booking);
        }
    }
}




