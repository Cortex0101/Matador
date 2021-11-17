public class WinnerController {
    Player[] players;
    FieldModel fieldModel;

    public WinnerController(Player[] players, FieldModel fieldModel) {
        this.players = players;
        this.fieldModel = fieldModel;
    }

    public Player getPlayerWithMostValuableAccount() {
        Player highestValuePlayer = players[0];
        for (Player player : players) {
            if (player.getAccount().getBalance() > highestValuePlayer.getAccount().getBalance()) {
                highestValuePlayer = player;
            }
        }
        if (multiplePlayersWithBalanceOf(highestValuePlayer.getAccount().getBalance())) {

        }
        return highestValuePlayer;
    }

    private boolean multiplePlayersWithBalanceOf(int balance) {
        for (Player player : players) {
            if (player.getAccount().getBalance() == balance) {
                return true;
            }
        }
        return false;
    }

    private int getTotalPropertyValue(Player player) {
        return 0;
    }
}
