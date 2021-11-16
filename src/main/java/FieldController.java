import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Street;

public class FieldController {
    private FieldModel fieldModel;

    public FieldController(){
        this.fieldModel = new FieldModel();
    }

    public void landOnField(int position,GUI_Field field, ChanceCardsPileController chanceCardsPileController, Player player, Player[] players, GameBoardCreator gameBoardCreator){
        switch (position) {
            case 1, 2, 4, 5, 7, 8, 10, 11, 13, 14, 16, 17, 19, 20, 22, 23 -> landOnOwnable(position,field, player, players, gameBoardCreator);
            case 3, 9, 15, 21 -> landOnChance(chanceCardsPileController, player);
            case 0, 6, 12 -> landOnFreeSpot();
            case 18 -> landOnGoToJail(player);
            default -> landOnFreeSpot();
        }

    }

    public void landOnOwnable(int position, GUI_Field field, Player activePlayer, Player[] players, GameBoardCreator gameBoardCreator){
        if (FieldModel.getFieldValue(position)>0){
            GUI_Street street = (GUI_Street) field;
            if (street.getOwnerName() != null) {
                landOnOwnedOwnable(position,street, activePlayer, players);
            }
            else
            {
                landOnUnownedOwnable(position,street, activePlayer, gameBoardCreator);
            }
        }
    }

    private void landOnUnownedOwnable(int position, GUI_Street street, Player player, GameBoardCreator gameBoardCreator){
        Bank.payBank(player, FieldModel.getFieldValue(position));
        street.setOwnerName(player.getName());
        GUI_Ownable gui_ownable = (GUI_Ownable) gameBoardCreator.getGUI().getFields()[position];
        gui_ownable.setBorder(player.getCar().getCarColor());
        System.out.println("Unowned Space: " + player.getAccount().getBalance());
        if(!player.getAccount().withdraw(0)){
            System.out.println("game over");
        }
    }
    private void landOnOwnedOwnable(int position, GUI_Street street, Player activePlayer, Player[] players){
        if(street.getOwnerName() != activePlayer.getName()) {
            for (int i = 0; i < players.length; i++) {
                if (players[i].getName().equals(street.getOwnerName())) {
                    Bank.transferMoney(activePlayer, players[i], FieldModel.getFieldValue(position));
                    System.out.println("Owned Space: " + activePlayer.getName() + " payed and now has " + activePlayer.getAccount().getBalance() + " and player " + players[i].getName() + " has " + players[i].getAccount().getBalance());
                    if (!activePlayer.getAccount().withdraw(0)) {
                        System.out.println("game over");
                    }
                }
            }
        }
    }

    public void landOnChance(ChanceCardsPileController chanceCardsPileController, Player player){
        ChanceCardController chanceCardController = chanceCardsPileController.drawCard();
        if (chanceCardController.model.getText().startsWith("Move to one of ")) {
            FreeFieldChanceCardController chanceCard = (FreeFieldChanceCardController) chanceCardController;
            chanceCard.action(player);
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
}
