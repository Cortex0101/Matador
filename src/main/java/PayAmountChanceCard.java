public class PayAmountChanceCard extends ChanceCard{
    int amount;

    public PayAmountChanceCard(String text, int amount){
        super(text);
        this.amount = amount;
    }

    public void action (Player player){
        if(player.getAccount().getBalance()>amount) {
            Bank.payBank(player, amount);
        }
    }
}
