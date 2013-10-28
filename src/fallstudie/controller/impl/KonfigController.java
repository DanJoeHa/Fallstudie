package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Konfig;
import fallstudie.view.impl.BestaetigenPopup;
import fallstudie.view.impl.HilfeTexte;
import fallstudie.view.impl.KonfigurationView;
import fallstudie.view.interfaces.View;

/**
 * Der Konfig-Controller ist für die Einstellung des Jobintervalls zur Löschung von alten Einträgen verantwortlich.
 *
 */
public class KonfigController implements Controller {
	
	/**
	 * Konfigurations View
	 */
	private KonfigurationView view;
	/**
	 * Bestätigungspopup
	 */
	public static BestaetigenPopup popup;
	/**
	 * Popup bereits angezeigt
	 */
	private boolean isPop = false;
	
	/**
	 * Erstellt ein neues Objekt und zeigt die KonfiView an.
	 */
	public KonfigController(){
		this.view = new KonfigurationView();
		this.view.setAnzahlMonate(Konfig.getJobIntervall());
		this.view.setController( this );
	}
	
	/**
	 * ActionListener für die Buttons in der View.
	 */
	public void actionPerformed(ActionEvent e)
	{
		String button = e.getActionCommand();
	
		//Popup hinzufügen + Infoboxausgabe
		if(button.equals("Speichern"))
		{		
			konfigPopup();
		}	
		if(button.equals("Ja")){
				jaAction();
		}
		if(button.equals("Nein")){
			popup.setVisible(false);
		}
	}

	/**
	 * Speichern der Konfiguration.
	 */
	private void jaAction() {
		String msg = "Neue Job-Konfiguration gespeichert.";
		int monate = this.view.getAnzahlMonate();
		System.out.println(monate);
		if(monate <= 0){
			msg = "Bitte nur Zahlen eingeben, die größer als 0 sind";
		}
		else{
			try {
				Konfig.setJobIntervall(this.view.getAnzahlMonate());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
			
		popup.setVisible(false);	

		HauptController.hauptfenster.setInfoBox(msg);
	}

	/**
	 * Bestätigungspopup
	 */
	private void konfigPopup() {
		popup = new BestaetigenPopup();
		
		popup.setController(this);
		popup.setTitle("Bestätigung");
		popup.setAusgabe(HilfeTexte.SpeichernPopup);
	}
	
	/**
	 * Liefert die KonfirgurationView
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	@Override
	public View getView() {
		return this.view;
	}

	/**
	 * keine Aktion.
	 */
	@Override
	public void fortsetzen() {}

	/**
	 * keine Aktion.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * keine Aktion.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/**
	 * keine Aktion.
	 */
	@Override
	public void mouseExited(MouseEvent e) {}

	/**
	 * keine Aktion.
	 */
	@Override
	public void mousePressed(MouseEvent e) {}

	/**
	 * keine Aktion.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}

	/**
	 * keine Aktion.
	 */
	@Override
	public void keyPressed(KeyEvent e) {}

	/**
	 * KeyListener auf Enter-Taste
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER && isPop==false){
			konfigPopup();
			isPop=true;
		}
		if ((e.getKeyCode() == KeyEvent.VK_ENTER  && popup.isFocused() == true && popup.hatFocus()== "popupJa")){
			jaAction();
			isPop=false;
		}
		if ((e.getKeyCode() == KeyEvent.VK_ENTER && popup.isFocused() == true && popup.hatFocus() == "popupNein")){
			popup.setVisible(false);
			isPop=false;
			HauptController.hauptfenster.setInfoBox("");
		}
	}

	/**
	 * keine Aktion.
	 */
	@Override
	public void keyTyped(KeyEvent e) {}
}
