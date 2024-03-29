package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Bereich;
import fallstudie.model.impl.Mitarbeiter;
import fallstudie.view.impl.BereichBearbeitenAnlegenView;
import fallstudie.view.impl.BereichLoeschenView;
import fallstudie.view.impl.BestaetigenPopup;
import fallstudie.view.impl.HilfeTexte;
import fallstudie.view.interfaces.View;

/**
 * Der Bereich-Controller ist für anlegen, bearbeiten und löschen von Bereichen verantwortlich.
 *
 */
public class BereichController implements Controller {
	
	/**
	 * Operation ("anlegen", "bearbeiten", "loeschen")
	 */
	private String operation;
	/**
	 * View für das Bearbeiten und Anlegen von Bereichen
	 */
	private BereichBearbeitenAnlegenView view;
	/**
	 * View für das Löschen von Bereichen.
	 */
	private BereichLoeschenView viewLoesch;
	/**
	 * Such-Controller
	 */
	private SuchController suche;
	/**
	 * Bereichsleiter
	 */
	private Mitarbeiter gewaehlterMA, oLeiter;
	/**
	 * zu bearbeitender Bereich
	 */
	private Bereich gewaehlterBereich;
	/**
	 * Popup zur Bestätigung
	 */
	public static BestaetigenPopup popup;
	/**
	 * Collection an Bereichen für Bearbeiten/Löschen
	 */
	private Collection<Bereich> bereich;
	
	/**
	 * Bereiche in Controller laden
	 * 
	 */
	public BereichController(){
		this.bereich = Bereich.getAlleBereiche();
	}
	
	/**
	 * Operation (anlegen, loeschen, bearbeiten) bestimmen
	 * 
	 * 
	 * @param operation ("anlegen" OR "loeschen" OR "bearbeiten")
	 */
	public void setOperation(String operation){
		//Operation speichern
		this.operation = operation;
		
		//neuen Bereich anlegen
		if( this.operation.equals("anlegen")){
			this.view = new BereichBearbeitenAnlegenView();
			this.view.setController( this );
			this.view.setButtonAbbrechenName("Zurücksetzen");
		}
		
		//Bereich löschen
		if(this.operation.equals("loeschen")){
			this.viewLoesch = new BereichLoeschenView();
			this.viewLoesch.setController( this );
			this.viewLoesch.setBereiche(Funktionen.BereicheCollection2Array( this.bereich ));
		}
		
		//Bereich bearbeiten
		if(this.operation.equals("bearbeiten")){
			this.viewLoesch = new BereichLoeschenView();
			this.viewLoesch.setController( this );
			this.viewLoesch.setBereiche(Funktionen.BereicheCollection2Array(Bereich.getAlleBereiche()));
			this.viewLoesch.setButtonName("Bearbeiten");
			this.viewLoesch.setHinweis("Bitte zu bearbeitenden Bereich auswählen");
		}
	}
	
	/**
	 * Bestimmen welcher Button gedrückt wurde und entsprechende Funktion ausführen
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		
		//Abbrechen
		if( button.equals("Abbrechen") ){
			HauptController.hauptfenster.zurueck();
		}
		
		//Zurücksetzen
		if( button.equals("Zurücksetzen") ){
			this.view.reset();
		}
		
		//Bereich löschen bestätigten
		if(button.equals("Löschen") ){
			bereichLoeschenPopup();
		}
		
		//Bereich anlegen/bearbeiten speichern
		if(button.equals("Speichern")){	
			anlegenBearbeitenPopup();			
		}
		

			
		//Bereich löschen durchführen
		if( this.operation.equals("loeschen") && button.equals("Ja") ) loescheBereich();
			
		//Abbrechen Lösch-Aktion
		if(button.equals("Nein") || button.equals("Nicht ersetzen")) popup.setVisible(false);
		
		//Bereich zur Bearbeitung ausgewählt
		if(button.equals("Bearbeiten") ) bearbeiteBereich();

		//Bereichsleiter suchen
		if(button.equals("Suchen")){
			
			this.suche = new SuchController();
			this.suche.setAufrufenderController(this);
			this.suche.setSuchdomain("Bereichsleiter");
			this.suche.setSuchbegriff(this.view.getLeiter());
			
			//Hilfe für Tabelle bei Mitarbeiter anlegen - AG suchen
			HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_Mitarbeiterbearbearbeiten_Mitarbeiterloeschen_Arbeitsgruppeanlegen_Arbeitsgruppebearbeiten_Gruppenleiter_Bereichanlegen_Bereichsleiter_Bereichbearbeiten_Bereichsleiter);

			this.suche.setOperation("auswahl");
			
			HauptController.hauptfenster.setUeberschrift("Bereichsleiter auswählen");
			HauptController.hauptfenster.setContent(this.suche.getView());
		}
		
		//Bereich anlegen/bearbeiten durchführen
		if(button.equals("Ja") || button.equals("Ersetzen")){	
			
			//Bereich anlegen
			if(this.operation.equals("anlegen")) anlegenBereich();
			
			//Bereich bearbeiten
			if(this.operation.equals("bearbeiten")) speicherAenderungen();
			
		}
		
	}
	
	/**
	 * Änderungen am zu bearbeitenden Bereich speichern.
	 */
	private void speicherAenderungen() {
		try
		{
			this.moveLeiter();
			this.gewaehlterBereich.setBeschreibung(this.view.getBezeichnung());
			this.gewaehlterBereich.setKurzbezeichnung(this.view.getKurzbezeichnung());
			this.gewaehlterBereich.setLeiter(oLeiter);
		}
		catch (Exception ex)
		{
			HauptController.hauptfenster.setInfoBox(ex.getMessage());
			if(oLeiter == null){
				HauptController.hauptfenster.setInfoBox("Mitarbeiter nicht vorhanden. Bereich wurde ohne Leiter angelegt.");
			}
		}finally{
			popup.setVisible(false);
			popup = null;
		}
	}

