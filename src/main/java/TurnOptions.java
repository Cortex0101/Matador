public class TurnOptions {
    private boolean hasRolledDice = false;

    public String getPlayerChoice(String playerName) {
        String choice = GUIInstance.getInstance().getUserSelection(playerName + ", it is your turn. Choose what to do.",
                hasRolledDice ? "Done" : "Roll",
                "Buy houses",
                "Sell houses",
                "Mortgage property",
                "Unmortgage Property");

        hasRolledDice = !choice.equals("Done");

        return choice;
    }
}