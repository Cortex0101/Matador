import gui_main.GUI;

public class FreeFieldChanceCardCreator {
    private FreeFieldChanceCardController[] freeFieldChanceCardControllers;

    public FreeFieldChanceCardCreator() {
        freeFieldChanceCardControllers = new FreeFieldChanceCardController[]{
                new FreeFieldChanceCardController(GUIInstance.getInstance().getFields(), new int[]{4, 5}),
                new FreeFieldChanceCardController(GUIInstance.getInstance().getFields(), new int[]{10, 11}),
                new FreeFieldChanceCardController(GUIInstance.getInstance().getFields(), new int[]{13, 14}),
                new FreeFieldChanceCardController(GUIInstance.getInstance().getFields(), new int[]{10, 11, 19, 20}),
                new FreeFieldChanceCardController(GUIInstance.getInstance().getFields(), new int[]{7, 8, 22, 23}),
                new FreeFieldChanceCardController(GUIInstance.getInstance().getFields(), new int[]{4, 5, 13, 14}),
                new FreeFieldChanceCardController(GUIInstance.getInstance().getFields(), new int[]{1, 2, 16, 17})
        };
    }

    public FreeFieldChanceCardController[] getFreeFieldChanceCardControllers() {
        return freeFieldChanceCardControllers;
    }
}
