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
}