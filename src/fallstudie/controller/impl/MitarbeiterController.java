package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Arbeitsgruppe;
import fallstudie.model.impl.Bereich;
import fallstudie.model.impl.Mitarbeiter;
import fallstudie.model.impl.Rolle;
import fallstudie.view.interfaces.View;

/**
 * MitarbeiterController, steuert alle Abläufe zu einem Mitarbeiter (außer Löschen eines Mitarbeiters)
 * @author Johannes
 * @version 0.1
 *
 */
public class MitarbeiterController implements Controller {

	private MitarbeiterBearbeitenView view;
	private MitarbeiterAnlegenView viewAnlegen;
	private String operation;
	
	private Collection<Rolle> rollen;
	private Collection<Bereich> bereiche;
	private Arbeitsgruppe arbeitsgruppe;
	private Mitarbeiter gewaehlterMitarbeiter;
	
	/**
	 * Konstruktor, ruft und speichert alle Rollen und Bereiche
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	public MitarbeiterController(){
		//alle Rollen und Bereiche holen und speichern
		this.rollen = Rolle.getAlleRollen();
		this.bereiche = Bereich.getAlleBereiche();
	}
	
	/**
	 * Bestimmt, welche View ausgegeben werden soll
	 * 
	 * @author Johannes
	 * @version 0.1
	 * @param operation
	 */
	public void setOperation(String operation){
		this.operation = operation;
		
		if( this.operation.equals("anlegen") ){
			this.viewAnlegen = new MitarbeiterAnlegenView();
			this.viewAnlegen.setBereich( this.bereiche.toArray() );
			this.viewAnlegen.setRolle( this.rollen.toArray() );
			this.viewAnlegen.setController( this );
		}
		
		if( this.operation.equals("bearbeiten") ){
			
			//Mitarbeiter suchen
			SuchController suche = new SuchController();
			suche.setSuchdomain("Mitarbeiter");
			suche.setOperation("suchen");
			HauptController.hauptfenster.setContent( suche.getView() );
			
			//warte auf Auswahl
			while( suche.getAuswahl() == null ){
				suche.getAuswahl();
			}
			
			//ausgewählten Mitarbeiter holen
			this.gewaehlterMitarbeiter = (Mitarbeiter) suche.getAuswahl();
			
			//Mitarbeiter bearbeiten
			this.view = new MitarbeiterBearbeitenView();
			this.view.setController( this );
			this.view.setVorname( this.gewaehlterMitarbeiter.getVorname() );
			this.view.setNachname( this.gewaehlterMitarbeiter.getNachname() );
			this.view.setBenutzername( this.gewaehlterMitarbeiter.getBenutzername() );
			this.view.setRolle( this.rollen.toArray(), this.gewaehlterMitarbeiter.getRolle().getRollenbezeichnung() );
			this.view.setArbeitsgruppe( this.gewaehlterMitarbeiter.getArbeitsgruppe() );
			this.view.setBereich( this.bereiche.toArray(), this.gewaehlterMitarbeiter.getBereich().getKurzbezeichnung() );
		}
	}
	
