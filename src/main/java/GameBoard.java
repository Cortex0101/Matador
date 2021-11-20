import gui_fields.*;

public class GameBoard {
    private FieldController fieldController;
    private FieldModel fieldModel;
    private GUI_Car[] playerCars;
    private Die die;
    private Player[] players;
    private GUIInstance GUIInstance;
    private PlayerController playerController;
    private int activePlayer;
    private boolean gameRunning;
    private FreeFieldChanceCardCreator freeFieldCards;
    ChanceCardsPileController chanceCardsPile;

    public GameBoard() {
        this.GUIInstance = new GUIInstance();
        this.players = new PlayerCreator(GUIInstance.getGUI()).getPlayers();
        this.fieldModel = new FieldModel();
        this.fieldController = new FieldController();
        this.gameRunning = true;
        playerController = new PlayerController(players);
        Bank.winnerController = new WinnerGUIController(new WinnerController(players, fieldModel), this.GUIInstance.getGUI());


        freeFieldCards = new FreeFieldChanceCardCreator(this.GUIInstance.getGUI());
        chanceCardsPile = new ChanceCardsPileController(freeFieldCards.getFreeFieldChanceCardControllers());
        chanceCardsPile.addCard(new OutOfJailChanceCardController(this.GUIInstance.getGUI()));

        while(gameRunning) {
            if (playerController.getActivePlayer().getCar().isInJail() && !playerController.getActivePlayer().hasGetOutOfJailCard()) {
                Bank.payBank(playerController.getActivePlayer(), 1);
                playerController.getActivePlayer().getCar().setInJail(false);
            }
            else if (playerController.getActivePlayer().getCar().isInJail() && playerController.getActivePlayer().hasGetOutOfJailCard()){
                playerController.getActivePlayer().setHasGetOutOfJailCard(false);
                playerController.getActivePlayer().getCar().setInJail(false);
            }

            GUIInstance.getGUI().getUserButtonPressed(playerController.getActivePlayer().getName() + ", it is your turn.","Roll dice");
            int roll = playerController.getActivePlayer().getDie().roll();
            GUIInstance.getGUI().setDie(roll);
            playerController.getActivePlayer().getCar().moveCar(roll);
            if (playerController.getActivePlayer().getCar().hasPassedStart()) {
                Bank.payPlayer(playerController.getActivePlayer(), 2);
            }
            fieldController.landOnField(playerController.getActivePlayer().getCar().getCarPosition(), fieldModel.FieldInfo()[playerController.getActivePlayer().getCar().getCarPosition()], chanceCardsPile, playerController.getActivePlayer(), players, GUIInstance);
            playerController.nextPlayerTurn();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
