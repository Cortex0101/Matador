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

    public boolean move(int count) { // Returns true if car wrapped around the board
        if (this.position + count <= MAX_POSITION) {
            this.position += count;
            return false;
        }
        else {
            this.position = (this.position + count) % MAX_POSITION - 1;
            return true;
        }
    }
}
