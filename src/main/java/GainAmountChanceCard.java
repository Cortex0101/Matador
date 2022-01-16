public class GainAmountChanceCard extends ChanceCard{
    int amount;

    public GainAmountChanceCard(String text, int amount){
        super(text);
        this.amount = amount;
    }

    public void action(Player player){
        Bank.payPlayer(player,amount);
    }
}
