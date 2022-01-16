public class MoveToFieldChanceCard extends ChanceCard{
    int position;

    public MoveToFieldChanceCard(String text, int position){
        super(text);
        this.position = position;
    }

    public void action (Player player){
        player.getCar().setCarPosition(position);

    }
}
