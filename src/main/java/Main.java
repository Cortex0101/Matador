import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_main.GUI;

public class Main {
    public static void main(String[] args) {
        GUI_Player player = new GUI_Player("John",20);
        GameBoard gameBoard = new GameBoard(new GUI_Car[]{player.getCar()});
    }
}