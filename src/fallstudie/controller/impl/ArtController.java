package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Art;
import fallstudie.model.impl.Mitarbeiter;
import fallstudie.view.impl.ArtAnlegenView;
import fallstudie.view.impl.ArtLoeschenView;
import fallstudie.view.impl.BestaetigenPopup;

import fallstudie.view.interfaces.View;

public class ArtController implements Controller {
	
	private ArtAnlegenView view;
	private ArtLoeschenView viewLoeschen;
	private String operation;
	private Object auswahl;
	private Collection<Art> art;
	public static BestaetigenPopup popup;
	private View test;
	
	
	
	public ArtController(){
		
	}
	
	public void setOperation(String operation){
		this.operation = operation;
		if( this.operation.equals("anlegen"))
		{
			this.view = new ArtAnlegenView();
			this.view.setController( this );
		}
		else
		{
			if(this.operation.equals("loeschen"))
			{
				this.viewLoeschen = new ArtLoeschenView();
				this.viewLoeschen.setController( this );
				//this.viewLoeschen.setArt(Art.getAlleArten().toArray()); //Collection in String Array umwandeln
				
				

				this.art = Art.getAlleArten();
				Iterator<Art> i = art.iterator();
				
				String[] sArt = new String[ art.size() ];
				int x = 0;
				while( i.hasNext() ){
					sArt[x] = i.next().getName();
					x++;
				} 
				this.viewLoeschen.setArt( sArt );
				
			}
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String button = e.getActionCommand();
		
		//Popup-Bestaetigung abfragen + Ausgaben in Infobox hinzuf�gen
		if(button.equals("Speichern") )
		{
			popup = new BestaetigenPopup();
			
			popup.setController(this);
			popup.setTitle("Bestätigung");
			popup.setAusgabe("Wollen Sie wirklich speichern?");
			
			test = this.view;
		}	
			if(test == this.view){
			if(button.equals("Ja")){
			
				
				try{				
				
					new Art(this.view.getArt());
				
				
					
				}
				catch(Exception ex){
					HauptController.hauptfenster.setInfoBox(ex.getMessage());
				}
					
				finally{
					this.view.reset();
					popup.setVisible(false);
				}
			}
			if(button.equals("Nein")){
				this.view.reset();
				popup.setVisible(false);
			}
			}
		

		if(button.equals("Löschen") )
		{
			//Art.loeschen(this.viewLoeschen.getArt());
			
			popup = new BestaetigenPopup();
			popup.setController(this);
			popup.setTitle("Löschen");
			popup.setAusgabe("Wollen Sie wirklich löschen?");
			
			test = this.viewLoeschen;
		}	
			if(test == this.viewLoeschen){
				if(button.equals("Ja")){
					
					
			//Wenn in Ergebnistabelle ein Eintrag gewählt wurde
			
				//durch suchergebnisse iterieren und zur auswahl passendes ERgebnis finden und in auswahl speichern 
				
				Iterator<Art> i = this.art.iterator();		
				while( i.hasNext() ){
					Art A = (Art) i.next();
					String Name = A.getName();
					if( Name.equals( this.viewLoeschen.getArt() ) ){
						if(A.loeschen()){
							HauptController.hauptfenster.setInfoBox("Art erfolgreich gelöscht.");
						}
						else{
							HauptController.hauptfenster.setInfoBox("Art konnte nicht gelöscht.");
						}
						break;
					}
				}
				
				this.viewLoeschen.reset();
				popup.setVisible(false);
			
				
			}
			if(button.equals("Nein")){
				popup.setVisible(false);
			}
	}
		}


	/**
	 * Rückgabe der aktiven View
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	@Override
	public View getView() {
		switch( this.operation ){
			case "anlegen": 	return (View) this.view;
			case "loeschen":	return (View) this.viewLoeschen;
		}
		return null;
	}

	@Override
	public void fortsetzen() {
		// TODO Auto-generated method stub
		
	}

}