	/**
	 * Bereich zur Bearbeitung anzeigen
	 */
	private void bearbeiteBereich() {
		String tempBereich = this.viewLoesch.getBereich();
		this.view = new BereichBearbeitenAnlegenView();
		HauptController.hauptfenster.setContent(view);
		this.view.setController( this );
		this.view.setKurzbezeichnung(tempBereich);
		this.view.setButtonAbbrechenName("Abbrechen");
		gewaehlterBereich = Bereich.getBereichByName(tempBereich);
		this.view.setBezeichnung(gewaehlterBereich.getBeschreibung());
		
		//Hilfefenster
		HauptController.hilfefenster.setTitle("Bereich bearbeiten");
		HauptController.hilfefenster.setHinweis(HilfeTexte.BereichBearbeitenAnlegenView);
		
		try
		{
			this.view.setLeiter(gewaehlterBereich.getLeiter().getBenutzername());
		}
		catch(Exception ex)
		{
		}
	}

	/**
	 * neuen Bereich speichern
	 */
	private void anlegenBereich() {
		try{
			Bereich neuerBereich = new Bereich(this.view.getKurzbezeichnung(), this.view.getBezeichnung(), oLeiter);
			this.gewaehlterBereich = neuerBereich;
			this.moveLeiter();
			this.view.reset();
		}
		catch (Exception ex)
		{
			HauptController.hauptfenster.setInfoBox(ex.getMessage());
			if(oLeiter == null){
				HauptController.hauptfenster.setInfoBox("Mitarbeiter nicht vorhanden. Bereich wurde ohne Leiter angelegt.");
				this.view.reset();
			}
		}finally{
			popup.setVisible(false);
		}
	}

	/**
	 * Popup zur Bestätigung beim Speichern
	 */
	private void anlegenBearbeitenPopup() {
		popup = new BestaetigenPopup();
		popup.setController(this);
		
		//Leiter holen
		if( !this.view.getLeiter().isEmpty() ){
			oLeiter = this.gewaehlterMA;
			if(oLeiter == null)
			{
				try {
					oLeiter = new Mitarbeiter(this.view.getLeiter());
				} catch (Exception e1) 
				{
					oLeiter = null;

				}
			}
		}
		
		
		//Prüfung ob Leiter ersetzt wird
		boolean replace = false;
		
		//Bereichsleiter bei Bearbeiten ersetzen, wenn ein Leiter gesetzt war
		if( this.operation == "bearbeiten" && this.gewaehlterBereich.getLeiter() != null ){
				
			//Bereich soll keinen Leiter mehr haben
			if( oLeiter == null ){
				replace = true;
			}else{
				//neuer Leiter != neuem Leiter
				if( !this.gewaehlterBereich.getLeiter().getBenutzername().equals( oLeiter.getBenutzername() ) ) replace = true;
			}

		}
		
		//Ersetzen-Popup anzeigen
		if(replace){
			//Ersetzen-PopUp
			popup.setTitle("Bereichsleiter ersetzen?");
			popup.setAusgabe("Wollen Sie den aktuellen Bereichsleiter ersetzen und speichern?");
			popup.setButtonName( "Ersetzen", "Nicht ersetzen" );
			
		//Wirklich speichern Popup anzeigen
		}else{
			popup.setTitle("Bestätigung");
			popup.setAusgabe(HilfeTexte.SpeichernPopup);
		}
	}

	/**
	 * ausgewählten Bereich löschen und ComboBox in View aktualisieren.
	 */
	private void loescheBereich() {
		//Bereich zum löschen finden und löschen
		Iterator<Bereich> i = this.bereich.iterator();		
		while( i.hasNext() ){
			Bereich B = (Bereich) i.next();
			String Bezeichnung = B.getKurzbezeichnung();
			if( Bezeichnung.equals( this.viewLoesch.getBereich() ) ){
				try{
					
					//Bereich löschen
					if( B.loeschen() ){
						
						//Bereich aus Collection entfernen
						this.bereich.remove(B);
						
						//ComboBox in View aktualisieren
						this.viewLoesch.setBereiche(Funktionen.BereicheCollection2Array(this.bereich));
						
						//View aktualisieren
						this.viewLoesch.revalidate();
						
						//Rückmeldung an User
						HauptController.hauptfenster.setInfoBox("Bereich gelöscht.");
					}
				}catch(Exception ex){
					HauptController.hauptfenster.setInfoBox(ex.getMessage());
				}
				break;
			}
		}
		
		//Popup wieder ausblenden
		popup.setVisible(false);
		popup = null;
	}

