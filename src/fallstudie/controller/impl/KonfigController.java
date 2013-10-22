package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Konfig;
import fallstudie.view.impl.BestaetigenPopup;
import fallstudie.view.impl.HilfeTexte;
import fallstudie.view.impl.KonfigurationView;
import fallstudie.view.interfaces.View;

public class KonfigController implements Controller {
	
	private KonfigurationView view;
	public static BestaetigenPopup popup;
	
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
			popup = new BestaetigenPopup();
			
			popup.setController(this);
			popup.setTitle("Bestätigung");
			popup.setAusgabe(HilfeTexte.SpeichernPopup);
		}	
		if(button.equals("Ja")){
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
		if(button.equals("Nein")){
			popup.setVisible(false);
		}
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
		// TODO Auto-generated method stub
		
	}
}
