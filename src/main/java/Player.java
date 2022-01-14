import gui_fields.GUI_Field;
import gui_fields.GUI_Player;

public class Player {
    private final String name;
    private final RaffleCup raffleCup;
    private final Account account;
    private final CarController carController;
    protected GUI_Player gui_player;
    private boolean getOutOfJailCard;
    private int rollCount;
    private boolean isActive = true;

    public Player(String name, int initialCapital) {
        this.name = name;
        this.raffleCup = new RaffleCup();
        this.account = new Account(initialCapital);
        this.gui_player = new GUI_Player(this.name, this.account.getBalance());
        this.carController = new CarController(this.gui_player);
        this.getOutOfJailCard = false;
    }

    public RaffleCup getRaffleCup() {
        return raffleCup;
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

    public int getRollCount(){ return rollCount;}

    public void IncrementRollCount() {rollCount++;}

    public void resetRollCount() {rollCount = 0;}

    public boolean getIsActive(){return isActive;}

    public void setIsActive(boolean isAciveState){this.isActive = isAciveState;}

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

    public PropertyCard[] getOwnedPropertyCards(PropertyCardController propertyCardController){ //Method is kinda clunky but it was the best solution my sleep deprived brain could come up with while the cards aren't directly attached to the player
        int ownedPropertyCount = 0;
            for (int i = 0; i < propertyCardController.getPropertyCards().length; i++) {
                if (propertyCardController.getPropertyCards()[i].getOwner() != null && propertyCardController.getPropertyCards()[i].getOwner().getName().equals(name)) {
                    ownedPropertyCount++;
                }
            }
        PropertyCard[] propertyCards = new PropertyCard[ownedPropertyCount];
        int filledPropertyCards = 0;
            for (int i = 0; i < propertyCardController.getPropertyCards().length; i++) {
                if (propertyCardController.getPropertyCards()[i].getOwner() != null && propertyCardController.getPropertyCards()[i].getOwner().getName().equals(name)) {
                    propertyCards[filledPropertyCards] = propertyCardController.getPropertyCards()[i];
                    filledPropertyCards++;
                }
            }
        return propertyCards;
    }
}
