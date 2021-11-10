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
    private int activePlayer;
    private boolean gameRunning;

    public GameBoard() {
        this.gameBoardCreator = new GameBoardCreator();
        this.players = gameBoardCreator.getPlayers();
        this.fieldModel = new FieldModel();
        this.fieldController = new FieldController();
        this.gameRunning = true;
        Scanner scan = new Scanner(System.in);
        gameBoardCreator.getGUI().getUserButtonPressed("","Roll dice");

        while(gameRunning) {
            int roll = players[activePlayer].getDie().roll();
            players[activePlayer].getCar().moveCar(roll);
            fieldController.landOnField(players[activePlayer].getCar().getCarPosition(), fieldModel.FieldInfo()[activePlayer]);
            scan.next("");
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
