public class CarModel {
    private int position;
    private boolean inJail;
    private boolean passedStart;
    public static final int MAX_POSITION = 39; // Number of fields (0 is GO, and 23 is the last one, so its wraps on 24)

    public CarModel() {
        position = 0;
        this.inJail = false;
        this.passedStart = false;
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
        else {
            this.position = (this.position + count) % MAX_POSITION - 1;
        }
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public boolean hasPassedStart() {
        return passedStart;
    }

    public void setPassedStart(boolean passedStart) {
        this.passedStart = passedStart;
    }
}
