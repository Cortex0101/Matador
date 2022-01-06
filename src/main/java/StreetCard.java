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

    public void addHouse() {
        ++this.houseCount;
    }

    public void removeHouse() {
        --this.houseCount;
    }

    public boolean hasHotel() {
        return getHouses() == 5;
    }

    public int getHousePrice() {
        return housePrice;
    }
}
