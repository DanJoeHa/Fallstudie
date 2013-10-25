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
import fallstudie.view.impl.ArbeitsgruppeBearbeitenAnlegenView;
import fallstudie.view.impl.BestaetigenPopup;
import fallstudie.view.impl.HilfeTexte;
import fallstudie.view.impl.SuchenView;
import fallstudie.view.interfaces.View;

/**
 * Der Arbeitsgruppen-Controller ist für die Anlage und das Bearbeiten von Arbeitsgruppen zuständig.
 * 
 *
 */
public class ArbeitsgruppenController implements Controller {
	
	/**
	 * View für das Anlegen und Bearbeiten von Arbeitsgruppen
	 */
	private ArbeitsgruppeBearbeitenAnlegenView view;
	/**
	 * View für das Suchen von Arbeitsgruppen/Mitarbeitern
	 */
	private SuchenView viewSuche;
	/**
	 * String für die Steuerung der durchzuführenden Operation (bearbeiten, anlegen) 
	 */
	private String operation;
	/**
	 * Collection zur Speicherung aller Bereiche aus der Model-Schicht
	 */
	private Collection<Bereich> bereiche;
	/**
	 * Speichert ein Objekt des Suchcontrollers
	 */
	private SuchController suche;
	/**
	 * String-Array für die Befüllung der ComboBox in der View
	 */
	private String[] sBereiche;
	/**
	 * Speichert die gewählte Arbeitsgruppe aus dem Suchcontroller
	 */
	private Arbeitsgruppe gewaehlteAG;
	/**
	 * Speichert den gewählten Mitarbeiter aus dem Suchcontroller
	 */
	private Mitarbeiter gewaehlteMA;
	/**
	 * Popup zur Speichern-Bestätigung
	 */
	public static BestaetigenPopup popup;
	
	//ActionPerformed
	/**
	 * Bereichs-Objekt zur Bestimmung ob Bereich existiert
	 */
	private Bereich oBereich = null;
	/**
	 * Mitarbeiter-Objekt zur Bestimmung ob Mitarbeiter existiert
	 */
	private Mitarbeiter oLeiter = null;
	
	
	
	/**
	 * Erstellt ein neues Objekt, befüllt mit allen Bereichen.
	 * 
	 */
	public ArbeitsgruppenController(){
		//alle Bereiche holen
		this.bereiche = Bereich.getAlleBereiche();	
		sBereiche = Funktionen.BereicheCollection2Array(bereiche);
	}
	
