import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;

public class FieldController {
    private Player player;
    private GUI_Ownable gui_ownable;


    public FieldController(Player player, GUI_Ownable field){
        this.player = player;
        this.gui_ownable = field;
    }
    public void checkOwnership(){
                if (gui_ownable.getOwnableLabel()!=null){
                    landOnOwnedOwnable();
                }
                else{
                    landOnUnownedOwnable();
                }
    }
    public void landOnUnownedOwnable(){}
    public void landOnOwnedOwnable(){}
    public void landOnChance(){}
    public void landOnFreeSpot(){}
    public void landOnGoToJail(){}
}
