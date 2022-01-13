public class ChanceCardCreator {
    private FreeFieldChanceCardController[] freeFieldChanceCardControllers;

    public ChanceCardCreator() {
        freeFieldChanceCardControllers = new FreeFieldChanceCardController[]{
                new FreeFieldChanceCardController(new int[]{4, 5}),
                new FreeFieldChanceCardController(new int[]{10, 11}),
                new FreeFieldChanceCardController(new int[]{13, 14}),
                new FreeFieldChanceCardController(new int[]{10, 11, 19, 20}),
                new FreeFieldChanceCardController(new int[]{7, 8, 22, 23}),
                new FreeFieldChanceCardController(new int[]{4, 5, 13, 14}),
                new FreeFieldChanceCardController(new int[]{1, 2, 16, 17})
        };
    }

    public FreeFieldChanceCardController[] getFreeFieldChanceCardControllers() {
        return freeFieldChanceCardControllers;
    }
}

//TODO retructure to work with new cards