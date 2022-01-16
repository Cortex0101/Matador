public class GetMoneyIfLowOnMoneyChanceCard extends ChanceCard{
    private int amountOfMoneyOwnedMax;
    private int amountGained;

    public GetMoneyIfLowOnMoneyChanceCard(String text, int amountOfMoneyOwnedMax, int amountGained){
        super(text);
        this.amountOfMoneyOwnedMax = amountOfMoneyOwnedMax;
        this.amountGained = amountGained;
    }

    public void action (Player player){
        if(player.getAccount().getBalance()<amountOfMoneyOwnedMax){Bank.payPlayer(player, amountGained);}
    }
}
