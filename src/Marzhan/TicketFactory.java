package Marzhan;
public class TicketFactory {
    public Ticket create(int choice) {
        return switch (choice) {
            case 1 -> new StandardTicket();
            case 2 -> new StudentTicket();
            case 3 -> new VipTicket();
            default -> throw new IllegalArgumentException("Unknown ticket type");
        };
    }
}




