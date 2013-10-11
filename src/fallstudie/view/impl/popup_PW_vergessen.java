package fallstudie.view.impl;

import javax.swing.JOptionPane;

public class popup_PW_vergessen{
        public static void main(String[] bla){
 
                // Erstellung Array vom Datentyp Object, Hinzufügen der Optionen               
                Object[] options = {"Schließen"};
 
                int selected = JOptionPane.showOptionDialog(null,
                                                            "Melden sie sich bitte bei ihrem User Help Desk unter der Telefonnummer 777/12345",
                                                            "Passwort vergessen?",
                                                            JOptionPane.DEFAULT_OPTION, 
                                                            JOptionPane.INFORMATION_MESSAGE, 
                                                            null, options, options[0]);
                System.out.println(selected);
 
                System.exit(0);
 
        }
}