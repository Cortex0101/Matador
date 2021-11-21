import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

import java.awt.*;

public class CarController {
    private final GUI_Player gui_player;
    private final CarModel carModel;
    private final GUI_Car carView;
    public static int carCount = 0;

    public CarController(GUI_Player player) {
        this.gui_player = player;
        this.carModel = new CarModel();
        this.carView = new GUI_Car(getColor(carCount), getColor(carCount), GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
        player.getCar().setPrimaryColor(getColor(carCount));
        carCount++;
    }

    public GUI_Player getGui_player() {
        return gui_player;
    }

    public boolean isInJail() {
        return this.carModel.isInJail();
    }

    public void setInJail(boolean inJail) {
        this.carModel.setInJail(inJail);
    }

    public Color getCarColor(){return carView.getPrimaryColor();}

    public boolean hasPassedStart() {
        return this.carModel.hasPassedStart();
    }

    public void setCarPosition(int position) {
        updateCarGUI(this.carModel.getPosition(), false);
        this.carModel.setPassedStart(this.getCarPosition() > position);
        this.carModel.setPosition(position);
        updateCarGUI(this.carModel.getPosition(), true);
    }

    public void moveCar(int count) {
        updateCarGUI(this.carModel.getPosition(), false);
        this.carModel.setPassedStart(this.carModel.getPosition() + count > CarModel.MAX_POSITION);
        this.carModel.move(count);
        updateCarGUI(this.carModel.getPosition(), true);
    }

    /**
     *
     * @param fieldIndex The index of the field to update
     * @param place Wether to remove or place a car on the fieldIndex. False if you want to remove, true if you want to place
     */
    private void updateCarGUI(int fieldIndex, boolean place) {
        GUIInstance.getInstance().getFields()[fieldIndex].setCar(this.gui_player, place);
    }

    public int getCarPosition() {
        return carModel.getPosition();
    }

    private static Color getColor(int i) {
        Color[] colors = new Color[]{Color.blue, Color.red, Color.yellow, Color.green};
        return colors[i];
    }
}
