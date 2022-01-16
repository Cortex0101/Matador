import java.util.ArrayList;

public class HandleStartOfTurnChoice {
    PlayerLostController playerLostController;

    public HandleStartOfTurnChoice(){
        this.playerLostController = new PlayerLostController();
    }

    public void roll(PlayerController playerController){
        playerController.getActivePlayer().IncrementRollCount();
        if (playerController.getActivePlayer().getRollCount() == 3) {
            playerController.getActivePlayer().getCar().setInJail(true);
            playerController.getActivePlayer().getCar().setCarPosition(10);
        }
    }

    public void buyHouse(Player player, PropertyCardController propertyCardController){
        final PropertyCard[] ownedPropertyCards = player.getOwnedPropertyCards(propertyCardController);
        ArrayList<StreetCard> streetCardsThatCanHaveHouses = new ArrayList<>();
        for (PropertyCard propertyCard : ownedPropertyCards) {
            if (!(propertyCard instanceof StreetCard)) continue;

            StreetCard streetCard = (StreetCard) propertyCard;
            if (propertyCardController.allStreetsInGroupOwnedBy(streetCard, player)) {
                PropertyCard[] propertyCards = propertyCardController.getStreetsInSameGroup(streetCard);
                if (streetCardsThatCanHaveHouses.stream().noneMatch(streetCard1 -> {
                    for (PropertyCard c : propertyCards) {
                        if (c.getName().equals(streetCard1.getName())) {
                            return true;
                        }
                    }
                    return false;
                })) {
                    for (PropertyCard property : propertyCards) {
                        streetCardsThatCanHaveHouses.add((StreetCard) property);
                    }
                }
            }
        }

        ArrayList<StreetCard> streetOptions = new ArrayList<>();
        for (StreetCard street : streetCardsThatCanHaveHouses) {
            if (propertyCardController.housesWouldBeEvenlyPlacedInGroup(street, true) && street.getHouses() != 5) {
                streetOptions.add(street);
            }
        }
        String[] streetNames = new String[streetOptions.size()];
        for (int i = 0; i < streetOptions.size(); ++i) {
            streetNames[i] = streetOptions.get(i).getName();
        }

        if (streetNames.length < 1) return;

        String streetName = GUIInstance.getInstance().getUserSelection("Select where to purchase a house, ", streetNames);
        for (int i = 0; i < streetNames.length; i++) {
            if (streetNames[i].equals(streetName)) {
                propertyCardController.purchaseHouse(streetOptions.get(i), player);
            }
        }
    }

    public void sellHouse(Player player, PropertyCardController propertyCardController){
        final PropertyCard[] ownedPropertyCards = player.getOwnedPropertyCards(propertyCardController);

        int amountOfOwnedStreets = 0;
        for (PropertyCard card : ownedPropertyCards) {
            if (card instanceof StreetCard) ++amountOfOwnedStreets;
        }

        StreetCard[] ownedStreetCards = new StreetCard[amountOfOwnedStreets];
        int j = 0;
        for (PropertyCard card : ownedPropertyCards) {
            if (card instanceof StreetCard) {
                ownedStreetCards[j] = (StreetCard) card;
                ++j;
            }
        }

        ArrayList<StreetCard> streetCardsThatCanSellHouses = new ArrayList<>();
        for (StreetCard streetCard : ownedStreetCards) {
            if (streetCard.getHouses() > 0 && propertyCardController.housesWouldBeEvenlyPlacedInGroup(streetCard, false)) {
                streetCardsThatCanSellHouses.add(streetCard);
            }
        }

        String[] streetNames = new String[streetCardsThatCanSellHouses.size()];
        for (int i = 0; i < streetCardsThatCanSellHouses.size(); i++) {
            streetNames[i] = streetCardsThatCanSellHouses.get(i).getName();
        }

        if (streetNames.length < 1) return;

        String streetName = GUIInstance.getInstance().getUserSelection("Select property to sell house on, ", streetNames);
        for (int i = 0; i < streetNames.length; i++) {
            if (streetNames[i].equals(streetName)) {
                propertyCardController.sellHouse(streetCardsThatCanSellHouses.get(i), player);
            }
        }
    }

    public void mortgageProperty(Player player, PropertyCardController propertyCards){
        int amountOfPropertyMortgaged = 0;
            if(player.getOwnedPropertyCards(propertyCards).length > 0){
                String[] propertyNames = new String[player.getOwnedPropertyCards(propertyCards).length];
                for (int i = 0; i < propertyNames.length; i++) {
                    if (!player.getOwnedPropertyCards(propertyCards)[i].isMortgaged()){
                        propertyNames[i] = player.getOwnedPropertyCards(propertyCards)[i].getName();
                    }
                    else {
                        propertyNames[i] = player.getOwnedPropertyCards(propertyCards)[i].getName() + ": Is already mortgaged";
                        amountOfPropertyMortgaged++;
                    }
                }

                if (!(amountOfPropertyMortgaged == propertyNames.length)) {
                    String propertiesToMortgage = GUIInstance.getInstance().getUserSelection("Choose property to mortgage", propertyNames);
                    int mortgageFieldPosition = 0;
                    for (int i = 0; i < 28; i++) {
                        if (propertiesToMortgage.equals(propertyCards.getPropertyCards()[i].getName())) { mortgageFieldPosition = i;}
                    }
                    for (int i = 0; i < propertyNames.length; i++) {
                        if (propertiesToMortgage.equals(propertyNames[i])) {
                            propertyCards.mortgageProperty(player.getOwnedPropertyCards(propertyCards)[i], mortgageFieldPosition);
                        }
                    }
                }
                else {
                    playerLostController.removePlayer(player, propertyCards);
                }
            }

        }

    public void unmortgageProperty(Player player, PropertyCardController propertyCards){
        if(player.getOwnedPropertyCards(propertyCards).length > 0) {
            int x = 0;
            for (int i = 0; i < player.getOwnedPropertyCards(propertyCards).length; i++) {

                if (player.getOwnedPropertyCards(propertyCards)[i].isMortgaged()) {
                    x++;
                }
            }
            if (x > 0) {
                String[] propertyNames = new String[x];
                int y = 0;
                for (int i = 0; i < player.getOwnedPropertyCards(propertyCards).length; i++) {
                    if (player.getOwnedPropertyCards(propertyCards)[i].isMortgaged()) {
                        propertyNames[y] = player.getOwnedPropertyCards(propertyCards)[i].getName();
                        y++;
                    }

                }
                String propertiesToUnmortgage = GUIInstance.getInstance().getUserSelection("Choose property to unmortgage", propertyNames);
                int unmortgageFieldPosition = 0;
                for (int i = 0; i < 40; i++) {
                    if (propertiesToUnmortgage.equals(propertyCards.getPropertyCards()[i].getName())) { unmortgageFieldPosition = i;}
                }
                for (String propertyName : propertyNames) {
                    if (propertiesToUnmortgage.equals(propertyName)) {
                        for (int j = 0; j < player.getOwnedPropertyCards(propertyCards).length; j++) {
                            if (propertiesToUnmortgage.equals(player.getOwnedPropertyCards(propertyCards)[j].getName())) {
                                propertyCards.unmortgageProperty(player.getOwnedPropertyCards(propertyCards)[j], unmortgageFieldPosition, player.getOwnedPropertyCards(propertyCards)[j].getOwner().getName());
                            }

                        }

                    }
                }
            }
        }
    }
}
