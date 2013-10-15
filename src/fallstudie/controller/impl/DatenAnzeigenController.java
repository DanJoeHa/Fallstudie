package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Jahresuebersicht;
import fallstudie.model.impl.Wochenuebersicht;
import fallstudie.model.impl.Zeile;
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
			
			//Ueberschift
			String headline = "Daten anzeigen ";
			String[] tabellenspalten = null;
			String[][] tabellenwerte = null;
			
			//Jahresübersicht
			if( kw == 0 ){
				
				headline += "Jahr " + jahr;
				
				//Jahresübersicht Zentralbereichsleiter/Fachbereichsorganisation
				if( HauptController.activeUser.checkRecht("Lesen alle Bereiche Jahr") ){
					//Jahresuebersicht oJahresuebersicht = new Jahresuebersicht( jahr, true );
					//TODO
				}
				
				//Jahresübersicht Bereichsleiter
				if( HauptController.activeUser.checkRecht("Lesen alle Arbeitsgruppen eines Bereichs Jahr") ){
					Jahresuebersicht oJahresuebersicht = new Jahresuebersicht( jahr, HauptController.activeUser.getBereich() );
					//TODO
				}
				
				//Jahresübersicht Gruppenleiter
				if( HauptController.activeUser.checkRecht("Lesen eigene Arbeitsgruppe Jahr") ){
					Jahresuebersicht oJahresuebersicht = new Jahresuebersicht( jahr, HauptController.activeUser.getArbeitsgruppe() );
					
					tabellenspalten[0] = oJahresuebersicht.getArbeitsgruppe().getKurzbezeichnung();
					Collection<Zeile> values;
					try {
						values = oJahresuebersicht.getZeileArbeitsgruppe();
						
						tabellenwerte = new String[ values.size() ][1];
						int x = 0;
						Iterator<Zeile> i = values.iterator();
						while( i.hasNext() ){
							Zeile z = (Zeile) i.next();
							tabellenwerte[x][0] = z.getArt().getName();
							tabellenwerte[x][1] = Integer.toString( z.getSumme() );
						}
						
					} catch (Exception e1) {
						HauptController.hauptfenster.setInfoBox("Keine Datensätze gefunden.");
						tabellenwerte[0][0] = "#";
					}
					
					
					
					
				}
			}else{
			//Kalenderwochenübersicht
				
				headline += "KW " + kw + "/" + jahr;
				
				//Kalenderwochenübersicht Zentralbereichsleiter/Fachbereichsorganisation
				if( HauptController.activeUser.checkRecht("Lesen alle Bereiche KW") ){
					//Wochenuebersicht oWochenuebersicht = new Wochenuebersicht( jahr, kw, true );
				}
				
				//Kalenderwochenübersicht Bereichsleiter
				if( HauptController.activeUser.checkRecht("Lesen alle Arbeitsgruppen eines Bereichs KW") ){
					Wochenuebersicht oWochenuebersicht = new Wochenuebersicht( jahr, kw, HauptController.activeUser.getBereich() );
				}
				
				//Kalenderwochenübersicht Gruppenleiter
				if( HauptController.activeUser.checkRecht("Lesen eigene Arbeitsgruppe KW") ){
					Wochenuebersicht oWochenuebersicht = new Wochenuebersicht( jahr, kw, HauptController.activeUser.getArbeitsgruppe() );
					
					tabellenspalten[0] = oWochenuebersicht.getArbeitsgruppe().getKurzbezeichnung();
					Collection<Zeile> values;
					try {
						values = oWochenuebersicht.getZeileArbeitsgruppe();
						
						tabellenwerte = new String[ values.size() ][1];
						int x = 0;
						Iterator<Zeile> i = values.iterator();
						while( i.hasNext() ){
							Zeile z = (Zeile) i.next();
							tabellenwerte[x][0] = z.getArt().getName();
							tabellenwerte[x][1] = Integer.toString( z.getSumme() );
						}
						
					} catch (Exception e1) {
						HauptController.hauptfenster.setInfoBox("Keine Datensätze gefunden.");
						tabellenwerte[0][0] = "#";
					}
					
				}
			}
			
			//an TabelleView übergeben
			this.viewErg = new TabelleView();
			this.viewErg.setController( this );
			this.viewErg.setTabelle(tabellenspalten, tabellenwerte);
			this.viewErg.setButtonName("Drucken");
			HauptController.hauptfenster.setUeberschrift(headline);
			HauptController.hauptfenster.setContent( this.viewErg );
		}
		
		//Daten ausdrucken
		if( button.equals("weiter") ){
			
		}
	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return null;
	}
}
