import gui_fields.*;
import gui_main.GUI;

import java.util.Scanner;

public class GameBoard {
    private FieldController fieldController;
    private FieldModel fieldModel;
    private GUI_Car[] playerCars;
    private Die die;
    private Player[] players;
    private GameBoardCreator gameBoardCreator;
    private PlayerController playerController;
    private int activePlayer;
    private boolean gameRunning;
    private FreeFieldChanceCardCreator freeFieldCards;
    ChanceCardsPileController chanceCardsPile;

    public GameBoard() {
        this.gameBoardCreator = new GameBoardCreator();
        this.players = gameBoardCreator.getPlayers();
        this.fieldModel = new FieldModel();
        this.fieldController = new FieldController();
        this.gameRunning = true;
        playerController = new PlayerController(players);

        freeFieldCards = new FreeFieldChanceCardCreator(this.gameBoardCreator.getGUI());
        chanceCardsPile = new ChanceCardsPileController(freeFieldCards.getFreeFieldChanceCardControllers());

        while(gameRunning) {
            if (playerController.getActivePlayer().getCar().isInJail()) {
                Bank.payBank(playerController.getActivePlayer(), 1);
                playerController.getActivePlayer().getCar().setInJail(false);
            }

            gameBoardCreator.getGUI().getUserButtonPressed("","Roll dice");
            int roll = playerController.getActivePlayer().getDie().roll();
            gameBoardCreator.getGUI().setDie(roll);

            playerController.getActivePlayer().getCar().moveCar(roll);
            if (playerController.getActivePlayer().getCar().hasPassedStart()) {
                Bank.payPlayer(playerController.getActivePlayer(), 2);
            }
            fieldController.landOnField(playerController.getActivePlayer().getCar().getCarPosition(), fieldModel.FieldInfo()[playerController.getActivePlayer().getCar().getCarPosition()], chanceCardsPile, playerController.getActivePlayer());

            playerController.nextPlayerTurn();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
