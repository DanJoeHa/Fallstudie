package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;
import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Arbeitsgruppe;
import fallstudie.model.impl.Mitarbeiter;
import fallstudie.view.impl.SuchenView;
import fallstudie.view.interfaces.View;

public class SuchController implements Controller {
	
	private SuchenView view;
	private TabelleView viewErg;
	private String suchdomain;
	private Collection<Object> suchergebnisse;
	private String operation;
	private Object auswahl = null;
	private String suchbegriff;
	
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
			ActionEvent e = new ActionEvent(this, 1, "suchen");
			this.actionPerformed(e);
		}
	}
	
	public void setSuchbegriff(String Suchbegriff){
		this.suchbegriff = Suchbegriff;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();

		if( this.suchdomain == "Mitarbeiter" ){
			if( button == "suchen" ){
				
				//initiere Ergebnistabelle
				this.viewErg = new TabelleView();
				this.viewErg.setController( this );
				this.viewErg.setButtonName("auswählen");
				
				//hole passende Suchergebnisse
				this.suchergebnisse = Mitarbeiter.suche( this.view.getSuchbegriff() );
				
				//Content auf Tabellen-Sicht wechseln
				this.viewErg.setTabelle( this.suchergebnisse.toArray() );
				HauptController.hauptfenster.setContent( this.viewErg );
			}
			
			//Wenn in Ergebnistabelle ein Eintrag gewählt wurde
			if( button == "auswählen" ){
				//durch suchergebnisse iterieren und zur auswahl passendes ERgebnis finden und in auswahl speichern 
				Iterator<Object> i = this.suchergebnisse.iterator();
				
				while( i.hasNext() ){
					Mitarbeiter MA = (Mitarbeiter) i.next();
					String benutzername = MA.getBenutzername();
					if( benutzername.equals( this.viewErg.getAuswahl() ) ){
						this.auswahl = MA;
						break;
					}
				}
			}
			
			//Wenn in Ergebnistabelle ein Eintrag zum löschen gewählt wurde
			if( button == "löschen" ){
				//TODO: popup wirklich löschen
				
				//durch Suchergebnisse iterien und zur auswahl passendes Object finden, 
				Iterator<Object> i = this.suchergebnisse.iterator();
				
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
		
		if( this.suchdomain == "Arbeitsgruppe" ){
			if( button == "suchen" ){
				
				//initiere Ergebnistabelle
				this.viewErg = new TabelleView();
				this.viewErg.setController( this );
				this.viewErg.setButtonName("auswählen");
				
				//hole passende Suchergebnisse
				this.suchergebnisse = Arbeitsgruppe.suche( this.view.getSuchbegriff() );
				
				//Content auf Tabellen-Sicht wechseln
				this.viewErg.setTabelle( this.suchergebnisse.toArray() );
				hauptfenster.setContent( this.viewErg );
				
			}
			
			//Wenn in Ergebnistabelle ein Eintrag gewählt wurde
			if( button == "auswählen" ){
				//durch suchergebnisse iterieren und zur auswahl passendes Ergebnis finden und in auswahl speichern 			
				Iterator<Object> i = this.suchergebnisse.iterator();
				
				while( i.hasNext() ){
					Arbeitsgruppe AG = (Arbeitsgruppe) i.next();
					String kurzbez = AG.getKurzbezeichnung();
					if( kurzbez.equals( this.viewErg.getAuswahl() ) ){
						this.auswahl = AG;
						break;
					}
				}
			}
			
			//Wenn in Ergebnistabelle ein Eintrag zum löschen gewählt wurde
			if( button == "löschen" ){
				//TODO: popup wirklich löschen
				
				//durch Suchergebnisse iterien und zur auswahl passendes Object finden, 
				Iterator<Object> i = this.suchergebnisse.iterator();
				
				while( i.hasNext() ){
					Arbeitsgruppe AG = (Arbeitsgruppe) i.next();
					String kurzbez = AG.getKurzbezeichnung();
					if( kurzbez.equals( this.viewErg.getAuswahl() ) ){
						if( AG.loeschen() ){
							HauptController.hauptfenster.setInfoBox("Arbeitsgruppe gelöscht.");
						}else{
							HauptController.hauptfenster.setInfoBox("Arbeitsgruppe konnte nicht gelöscht werden. Bitte ordnen Sie zunächst alle Mitarbeiter einer anderen Organisationseinheit zu.");
						}
						break;
					}
				}
			}
		}
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
}
