public class EveryPlayerPaysChanceCard extends ChanceCard{
    int amount;

    public EveryPlayerPaysChanceCard(String text, int amount){
        super(text);
        this.amount = amount;
    }

    public void action (Player player, Player[] players){
        for (Player allPlayers: players){
            if (allPlayers.getAccount().getBalance()>amount){
                Bank.transferMoney(allPlayers, player, amount);
            }
        }

    }
}
