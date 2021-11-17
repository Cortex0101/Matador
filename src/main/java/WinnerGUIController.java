import gui_main.GUI;

public class WinnerGUIController {
    WinnerController winnerController;
    GUI gui;

    public WinnerGUIController(WinnerController winnerController, GUI gui) {
        this.winnerController = winnerController;
        this.gui = gui;
    }

    public void printPlayerInfoTable() {
        StringBuilder sb = new StringBuilder("Player balances:\n");
        for (Player player : winnerController.getPlayers()) {
            sb.append("Player \"").append(player.getName()).append("\"").append(" has a balance of ").append(player.getAccount().getBalance()).append("$");
            sb.append(" and owns fields of ").append(winnerController.getTotalPropertyValue(player)).append("$\n");
        }
        sb.append("Player " + "\"").append(winnerController.getWinner().getName()).append("\"").append(" won!");

        gui.showMessage(sb.toString());
        gui.getUserButtonPressed("", "Close game");
        gui.close();
    }
}
