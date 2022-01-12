public class HandleStartOfTurnChoice {

    public void roll(PlayerController playerController){
        playerController.getActivePlayer().IncrementRollCount();
        if (playerController.getActivePlayer().getRollCount() == 3) {
            playerController.getActivePlayer().getCar().setInJail(true);
        }
    }

    public void buyHouse(){
        //TODO
    }

    public void sellHouse(){
        //TODO
    }

    public void mortgageProperty(Player player, PropertyCardController propertyCards){
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
                String propertiesToMortgage = GUIInstance.getInstance().getUserSelection("Choose property to mortgage", propertyNames);
                for (int i = 0; i < propertyNames.length; i++) {
                    if (propertiesToMortgage.equals(propertyNames[i])){propertyCards.mortgageProperty(player.getOwnedPropertyCards(propertyCards)[i]);}
                }
            }

        }

    public void unmortgageProperty(Player player, PropertyCardController propertyCards){
        System.out.println("test1");
        if(player.getOwnedPropertyCards(propertyCards).length > 0){
            int x = 0;
            System.out.println("test2");
            for (int i = 0; i < player.getOwnedPropertyCards(propertyCards).length; i++) {

                if (player.getOwnedPropertyCards(propertyCards)[i].isMortgaged()){
                    x++;
                }
            }
            String[] propertyNames = new String[x];
            int y = 0;
            for (int i = 0; i < player.getOwnedPropertyCards(propertyCards).length; i++) {
                if (player.getOwnedPropertyCards(propertyCards)[i].isMortgaged()) {
                    System.out.println(player.getOwnedPropertyCards(propertyCards)[i].getName());
                    propertyNames[y] = player.getOwnedPropertyCards(propertyCards)[i].getName();
                    y++;
                }

            }
            String propertiesToUnmortgage = GUIInstance.getInstance().getUserSelection("Choose property to unmortgage", propertyNames);
            for (int i = 0; i < propertyNames.length; i++) {
                if (propertiesToUnmortgage.equals(propertyNames[i])){
                    for (int j = 0; j < player.getOwnedPropertyCards(propertyCards).length; j++) {
                        if(propertiesToUnmortgage.equals(player.getOwnedPropertyCards(propertyCards)[j].getName())){ propertyCards.unmortgageProperty(player.getOwnedPropertyCards(propertyCards)[j]); }

                    }

                }
            }
        }


    }

    public void tradeProperty(){
        //TODO
    }
}
