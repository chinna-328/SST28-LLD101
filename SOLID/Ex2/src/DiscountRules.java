public interface DiscountRules {
    double getDiscountAmount(double subtotal, int distinctLines);
}