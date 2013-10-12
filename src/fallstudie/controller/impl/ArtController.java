package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import fallstudie.model.impl.Art;

public class ArtController extends HauptController {
	
	private ArtAnlegenView view;
	private ArtLoeschenView viewLoeschen;
	private String operation;
	
	public ArtController(){
		
	}
	
	public void setOperation(String operation){
		this.operation = operation;
		if( this.operation.equals("anlegen"))
		{
			this.view = new ArtAnlegenView();
		}
		else
		{
			if(this.operation.equals("loeschen"))
			{
				this.view = new ArtLoeschenView();
				this.viewLoeschen.setArt(Art.getAlleArten()); //Collection in String Array umwandeln
			}
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String button = e.getActionCommand();
		
		//Popup-Bestaetigung abfragen + Ausgaben in Infobox hinzufügen
		if(button.equals("Speichern")
		{
			new Art(this.view.getArt());
		}
		else
		{
			if(button.equals("Loeschen")
			{
				Art.loeschen(this.view.getArt());
			}
		}
	}

}
