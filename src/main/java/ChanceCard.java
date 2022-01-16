public class ChanceCard {
    String text;

    public ChanceCard(String text){
        this.text = text;
    }

    public void display() {
        GUIInstance.getInstance().displayChanceCard(this.text);
    }

    public void action(Player player){

    }
}
