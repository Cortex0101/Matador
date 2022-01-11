import gui_codebehind.GUI_Center;
import gui_fields.GUI_Field;

public class GameBoard {
    private final FieldController fieldController;
    private final FieldModel fieldModel;
    private final Player[] players;
    private final PlayerController playerController;
    private final ChanceCardCreator freeFieldCards;
    private ChanceCardsPileController chanceCardsPile;
    PropertyCard[] propertyCards = PropertyCardCreator.createPropertyCards();
    private PropertyCardController propertyCardController;

    public GameBoard() {
        this.fieldModel = new FieldModel();
        GUIInstance.setFields(this.fieldModel);
        this.players = new PlayerCreator().getPlayers();
        this.propertyCardController = new PropertyCardController(propertyCards, fieldModel.FieldInfo());
        this.fieldController = new FieldController();
        playerController = new PlayerController(players);
        freeFieldCards = new ChanceCardCreator();
        chanceCardsPile = new ChanceCardsPileController(freeFieldCards.getFreeFieldChanceCardControllers());
        chanceCardsPile.addCard(new OutOfJailChanceCardController());
        GUI_Center.chanceCardText = "Chance";
    }

    public void play() {
        while(players.length > 1) {
            handleInJail();
            if (playerController.getActivePlayer().getIsActive()) {
                if (!playerController.getActivePlayer().getCar().isInJail()) {
                    do {
                        playerController.getActivePlayer().getCar().moveCar(rollDie());
                        playerController.getActivePlayer().IncrementRollCount();
                        if (playerController.getActivePlayer().getRollCount() == 3) {
                            playerController.getActivePlayer().getCar().setInJail(true);
                        }
                        fieldController.landOnField(playerController.getActivePlayer().getCar().getCarPosition(),
                                chanceCardsPile,
                                playerController.getActivePlayer(),
                                players, propertyCardController);
                    }
                    while (playerController.getActivePlayer().getRaffleCup().isDouble() && !playerController.getActivePlayer().getCar().isInJail());
                    playerController.getActivePlayer().resetRollCount();
                }
                if (playerController.getActivePlayer().getCar().hasPassedStart()) {
                    Bank.payPlayer(playerController.getActivePlayer(), 2);
                }
            }
            playerController.nextPlayerTurn();

        }
        GUIInstance.getInstance().showMessage(playerController.getActivePlayer().getName()+" is the last player standing and wins!");
        System.exit(0);
    }

    private void handleInJail() {
        boolean pay = false; // TODO
        boolean chanceCard = false; // TODO
        boolean roll = false; // TODO
        boolean isDouble = false; // TODO


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
        int[] eyes = playerController.getActivePlayer().getRaffleCup().getIndividualEyes();
        GUIInstance.getInstance().setDice(eyes[0], eyes[1]);
        return roll;
    }
}
