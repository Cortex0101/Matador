import gui_codebehind.GUI_Center;
import gui_main.GUI;

import java.awt.*;

public class GameBoard {
    private final FieldController fieldController;
    private final FieldModel fieldModel;
    private final Player[] players;
    private final PlayerController playerController;
    private final ChanceCardCreator cards;
    private ChanceCardsPileController chanceCardsPile;
    PropertyCard[] propertyCards = PropertyCardCreator.createPropertyCards();
    private PropertyCardController propertyCardController;
    private HandleStartOfTurnChoice handleStartOfTurnChoice;

    public GameBoard() {
        this.fieldModel = new FieldModel();
        GUIInstance.setFields(this.fieldModel);
        this.players = new PlayerCreator().getPlayers();
        this.propertyCardController = new PropertyCardController(propertyCards, fieldModel.FieldInfo());
        this.fieldController = new FieldController();
        this.handleStartOfTurnChoice = new HandleStartOfTurnChoice();
        playerController = new PlayerController(players);
        cards = new ChanceCardCreator();
        chanceCardsPile = new ChanceCardsPileController(cards.getChanceCards());
        GUI_Center.chanceCardText = "Chance";

    }

    public void play() {
        while (players.length > 1) {
            handleInJail();
            if (playerController.getActivePlayer().getIsActive()) {
                if (!playerController.getActivePlayer().getCar().isInJail()) {

                    String playerChoice = GUIInstance.getInstance().getUserSelection(playerController.getActivePlayer().getName() + ", it is your turn. Choose what to do.", "Roll", "Trade Properties", "Buy houses", "Sell houses", "Mortgage property", "Unmortgage Property");
                    switch (playerChoice) {
                        case "Roll": //it's not pretty but i didn't wanna mess with it just to cram it all in the HandleStartOfTurnChoice.roll method so it's staying like this for now
                            do {
                                playerController.getActivePlayer().getCar().moveCar(rollDie());
                                handleStartOfTurnChoice.roll(playerController);
                                fieldController.landOnField(playerController.getActivePlayer().getCar().getCarPosition(),
                                        chanceCardsPile,
                                        playerController.getActivePlayer(),
                                        players, propertyCardController, handleStartOfTurnChoice);
                            }
                            while (playerController.getActivePlayer().getRaffleCup().isDouble() && !playerController.getActivePlayer().getCar().isInJail());
                            playerController.getActivePlayer().resetRollCount();
                            if (playerController.getActivePlayer().getCar().hasPassedStart()) {
                                Bank.payPlayer(playerController.getActivePlayer(), 4000);
                            }
                            playerController.nextPlayerTurn();
                            break;
                        case "Trade Properties":
                            //TODO
                            break;
                        case "Buy houses":
                            handleStartOfTurnChoice.buyHouse(playerController.getActivePlayer(), propertyCardController);
                            break;
                        case "Sell houses":
                            handleStartOfTurnChoice.sellHouse(playerController.getActivePlayer(), propertyCardController);
                            break;
                        case "Mortgage property":
                            handleStartOfTurnChoice.mortgageProperty(playerController.getActivePlayer(), propertyCardController);
                            break;
                        case "Unmortgage Property":
                            handleStartOfTurnChoice.unmortgageProperty(playerController.getActivePlayer(), propertyCardController);
                            break;
                    }
                }
            }
        }
        GUIInstance.getInstance().showMessage(playerController.getActivePlayer().getName() + " is the last player standing and wins!");
        System.exit(0);
    }

    private void handleInJail() {
        if (playerController.getActivePlayer().getCar().isInJail()) {
            String doesntHaveGetOutOfJailCard = "";
            if(!playerController.getActivePlayer().hasGetOutOfJailCard()){
                doesntHaveGetOutOfJailCard = ": no card owned";
            }

            String handleJailChoice = GUIInstance.getInstance().getUserSelection(playerController.getActivePlayer().getName() + ", choose how you wanna get out of jail.", "pay 1000 kr.", "use chance card"+doesntHaveGetOutOfJailCard, "roll: " + (3 - playerController.getActivePlayer().getRollCount()) + " attempts left");
            if (handleJailChoice.equals("roll: " + (3 - playerController.getActivePlayer().getRollCount()) + " attempts left")) {
                handleJailChoice = "roll";
            }
            switch (handleJailChoice) {
                case "pay 1000 kr.":
                    if (playerController.getActivePlayer().getAccount().getBalance() > 1000) {
                        Bank.payBank(playerController.getActivePlayer(), 1000);
                        playerController.getActivePlayer().getCar().setInJail(false);
                    } else {
                        handleStartOfTurnChoice.mortgageProperty(playerController.getActivePlayer(), propertyCardController);
                    }
                    break;
                case "use chance card":
                    if (playerController.getActivePlayer().hasGetOutOfJailCard()) {
                        playerController.getActivePlayer().setHasGetOutOfJailCard(false);
                        playerController.getActivePlayer().getCar().setInJail(false);
                    }
                    break;
                case "roll":
                    if (playerController.getActivePlayer().getRaffleCup().isDouble()) {
                        playerController.getActivePlayer().getCar().setInJail(false);
                    } else if (!playerController.getActivePlayer().getRaffleCup().isDouble() && playerController.getActivePlayer().getRollCount() < 3) {
                        playerController.getActivePlayer().IncrementRollCount();
                        playerController.nextPlayerTurn();
                    } else {
                        if (playerController.getActivePlayer().getAccount().getBalance()<1000){
                            handleStartOfTurnChoice.mortgageProperty(playerController.getActivePlayer(),propertyCardController);
                        }
                        Bank.payBank(playerController.getActivePlayer(), 1000);
                        playerController.getActivePlayer().resetRollCount();
                        playerController.getActivePlayer().getCar().setInJail(false);
                    }
                    break;
            }
        }
    }

    private int rollDie() {
        String rollDiceText = ", it is your turn. roll the dice";
        if (playerController.getActivePlayer().getRollCount()>=1) {
            rollDiceText = ", it is your turn. roll the dice \n you have rolled doubles "+playerController.getActivePlayer().getRollCount()+" times this round";
        }
        GUIInstance.getInstance().getUserButtonPressed(playerController.getActivePlayer().getName() + rollDiceText, "Roll dice");
        int roll = playerController.getActivePlayer().getRaffleCup().shake();
        int[] eyes = playerController.getActivePlayer().getRaffleCup().getIndividualEyes();
        GUIInstance.getInstance().setDice(eyes[0], eyes[1]);
        return roll;
    }
}
