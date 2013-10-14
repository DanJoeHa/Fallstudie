package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import fallstudie.controller.interfaces.Controller;
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
	
		//Popup hinzuf�gen + Infoboxausgabe
		if(button.equals("Speichern"))
		{
				Konfig.setJobIntervall(this.view.getAnzahlMonate());
				//Pr�fung, dass nur int zur�ckgegeben werden in GUI Klasse einf�gen
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
