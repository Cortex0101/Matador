import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

import java.awt.*;

public class CarController {
    private final CarModel carModel;
    private final CarView carView;

    public CarController(GUI_Player player) {
        this.carModel = new CarModel();
        this.carView = new CarView(player);
    }
    
    public boolean isInJail() {
        return this.carModel.isInJail();
    }

    public void setInJail(boolean inJail) {
        this.carModel.setInJail(inJail);
    }

    public Color getCarColor(){ return carView.getCarColor(); }

    public boolean hasPassedStart() {
        return this.carModel.hasPassedStart();
    }

    public void setCarPosition(int position) {
        this.carView.moveCar(this.carModel.getPosition(), position);
        this.carModel.setPassedStart(this.getCarPosition() > position);
        this.carModel.setPosition(position);
    }

    public void moveCar(int count) {
        final int from = this.carModel.getPosition();
        this.carModel.setPassedStart(this.carModel.getPosition() + count > CarModel.MAX_POSITION);
        this.carModel.move(count);
        this.carView.moveCar(from, this.carModel.getPosition());
    }

    public int getCarPosition() {
        return carModel.getPosition();
    }

    private static Color getColor(int i) {
        Color[] colors = new Color[]{Color.blue, Color.red, Color.yellow, Color.green};
        return colors[i];
    }
}
