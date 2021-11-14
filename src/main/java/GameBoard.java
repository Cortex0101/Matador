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

        FieldSelector fs = new FieldSelector(fieldModel.FieldInfo(), gameBoardCreator.getGUI());

        ChanceCardController[] cardControllers = new ChanceCardController[]{
                new ChanceCardController(new ChanceCardModel("A"), this.gameBoardCreator.getGUI()),
                new ChanceCardController(new ChanceCardModel("B"), this.gameBoardCreator.getGUI()),
                new ChanceCardController(new ChanceCardModel("C"), this.gameBoardCreator.getGUI()),
                new ChanceCardController(new ChanceCardModel("D"), this.gameBoardCreator.getGUI()),
                new ChanceCardController(new ChanceCardModel("E"), this.gameBoardCreator.getGUI())
        };
        ChanceCardsPileController pile = new ChanceCardsPileController(cardControllers);

        FreeFieldChanceCardController ffccc = new FreeFieldChanceCardController(this.gameBoardCreator.getGUI(), new GUI_Field[]{this.fieldModel.FieldInfo()[1], this.fieldModel.FieldInfo()[2]});

        while(gameRunning) {
            gameBoardCreator.getGUI().getUserButtonPressed("","Roll dice");
            int roll = playerController.getActivePlayer().getDie().roll();
            gameBoardCreator.getGUI().setDie(roll);

            ffccc.action();

            playerController.getActivePlayer().getCar().moveCar(roll);
            fieldController.landOnField(playerController.getActivePlayer().getCar().getCarPosition(), fieldModel.FieldInfo()[playerController.getActivePlayer().getCar().getCarPosition()]);
            playerController.nextPlayerTurn();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