	/**
	 * Popup zur Bestätigung von Bereich löschen.
	 */
	private void bereichLoeschenPopup() {
		popup = new BestaetigenPopup();
		popup.setController(this);
		popup.setTitle("Löschen");
		popup.setAusgabe(HilfeTexte.LoeschenPopup);
	}
	
	/**
	 * Prüft, ob Leiter = Leiter der alten AG -> setzt null, und schiebt Leiter in neue AG
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	private void moveLeiter(){
		//Leiter in neuen Bereich schieben und alten Bereich entfernen
		if( oLeiter != null){
			if( oLeiter.getBereich().getLeiter() != null ){
				//nur wenn Leiter = zu verschiebendem Leiter
				if( oLeiter.getBereich().getLeiter().getBenutzername().equals( oLeiter.getBenutzername() ) ){
					try {
						oLeiter.getBereich().setLeiter(null);
					} catch (Exception e) {}
				}
			}
			
			//neuen Leiter in Bereich schieben
			oLeiter.setBereich(this.gewaehlterBereich);
		}
	}

	/**
	 * Gibt die aktive View zurück.
	 */
	@Override
	public View getView() {
		switch( this.operation ){
			case "anlegen": return this.view;
			case "bearbeiten": return this.viewLoesch;
			case "loeschen": return this.viewLoesch;
		}
		return null;
	}

	/**
	 * Wird vom SuchController aufgerufen, wenn ein Mitarbeiter als Bereichsleiter ausgewählt wurde.
	 */
	@Override
	public void fortsetzen() {
		gewaehlterMA = (Mitarbeiter) this.suche.getAuswahl();
		this.view.setLeiter(gewaehlterMA.getBenutzername());
		if( this.operation.equals("anlegen") ){
			this.view.setButtonAbbrechenName("Zurücksetzen");
			HauptController.hauptfenster.setUeberschrift("Bereich anlegen");
		}else{
			this.view.setButtonAbbrechenName("Abbrechen");
			HauptController.hauptfenster.setUeberschrift("Bereich bearbeiten");
		}
		HauptController.hauptfenster.setContent(this.view);
		this.view.repaint();
		
	}

	/**
	 * keine Aktion.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * keine Aktion.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/**
	 * keine Aktion.
	 */
	@Override
	public void mouseExited(MouseEvent e) {}

	/**
	 * keine Aktion.
	 */
	@Override
	public void mousePressed(MouseEvent e) {}

	/**
	 * keine Aktion.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}

	/**
	 *  * Setzt den Fokus auf den Speichern Button, 
	 * wird gebraucht um KeyReleased korrekt auszufrühren.
	 */
	@Override
	public void keyPressed(KeyEvent e) {	
		if(this.operation == "loeschen"||this.operation == "bearbeiten"){
			this.viewLoesch.setzeFocus();
		}
	}

	/**
	 * Enter-Taste ruft entsprechend der Operation ein Popup zur Bestätigung auf.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				if(this.operation == "bearbeiten"){
					if (this.viewLoesch.hatFocus() == "buttonLoeschen")
					{
						bearbeiteBereich();
					}
					else if(this.view.hatFocus() == "buttonSpeichern" && popup==null){
						anlegenBearbeitenPopup();
					}
					else if(popup.isFocused() == true && popup.hatFocus()== "popupJa"){
						speicherAenderungen();
					}
					else if(popup.isFocused() == true && popup.hatFocus() == "popupNein"){
						popup.setVisible(false);
						popup=null;
						HauptController.hauptfenster.setInfoBox("");
					}
				}
				if(this.operation == "anlegen"){
					if(this.view.hatFocus() == "buttonSpeichern" && popup==null){
						anlegenBearbeitenPopup();
					}else if(popup.isFocused() == true && popup.hatFocus()== "popupJa"){
						anlegenBereich();
					}
					else if(popup.isFocused() == true && popup.hatFocus() == "popupNein"){
						popup.setVisible(false);
						popup=null;
						HauptController.hauptfenster.setInfoBox("");
					}
				}
				if(this.operation == "loeschen"){
					if(this.viewLoesch.hatFocus() == "buttonLoeschen" && popup==null){
						bereichLoeschenPopup();
					}else if(popup.isFocused() == true && popup.hatFocus()== "popupJa"){
						loescheBereich();
					}
					else if(popup.isFocused() == true && popup.hatFocus() == "popupNein"){
						popup.setVisible(false);
						popup=null;
						HauptController.hauptfenster.setInfoBox("");
					}
				}
			}
	}

	/**
	 * Keine Aktion.
	 */
	@Override
	public void keyTyped(KeyEvent e) {}
}
