import gui_main.GUI;

public class OutOfJailChanceCardController extends ChanceCardController {
    public OutOfJailChanceCardController() {
        super(new ChanceCardModel("Get out of jail"));
        this.view.setText(this.model.getText());
    }

    public void action(Player player) {
        this.onDraw();
        player.setHasGetOutOfJailCard(true);
    }
}
