package Marzhan;
import java.math.BigDecimal;
public class NoDiscount implements DiscountStrategy {
    public BigDecimal apply(BigDecimal price) { return price; }
    public String getName() { return "No discount"; }
}




