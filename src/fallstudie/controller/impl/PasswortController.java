package fallstudie.controller.impl;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

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
			speichernAction();
		}
		
	}

	private void speichernAction() {
		//Hole Daten aus View
		String neuesPasswort = this.view.getNeuesPasswort();
		String wdhPasswort = this.view.getWdhPasswort();
		String altesPasswort = this.view.getAltesPasswort();
		
		//Prüfe, ob altes Passwort mit derzeitigem Passwort übereinstimmt
		if( HauptController.activeUser.checkPasswort(altesPasswort)){
			
			//Pürfe, neues Passwort vorhanden
			if(!neuesPasswort.equals(""))
			{
				
				//Prüfe, ob neues Passwort und dessen Wiederholung gleich sind
				if(wdhPasswort.equals(neuesPasswort)){
					
					//Prüfe, ob neues Passowrt mit dem alten Passowort übereinstimmt
					if(!altesPasswort.equals(neuesPasswort)){
						HauptController.activeUser.setPasswort(neuesPasswort);
						HauptController.hauptfenster.setInfoBox("Passwort erfolgreich geändert");
						this.view.reset();
					}
					else{
						HauptController.hauptfenster.setInfoBox("Ihr Passwort wurde nicht geändert! Das neue Passwort darf nicht mit dem alten Passwort übereinstimmen");
						this.view.reset();
					}
					
				}
				else
				{
					HauptController.hauptfenster.setInfoBox("Passwörter stimmen nicht überein");
					this.view.reset();
				}
			}
			else
			{
				HauptController.hauptfenster.setInfoBox("Bitte neues Passwort eintragen.");
				this.view.reset();
			}
		}
		else
		{
			HauptController.hauptfenster.setInfoBox("Altes Passwort falsch");
		}
	}

	@Override
	public View getView() {
		return this.view;
	}

	@Override
	public void fortsetzen() {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			speichernAction();
		}		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
