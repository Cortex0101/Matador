import gui_main.GUI;

public class PlayerCreator {
    private int playercount;
    private String[] playernames;
    private Player[] players;
    public PlayerCreator(GUI gui){
        playercount = gui.getUserInteger("How many players are present",2,4);

        playernames = new String[playercount];
        for (int i = 0; i < playercount; i++) {
            playernames[i] = gui.getUserString("What shall player " + (i + 1) + " be called?");
        }

        players = new Player[playercount];
        for (int i = 0; i < playercount; i++) {
            players[i] = new Player(playernames[i], 20, gui.getFields());
        }
    }
    public Player[] getPlayers(){
        return players;
    }

    public int getPlayerCount() {
        return playercount;
    }

    public String[] getPlayerNames() {
        return playernames;
    }
}
