package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.util.Collection;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Bereich;
import fallstudie.model.impl.Mitarbeiter;
import fallstudie.view.interfaces.View;

public class ArbeitsgruppenController implements Controller {
	
	private ArbeitsgruppeBearbeitenAnlegenView view;
	private String operation;
	
	/**
	 * Zeigt  die View zur Arbeitsgruppenbearbeiten/-anlage abhängig von der Operation an
	 * 
	 * @author Johannes
	 * @version 0.1
	 */
	public ArbeitsgruppenController(){
		
		//View laden
		this.view = new ArbeitsgruppeBearbeitenAnlegenView();	
		this.view.setController( this );
		
	}
	
	/**
	 * Erwartet einen der Strings "anlegen" oder "bearbeiten"
	 * 
	 * @author Johannes
	 * @version 1.0
	 * @param operation
	 */
	public void setOperation(String operation){
		
		//Operation speichern
		this.operation = operation;
		
		//wenn Neuanlage einer Arbeitsgruppe
		if( this.operation == "anlegen" ){
			
			//alle Bereiche holen und an View geben
			Collection<Bereich> bereiche = Bereich.getAlleBereiche();
			this.view.setBereich( bereiche.toArray() );
		}
		
		//wenn Bearbeiten einer Arbeitsgruppe
		if( this.operation == "bearbeiten" ){
			
			// nach Arbeitsgruppe suchen
			SuchController suche = new SuchController();
			suche.setSuchdomain("Arbeitsgruppe");
			suche.setOperation("auswahl");
			HauptController.hauptfenster.setContent( suche.getView() );
			
			//warte auf Auswahl
			while( suche.getAuswahl() == null ){
				suche.getAuswahl();
			}
			
			//ausgewählten Mitarbeiter holen
			Mitarbeiter gewaehlterAGLeiter = (Mitarbeiter) suche.getAuswahl();
			
			//an Maske übergeben & Maske anzeigen
			this.view.setAGLeiter( gewaehlterAGLeiter.getBenutzername() );
			HauptController.hauptfenster.setContent( this.getView() );
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Gibt die aktuelle View des Controllers zurück
	 * 
	 * @author Johannes
	 * @version 1.0
	 * @return (View) aktuelle View
	 */
	@Override
	public View getView() {
		return this.view;
	}
	
}
