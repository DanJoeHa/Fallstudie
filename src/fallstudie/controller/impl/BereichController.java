package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Art;
import fallstudie.model.impl.Bereich;
import fallstudie.model.impl.Mitarbeiter;
import fallstudie.view.impl.BereichBearbeitenAnlegenView;
import fallstudie.view.impl.BereichLoeschenView;
import fallstudie.view.interfaces.View;

public class BereichController implements Controller {
	
	private String operation;
	private BereichBearbeitenAnlegenView view;
	private BereichLoeschenView viewLoesch;
	private SuchController suche;
	private Mitarbeiter gewaehlterMA;
	private Bereich gewaehlterBereich;
	
	private Collection<Bereich> bereich;
	
	public BereichController(){
		this.bereich = Bereich.getAlleBereiche();
	}
	
	public void setOperation(String operation){
		this.operation = operation;
		if( this.operation.equals("anlegen"))
		{
			this.view = new BereichBearbeitenAnlegenView();
			this.view.setController( this );
		}
		else
		{
			if(this.operation.equals("loeschen"))
			{
				this.viewLoesch = new BereichLoeschenView();
				this.viewLoesch.setController( this );
				this.viewLoesch.setBereiche(Funktionen.BereicheCollection2Array( this.bereich ));
			}
			else
			{
				if(this.operation.equals("bearbeiten"))
				{
					this.viewLoesch = new BereichLoeschenView();
					this.viewLoesch.setController( this );
					this.viewLoesch.setBereiche(Funktionen.BereicheCollection2Array(Bereich.getAlleBereiche()));
					this.viewLoesch.setButtonName("Bearbeiten");
					this.viewLoesch.setHinweis("Bitte zu bearbeitenden Bereich auswählen");
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		
		if(button.equals("Löschen") )
		{
			Iterator<Bereich> i = this.bereich.iterator();		
			while( i.hasNext() ){
				Bereich B = (Bereich) i.next();
				String Bezeichnung = B.getKurzbezeichnung();
				if( Bezeichnung.equals( this.viewLoesch.getBereich() ) ){
					if(B.loeschen()){
						HauptController.hauptfenster.setInfoBox("Bereich erfolgreich gelöscht.");
					}
					else{
						HauptController.hauptfenster.setInfoBox("Bereich konnte nicht gelöscht.");
					}
					break;
				}
			}
		}
		
		if(button.equals("Bearbeiten") )
		{
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
		if(button.equals("Suchen"))
		{
			this.suche = new SuchController();
			this.suche.setAufrufenderController(this);
			this.suche.setSuchdomain("Bereichsleiter");
			this.suche.setSuchbegriff(this.view.getLeiter());
			this.suche.setOperation("auswahl");
			
			HauptController.hauptfenster.setUeberschrift("Bereichsleiter auswählen");
			HauptController.hauptfenster.setContent(this.suche.getView());
		}
		
		if(button.equals("Speichern"))
		{
			if(this.operation.equals("anlegen"))
			{
				try
				{
					new Bereich(this.view.getKurzbezeichnung(), this.view.getBezeichnung(), gewaehlterMA);
				}
				catch (Exception ex)
				{
					HauptController.hauptfenster.setInfoBox(ex.getMessage());
				}
			}
			if(this.operation.equals("bearbeiten"))
			{
				try
				{
					this.gewaehlterBereich.setBeschreibung(this.view.getBezeichnung());
					this.gewaehlterBereich.setKurzbezeichnung(this.view.getKurzbezeichnung());
					this.gewaehlterBereich.setLeiter(this.gewaehlterMA);
				}
				catch (Exception ex)
				{
					HauptController.hauptfenster.setInfoBox(ex.getMessage());
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
