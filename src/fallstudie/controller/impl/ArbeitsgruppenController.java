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
	 * Zeit  die View zur Arbeitsgruppenbearbeiten/-anlage abhängig von der Operation an
	 * 
	 * @author Johannes
	 * @version 0.1
	 */
	public ArbeitsgruppenController(){
		
		//View laden
		this.view = new ArbeitsgruppeBearbeitenAnlegenView();	
		
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
			hauptfenster.setContent( suche.getView() );
			
			//warte auf Auswahl
			while( suche.getAuswahl() == null ){
				suche.getAuswahl();
			}
			
			//ausgewählten Mitarbeiter holen
			Mitarbeiter gewaehlterAGLeiter = (Mitarbeiter) suche.getAuswahl();
			
			//an Maske übergeben & Maske anzeigen
			this.view.setAGLeiter( gewaehlterAGLeiter.getBenutzername() );
			hauptfenster.setContent( this.getView() );
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
