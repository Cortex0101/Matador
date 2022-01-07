import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Street;

public class FieldController {
    private FieldModel fieldModel;
    private PropertyCardController propertyCardController;

    public FieldController(PropertyCardController propertyCardController){
        this.fieldModel = new FieldModel();
        this.propertyCardController = propertyCardController;
    }

    public void landOnField(int position,GUI_Field field, ChanceCardsPileController chanceCardsPileController, Player player, Player[] players){
        switch (position) {
            case 1, 3, 6, 8, 9, 10, 11, 13, 14, 16, 18, 19, 21, 23, 24, 26, 27, 29, 31, 32, 34, 37, 39 -> landOnProperty(position,field, player, players, "Property");
            case 5, 15, 25, 35 -> landOnProperty(position,field, player, players, "Shipping");
            case 12, 28 -> landOnProperty(position,field, player, players, "Brewery");
            case 2, 7, 17, 22, 33, 36 -> landOnChance(chanceCardsPileController, players, player);
            case 30 -> landOnGoToJail(player);
            case 4 -> landOnIncomeTax(player);
            case 38 -> landOnStateTax(player);
            default -> landOnFreeSpot();
        }
    }

    public void landOnProperty(int position, GUI_Field field, Player activePlayer, Player[] players, String propertyType){
        if (FieldModel.getFieldPrice(position)>0){
            GUI_Street street = (GUI_Street) field;
            if (street.getOwnerName() != null) {
                landOnOwnedProperty(position,street, activePlayer, players);
            } else {
                landOnUnownedProperty(position,street, activePlayer);
            }
        }
    }

    private void landOnUnownedProperty(int position, GUI_Street street, Player player){
        Bank.payBank(player, FieldModel.getFieldPrice(position));
        street.setOwnerName(player.getName());
        GUI_Ownable gui_ownable = (GUI_Ownable) GUIInstance.getInstance().getFields()[position];
        gui_ownable.setOwnerName(player.getName());
        gui_ownable.setBorder(player.getCar().getCarColor());
        System.out.println("Unowned Space: " + player.getAccount().getBalance());
        if(!player.getAccount().withdraw(0)){
            System.out.println("game over");
        }
    }
    private void landOnOwnedProperty(int position, GUI_Street street, Player activePlayer, Player[] players){
        if(!street.getOwnerName().equals(activePlayer.getName())) {
            for (Player player : players) {
                if (player.getName().equals(street.getOwnerName())) {
                    Bank.transferMoney(activePlayer, player, FieldModel.getFieldPrice(position));
                    System.out.println("Owned Space: " + activePlayer.getName() + " payed and now has " + activePlayer.getAccount().getBalance() + " and player " + player.getName() + " has " + player.getAccount().getBalance());
                    if (!activePlayer.getAccount().withdraw(0)) {
                        System.out.println("game over");
                    }
                }
            }
        }
    }

    public void landOnChance(ChanceCardsPileController chanceCardsPileController, Player[] players, Player player){
        ChanceCardController chanceCardController = chanceCardsPileController.drawCard();
        if (chanceCardController.model.getText().startsWith("Move to one of ")) {
            FreeFieldChanceCardController chanceCard = (FreeFieldChanceCardController) chanceCardController;
            chanceCard.action(players, player);
        }
        else if (chanceCardController.model.getText().startsWith("Get out of ")) {
            OutOfJailChanceCardController chanceCard = (OutOfJailChanceCardController) chanceCardController;
            chanceCard.action(player);
        }
        System.out.println("Chance");
    }

    public void landOnFreeSpot(){
        System.out.println("FreeSpot");
    }

    public void landOnGoToJail(Player player){
        player.getCar().setCarPosition(6);
        player.getCar().setInJail(true);
        System.out.println("Jail");
    }

    public void landOnIncomeTax(Player player){
        boolean option1 = true;
        boolean option2 = false;
        if(option1)
        Bank.payBank(player, 4000);
        else if(option2){
            Bank.payBank(player, player.getAccount().getBalance()/10);
        }

    }

    public void landOnStateTax(Player player){
        Bank.payBank(player, 2000);
    }
}
