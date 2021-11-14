import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

import java.awt.*;

public class CarController {
    private boolean inJail;
    private boolean passedStart;

    private GUI_Field[] gui_fields;
    private GUI_Player gui_player;

    private CarModel carModel;
    private GUI_Car carView;

    static int carCount = 0;

    public CarController(GUI_Field[] fields, GUI_Player player) {
        this.inJail = false;
        this.passedStart = false;

        this.gui_fields = fields;
        this.gui_player = player;

        this.carModel = new CarModel();

        this.carView = new GUI_Car(getColor(carCount), getColor(carCount), GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
        player.getCar().setPrimaryColor(getColor(carCount));
        carCount++;
    }

    private static Color getColor(int i) {
        Color[] colors = new Color[]{Color.blue, Color.red, Color.yellow, Color.green};
        return colors[i];
    }

    public GUI_Player getGui_player() {
        return gui_player;
    }

    public boolean isInJail() {
        return inJail;
    }

    public boolean hasPassedStart() {
        return passedStart;
    }

    public void setCarPosition(int position) { // Need to check if player passed start, does not do it currently
        this.gui_fields[this.carModel.getPosition()].setCar(this.gui_player, false);
        this.carModel.setPosition(position);
        this.gui_fields[this.carModel.getPosition()].setCar(this.gui_player, true);
    }

    public void moveCar(int count) {
        this.gui_fields[this.carModel.getPosition()].setCar(this.gui_player, false);
        passedStart = this.carModel.move(count);
        this.gui_fields[this.carModel.getPosition()].setCar(this.gui_player, true);
    }

    public int getCarPosition() {
        return carModel.getPosition();
    }
}