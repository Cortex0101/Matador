import gui_main.GUI;

import java.security.Guard;

public class GUIInstance {
    private FieldModel fieldModel;
    private GUI gui;

    public GUIInstance(){
        this.fieldModel = new FieldModel();
        gui = new GUI(fieldModel.FieldInfo());
    }

    public GUI getGUI(){
        return gui;
    }
}
