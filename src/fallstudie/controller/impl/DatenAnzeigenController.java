package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Jahresuebersicht;
import fallstudie.model.impl.Wochenuebersicht;
import fallstudie.view.impl.DatenAnzeigenAuswahlView;
import fallstudie.view.impl.TabelleView;
import fallstudie.view.interfaces.View;

public class DatenAnzeigenController implements Controller {
	
	private DatenAnzeigenAuswahlView view;
	private TabelleView viewErg;
	
	private String suchdomain;
	
	/**
	 * Maske zur Eingabe der Rahmendaten KW und Jahr anzeigen
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	public DatenAnzeigenController(){
		
		//Maske zur Eingabe der Rahmendaten KW und Jahr anzeigen
		this.view = new DatenAnzeigenAuswahlView();
		this.view.setController( this );
		
	}
	
	/**
	 * 
	 * @param suchdomain
	 */
	public void setSuchdomain(String suchdomain){
	}
	
	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		
		//Rahmendaten eingegeben
		if( button.equals("weiter") ){
			
			//Angaben holen
			int kw = this.view.getWoche();
			int jahr = this.view.getJahr();			
			
			//Jahresübersicht
			if( kw == 0 ){
				
				if( HauptController.activeUser.checkRecht(recht) )
				
				//Jahresübersicht Bereichsleiter
				Jahresuebersicht oJahresuebersicht = new Jahresuebersicht( jahr, HauptController.activeUser.getBereich() );
				
				//Jahresübersicht Zentralbereichsleiter/Fachbereichsorganisation
				Jahresuebersicht oJahresuebersicht = new Jahresuebersicht( jahr );
				
				//Jahresübersicht Gruppenleiter
				Jahresuebersicht oJahresuebersicht = new Jahresuebersicht( jahr, HauptController.activeUser.getArbeitsgruppe() );
				
			}else{
			//Kalenderwochenübersicht
				
				//Kalenderwochenübersicht Bereichsleiter
				Wochenuebersicht oWochenuebersicht = new Wochenuebersicht( jahr, kw, HauptController.activeUser.getBereich() );
				
				//Kalenderwochenübersicht Zentralbereichsleiter/Fachbereichsorganisation
				Wochenuebersicht oWochenuebersicht = new Wochenuebersicht( jahr, kw );
				
				//Kalenderwochenübersicht Gruppenleiter
				Wochenuebersicht oWochenuebersicht = new Wochenuebersicht( jahr, kw, HauptController.activeUser.getArbeitsgruppe() );
				
			}
			
			//an TabelleView übergeben
		}
	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return null;
	}
}
