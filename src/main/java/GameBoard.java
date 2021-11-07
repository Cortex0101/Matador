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
        fields[i++] = new GUI_Start("BURGER JOINT", "1$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("PIZZA HOUSE", "1$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("CHANCE", "", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("CANDY STORE", "1$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("ICE CREAM PARLOUR", "1$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("VISITING", "", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("MUSEUM", "2$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("LIBRARY", "2$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("CHANCE", "Modtag: 200", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("SKATE PARK", "2$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("SWIMMING POOL", "2$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("PARKING", "", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("VIDEO GAME ARCADE", "3$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("MOVIE THEATRE", "3$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("CHANCE", "", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("TOY STORE", "3$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("PET STORE", "3$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Jail("C:\\Users\\ldeir\\OneDrive\\Desktop\\MSP73320hi35g4i8cc53bd00002230f3i7bb8efh8f.gif", "JAIL", "", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("BOWLING ALLEY", "4$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("THE ZOO", "4$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("CHANCE", "", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Start("PARK PLACE", "4$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i] = new GUI_Start("BOARDWALK", "4$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);

        gui = new GUI(fields);

        GUI_Player player = new GUI_Player("John", 20);
        CarController carController = new CarController(gui.getFields(),player);
        carController.moveCar(2);
        carController.moveCar(3);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
