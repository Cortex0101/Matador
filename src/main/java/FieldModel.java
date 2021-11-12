import gui_fields.*;

import java.awt.*;

public class FieldModel {
    private GUI_Field[] fields;

    public FieldModel(){
        fields = new GUI_Field[24];

        int i = 0;
        fields[i++] = new GUI_Start("GO", "2$", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("BURGER JOINT",FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("PIZZA HOUSE",FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Chance", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("CANDY STORE",FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("ICE CREAM PARLOR",FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Jail("VISITING", "Modtag kr. 200,-\nnår de passerer start","JAIL","TEST", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("MUSEUM",FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("lIBRARY",FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Chance", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("SKATE PARK",FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("SWIMMING POOL",FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Tax("PARKING", "", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("GAME ARCADE",FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("MOVIE THEATRE",FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Chance", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("TOY STORE",FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("PET STORE",FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Jail("C:\\Users\\ldeir\\OneDrive\\Desktop\\MSP73320hi35g4i8cc53bd00002230f3i7bb8efh8f.gif", "JAIL", "", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("BOWLING ALLEY",FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("THE ZOO",FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i++] = new GUI_Chance("?", "Chance", "Modtag kr. 200,-\nnår de passerer start", Color.RED, Color.BLACK);
        fields[i++] = new GUI_Street("PARK PLACE",FieldModel.getFieldValue(i-1)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
        fields[i] = new GUI_Street("BOARDWALK",FieldModel.getFieldValue(i)+"$", "Modtag kr. 200,-\nnår de passerer start", FieldModel.getFieldValue(i - 1) + "$",Color.RED, Color.BLACK);
    }
    public GUI_Field[] FieldInfo() {
        return fields;
    }
    public static int getFieldValue(int field){

        return switch (field) {
            case 1, 2, 4, 5 -> 1;
            case 7,8,10,11 -> 2;
            case 13, 14, 16, 17 -> 3;
            case 19, 20 -> 4;
            case 22, 23 -> 5;
            case 0, 3, 6, 9, 12, 15, 18, 21 -> 0;
            default -> 0;
        };
    }
}
