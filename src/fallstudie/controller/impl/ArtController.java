package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Art;
import fallstudie.view.impl.ArtAnlegenView;
import fallstudie.view.impl.ArtLoeschenView;
import fallstudie.view.interfaces.View;

public class ArtController implements Controller {
	
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
			this.view.setController( this );
		}
		else
		{
			if(this.operation.equals("loeschen"))
			{
				this.viewLoeschen = new ArtLoeschenView();
				this.viewLoeschen.setController( this );
				this.viewLoeschen.setArt(Art.getAlleArten().toArray()); //Collection in String Array umwandeln
			}
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String button = e.getActionCommand();
		
		//Popup-Bestaetigung abfragen + Ausgaben in Infobox hinzuf�gen
		if(button.equals("Speichern") )
		{
			new Art(this.view.getArt());
		}

		if(button.equals("Loeschen") )
		{
			Art.loeschen(this.viewLoeschen.getArt());
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
	}

}
