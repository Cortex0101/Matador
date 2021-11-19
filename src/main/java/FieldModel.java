import gui_fields.*;

import java.awt.*;

public class FieldModel {
    private final GUI_Field[] fields;

    public FieldModel(){
        this.fields = new GUI_Field[24];
        String[][] fieldText = new String[fields.length][5];
        FileReader fileReader = new FileReader();
        int i = 0;
        for (int j = 0; j < 24; j++) {
            fieldText[j] = fileReader.ReadFileText();
        }

        fields[i++] = new GUI_Start(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2],Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Chance(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Jail("default", fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Chance(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Refuge("default", fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Chance(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Jail("default", fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Chance(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i] = new GUI_Street(fieldText[i][0], fieldText[i][1], fieldText[i][2], fieldText[i][3], Color.decode(fieldText[i][4]), Color.BLACK);
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
