import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Street;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PropertyCardController {
    private final PropertyCard[] propertyCards; // 0 - 21 are streets 22 - 26 are shipping, 27 - 28 are brewery
    private final Map<Integer, PropertyCard> propertyCardMap = new HashMap<>();
    private final Map<PropertyCard, GUI_Ownable> ownableMap = new HashMap<>();

    public PropertyCardController(PropertyCard[] propertyCards, GUI_Field[] fields) {
        this.propertyCards = propertyCards;
        propertyCardMap.put(1, propertyCards[0]);
        propertyCardMap.put(3, propertyCards[1]);

        propertyCardMap.put(6, propertyCards[2]);
        propertyCardMap.put(8, propertyCards[3]);
        propertyCardMap.put(9, propertyCards[4]);

        propertyCardMap.put(11, propertyCards[5]);
        propertyCardMap.put(13, propertyCards[6]);
        propertyCardMap.put(14, propertyCards[7]);

        propertyCardMap.put(16, propertyCards[8]);
        propertyCardMap.put(18, propertyCards[9]);
        propertyCardMap.put(19, propertyCards[10]);

        propertyCardMap.put(21, propertyCards[11]);
        propertyCardMap.put(23, propertyCards[12]);
        propertyCardMap.put(24, propertyCards[13]);

        propertyCardMap.put(26, propertyCards[14]);
        propertyCardMap.put(27, propertyCards[15]);
        propertyCardMap.put(29, propertyCards[16]);

        propertyCardMap.put(31, propertyCards[17]);
        propertyCardMap.put(32, propertyCards[18]);
        propertyCardMap.put(34, propertyCards[19]);

        propertyCardMap.put(37, propertyCards[20]);
        propertyCardMap.put(39, propertyCards[21]);

        propertyCardMap.put(5, propertyCards[22]);
        propertyCardMap.put(15, propertyCards[23]);
        propertyCardMap.put(25, propertyCards[24]);
        propertyCardMap.put(35, propertyCards[25]);

        propertyCardMap.put(12, propertyCards[26]);
        propertyCardMap.put(28, propertyCards[27]);

        ownableMap.put(propertyCards[0], (GUI_Ownable) fields[1]);
        ownableMap.put(propertyCards[1], (GUI_Ownable) fields[3]);
        ownableMap.put(propertyCards[2], (GUI_Ownable) fields[6]);
        ownableMap.put(propertyCards[3], (GUI_Ownable) fields[8]);
        ownableMap.put(propertyCards[4], (GUI_Ownable) fields[9]);
        ownableMap.put(propertyCards[5], (GUI_Ownable) fields[11]);
        ownableMap.put(propertyCards[6], (GUI_Ownable) fields[13]);
        ownableMap.put(propertyCards[7], (GUI_Ownable) fields[14]);
        ownableMap.put(propertyCards[8], (GUI_Ownable) fields[16]);
        ownableMap.put(propertyCards[9], (GUI_Ownable) fields[18]);
        ownableMap.put(propertyCards[10], (GUI_Ownable) fields[19]);
        ownableMap.put(propertyCards[11], (GUI_Ownable) fields[21]);
        ownableMap.put(propertyCards[12], (GUI_Ownable) fields[23]);
        ownableMap.put(propertyCards[13], (GUI_Ownable) fields[24]);
        ownableMap.put(propertyCards[14], (GUI_Ownable) fields[26]);
        ownableMap.put(propertyCards[15], (GUI_Ownable) fields[27]);
        ownableMap.put(propertyCards[16], (GUI_Ownable) fields[29]);
        ownableMap.put(propertyCards[17], (GUI_Ownable) fields[31]);
        ownableMap.put(propertyCards[18], (GUI_Ownable) fields[32]);
        ownableMap.put(propertyCards[19], (GUI_Ownable) fields[34]);
        ownableMap.put(propertyCards[20], (GUI_Ownable) fields[37]);
        ownableMap.put(propertyCards[21], (GUI_Ownable) fields[39]);
        ownableMap.put(propertyCards[22], (GUI_Ownable) fields[5]);
        ownableMap.put(propertyCards[23], (GUI_Ownable) fields[15]);
        ownableMap.put(propertyCards[24], (GUI_Ownable) fields[25]);
        ownableMap.put(propertyCards[25], (GUI_Ownable) fields[35]);
        ownableMap.put(propertyCards[26], (GUI_Ownable) fields[12]);
        ownableMap.put(propertyCards[27], (GUI_Ownable) fields[28]);
    }

    public PropertyCard[] getPropertyCards(){return propertyCards;}

    public void mortgageProperty(PropertyCard propertyCard, int position) {
        if (propertyCard.isMortgaged()) return;
        if (propertyCard.getOwner() == null) return;

        if (isStreet(propertyCard)) {
            Bank.payPlayer(propertyCard.getOwner(), ((StreetCard) propertyCard).getHouses()*((StreetCard) propertyCard).getHousePrice());
        }

        Bank.payPlayer(propertyCard.getOwner(), propertyCard.getMortgageValue());
        GUIInstance.getInstance().getFields()[position].setSubText("Mortgaged");
        propertyCard.setMortgaged(true);
    }

    public void unmortgageProperty(PropertyCard propertyCard, int position, String ownerName){
        Bank.payBank(propertyCard.getOwner(), propertyCard.getMortgageValue());
        GUIInstance.getInstance().getFields()[position].setSubText(ownerName);
        propertyCard.setMortgaged(false);
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

    public boolean allStreetsInGroupOwnedBy(StreetCard streetCard, Player player) {
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

    public boolean housesWouldBeEvenlyPlacedInGroup(StreetCard streetCard, boolean purchase) {
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
        updateHousesGUI(streetCard);
        return true;
    }

    public boolean sellHouse(StreetCard streetCard, Player player) {
        if (!allStreetsInGroupOwnedBy(streetCard, player)) return false;
        if (streetCard.getHouses() < 1) return false;
        if (!housesWouldBeEvenlyPlacedInGroup(streetCard, false)) return false;

        Bank.payPlayer(player, streetCard.getHousePrice() / 2);
        streetCard.removeHouse();
        updateHousesGUI(streetCard);
        return true;
    }

    public PropertyCard getCorrespondingPropertyCard(int position) {
        return propertyCardMap.get(position);
    }

    private GUI_Ownable getCorrespondingOwnable(PropertyCard propertyCard) {
        return ownableMap.get(propertyCard);
    }

    private void updateHousesGUI(PropertyCard propertyCard) {
        if (!(propertyCard instanceof StreetCard)) return;

        StreetCard streetCard = (StreetCard) propertyCard;
        GUI_Street street = (GUI_Street) getCorrespondingOwnable(propertyCard);

        if (streetCard.hasHotel()) {
            street.setHotel(true);
        } else {
            street.setHotel(false);
            street.setHouses(streetCard.getHouses());
        }
    }

    public int getRent(PropertyCard propertyCard) {
        if (propertyCard instanceof StreetCard) {
            return getRent((StreetCard) propertyCard);
        }
        if (propertyCard instanceof ShippingCard) {
            return getRent((ShippingCard) propertyCard);
        }
        if (propertyCard instanceof BreweryCard) {
            return getRent((BreweryCard) propertyCard);
        }
        return -1;
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
        int shipsOwnedByOwner = amountOfShipsOwnedByPlayer(shippingCard.getOwner()); // will fail if no owner...
        if (shipsOwnedByOwner > 0) {
            return shippingCard.getRents(shipsOwnedByOwner - 1);
        }
        return 0;
     }

    public int getRent(BreweryCard breweryCard) {
        if (ownsBothBreweries(breweryCard.getOwner())) {
            return breweryCard.getRents(1);
        }
        return breweryCard.getRents(0);
    }

    private boolean ownsBothBreweries(Player player) {
        if (propertyCards[26].getOwner() != null && propertyCards[27].getOwner() != null) {
            return propertyCards[26].getOwner().equals(player) && propertyCards[27].getOwner().equals(player);
        }
        return false;
    }

    private int amountOfShipsOwnedByPlayer(Player player) {
        int shipsOwnedByPlayer = 0;
        for (int i = 22; i < 26; i++) {
            Player owner = propertyCards[i].getOwner();
            if (owner != null) {
                if (owner.equals(player)) {
                    ++shipsOwnedByPlayer;
                }
            }
        }
        return shipsOwnedByPlayer;
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
