package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

public class KonfigController extends HauptController {
	
	private KonfigurationView view;
	
	public KonfigController(){
		this.view = new KonfigurationView();
		this.view.setAnzahlMonate(Konfig.getJobIntervall());
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String button = e.getActionCommand();
	
		//Popup hinzufügen + Infoboxausgabe
		if(button.equals("Speichern"))
		{
				Konfig.setJobIntervall(this.view.getAnzahlMonate());
				//Prüfung, dass nur int zurückgegeben werden in GUI Klasse einfügen
		}
	}
}
