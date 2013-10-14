package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Art;
import fallstudie.model.impl.Eintrag;
import fallstudie.view.impl.ErfassenView;
import fallstudie.view.interfaces.View;

public class ErfassenController implements Controller {
	
	private static final int FULL = 0;
	private static final int MEDIUM = 0;
	private ErfassenView view;
	private Collection<Art> art;
	private Art tempArt;
	
	public ErfassenController(){
		this.view = new ErfassenView();

		this.art = Art.getAlleArten();
		Iterator<Art> i = art.iterator();
		
		String[] sArt = new String[ art.size() ];
		int x = 0;
		while( i.hasNext() ){
			sArt[x] = i.next().getName();
			x++;
		} 
		
		this.view.setArten(sArt);
	}
	
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		
		if(button == "Speichern")
		{	
			Iterator<Art> i = this.art.iterator();		
			while( i.hasNext() ){
				tempArt = (Art) i.next();
				String Name = A.getName();
				if(Name.equals( this.view.getArt())){
					break;
				}
			}
			
			Eintrag eintrag = new Eintrag(HauptController.activeUser.getArbeitsgruppe(),view.getKalenderjahr(),view.getKalenderwoche(),view.getAnzahl(), tempArt);
			Calendar cal = Calendar.getInstance();
		    DateFormat df;
		    df = DateFormat.getDateTimeInstance( FULL, MEDIUM );
			HauptController.hauptfenster.setInfoBox("Ihr Eintrag wurde erfolgreich am"+ df.format(cal.getTime()) +"erfasst");
		}
	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return this.view;
	}
}
