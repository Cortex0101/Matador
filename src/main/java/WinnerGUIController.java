import gui_main.GUI;

public class WinnerGUIController {
    WinnerController winnerController;
    GUI gui;

    public WinnerGUIController(WinnerController winnerController, GUI gui) {
        this.winnerController = winnerController;
        this.gui = gui;
    }

    public void printPlayerInfoTable() {
        gui.showMessage("Player1: 5\n Player2: 5\n Player3: 5\n Player4: 5");
    }
}
