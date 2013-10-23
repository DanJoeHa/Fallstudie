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

public class KonfigController implements Controller {
	
	private KonfigurationView view;
	public static BestaetigenPopup popup;
	private boolean isPop=false;
	
	public KonfigController(){
		this.view = new KonfigurationView();
		this.view.setAnzahlMonate(Konfig.getJobIntervall());
		this.view.setController( this );
	}
	
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

	private void konfigPopup() {
		popup = new BestaetigenPopup();
		
		popup.setController(this);
		popup.setTitle("Bestätigung");
		popup.setAusgabe(HilfeTexte.SpeichernPopup);
	}
	
	/**
	 * Lieftert die KonfirgurationView
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
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

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
