import gui_codebehind.GUI_Center;
import gui_fields.*;

public class GameBoard {
    private final FieldController fieldController;
    private final FieldModel fieldModel;
    private final Player[] players;
    private final PlayerController playerController;
    private final boolean gameRunning;
    private final FreeFieldChanceCardCreator freeFieldCards;
    ChanceCardsPileController chanceCardsPile;

    public GameBoard() {
        this.players = new PlayerCreator().getPlayers();
        this.fieldModel = new FieldModel();
        this.fieldController = new FieldController();
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
            playerController.getActivePlayer().getCar().moveCar(rollDie());

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
        if (playerController.getActivePlayer().getCar().isInJail() && !playerController.getActivePlayer().hasGetOutOfJailCard()) {
            Bank.payBank(playerController.getActivePlayer(), 1);
            playerController.getActivePlayer().getCar().setInJail(false);
        }
        else if (playerController.getActivePlayer().getCar().isInJail() && playerController.getActivePlayer().hasGetOutOfJailCard()){
            playerController.getActivePlayer().setHasGetOutOfJailCard(false);
            playerController.getActivePlayer().getCar().setInJail(false);
        }
    }

    private int rollDie() {
        GUIInstance.getInstance().getUserButtonPressed(playerController.getActivePlayer().getName() + ", it is your turn.","Roll dice");
        int roll = playerController.getActivePlayer().getDie().roll();
        GUIInstance.getInstance().setDie(roll);
        return roll;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
