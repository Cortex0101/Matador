import gui_codebehind.GUI_Center;
import gui_fields.*;

public class GameBoard {
    private final FieldController fieldController;
    private final FieldModel fieldModel;
    private final Player[] players;
    private final PlayerController playerController;
    private final boolean gameRunning;
    private final FreeFieldChanceCardCreator freeFieldCards;
    private ChanceCardsPileController chanceCardsPile;
    PropertyCard[] propertyCards = PropertyCardCreator.createPropertyCards();
    private PropertyCardController propertyCardController = new PropertyCardController(propertyCards);

    public GameBoard() {
        this.players = new PlayerCreator().getPlayers();
        this.fieldModel = new FieldModel();
        this.fieldController = new FieldController(propertyCardController);
        this.gameRunning = true;
        playerController = new PlayerController(players);
        Bank.winnerController = new WinnerGUIController(new WinnerController(players));
        freeFieldCards = new FreeFieldChanceCardCreator();
        chanceCardsPile = new ChanceCardsPileController(freeFieldCards.getFreeFieldChanceCardControllers());
        chanceCardsPile.addCard(new OutOfJailChanceCardController());
        GUI_Center.chanceCardText = "Chance";
    }

    public void play() {
        while(true) {
            handleInJail();
            if(!playerController.getActivePlayer().getCar().isInJail()) {
                do {
                    playerController.getActivePlayer().getCar().moveCar(rollDie());
                    playerController.getActivePlayer().IncrementRollCount();
                    if(playerController.getActivePlayer().getRollCount() == 3){
                        playerController.getActivePlayer().getCar().setInJail(true);
                    }
                }
                while(playerController.getActivePlayer().getRaffleCup().isDouble() && !playerController.getActivePlayer().getCar().isInJail());
                playerController.getActivePlayer().resetRollCount();
            }
            if (playerController.getActivePlayer().getCar().hasPassedStart()) {
                Bank.payPlayer(playerController.getActivePlayer(), 2);
            }

            fieldController.landOnField(playerController.getActivePlayer().getCar().getCarPosition(),
                    fieldModel.FieldInfo()[playerController.getActivePlayer().getCar().getCarPosition()],
                    chanceCardsPile,
                    playerController.getActivePlayer(),
                    players);

            playerController.nextPlayerTurn();
        }
    }

    private void handleInJail() {
        //Temporary boolean to simulate the different buttons. will be replaced with button inputs later
        boolean pay = false;
        boolean chanceCard = false; // can only be true if the player has a getOutOfJailFreeCard. the check for playerController.getActivePlayer().hasGetOutOfJailCard() will be done with the button and be greyed out if false
        boolean roll = false;
        boolean isDouble = false;


        if (playerController.getActivePlayer().getCar().isInJail() && chanceCard) {
            playerController.getActivePlayer().setHasGetOutOfJailCard(false);
            playerController.getActivePlayer().getCar().setInJail(false);

        }

        else if (playerController.getActivePlayer().getCar().isInJail() && roll){
            if (isDouble){ playerController.getActivePlayer().getCar().setInJail(false); }
            else if (!isDouble && playerController.getActivePlayer().getRollCount() < 3) { playerController.getActivePlayer().IncrementRollCount();
                System.out.println(playerController.getActivePlayer().getRollCount());}
            else {
                Bank.payBank(playerController.getActivePlayer(), 1000);
                playerController.getActivePlayer().resetRollCount();
                playerController.getActivePlayer().getCar().setInJail(false);
            }
        }

        else if (playerController.getActivePlayer().getCar().isInJail() && pay){
            Bank.payBank(playerController.getActivePlayer(), 1000);
            playerController.getActivePlayer().getCar().setInJail(false);
        }
    }

    private int rollDie() {
        GUIInstance.getInstance().getUserButtonPressed(playerController.getActivePlayer().getName() + ", it is your turn.","Roll dice");
        int roll = playerController.getActivePlayer().getRaffleCup().shake();
        GUIInstance.getInstance().setDie(roll);
        return roll;
    }
}
