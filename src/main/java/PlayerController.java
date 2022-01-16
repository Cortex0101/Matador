public class PlayerController {
    private final Player[] players;
    private int activePlayerIndex;

    public PlayerController(Player[] players) {
        this.players = players;
        this.activePlayerIndex = 0;
    }

    public Player getActivePlayer() {
        return this.players[this.activePlayerIndex];
    }

    public void nextPlayerTurn() {
        if (this.activePlayerIndex == this.players.length - 1) {
            activePlayerIndex = 0;
        } else {
            activePlayerIndex++;
        }
    }
}
