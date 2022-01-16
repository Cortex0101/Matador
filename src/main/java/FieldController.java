import gui_fields.*;

public class FieldController {
    private final PlayerLostController playerLostController;

    public FieldController(){
        this.playerLostController = new PlayerLostController();
    }

    public void landOnField(int position, ChanceCardsPileController chanceCardsPileController, Player player, Player[] players, PropertyCardController propertyCards, HandleStartOfTurnChoice handleStartOfTurnChoice){
        switch (position) {
            case 1, 3, 6, 8, 9, 11, 13, 14, 16, 18, 19, 21, 23, 24, 26, 27, 29, 31, 32, 34, 37, 39 -> landOnProperty(position, player, players, "Property", propertyCards, handleStartOfTurnChoice);
            case 5, 15, 25, 35 -> landOnProperty(position, player, players, "Shipping", propertyCards, handleStartOfTurnChoice);
            case 12, 28 -> landOnProperty(position, player, players, "Brewery", propertyCards, handleStartOfTurnChoice);
            case 2, 7, 17, 22, 33, 36 -> landOnChance(chanceCardsPileController, player);
            case 30 -> landOnGoToJail(player);
            case 4 -> landOnIncomeTax(player);
            case 38 -> landOnStateTax(player);
        }
    }

    public void landOnProperty(int position, Player activePlayer, Player[] players, String propertyType, PropertyCardController propertyCards, HandleStartOfTurnChoice handleStartOfTurnChoice){
        if (propertyType.equals("Property") && propertyCards.getCorrespondingPropertyCard(position).getOwner()!=null){
            landOnOwnedProperty(position, activePlayer, players, propertyType, propertyCards, handleStartOfTurnChoice);

        }
        else if (propertyType.equals("Shipping") && propertyCards.getCorrespondingPropertyCard(position).getOwner()!=null){
            landOnOwnedProperty(position, activePlayer, players, propertyType, propertyCards, handleStartOfTurnChoice);
        }
        else if (propertyType.equals("Brewery") && propertyCards.getCorrespondingPropertyCard(position).getOwner()!=null) {
            landOnOwnedProperty(position, activePlayer, players, propertyType, propertyCards, handleStartOfTurnChoice);
        }
        else{ landOnUnownedProperty(position, activePlayer, players, propertyCards, handleStartOfTurnChoice); }
    }

    private void landOnUnownedProperty(int position, Player activePlayer, Player[] players, PropertyCardController propertyCards, HandleStartOfTurnChoice handleStartOfTurnChoice){
        String unownedPropertyChoice = GUIInstance.getInstance().getUserSelection("Choose what to do with the property", "buy property", "auction property");
        if(unownedPropertyChoice.equals("auction property")){
            HandleAuction handleAuction = new HandleAuction();
            handleAuction.Auction(players);
            Bank.payBank(handleAuction.getHighestBidder(), handleAuction.getHighestbid());
            propertyCards.getCorrespondingPropertyCard(position).setOwner(handleAuction.getHighestBidder());
            GUI_Ownable gui_ownable = (GUI_Ownable) GUIInstance.getInstance().getFields()[position];
            gui_ownable.setBorder(handleAuction.getHighestBidder().getCar().getCarColor());
            gui_ownable.setOwnableLabel(activePlayer.getName());
            gui_ownable.setOwnerName(activePlayer.getName());
        }
        else {
            if (checkIfPlayerCanAffordCost(handleStartOfTurnChoice, activePlayer, propertyCards, FieldModel.getFieldPrice(position))) {
                Bank.payBank(activePlayer, FieldModel.getFieldPrice(position));
                propertyCards.getCorrespondingPropertyCard(position).setOwner(activePlayer);
                GUI_Ownable gui_ownable = (GUI_Ownable) GUIInstance.getInstance().getFields()[position];
                gui_ownable.setBorder(activePlayer.getCar().getCarColor());
                gui_ownable.setOwnableLabel(activePlayer.getName());
                gui_ownable.setOwnerName(activePlayer.getName());
            }
        }
    }
    private void landOnOwnedProperty(int position, Player activePlayer, Player[] players, String propertyType, PropertyCardController propertyCards, HandleStartOfTurnChoice handleStartOfTurnChoice){
                for (Player player : players) {
                    if (propertyType.equals("Property") && propertyCards.getCorrespondingPropertyCard(position).getOwner().equals(player)) {
                        StreetCard card = (StreetCard) propertyCards.getCorrespondingPropertyCard(position);
                        if (checkIfPlayerCanAffordCost(handleStartOfTurnChoice, player, propertyCards, propertyCards.getRent(card))) {
                            Bank.transferMoney(activePlayer, player, propertyCards.getRent(card));
                        }
                    }
                    if (propertyType.equals("Shipping") && propertyCards.getCorrespondingPropertyCard(position).getOwner().equals(player)) {
                        ShippingCard card = (ShippingCard) propertyCards.getCorrespondingPropertyCard(position);
                        if (checkIfPlayerCanAffordCost(handleStartOfTurnChoice, player, propertyCards, propertyCards.getRent(card))) {
                            Bank.transferMoney(activePlayer, player, propertyCards.getRent(card));
                        }
                    }
                    if (propertyType.equals("Brewery") && propertyCards.getCorrespondingPropertyCard(position).getOwner().equals(player)) {
                        BreweryCard card = (BreweryCard) propertyCards.getCorrespondingPropertyCard(position);
                        if (checkIfPlayerCanAffordCost(handleStartOfTurnChoice, player, propertyCards, propertyCards.getRent(card))) {
                            Bank.transferMoney(activePlayer, player, propertyCards.getRent(card) * activePlayer.getRaffleCup().getEyes());
                        }
                    }
                }
    }

    public void landOnChance(ChanceCardsPileController chanceCardsPileController, Player player){
        ChanceCard chanceCard = chanceCardsPileController.drawCard();
        chanceCard.action(player);
        chanceCard.display();
    }

    public void landOnGoToJail(Player player){
        player.getCar().setCarPosition(10);
        player.getCar().setInJail(true);
    }

    public void landOnIncomeTax(Player player){
        String incomeTaxText = GUIInstance.getInstance().getUserSelection("Choose how to pay for income tax","Pay 4000$","Pay 10% of currently owned money");
        if(incomeTaxText.equals(""))
            Bank.payBank(player, 4000);
        else{
            Bank.payBank(player, player.getAccount().getBalance()/10);
        }
    }

    public void landOnStateTax(Player player){
        Bank.payBank(player, 2000);
    }

    public boolean checkIfPlayerCanAffordCost(HandleStartOfTurnChoice handleStartOfTurnChoice, Player player, PropertyCardController propertyCards, int cost){
        while(player.getAccount().getBalance()<cost)
        {

            if(player.getOwnedPropertyCards(propertyCards).length > 0){
                handleStartOfTurnChoice.mortgageProperty(player, propertyCards);
            }
            else{
                player.setIsActive(false);
                playerLostController.removePlayer(player, propertyCards);
                return false;
            }
        }
        return true;
    }
}
