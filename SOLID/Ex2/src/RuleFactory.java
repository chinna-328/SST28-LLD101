public class RuleFactory {
    public TaxRules getTaxRule(String customerType) {
        if ("student".equalsIgnoreCase(customerType)) return new StudentTax();
        if ("staff".equalsIgnoreCase(customerType)) return new StaffTax();
        return () -> 8.0; 
    }

    public DiscountRules getDiscountRule(String customerType) {
        if ("student".equalsIgnoreCase(customerType)) return new StudentDiscount();
        if ("staff".equalsIgnoreCase(customerType)) return new StaffDiscount();
        return (sub, lines) -> 0.0; 
    }
}