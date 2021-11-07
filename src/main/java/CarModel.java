public class CarModel {
    private int position;
    private final int MAX_POSITION = 23; // Number of fields (0 is GO, and 23 is the last one, so its wraps on 24)

    public CarModel() {
        position = 0;
    }

    public void setPosition(int position) {
        if (position <= MAX_POSITION) {
            this.position = position;
        }
    }

    public int getPosition() {
        return position;
    }

    public void move(int count) {
        if (this.position + count <= MAX_POSITION) {
            this.position += count;
        }
    }
}
