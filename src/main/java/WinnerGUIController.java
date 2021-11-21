import gui_main.GUI;

public class WinnerGUIController {
    WinnerController winnerController;

    public WinnerGUIController(WinnerController winnerController) {
        this.winnerController = winnerController;
    }

    public void printPlayerInfoTable() {
        StringBuilder sb = new StringBuilder("Player balances:\n");
        for (Player player : winnerController.getPlayers()) {
            sb.append("Player \"").append(player.getName()).append("\"").append(" has a balance of ").append(player.getAccount().getBalance()).append("$");
            sb.append(" and owns fields of ").append(winnerController.getTotalPropertyValue(player)).append("$\n");
        }
        sb.append("Player " + "\"").append(winnerController.getWinner().getName()).append("\"").append(" won!");

        GUIInstance.getInstance().showMessage(sb.toString());
        GUIInstance.getInstance().getUserButtonPressed("", "Close game");
        GUIInstance.getInstance().close();
    }
}
