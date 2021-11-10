import gui_main.GUI;

import java.security.Guard;

public class GameBoardCreator {
    private FieldModel fieldModel;
    private GUI gui;
    private PlayerCreator players;

    public GameBoardCreator(){
        this.fieldModel = new FieldModel();
        gui = new GUI(fieldModel.FieldInfo());
        this.players = new PlayerCreator(gui);

    }

    public Player[] getPlayers() {
        return players.getPlayers();
    }

    public GUI getGUI(){
        return gui;
    }
}
