import gui_fields.GUI_Street;

public class FieldController {
    private FieldModel fieldModel;


    public FieldController(){
        this.fieldModel = new FieldModel();
    }

    public void landOnField(){

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
