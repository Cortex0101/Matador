public class PropertyCard {
    private final int[] rents;
    private final String name;
    private Player owner;
    private boolean mortgaged;
    private final int mortgageValue;

    public PropertyCard(String name, int[] rents, int mortgageValue) {
        this.rents = rents;
        this.name = name;
        this.owner = null;
        this.mortgaged = false;
        this.mortgageValue = mortgageValue;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean isMortgaged() {
        return mortgaged;
    }

    public void setMortgaged(boolean mortgaged) {
        this.mortgaged = mortgaged;
    }
}
