import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class GameBoard {
    private GUI_Field[] fields;
    private GUI_Car[] playerCars;
    private GUI gui;

    public GameBoard() {
        fields = new GUI_Field[24];

        int i = 0;
        fields[i++] = new GUI_Start("GO", "2$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("BURGER JOINT", FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("PIZZA HOUSE", FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("CHANCE", "", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("CANDY STORE", FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("ICE CREAM PARLOUR", FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("VISITING", "", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("MUSEUM", FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("LIBRARY", FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("CHANCE", "Modtag: 200", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("SKATE PARK", FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("SWIMMING POOL", FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("PARKING", "", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("VIDEO GAME ARCADE", FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("MOVIE THEATRE", FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("CHANCE", "", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("TOY STORE", FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("PET STORE", FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Jail("C:\\Users\\ldeir\\OneDrive\\Desktop\\MSP73320hi35g4i8cc53bd00002230f3i7bb8efh8f.gif", "JAIL", "", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("BOWLING ALLEY", FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("THE ZOO", FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("CHANCE", "", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("PARK PLACE", FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i] = new GUI_Start("BOARDWALK", FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);

        gui = new GUI(fields);
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
