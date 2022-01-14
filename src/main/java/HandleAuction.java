import java.util.Arrays;

public class HandleAuction {
    private int currentHighestbid;
    private Player currentHighestBidder;
    private boolean[] playerStillBidding;
    private int playerAmountStillBidding;

    public Player Auction(Player[] players) {
        playerStillBidding = new boolean[players.length];
        Arrays.fill(playerStillBidding, true);
        do {
            playerAmountStillBidding = 0;
            for (int i = 0; i < playerStillBidding.length; i++) {
                if (playerStillBidding[i]) {
                    playerAmountStillBidding++;
                }
            }
            for (int i = 0; i < playerStillBidding.length; i++) {
                if (playerStillBidding[i] && playerAmountStillBidding>1){
                    if (GUIInstance.getInstance().getUserSelection(players[i].getName() + ", choose to bid or pass", "bid", "pass").equals("bid")) {
                        int bid = GUIInstance.getInstance().getUserInteger(players[i].getName() + ", Choose how much to bid");
                        while (bid > players[i].getAccount().getBalance()) {
                            bid = GUIInstance.getInstance().getUserInteger("you do not have enough money to bid that much. if you wish to pass, type 0 in the box");
                        }
                        if (bid > 0) {
                            currentHighestbid += bid;
                            currentHighestBidder = players[i];
                        }
                    }
                    else if (playerAmountStillBidding>1){
                        playerStillBidding[i]=false;
                        playerAmountStillBidding--;
                    }
                }
            }
        }
        while (playerAmountStillBidding>1);
        return currentHighestBidder;
    }

    public int getHighestbid() {
        return currentHighestbid;
    }

    public Player getHighestBidder(){
        return currentHighestBidder;
    }
}
