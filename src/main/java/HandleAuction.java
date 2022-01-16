import java.util.Arrays;

public class HandleAuction {
    private int currentHighestbid;
    private Player currentHighestBidder;

    public void Auction(Player[] players) {
        int disabledPlayers = 0;
        for (int i = 0; i < disabledPlayers; i++) {
            if (!players[i].getIsActive()){disabledPlayers++;}
        }
        boolean[] playerStillBidding = new boolean[players.length - disabledPlayers];
        Arrays.fill(playerStillBidding, true);
        int playerAmountStillBidding;
        do {
            playerAmountStillBidding = 0;
            for (boolean b : playerStillBidding) {
                if (b) {
                    playerAmountStillBidding++;
                }
            }
            for (int i = 0; i < playerStillBidding.length; i++) {
                if (playerStillBidding[i] && playerAmountStillBidding >1){
                    if (GUIInstance.getInstance().getUserSelection(players[i].getName() + ", choose to bid or pass.                             Current price: " + currentHighestbid, "bid", "pass").equals("bid")) {
                        int bid = GUIInstance.getInstance().getUserInteger(players[i].getName() + ", Choose how much to raise the bid by \n Current price: "+ currentHighestbid);
                        while (bid > players[i].getAccount().getBalance()) {
                            bid = GUIInstance.getInstance().getUserInteger("you do not have enough money to bid that much. if you wish to pass, type 0 in the box");
                        }
                        if (bid > 0) {
                            currentHighestbid += bid;
                            currentHighestBidder = players[i];
                        }
                    }
                    else {
                        playerStillBidding[i]=false;
                        playerAmountStillBidding--;
                    }
                }
            }
        }
        while (playerAmountStillBidding >1);
    }

    public int getHighestbid() {
        return currentHighestbid;
    }

    public Player getHighestBidder(){
        return currentHighestBidder;
    }
}
