package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;
import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Arbeitsgruppe;
import fallstudie.model.impl.Bereich;
import fallstudie.model.impl.Mitarbeiter;
import fallstudie.model.impl.Rolle;
import fallstudie.view.impl.HilfeTexte;
import fallstudie.view.impl.MitarbeiterAnlegenView;
import fallstudie.view.impl.MitarbeiterBearbeitenView;
import fallstudie.view.impl.SuchenView;
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
	private SuchenView viewSuche;
	private String operation;
	private Collection<Rolle> rollen;
	private Collection<Bereich> bereiche;
	private Mitarbeiter gewaehlterMitarbeiter;
	private Arbeitsgruppe gewaehlteAG;
	private SuchController suche;
	private boolean suchag = false;
	
	
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
			this.viewAnlegen.setBereich(Funktionen.BereicheCollection2Array(this.bereiche));
			this.viewAnlegen.setRolle( Funktionen.RollenCollection2Array(this.rollen));
			this.viewAnlegen.setController( this );
		}
		
		if( this.operation.equals("bearbeiten") ){
			
			//Mitarbeiter suchen
			suche = new SuchController();
			suche.setAufrufenderController(this);
			suche.setSuchdomain("Mitarbeiter");
			suche.setOperation("suchen");
			HauptController.hauptfenster.setUeberschrift("Mitarbeiter zur Bearbeitung auswählen");
			this.viewSuche = (SuchenView) suche.getView() ;
			HauptController.hauptfenster.setContent(viewSuche);
			
			
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
		
		//Zurücksetzen-Button
		if( button.equals("Zurücksetzen") ){
			this.viewAnlegen.reset();
		}
		
		//Abbrechen-Button
		if( button.equals("Abbrechen") ){
			HauptController.hauptfenster.zurueck();
		}
		
		//Arbeitsgruppe suchen
		if( button.equals("Suchen") ){
			suche = new SuchController();
			suche.setAufrufenderController( this );
			suche.setSuchdomain("Arbeitsgruppe");
			
			switch( this.operation )
			{
				case "anlegen": suche.setSuchbegriff(this.viewAnlegen.getArbeitsgruppe());
					break;
				case "bearbeiten": suche.setSuchbegriff(this.view.getArbeitsgruppe());
					break;
			}
			//Hilfe für Tabelle bei Mitarbeiter anlegen - AG suchen
			HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_Mitarbeiteranlegen_Mitarbeiterbearbeiten_Arbeitsgruppebearbeiten_AG);
			
			suche.setOperation("auswahl");
			HauptController.hauptfenster.setContent(suche.getView() );
			
		}//Arbeitsgruppe suchen			
		
		//Mitarbeiter anlegen
		if(this.operation.equals("anlegen"))
		{
			if( button.equals("Speichern") ){
				
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
						new Mitarbeiter(benutzername, passwort, vorname, nachname, rolle, bereich);
					}else{
						
						//Mitarbeiter mit Arbeitsgruppe anlegen
						new Mitarbeiter(benutzername, passwort, vorname, nachname, rolle, this.gewaehlteAG);
					}
				}catch(Exception ex){
					HauptController.hauptfenster.setInfoBox("Mitarbeiter konnte nicht gespeichert werden.");
				}
			}	
		}//anlegen
		
		
		//Mitarbeiter bearbeiten
		if(this.operation.equals("bearbeiten"))
		{
			if( button.equals("Speichern") ){
				
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
					if( !this.gewaehlterMitarbeiter.setArbeitsgruppe( this.gewaehlteAG ) ) errmsg+= "Arbeitsgruppe konnte nicht geändert werden. \n";
				}
				
				//Rückmeldung an User ausgeben
				if( !errmsg.isEmpty() ){
					msg = errmsg;
				}
				HauptController.hauptfenster.setInfoBox(msg);
			}
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
	private Rolle findeRolleZuBezeichnung (String rollenbezeichnung){
		//finde rollenobjekt
		Iterator<Rolle> i = this.rollen.iterator();
		while( i.hasNext() ){
			Rolle ix = i.next();
			if( ix.getRollenbezeichnung().equals( rollenbezeichnung ) ){
				return ix;
			}
		}
		return null;
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
		return null;
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
			case "anlegen": return (View) this.viewAnlegen;
			case "bearbeiten": return (View) this.viewSuche;
		}
		return null;
	}

	@Override
	public void fortsetzen() {
		
		try{ //if(this.operation.equals("bearbeiten"))
			
			//Mitarbeiter zur Bearbeitung ausgewählt
			if(!suchag){
				
				//Mitarbeiter bearbeiten View
				this.view = new MitarbeiterBearbeitenView();
				this.view.setController( this );
				
				//ausgewählten Mitarbeiter holen
				this.gewaehlterMitarbeiter = (Mitarbeiter) suche.getAuswahl();
				
				this.view.setVorname( this.gewaehlterMitarbeiter.getVorname() );
				this.view.setNachname( this.gewaehlterMitarbeiter.getNachname() );
				this.view.setBenutzername( this.gewaehlterMitarbeiter.getBenutzername() );
				this.view.setRolle(Funktionen.RollenCollection2Array(this.rollen), this.gewaehlterMitarbeiter.getRolle().getRollenbezeichnung() );
				try{
					this.view.setArbeitsgruppe( this.gewaehlterMitarbeiter.getArbeitsgruppe().getKurzbezeichnung());
				}
				catch (Exception ex)
				{
					this.view.setArbeitsgruppe("");
				}
				this.view.setBereich(Funktionen.BereicheCollection2Array(this.bereiche), this.gewaehlterMitarbeiter.getBereich().getKurzbezeichnung() );
				HauptController.hauptfenster.setUeberschrift("Mitarbeiter bearbeiten");
				HauptController.hauptfenster.setContent( this.view );
				this.view.repaint();
				this.suchag = true;
			
			}else{
				//Arbeitsgruppe für zu bearbeitenden Mitarbeiter ausgewählt
				this.gewaehlteAG = (Arbeitsgruppe) this.suche.getAuswahl();
				this.view.setArbeitsgruppe(gewaehlteAG.getKurzbezeichnung());
				
				HauptController.hauptfenster.setUeberschrift("Mitarbeiter bearbeiten");
				HauptController.hauptfenster.setContent( this.view );
				this.view.repaint();
				
			}
				
		}catch(Exception ex){ //if(this.operation.equals("anlegen"))
			
			//ausgewählte Arbeitsgruppe holen
			this.gewaehlteAG = (Arbeitsgruppe) this.suche.getAuswahl();
			this.viewAnlegen.setRolle( Funktionen.RollenCollection2Array(this.rollen) );
			this.viewAnlegen.setBereich(Funktionen.BereicheCollection2Array(this.bereiche));
			this.viewAnlegen.setArbeitsgruppe(gewaehlteAG.getKurzbezeichnung());
			
			HauptController.hauptfenster.setUeberschrift("Mitarbeiter anlegen");
			HauptController.hauptfenster.setContent( this.viewAnlegen );
			this.view.repaint();
		}
		
		
	}
}
