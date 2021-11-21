import gui_fields.GUI_Field;
import gui_main.GUI;

import java.util.Arrays;

public class FieldSelector {
     GUI_Field[] options;
     GUI_Field selected;

     public FieldSelector(GUI_Field[] options) {
          this.options = options;
          this.selected = null;
     }

     public GUI_Field getSelected() {
          return this.selected;
     }

     public void getUserSelection() {
          String[] fieldTitles = getFieldTitlesArray(this.options);
          String userSelection = GUIInstance.getInstance().getUserSelection("Which field would you like to move to?", fieldTitles);
          this.selected = options[Arrays.asList(fieldTitles).indexOf(userSelection)];
     }

     public String[] getFieldTitles() {
          return getFieldTitlesArray(this.options);
     }

     /**
      * Utility function to convert an array of fields to an array of string with the fields titles
      */
     static private String[] getFieldTitlesArray(GUI_Field[] fields) {
          String[] titles = new String[fields.length];
          for (int i = 0; i < fields.length; i++) {
               titles[i] = fields[i].getTitle();
          }
          return titles;
     }
}
