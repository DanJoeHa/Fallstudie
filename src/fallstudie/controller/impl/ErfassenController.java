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

/**
 * Der Erfassen-Controller ist für das Erfassen von neuen Einträgen verantwortlich.
 *
 */
public class ErfassenController implements Controller {
	
	/**
	 * Marc?!
	 */
	private static final int FULL = 0;
	/**
	 * Marc?!
	 */
	private static final int MEDIUM = 0;
	/**
	 * View zum Erfassen von neuen Einträgen
	 */
	private ErfassenView view;
	/**
	 * Collection an Arten aus Model-Schicht
	 */
	private Collection<Art> art;
	/**
	 * Zwischenspeicher
	 */
	private Art tempArt;
	
	/**
	 * Erstellt ein neues Objekt und befüllt die View zur Erfassung von neuen Einträgen.
	 */
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
	
	/**
	 * ActionListener auf Buttons "Speichern" und "Zurücksetzen"
	 */
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

	/**
	 * Speichert einen neuen Eintrag.
	 */
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

	/**
	 * Liefert die Erfassen View zurück
	 */
	@Override
	public View getView() {
		return this.view;
	}

	/**
	 * keine Aktion
	 */
	@Override
	public void fortsetzen() {}

	/**
	 * keine Aktion
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * keine Aktion
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/**
	 * keine Aktion
	 */
	@Override
	public void mouseExited(MouseEvent e) {}

	/**
	 * keine Aktion
	 */
	@Override
	public void mousePressed(MouseEvent e) {}

	/**
	 * keine Aktion
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}

	/**
	 * keine Aktion
	 */
	@Override
	public void keyPressed(KeyEvent e) {}

	/**
	 * Enter-Taste löst Speichern-Popup zur Bestätigung aus 
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			speichernAction();
		}
	}

	/**
	 * keine Aktion
	 */
	@Override
	public void keyTyped(KeyEvent e) {}
	
}
