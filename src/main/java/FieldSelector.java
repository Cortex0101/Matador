import gui_fields.GUI_Field;
import gui_main.GUI;

import java.util.Arrays;

public class FieldSelector {
     GUI_Field[] fieldOptions;
     GUI_Field fieldSelected;
     String[] fieldTitles;

     public FieldSelector(GUI_Field[] options) {
          this.fieldOptions = options;
          this.fieldTitles = getFieldTitlesArray(options);
     }

     public GUI_Field getSelected() {
          return fieldSelected;
     }

     public void getUserSelection() {
          String userSelection = GUIInstance.getInstance().getUserSelection("Which field would you like to move to?", fieldTitles);
          fieldSelected = fieldOptions[Arrays.asList(fieldTitles).indexOf(userSelection)];
     }

     public String[] getFieldTitles() {
          return this.fieldTitles;
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
