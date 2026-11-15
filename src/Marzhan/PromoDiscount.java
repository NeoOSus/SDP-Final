package Marzhan;
import java.math.BigDecimal;
public class PromoDiscount implements DiscountStrategy {
    public BigDecimal apply(BigDecimal price) { return price.multiply(new BigDecimal("0.80")); }
    public String getName() { return "Promo discount (20%)"; }
}




