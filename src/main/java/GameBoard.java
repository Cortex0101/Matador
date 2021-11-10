import gui_fields.*;
import gui_main.GUI;

import java.awt.*;
import java.lang.reflect.Field;

public class GameBoard {
    private GUI_Field[] fields;
    private GUI_Car[] playerCars;
    private GUI gui;
    private FieldModel field;

    public GameBoard() {
        this.field = new FieldModel();
        gui = new GUI(field.FieldInfo());
        PlayerCreator players = new PlayerCreator(gui);
        System.out.println(players.getPlayerCount());
        System.out.println(players.getPlayerNames()[0]);
        System.out.println(players.getPlayerNames()[1]);

        Player player = players.getPlayers()[0];
        player.getCar().moveCar(25);
        System.out.println(player.getCar().hasPassedStart());
        player.getCar().moveCar(1);
        System.out.println(player.getCar().hasPassedStart());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
