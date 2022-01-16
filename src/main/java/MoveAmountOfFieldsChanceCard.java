public class MoveAmountOfFieldsChanceCard extends ChanceCard{
    int amount;

    public MoveAmountOfFieldsChanceCard(String text, int amount){
        super(text);
        this.amount = amount;
    }

    public void action (Player player){
        player.getCar().moveCar(amount);
    }
}
