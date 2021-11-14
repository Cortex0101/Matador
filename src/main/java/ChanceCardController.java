import gui_main.GUI;

public class ChanceCardController {
    private ChanceCardModel model;
    private ChanceCardView view;
    private GUI gui;

    public ChanceCardController(ChanceCardModel model, GUI gui) {
        this.model = model;
        this.view = new ChanceCardView(this.model.getText());
        this.gui = gui;
    }

    public void onDraw() {
        this.view.display(gui);
    }
}
