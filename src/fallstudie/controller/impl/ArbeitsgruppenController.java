package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Arbeitsgruppe;
import fallstudie.model.impl.Bereich;
import fallstudie.model.impl.Mitarbeiter;
import fallstudie.view.impl.ArbeitsgruppeBearbeitenAnlegenView;
import fallstudie.view.impl.BereichBearbeitenAnlegenView;
import fallstudie.view.impl.DatenAnzeigenAuswahlView;
import fallstudie.view.impl.SuchenView;
import fallstudie.view.interfaces.View;

public class ArbeitsgruppenController implements Controller {
	
	private ArbeitsgruppeBearbeitenAnlegenView view;
	private DatenAnzeigenAuswahlView viewDatenAnz;
	private SuchenView viewSuche;
	private String operation;
	private Collection<Bereich> bereiche;
	private SuchController suche;
	private String[] sBereiche;
	private Arbeitsgruppe gewaehlteAG;
	/**
	 * Zeigt  die View zur Arbeitsgruppenbearbeiten/-anlage abhängig von der Operation an
	 * 
	 * @author Johannes
	 * @version 0.1
	 */
	public ArbeitsgruppenController(){
		//alle Bereiche holen
		this.bereiche = Bereich.getAlleBereiche();	
		sBereiche = Funktionen.BereicheCollection2Array(bereiche);
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
		if( this.operation.equals( "anlegen" ) ){
			
			this.view = new ArbeitsgruppeBearbeitenAnlegenView();	
			this.view.setController( this );
			//alle Bereiche holen und an View geben	
			this.view.setBereich( sBereiche );
		}
		
		//wenn Bearbeiten einer Arbeitsgruppe
		if( this.operation.equals( "bearbeiten" ) ){
			
			// nach Arbeitsgruppe suchen
			suche = new SuchController();
			suche.setAufrufenderController(this);
			suche.setSuchdomain("Arbeitsgruppe");
			suche.setOperation("suchen");
			HauptController.hauptfenster.setUeberschrift("Arbeitsgruppe zur Bearbeitung auswählen");
			this.viewSuche = (SuchenView) suche.getView() ;
			HauptController.hauptfenster.setContent( viewSuche);
			
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Button bestimmen
		String button = e.getActionCommand();
			
		//Änderungen speichern
		if( button.equals("Speichern") ){
			
			//Bereichsobjekt zur Auswahl finden
			Iterator<Bereich> i = this.bereiche.iterator();
			Bereich oBereich = null;
			while( i.hasNext() ){
				oBereich = i.next();
				if(oBereich.getKurzbezeichnung().equals( this.view.getBereich() ) ) break;
			}
			
			Mitarbeiter oLeiter = new Mitarbeiter( this.view.getAGLeiter() );
			try {
				this.gewaehlteAG.setBereich(oBereich);
				this.gewaehlteAG.setBeschreibung(this.view.getBezeichnung() );
				this.gewaehlteAG.setKurzbezeichnung(this.view.getKurzbezeichnung());
				this.gewaehlteAG.setLeiter(oLeiter);
				
			} catch (Exception e1) {
				HauptController.hauptfenster.setInfoBox( e1.getMessage() );
			}
		}
		
		//Zurücksetzen button
		if( button.equals("Zurücksetzen") ){
			//TODO: was soll hier passieren?!
		}
		
		if( button.equals("Suchen") ){
			this.view.getAGLeiter();
			this.viewDatenAnz = new DatenAnzeigenAuswahlView();
			HauptController.hauptfenster.setContent(viewDatenAnz);
			SuchController suchcontroller = new SuchController();
			suchcontroller.setSuchdomain("Gruppenleiter");
			this.view.setController(suchcontroller);
		}
	}
	
	public void BearbeitenFortsetzen (){

		//ausgewählte Arbeitsgruppe holen
		this.gewaehlteAG = (Arbeitsgruppe) suche.getAuswahl();

		//an Maske übergeben & Maske anzeigen
		this.view = new ArbeitsgruppeBearbeitenAnlegenView();
		this.view.setController(this);
		this.view.setBereich( sBereiche, gewaehlteAG.getBereich().getKurzbezeichnung());
		this.view.setKurzbezeichnung( gewaehlteAG.getKurzbezeichnung() );
		this.view.setBezeichnung( gewaehlteAG.getBeschreibung() );
		String LeiterBenutzerName = "";
		if( gewaehlteAG.getLeiter() != null ){
			LeiterBenutzerName = gewaehlteAG.getLeiter().getBenutzername();
		}
		this.view.setAGLeiter( LeiterBenutzerName );
		HauptController.hauptfenster.setUeberschrift("Arbeitsgruppe bearbeiten");
		HauptController.hauptfenster.setContent( this.view );
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
		if(this.operation.equals( "bearbeiten" ))
		{
			return this.viewSuche;
		}
		else
		{
			return this.view;
		}
	}
}
