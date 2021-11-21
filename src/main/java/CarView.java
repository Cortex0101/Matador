import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

import java.awt.*;

public class CarView {
    private final GUI_Player player;
    private final GUI_Car car;
    public static int carCount = 0;

    public CarView(GUI_Player player) {
        this.player = player;
        this.car = new GUI_Car(getColor(carCount), getColor(carCount), GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
        this.player.getCar().setPrimaryColor(getColor(carCount));
        carCount++;
    }

    public void moveCar(int from, int to) {
        GUIInstance.getInstance().getFields()[from].setCar(this.player, false);
        GUIInstance.getInstance().getFields()[to].setCar(this.player, true);
    }

    public Color getCarColor() {
        return this.car.getPrimaryColor();
    }

    private static Color getColor(int i) {
        Color[] colors = new Color[]{Color.blue, Color.red, Color.yellow, Color.green};
        return colors[i];
    }
}
