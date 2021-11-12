public class PlayerController {
    private Player[] players;
    private int activePlayerIndex;
    private Player activePlayer;

    public PlayerController(Player[] players) {
        this.players = players;
        this.activePlayerIndex = 0;
        this.activePlayer = players[activePlayerIndex];
    }

    public Player getActivePlayer() {
        return this.activePlayer;
    }

    public void nextPlayerTurn() {
        if (this.activePlayerIndex == this.players.length - 1) {
            activePlayerIndex = 0;
        }
        else {
            activePlayerIndex++;
        }
        activePlayer = players[activePlayerIndex];
    }
}
