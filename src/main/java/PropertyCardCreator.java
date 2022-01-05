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

         return propertyCards;
    }
}
