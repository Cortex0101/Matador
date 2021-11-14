import gui_fields.GUI_Field;
import gui_main.GUI;

public class FreeFieldChanceCardController extends ChanceCardController {
    private FieldSelector fieldSelector;

    public FreeFieldChanceCardController(GUI gui, GUI_Field[] options) {
        super(new ChanceCardModel("asdfasdf"), gui);
        fieldSelector = new FieldSelector(options, gui);
        String str = "Move to one of ";
        for (String title : fieldSelector.getFieldTitles()) {
            str += title + ", ";
        }
        str += "\nIf the field is unowned, you will get it for free. Otherwise you will have to pay the owner.";
        this.model.setText(str);
        this.view.setText(str);
    }

    public void action() {
        this.onDraw();
        this.fieldSelector.getUserSelection();
    }
}
