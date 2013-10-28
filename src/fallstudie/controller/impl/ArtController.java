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

/**
 * Der Art-Controller ist für die Anlage und das Löschen von "Strich-Arten" zuständig.
 *
 */
public class ArtController implements Controller {
	
	/**
	 * View für das Anlegen einer Art.
	 */
	private ArtAnlegenView view;
	/**
	 * View für das Löschen einer Art.
	 */
	private ArtLoeschenView viewLoeschen;
	/**
	 * String für die interne Steuerung, der durchzuführenden Operation (anlegen, loeschen).
	 */
	private String operation;
	/**
	 * Collection aller angelegten Arten.
	 */
	private Collection<Art> art;
	/**
	 * Popup zur Bestätigung von Löschen und Speichern.
	 */
	public static BestaetigenPopup popup;
	/**
	 * Speichert die aktuelle View.
	 */
	private View aktView;	
	
	
	/**
	 * Standard-Konstruktor
	 */
	public ArtController(){
		
	}
	
	/**
	 * Operation wird gesetzt.
	 * @param (String) operation (anlegen, loeschen)
	 */
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
				
				//Arten in View setzen
				this.getArtenFromModelToView();
				
			}
		}
	}

	/**
	 * Holt alle verfügbaren Arten aus der Model-Schicht und befüllt die ComboBox in der View.
	 */
	private void getArtenFromModelToView() {
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
	
	/**
	 * Methode die bei Mausklicks die Popups lädt und dann entsprechend bei Ja bzw. Nein den Code ausführt.
	 */
	public void actionPerformed(ActionEvent e)
	{
		String button = e.getActionCommand();
		
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
	
	/**
	 * hier wird die Art gelöscht.
	 */
	private void artLoeschenPopup() {
		//Wenn in Ergebnistabelle ein Eintrag gewählt wurde
		
			//durch suchergebnisse iterieren und zur auswahl passendes ERgebnis finden und in auswahl speichern 
			
			Iterator<Art> i = this.art.iterator();		
			while( i.hasNext() ){
				Art A = (Art) i.next();
				String Name = A.getName();
				if( Name.equals( this.viewLoeschen.getArt() ) ){
					if(A.loeschen()){
						
						//Art aus Collection entfernen
						this.art.remove(A);
						
						//ComboBox in View aktualisieren
						this.getArtenFromModelToView();
						
						//View aktualisieren
						//this.viewLoesch.revalidate();
						
						//Rückmeldung an User
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
	/**
	 * hier wird die Art angelegt.
	 */
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
	/**
	 * hier wird das Popup generiert zum löschen.
	 */
	private void artLoeschen() {
		popup = new BestaetigenPopup();
		popup.setController(this);
		popup.setTitle("Löschen");
		popup.setAusgabe(HilfeTexte.LoeschenPopup);
		popup.setVisible(true);
		this.operation = "popupNein";

		aktView = this.viewLoeschen;
	}
	/**
	 * hier wird das Popup generiert zum speichern.
	 */
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

	/**
	 * keine Aktion.
	 */
	@Override
	public void fortsetzen() {}

	/**
	 * keine Aktion.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * keine Aktion.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/**
	 * keine Aktion.
	 */
	@Override
	public void mouseExited(MouseEvent e) {}

	/**
	 * keine Aktion.
	 */
	@Override
	public void mousePressed(MouseEvent e) {}

	/**
	 * keine Aktion.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}

	/**
	 * keine Aktion.
	 */
	@Override
	public void keyPressed(KeyEvent e) {}
	
	/**
	 * Nach Enter Taste wird Popup geladen und dann entsprechend bei Ja bzw. Nein der Code ausgeführt.
	 * @param KeyEvent
	 * @author Patrick Lachnit
	 */
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
					this.operation = "anlegen";
				}else{
					artLoeschenPopup();
				}
			}
			if ((e.getKeyCode() == KeyEvent.VK_ENTER && popup.isFocused() == true && popup.hatFocus() == "popupNein"))
			{
				if(this.operation =="popupJa"){
					this.view.reset();
					popup.setVisible(false);
					this.operation ="anlegen";
				}else{
					this.viewLoeschen.reset();
					popup.setVisible(false);
					this.operation = "loeschen";
				}
			}
	}	

	/**
	 * keine Aktion.
	 */
	@Override
	public void keyTyped(KeyEvent e) {}

}
