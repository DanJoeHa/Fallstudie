package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Art;
import fallstudie.view.impl.ArtAnlegenView;
import fallstudie.view.impl.ArtLoeschenView;
import fallstudie.view.impl.BestaetigenPopup;
import fallstudie.view.impl.HilfeTexte;
import fallstudie.view.interfaces.View;

public class ArtController implements Controller {
	
	private ArtAnlegenView view;
	private ArtLoeschenView viewLoeschen;
	private String operation;
	private Collection<Art> art;
	public static BestaetigenPopup popup;
	private View aktView;
	private String button;
	
	
	
	
	
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
		button = e.getActionCommand();
		
		//Popup-Bestaetigung abfragen + Ausgaben in Infobox hinzuf�gen
		if(button.equals("Speichern") )
		{
			artAnlegen();
		}	
			if(aktView == this.view){
			if(button.equals("Ja")){
				
				artAnlegenPopup();
			}
			if(button.equals("Nein")){
				this.view.reset();
				popup.setVisible(false);
			}
			}
		

		if(button.equals("Löschen") )
		{
			//Art.loeschen(this.viewLoeschen.getArt());
			
			artLoeschen();
		}	
			if(aktView == this.viewLoeschen){
				if(button.equals("Ja")){
					
					
			artLoeschenPopup();
			
				
			}
			if(button.equals("Nein")){
				popup.setVisible(false);
			}
	}
		}

	private void artLoeschenPopup() {
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

	private void artAnlegenPopup() {
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

	private void artLoeschen() {
		popup = new BestaetigenPopup();
		popup.setController(this);
		popup.setTitle("Löschen");
		popup.setAusgabe(HilfeTexte.LoeschenPopup);
		popup.setVisible(true);
		this.operation = "popupNein";

		aktView = this.viewLoeschen;
	}

	private void artAnlegen() {
		popup = new BestaetigenPopup();
		popup.setController(this);
		popup.setTitle("Bestätigung");
		popup.setAusgabe(HilfeTexte.SpeichernPopup);
		popup.setVisible(true);
		aktView = this.view;
		this.operation = "popupJa";
		
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER && this.operation=="anlegen")
			{
				artAnlegen();
				
			}
			
			if (e.getKeyCode() == KeyEvent.VK_ENTER && this.operation=="loeschen")
			{
			artLoeschen();
			}
		
			if ((e.getKeyCode() == KeyEvent.VK_ENTER  && popup.isFocused() == true && popup.hatFocus()== "popupJa"))
			{
				if(this.operation == "popupJa"){
					artAnlegenPopup();
				}else{
					artLoeschenPopup();
				}
			}
			if ((e.getKeyCode() == KeyEvent.VK_ENTER && popup.isFocused() == true && popup.hatFocus() == "popupNein"))
			{
				if(this.operation =="popupJa"){
					this.view.reset();
					popup.setVisible(false);
				}else{
					this.viewLoeschen.reset();
					popup.setVisible(false);
				}
			}
	}	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
