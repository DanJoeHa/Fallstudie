package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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
		this.view.setController( this );

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
			speichernAction();

		}
		if(button == "Zurücksetzen")
		{	
			this.view.reset();
		}
	}

	private void speichernAction() {
		Iterator<Art> i = this.art.iterator();		
		while( i.hasNext() ){
			tempArt = (Art) i.next();
			String Name = tempArt.getName();
			if(Name.equals( this.view.getArt())){
				break;
			}
		}
		int anzahl = view.getAnzahl();
		int kalenderjahr = view.getKalenderjahr();
		
		//Prüfung, ob Kalenderjahr eine Zahl ist (GUI gibt 0 zurück, wenn parseInt Exception wirft bzw. ein String eingegeben wurde)
		if(kalenderjahr == 0 )
		{
			HauptController.hauptfenster.setInfoBox("Kalenderjahr bitte in Ziffern angeben");
		}
		else{
			//Prüfung, ob Anzahl korrekten Wert hat
			if(anzahl <= 0)
			{
				HauptController.hauptfenster.setInfoBox("Anzahl darf nur Werte größer 0 und keine Buchstaben beinhalten");
			}
			else
			{
				new Eintrag(kalenderjahr,view.getKalenderwoche(), anzahl,HauptController.activeUser.getArbeitsgruppe(), tempArt);
				Calendar cal = Calendar.getInstance();
			    DateFormat df;
			    df = DateFormat.getDateTimeInstance( FULL, MEDIUM );
				HauptController.hauptfenster.setInfoBox("Ihr Eintrag wurde erfolgreich am "+ df.format(cal.getTime()) +" erfasst");
				view.reset();
			}
		}
	}

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
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			speichernAction();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
}
