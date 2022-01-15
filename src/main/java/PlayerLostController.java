import gui_fields.GUI_Field;
import gui_fields.GUI_Ownable;

import java.awt.*;

public class PlayerLostController {

    public void removePlayer(Player player, PropertyCardController propertyCardController){
        player.setIsActive(false);
        Bank.payBank(player, player.getAccount().getBalance());
        for (int i = 0; i < 40; i++) {
            if(GUIInstance.getInstance().getFields()[i] instanceof GUI_Ownable) {
                GUI_Ownable gui_ownable = (GUI_Ownable) GUIInstance.getInstance().getFields()[i];
                if (gui_ownable.getOwnerName() != null && gui_ownable.getOwnerName().equals(player.getName())) {
                    gui_ownable.setBorder(Color.BLACK);
                }
            }
        }
        for (int i = 0; i < player.getOwnedPropertyCards(propertyCardController).length; i++) {
            player.getOwnedPropertyCards(propertyCardController)[i].setMortgaged(false);
            player.getOwnedPropertyCards(propertyCardController)[i].setOwner(null);
        }
        player.getCar().removeCarFromGame(player);
    }
}
