package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Konfig;
import fallstudie.view.impl.KonfigurationView;
import fallstudie.view.interfaces.View;

public class KonfigController implements Controller {
	
	private KonfigurationView view;
	
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
				String msg;
				if( Konfig.setJobIntervall(this.view.getAnzahlMonate()) ){
					msg = "Neue Job-Konfiguration gespeichert.";
				}else{
					msg = "Job-Konfiguration konnte nicht geändert werden.";
				}
				HauptController.hauptfenster.setInfoBox(msg);
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
}
