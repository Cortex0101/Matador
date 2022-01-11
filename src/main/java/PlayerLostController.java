import gui_fields.GUI_Ownable;

import java.awt.*;

public class PlayerLostController {

    public void removePlayer(Player player, PropertyCardController propertyCardController){
        PropertyCard[] cards = new PropertyCard[player.getOwnedPropertyCards(propertyCardController).length];
        for (int i = 0; i < player.getOwnedPropertyCards(propertyCardController).length; i++) {
            cards[i] = player.getOwnedPropertyCards(propertyCardController)[i];
            player.getOwnedPropertyCards(propertyCardController)[i].setOwner(null);
        }
        for (int i = 0; i < 40; i++) {
            GUI_Ownable gui_ownable = (GUI_Ownable) GUIInstance.getInstance().getFields()[i];
            if(gui_ownable.getOwnerName().equals(player.getName()))
            gui_ownable.setBorder(Color.BLACK);
        }
    }
}