	/**
	 * Erwartet einen der Strings "anlegen" oder "bearbeiten", zeigt  die entsprechende View abhängig von der Operation an.
	 * 
	 * @param (String) operation
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
			this.view.setButtonAbbrechenName("Zurücksetzen");
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
	
	/**
	 * ActionListener für die Views, horcht auf die Buttons.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Button bestimmen
		String button = e.getActionCommand();
		
		//Abbrechen
		if( button.equals("Abbrechen") ){
			HauptController.startArbeitsgruppeBearbeiten();
		}
		
		//Zurücksetzen
		if( button.equals("Zurücksetzen") ){
			this.view.reset();
		}
		
		//Änderungen speichern
		if( button.equals("Speichern") ){
			popupAufruf();
		}
		
		//Speichern der Angaben
		if(button.equals("Ja") ||  button.equals("Ersetzen")){
			arbeitsgruppeSpeichernAnlegenAction();	
		}
		
		//Nicht speichern
		if(button.equals("Nein") || button.equals("Nicht ersetzen") ){
			popup.setVisible(false);
		}
		
		
		//Zurücksetzen button
		if( button.equals("Zurücksetzen") ){
			this.view.reset();
		}
		
		//Gruppenleiter suchen
		if( button.equals("Suchen") ){
			
			this.suche= new SuchController();
			this.suche.setSuchdomain("Gruppenleiter");
			this.suche.setAufrufenderController(this);
			this.suche.setSuchbegriff(this.view.getAGLeiter());
			
			//Hilfe für Tabelle bei Arbeitsgruppe anlegen - GL suchen
			HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_Mitarbeiterbearbearbeiten_Mitarbeiterloeschen_Arbeitsgruppeanlegen_Arbeitsgruppebearbeiten_Gruppenleiter_Bereichanlegen_Bereichsleiter_Bereichbearbeiten_Bereichsleiter);

			this.suche.setOperation("auswahl");
			HauptController.hauptfenster.setContent(this.suche.getView());
		}
	}

	private void arbeitsgruppeSpeichernAnlegenAction() {
		//Bereichsobjekt zur Auswahl finden
		Iterator<Bereich> i = this.bereiche.iterator();
		
		while( i.hasNext() ){
			oBereich = i.next();
			if(oBereich.getKurzbezeichnung().equals( this.view.getBereich() ) ) break;
		}
		
		//Arbeitsgruppe bearbeiten
		if(operation.equals("bearbeiten"))
		{
			try {

					this.moveLeiter();
					boolean erfolgreich1 = false;
					boolean erfolgreich2 = false;
					boolean erfolgreich3 = false;
					boolean erfolgreich4 = false;
					erfolgreich1 = this.gewaehlteAG.setBereich(oBereich);
					erfolgreich2 = this.gewaehlteAG.setBeschreibung(this.view.getBezeichnung() );
					erfolgreich3 = this.gewaehlteAG.setKurzbezeichnung(this.view.getKurzbezeichnung());
					erfolgreich4 = this.gewaehlteAG.setLeiter(oLeiter);

					//Abfrage ob erfolgreich, weil in DB Ebene nicht möglich
					if(erfolgreich1 && erfolgreich2 && erfolgreich3 && erfolgreich4)
					{
						HauptController.hauptfenster.setInfoBox("Arbeitsgruppe wurde erfolgreich bearbeitet.");
						if(oLeiter == null){
							HauptController.hauptfenster.setInfoBox("Mitarbeiter nicht vorhanden. Arbeitsgruppe wurde ohne Leiter gespeichert.");
						}
					}
					else
					{
						HauptController.hauptfenster.setInfoBox("Arbeitsgruppe wurde nicht geändert.");
					}						
				
			} catch (Exception e1) {
				HauptController.hauptfenster.setInfoBox( e1.getMessage() );
			}
		}
		
		//neue Arbeitsgruppe anlegen
		if(operation.equals("anlegen"))
		{
			arbeitsgruppeAnlegenAction();
		}
		
		//Popup ausblenden
		popup.setVisible(false);
		popup=null;
	}

	private void popupAufruf() {
		//Leiter holen
		if( !this.view.getAGLeiter().isEmpty() ){
			//Leiter setzen, falls Leiter über SuchController gefunden wurde
			oLeiter = this.gewaehlteMA;
			//Falls Leiter nicht über SuchController gefunden wurde
			if(oLeiter == null){
				//Hole Mitarbeiter aus DB
				try {
					oLeiter = new Mitarbeiter(this.view.getAGLeiter());
				} catch (Exception e1) 
				{
					oLeiter = null;
				}
			}
		}
		
		//Prüfung ob Leiter ersetzt wird
		boolean replace = false;
		
		//Arbeitsgruppenleiter bei Bearbeiten ersetzen, wenn ein Leiter gesetzt war
		if( this.operation == "bearbeiten" && this.gewaehlteAG.getLeiter() != null ){
				
			//Arbeitsgruppe soll keinen Leiter mehr haben
			if( oLeiter == null ){
				replace = true;
			}else{
				//neuer Leiter != neuem Leiter
				if( !this.gewaehlteAG.getLeiter().getBenutzername().equals( oLeiter.getBenutzername() ) ) replace = true;
			}

		}
		
		//Ersetzen-Popup anzeigen
		if(replace){
			//Ersetzen-PopUp
			popup = new BestaetigenPopup();
			popup.setController(this);
			popup.setTitle("Arbeitsgruppenleiter ersetzen?");
			popup.setAusgabe("Wollen Sie den aktuellen Arbeitsgruppenleiter ersetzen und speichern?");
			popup.setButtonName( "Ersetzen", "Nicht ersetzen" );
			
		//Wirklich speichern Popup anzeigen
		}else{
			popup = new BestaetigenPopup();
			
			popup.setController(this);
			popup.setTitle("Bestätigung");
			popup.setAusgabe(HilfeTexte.SpeichernPopup);
		}
	}

	private void arbeitsgruppeAnlegenAction() {
		try{
			
			Arbeitsgruppe neueAG = new Arbeitsgruppe(this.view.getKurzbezeichnung(), this.view.getBezeichnung(), oBereich, oLeiter);
			this.gewaehlteAG = neueAG;
			this.moveLeiter();
		}
		catch (Exception e1)
		{
			HauptController.hauptfenster.setInfoBox( e1.getMessage() );
			//ist Mitarbeiter nicht in DB vorhanden
			if(oLeiter == null){
				HauptController.hauptfenster.setInfoBox("Mitarbeiter nicht vorhanden. Arbeitsgruppe wurde ohne Leiter angelegt.");
			}
		}
	}
	
	/**
	 * Methode die nach der Suche nach Arbeitsgruppe/Mitarbeiter aus dem Suchcontroller aufgerufen wird.
	 * 
	 */
	public void fortsetzen (){

		try//if(operation.equals("bearbeiten"))
		{
			//ausgewählte Arbeitsgruppe holen
			this.gewaehlteAG = (Arbeitsgruppe) suche.getAuswahl();
			//an Maske übergeben & Maske anzeigen
			this.view = new ArbeitsgruppeBearbeitenAnlegenView();
			this.view.setController(this);
			this.view.setButtonAbbrechenName("Abbrechen");
			this.view.setBereich( sBereiche, gewaehlteAG.getBereich().getKurzbezeichnung());
			this.view.setKurzbezeichnung( gewaehlteAG.getKurzbezeichnung() );
			this.view.setBezeichnung( gewaehlteAG.getBeschreibung() );
			
			String LeiterBenutzerName = "";
			if( gewaehlteAG.getLeiter() != null )
			{
				LeiterBenutzerName = gewaehlteAG.getLeiter().getBenutzername();
			}
			this.view.setAGLeiter( LeiterBenutzerName );
			
			HauptController.hauptfenster.setUeberschrift("Arbeitsgruppe bearbeiten");
		}catch(Exception ex)
		
			//if(operation.equals("anlegen"))
			{
				this.gewaehlteMA = (Mitarbeiter) suche.getAuswahl();
				HauptController.hauptfenster.setUeberschrift("Arbeitsgruppe anlegen");
				
				this.view.setAGLeiter(this.gewaehlteMA.getBenutzername());
				
			}
		HauptController.hauptfenster.setContent( this.view );
		this.view.repaint();

	}
	
