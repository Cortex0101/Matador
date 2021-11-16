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
        StringBuilder str = new StringBuilder("Move to one of ");
        for (String title : fieldSelector.getFieldTitles()) {
            str.append(title).append(", ");
        }
        str.append("\nIf the field is unowned, you will get it for free. Otherwise you will have to pay the owner.");
        this.model.setText(str.toString());
        this.view.setText(str.toString());
    }

    public void action(Player[] players, Player player) {
        this.onDraw();
        this.fieldSelector.getUserSelection();

        GUI_Ownable field = (GUI_Ownable) this.fieldSelector.getSelected();
        if (field.getOwnerName() == null) {
            player.getCar().setCarPosition(Arrays.asList(this.fields).indexOf(this.fieldSelector.getSelected()));
            if (player.getCar().hasPassedStart()) {
                Bank.payPlayer(player, 2);
            }
            field.setOwnerName(player.getName());
            field.setBorder(player.getCar().getCarColor());
        } else {
            player.getCar().setCarPosition(Arrays.asList(this.fields).indexOf(this.fieldSelector.getSelected()));
            if (player.getCar().hasPassedStart()) {
                Bank.payPlayer(player, 2);
            }
            Player to = null;
            for (Player owner : players) {
                if (owner.getName().equals(field.getOwnerName())) {
                    to = owner;
                    break;
                }
            }
            Bank.transferMoney(player, to, Character.getNumericValue(field.getRent().charAt(0)));
        }
    }
}
