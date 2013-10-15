package fallstudie.view.impl;

import javax.swing.JOptionPane;

public class UHDpopup{
        public static void main(String[] bla){
 
                // Erstellung Array vom Datentyp Object, Hinzufügen der Optionen               
                Object[] options = {"OK"};
 
                int selected = JOptionPane.showOptionDialog(null,
                                                            "Bitte wenden Sie sich an den User Help Desk"
                                                            + " Telefon-Nr.: 0711/123456",
                                                            "Passwort vergessen?",
                                                            JOptionPane.DEFAULT_OPTION, 
                                                            JOptionPane.INFORMATION_MESSAGE, 
                                                            null, options, options[0]);
                System.out.println(selected);
 
                System.exit(0);
 
        }
}