import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;
import gui_main.GUI;

import java.util.Arrays;
import java.util.Collections;

public class FreeFieldChanceCardController extends ChanceCardController {
    private FieldSelector fieldSelector;
    GUI_Field[] fields;
    private int[] positions;

    public FreeFieldChanceCardController(GUI gui, GUI_Field[] fields, int[] positions) {
        super(new ChanceCardModel("asdfasdf"), gui);
        this.fields = fields;
        this.positions = positions;
        GUI_Field[] options = new GUI_Field[positions.length];
        for (int i = 0; i < positions.length; i++) {
            options[i] = fields[positions[i]];
        }
        fieldSelector = new FieldSelector(options, gui);
        String str = "Move to one of ";
        for (String title : fieldSelector.getFieldTitles()) {
            str += title + ", ";
        }
        str += "\nIf the field is unowned, you will get it for free. Otherwise you will have to pay the owner.";
        this.model.setText(str);
        this.view.setText(str);
    }

    public void action(Player player) {
        this.onDraw();
        this.fieldSelector.getUserSelection();

        GUI_Ownable field = (GUI_Ownable) this.fieldSelector.getSelected();
        if (field.getOwnerName() == null) {
            player.getCar().setCarPosition(Arrays.asList(this.fields).indexOf(this.fieldSelector.getSelected()));
            field.setOwnerName(player.getName());
        } else {
            player.getCar().setCarPosition(Arrays.asList(this.fields).indexOf(this.fieldSelector.getSelected()));
            // PAY OWNER
        }
    }
}