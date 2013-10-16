package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Arbeitsgruppe;
import fallstudie.model.impl.Mitarbeiter;
import fallstudie.view.impl.SuchenView;
import fallstudie.view.impl.TabelleView;
import fallstudie.view.interfaces.View;

public class SuchController implements Controller {
	
	private SuchenView view;
	private TabelleView viewErg;
	private String suchdomain;
	private Collection<Mitarbeiter> suchergebnisseMa;
	private Collection<Arbeitsgruppe> suchergebnisseAg;
	private String operation;
	private Object auswahl = null;
	private String suchbegriff;
	
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

		if( this.suchdomain == "Mitarbeiter"|| this.suchdomain == "Sachbearbeiter" || this.suchdomain == "Gruppenleiter" || this.suchdomain == "Bereichsleiter" ){
			if( button == "Suchen" ){
				try{
						
						//initiere Ergebnistabelle
						this.viewErg = new TabelleView();
						this.viewErg.setController( this );
						this.viewErg.setButtonName("auswählen");
						
						
						
						String suche;
						if(e.getID() == 1 ){
							suche = this.suchbegriff;
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
		
			if(button == "Abbrechen"){
			
				HauptController.hauptfenster.zurueck();
				
			}
			
			//Wenn in Ergebnistabelle ein Eintrag gewählt wurde
			if( button == "auswählen" ){
				
				//durch suchergebnisse iterieren und zur auswahl passendes ERgebnis finden und in auswahl speichern 
				Iterator<Mitarbeiter> i = this.suchergebnisseMa.iterator();
				String name = this.viewErg.getAuswahl();
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
			
			
			//Wenn in Ergebnistabelle ein Eintrag zum löschen gewählt wurde
			if( button == "Löschen" ){
				//TODO: popup wirklich löschen
				
				//durch Suchergebnisse iterien und zur auswahl passendes Object finden, 
				Iterator<Mitarbeiter> i = this.suchergebnisseMa.iterator();
				
				while( i.hasNext() ){
					Mitarbeiter MA = (Mitarbeiter) i.next();
					String benutzername = MA.getBenutzername();
					if( benutzername.equals( this.viewErg.getAuswahl() ) ){
						if( MA.loeschen() ){
							HauptController.hauptfenster.setInfoBox("Mitarbeiter gelöscht.");
						}else{
							HauptController.hauptfenster.setInfoBox("Mitarbeiter konnte nicht gelöscht werden. Bitte stellen Sie sicher, dass der Mitarbeiter keiner Arbeitsgruppe/keinem Bereich als Leiter zugeordnet ist.");
						}
						break;
					}
				}	
			}
		}
		
		if( this.suchdomain.equals( "Arbeitsgruppe" )){ 
			if( button.equals( "Suchen") ){
				try{
						//initiere Ergebnistabelle
						this.viewErg = new TabelleView();
						this.viewErg.setController( this );
						this.viewErg.setButtonName("auswählen");
						
						//hole passende Suchergebnisse
						
						String suche;
						if(e.getID() == 1 ){
							suche = this.suchbegriff;
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
		
			//Wenn in Ergebnistabelle ein Eintrag gewählt wurde
			if( button == "auswählen" ){
				//durch suchergebnisse iterieren und zur auswahl passendes Ergebnis finden und in auswahl speichern 			
				Iterator<Arbeitsgruppe> i = this.suchergebnisseAg.iterator();
				String AGname = this.viewErg.getAuswahl();
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
			
			//Wenn in Ergebnistabelle ein Eintrag zum löschen gewählt wurde
			if( button == "Löschen" ){
				//TODO: popup wirklich löschen
				
				//durch Suchergebnisse iterien und zur auswahl passendes Object finden, 
				Iterator<Arbeitsgruppe> i = this.suchergebnisseAg.iterator();
				
				while( i.hasNext() ){
					Arbeitsgruppe AG = (Arbeitsgruppe) i.next();
					String kurzbez = AG.getKurzbezeichnung();
					if( kurzbez.equals( this.viewErg.getAuswahl() ) ){
						try{
							AG.loeschen();
						}catch(Exception ex){
							HauptController.hauptfenster.setInfoBox(ex.getMessage());
						}
						break;
					}
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
	public void fortsetzen() {
		// TODO Auto-generated method stub
		
	}
}
