public class PayPerHouseChanceCard extends ChanceCard{
    int costPerHouse;

    public PayPerHouseChanceCard(String text, int costPerHouse){
        super(text);
        this.costPerHouse = costPerHouse;

    }

    public void action(Player player, PropertyCardController propertyCardController) {
            int ownedStreetCount = 0;
            for (int i = 0; i < propertyCardController.getPropertyCards().length; i++) {
                if (propertyCardController.getPropertyCards()[i] instanceof StreetCard && propertyCardController.getPropertyCards()[i].getOwner().equals(player)) {
                    ownedStreetCount += ((StreetCard) propertyCardController.getPropertyCards()[i]).getHouses();
                }
            }
        if (player.getAccount().getBalance()>costPerHouse) {
            Bank.payBank(player, ownedStreetCount * costPerHouse);
        }
    }
}
