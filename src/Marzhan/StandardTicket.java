package Marzhan;
import java.math.BigDecimal;
public class StandardTicket implements Ticket {
    public String getType() { return "Standard"; }
    public BigDecimal getPrice() { return new BigDecimal("2500.00"); }
}




