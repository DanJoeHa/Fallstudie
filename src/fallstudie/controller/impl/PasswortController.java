package fallstudie.controller.impl;
import java.awt.event.ActionEvent;

import fallstudie.view.impl.PasswortAendernView;
import fallstudie.view.interfaces.View;

/**
 * Controller zur Passwortänderung
 * 
 * @author Marc, Johannes
 * @version 1.0
 *
 */
public class PasswortController extends HauptController {
	
	private PasswortAendernView activeView;
	
	/**
	 * Zeigt die Passwort-Ändern Maske
	 * 
	 * @author Marc
	 * @version 1.0
	 */
	public PasswortController(){
		this.activeView = new PasswortAendernView();
	}
	
	/**
	 * Hört auf Button "Speichern", prüft ob Passwörter übereinstimmen und informiert User über Erfolg der Operation
	 * 
	 * @author Marc, Johannes
	 * @version 1.0
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		
		//get action
		String button = e.getActionCommand();
		
		// wenn button "Speichern" angeklickt wurde
		if(button == "Speichern")
		{
			//Hole Daten aus View
			String neuesPasswort = this.activeView.getNeuesPasswort();
			String wdhPasswort = this.activeView.getWdhPasswort();
			String altesPasswort = this.activeView.getAltesPasswort();
			
			//Prüfe, ob altes Passwort mit derzeitigem Passwort übereinstimmt
			if( activeUser.checkPasswort(altesPasswort)){
				
				//Prüfe, ob neues Passwort und dessen Wiederholung gleich sind
				if(wdhPasswort.equals(neuesPasswort)){
					activeUser.setPasswort(neuesPasswort);
					hauptfenster.setInfoBox("Passwort erfolgreich geändert");
				}
				else
				{
					hauptfenster.setInfoBox("Passwörter stimmen nicht überein");
				}
			}
			else
			{
				hauptfenster.setInfoBox("Altes Passwort falsch");
			}
		}
		
	}
}
