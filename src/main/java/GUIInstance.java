import gui_main.GUI;

import java.awt.*;
import java.security.Guard;

public final class GUIInstance {
    private static GUIInstance instance;

    private static GUI gui;

    private GUIInstance(){
        gui = new GUI(new FieldModel().FieldInfo());
    }

    public static void setFields(FieldModel fieldModel) {
        gui = new GUI(fieldModel.FieldInfo(), Color.decode("#169c02"));
    }

    public static GUI getInstance() {
        return instance.gui;
    }
}
