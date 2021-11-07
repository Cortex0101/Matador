import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

public class CarController {
    private boolean inJail;
    private boolean passedStart;

    private GUI_Field[] gui_fields;
    private GUI_Player gui_player;

    private CarModel carModel;
    private GUI_Car carView;

    public CarController(GUI_Field[] fields, GUI_Player player) {
        this.inJail = false;
        this.passedStart = false;

        this.gui_fields = fields;
        this.gui_player = player;

        this.carModel = new CarModel();
        this.carView = new GUI_Car();
    }

    public boolean isInJail() {
        return inJail;
    }

    public boolean hasPassedStart() {
        return passedStart;
    }

    public void setCarPosition(int position) {
        // TODO: Implement, make sure that passedStart and inJail is set appropiately
    }

    public void moveCar(int count) {
        this.gui_fields[this.carModel.getPosition()].setCar(this.gui_player, false);
        passedStart = this.carModel.move(count);
        this.gui_fields[this.carModel.getPosition()].setCar(this.gui_player, true);
    }
}
