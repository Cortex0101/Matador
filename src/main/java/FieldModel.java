public class FieldModel {
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
}
