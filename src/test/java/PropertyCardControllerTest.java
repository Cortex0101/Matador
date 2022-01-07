import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyCardControllerTest {
    PropertyCard[] propertyCards = PropertyCardCreator.createPropertyCards();
    PropertyCardController propertyCardController = new PropertyCardController(propertyCards);

    @DisplayName("Property counts work")
    @Test
    void setPropertyCounts() {
        int expected = propertyCardController.fieldsInGroup(propertyCards[0]);
        assertEquals(expected, 2);

        expected = propertyCardController.fieldsInGroup(propertyCards[5]);
        assertEquals(expected, 3);

        expected = propertyCardController.fieldsInGroup(propertyCards[16]);
        assertEquals(expected, 3);

        expected = propertyCardController.fieldsInGroup(propertyCards[20]);
        assertEquals(expected, 2);

        expected = propertyCardController.fieldsInGroup(propertyCards[23]);
        assertEquals(expected, 4);

        expected = propertyCardController.fieldsInGroup(propertyCards[26]);
        assertEquals(expected, 2);
    }

    @DisplayName("Can detect owning all streets")
    @Test
    void ownAllStreets() {
        Player john = new Player("John", 30000);
        Player bob = new Player("Bob", 30000);

        propertyCards[0].setOwner(john);
        propertyCards[1].setOwner(john);
        assertTrue(propertyCardController.allStreetsInGroupOwnedBy((StreetCard) propertyCards[1], john));
        assertFalse(propertyCardController.allStreetsInGroupOwnedBy((StreetCard) propertyCards[1], bob));

        propertyCards[8].setOwner(john);
        propertyCards[9].setOwner(john);
        propertyCards[10].setOwner(john);
        assertTrue(propertyCardController.allStreetsInGroupOwnedBy((StreetCard) propertyCards[10], john));
        assertFalse(propertyCardController.allStreetsInGroupOwnedBy((StreetCard) propertyCards[10], bob));

        propertyCards[11].setOwner(john);
        propertyCards[12].setOwner(bob);
        propertyCards[13].setOwner(john);
        assertFalse(propertyCardController.allStreetsInGroupOwnedBy((StreetCard) propertyCards[11], john));
        assertFalse(propertyCardController.allStreetsInGroupOwnedBy((StreetCard) propertyCards[13], bob));

        propertyCards[20].setOwner(bob);
        propertyCards[21].setOwner(bob);
        assertTrue(propertyCardController.allStreetsInGroupOwnedBy((StreetCard) propertyCards[20], bob));

        //assertFalse(propertyCardController.allStreetsInGroupOwnedBy((StreetCard) propertyCards[15], john));
        assertFalse(propertyCardController.allStreetsInGroupOwnedBy((StreetCard) propertyCards[15], bob));
    }

    @DisplayName("Can purchase houses and sell")
    @Test
    void purchaseAndSellHouses() {
        final int initialCapital = 30000;
        Player john = new Player("John", initialCapital);
        propertyCards[0].setOwner(john);
        propertyCards[1].setOwner(john);

        assertTrue(propertyCardController.purchaseHouse((StreetCard) propertyCards[1], john));
        assertEquals(1, ((StreetCard) propertyCards[1]).getHouses());
        assertEquals(initialCapital - ((StreetCard) propertyCards[1]).getHousePrice(), john.getAccount().getBalance());

        assertFalse(propertyCardController.purchaseHouse((StreetCard) propertyCards[1], john));
        assertEquals(1, ((StreetCard) propertyCards[1]).getHouses());
        assertEquals(initialCapital - ((StreetCard) propertyCards[1]).getHousePrice(), john.getAccount().getBalance());

        assertTrue(propertyCardController.purchaseHouse((StreetCard) propertyCards[0], john));
        assertEquals(1, ((StreetCard) propertyCards[0]).getHouses());
        assertEquals(initialCapital - ((StreetCard) propertyCards[1]).getHousePrice() - ((StreetCard) propertyCards[0]).getHousePrice(), john.getAccount().getBalance());

        assertTrue(propertyCardController.purchaseHouse((StreetCard) propertyCards[0], john));
        assertTrue(propertyCardController.purchaseHouse((StreetCard) propertyCards[1], john));
        assertTrue(propertyCardController.purchaseHouse((StreetCard) propertyCards[1], john));
        assertTrue(propertyCardController.purchaseHouse((StreetCard) propertyCards[0], john));
        assertTrue(propertyCardController.purchaseHouse((StreetCard) propertyCards[1], john));
        assertFalse(propertyCardController.purchaseHouse((StreetCard) propertyCards[1], john));
        assertTrue(propertyCardController.purchaseHouse((StreetCard) propertyCards[0], john));
        assertTrue(propertyCardController.purchaseHouse((StreetCard) propertyCards[1], john));
        assertTrue(propertyCardController.purchaseHouse((StreetCard) propertyCards[0], john));

        assertFalse(propertyCardController.purchaseHouse((StreetCard) propertyCards[0], john));
        assertFalse(propertyCardController.purchaseHouse((StreetCard) propertyCards[1], john));

        assertFalse(propertyCardController.purchaseHouse((StreetCard) propertyCards[2], john));


        int capitalAfterBuying = john.getAccount().getBalance();
        // Sell
        assertTrue(propertyCardController.sellHouse((StreetCard) propertyCards[0], john));
        assertEquals(4, ((StreetCard) propertyCards[0]).getHouses());
        assertEquals(capitalAfterBuying + (((StreetCard) propertyCards[0]).getHousePrice() / 2), john.getAccount().getBalance());

        assertFalse(propertyCardController.sellHouse((StreetCard) propertyCards[0], john));

        assertTrue(propertyCardController.sellHouse((StreetCard) propertyCards[1], john));
        assertEquals(4, ((StreetCard) propertyCards[1]).getHouses());
        assertEquals(capitalAfterBuying + (((StreetCard) propertyCards[0]).getHousePrice() / 2) + (((StreetCard) propertyCards[1]).getHousePrice() / 2), john.getAccount().getBalance());

        assertTrue(propertyCardController.sellHouse((StreetCard) propertyCards[0], john));
        assertTrue(propertyCardController.sellHouse((StreetCard) propertyCards[1], john));
        assertTrue(propertyCardController.sellHouse((StreetCard) propertyCards[0], john));
        assertTrue(propertyCardController.sellHouse((StreetCard) propertyCards[1], john));
        assertTrue(propertyCardController.sellHouse((StreetCard) propertyCards[0], john));
        assertTrue(propertyCardController.sellHouse((StreetCard) propertyCards[1], john));
        assertTrue(propertyCardController.sellHouse((StreetCard) propertyCards[0], john));
        assertTrue(propertyCardController.sellHouse((StreetCard) propertyCards[1], john));
        assertFalse(propertyCardController.sellHouse((StreetCard) propertyCards[0], john));
        assertFalse(propertyCardController.sellHouse((StreetCard) propertyCards[1], john));

        assertEquals(initialCapital - ((((StreetCard) propertyCards[0]).getHousePrice() / 2) * 10), john.getAccount().getBalance());
    }
}