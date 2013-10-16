package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.apache.pdfbox.exceptions.COSVisitorException;

import fallstudie.controller.interfaces.Controller;
import fallstudie.exportieren.PDFDruck;
import fallstudie.model.impl.Jahresuebersicht;
import fallstudie.model.impl.Wochenuebersicht;
import fallstudie.model.impl.Zeile;
import fallstudie.view.impl.DatenAnzeigenAuswahlView;
import fallstudie.view.impl.TabelleView;
import fallstudie.view.interfaces.View;

public class DatenAnzeigenController implements Controller {
	
	private DatenAnzeigenAuswahlView view;
	private TabelleView viewErg;
	private String headline = "Daten anzeigen ";
	private String[] tabellenspalten = new String[1];
	private Object[][] tabellenwerte = new Object[1][1];
	
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
		if( button.equals("Weiter") ){
			
			//Angaben holen
			int kw = this.view.getWoche();
			int jahr = this.view.getJahr();						
			
			//Jahresübersicht
			if( kw == 0 ){
				
				this.headline += "Jahr " + jahr;
				
				//Jahresübersicht Zentralbereichsleiter/Fachbereichsorganisation
				if( HauptController.activeUser.checkRecht("Lesen alle Bereiche Jahr") ){
					Collection<Jahresuebersicht> coJahresuebersichten = Jahresuebersicht.getAlleJahresuebersichtenZuAllenBereichen( jahr );
					
					try{
						
						//bestimme max. Anzahl an Zeilen anhand der gespeicherten Arten
						Iterator<Jahresuebersicht> itJahre = coJahresuebersichten.iterator();
						int maxZeilen = 0;
						while( itJahre.hasNext() ){
							Jahresuebersicht oJahr = itJahre.next();
							
							int anzZeilen = oJahr.getZeileBereich().size();
							if( anzZeilen > maxZeilen ) maxZeilen = anzZeilen;
						}
						
						
						
						tabellenspalten = new String[ coJahresuebersichten.size() + 1 ];
						tabellenwerte = new Object[ maxZeilen ][ coJahresuebersichten.size() + 1 ];
						tabellenspalten[0] = "Art\\Bereich";
						
						//Schleifenvars
						int spalte = 1;
						String[][] aArtPos = {{"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}};
						int artName = 0;
						int artZeile = 1;
						int i = 0;
						
						//alle Jahresuebersichten aus Collection durchlaufen
						itJahre = coJahresuebersichten.iterator();
						while( itJahre.hasNext() ){
							
							//Schreibe Bereich in Tabellenkopf
							Jahresuebersicht oJahresuebersicht = itJahre.next();
							tabellenspalten[spalte] = oJahresuebersicht.getBereich().getKurzbezeichnung();
							
							//alle Zeilen des aktiven Jahresbericht holen un mit durchzählen
							Collection<Zeile> z = oJahresuebersicht.getZeileBereich();
							Iterator<Zeile> itZeile = z.iterator();
							//int i = 0;
							
							//alle Zeilen durchlaufen
							while( itZeile.hasNext() ){
								
								//naechste Zeile holen
								Zeile oZeile = (Zeile) itZeile.next();
								
								//prüfen ob Art bereits in Tabellenarray vorhanden und wenn ja wo
								int artInZeile;
								boolean found = false;
								for(artInZeile = 0; artInZeile < aArtPos.length; artInZeile++){
									//wenn Schleife an erster Stelle ohne Inhalt angekommen ist, abbrechen
									if( aArtPos[artInZeile][artName].isEmpty() ) break;
									
									//wenn Uebereinstimmung zwischen Zeilenart und Arrayspeicher gefunden
									if( aArtPos[artInZeile][artName].equals( oZeile.getArt().getName() ) ){
										found = true;
										break;	
									}
								}
								
								//Tabellenzeile bestimmen
								int zeile = 0;
								if(found){
									zeile = Integer.parseInt( aArtPos[ artInZeile ][ artZeile ] );
								}else{
									zeile = i;
									tabellenwerte[zeile][0] = oZeile.getArt().getName();
									aArtPos[artInZeile][artName] = oZeile.getArt().getName();
									aArtPos[artInZeile][artZeile] = Integer.toString(artInZeile);
								}
								
								//Tabellenwert in Spalte & Zeile eintragen
								tabellenwerte[zeile][spalte] = Integer.toString( oZeile.getSumme() );
								i++;
							}
							
							//nächste Spalte im Gesamtbericht
							spalte++;
						}
						
					}catch(Exception ex){
						HauptController.hauptfenster.setInfoBox("Keine Datensätze gefunden");
						ex.printStackTrace();
					}
					
				}
				
				//Jahresübersicht Bereichsleiter
				if( HauptController.activeUser.checkRecht("Lesen alle Arbeitsgruppen eines Bereichs Jahr") ){
					Collection<Jahresuebersicht> coJahresuebersichten = Jahresuebersicht.getAlleJahresuebersichtenZumBereich( jahr, HauptController.activeUser.getBereich() );
					
					try{
						
						//bestimme max. Anzahl an Zeilen anhand der gespeicherten Arten
						Iterator<Jahresuebersicht> itJahre = coJahresuebersichten.iterator();
						int maxZeilen = 0;
						while( itJahre.hasNext() ){
							Jahresuebersicht oJahr = itJahre.next();
							
							int anzZeilen = oJahr.getZeileBereich().size();
							if( anzZeilen > maxZeilen ) maxZeilen = anzZeilen;
						}
						
						
						
						tabellenspalten = new String[ coJahresuebersichten.size() + 1 ];
						tabellenwerte = new Object[ maxZeilen ][ coJahresuebersichten.size() + 1 ];
						tabellenspalten[0] = "Art\\Bereich";
						
						//Schleifenvars
						int spalte = 1;
						String[][] aArtPos = {{"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}};
						int artName = 0;
						int artZeile = 1;
						int i = 0;
						
						//alle Jahresuebersichten aus Collection durchlaufen
						itJahre = coJahresuebersichten.iterator();
						while( itJahre.hasNext() ){
							
							//Schreibe Bereich in Tabellenkopf
							Jahresuebersicht oJahresuebersicht = itJahre.next();
							tabellenspalten[spalte] = oJahresuebersicht.getArbeitsgruppe().getKurzbezeichnung();
							
							//alle Zeilen des aktiven Jahresbericht holen un mit durchzählen
							Collection<Zeile> z = oJahresuebersicht.getZeileArbeitsgruppe();
							Iterator<Zeile> itZeile = z.iterator();
							//int i = 0;
							
							//alle Zeilen durchlaufen
							while( itZeile.hasNext() ){
								
								//naechste Zeile holen
								Zeile oZeile = (Zeile) itZeile.next();
								
								//prüfen ob Art bereits in Tabellenarray vorhanden und wenn ja wo
								int artInZeile;
								boolean found = false;
								for(artInZeile = 0; artInZeile < aArtPos.length; artInZeile++){
									//wenn Schleife an erster Stelle ohne Inhalt angekommen ist, abbrechen
									if( aArtPos[artInZeile][artName].isEmpty() ) break;
									
									//wenn Uebereinstimmung zwischen Zeilenart und Arrayspeicher gefunden
									if( aArtPos[artInZeile][artName].equals( oZeile.getArt().getName() ) ){
										found = true;
										break;	
									}
								}
								
								//Tabellenzeile bestimmen
								int zeile = 0;
								if(found){
									zeile = Integer.parseInt( aArtPos[ artInZeile ][ artZeile ] );
								}else{
									zeile = i;
									tabellenwerte[zeile][0] = oZeile.getArt().getName();
									aArtPos[artInZeile][artName] = oZeile.getArt().getName();
									aArtPos[artInZeile][artZeile] = Integer.toString(artInZeile);
								}
								
								//Tabellenwert in Spalte & Zeile eintragen
								tabellenwerte[zeile][spalte] = Integer.toString( oZeile.getSumme() );
								i++;
							}
							
							//nächste Spalte im Gesamtbericht
							spalte++;
						}
						
					}catch(Exception ex){
						HauptController.hauptfenster.setInfoBox("Keine Datensätze gefunden");
						ex.printStackTrace();
					}
					
				}
				
				//Jahresübersicht Gruppenleiter
				if( HauptController.activeUser.checkRecht("Lesen eigene Arbeitsgruppe Jahr") ){
					Jahresuebersicht oJahresuebersicht = new Jahresuebersicht( jahr, HauptController.activeUser.getArbeitsgruppe() );
					
					
					try {
						
						tabellenspalten = new String[2];
						tabellenspalten[0] = "Art";
						tabellenspalten[1] = "Summe";
						Collection<Zeile> values;
						
						values = oJahresuebersicht.getZeileArbeitsgruppe();
						
						tabellenwerte = new String[ values.size() ][2];
						int x = 0;
						Iterator<Zeile> i = values.iterator();
						while( i.hasNext() ){
							Zeile z = (Zeile) i.next();
							tabellenwerte[x][0] = z.getArt().getName();
							tabellenwerte[x][1] = Integer.toString( z.getSumme() );
							x++;
						}
						
					} catch (Exception e1) {
						HauptController.hauptfenster.setInfoBox("Keine Datensätze gefunden.");
						tabellenwerte[0][0] = "#";
					}					
					
				}
			}else{
			//Kalenderwochenübersicht
				
				this.headline += "KW " + kw + "/" + jahr;
				
				//Kalenderwochenübersicht Zentralbereichsleiter/Fachbereichsorganisation
				if( HauptController.activeUser.checkRecht("Lesen alle Bereiche KW") ){
					Collection<Wochenuebersicht> coWochenuebersichten = Wochenuebersicht.getAlleWochenuebersichtenZuAllenBereichen(jahr, kw);
					
					try{
						
						//bestimme max. Anzahl an Zeilen anhand der gespeicherten Arten
						Iterator<Wochenuebersicht> itWoche = coWochenuebersichten.iterator();
						int maxZeilen = 0;
						while( itWoche.hasNext() ){
							Wochenuebersicht oJahr = itWoche.next();
							
							int anzZeilen = oJahr.getZeileBereich().size();
							if( anzZeilen > maxZeilen ) maxZeilen = anzZeilen;
						}
						
						
						
						tabellenspalten = new String[ coWochenuebersichten.size() + 1 ];
						tabellenwerte = new Object[ maxZeilen ][ coWochenuebersichten.size() + 1 ];
						tabellenspalten[0] = "Art\\Bereich";
						
						//Schleifenvars
						int spalte = 1;
						String[][] aArtPos = {{"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}};
						int artName = 0;
						int artZeile = 1;
						int i = 0;
						
						//alle Wochenuebersichten aus Collection durchlaufen
						itWoche = coWochenuebersichten.iterator();
						while( itWoche.hasNext() ){
							
							//Schreibe Bereich in Tabellenkopf
							Wochenuebersicht oWochenuebersicht = itWoche.next();
							tabellenspalten[spalte] = oWochenuebersicht.getBereich().getKurzbezeichnung();
							
							//alle Zeilen des aktiven Jahresbericht holen un mit durchzählen
							Collection<Zeile> z = oWochenuebersicht.getZeileBereich();
							Iterator<Zeile> itZeile = z.iterator();
							
							//alle Zeilen durchlaufen
							while( itZeile.hasNext() ){
								
								//naechste Zeile holen
								Zeile oZeile = (Zeile) itZeile.next();
								
								//prüfen ob Art bereits in Tabellenarray vorhanden und wenn ja wo
								int artInZeile;
								boolean found = false;
								for(artInZeile = 0; artInZeile < aArtPos.length; artInZeile++){
									//wenn Schleife an erster Stelle ohne Inhalt angekommen ist, abbrechen
									if( aArtPos[artInZeile][artName].isEmpty() ) break;
									
									//wenn Uebereinstimmung zwischen Zeilenart und Arrayspeicher gefunden
									if( aArtPos[artInZeile][artName].equals( oZeile.getArt().getName() ) ){
										found = true;
										break;	
									}
								}
								
								//Tabellenzeile bestimmen
								int zeile = 0;
								if(found){
									zeile = Integer.parseInt( aArtPos[ artInZeile ][ artZeile ] );
								}else{
									zeile = i;
									tabellenwerte[zeile][0] = oZeile.getArt().getName();
									aArtPos[artInZeile][artName] = oZeile.getArt().getName();
									aArtPos[artInZeile][artZeile] = Integer.toString(artInZeile);
								}
								
								//Tabellenwert in Spalte & Zeile eintragen
								tabellenwerte[zeile][spalte] = Integer.toString( oZeile.getSumme() );
								i++;
							}
							
							//nächste Spalte im Gesamtbericht
							spalte++;
						}
						
					}catch(Exception ex){
						HauptController.hauptfenster.setInfoBox("Keine Datensätze gefunden");
						ex.printStackTrace();
					}
					
					
					
				}
				
				//Kalenderwochenübersicht Bereichsleiter
				if( HauptController.activeUser.checkRecht("Lesen alle Arbeitsgruppen eines Bereichs KW") ){
					Collection<Wochenuebersicht> coWochenuebersichten = Wochenuebersicht.getAlleWochenuebersichtenZumBereich( jahr, kw, HauptController.activeUser.getBereich() );
					
					try{
						
						//bestimme max. Anzahl an Zeilen anhand der gespeicherten Arten
						Iterator<Wochenuebersicht> itWoche = coWochenuebersichten.iterator();
						int maxZeilen = 0;
						while( itWoche.hasNext() ){
							Wochenuebersicht oJahr = itWoche.next();
							
							int anzZeilen = oJahr.getZeileBereich().size();
							if( anzZeilen > maxZeilen ) maxZeilen = anzZeilen;
						}
						
						
						
						tabellenspalten = new String[ coWochenuebersichten.size() + 1 ];
						tabellenwerte = new Object[ maxZeilen ][ coWochenuebersichten.size() + 1 ];
						tabellenspalten[0] = "Art\\Arbeitsgruppe";
						
						//Schleifenvars
						int spalte = 1;
						String[][] aArtPos = {{"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}};
						int artName = 0;
						int artZeile = 1;
						int i = 0;
						
						//alle Wochenuebersichten aus Collection durchlaufen
						itWoche = coWochenuebersichten.iterator();
						while( itWoche.hasNext() ){
							
							//Schreibe Bereich in Tabellenkopf
							Wochenuebersicht oWochenuebersicht = itWoche.next();
							tabellenspalten[spalte] = oWochenuebersicht.getArbeitsgruppe().getKurzbezeichnung();
							
							//alle Zeilen des aktiven Jahresbericht holen un mit durchzählen
							Collection<Zeile> z = oWochenuebersicht.getZeileArbeitsgruppe();
							Iterator<Zeile> itZeile = z.iterator();
							
							//alle Zeilen durchlaufen
							while( itZeile.hasNext() ){
								
								//naechste Zeile holen
								Zeile oZeile = (Zeile) itZeile.next();
								
								//prüfen ob Art bereits in Tabellenarray vorhanden und wenn ja wo
								int artInZeile;
								boolean found = false;
								for(artInZeile = 0; artInZeile < aArtPos.length; artInZeile++){
									//wenn Schleife an erster Stelle ohne Inhalt angekommen ist, abbrechen
									if( aArtPos[artInZeile][artName].isEmpty() ) break;
									
									//wenn Uebereinstimmung zwischen Zeilenart und Arrayspeicher gefunden
									if( aArtPos[artInZeile][artName].equals( oZeile.getArt().getName() ) ){
										found = true;
										break;	
									}
								}
								
								//Tabellenzeile bestimmen
								int zeile = 0;
								if(found){
									zeile = Integer.parseInt( aArtPos[ artInZeile ][ artZeile ] );
								}else{
									zeile = i;
									tabellenwerte[zeile][0] = oZeile.getArt().getName();
									aArtPos[artInZeile][artName] = oZeile.getArt().getName();
									aArtPos[artInZeile][artZeile] = Integer.toString(artInZeile);
								}
								
								//Tabellenwert in Spalte & Zeile eintragen
								tabellenwerte[zeile][spalte] = Integer.toString( oZeile.getSumme() );
								i++;
							}
							
							//nächste Spalte im Gesamtbericht
							spalte++;
						}
						
					}catch(Exception ex){
						HauptController.hauptfenster.setInfoBox("Keine Datensätze gefunden");
						ex.printStackTrace();
					}
				}
				
				//Kalenderwochenübersicht Gruppenleiter
				if( HauptController.activeUser.checkRecht("Lesen eigene Arbeitsgruppe KW") ){
					Wochenuebersicht oWochenuebersicht = new Wochenuebersicht( jahr, kw, HauptController.activeUser.getArbeitsgruppe() );
					
					
					try {
						tabellenspalten = new String[2];
						tabellenspalten[0] = "Art";
						tabellenspalten[1] = "Summe";
						Collection<Zeile> values;
						
						values = oWochenuebersicht.getZeileArbeitsgruppe();
						
						tabellenwerte = new String[ values.size() ][2];
						int x = 0;
						Iterator<Zeile> i = values.iterator();
						while( i.hasNext() ){
							Zeile z = (Zeile) i.next();
							tabellenwerte[x][0] = z.getArt().getName();
							tabellenwerte[x][1] = Integer.toString( z.getSumme() );
							x++;
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
		if( button.equals("Drucken") ){
			
			String[][] tabwerte = new String[this.tabellenwerte.length][this.tabellenwerte[0].length];
			
			for( int i = 0; i < this.tabellenwerte.length; i++){
				for( int j = 0; j < this.tabellenwerte[i].length; j++){
					String w = "";
					if( this.tabellenwerte[i][j] != null ) w = (String) this.tabellenwerte[i][j];
					tabwerte[i][j] = w;
				}
			}

			try {
				PDFDruck.generateTablePDF(tabwerte, this.headline, this.tabellenspalten);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public View getView() {
		return this.view;
	}
}
