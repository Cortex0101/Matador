import gui_fields.*;

import java.awt.*;

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
            case 2, 7, 17, 22, 33, 36 -> landOnChance();
            case 30 -> landOnGoToJail(player);
            case 4 -> landOnIncomeTax(player);
            case 38 -> landOnStateTax(player);
            default -> landOnFreeSpot();
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
        else{ landOnUnownedProperty(position, activePlayer, propertyCards, handleStartOfTurnChoice); }
    }

    private void landOnUnownedProperty(int position, Player player, PropertyCardController propertyCards, HandleStartOfTurnChoice handleStartOfTurnChoice){
        if(checkIfPlayerCanAffordCost(handleStartOfTurnChoice, player,propertyCards, FieldModel.getFieldPrice(position))) {
            Bank.payBank(player, FieldModel.getFieldPrice(position));
            propertyCards.getCorrespondingPropertyCard(position).setOwner(player);
            GUI_Ownable gui_ownable = (GUI_Ownable) GUIInstance.getInstance().getFields()[position];
            gui_ownable.setBorder(player.getCar().getCarColor());
            gui_ownable.setOwnerName(player.getName());
        }
    }
    private void landOnOwnedProperty(int position, Player activePlayer, Player[] players, String propertyType, PropertyCardController propertyCards, HandleStartOfTurnChoice handleStartOfTurnChoice){
                for (Player player : players) {
                    if (propertyType.equals("Property") && propertyCards.getCorrespondingPropertyCard(position).getOwner().equals(player)) {
                        StreetCard card = (StreetCard) propertyCards.getCorrespondingPropertyCard(position);
                        if (checkIfPlayerCanAffordCost(handleStartOfTurnChoice, player, propertyCards, FieldModel.getFieldPrice(position))) {
                            Bank.transferMoney(activePlayer, player, propertyCards.getRent(card));
                        }
                    }
                    if (propertyType.equals("Shipping") && propertyCards.getCorrespondingPropertyCard(position).getOwner().equals(player)) {
                        ShippingCard card = (ShippingCard) propertyCards.getCorrespondingPropertyCard(position);
                        if (checkIfPlayerCanAffordCost(handleStartOfTurnChoice, player, propertyCards, FieldModel.getFieldPrice(position))) {
                            Bank.transferMoney(activePlayer, player, propertyCards.getRent(card));
                        }
                    }
                    if (propertyType.equals("Brewery") && propertyCards.getCorrespondingPropertyCard(position).getOwner().equals(player)) {
                        BreweryCard card = (BreweryCard) propertyCards.getCorrespondingPropertyCard(position);
                        if (checkIfPlayerCanAffordCost(handleStartOfTurnChoice, player, propertyCards, FieldModel.getFieldPrice(position))) {
                            Bank.transferMoney(activePlayer, player, propertyCards.getRent(card) * activePlayer.getRaffleCup().getEyes());
                        }
                    }
                }
    }

    public void landOnChance(){
        //TODO
    }

    public void landOnFreeSpot(){
    }

    public void landOnGoToJail(Player player){
        player.getCar().setCarPosition(10);
        player.getCar().setInJail(true);
    }

    public void landOnIncomeTax(Player player){
        boolean option1 = true; //TODO
        boolean option2 = false; //TODO
        if(option1)
            Bank.payBank(player, 4000);
        else if(option2){
            Bank.payBank(player, player.getAccount().getBalance()/10); //TODO total worth of all owned properties
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
