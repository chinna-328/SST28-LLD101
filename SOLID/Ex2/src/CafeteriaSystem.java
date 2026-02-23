import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private final InvoiceFormatter formatter;
    private final RuleFactory ruleFactory;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceStore store, InvoiceFormatter formatter, RuleFactory ruleFactory) {
        this.store = store;
        this.formatter = formatter;
        this.ruleFactory = ruleFactory;
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);
        
        // 1. Calculate Subtotal
        double subtotal = 0.0;
        List<InvoiceLine> invoiceLines = new ArrayList<>();
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            invoiceLines.add(new InvoiceLine(item.name, l.qty, lineTotal));
        }

        // 2. Apply Rules
        TaxRules taxRule = ruleFactory.getTaxRule(customerType);
        DiscountRules discountRule = ruleFactory.getDiscountRule(customerType);

        double taxPct = taxRule.getTaxPercentage();
        double tax = subtotal * (taxPct / 100.0);
        double discount = discountRule.getDiscountAmount(subtotal, lines.size());
        double total = subtotal + tax - discount;

        // 3. Format Output
        String printable = formatter.format(invId, invoiceLines, subtotal, taxPct, tax, discount, total);
        System.out.print(printable);

        // 4. Persist
        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}