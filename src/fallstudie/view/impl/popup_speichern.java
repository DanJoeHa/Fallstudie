package fallstudie.view.impl;
import javax.swing.JOptionPane;
 
public class popup_speichern{
        public static void main(String[] bla){
 
                // Erstellung Array vom Datentyp Object, Hinzuf�gen der Optionen               
                Object[] options = {"Abbrechen", "L�schen"};
 
                int selected = JOptionPane.showOptionDialog(null,
                                                            "Wollen Sie wirklich speichern?",
                                                            "Alternativen",
                                                            JOptionPane.DEFAULT_OPTION, 
                                                            JOptionPane.INFORMATION_MESSAGE, 
                                                            null, options, options[0]);
                System.out.println(selected);
 
                System.exit(0);
 
        }
}