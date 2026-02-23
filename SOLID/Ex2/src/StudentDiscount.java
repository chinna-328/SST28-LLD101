public class StudentDiscount implements DiscountRules {
    @Override public double getDiscountAmount(double subtotal, int distinctLines) {
        return subtotal >= 180.0 ? 10.0 : 0.0;
    }
}