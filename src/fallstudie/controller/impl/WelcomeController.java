package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.impl.WelcomeView;
import fallstudie.view.interfaces.View;

/**
 * Der Welcome-Controller ist für die Anzeige der Welcome-View verantwortlich.
 *
 */
public class WelcomeController implements Controller {
	
	/**
	 * Welcome View
	 */
	private WelcomeView view;
	
	/**
	 * Erstellt ein neues Objekt und lädt die Welcome-View mit personalisierter Nachricht.
	 */
	public WelcomeController(){
		this.view = new WelcomeView();
		
		String headline = "Herzlich Willkommen " + HauptController.activeUser.getFullName();
		HauptController.hauptfenster.setUeberschrift(headline);
		
		String msg = headline + ", \n \n \n \n Ihr letzter Login erfolgte am " + HauptController.activeUser.getLogin();
		msg += ". \n \n Bitte wählen Sie eine Aktion im Menübaum.";
		this.view.setHinweis(msg);
	}
	
	/**
	 * keine Aktion
	 */
	@Override
	public void actionPerformed(ActionEvent e) {}
	
	/**
	 * Gibt View zurück
	 */
	@Override
	public View getView() {
		return this.view;
	}
	
	/**
	 * keine Aktion
	 */
	@Override
	public void fortsetzen() {
		
	}
	/**
	 * keine Aktion
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}
	
	/**
	 * keine Aktion
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	/**
	 * keine Aktion
	 */
	@Override
	public void mouseExited(MouseEvent e) {}
	
	/**
	 * keine Aktion
	 */
	@Override
	public void mousePressed(MouseEvent e) {}
	
	/**
	 * keine Aktion
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	/**
	 * keine Aktion
	 */
	@Override
	public void keyPressed(KeyEvent e) {}
	
	/**
	 * keine Aktion
	 */
	@Override
	public void keyReleased(KeyEvent e) {}

	/**
	 * keine Aktion
	 */
	@Override
	public void keyTyped(KeyEvent e) {}

}
