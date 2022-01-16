public class ChanceCardCreator {
    ChanceCard[] chanceCards;

    public ChanceCardCreator() {
        this.chanceCards = new ChanceCard[]{
                new PayPerHouseChanceCard("The oil prices has gone up, and you have to pay 500$ per house.", 500),
                new PayPerHouseChanceCard("The property tax has gone up: pay 800$ per house.",800),

                new PayAmountChanceCard("You have run past a red light. Pay 1000$.", 1000),
                new PayAmountChanceCard("You have to wash and oil your car. Pay 300$.", 300),
                new PayAmountChanceCard("You have to pay for beer delivery. Pay 200$.", 200),
                new PayAmountChanceCard("You have to repair your car. Pay 3000$.", 3000),
                new PayAmountChanceCard("You have to repair your car. Pay 3000$.", 3000),
                new PayAmountChanceCard("You have to pay for new tires for your car. Pay 1000$.", 1000),
                new PayAmountChanceCard("You got a parking ticket. Pay 200$.", 200),
                new PayAmountChanceCard("You have to pay car insurance. Pay 1000$.", 1000),
                new PayAmountChanceCard("You've been to a foreign country and bought too many cigarettes. Pay 200$.", 200),
                new PayAmountChanceCard("You have to pay a dentist bill. Pay 2000$.", 2000),

                new GainAmountChanceCard("You've won the lottery. Gain 500$.", 500),
                new GainAmountChanceCard("You've won the lottery. Gain 500$.", 500),
                new GainAmountChanceCard("You receive dividend, Gain 1000$.", 1000),
                new GainAmountChanceCard("You receive dividend, Gain 1000$.", 1000),
                new GainAmountChanceCard("You receive dividend, Gain 1000$.", 1000),
                new GainAmountChanceCard("The municipality has remitted a quarterly tax. Gain 3000$", 3000),
                new GainAmountChanceCard("You won a bet. Gain 1000$.", 1000),
                new GainAmountChanceCard("due to rush hour, you've received a wage increase. Gain 1000$.", 1000),
                new GainAmountChanceCard("Your premium bond has been printed. Gain 1000$.", 1000),
                new GainAmountChanceCard("Your premium bond has been printed. Gain 1000$.", 1000),
                new GainAmountChanceCard("You've sold some old furniture on an auction. Gain 1000$.", 1000),
                new GainAmountChanceCard("Your stocks are paying off. Gain 200$.", 200),

                new GetMoneyIfLowOnMoneyChanceCard("You receive the “Matador-legatet”! Receive 40000$ if you have 5000$ or less", 5000, 40000),

                new EveryPlayerPaysChanceCard("It's your birthday. Receive 200$ from every player.", 200),
                new EveryPlayerPaysChanceCard("You've paid in advance for a joint party and everyone is paying you back. Receive 500$ from every player", 500),
                new EveryPlayerPaysChanceCard("it's your birthday. Receive 500$ from every player", 500),

                new MoveToFieldChanceCard("Move to Start.", 0),
                new MoveToFieldChanceCard("Move to Start.", 0),
                new MoveToFieldChanceCard("Move to Frederiksberg Allé. If you pass start, gain 4000$.", 11),
                new MoveToFieldChanceCard("Move to Helsingør - Helsingborg. If you pass start, gain 4000$.", 5),
                new MoveToFieldChanceCard("Move to Squash. If you pass start, gain 4000$.", 12), //probably just change this card to a regular move to field card that moves to a specific field
                new MoveToFieldChanceCard("Move to Mols-linien. If you pass start, gain 4000$.", 15),
                new MoveToFieldChanceCard("Move to Grønningen. If you pass start, gain 4000$.", 24),
                new MoveToFieldChanceCard("Move to Vimmelskaftet. If you pass start, gain 4000$.", 32),
                new MoveToFieldChanceCard("Move to Coca Cola. If you pass start, gain 4000$.", 28), //probably just change this card to a regular move to field card that moves to a specific field
                new MoveToFieldChanceCard("Move to Strandvejen. If you pass start, gain 4000$.", 19),
                new MoveToFieldChanceCard("Move to Rådhuspladsen.", 39),

                new MoveAmountOfFieldsChanceCard("Move three fields forward", 3),
                new MoveAmountOfFieldsChanceCard("Move three fields forward", 3),
                new MoveAmountOfFieldsChanceCard("Move three fields forward", 3),

                new OutOfJailChanceCard("Use this card to get out of jail for free!"),
                new OutOfJailChanceCard("Use this card to get out of jail for free!"),

                new GoToJailChanceCard("Go to jail, If you pass start, you receive 4000$."),
                new GoToJailChanceCard("Go to jail, If you pass start, you receive 4000$."),
        };
    }

    public ChanceCard[] getChanceCards(){
        return chanceCards;
    }
}

//TODO translate chancecard text