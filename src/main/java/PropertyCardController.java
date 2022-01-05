import java.util.Arrays;

public class PropertyCardController {
    private PropertyCard[] propertyCards; // 0 - 21 are streets 22 - 26 are shipping, 27 - 28 are brewery

    public PropertyCardController(PropertyCard[] propertyCards) {
        this.propertyCards = propertyCards;
    }

    public void mortgageProperty(PropertyCard propertyCard) {
        if (propertyCard.isMortgaged()) return;
        if (propertyCard.getOwner() == null) return;

        if (isStreet(propertyCard)) {
            // check if there are houses.
        }

        Bank.payPlayer(propertyCard.getOwner(), propertyCard.getMortgageValue());
        propertyCard.setMortgaged(true);
    }

    private boolean isStreet(PropertyCard propertyCard) {
        int index = Arrays.binarySearch(propertyCards, propertyCard);
        return index >= 0 && index < 22;
    }

    private boolean isShipping(PropertyCard propertyCard) {
        int index = Arrays.binarySearch(propertyCards, propertyCard);
        return index > 21 && index < 27;
    }

    private boolean isBrewery(PropertyCard propertyCard) {
        int index = Arrays.binarySearch(propertyCards, propertyCard);
        return index > 26 && index < 29;
    }
}
