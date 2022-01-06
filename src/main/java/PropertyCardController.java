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
            // TODO: check if there are houses.
        }

        Bank.payPlayer(propertyCard.getOwner(), propertyCard.getMortgageValue());
        propertyCard.setMortgaged(true);
    }

    public int fieldsInGroup(PropertyCard propertyCard) {
        if (isStreet(propertyCard)) {
            int index = indexOfCard(propertyCard);
            return switch (index) {
                case 0, 1, 20, 21 -> 2;
                default -> 3;
            };
        } else if (isShipping(propertyCard)) {
            return 4;
        } else if (isBrewery(propertyCard)) {
            return 2;
        }
        return -1; // error;
    }

    public boolean allStreetsInGroupOwnedBy(StreetCard streetCard, Player player) {
        Player owner = streetCard.getOwner();
        if (owner == null) return false;
        if (!streetCard.getOwner().equals(player)) return false;

        int amountOfFieldsInGroup = fieldsInGroup(streetCard);
        int cardIndex = indexOfCard(streetCard);
        int indexOfFirstCardInGroup = -1;
        switch (cardIndex) {
            case 0: case 1: indexOfFirstCardInGroup = 0; break;
            case 20: case 21: indexOfFirstCardInGroup = 20; break;
            default: indexOfFirstCardInGroup = cardIndex - ((cardIndex - 2) % 3);
        }

        boolean allStreetsOwnedByPlayer = true;
        for (int i = 0; i < amountOfFieldsInGroup; i++) {
            if (!propertyCards[indexOfFirstCardInGroup + i].getOwner().equals(player)) {
                allStreetsOwnedByPlayer = false;
            }
        }
        return allStreetsOwnedByPlayer;
    }

    private boolean isStreet(PropertyCard propertyCard) {
        int index = indexOfCard(propertyCard);
        return index >= 0 && index < 22;
    }

    private boolean isShipping(PropertyCard propertyCard) {
        int index = indexOfCard(propertyCard);
        return index > 21 && index < 26;
    }

    private boolean isBrewery(PropertyCard propertyCard) {
        int index = indexOfCard(propertyCard);
        return index > 25 && index < 28;
    }

    private int indexOfCard(PropertyCard propertyCard) {
        for (int i = 0; i < propertyCards.length; i++) {
            if (propertyCards[i].equals(propertyCard)) return i;
        }
        return -1;
    }
}