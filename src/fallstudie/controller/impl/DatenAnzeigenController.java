package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import fallstudie.controller.interfaces.Controller;
import fallstudie.exportieren.CSVExport;
import fallstudie.exportieren.PDFExport;
import fallstudie.model.impl.Bereich;
import fallstudie.model.impl.Jahresuebersicht;
import fallstudie.model.impl.UebersichtSchnittstellenKlasse;
import fallstudie.model.impl.Wochenuebersicht;
import fallstudie.model.impl.Zeile;
import fallstudie.view.impl.DatenAnzeigenAuswahlView;
import fallstudie.view.impl.HilfeTexte;
import fallstudie.view.impl.TabelleView;
import fallstudie.view.interfaces.View;

/**
 * Der Daten Anzeigen Controller ist verantwortlich für die Anzeige der Statistiken.
 *
 */
public class DatenAnzeigenController implements Controller {
	
	/**
	 * View zur Eingabe von Jahr und KW
	 */
	private DatenAnzeigenAuswahlView view;
	/**
	 * Ergebnisdarstellung in Tabelle
	 */
	private TabelleView viewErg;
	
	/**
	 * Überschrift für View und Druck
	 */
	private String headline;
	/**
	 * Tabellenheader
	 */
	private String[] tabellenspalten = new String[1];
	/**
	 * Tabellendimension
	 */
	private Object[][] tabellenwerte = new Object[1][1];
	/**
	 * Array zur Speicherung der Positionen von Postarten
	 */
	private String[][] aArtPos;
	/**
	 * der Name der Art steht immer in Spalte 0
	 */
	private int artName = 0;
	/**
	 * die Zeile der Art steht immer in Spalte 1
	 */
	private int artZeile = 1;
	/**
	 * Kalenderwoche, Jahr und Summenspalte
	 */
	private int kw, jahr, sumcol;
	/**
	 * Bereiche für DrillDown-Operation
	 */
	private Collection<Bereich> bereiche;
	/**
	 * Fehlermeldung, wenn keine Datensätze vorhanden
	 */
	private String noDS = "Keine Datensätze gefunden!";
	/**
	 * Flag ob in DrillDown
	 */
	private boolean drilldown = false;
	
	
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
	 * ActionListener für Views der Controllers
	 * 
	 * @author Johannes
	 * @version 1.0
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//hole Button
		String button = e.getActionCommand();
		
		//Rahmendaten eingegeben
		if( button.equals("Weiter") ) this.weiterAction();
		
		//Daten ausdrucken PDF
		if( button.equals("PDF") ) this.drucken(true);
		
		//Daten ausdrucken CSV
		if(button.equals("CSV")) this.drucken(false);
		
		//Drill-Down in einen Bereich
		if( button.equals("DrillDown") ) this.drilldown();
		
