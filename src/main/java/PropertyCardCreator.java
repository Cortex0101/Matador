public class PropertyCardCreator {
     public PropertyCardCreator() {}

    public static PropertyCard[] createPropertyCards() {
         PropertyCard[] propertyCards = new PropertyCard[28];

         propertyCards[0] = new StreetCard(
                 "Rødovrevej",
                 new int[]{50, 250, 750, 2250, 4000, 6000},
                 600,
                 1000);

        propertyCards[1] = new StreetCard(
                "Hvidovrevej",
                new int[]{50, 250, 750, 2250, 4000, 6000},
                600,
                1000);

        // ship

        propertyCards[2] = new StreetCard(
                "Roskildevej",
                new int[]{100, 600, 1800, 5400, 8000, 11000},
                1000,
                1000);

        propertyCards[3] = new StreetCard(
                "Valby Langgade",
                new int[]{100, 600, 1800, 5400, 8000, 11000},
                1000,
                1000);


        propertyCards[4] = new StreetCard(
                "Allegade",
                new int[]{150, 800, 2000, 6000, 9000, 12000},
                1200,
                1000);

        propertyCards[5] = new StreetCard(
                "Allegade",
                new int[]{200, 1000, 3000, 9000, 12500, 15000},
                1400,
                2000);

        // Brewery

        propertyCards[6] = new StreetCard(
                "Allegade",
                new int[]{200, 1000, 3000, 9000, 12500, 15000},
                1400,
                2000);

        propertyCards[7] = new StreetCard(
                "Allegade",
                new int[]{250, 1250, 3750, 10000, 14000, 18000},
                1600,
                2000);

        // Brewery

        propertyCards[8] = new StreetCard(
                "Allegade",
                new int[]{300, 1400, 4000, 11000, 15000, 19000},
                1800,
                2000);

        propertyCards[9] = new StreetCard(
                "Allegade",
                new int[]{300, 1400, 4000, 11000, 15000, 19000},
                1800,
                2000);

        propertyCards[10] = new StreetCard(
                "Allegade",
                new int[]{350, 1600, 4400, 12000, 16000, 20000},
                2000,
                2000);

        propertyCards[11] = new StreetCard(
                "Allegade",
                new int[]{350, 1800, 5000, 14000, 17500, 21000},
                2200,
                3000);

        propertyCards[12] = new StreetCard(
                "Allegade",
                new int[]{350, 1800, 5000, 14000, 17500, 21000},
                2200,
                3000);

        propertyCards[13] = new StreetCard(
                "Allegade",
                new int[]{400, 2000, 6000, 15000, 18500, 22000},
                2400,
                3000);

        // Brewery

        propertyCards[14] = new StreetCard(
                "Valby Langgade",
                new int[]{450, 2200, 6600, 16000, 19500, 23000},
                2600,
                3000);

        propertyCards[15] = new StreetCard(
                "Valby Langgade",
                new int[]{450, 2200, 6600, 16000, 19500, 23000},
                2600,
                3000);

        propertyCards[16] = new StreetCard(
                "Valby Langgade",
                new int[]{500, 2400, 7200, 17000, 20500, 24000},
                2800,
                3000);

        propertyCards[17] = new StreetCard(
                "Valby Langgade",
                new int[]{550, 2600, 7800, 18000, 22000, 25000},
                3000,
                4000);


        propertyCards[18] = new StreetCard(
                "Valby Langgade",
                new int[]{550, 2600, 7800, 18000, 22000, 25000},
                3000,
                4000);

        propertyCards[19] = new StreetCard(
                "Valby Langgade",
                new int[]{600, 3000, 9000, 20000, 24000, 28000},
                3200,
                4000);


        propertyCards[20] = new StreetCard(
                "Valby Langgade",
                new int[]{700, 3500, 10000, 22000, 26000, 30000},
                4000,
                4000);

        propertyCards[21] = new StreetCard(
                "Valby Langgade",
                new int[]{1000, 4000, 12000, 28000, 32000, 40000},
                4000,
                4000);

        propertyCards[22] = new ShippingCard(
                "X",
                new int[]{500, 1000, 2000, 4000},
                2000);

        propertyCards[23] = new ShippingCard(
                "X",
                new int[]{500, 1000, 2000, 4000},
                2000);

        propertyCards[24] = new ShippingCard(
                "X",
                new int[]{500, 1000, 2000, 4000},
                2000);

        propertyCards[25] = new ShippingCard(
                "X",
                new int[]{500, 1000, 2000, 4000},
                2000);

        propertyCards[26] = new BreweryCard(
                "X",
                new int[]{100, 200},
                1500);

        propertyCards[27] = new BreweryCard(
                "X",
                new int[]{100, 200},
                1500);

        return propertyCards;
    }
}
