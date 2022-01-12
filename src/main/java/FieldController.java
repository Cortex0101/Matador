import gui_fields.*;

public class FieldController {
    private final PlayerLostController playerLostController;

    public FieldController(){
        this.playerLostController = new PlayerLostController();
    }

    public void landOnField(int position, ChanceCardsPileController chanceCardsPileController, Player player, Player[] players, PropertyCardController propertyCards){
        switch (position) {
            case 1, 3, 6, 8, 9, 11, 13, 14, 16, 18, 19, 21, 23, 24, 26, 27, 29, 31, 32, 34, 37, 39 -> landOnProperty(position, player, players, "Property", propertyCards);
            case 5, 15, 25, 35 -> landOnProperty(position, player, players, "Shipping", propertyCards);
            case 12, 28 -> landOnProperty(position, player, players, "Brewery", propertyCards);
            case 2, 7, 17, 22, 33, 36 -> landOnChance();
            case 30 -> landOnGoToJail(player);
            case 4 -> landOnIncomeTax(player);
            case 38 -> landOnStateTax(player);
        }
    }

    public void landOnProperty(int position, Player activePlayer, Player[] players, String propertyType, PropertyCardController propertyCards){
        if (propertyType.equals("Property") && propertyCards.getCorrespondingPropertyCard(position).getOwner()!=null){
            landOnOwnedProperty(position, activePlayer, players, propertyType, propertyCards);

        }
        else if (propertyType.equals("Shipping") && propertyCards.getCorrespondingPropertyCard(position).getOwner()!=null){
            landOnOwnedProperty(position, activePlayer, players, propertyType, propertyCards);
        }
        else if (propertyType.equals("Brewery") && propertyCards.getCorrespondingPropertyCard(position).getOwner()!=null) {
            landOnOwnedProperty(position, activePlayer, players, propertyType, propertyCards);
        }
        else{ landOnUnownedProperty(position, activePlayer, propertyCards); }
    }

    private void landOnUnownedProperty(int position, Player player, PropertyCardController propertyCards){
        if(checkIfPlayerCanAffordCost(player,propertyCards, FieldModel.getFieldPrice(position))) {
            Bank.payBank(player, FieldModel.getFieldPrice(position));
            propertyCards.getCorrespondingPropertyCard(position).setOwner(player);
            GUI_Ownable gui_ownable = (GUI_Ownable) GUIInstance.getInstance().getFields()[position];
            gui_ownable.setBorder(player.getCar().getCarColor());
            gui_ownable.setOwnerName(player.getName());
        }
    }
    private void landOnOwnedProperty(int position, Player activePlayer, Player[] players, String propertyType, PropertyCardController propertyCards){
                for (Player player : players) {
                    if (propertyType.equals("Property") && propertyCards.getCorrespondingPropertyCard(position).getOwner().equals(player)) {
                        StreetCard card = (StreetCard) propertyCards.getCorrespondingPropertyCard(position);
                        if (checkIfPlayerCanAffordCost(player, propertyCards, FieldModel.getFieldPrice(position))) {
                            Bank.transferMoney(activePlayer, player, propertyCards.getRent(card));
                        }
                    }
                    if (propertyType.equals("Shipping") && propertyCards.getCorrespondingPropertyCard(position).getOwner().equals(player)) {
                        ShippingCard card = (ShippingCard) propertyCards.getCorrespondingPropertyCard(position);
                        if (checkIfPlayerCanAffordCost(player, propertyCards, FieldModel.getFieldPrice(position))) {
                            Bank.transferMoney(activePlayer, player, propertyCards.getRent(card));
                        }
                    }
                    if (propertyType.equals("Brewery") && propertyCards.getCorrespondingPropertyCard(position).getOwner().equals(player)) {
                        BreweryCard card = (BreweryCard) propertyCards.getCorrespondingPropertyCard(position);
                        if (checkIfPlayerCanAffordCost(player, propertyCards, FieldModel.getFieldPrice(position))) {
                            Bank.transferMoney(activePlayer, player, propertyCards.getRent(card) * activePlayer.getRaffleCup().getEyes());
                        }
                    }
                }
    }

    public void landOnChance(){
        //TODO
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

    public boolean checkIfPlayerCanAffordCost(Player player, PropertyCardController propertyCards, int cost){
        while(player.getAccount().getBalance()<cost)
        {

            if(player.getOwnedPropertyCards(propertyCards).length > 0){
                String[] propertyNames = new String[player.getOwnedPropertyCards(propertyCards).length];
                for (int i = 0; i < propertyNames.length; i++) {
                    if (!player.getOwnedPropertyCards(propertyCards)[i].isMortgaged()){
                        propertyNames[i] = player.getOwnedPropertyCards(propertyCards)[i].getName();
                    }
                    else {
                        propertyNames[i] = player.getOwnedPropertyCards(propertyCards)[i].getName() + ": Is already mortgaged";
                    }
                }
                    String mortgagedProperty = GUIInstance.getInstance().getUserSelection("Choose property to mortgage", propertyNames);
                    for (int i = 0; i < propertyNames.length; i++) {
                        if (mortgagedProperty.equals(propertyNames[i]) && !mortgagedProperty.contains(": Is already mortgaged")) {
                            propertyCards.mortgageProperty(player.getOwnedPropertyCards(propertyCards)[i]);
                        }
                    }
                if(propertyNames.length > 0) {
                    System.out.println(player.getName());
                    for (int i = 0; i < propertyNames.length; i++) {
                        System.out.println(propertyNames[i]);
                    }
                    System.out.println("chosen mort:" + mortgagedProperty);
                }
                else {
                    player.setIsActive(false);
                    playerLostController.removePlayer(player, propertyCards);
                    return false;
                }
            }
            else{
                player.setIsActive(false);
                playerLostController.removePlayer(player, propertyCards);
                return false;
            }
        }
        return true;
    }
}//TODO replace propertycard casting with instanceof
//TODO condense ownedproperty
//TODO split checkifplayercanafford
//TODO rename isActive in player to something more fitting
