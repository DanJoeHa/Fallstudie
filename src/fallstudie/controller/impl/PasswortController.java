package fallstudie.controller.impl;
import java.awt.event.ActionEvent;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.impl.PasswortAendernView;
import fallstudie.view.interfaces.View;

/**
 * Controller zur Passwortänderung
 * 
 * @author Marc, Johannes
 * @version 1.0
 *
 */
public class PasswortController implements Controller {
	
	private PasswortAendernView view;
	
	/**
	 * Zeigt die Passwort-Ändern Maske
	 * 
	 * @author Marc
	 * @version 1.0
	 */
	public PasswortController(){
		this.view = new PasswortAendernView();
		this.view.setController( this );
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
		if(button.equals("Speichern"))
		{
			//Hole Daten aus View
			String neuesPasswort = this.view.getNeuesPasswort();
			String wdhPasswort = this.view.getWdhPasswort();
			String altesPasswort = this.view.getAltesPasswort();
			
			//Prüfe, ob altes Passwort mit derzeitigem Passwort übereinstimmt
			if( HauptController.activeUser.checkPasswort(altesPasswort)){
				
				//Prüfe, ob neues Passwort und dessen Wiederholung gleich sind
				if(wdhPasswort.equals(neuesPasswort)){
					HauptController.activeUser.setPasswort(neuesPasswort);
					HauptController.hauptfenster.setInfoBox("Passwort erfolgreich geändert");
				}
				else
				{
					HauptController.hauptfenster.setInfoBox("Passwörter stimmen nicht überein");
					this.view.reset();
				}
			}
			else
			{
				HauptController.hauptfenster.setInfoBox("Altes Passwort falsch");
			}
		}
		
	}

	@Override
	public View getView() {
		return this.view;
	}
}
