public class PolicyFactory {
    public TaxPolicy getTaxPolicy(String customerType) {
        if ("student".equalsIgnoreCase(customerType)) return new StudentTaxPolicy();
        if ("staff".equalsIgnoreCase(customerType)) return () -> 2.0; // Quick lambda for staff
        return () -> 8.0; // Default tax
    }

    public DiscountPolicy getDiscountPolicy(String customerType) {
        if ("student".equalsIgnoreCase(customerType)) return new StudentDiscountPolicy();
        if ("staff".equalsIgnoreCase(customerType)) return (sub, lines) -> lines >= 3 ? 15.0 : 5.0; // Quick lambda for staff
        return (sub, lines) -> 0.0; // Default discount
    }
}