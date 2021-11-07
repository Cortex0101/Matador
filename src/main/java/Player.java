import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

public class Player {
    private final String name;
    private final Die die;
    private final Account account;
    private final CarController car;

    public Player(String name, int initialCapital, GUI_Field[] fields) {
        this.name = name;
        this.die = new Die();
        this.account = new Account(initialCapital);
        this.car = new CarController(fields, new GUI_Player(this.name, this.account.getBalance()));
    }

    public Die getDie() {
        return die;
    }

    public CarController getCar() {
        return car;
    }

    public String getName() {
        return name;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }
}
