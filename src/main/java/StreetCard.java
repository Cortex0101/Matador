public class StreetCard extends PropertyCard {
    private final int housePrice;
    private int houseCount;
    public StreetCard(String name, int[] rents, int mortgageValue, int housePrice) {
        super(name, rents, mortgageValue);
        this.housePrice = housePrice;
        this.houseCount = 0;
    }

    public int getHouses() {
        return houseCount;
    }

    public void setHouses(int houses) {
        this.houseCount = houses;
    }

    public int getHousePrice() {
        return housePrice;
    }
}
