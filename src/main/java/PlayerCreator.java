import gui_main.GUI;

public class PlayerCreator {
    private int playercount;
    private Player[] players;

    public PlayerCreator(){
        playercount = GUIInstance.getInstance().getUserInteger("How many players are present",2,4);
        players = new Player[playercount];
        for (int i = 0; i < playercount; i++) {
            //added more initial capital for development testing purposes
            players[i] = new Player(GUIInstance.getInstance().getUserString("What shall player " + (i + 1) + " be called?"), 200000);
            GUIInstance.getInstance().addPlayer(players[i].gui_player);
        }
    }

    public Player[] getPlayers(){
        return players;
    }
}
