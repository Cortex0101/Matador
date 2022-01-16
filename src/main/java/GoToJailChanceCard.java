public class GoToJailChanceCard extends ChanceCard{
    public GoToJailChanceCard(String text){
        super(text);
    }

    public void action (Player player){
        player.getCar().setInJail(true);
        player.getCar().setCarPosition(10);
    }
}
