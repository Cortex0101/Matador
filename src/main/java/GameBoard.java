import gui_codebehind.GUI_Center;

public class GameBoard {
    private final FieldController fieldController;
    private final Player[] players;
    private final PlayerController playerController;
    private final ChanceCardsPileController chanceCardsPile;
    PropertyCard[] propertyCards = PropertyCardCreator.createPropertyCards();
    private final PropertyCardController propertyCardController;
    private final HandleStartOfTurnChoice handleStartOfTurnChoice;

    public GameBoard() {
        FieldModel fieldModel = new FieldModel();
        GUIInstance.setFields(fieldModel);
        this.players = new PlayerCreator().getPlayers();
        this.propertyCardController = new PropertyCardController(propertyCards, fieldModel.FieldInfo());
        this.fieldController = new FieldController();
        this.handleStartOfTurnChoice = new HandleStartOfTurnChoice();
        playerController = new PlayerController(players);
        ChanceCardCreator cards = new ChanceCardCreator();
        chanceCardsPile = new ChanceCardsPileController(cards.getChanceCards());
        GUI_Center.chanceCardText = "Chance";

    }

    public void play() {
        int numberOfActivePlayers = players.length;
        while (numberOfActivePlayers
                > 1) {
            numberOfActivePlayers = 0;
            for (Player player : players) {
                if (player.getIsActive()) {
                    numberOfActivePlayers++;
                }
            }
            handleInJail();
            if (playerController.getActivePlayer().getIsActive()) {
                if (!playerController.getActivePlayer().getCar().isInJail()) {

                    String playerChoice = GUIInstance.getInstance().getUserSelection(playerController.getActivePlayer().getName() + ", it is your turn. Choose what to do.", "Roll", "Buy houses", "Sell houses", "Mortgage property", "Unmortgage Property");
                    switch (playerChoice) {
                        case "Roll" -> {
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
                        }
                        case "Buy houses" -> handleStartOfTurnChoice.buyHouse(playerController.getActivePlayer(), propertyCardController);
                        case "Sell houses" -> handleStartOfTurnChoice.sellHouse(playerController.getActivePlayer(), propertyCardController);
                        case "Mortgage property" -> handleStartOfTurnChoice.mortgageProperty(playerController.getActivePlayer(), propertyCardController);
                        case "Unmortgage Property" -> handleStartOfTurnChoice.unmortgageProperty(playerController.getActivePlayer(), propertyCardController);
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
