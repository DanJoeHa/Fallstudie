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

public class BereichController implements Controller {
	
	private String operation;
	private BereichBearbeitenAnlegenView view;
	private BereichLoeschenView viewLoesch;
	private SuchController suche;
	private Mitarbeiter gewaehlterMA, oLeiter;
	private Bereich gewaehlterBereich;
	public static BestaetigenPopup popup;
	private boolean isPop=false;
	

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
		if( this.operation.equals("loeschen") && button.equals("Ja") ){
			
			bereichLoeschen();
		}
			
		//Abbrechen Lösch-Aktion
		if(button.equals("Nein") || button.equals("Nicht ersetzen")){
			popup.setVisible(false);
		}
		
		//Bereich zur Bearbeitung ausgewählt
		if(button.equals("Bearbeiten") ){
			
			bearbeitenButton();
			
		}
		
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
			if(this.operation.equals("anlegen")){
				
				bereichAnlegen();
			}
			
			//Bereich bearbeiten
			if(this.operation.equals("bearbeiten"))	{
				
				bearbeitenBereich();
			}					
		}
		
	}

	private void bearbeitenBereich() {
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
		}
	}

	private void bearbeitenButton() {
		String tempBereich = this.viewLoesch.getBereich();
		this.view = new BereichBearbeitenAnlegenView();
		HauptController.hauptfenster.setContent(view);
		this.view.setController( this );
		this.view.setKurzbezeichnung(tempBereich);
		this.view.setButtonAbbrechenName("Abbrechen");
		gewaehlterBereich = Bereich.getBereichByName(tempBereich);
		this.view.setBezeichnung(gewaehlterBereich.getBeschreibung());
		try
		{
			this.view.setLeiter(gewaehlterBereich.getLeiter().getBenutzername());
		}
		catch(Exception ex)
		{
		}
	}

	private void bereichAnlegen() {
		try{
			Bereich neuerBereich = new Bereich(this.view.getKurzbezeichnung(), this.view.getBezeichnung(), oLeiter);
			this.gewaehlterBereich = neuerBereich;
			this.moveLeiter();
		}
		catch (Exception ex)
		{
			HauptController.hauptfenster.setInfoBox(ex.getMessage());
			if(oLeiter == null){
				HauptController.hauptfenster.setInfoBox("Mitarbeiter nicht vorhanden. Bereich wurde ohne Leiter angelegt.");
			}
		}finally{
			popup.setVisible(false);
		}
	}

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

	private void bereichLoeschen() {
		//Bereich zum löschen finden und löschen
		Iterator<Bereich> i = this.bereich.iterator();		
		while( i.hasNext() ){
			Bereich B = (Bereich) i.next();
			String Bezeichnung = B.getKurzbezeichnung();
			if( Bezeichnung.equals( this.viewLoesch.getBereich() ) ){
				try{
					B.loeschen();
				}catch(Exception ex){
					HauptController.hauptfenster.setInfoBox(ex.getMessage());
				}
				break;
			}
		}
		
		//Popup wieder ausblenden
		popup.setVisible(false);
	}

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

	@Override
	public View getView() {
		switch( this.operation ){
			case "anlegen": return this.view;
			case "bearbeiten": return this.viewLoesch;
			case "loeschen": return this.viewLoesch;
		}
		return null;
	}

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
		HauptController.hauptfenster.setUeberschrift("Bereich anlegen");
		HauptController.hauptfenster.setContent(this.view);
		this.view.repaint();
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {	
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {		
	}

	@Override
	public void mouseReleased(MouseEvent e) {	
	}

	@Override
	public void keyPressed(KeyEvent e) {		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Operation löschen & anlegen & bearbeiten	
			if(this.operation=="loeschen"){
				if (e.getKeyCode() == KeyEvent.VK_ENTER && this.operation=="loeschen" && isPop == false)
				{
					bereichLoeschenPopup();
					isPop = true;
					this.operation = "popupLoeschen";
				}
			}
			if(this.operation=="anlegen"){
				if (e.getKeyCode() == KeyEvent.VK_ENTER && this.operation=="anlegen" && isPop == false)
				{
					anlegenBearbeitenPopup();
					isPop = true;
					this.operation = "popupAnlegen";
				}
			}
		
			if(this.operation == "popupLoeschen"){
				if ((e.getKeyCode() == KeyEvent.VK_ENTER  && popup.isFocused() == true && popup.hatFocus()== "popupJa" ))
				{
					bereichLoeschen();
					isPop = false;
					this.operation = "loeschen";
				}
			}
			if(this.operation == "popupAnlegen"){
				if ((e.getKeyCode() == KeyEvent.VK_ENTER  && popup.isFocused() == true && popup.hatFocus()== "popupJa" ))
				{
					bereichAnlegen();
					isPop = false;
					this.operation = "anlegen";
				}
			}
		
			if(this.operation=="popupLoeschen"){	
				if ((e.getKeyCode() == KeyEvent.VK_ENTER && popup.isFocused() == true && popup.hatFocus() == "popupNein"))
				{
					popup.setVisible(false);
					isPop = false;
					this.operation = "loeschen";
				}
			}	
			if(this.operation=="popupAnlegen"){
				if ((e.getKeyCode() == KeyEvent.VK_ENTER && popup.isFocused() == true && popup.hatFocus() == "popupNein"))
				{
					popup.setVisible(false);
					isPop = false;
					this.operation = "anlegen";
				}
			}
			if(this.operation=="bearbeiten"){
				if (e.getKeyCode() == KeyEvent.VK_ENTER && this.operation=="bearbeiten" && isPop == false)
				{
					bearbeitenButton();
					isPop = true;
					popup.setVisible(false);
				}
			}
			if(this.operation=="bearbeiten"){
				if (e.getKeyCode() == KeyEvent.VK_ENTER && this.operation=="bearbeiten")
				{
					anlegenBearbeitenPopup();
					isPop = true;
					this.operation = "popupBearbeiten";
				}
			}
			if(this.operation == "popupBearbeiten"){
				if ((e.getKeyCode() == KeyEvent.VK_ENTER  && popup.isFocused() == true && popup.hatFocus()== "popupJa" ))
				{
					bearbeitenBereich();
					isPop = false;
					this.operation = "bearbeiten";
				}
			}
			if(this.operation=="popupBearbeiten"){
				if ((e.getKeyCode() == KeyEvent.VK_ENTER && popup.isFocused() == true && popup.hatFocus() == "popupNein"))
				{
					popup.setVisible(false);
					isPop = false;
					this.operation = "bearbeiten";
				}
			}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
}
