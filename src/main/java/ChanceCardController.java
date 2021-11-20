import gui_main.GUI;

public class ChanceCardController {
    protected ChanceCardModel model;
    protected ChanceCardView view;

    public ChanceCardController(ChanceCardModel model, GUI gui) {
        this.model = model;
        this.view = new ChanceCardView(this.model.getText());
    }

    public void onDraw() {
        this.view.display();
    }
}
