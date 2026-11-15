package Marzhan;
import java.math.BigDecimal;
public class StudentDiscount implements DiscountStrategy {
    public BigDecimal apply(BigDecimal price) { return price.multiply(new BigDecimal("0.90")); }
    public String getName() { return "Student discount (10%)"; }
}