		//Abbrechen-Button
		if( button.equals("Abbrechen") ) this.abbrechen();

	}

	/**
	 * Ruft die Ergebnistabelle auf.
	 */
	private void weiterAction() {
		
		//InfoBox leeren
		HauptController.hauptfenster.setInfoBox("");
		
		//View laden
		this.viewErg = new TabelleView();
		this.viewErg.setController( this );
		
		//Bereiche-Collection initiieren/zurücksetzen
		this.bereiche = new LinkedList<>();
		
		//Angaben holen
		try
		{
			//hole Kalenderwoche und Jahr aus View
			kw = this.view.getWoche();
			jahr = this.view.getJahr();
		
			//Angaben prüfen
			if( kw < 0 || jahr < 0 ) throw new Exception("Bitte mindestens das Kalenderjahr ausfüllen (nur Ziffern > 0!).");
			
			//Jahresübersicht
			if( kw == 0 ){
				
				//Überschrift erweitern
				this.headline = "Daten anzeigen Jahr " + jahr;
				
				//Jahresübersicht Zentralbereichsleiter/Fachbereichsorganisation
				if( HauptController.activeUser.checkRecht("Lesen alle Bereiche Jahr") ){
					
					this.headline += " für alle Bereiche";
					
					if( !this.uebersichtJahrZentralbereichsleiter() ) throw new Exception(this.noDS);
					HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_SummierteErgebnisseGesamtbereich);
				}
				
				//Jahresübersicht Bereichsleiter
				if( HauptController.activeUser.checkRecht("Lesen alle Arbeitsgruppen eines Bereichs Jahr") ){
					UebersichtSchnittstellenKlasse uebersichtSST = Jahresuebersicht.getAlleJahresuebersichtenZumBereich( jahr, HauptController.activeUser.getBereich() );
					this.headline += " für alle Arbeitsgruppen in Ihrem Bereich '" + HauptController.activeUser.getBereich().getKurzbezeichnung() + "'";
						
					if( !this.generiereJahresuebersichtenZuBereich(uebersichtSST) ) throw new Exception(this.noDS);
					HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_SummierteErgebnisseGesamtbereich_AG);
				}
				
				//Jahresübersicht Gruppenleiter
				if( HauptController.activeUser.checkRecht("Lesen eigene Arbeitsgruppe Jahr") ){
					
					this.headline += " für Ihre Arbeitsgruppe '" + HauptController.activeUser.getArbeitsgruppe().getKurzbezeichnung() + "'";
					if( !this.uebersichtJahrGruppenleiter() ) throw new Exception(this.noDS);
					HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_Arbeitsgruppeeinsehen_Gruppenleiter);
				}
				
			}else{
			//Kalenderwochenübersicht
				
				this.headline = "Daten anzeigen KW " + kw + "/" + jahr;
				
				//Kalenderwochenübersicht Zentralbereichsleiter/Fachbereichsorganisation
				if( HauptController.activeUser.checkRecht("Lesen alle Bereiche KW") ){
					this.headline += " für alle Bereiche";
					if( !this.uebersichtWocheZentralbereichsleiter() ) throw new Exception(this.noDS);
					HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_SummierteErgebnisseGesamtbereich);
				}
				
				//Kalenderwochenübersicht Bereichsleiter
				if( HauptController.activeUser.checkRecht("Lesen alle Arbeitsgruppen eines Bereichs KW") ){
					UebersichtSchnittstellenKlasse uebersichtSST = Wochenuebersicht.getAlleWochenuebersichtenZumBereich( jahr, kw, HauptController.activeUser.getBereich() );
					
					this.headline += " für alle Arbeitsgruppen in Ihrem Bereich '" + HauptController.activeUser.getBereich().getKurzbezeichnung() + "'";
					if( !this.generiereWochenuebersichtenZuBereich(uebersichtSST) ) throw new Exception(this.noDS);
					HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_SummierteErgebnisseGesamtbereich_AG);

				}
				
				//Kalenderwochenübersicht Gruppenleiter
				if( HauptController.activeUser.checkRecht("Lesen eigene Arbeitsgruppe KW") ){
					this.headline += " für Ihre Arbeitsgruppe '" + HauptController.activeUser.getArbeitsgruppe().getKurzbezeichnung() + "'";
					if( !this.uebersichtWocheGruppenleiter() ) throw new Exception(this.noDS);
					HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_Arbeitsgruppeeinsehen_Gruppenleiter);
				}
			}
			
			//an TabelleView übergeben
			this.viewErg.setTabelle(tabellenspalten, tabellenwerte);
			this.viewErg.setButtonName("PDF");
			this.viewErg.setSichtbarkeit();
			HauptController.hauptfenster.setUeberschrift(headline);
			HauptController.hauptfenster.setContent( this.viewErg );
			
		}
		catch(Exception ex)
		{
			HauptController.hauptfenster.setInfoBox(ex.getMessage());
			this.view.reset();
		}
	}
	
	/**
	 * Wechselt die View und setzt die InfoBox wieder leer
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	private void abbrechen(){
		//leere InfoBox
		HauptController.hauptfenster.setInfoBox("");
		
		//wenn in DrillDown
		if(this.drilldown){
			
			//Flag wieder false setzten
			this.drilldown = false;
			
			//eigene ActionEvent-Methode aufrufen um übergeordnete View wieder aufzubauen
			ActionEvent event = new ActionEvent(this, 1, "Weiter");
			this.actionPerformed(event);
			
		//Daten anzeige in übergeordneter View
		}else{
			
			//Daten anzeigen neu starten
			HauptController.startDatenAnzeigen();
		}
	}
	
	/**
	 * Generierte Jahresübersicht für den Zentralbereichsleiter/Fachbereichsorganisation
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	private boolean uebersichtJahrZentralbereichsleiter(){
		
		//hole Daten aus Model-Schicht
		UebersichtSchnittstellenKlasse uebersichtSST = Jahresuebersicht.getAlleJahresuebersichtenZuAllenBereichen( jahr );
		
		try{
			
			//Maximale Anzahl an Zeilen bestimmen
			int maxZeilen = this.bestimmeMaxZeilen(uebersichtSST);
			if( maxZeilen == 0 ) return false;
			
			//Jahresübersichten in Collection holen
			Collection<Jahresuebersicht> coJahresuebersichten = uebersichtSST.Jahresuebersichten;
			
			//Bereiche-Collection für Combobox in View befüllen
			this.fuelleBereichCollectionJahresuebersicht(coJahresuebersichten);

			//DrillDown-Button und ComboBox anzeigen
			this.viewErg.setBereiche(Funktionen.BereicheCollection2Array(this.bereiche));
			
			//ComboBox nur anzeigen, wenn Bereiche in Collection vorhanden
			if( this.bereiche.size() > 0 ) this.viewErg.setDrillDown(true);
			
			//Definiere Tabelle
			this.definiereTabelle(maxZeilen, coJahresuebersichten.size(), "Bereich");
			
			//Schleifenvars
			int spalte = 1;
			this.initiierePositionsArray(maxZeilen);			
			
			//alle Jahresuebersichten aus Collection durchlaufen
			Iterator<Jahresuebersicht> itJahre = coJahresuebersichten.iterator();
			while( itJahre.hasNext() ){
				
				//Schreibe Bereich in Tabellenkopf
				Jahresuebersicht oJahresuebersicht = itJahre.next();
				tabellenspalten[spalte] = oJahresuebersicht.getBereich().getKurzbezeichnung();
				
				//alle Zeilen des aktiven Jahresbericht holen un mit durchzählen
				Collection<Zeile> z = oJahresuebersicht.getZeileBereich();
				Iterator<Zeile> itZeile = z.iterator();
				
				//alle Zeilen durchlaufen
				while( itZeile.hasNext() ){

					//Zeile hinzufügen
					this.addZeile(itZeile, spalte, true);
					
				}
				
				//nächste Spalte im Gesamtbericht
				spalte++;
			}
			
		}catch(Exception ex){
			HauptController.hauptfenster.setInfoBox(noDS);
			ex.printStackTrace();
		}
		
		//Rückgabe
		return true;
		
	}
	
	/**
	 * Befüllt die BereichsCollection aus einer Collection von Jahresübersichten
	 * 
	 * @author Johannes
	 * @version 1.0
	 * @param coJahresuebersichten
	 */
	private void fuelleBereichCollectionJahresuebersicht(Collection<Jahresuebersicht> coJahresuebersichten) {
		
		Iterator<Jahresuebersicht> itJahre = coJahresuebersichten.iterator();
		while( itJahre.hasNext() ){
			Jahresuebersicht oJahr = itJahre.next();
			if( oJahr.getBereich() != null ) this.bereiche.add(oJahr.getBereich());
			
		}
	}
	
	/**
	 * Bestimmt die maximale Anzahl an Zeilen
	 * 
	 * @version 1.0
	 * @param uebersichtSST
	 * @return
	 * @throws Exception
	 */
	private int bestimmeMaxZeilen(UebersichtSchnittstellenKlasse uebersichtSST) {		
		//maximale Anzahl zeilen bestimmen
		return uebersichtSST.anzahlArten;
	}

	/**
	 * Befüll das PositionsArray zur Speicherung von Arten und deren Zeilen mit leeren Werten
	 * @param maxZeilen
	 * @author Johannes
	 * @version 1.0
	 */
	private void initiierePositionsArray(int maxZeilen) {
		aArtPos = new String[maxZeilen][2]; 
		for(int s = 0; s < maxZeilen; s++){
			aArtPos[s][0] = "";
			aArtPos[s][1] = "";
		}
	}
	
	
	/**
	 * Generiert Jahresübersicht für Gruppenleiter
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	private boolean uebersichtJahrGruppenleiter(){
		
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
			//wenn leer
			return false;
		}
		
		//Rückgabe
		return true;
		
	}
	
	/**
	 * Generiert die Wochenübersicht für Gruppenleiter und Fachbereichsorganisation
	 * 
	 * @author Johannes
	 * @version 1.0
	 * 
	 */
	private boolean uebersichtWocheZentralbereichsleiter(){
		
		//hole Uebersicht aus Model-Schicht
		UebersichtSchnittstellenKlasse uebersichtSST = Wochenuebersicht.getAlleWochenuebersichtenZuAllenBereichen(jahr, kw);
			
		try{
			//Maximale Anzahl an Zeilen bestimmen
			int maxZeilen = bestimmeMaxZeilen(uebersichtSST);
			if( maxZeilen == 0 ) return false;
			
			//hole Wochenübersichten
			Collection<Wochenuebersicht> coWochenuebersichten = uebersichtSST.Wochenuebersichten;
			
			//befülle Bereich-COllectoin (für Combobox in View-Schicht)
			this.befuelleBereichCollectionWochenuebersicht(coWochenuebersichten);
			
			//DrillDown-Button und ComboBox anzeigen
			this.viewErg.setBereiche(Funktionen.BereicheCollection2Array(this.bereiche));
			this.viewErg.setDrillDown(true);
			
			//Summenspalte bestimmen und Array-Größen definieren
			this.definiereTabelle(maxZeilen, coWochenuebersichten.size(), "Bereiche");
			
			//Schleifenvars
			int spalte = 1;
			this.initiierePositionsArray(maxZeilen);	
			
			//alle Wochenuebersichten aus Collection durchlaufen
			Iterator<Wochenuebersicht> itWoche = coWochenuebersichten.iterator();
			while( itWoche.hasNext() ){
				
				//Schreibe Bereich in Tabellenkopf
				Wochenuebersicht oWochenuebersicht = itWoche.next();
				tabellenspalten[spalte] = oWochenuebersicht.getBereich().getKurzbezeichnung();
				
				//alle Zeilen des aktiven Jahresbericht holen un mit durchzählen
				Collection<Zeile> z = oWochenuebersicht.getZeileBereich();
				Iterator<Zeile> itZeile = z.iterator();
				
				//alle Zeilen durchlaufen
				while( itZeile.hasNext() ){
					
					//Zeile hinzufügen
					this.addZeile(itZeile, spalte, true);
			
				}
				
				//nächste Spalte im Gesamtbericht
				spalte++;
			}
			
		}catch(Exception ex){
			HauptController.hauptfenster.setInfoBox(noDS);
			ex.printStackTrace();
		}
		
		//Rückgabe
		return true;
		
	}
	
	/**
	 * Definiert die TabellenArrays
	 * 
	 * @author Johannes
	 * @version 1.0
	 * 
	 * @param maxZeilen
	 * @param collectionsize
	 * @param organisationseinheit
	 */
	private void definiereTabelle(int maxZeilen, int collectionsize, String organisationseinheit) {
		sumcol = collectionsize + 1;
		tabellenspalten = new String[ collectionsize + 2 ];
		tabellenwerte = new Object[ maxZeilen ][ collectionsize + 2 ];
		tabellenspalten[0] = "Art\\" + organisationseinheit;
		tabellenspalten[sumcol] = "Summe";
	}

	/**
	 * Befüllt aus einer Wochenübersicht Collection die Bereich Collection
	 * @author Johannes
	 * @version 1.0
	 * @param coWochenuebersichten
	 */
	private void befuelleBereichCollectionWochenuebersicht(Collection<Wochenuebersicht> coWochenuebersichten) {
		Iterator<Wochenuebersicht> itWoche = coWochenuebersichten.iterator();
		while( itWoche.hasNext() ){
			Wochenuebersicht oWoche = itWoche.next();
			if( oWoche.getBereich() != null ) this.bereiche.add(oWoche.getBereich());
		}
	}
	
	/**
	 * Generierte die Wochenuebersicht für den Gruppenleiter
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	private boolean uebersichtWocheGruppenleiter(){
		
		Wochenuebersicht oWochenuebersicht = new Wochenuebersicht( jahr, kw, HauptController.activeUser.getArbeitsgruppe() );
		
		
		try {
			
			tabellenspalten = new String[2];
			tabellenspalten[0] = "Art";
			tabellenspalten[1] = "Summe";
			
			Collection<Zeile> values  = oWochenuebersicht.getZeileArbeitsgruppe();
			
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
			//wenn leer
			return false;
		}
		
		//Rückgabe
		return true;
	}
	
	/**
	 * Wechselt von Bereichsebene auf Arbeitsgruppenebene
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	private void drilldown(){
		//drilldown
		this.drilldown = true;
		
		//Bereichsobjekt zur Auswahl finden
		Iterator<Bereich> i = this.bereiche.iterator();
		Bereich oBereich = null;
		while( i.hasNext() ){
			oBereich = i.next();
			if(oBereich.getKurzbezeichnung().equals( this.viewErg.getDrillDownBereich() ) ) break;
		}
		
		//Headline anpassen
		this.headline += " > Arbeitsgruppen des Bereich " + oBereich.getKurzbezeichnung();
		
		UebersichtSchnittstellenKlasse uebersichtSST;
		
		//Jahr
		if( kw == 0){
			
			//Daten holen
			uebersichtSST = Jahresuebersicht.getAlleJahresuebersichtenZumBereich( jahr, oBereich );
			
			//Tabelle neu generieren
			this.generiereJahresuebersichtenZuBereich(uebersichtSST);

		//Woche
		}else{
			
			//Daten holen
			uebersichtSST = Wochenuebersicht.getAlleWochenuebersichtenZumBereich( jahr, kw, oBereich );
			
			//Tabelle neu generieren
			this.generiereWochenuebersichtenZuBereich(uebersichtSST);

		}
		
		//neue View erstellen
		this.viewErg = new TabelleView();
		this.viewErg.setController(this);
		this.viewErg.setButtonName("PDF");
		this.viewErg.setSichtbarkeit();
		
		//DrillDown ausblenden
		this.viewErg.setDrillDown(false);
		
		//an TabelleView übergeben
		HauptController.hauptfenster.setUeberschrift( this.headline );
		this.viewErg.setTabelle(tabellenspalten, tabellenwerte);
		HauptController.hauptfenster.setContent(this.viewErg);
		HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_SummierteErgebnisseGesamtbereich_AG);
	}
	
	/**
	 * Ausdrucken
	 * boolean, true = PDF, false = CSV
	 * @author Johannes
	 * @version 1.0
	 */
	private void drucken(boolean art){
		
		String[][] tabwerte = new String[this.tabellenwerte.length][this.tabellenwerte[0].length];
		
		for( int i = 0; i < this.tabellenwerte.length; i++){
			for( int j = 0; j < this.tabellenwerte[i].length; j++){
				String w = "";
				if( this.tabellenwerte[i][j] != null ) w = (String) this.tabellenwerte[i][j];
				tabwerte[i][j] = w;
			}
		}

		try {
			if(!art)CSVExport.exportCSV(tabwerte, this.tabellenspalten);
			if(art)PDFExport.generateTablePDF(tabwerte, this.tabellenspalten, this.headline);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	
	/**
	 * Generiert die Jahresübersicht zu einem Bereich, d.h. gibt alle Arbeitsgruppen desselben aus.
	 * @param uebersichtSST
	 */
	private boolean generiereJahresuebersichtenZuBereich(UebersichtSchnittstellenKlasse uebersichtSST){
		
		try{
			//Maximale Anzahl an Zeilen bestimmen
			int maxZeilen = bestimmeMaxZeilen(uebersichtSST);
			if( maxZeilen == 0 ) return false;
			
			//hole Collection
			Collection<Jahresuebersicht> coJahresuebersichten = uebersichtSST.Jahresuebersichten;
			
			//BereichCollection füllen
			this.fuelleBereichCollectionJahresuebersicht(coJahresuebersichten);
			
			//Definiere Tabelle
			this.definiereTabelle(maxZeilen, coJahresuebersichten.size(), "Arbeitsgruppe");
			
			//Schleifenvars
			int spalte = 1;
			this.initiierePositionsArray(maxZeilen);	
			
			//alle Jahresuebersichten aus Collection durchlaufen
			Iterator<Jahresuebersicht> itJahre = coJahresuebersichten.iterator();
			while( itJahre.hasNext() ){
				
				//Schreibe Bereich in Tabellenkopf
				Jahresuebersicht oJahresuebersicht = itJahre.next();
				tabellenspalten[spalte] = oJahresuebersicht.getArbeitsgruppe().getKurzbezeichnung();
				
				//alle Zeilen des aktiven Jahresbericht holen un mit durchzählen
				Collection<Zeile> z = oJahresuebersicht.getZeileArbeitsgruppe();
				Iterator<Zeile> itZeile = z.iterator();
				
				//alle Zeilen durchlaufen
				while( itZeile.hasNext() ){
					
					//Zeile hinzufügen
					this.addZeile(itZeile, spalte, true);
		
				}
				
				//nächste Spalte im Gesamtbericht
				spalte++;
			}
			
		}catch(Exception ex){
			HauptController.hauptfenster.setInfoBox(noDS);
			ex.printStackTrace();
		}
		
		//Rückgabe
		return true;
		
	}
	
	/**
	 * Generiert die Wochenübersicht zu einem Bereich, d.h. die Arbeitsgruppen zu einem Bereich.
	 * @param uebersichtSST
	 */
	private boolean generiereWochenuebersichtenZuBereich(UebersichtSchnittstellenKlasse uebersichtSST){
		
		try{
			
			//Maximale Anzahl an Zeilen bestimmen
			int maxZeilen = bestimmeMaxZeilen(uebersichtSST);
			if( maxZeilen == 0 ) return false;
			
			//hole Collection
			Collection<Wochenuebersicht> coWochenuebersichten = uebersichtSST.Wochenuebersichten;
			
			//Befülle Bereich Collection aus Wochenübersicht-Collection
			this.befuelleBereichCollectionWochenuebersicht(coWochenuebersichten);
			
			//Definiere Tabelle
			this.definiereTabelle(maxZeilen, coWochenuebersichten.size(), "Arbeitsgruppe");
			
			//Schleifenvars
			int spalte = 1;
			this.initiierePositionsArray(maxZeilen);	
			
			//alle Wochenuebersichten aus Collection durchlaufen
			Iterator<Wochenuebersicht> itWoche = coWochenuebersichten.iterator();
			while( itWoche.hasNext() ){
				
				//Schreibe Bereich in Tabellenkopf
				Wochenuebersicht oWochenuebersicht = itWoche.next();
				tabellenspalten[spalte] = oWochenuebersicht.getArbeitsgruppe().getKurzbezeichnung();
				
				//alle Zeilen des aktiven Jahresbericht holen un mit durchzählen
				Collection<Zeile> z = oWochenuebersicht.getZeileArbeitsgruppe();
				Iterator<Zeile> itZeile = z.iterator();
				
				//alle Zeilen durchlaufen
				while( itZeile.hasNext() ){
					
					//Zeile hinzufügen
					this.addZeile(itZeile, spalte, true);
	
				}
				
				//nächste Spalte im Gesamtbericht
				spalte++;
			}
			
		}catch(Exception ex){
			HauptController.hauptfenster.setInfoBox(noDS);
			ex.printStackTrace();
		}
		
		//Rückgabe
		return false;
		
	}
	
	/**
	 * Fügt dem Bericht eine Zeile hinzu
	 * 
	 * @param itZeile
	 * @param i
	 * @param spalte
	 */
	private void addZeile(Iterator<Zeile> itZeile, int spalte, boolean summierung){
		
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
			zeile = artInZeile;
			tabellenwerte[zeile][0] = oZeile.getArt().getName();
			aArtPos[artInZeile][artName] = oZeile.getArt().getName();
			aArtPos[artInZeile][artZeile] = Integer.toString(artInZeile);
		}
		
		//Tabellenwert in Spalte & Zeile eintragen
		tabellenwerte[zeile][spalte] = Integer.toString( oZeile.getSumme() );
		
		//wenn Summierung erforderlich, Summenspalte befüllen
		if(summierung){
			int sum = 0;
			if( tabellenwerte[zeile][sumcol] != null ){				
				sum = Integer.parseInt( tabellenwerte[zeile][sumcol].toString() ) + oZeile.getSumme();
			}else{
				sum = oZeile.getSumme();
			}
			tabellenwerte[zeile][sumcol] = Integer.toString( sum );
		}
		
	}
	
	/**
	 * Liefert die aktuelle View.
	 */
	@Override
	public View getView() {
		return this.view;
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
	 * Setzt Messsage in der InfoBox.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			HauptController.hauptfenster.setInfoBox("Einen Moment bitte!");
		}
	}

	/**
	 * Führt die WeiterAction aus.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			weiterAction();
		}
	}

	/**
	 * keine Aktion.
	 */
	@Override
	public void keyTyped(KeyEvent e) {}
}
