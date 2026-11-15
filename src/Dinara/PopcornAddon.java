package Dinara;
import java.math.BigDecimal;
public class PopcornAddon implements BookingAddon {
    public BigDecimal getPrice() { return new BigDecimal("1200.00"); }
    public String getDescription() { return "Popcorn"; }
}


