package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Mitarbeiter;
import fallstudie.view.impl.HilfeTexte;
import fallstudie.view.impl.LoginView;
import fallstudie.view.interfaces.View;

/**
 * Der Login-Controller ist für das überprüfen von Passwörtern und Benutzer verantwortlich.
 *
 */
public class LoginController implements Controller, KeyListener
{
	/**
	 * die Login-View
	 */
	private LoginView view;
	
	/**
	 * Erstellt ein neues Objekt und erstellt die Login-View.
	 */
	public LoginController()
	{
		//View laden
		this.view = new LoginView();
		this.view.setController( this );
	}
	
	/**
	 * Horcht auf den Button Login
	 */
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		if(button == "Login")
		{
			loginAction();
		}
		
		
	}

	/**
	 * Prüft die Userangaben Benutzername und Passwort. Ggf. wird der Bentuzer eingeloggt oder erhält die Rückmeldung über fehlerhafte Daten. Der User wird anschließend weitergeleitet.
	 */
	private void loginAction() {
		//InfoBox leeren, wenn andere Navigation ausgewählt wird
		HauptController.hauptfenster.setInfoBox(" ");
		
		try{
			HauptController.activeUser = Mitarbeiter.einloggen(this.view.getBenutzername(), this.view.getPasswort());
			
			//Navigations-Baum entsprechend Rechten von User aufbauen
			HauptController.hauptfenster.createNavTree(HauptController.activeUser.checkRecht("Daten erfassen"), HauptController.activeUser.checkRecht("Lesen"), HauptController.activeUser.checkRecht("Arbeitsgruppe anlegen"), HauptController.activeUser.checkRecht("Eintragsart anlegen"), HauptController.activeUser.checkRecht("Bereich anlegen"), HauptController.activeUser.checkRecht("Mitarbeiter anlegen"), HauptController.activeUser.checkRecht("Jobintervall festlegen"));
			
			if( !HauptController.activeUser.passwortIsChanged() ){
				PasswortController pc = new PasswortController();
				HauptController.hauptfenster.setContent( pc.getView() );
				HauptController.hauptfenster.setUeberschrift("Initial Passwort ändern");
				HauptController.hilfefenster.setHinweis(HilfeTexte.PasswortaendernView);
				HauptController.hilfefenster.setTitle("Hilfe - Passwort ändern");
			}else{
				WelcomeController wc = new WelcomeController();
				HauptController.hauptfenster.setContent( wc.getView() );
				HauptController.hilfefenster.setHinweis(HilfeTexte.WelcomeView);
				HauptController.hilfefenster.setTitle("Hilfe - Startseite");
			}
		}catch(Exception ex){
			HauptController.hauptfenster.setInfoBox( ex.getMessage() );
			this.view.reset();
		}
	}

	/**
	 * Gibt die View zurück
	 */
	@Override
	public View getView() {
		return this.view;
	}

	/**
	 * keine Aktion
	 */
	@Override
	public void fortsetzen() {}

	/**
	 * Horcht auf Key "Enter", setzt Message in der InfoBox.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			HauptController.hauptfenster.setInfoBox("Moment bitte, Loginvorgang!");
		}
	}

	/**
	 * Wenn Key "Enter" losgelassen wurde, login durchführen.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			loginAction();
		}		
	}

	/**
	 * keine Action
	 */
	@Override
	public void keyTyped(KeyEvent e) {}

	/**
	 * keine Action
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * keine Action
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/**
	 * keine Action
	 */
	@Override
	public void mouseExited(MouseEvent e) {}

	/**
	 * Wenn Mausklick auf Button "login" Info in InfoBox ausgeben.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getClickCount()==1 && this.view.getBenutzername().isEmpty()==false && this.view.getPasswort().isEmpty()==false){
		HauptController.hauptfenster.setInfoBox("Moment bitte, Loginvorgang!");
		}
	}

	/**
	 * keine Action
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}
	
}
