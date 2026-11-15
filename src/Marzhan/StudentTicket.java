package Marzhan;
import java.math.BigDecimal;
public class StudentTicket implements Ticket {
    public String getType() { return "Student"; }
    public BigDecimal getPrice() { return new BigDecimal("1800.00"); }
}




