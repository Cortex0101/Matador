import gui_fields.GUI_Field;
import gui_fields.GUI_Street;

public class FieldController {
    private FieldModel fieldModel;


    public FieldController(){
        this.fieldModel = new FieldModel();
    }

    public void landOnField(int position,GUI_Field field){
        switch (position) {
            case 1, 2, 4, 5, 7, 8, 10, 11, 13, 14, 16, 17, 19, 20, 22, 23 -> landOnOwnable(position,field);
            case 3, 9, 15, 21 -> landOnChance();
            case 0, 6, 12 -> landOnFreeSpot();
            case 18 -> landOnGoToJail();
            default -> landOnFreeSpot();
        }

    }

    public void landOnOwnable(int position, GUI_Field field){
        if (FieldModel.getFieldValue(position)>0){
            GUI_Street street = (GUI_Street) field;
            if (street.getOwnerName() != null) {
                landOnOwnedOwnable();
                System.out.println("Owned Space");
            }
            else
            {
                landOnUnownedOwnable();
                System.out.println("Unowned Space");
            }
        }
    }

    private void landOnUnownedOwnable(){}
    private void landOnOwnedOwnable(){}
    public void landOnChance(){
        System.out.println("Chance");
    }
    public void landOnFreeSpot(){
        System.out.println("FreeSpot");
    }
    public void landOnGoToJail(){
        System.out.println("Jail");
    }
}
