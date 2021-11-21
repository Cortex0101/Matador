import gui_fields.GUI_Field;
import gui_fields.GUI_Street;

import java.util.ArrayList;
import java.util.Arrays;

public class WinnerController {
    Player[] players;

    public WinnerController(Player[] players) {
        this.players = players;
    }

    /***
     * Note that if multiple palyers have equal balance AND equal property value, the last player in the array
     * is picked as winner
     * @return Winning player
     */
    public Player getWinner() {
        Player highestAccountValuePlayer = getPlayerWithMostValuableAccount(this.players);
        if (multiplePlayersWithBalanceOf(highestAccountValuePlayer.getAccount().getBalance())) {
            Player[] mostValuablePlayers = getPlayersWithBalanceOf(highestAccountValuePlayer.getAccount().getBalance());
            for (Player valuablePlayer : mostValuablePlayers) {
                int propertyValue = getTotalPropertyValue(valuablePlayer);
                Bank.payPlayer(valuablePlayer, propertyValue);
            }
            highestAccountValuePlayer = getPlayerWithMostValuableAccount(mostValuablePlayers);
        }
        return highestAccountValuePlayer;
    }

    private Player getPlayerWithMostValuableAccount(Player[] playerss) {
        Player highestValuePlayer = playerss[0];
        for (Player player : playerss) {
            if (player.getAccount().getBalance() > highestValuePlayer.getAccount().getBalance()) {
                highestValuePlayer = player;
            }
        }
        return highestValuePlayer;
    }

    public Player[] getPlayers() {
        return players;
    }

    private boolean multiplePlayersWithBalanceOf(int balance) {
        for (Player player : players) {
            if (player.getAccount().getBalance() == balance) {
                return true;
            }
        }
        return false;
    }

    private Player[] getPlayersWithBalanceOf(int balance) {
        ArrayList<Player> playersWithEqualBalance = new ArrayList<>();
        for (Player player : players) {
            if (player.getAccount().getBalance() == balance) {
                playersWithEqualBalance.add(player);
            }
        }
        return playersWithEqualBalance.toArray(new Player[0]);
    }

    public int getTotalPropertyValue(Player player) {
        int totalValue = 0;
        for (GUI_Field field : GUIInstance.getInstance().getFields()) {
            try {
                GUI_Street street = (GUI_Street) field;
                if (street.getOwnerName() == null) { continue; }
                if (street.getOwnerName().equals(player.getName())) {
                    totalValue += Character.getNumericValue(street.getRent().charAt(0));
                }
            }
            catch (ClassCastException ignored) {}
        }
        return totalValue;
    }
}
