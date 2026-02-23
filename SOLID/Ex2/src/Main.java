import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Cafeteria Billing ===");

        // Dependency Wiring
        InvoiceStore store = new FileStore();
        InvoiceFormatter formatter = new InvoiceFormatter();
        RuleFactory rules = new RuleFactory();

        CafeteriaSystem sys = new CafeteriaSystem(store, formatter, rules);

        sys.addToMenu(new MenuItem("M1", "Veg Thali", 80.00));
        sys.addToMenu(new MenuItem("C1", "Coffee", 30.00));
        sys.addToMenu(new MenuItem("S1", "Sandwich", 60.00));

        // Original Test Case (Student)
        List<OrderLine> studentOrder = List.of(
                new OrderLine("M1", 2),
                new OrderLine("C1", 1)
        );
        sys.checkout("student", studentOrder);

        // Stretch Goal Test Case (Staff)
        System.out.println("\n=== Staff Checkout (Stretch Goal) ===");
        List<OrderLine> staffOrder = List.of(
                new OrderLine("M1", 1),
                new OrderLine("C1", 1),
                new OrderLine("S1", 1)
        );
        sys.checkout("staff", staffOrder);
    }
}