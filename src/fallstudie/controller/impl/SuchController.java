package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Iterator;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Arbeitsgruppe;
import fallstudie.model.impl.Mitarbeiter;
import fallstudie.view.impl.BestaetigenPopup;
import fallstudie.view.impl.HilfeTexte;
import fallstudie.view.impl.SuchenView;
import fallstudie.view.impl.TabelleView;
import fallstudie.view.interfaces.View;

/**
 * Der Such-Controller ist Veranwortlich für das Finden von Arbeitsgruppen und Mitarbeitern. Er wird von anderen Controllern aufgerufen und kommuniziert mit diesen mittels der Schnittstellen-Methode "fortsetzen()." 
 * 
 * @author Johannes
 * @version 1.0
 */
public class SuchController implements Controller,MouseListener {
	
	/**
	 * Suchmaske
	 */
	private SuchenView view;
	/**
	 * Ergebnismaske zur Auswahl
	 */
	private TabelleView viewErg;
	/**
	 * Suchdomain ("Arbeitsgruppe", "Mitarbeiter")
	 */
	private String suchdomain;
	/**
	 * Collection an gefundenen Mitarbeitern
	 */
	private Collection<Mitarbeiter> suchergebnisseMa;
	/**
	 * Collection an gefundenen Arbeitsgruppen
	 */
	private Collection<Arbeitsgruppe> suchergebnisseAg;
	/**
	 * Operation zur Ausführung ("suchen", "loeschen", "auswahl")
	 */
	private String operation;
	/**
	 * gewähltes Objekt
	 */
	private Object auswahl = null;
	/**
	 * Suchbegriff (bei Aufruf aus anderem Controller)
	 */
	private String suchbegriff = "";
	/**
	 * Popup zur Lösch-Bestätigung
	 */
	public static BestaetigenPopup popup;
	/**
	 * Objekt des aufrufenden Controllers
	 */
	private Controller aufrufenderController;

	/**
	 * Standard-Konstruktor
	 */
	public SuchController(){
		
	}
	
	/**
	 * Suchdomain setzen ("Arbeitsgruppe" oder "Mitarbeiter")
	 *
	 * @param (String)suchdomain
	 */
	public void setSuchdomain (String suchdomain){
		this.suchdomain = suchdomain;
	}
	
	/**
	 * Liefert das gewählte Objekt zurück
	 * @return (Object) Auswahl
	 */
	public Object getAuswahl(){
		return this.auswahl;
	}

	/**
	 * Setzt die Operation ("suchen", "loeschen", "auswahl") und zeigt anhand dieser die betreffende View an.
	 * 
	 * @param (String) operation
	 */
	public void setOperation(String operation){
		this.operation = operation;
		
		if( this.operation.equals("suchen") || this.operation.equals("loeschen") ){
			//Suchen View ausgeben
			this.view = new SuchenView();
			this.view.setController( this );
		}
		
		if( this.operation.equals("auswahl") ){
			//Ergebnistabelle anzeigen
			ActionEvent e = new ActionEvent(this, 1, "Suchen");
			this.actionPerformed(e);
		}
	}
	
	/**
	 * Ermöglicht das Setzen eines Suchbegriffs in einem aufrufenden Controller.
	 * 
	 * @param (String) Suchbegriff
	 */
	public void setSuchbegriff(String Suchbegriff){
		this.suchbegriff = Suchbegriff;
	}
	
