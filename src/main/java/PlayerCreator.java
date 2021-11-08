import gui_main.GUI;

public class PlayerCreator {
    private int playercount;
    private String[] playernames;
    private Player[] players;
    public PlayerCreator(GUI gui){
        playercount = gui.getUserInteger("Input the amount of players",2,4);
        playernames = new String[playercount];
        players = new Player[playercount];
        for (int i = 0; i < playercount; i++) {
            playernames[i] = gui.getUserString("..");
        }
        for (int i = 0; i < playercount; i++) {
            players[i] = new Player(playernames[i], 20, gui.getFields());

        }
    }
    public Player[] getPlayers(){
        return players;
    }

    public int getPlayercount() {
        return playercount;
    }

    public String[] getPlayernames() {
        return playernames;
    }
}
