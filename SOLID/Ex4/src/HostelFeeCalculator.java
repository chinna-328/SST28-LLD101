import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final RoomPricingProvider roomPricing;
    private final AddOnPricingProvider addOnPricing;

    public HostelFeeCalculator(FakeBookingRepo repo, 
                             RoomPricingProvider roomPricing, 
                             AddOnPricingProvider addOnPricing) {
        this.repo = repo;
        this.roomPricing = roomPricing;
        this.addOnPricing = addOnPricing;
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7781); 
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        Money total = roomPricing.getBasePrice(req.roomType);
        
        for (AddOn a : req.addOns) {
            total = total.plus(addOnPricing.getPrice(a));
        }
        
        return total;
    }
}