	/**
	 * ActionListener für Buttons "suchen" (Arbeitsgruppe), "anlegen" und "speichern"
	 * 
	 * @author Johannes
	 * @version 0.1
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		
		//Arbeitsgruppe suchen
		if( button.equals("suchen") ){
			SuchController sucheAG = new SuchController();
			sucheAG.setSuchdomain("Arbeitsgruppe");
			sucheAG.setOperation("auswahl");
			sucheAG.setSuchbegriff( this.getView().getArbeitsgruppe() );
			HauptController.hauptfenster.setContent( sucheAG.getView() );
			
			//warte auf Auswahl
			while( sucheAG.getAuswahl() == null ){
				sucheAG.getAuswahl();
			}
			
			//gewählte AG speichern
			this.arbeitsgruppe = (Arbeitsgruppe) sucheAG.getAuswahl();
			
			//AG-Kurzbezeichnung an Maske liefern
			this.getView().setArbeitsgruppe( this.arbeitsgruppe.getKurzbezeichnung() );
			
		}//Arbeitsgruppe suchen
		
		//Mitarbeiter anlegen
		if( button.equals("anlegen") ){
			
			//hole Nutzerdaten
			String benutzername = this.viewAnlegen.getBenutzername();
			String nachname = this.viewAnlegen.getNachname();
			String vorname = this.viewAnlegen.getVorname();
			String passwort = this.viewAnlegen.getPasswort();
			String rollenbez = this.viewAnlegen.getRolle();
			
			//finde passendes Rollen-Objekt
			Rolle rolle = this.findeRolleZuBezeichnung(rollenbez);
			try{
				if(rollenbez.equals("Zentralbereichsleiter") || rollenbez.equals("Bereichsleiter") ){
					//finde passendes Bereichs-Objekt
					Bereich bereich = this.findeBereichZuBezeichnung(this.viewAnlegen.getBereich());
					
					//Mitarbeiter mit Bereich anlegen
					Mitarbeiter neuerMitarbeiter = new Mitarbeiter(benutzername, passwort, vorname, nachname, rolle, bereich);
				}else{
					
					//Mitarbeiter mit Arbeitsgruppe anlegen
					Mitarbeiter neuerMitarbeiter = new Mitarbeiter(benutzername, passwort, vorname, nachname, rolle, this.arbeitsgruppe);
				}
			}catch(Exception ex){
				HauptController.hauptfenster.setInfoBox("Mitarbeiter konnte nicht gespeichert werden.");
			}
			
		}//anlegen
		
		//Mitarbeiter bearbeiten
		if( button.equals("speichern") ){
			
			String msg = "Benutzerdaten wurden erfolgreich geändert.";
			String errmsg = "";
			
			if( !this.gewaehlterMitarbeiter.setVorname( this.view.getVorname() ) ) errmsg+= "Vorname konnte nicht geändert werden. \n";
			if( !this.gewaehlterMitarbeiter.setNachname( this.view.getNachname() ) ) errmsg+= "Nachname konnte nicht geändert werden. \n";
			String rollenbezeichnung = this.view.getRolle();
			if( !this.gewaehlterMitarbeiter.setRolle( this.findeRolleZuBezeichnung( rollenbezeichnung ) ) ) errmsg+= "Rolle konnte nicht geändert werden. \n";
			//TODO: klären: passwort für einen Mitarbeiter ändern?
			
			if( rollenbezeichnung.equals("Zentralbereichsleiter") || rollenbezeichnung.equals("Bereichsleiter") ){
				if( !this.gewaehlterMitarbeiter.setBereich( this.findeBereichZuBezeichnung( this.view.getBereich() ) ) ) errmsg+= "Bereich konnte nicht geändert werden. \n";
			}else{
				if( !this.gewaehlterMitarbeiter.setArbeitsgruppe( this.arbeitsgruppe ) ) errmsg+= "Arbeitsgruppe konnte nicht geändert werden. \n";
			}
			
			//Rückmeldung an User ausgeben
			if( !errmsg.isEmpty() ){
				msg = errmsg;
			}
			HauptController.hauptfenster.setInfoBox(msg);
		}
	}
	
	/**
	 * Findet zu einer übergebenen Rollenbezeichnung das entsprechende Objekt
	 * 
	 * @author Johannes
	 * @version 0.1
	 * @param rollenbezeichnung
	 * @return
	 */
	private Rolle findeRolleZuBezeichnung(String rollenbezeichnung){
		//finde rollenobjekt
		Iterator<Rolle> i = this.rollen.iterator();
		while( i.hasNext() ){
			Rolle ix = i.next();
			if( ix.getRollenbezeichnung().equals( rollenbezeichnung ) ){
				return ix;
			}
		}
	}
	
	/**
	 * Findet zu einer übergebenen Bereichsbezeichnung das entsprechende Objekt
	 * 
	 * @author Johannes
	 * @version 0.1
	 * @param bereichsbezeichnung
	 * @return Bereich
	 */
	private Bereich findeBereichZuBezeichnung(String bereichsbezeichnung){
		//finde bereichsobjekt
		Iterator<Bereich> i = this.bereiche.iterator();
		while( i.hasNext() ){
			Bereich ix = i.next();
			if( ix.getKurzbezeichnung().equals( bereichsbezeichnung ) ){
				return ix;
			}
		}
	}
	
	/**
	 * Liefert aktive View des Controllers
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	@Override
	public View getView() {
		switch( this.operation ){
			case "anlegen": return this.viewAnlegen;
							break;
			case "bearbeiten": return this.view;
							break;
		}
	}
}
