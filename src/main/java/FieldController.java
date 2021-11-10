import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_fields.GUI_Street;

public class FieldController {
    private Player player;
    private FieldModel fieldModel;


    public FieldController(Player player, FieldModel fieldModel){
        this.player = player;
        this.fieldModel = fieldModel;
    }
    public void landOnOwnable(int position){

                if (FieldModel.getFieldValue(position)>0){
                    GUI_Street street = (GUI_Street) fieldModel.getField(position);
                    if (street.getOwnerName() != null) {
                        landOnOwnedOwnable();
                    }
                    else
                    {
                        landOnUnownedOwnable();
                    }
                }
    }
    private void landOnUnownedOwnable(){}
    private void landOnOwnedOwnable(){}
    public void landOnChance(){}
    public void landOnFreeSpot(){}
    public void landOnGoToJail(){}
}
