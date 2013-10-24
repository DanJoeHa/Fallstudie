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

public class SuchController implements Controller,MouseListener {
	
	private SuchenView view;
	private TabelleView viewErg;
	private String suchdomain;
	private Collection<Mitarbeiter> suchergebnisseMa;
	private Collection<Arbeitsgruppe> suchergebnisseAg;
	private String operation;
	private Object auswahl = null;
	private String suchbegriff = "";
	public static BestaetigenPopup popup;
	
	private Controller aufrufenderController;

	public SuchController(){
		
	}
	
	public void setSuchdomain (String suchdomain){
		this.suchdomain = suchdomain;
	}
	
	public Object getAuswahl(){
		return this.auswahl;
	}

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
	
	public void setSuchbegriff(String Suchbegriff){
		this.suchbegriff = Suchbegriff;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		
		//Abbrechen-Button
		if( button.equals("Abbrechen") ){
			HauptController.hauptfenster.zurueck();
		}
		
		//Wenn in Ergebnistabelle ein Eintrag zum löschen gewählt wurde
		if( button == "Löschen" ){
			auswahlLoeschen();	
		}
		
		if( this.suchdomain == "Mitarbeiter"|| this.suchdomain == "Sachbearbeiter" || this.suchdomain == "Gruppenleiter" || this.suchdomain == "Bereichsleiter" ){
			if( button.equals( "Suchen" )){
				suchenActionMitarbeiter(e.getID());
			}
			
			//Wenn in Ergebnistabelle ein Eintrag gewählt wurde
			if( button.equals( "auswählen" ) ){
				auswahlAction_allgemein();
			}
			
			if(button.equals("Ja")){
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
					suchenActionMitarbeiter(e.getID());
				}
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
				}
			}
			if(button.equals("Nein")){
				popup.setVisible(false);
			}
		}
	
}

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

	private void auswahlLoeschen() {
		popup = new BestaetigenPopup();
		popup.setController(this);
		popup.setTitle("Löschen");
		popup.setAusgabe(HilfeTexte.LoeschenPopup);
	}

	private void auswahlAction_allgemein() {
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

	public void setAufrufenderController(Controller c)
	{
		this.aufrufenderController = c;
	}

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

	@Override
	public void fortsetzen() {}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==2){
			if (this.operation=="loeschen"){
				auswahlLoeschen();
			}else if(this.suchdomain.equals("Arbeitsgruppe") && (this.operation.equals("auswahl")||this.operation.equals("suchen"))){
				auswahlAction_Arbeitsgruppe();
			}else{
				auswahlAction_allgemein();
			}
		} 
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

		if( this.suchdomain == "Mitarbeiter"|| this.suchdomain == "Sachbearbeiter" || this.suchdomain == "Gruppenleiter" || this.suchdomain == "Bereichsleiter" ){
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				suchenActionMitarbeiter(e.getID());
			}
		}
		if( this.suchdomain.equals( "Arbeitsgruppe" )){ 
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				suchenActionArbeitsgruppe(e.getID());
				
			}			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
