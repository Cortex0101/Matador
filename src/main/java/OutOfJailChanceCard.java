public class OutOfJailChanceCard extends ChanceCard {
    public OutOfJailChanceCard(String text){
        super(text);
    }

    public void action (Player player){
        player.hasGetOutOfJailCard();

    }

}
