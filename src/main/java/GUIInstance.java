import gui_main.GUI;

import java.security.Guard;

public final class GUIInstance {
    private static GUIInstance instance;

    private static GUI gui;

    private GUIInstance(){
        gui = new GUI(new FieldModel().FieldInfo());
    }

    public static GUI getInstance() {
        if (instance == null) {
            instance = new GUIInstance();
        }
        return instance.gui;
    }
}
