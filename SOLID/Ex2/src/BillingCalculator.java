import java.util.*;

public class BillingCalculator {
    private final MenuCatalog menu;

    public BillingCalculator(MenuCatalog menu) {
        this.menu = menu;
    }

    public InvoiceData calculate(String invoiceId, List<OrderLine> order, 
                                 TaxPolicy taxPolicy, DiscountPolicy discountPolicy) {
        List<InvoiceLine> lines = new ArrayList<>();
        double subtotal = 0.0;

        for (OrderLine ol : order) {
            MenuItem item = menu.getItem(ol.itemId);
            double lineTotal = item.price * ol.qty;
            subtotal += lineTotal;
            lines.add(new InvoiceLine(item.name, ol.qty, lineTotal));
        }

        double taxPct = taxPolicy.getTaxPercentage();
        double taxAmount = subtotal * (taxPct / 100.0);
        double discountAmount = discountPolicy.calculateDiscount(subtotal, order.size());
        double total = subtotal + taxAmount - discountAmount;

        return new InvoiceData(invoiceId, lines, subtotal, taxPct, taxAmount, discountAmount, total);
    }
}