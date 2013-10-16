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
	
	private Collection<Bereich> bereich;
	
	public BereichController(){
		
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
				this.viewLoesch.setBereiche(Funktionen.BereicheCollection2Array(Bereich.getAlleBereiche()));
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
			Bereich bereich = Bereich.getBereichByName(tempBereich);
			this.view.setBezeichnung(bereich.getBeschreibung());
			try
			{
				this.view.setLeiter(bereich.getLeiter().getFullName());
			}
			catch(Exception ex)
			{
				this.view.setLeiter(ex.getMessage());
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
		// TODO Auto-generated method stub
		
	}
}
