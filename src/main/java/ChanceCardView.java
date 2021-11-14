import gui_main.GUI;

public class ChanceCardView {
    private String text;

    public ChanceCardView(String text) {
        this.text = text;
    }

    public void display(GUI gui) {
        gui.displayChanceCard(this.text);
    }

    public void setText(String text) {this.text = text;}
}
