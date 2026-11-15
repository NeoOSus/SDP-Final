# Cinema Ticket Reservation System

A small Java console application for booking cinema seats. The project was created as a team exercise in applying six software design patterns to one working flow.

## Team

| Participant | Responsibility | Patterns |
| --- | --- | --- |
| Yerkebulan | Booking creation and coordination of the reservation flow | Builder, Facade |
| Marzhan | Ticket creation and price discounts | Factory Method, Strategy |
| Dinara | Booking notifications and optional ticket add-ons | Observer, Decorator |

## What the application does

- Shows the available movies and seats.
- Reserves one or more seats for a selected movie.
- Offers Standard, Student, and VIP tickets.
- Calculates no discount, a 10% student discount, or a 20% promo discount.
- Optionally adds popcorn to the booking.
- Prints a confirmation notification and the total price in KZT.
- Lists reservations made during the current run and prevents booking an occupied seat again.

Reservations are stored in memory. They are cleared when the application closes; there is no database or payment processing in this MVP.

## Requirements

- Java Development Kit (JDK) 17 or newer.
- IntelliJ IDEA or another Java IDE.
- No external libraries are required.

## Run in IntelliJ IDEA

1. Open the project folder in IntelliJ IDEA.
2. Make sure the project SDK is set to JDK 17 or newer.
3. Open `src/Yerkebulan/Main.java`.
4. Run the `main` method.

Use the console menu to browse movies, create a reservation, or list existing reservations.

## Run from PowerShell

From the project root, compile all Java source files and run the application:

```powershell
$files = Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName }
javac -d out $files
java -cp out Yerkebulan.Main
```

## Project structure

```text
src/
├── Yerkebulan/   Booking model and builder, reservation facade, application entry point
├── Marzhan/      Ticket types, ticket factory, discount strategies
└── Dinara/       Booking observers and optional add-on components
```

The folders reflect the team's division of work. Classes in different folders collaborate through the reservation flow.

## Design patterns in the project

### Factory Method

`TicketFactory` creates a ticket based on the selected ticket type. The rest of the application can request a ticket without directly constructing each ticket class.

### Strategy

`DiscountStrategy` represents a discount calculation. The selected strategy applies no discount, a student discount, or a promotional discount to the ticket subtotal.

### Observer

`BookingSubject` notifies registered `BookingObserver` objects after a reservation succeeds. `ConsoleBookingObserver` demonstrates this by printing a confirmation message.

### Decorator

`BookingAddon` represents an optional service that contributes to the booking price. `PopcornAddon` adds popcorn and its price to the booking at runtime.

### Builder

`Booking.Builder` assembles a booking from its customer, movie, seats, ticket, discount, and optional add-ons. It checks that the required booking details are present before creating the booking.

### Facade

`CinemaFacade` provides a simple interface for the reservation flow. It checks seat availability, selects the ticket and discount, builds the booking, records the occupied seats, and sends the notification.

## Example booking flow

1. The customer selects a movie and available seats.
2. The ticket factory creates the chosen ticket type.
3. The selected discount strategy calculates the discounted ticket subtotal.
4. The customer may add popcorn, which contributes its price to the booking.
5. The booking builder creates the booking after checking required details.
6. The facade marks the seats as occupied and the observer prints a confirmation.

## Limitations and possible future work

- Save bookings between application runs.
- Add cancellation and seat release.
- Add showtimes and separate seat maps per showtime.
- Add input validation for customer and booking details.
- Add a graphical interface and a payment integration.

