package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
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
		
		//Bereich löschen bestätigten
		if(button.equals("Löschen") ){
			popup = new BestaetigenPopup();
			popup.setController(this);
			popup.setTitle("Löschen");
			popup.setAusgabe(HilfeTexte.LoeschenPopup);
		}
		
		//Bereich anlegen/bearbeiten speichern
		if(button.equals("Speichern")){	
			popup = new BestaetigenPopup();
			popup.setController(this);
			
			//Leiter holen
			if( !this.view.getLeiter().isEmpty() ) oLeiter = this.gewaehlterMA;	
			
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
		

			
		//Bereich löschen durchführen
		if( this.operation.equals("loeschen") && button.equals("Ja") ){
			
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
			
		//Abbrechen Lösch-Aktion
		if(button.equals("Nein") || button.equals("Nicht ersetzen")){
			popup.setVisible(false);
		}
		
		//Bereich zur Bearbeitung ausgewählt
		if(button.equals("Bearbeiten") ){
			
			String tempBereich = this.viewLoesch.getBereich();
			this.view = new BereichBearbeitenAnlegenView();
			HauptController.hauptfenster.setContent(view);
			this.view.setController( this );
			this.view.setKurzbezeichnung(tempBereich);
			gewaehlterBereich = Bereich.getBereichByName(tempBereich);
			this.view.setBezeichnung(gewaehlterBereich.getBeschreibung());
			try
			{
				this.view.setLeiter(gewaehlterBereich.getLeiter().getBenutzername());
			}
			catch(Exception ex)
			{
				this.view.setLeiter(ex.getMessage());
			}
			
		}
		
		//Bereichsleiter suchen
		if(button.equals("Suchen")){
			
			this.suche = new SuchController();
			this.suche.setAufrufenderController(this);
			this.suche.setSuchdomain("Bereichsleiter");
			this.suche.setSuchbegriff(this.view.getLeiter());
			this.suche.setOperation("auswahl");
			
			HauptController.hauptfenster.setUeberschrift("Bereichsleiter auswählen");
			HauptController.hauptfenster.setContent(this.suche.getView());
		}
		
		//Bereich anlegen/bearbeiten durchführen
		if(button.equals("Ja") || button.equals("Ersetzen")){	
			
			//Bereich anlegen
			if(this.operation.equals("anlegen"))
			{
				try
				{
					new Bereich(this.view.getKurzbezeichnung(), this.view.getBezeichnung(), oLeiter);
				}
				catch (Exception ex)
				{
					HauptController.hauptfenster.setInfoBox(ex.getMessage());
				}finally{
					popup.setVisible(false);
				}
			}
			
			//Bereich bearbeiten
			if(this.operation.equals("bearbeiten"))	{
				
				try
				{
					this.gewaehlterBereich.setBeschreibung(this.view.getBezeichnung());
					this.gewaehlterBereich.setKurzbezeichnung(this.view.getKurzbezeichnung());
					this.gewaehlterBereich.setLeiter(oLeiter);
				}
				catch (Exception ex)
				{
					HauptController.hauptfenster.setInfoBox(ex.getMessage());
				}finally{
					popup.setVisible(false);
				}
			}					
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
		HauptController.hauptfenster.setUeberschrift("Bereich anlegen");
		HauptController.hauptfenster.setContent(this.view);
		this.view.repaint();
		
		
	}
}