	/**
	 * ActionListener auf Buttons "Abbrechen", "Löschen", "Suchen" und die Popup-Optionen "Ja" und "Nein"
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		
		//Abbrechen-Button
		if( button.equals("Abbrechen") ) HauptController.hauptfenster.zurueck();
		
		//Wenn in Ergebnistabelle ein Eintrag zum löschen gewählt wurde
		if( button == "Löschen" ) auswahlLoeschen();
		
		if( this.suchdomain == "Mitarbeiter"|| this.suchdomain == "Sachbearbeiter" || this.suchdomain == "Gruppenleiter" || this.suchdomain == "Bereichsleiter" ){
			if( button.equals( "Suchen" )){
				suchenActionMitarbeiter(e.getID());
			}
			
			//Wenn in Ergebnistabelle ein Eintrag gewählt wurde
			if( button.equals( "auswählen" ) ){
				auswahlAction_Mitarbeiter();
			}
			
			if(button.equals("Ja")){
				mitarbeiterLoeschen(e.getID());
			}
			if(button.equals("Nein")){
				popup.setVisible(false);
			}
		}
		
		if( this.suchdomain.equals( "Arbeitsgruppe" )){ 
			if( button.equals( "Suchen") ){
				suchenActionArbeitsgruppe(e.getID());
			}
		
			//Wenn in Ergebnistabelle ein Eintrag gewählt wurde
			if( button.equals( "auswählen" ) ){
				auswahlAction_Arbeitsgruppe();
			}
			
			if(button.equals("Ja")){
				arbeitsgruppeLoeschen(e.getID());
			}
			if(button.equals("Nein")){
				popup.setVisible(false);
			}
		}
	
}

	/**
	 * Löscht den in der Tabelle gewählten Mitarbeiter
	 * 
	 * @param (int) id des Suchen-Button
	 */
	private void mitarbeiterLoeschen(int id) {
		//durch Suchergebnisse iterien und zur auswahl passendes Object finden, 
		Iterator<Mitarbeiter> i = this.suchergebnisseMa.iterator();
		String auswahl =  this.viewErg.getAuswahl();
		if(auswahl.equals(""))
		{
			popup.setVisible(false);
			HauptController.hauptfenster.setInfoBox("Bitte den zu löschenden Mitarbeiter mit Klick in die Tabelle auswählen.");
		}
		else
		{
			while( i.hasNext() ){
				Mitarbeiter MA = (Mitarbeiter) i.next();
				String benutzername = MA.getBenutzername();
				if( benutzername.equals(auswahl ) ){
					try{
						MA.loeschen();
					}catch (Exception ex)
					{
						HauptController.hauptfenster.setInfoBox(ex.getMessage());
					}
					break;
				}
			}	
			popup.setVisible(false);
			popup=null;
			suchenActionMitarbeiter(id);
		}
	}

	/**
	 * Löscht die in der Tabelle gewählte Arbeitsgruppe
	 * 
	 * @param (int) id des Suchen-Button
	 */
	private void arbeitsgruppeLoeschen(int id) {
		//durch Suchergebnisse iterien und zur auswahl passendes Object finden, 
		Iterator<Arbeitsgruppe> i = this.suchergebnisseAg.iterator();
		
		String auswahl = this.viewErg.getAuswahl();
		if(auswahl.equals(""))
		{
			popup.setVisible(false);
			HauptController.hauptfenster.setInfoBox("Bitte die zu löschende Arbeitsgruppe mit Klick in die Tabelle auswählen.");
		}
		else
		{
			while( i.hasNext() ){
				Arbeitsgruppe AG = (Arbeitsgruppe) i.next();
				String kurzbez = AG.getKurzbezeichnung();
				if( kurzbez.equals( auswahl) ){
					try{
						AG.loeschen();
					}catch(Exception ex){
						HauptController.hauptfenster.setInfoBox(ex.getMessage());
					}
					break;
				}
			}
			popup.setVisible(false);
			popup=null;
			suchenActionArbeitsgruppe(id);
		}
	}

