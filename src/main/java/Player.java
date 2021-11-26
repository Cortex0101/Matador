import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

public class Player {
    private final String name;
    private final Die die;
    private final Account account;
    private final CarController carController;
    protected GUI_Player gui_player;
    private boolean getOutOfJailCard;

    public Player(String name, int initialCapital) {
        this.name = name;
        this.die = new Die();
        this.account = new Account(initialCapital);
        this.gui_player = new GUI_Player(this.name, this.account.getBalance());
        this.carController = new CarController(this.gui_player);
        this.getOutOfJailCard = false;
    }

    public Die getDie() {
        return die;
    }

    public CarController getCar() {
        return carController;
    }

    public String getName() {
        return name;
    }

    public Account getAccount() {
        return account;
    }

    /*
        Needed because the GUI does not update the balance automatically. It is called by the Bank class, so dont worry about it.
     */
    public void updateAccountGUI() {
        this.gui_player.setBalance(this.getAccount().getBalance());
    }

    public boolean hasGetOutOfJailCard() {
        return this.getOutOfJailCard;
    }

    public void setHasGetOutOfJailCard(boolean bool) {
        this.getOutOfJailCard = bool;
    }
}
