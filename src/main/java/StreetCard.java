public class StreetCard extends PropertyCard {
    private final int housePrice;
    private int houseCount;
    public StreetCard(String name, int[] rents, int mortgageValue, int housePrice) {
        super(name, rents, mortgageValue);
        this.housePrice = housePrice;
        this.houseCount = 0;
    }
}
