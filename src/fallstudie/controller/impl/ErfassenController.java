package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.util.Calendar;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Art;
import fallstudie.model.impl.Eintrag;
import fallstudie.view.impl.ErfassenView;
import fallstudie.view.interfaces.View;

public class ErfassenController implements Controller {
	
	private static final int FULL = 0;
	private static final int MEDIUM = 0;
	private ErfassenView view;
	
	public ErfassenController(){
		this.view = new ErfassenView();
		//this.view.setArten(Art.getAlleArten().toString()); //Collection umwandeln in String? Vereinheitlichung? immer String Array?
	}
	
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		
		if(button == "Speichern")
		{	
			
			Eintrag eintrag = new Eintrag(activeUser.getArbeitsgruppe(),view.getKalenderjahr(),view.getKalenderwoche(),view.getAnzahl(), view.getArt());
			//kein Objekt Art �bergeben, sondern String, weil aus dem String kann kein Objekt erzeugt w-erden, da ansonsten �ber den Konstruktor der Modell-Schicht eine Art angelegt wird
			Calendar cal = Calendar.getInstance();
		    DateFormat df;
		    df = DateFormat.getDateTimeInstance( FULL, MEDIUM );
			HauptController.hauptfenster.setInfoBox("Ihr Eintrag wurde erfolgreich am"+ df.format(cal.getTime()) +"erfasst");
		}
	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return null;
	}
}
