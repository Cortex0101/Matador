import gui_fields.*;

import java.awt.*;

public class FieldModel {
    private final GUI_Field[] fields;

    public FieldModel(){
        this.fields = new GUI_Field[40];
        String[][] fieldText = new String[fields.length][5];
        FileReader fileReader = new FileReader();
        int i = 0;
        for (int j = 0; j < 40; j++) {
            fieldText[j] = fileReader.ReadFileText();
        }

        fields[i++] = new GUI_Start(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2],Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.LIGHT_GRAY);
        fields[i++] = new GUI_Chance(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.LIGHT_GRAY);
        fields[i++] = new GUI_Tax(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Shipping("default",fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.LIGHT_GRAY);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Chance(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Jail("default", fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], Color.decode(fieldText[i-1][4]), Color.GRAY);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Brewery("default", fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Shipping("default", fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.LIGHT_GRAY);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Chance(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Refuge("default", fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Chance(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Shipping("default", fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.LIGHT_GRAY);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Brewery("default", fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Jail("default", fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], Color.decode(fieldText[i-1][4]), Color.LIGHT_GRAY);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Chance(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Shipping("default", fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.LIGHT_GRAY);
        fields[i++] = new GUI_Chance(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i++] = new GUI_Street(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], fieldText[i-1][3], Color.decode(fieldText[i-1][4]), Color.LIGHT_GRAY);
        fields[i++] = new GUI_Tax(fieldText[i-1][0], fieldText[i-1][1], fieldText[i-1][2], Color.decode(fieldText[i-1][4]), Color.BLACK);
        fields[i] = new GUI_Street(fieldText[i][0], fieldText[i][1], fieldText[i][2], fieldText[i][3], Color.decode(fieldText[i][4]), Color.LIGHT_GRAY);
    }
    public GUI_Field[] FieldInfo() {
        return fields;
    }
    public static int getFieldPrice(int position){
        return switch (position) { // First field "Rødovrevej" is at field 1
            case 1, 3 -> 1200;
            case 6, 8 -> 2000; case 9 -> 2400;
            case 11, 13 -> 2800; case 14 -> 3200;
            case 16, 18 -> 3600; case 19 -> 4000;
            case 21, 23 -> 4400; case 24 -> 4800;
            case 26, 27 -> 5200; case 29 -> 5600;
            case 31, 32 -> 6000; case 34 -> 6400;
            case 37 -> 7000; case 39 -> 8000;
            case 12, 28 -> 3000;
            case 5, 15, 25, 35 -> 4000;
            default -> 0;
        };
    }
}

