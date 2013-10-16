package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Konfig;
import fallstudie.view.impl.HauptView;
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
				String msg = "Neue Job-Konfiguration gespeichert.";
				int monate = this.view.getAnzahlMonate();
				System.out.println(monate);
				if(monate == 0){
					System.out.println("HI");
					msg = "Bitte nur Zahlen eingeben, die größer als 0 sind";
				}
				else{
					try {
						Konfig.setJobIntervall(this.view.getAnzahlMonate());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
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

	@Override
	public void fortsetzen() {
		// TODO Auto-generated method stub
		
	}
}
