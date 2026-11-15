package Marzhan;
import java.math.BigDecimal;
public class VipTicket implements Ticket {
    public String getType() { return "VIP"; }
    public BigDecimal getPrice() { return new BigDecimal("4000.00"); }
}




