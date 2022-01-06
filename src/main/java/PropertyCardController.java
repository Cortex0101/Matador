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

    int fieldsInGroup(PropertyCard propertyCard) {
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

    public PropertyCard[] getStreetsInSameGroup(StreetCard streetCard) {
        int amountOfFieldsInGroup = fieldsInGroup(streetCard);
        int cardIndex = indexOfCard(streetCard);
        int indexOfFirstCardInGroup = switch (cardIndex) {
            case 0, 1 -> 0;
            case 20, 21 -> 20;
            default -> cardIndex - ((cardIndex - 2) % 3);
        };

        return Arrays.copyOfRange(propertyCards, indexOfFirstCardInGroup, indexOfFirstCardInGroup + amountOfFieldsInGroup);
    }

    boolean allStreetsInGroupOwnedBy(StreetCard streetCard, Player player) {
        Player owner = streetCard.getOwner();
        if (owner == null) return false;
        if (!streetCard.getOwner().equals(player)) return false;

        for (PropertyCard propertyCard : getStreetsInSameGroup(streetCard)) {
            if (propertyCard.getOwner() == null) {
                return false;
            } else if (!propertyCard.getOwner().equals(owner)) {
                return false;
            }
        }
        return true;
    }

    private boolean housesWouldBeEvenlyPlacedInGroup(StreetCard streetCard, boolean purchase) {
        PropertyCard[] streetCards = getStreetsInSameGroup(streetCard);
        for (PropertyCard card : streetCards) {
            if (purchase) {
                if (((StreetCard) card).getHouses() < streetCard.getHouses()) {
                    return false;
                }
            } else {
                if (((StreetCard) card).getHouses() > streetCard.getHouses()) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean purchaseHouse(StreetCard streetCard, Player player) {
        if (!allStreetsInGroupOwnedBy(streetCard, player)) return false;
        if (streetCard.hasHotel()) return false;
        if (!housesWouldBeEvenlyPlacedInGroup(streetCard, true)) return false;

        Bank.payBank(player, streetCard.getHousePrice()); // If the player does not have money, he will loose the game when trying to purchase - maybe it shoulden't be an option?
        streetCard.addHouse();
        return true;
    }

    public boolean sellHouse(StreetCard streetCard, Player player) {
        if (!allStreetsInGroupOwnedBy(streetCard, player)) return false;
        if (streetCard.getHouses() < 1) return false;
        if (!housesWouldBeEvenlyPlacedInGroup(streetCard, false)) return false;

        Bank.payPlayer(player, streetCard.getHousePrice() / 2);
        streetCard.removeHouse();
        return true;
    }

    public int getRent(StreetCard streetCard) {
        if (!allStreetsInGroupOwnedBy(streetCard, streetCard.getOwner())) { // Will fail is street card is owned by no one - TODO: fix, maybe use exception
            return streetCard.getRents(0);
        } else if (streetCard.getHouses() == 0) {
            return streetCard.getRents(0) * 2;
        } else {
            return streetCard.getRents(streetCard.getHouses());
        }
    }

    public int getRent(ShippingCard shippingCard) {
        return 0;
    }

    public int getRent(BreweryCard breweryCard) {
        return 0;
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
