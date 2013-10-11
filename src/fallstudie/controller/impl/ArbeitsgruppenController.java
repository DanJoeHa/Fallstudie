package fallstudie.controller.impl;

import java.util.Collection;

import fallstudie.model.impl.Bereich;

public class ArbeitsgruppenController extends HauptController {
	
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
			
			//TODO: nach Arbeitsgruppe suchen
			//--> Suchcontroller hier oder aus Main?!
			SuchController suche = new SuchController();
			suche.setSuchdomain("Arbeitsgruppe");
			suche.setOperation("auswahl");
			
			
		}
	}
	
}
