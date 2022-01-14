public class ChanceCardCreator {
    ChanceCard[] chanceCards;

    public ChanceCardCreator() {
        this.chanceCards = new ChanceCard[]{
                new PayPerHouseChanceCard("Oliepriserne er steget, og De skal betale kr 500 pr hus og kr 2000 pr hotel.", 500, 2000),
                new PayPerHouseChanceCard("Ejendomsskatten er steget. Ekstraudgifterne er: 800 kr pr hus, 2300 kr pr hotel.",800, 2300),
                new PayAmountChanceCard("De har kørt frem for “fuldt stop”, Betal 1000 kroner i bøde", 1000),
                new PayAmountChanceCard("Betal for vognvask og smøring kr 300", 1000),
                new PayAmountChanceCard("Betal kr 200 for levering af 2 kasser øl", 1000),
                new PayAmountChanceCard("Betal 3000 for reparation af deres vogn", 1000),
                new PayAmountChanceCard("Betal 3000 for reparation af deres vogn", 1000),
                new PayAmountChanceCard("De har købt 4 nye dæk til Deres vogn, betal kr 1000", 1000),
                new PayAmountChanceCard("De har fået en parkeringsbøde, betal kr 200 i bøde", 1000),
                new PayAmountChanceCard("Betal deres bilforsikring, kr 1000", 1000),
                new PayAmountChanceCard("De har været udenlands og købt for mange smøger, betal kr 200 i told.", 1000),
                new PayAmountChanceCard("Tandlægeregning, betal kr 2000.", 1000),
                new GainAmountChanceCard("De har vundet i klasselotteriet. Modtag 500 kr.", 500),
                new GainAmountChanceCard("De har vundet i klasselotteriet. Modtag 500 kr.", 500),
                new GainAmountChanceCard("De modtager Deres aktieudbytte. Modtag kr 1000 af banken", 1000),
                new GainAmountChanceCard("De modtager Deres aktieudbytte. Modtag kr 1000 af banken", 1000),
                new GainAmountChanceCard("De modtager Deres aktieudbytte. Modtag kr 1000 af banken", 1000),
                new GainAmountChanceCard("Kommunen har eftergivet et kvartals skat. Hæv i banken 3000 kr.", 3000),
                new GainAmountChanceCard("De have en række med elleve rigtige i tipning, modtag 1000 kr.", 1000),
                new GainAmountChanceCard("Grundet dyrtiden har De fået gageforhøjelse, modtag 1000 kr.", 1000),
                new GainAmountChanceCard("Deres præmieobligation er udtrykket. De modtager 1000 kr af banken. ", 1000),
                new GainAmountChanceCard("Deres præmieobligation er udtrykket. De modtager 1000 kr af banken. ", 1000),
                new GainAmountChanceCard("De har solg nogle gamle møbler på auktion. Modtag 1000 kr af banken.", 1000),
                new GainAmountChanceCard("Værdien af egen avl fra nyttehaven udgør 200 som de modtager af banken", 200),
                new GetMoneyIfLowOnMoneyChanceCard("De modtager “Matador-legatet” på kr 40.000, men kun hvis værdier ikke overstiger 15.000 kr", 15000, 40000),
                new EveryPlayerPaysChanceCard("Det er deres fødselsdag. Modtag af hver medspiller 200 kr.", 200),
                new EveryPlayerPaysChanceCard("De har lagt penge ud til et sammenskudsgilde. Mærkværdigvis betaler alle straks. Modtag fra hver medspiller 500 kr.", 200),
                new EveryPlayerPaysChanceCard("De skal holde familiefest og får et tilskud fra hver medspiller på 500 kr.", 200),
                new MoveToFieldChanceCard("Ryk frem til START", 0),
                new MoveToFieldChanceCard("Ryk frem til START", 0),
                new MoveToFieldChanceCard("Ryk frem til Frederiksberg Allé. Hvis De passere START, indkasser da 4000 kr.", 11),
                new MoveToFieldChanceCard("Ryk frem til det nærmeste rederi og betal ejeren to gange den leje han ellers er berettiget til, hvis selskabet ikke ejes af nogen kan De købe det af banken.", 0), //probably just change this card to a regular move to field card that moves to a specific field
                new MoveToFieldChanceCard("Ryk frem til det nærmeste rederi og betal ejeren to gange den leje han ellers er berettiget til, hvis selskabet ikke ejes af nogen kan De købe det af banken.", 0), //probably just change this card to a regular move to field card that moves to a specific field
                new MoveToFieldChanceCard("Tag med Mols-Linien, flyt brikken frem og hvis De passerer START indkassér da kr 4000.", 15),
                new MoveToFieldChanceCard("Ryk frem til Grønningen, hvis De passerer start indkasser da kr 4000", 24),
                new MoveToFieldChanceCard("Ryk frem til Vimmelskaftet, hvis de passerer start indkasser da kr 4000", 32),
                new MoveToFieldChanceCard("Tag med den nærmeste færge, hvis de passerer start indkasser da kr 4000", 0), //probably just change this card to a regular move to field card that moves to a specific field
                new MoveToFieldChanceCard("Ryk frem til Strandvejen. Hvis De passere START, indkasser da 4000 kr.", 19),
                new MoveToFieldChanceCard("Tag til Rådhuspladsen", 39),
                new MoveAmountOfFieldsChanceCard("Ryk tre felter frem", 3),
                new MoveAmountOfFieldsChanceCard("Ryk tre felter tilbage", -3),
                new MoveAmountOfFieldsChanceCard("Ryk tre felter tilbage", -3),
                new OutOfJailChanceCard("I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller De kan sælge det."),
                new OutOfJailChanceCard("I anledning af kongens fødselsdag benådes De herved for fængsel. Dette kort kan opbevares indtil De får brug for det, eller De kan sælge det."),
                new GoToJailChanceCard("Gå i fængsel, De indkasserer ikke 4000 kr for at passere start."),
                new GoToJailChanceCard("Gå i fængsel, De indkasserer ikke 4000 kr for at passere start."),
        };
    }

    public ChanceCard[] getChanceCards(){
        return chanceCards;
    }
}

//TODO translate chancecard text