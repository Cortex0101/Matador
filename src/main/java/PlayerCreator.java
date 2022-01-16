public class PlayerCreator {
    private final Player[] players;

    public PlayerCreator(){
        final int PLAYER_COUNT = getPlayerCountFromUser();
        final int INITIAL_CAPITAL = 30000;

        players = new Player[PLAYER_COUNT];
        for (int i = 0; i < PLAYER_COUNT; i++) {
            if (Debugger.getInstance().enabled()) {
                players[i] = new Player("Player" + i, INITIAL_CAPITAL);
            } else {
                players[i] = new Player(GUIInstance.getInstance().getUserString("What shall player " + (i + 1) + " be called?"), INITIAL_CAPITAL);
            }
            GUIInstance.getInstance().addPlayer(players[i].gui_player);
        }
    }

    private int getPlayerCountFromUser() {
        final int MIN_PLAYERS = 3;
        final int MAX_PLAYERS = 6;
        final int DEBUG_MODE = 69;

        int playerCount;
        do {
            playerCount = GUIInstance.getInstance().getUserInteger("How many players are present", MIN_PLAYERS, MAX_PLAYERS);
        } while (!(playerCount >= MIN_PLAYERS && playerCount <= MAX_PLAYERS) && playerCount != DEBUG_MODE);

        if (playerCount == 69) {
            Debugger.getInstance().enable();
            return 2; // debug mode will just have 2 players for now, as we can test everything we need to with just 2 players.
        }

        return playerCount;
    }

    public Player[] getPlayers(){
        return players;
    }
}
