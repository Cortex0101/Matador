public class ChanceCardView {
    private String text;

    public ChanceCardView(String text) {
        this.text = text;
    }

    public void display() {
        GUIInstance.getInstance().displayChanceCard(this.text);
    }

    public void setText(String text) {this.text = text;}
}
