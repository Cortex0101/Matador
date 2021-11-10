import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class FieldModel {
    private GUI_Field[] fields;

    public FieldModel(){
        fields = new GUI_Field[24];
    }
    public static int getFieldValue(int field){

        return switch (field) {
            case 1, 2, 4, 5 -> 1;
            case 7,8,10,11 -> 2;
            case 13, 14, 16, 17 -> 3;
            case 19, 20 -> 4;
            case 22, 23 -> 5;
            default -> 0;
        };
    }

    public GUI_Field[] FieldInfo() {
        int i = 0;
        fields[i++] = new GUI_Start("GO", "2$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("BURGER JOINT",getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("PIZZA HOUSE",getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Chance", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("CANDY STORE",getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("ICE CREAM PARLOR",getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Jail("VISITING", "Modtag kr. 200,-\nnår de passerer start","JAIL","TEST", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("MUSEUM",getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("lIBRARY",getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Chance", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("SKATE PARK",getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("SWIMMING POOL",getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Tax("PARKING", "", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("GAME ARCADE",getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("MOVIE THEATRE",getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Chance", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("TOY STORE",getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("PET STORE",getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Jail("C:\\Users\\ldeir\\OneDrive\\Desktop\\MSP73320hi35g4i8cc53bd00002230f3i7bb8efh8f.gif", "JAIL", "", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("BOWLING ALLEY",getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("THE ZOO",getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Chance", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("PARK PLACE",getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i] = new GUI_Street("BOARDWALK",getFieldValue(i)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);

        return fields;
    }
    public GUI_Field getField (int fieldNo) {
        return (fields[fieldNo]);
    }
}
