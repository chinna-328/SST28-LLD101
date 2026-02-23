public class InvoiceLine {
    public final String name;
    public final int qty;
    public final double total;

    public InvoiceLine(String name, int qty, double total) {
        this.name = name;
        this.qty = qty;
        this.total = total;
    }
}