	/**
	 * Zeigt die Ergebnistabelle zur Auswahl/Löschen von Arbeitsgruppen an.
	 * 
	 * @param (int) id des Suchen-Button
	 */
	private void suchenActionArbeitsgruppe(int id) {
		try{
				//initiere Ergebnistabelle
				this.viewErg = new TabelleView();
				this.viewErg.setController( this );
				
				//Hilfe für Tabelle bei Mitarbeiter anlegen - AG suchen
				HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_Mitarbeiteranlegen_Mitarbeiterbearbeiten_Arbeitsgruppebearbeiten_AG);

				this.viewErg.setButtonName("auswählen");
				
				//hole passende Suchergebnisse
				String suche = "";
				if(id == 1 ){
					if( !this.suchbegriff.isEmpty() ) suche = this.suchbegriff;
				}
				else
				{
					suche = this.view.getSuchbegriff();	
				}
				
				this.suchergebnisseAg = Arbeitsgruppe.suche( suche );
								
				//festgelegter String Array
				String[] AGColumn = new String[]{ "Kurzbeschreibung", "Beschreibung", "Leiter", "Bereich"};
				
				//Content auf Tabellen-Sicht wechseln
				this.viewErg.setTabelle( AGColumn, Funktionen.ArbeitsgruppeCollection2ArraySuche(this.suchergebnisseAg ) );
				if(operation.equals("loeschen"))
				{
					this.viewErg.setButtonName("Löschen");
				}
				HauptController.hauptfenster.setContent( this.viewErg );
				
		}catch(Exception ex){
			HauptController.hauptfenster.setInfoBox("Keine Datensätze gefunden.");
		}
	}

	/**
	 * Zeigt die Ergebnistabelle zur Auswahl/Löschen von Mitarbeitern an
	 * 
	 * @param (int) id des Suchen-Button
	 */
	private void suchenActionMitarbeiter(int id) {
		try{
				
				//initiere Ergebnistabelle
				this.viewErg = new TabelleView();
				this.viewErg.setController( this );
				
				//Hilfe für Tabelle bei Mitarbeiter anlegen - AG suchen
				HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_Mitarbeiterbearbearbeiten_Mitarbeiterloeschen_Arbeitsgruppeanlegen_Arbeitsgruppebearbeiten_Gruppenleiter_Bereichanlegen_Bereichsleiter_Bereichbearbeiten_Bereichsleiter);

				this.viewErg.setButtonName("auswählen");
				
				String suche = "";
				if(id == 1 ){
					if( !this.suchbegriff.isEmpty() ) suche = this.suchbegriff;
				}
				else
				{
					suche = this.view.getSuchbegriff();	
				}
				
				//hole passende Suchergebnisse
				this.suchergebnisseMa = Mitarbeiter.suche( suche, this.suchdomain );
				
				//festgelegter String Array
				String[] MAColumn = new String[]{ "Benutzername", "Vorname", "Nachname", "Arbeitsgruppe", "Bereich", "Rolle"};
				
				//Content auf Tabellen-Sicht wechseln
				this.viewErg.setTabelle( MAColumn, Funktionen.MitarbeiterCollection2ArraySuche(this.suchergebnisseMa ));
				if(operation.equals("loeschen"))
				{
					this.viewErg.setButtonName("Löschen");
				}
				HauptController.hauptfenster.setContent( this.viewErg );
		}catch(Exception ex){
			HauptController.hauptfenster.setInfoBox("Keine Datensätze gefunden.");
		}
	}

	/**
	 * Bestätigungspopup bei Löschen 
	 */
	private void auswahlLoeschen() {
		popup = new BestaetigenPopup();
		popup.setController(this);
		popup.setTitle("Löschen");
		popup.setAusgabe(HilfeTexte.LoeschenPopup);
	}

	/**
	 * Speichert den gewählten Mitarbeiter zur Rückgabe an aufrufenden Controller.
	 */
	private void auswahlAction_Mitarbeiter() {
		//durch suchergebnisse iterieren und zur auswahl passendes ERgebnis finden und in auswahl speichern 
		Iterator<Mitarbeiter> i = this.suchergebnisseMa.iterator();
		String name = this.viewErg.getAuswahl();
		if(name.equals(""))
		{
			HauptController.hauptfenster.setInfoBox("Bitte einen Mitarbeiter mit Klick in die Tabelle auswählen.");
		}
		else
		{
			while( i.hasNext() ){
				Mitarbeiter MA = (Mitarbeiter) i.next();
				String benutzername = MA.getBenutzername();
				if( benutzername.equals( name ) ){
					this.auswahl = MA;
					this.aufrufenderController.fortsetzen();
					break;
				}
			}
		}
	}

	/**
	 * Speichert die gewählte Arbeitsgruppe zur Rückgabe an aufrufenden Controller.
	 */
	private void auswahlAction_Arbeitsgruppe() {
		//durch suchergebnisse iterieren und zur auswahl passendes Ergebnis finden und in auswahl speichern 			
		Iterator<Arbeitsgruppe> i = this.suchergebnisseAg.iterator();
		String AGname = this.viewErg.getAuswahl();
		if(AGname.equals(""))
		{
			HauptController.hauptfenster.setInfoBox("Bitte eine Arbeitsgruppe mit Klick in die Tabelle auswählen.");
		}
		else
		{
			while( i.hasNext() ){
				Arbeitsgruppe AG = (Arbeitsgruppe) i.next();
				String kurzbez = AG.getKurzbezeichnung();
				if( kurzbez.equals( AGname ) ){
					this.auswahl = AG;
					this.aufrufenderController.fortsetzen();
					break;
				}
			}
		}
	}	

	/**
	 * Aufrufenden Controller speichern um Programmfluss wieder zurückgeben zu können.
	 * @param (Controller) aufrufendes Controller-Objekt
	 */
	public void setAufrufenderController(Controller c)
	{
		this.aufrufenderController = c;
	}

	/**
	 * Liefert die aktive View
	 */
	@Override
	public View getView() {
		if( this.operation.equals("suchen") || this.operation.equals("loeschen") ){
			//Suchen View liefern
			return this.view;
		}else{
			//Ergebnistabelle liefern
			return this.viewErg;
		}
	}

	/**
	 * keine Aktion
	 */
	@Override
	public void fortsetzen() {}

	/**
	 * Löscht bzw. wählt bei Doppelklick in der Tabelle das betreffende Element.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2){
			if (this.operation=="loeschen"){
				auswahlLoeschen();
			}else if(this.suchdomain.equals("Arbeitsgruppe") && (this.operation.equals("auswahl")||this.operation.equals("suchen"))){
				auswahlAction_Arbeitsgruppe();
			}else{
				auswahlAction_Mitarbeiter();
			}
		} 
	}

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
	 * Zeigt Popup zur Bestätigung abhängig von Suchdomain und Operation an.
	 */
	@Override
	public void keyReleased(KeyEvent e) {

		if( this.suchdomain == "Mitarbeiter"|| this.suchdomain == "Sachbearbeiter" || this.suchdomain == "Gruppenleiter" || this.suchdomain == "Bereichsleiter" ){
			if(e.getKeyCode()==KeyEvent.VK_ENTER && popup==null){
				suchenActionMitarbeiter(e.getID());
			}
			else if(e.getKeyCode()==KeyEvent.VK_ENTER && popup.isFocused() == true && popup.hatFocus()== "popupJa"){
				mitarbeiterLoeschen(e.getID());
			}
			else if(e.getKeyCode()==KeyEvent.VK_ENTER && popup.isFocused() == true && popup.hatFocus() == "popupNein"){
				popup.setVisible(false);
				HauptController.hauptfenster.setInfoBox("");
			}
		}
		if( this.suchdomain.equals( "Arbeitsgruppe" )){ 
			if(e.getKeyCode()==KeyEvent.VK_ENTER && popup==null){
				suchenActionArbeitsgruppe(e.getID());
				
			}else if(e.getKeyCode()==KeyEvent.VK_ENTER && popup.isFocused() == true && popup.hatFocus()== "popupJa"){
				arbeitsgruppeLoeschen(e.getID());
			}
			else if(e.getKeyCode()==KeyEvent.VK_ENTER && popup.isFocused() == true && popup.hatFocus() == "popupNein"){
				popup.setVisible(false);
				HauptController.hauptfenster.setInfoBox("");
			}			
		}
		
	}

	/**
	 * keine Aktion
	 */
	@Override
	public void keyTyped(KeyEvent e) {}
}
