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

    public GameBoard() {
        this.gameBoardCreator = new GameBoardCreator();
        this.players = gameBoardCreator.getPlayers();
        this.fieldModel = new FieldModel();
        this.fieldController = new FieldController();
        this.gameRunning = true;
        playerController = new PlayerController(players);

        FreeFieldChanceCardCreator freeFieldCards = new FreeFieldChanceCardCreator(this.gameBoardCreator.getGUI());
        ChanceCardsPileController chanceCardsPile = new ChanceCardsPileController(freeFieldCards.getFreeFieldChanceCardControllers());

        while(gameRunning) {
            gameBoardCreator.getGUI().getUserButtonPressed("","Roll dice");
            int roll = playerController.getActivePlayer().getDie().roll();
            gameBoardCreator.getGUI().setDie(roll);


            FreeFieldChanceCardController chanceCard = (FreeFieldChanceCardController) chanceCardsPile.drawCard();
            chanceCard.action(playerController.getActivePlayer());

            //playerController.getActivePlayer().getCar().moveCar(roll);
            fieldController.landOnField(playerController.getActivePlayer().getCar().getCarPosition(), fieldModel.FieldInfo()[playerController.getActivePlayer().getCar().getCarPosition()]);
            playerController.nextPlayerTurn();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
