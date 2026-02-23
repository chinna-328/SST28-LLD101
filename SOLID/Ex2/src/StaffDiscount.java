public class StaffDiscount implements DiscountRules {
    @Override public double getDiscountAmount(double subtotal, int distinctLines) {
        return distinctLines >= 3 ? 15.0 : 5.0;
    }
}