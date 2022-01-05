import gui_main.GUI;

public class PlayerCreator {
    private final Player[] players;

    public PlayerCreator(){
        final int PLAYER_COUNT = getPlayerCountFromUser();

        players = new Player[PLAYER_COUNT];
        for (int i = 0; i < PLAYER_COUNT; i++) {
            players[i] = new Player(GUIInstance.getInstance().getUserString("What shall player " + (i + 1) + " be called?"), 30000);
            GUIInstance.getInstance().addPlayer(players[i].gui_player);
        }
    }

    private int getPlayerCountFromUser() {
        final int MIN_PLAYERS = 3;
        final int MAX_PLAYERS = 6;
        return GUIInstance.getInstance().getUserInteger("How many players are present", MIN_PLAYERS, MAX_PLAYERS);
    }

    public Player[] getPlayers(){
        return players;
    }
}
