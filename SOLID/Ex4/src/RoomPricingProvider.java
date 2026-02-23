public interface RoomPricingProvider {
    Money getBasePrice(int roomType);
}