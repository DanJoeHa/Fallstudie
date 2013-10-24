package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Arbeitsgruppe;
import fallstudie.model.impl.Bereich;
import fallstudie.model.impl.Mitarbeiter;
import fallstudie.model.impl.Rolle;
import fallstudie.view.impl.BestaetigenPopup;
import fallstudie.view.impl.HilfeTexte;
import fallstudie.view.impl.MitarbeiterAnlegenView;
import fallstudie.view.impl.MitarbeiterBearbeitenView;
import fallstudie.view.impl.SuchenView;
import fallstudie.view.interfaces.View;

/**
 * MitarbeiterController, steuert alle Abläufe zu einem Mitarbeiter (außer Löschen eines Mitarbeiters)
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
	private Arbeitsgruppe gewaehlteAG = null;
	private SuchController suche;
	private boolean suchag = false;
	public static BestaetigenPopup popup;
	
	
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
	 * Bestimmt, welche View ausgegeben werden soll. Erwartet einen der Strings "anlegen" oder "bearbeiten"
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
			this.suchag = false;
			this.viewAnlegen.reset();
		}
		
		//Abbrechen-Button
		if( button.equals("Abbrechen") ){
			HauptController.startMitarbeiterBearbeiten();
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
		
		//Speicherbutton
		if( button.equals("Speichern") ){
			speichernPopup();
		}
		
		//Speicherabfrage ausblenden
		if(button.equals("Nein")){
			popup.setVisible(false);
		}
		
		//Mitarbeiter anlegen
		if(this.operation.equals("anlegen"))
		{
			if(button.equals("Ja")){
				speichernMitarbeiter();
			}
			if(button.equals("Nein")){
				popup.setVisible(false);
			}
		}//anlegen
		
		
		//Mitarbeiter bearbeiten
		if(this.operation.equals("bearbeiten"))
		{
			if( button.equals("Ja") ){
				
				bearbeitenMitarbeiter();
			}
		}
	}

	private void bearbeitenMitarbeiter() {
		try{
			//Daten aus View holen
			String vorname = this.view.getVorname();
			String nachname = this.view.getNachname();
			String rollenbezeichnung = this.view.getRolle();
			String passwort = this.view.getPasswort();
			String arbeitsgruppe = this.view.getArbeitsgruppe();
			String bereich = this.view.getBereich();
			
			//Vorname ändern
			if( !this.gewaehlterMitarbeiter.setVorname( vorname ) ){
				throw new Exception("Vorname konnte nicht geändert werden.");
			}
			
			//Nachname ändern
			if( !this.gewaehlterMitarbeiter.setNachname( nachname ) ){
				throw new Exception("Nachname konnte nicht geändert werden.");
			}
			
			//Rolle finden
			Rolle rolle = this.findeRolleZuBezeichnung( rollenbezeichnung );
			if( rolle == null ){
				throw new Exception("Bitte eine gültige Rolle auswählen!");
			}
			
			//Rolle ändern
			if( !this.gewaehlterMitarbeiter.setRolle( rolle ) ){
				throw new Exception("Rolle konnte nicht geändert werden.");
			}
			
			//Passwort ändern (nur wenn neues angegeben wurde!)
			if(!passwort.isEmpty()){
				if( !this.gewaehlterMitarbeiter.setPasswort(passwort) ){
					throw new Exception("Passwort konnte nicht geändert werden.");
				}
			}
			
			//Bereich speichern
			if( rollenbezeichnung.equals("Bereichsleiter") ){
				
				//Bereich finden
				Bereich b = this.findeBereichZuBezeichnung( bereich );
				
				//Prüfen ob Bereich existiert
				if(b == null){
					throw new Exception("Bitte gültigen Bereich auswählen!");
				}else{
					if( !this.gewaehlterMitarbeiter.setBereich( b ) ){
						throw new Exception("Bereich konnte nicht geändert werden.");
					}
				}
				
				
			}

			//Arbeitsgruppe speichern
			if( rollenbezeichnung.equals("Sachbearbeiter") || rollenbezeichnung.equals("Gruppenleiter")){
				
				//Arbeitsgruppe finden falls keine über Suchen ausgewählt wurde
				if( this.gewaehlteAG == null ){
					
					//Prüfen ob eine Arbeitsgruppe angegeben wurde und ob diese existiert
					if( !arbeitsgruppe.isEmpty() ){
						//Arbeitsgruppe aus Model-Schicht holen
						int id = Arbeitsgruppe.getIDbyKurzbezeichnung(arbeitsgruppe);
						this.gewaehlteAG = new Arbeitsgruppe( id );
					}else{
						//keine Arbeitsgruppe angegeben
						throw new Exception("Mitarbeiter muss einer Arbeitsgruppe zugeordnet werden.");
					}							
				
				}
				
				//Speichere Arbeitsgruppe
				if( !this.gewaehlterMitarbeiter.setArbeitsgruppe( this.gewaehlteAG ) ){
					throw new Exception("Arbeitsgruppe konnte nicht geändert werden. Bitte stellen Sie sicher, dass eine gültige Kurzbezeichnung angegeben ist.");
				}
			}
			
			//Rückmeldung an User ausgeben
			HauptController.hauptfenster.setInfoBox("Benutzerdaten erfolgreich geändert");
			
		}catch(Exception ex){
			HauptController.hauptfenster.setInfoBox(ex.getMessage());
		}finally{
			popup.setVisible(false);
		}
	}

	private void speichernMitarbeiter() {
		//hole Nutzerdaten
		String benutzername = this.viewAnlegen.getBenutzername();
		String nachname = this.viewAnlegen.getNachname();
		String vorname = this.viewAnlegen.getVorname();
		String passwort = this.viewAnlegen.getPasswort();
		String rollenbez = this.viewAnlegen.getRolle();
		
		if (!(vorname.equals("")||nachname.equals("")||benutzername.equals("")||passwort.equals("")))
		{
			//finde passendes Rollen-Objekt
			Rolle rolle = this.findeRolleZuBezeichnung(rollenbez);
			try{
				
				//Bereichsleiter anlegen
				if( rollenbez.equals("Bereichsleiter") ){
				
					//finde passendes Bereichs-Objekt oder null
					Bereich bereich = this.findeBereichZuBezeichnung(this.viewAnlegen.getBereich());
					
					//Mitarbeiter mit Bereich anlegen
					if( bereich != null ){
						new Mitarbeiter(benutzername, passwort, vorname, nachname, rolle, bereich);
					}else{
						throw new Exception("Bitte einen gültigen Bereich auswählen!");
					}
				}
				
				//Sachbearbeiter oder Gruppenleiter anlegen
				if( rollenbez.equals("Sachbearbeiter") || rollenbez.equals("Gruppenleiter") ){
					
					//Mitarbeiter mit Arbeitsgruppe anlegen
					if( this.gewaehlteAG == null ){
						
						//wenn keine Arbeitsgruppe angegeben wurde
						if( this.viewAnlegen.getArbeitsgruppe().isEmpty() ){
							throw new Exception("Bitte eine gültige Arbeitsgruppe auswählen!");
						}else{
							//Arbeitsgruppe aus Model-Schicht holen, falls in View nur eingegeben und nicht gesucht
							int id = Arbeitsgruppe.getIDbyKurzbezeichnung(this.viewAnlegen.getArbeitsgruppe());
							this.gewaehlteAG = new Arbeitsgruppe( id );
						}
						
					}
					//speichern
					new Mitarbeiter(benutzername, passwort, vorname, nachname, rolle, this.gewaehlteAG);
					
				}
				
				//Fachbereichsorganisation oder Zentralbereichsleiter anlegen
				if( rollenbez.equals("Fachbereichsorganisation") || rollenbez.equals("Zentralbereichsleiter") ){
					new Mitarbeiter(benutzername, passwort, vorname, nachname, rolle);
				}						
				
			}catch(Exception ex){
				HauptController.hauptfenster.setInfoBox(ex.getMessage());
				this.viewAnlegen.reset();
			}
		}
		else
		{
			HauptController.hauptfenster.setInfoBox("Bitte die Pflichtfelder Vorname, Nachname, Benutzername, Passwort ausfüllen.");
		}
		popup.setVisible(false);
	}

	private void speichernPopup() {
		popup = new BestaetigenPopup();
		popup.setController(this);
		popup.setTitle("Speichern");
		popup.setAusgabe(HilfeTexte.SpeichernPopup);
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

	/**
	 * Methode wird vom SuchController aufgerufen, wenn nach Mitarbeiter/Arbeitsgruppe gesucht wurde.
	 */
	@Override
	public void fortsetzen() {
		
		if(this.operation.equals("anlegen")){ //}catch(Exception ex){
			
			//ausgewählte Arbeitsgruppe holen
			this.gewaehlteAG = (Arbeitsgruppe) this.suche.getAuswahl();
			this.viewAnlegen.setRolle( Funktionen.RollenCollection2Array(this.rollen) );
			this.viewAnlegen.setBereich(Funktionen.BereicheCollection2Array(this.bereiche));
			this.viewAnlegen.setArbeitsgruppe(gewaehlteAG.getKurzbezeichnung());
			
			HauptController.hauptfenster.setUeberschrift("Mitarbeiter anlegen");
			HauptController.hauptfenster.setContent( this.viewAnlegen );
			this.viewAnlegen.repaint();
		}
		
		if(this.operation.equals("bearbeiten")){ //try{
			
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
				
				String rolle = this.gewaehlterMitarbeiter.getRolle().getRollenbezeichnung();
				if( rolle.equals("Bereichsleiter") ){
					this.view.setBereich(Funktionen.BereicheCollection2Array(this.bereiche), this.gewaehlterMitarbeiter.getBereich().getKurzbezeichnung() );
				}
				
				if( rolle.equals("Gruppenleiter")|| rolle.equals("Sachbearbeiter") ){
					if( this.gewaehlterMitarbeiter.getArbeitsgruppe() != null ){
						if( this.gewaehlterMitarbeiter.getArbeitsgruppe().getBereich() != null ){
							this.view.setBereich(Funktionen.BereicheCollection2Array(this.bereiche), this.gewaehlterMitarbeiter.getArbeitsgruppe().getBereich().getKurzbezeichnung() );
						}
					}else{
						this.view.setBereich(Funktionen.BereicheCollection2Array(this.bereiche));
					}
					
				}
				
				if( rolle.equals("Fachbereichsorganisation") || rolle.equals("Zentralbereichsleiter") ){
					this.view.setBereich(Funktionen.BereicheCollection2Array(this.bereiche));
				}
				
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
				
		}	
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(this.operation == "anlegen"){
				if (this.viewAnlegen.hatFocus() == "buttonSpeichern")
				{
					speichernPopup();
				}
				else if(popup.isFocused() == true && popup.hatFocus()== "popupJa"){
					speichernMitarbeiter();
				}
				else if(popup.isFocused() == true && popup.hatFocus() == "popupNein"){
					popup.setVisible(false);
					HauptController.hauptfenster.setInfoBox("");
				}
			}
			if(this.operation == "bearbeiten"){
				if (this.view.hatFocus() == "buttonSpeichern")
				{
					speichernPopup();
				}
				else if(popup.isFocused() == true && popup.hatFocus()== "popupJa"){
					bearbeitenMitarbeiter();
				}
				else if(popup.isFocused() == true && popup.hatFocus() == "popupNein"){
					popup.setVisible(false);
					HauptController.hauptfenster.setInfoBox("");
				}
			}
		}
	}
		
	@Override
	public void keyTyped(KeyEvent e) {}
}