	/**
	 * Prüft, ob Leiter = Leiter der alten AG -> setzt null, und schiebt Leiter in neue AG
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	private void moveLeiter(){
		//Leiter in neue AG schieben und alte AG Leiter entfernen
		if( oLeiter != null){
			
			if( oLeiter.getArbeitsgruppe().getLeiter() != null ){
				
				//nur wenn Leiter = zu verschiebendem Leiter
				if( oLeiter.getArbeitsgruppe().getLeiter().getBenutzername().equals( oLeiter.getBenutzername() ) ){
						oLeiter.getArbeitsgruppe().setLeiter(null);

				}
			}
			
			//neuen Leiter in AG schieben
			oLeiter.setArbeitsgruppe(this.gewaehlteAG);
			
		}
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
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(this.operation == "anlegen" && popup==null){
				this.view.setzeFocus();
			}
			if(this.operation =="bearbeiten" && popup==null){
				this.view.setzeFocus();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(this.operation == "anlegen"){
				if (this.view.hatFocus()=="buttonSpeichern")
				{
					popupAufruf();
				}
				else if(popup.isFocused() == true && popup.hatFocus()== "popupJa"){
					arbeitsgruppeSpeichernAnlegenAction();
				}
				else if(popup.isFocused() == true && popup.hatFocus() == "popupNein"){
					popup.setVisible(false);
					popup=null;
					HauptController.hauptfenster.setInfoBox("");
				}
			}
			if(this.operation == "bearbeiten"){
				if (this.view.hatFocus() == "buttonSpeichern")
				{
					popupAufruf();
				}
				else if(popup.isFocused() == true && popup.hatFocus()== "popupJa"){
					arbeitsgruppeSpeichernAnlegenAction();
				}
				else if(popup.isFocused() == true && popup.hatFocus() == "popupNein"){
					popup.setVisible(false);
					popup=null;
					HauptController.hauptfenster.setInfoBox("");
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}
