package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import fallstudie.controller.interfaces.Controller;
import fallstudie.exportieren.PDFDruck;
import fallstudie.model.impl.Bereich;
import fallstudie.model.impl.Jahresuebersicht;
import fallstudie.model.impl.UebersichtSchnittstellenKlasse;
import fallstudie.model.impl.Wochenuebersicht;
import fallstudie.model.impl.Zeile;
import fallstudie.view.impl.DatenAnzeigenAuswahlView;
import fallstudie.view.impl.HilfeTexte;
import fallstudie.view.impl.TabelleView;
import fallstudie.view.interfaces.View;

public class DatenAnzeigenController implements Controller {
	
	private DatenAnzeigenAuswahlView view;					//View zur Eingabe von Jahr und KW
	private TabelleView viewErg;							//Ergebnisdarstellung in Tabelle
	
	private String headline;								//Überschrift für View und Druck
	private String[] tabellenspalten = new String[1];		//Tabellenheader
	private Object[][] tabellenwerte = new Object[1][1];	//Tabellendimension
	private String[][] aArtPos;								//Array zur Speicherung der Positionen von Postarten
	private int artName = 0;								//der Name der Art steht immer in Spalte 0
	private int artZeile = 1;								//die Zeile der Art steht immer in Spalte 1
	private int kw, jahr, sumcol;							//Kalenderwoche, Jahr und Summenspalte
	private Collection<Bereich> bereiche;					//Bereiche für DrillDown-Operation
	private String noDS = "Keine Datensätze gefunden!";		//Fehlermeldung, wenn keine Datensätze vorhanden
	private boolean drilldown = false;						//Flag ob in DrillDown
	
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
		if( button.equals("Weiter") ){
			
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
				if( kw < 0 || jahr < 0 ) throw new Exception();
				
				//Jahresübersicht
				if( kw == 0 ){
					
					//Überschrift erweitern
					this.headline = "Daten anzeigen Jahr " + jahr;
					
					//Jahresübersicht Zentralbereichsleiter/Fachbereichsorganisation
					if( HauptController.activeUser.checkRecht("Lesen alle Bereiche Jahr") ){
						
						this.headline += " für alle Bereiche";
						
						this.uebersichtJahrZentralbereichsleiter();
						HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_SummierteErgebnisseGesamtbereich);
					}
					
					//Jahresübersicht Bereichsleiter
					if( HauptController.activeUser.checkRecht("Lesen alle Arbeitsgruppen eines Bereichs Jahr") ){
						UebersichtSchnittstellenKlasse uebersichtSST = Jahresuebersicht.getAlleJahresuebersichtenZumBereich( jahr, HauptController.activeUser.getBereich() );
						this.headline += " für alle Arbeitsgruppen in Ihrem Bereich '" + HauptController.activeUser.getBereich().getKurzbezeichnung() + "'";
							
						this.generiereJahresuebersichtenZuBereich(uebersichtSST);
						HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_SummierteErgebnisseGesamtbereich_AG);
					}
					
					//Jahresübersicht Gruppenleiter
					if( HauptController.activeUser.checkRecht("Lesen eigene Arbeitsgruppe Jahr") ){
						
						this.headline += " für Ihre Arbeitsgruppe '" + HauptController.activeUser.getArbeitsgruppe().getKurzbezeichnung() + "'";
						this.uebersichtJahrGruppenleiter();
						HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_Arbeitsgruppeeinsehen_Gruppenleiter);
					}
					
				}else{
				//Kalenderwochenübersicht
					
					this.headline = "Daten anzeigen KW " + kw + "/" + jahr;
					
					//Kalenderwochenübersicht Zentralbereichsleiter/Fachbereichsorganisation
					if( HauptController.activeUser.checkRecht("Lesen alle Bereiche KW") ){
						this.headline += " für alle Bereiche";
						this.uebersichtWocheZentralbereichsleiter();
						HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_SummierteErgebnisseGesamtbereich);
					}
					
					//Kalenderwochenübersicht Bereichsleiter
					if( HauptController.activeUser.checkRecht("Lesen alle Arbeitsgruppen eines Bereichs KW") ){
						UebersichtSchnittstellenKlasse uebersichtSST = Wochenuebersicht.getAlleWochenuebersichtenZumBereich( jahr, kw, HauptController.activeUser.getBereich() );
						
						this.headline += " für alle Arbeitsgruppen in Ihrem Bereich '" + HauptController.activeUser.getBereich().getKurzbezeichnung() + "'";
						this.generiereWochenuebersichtenZuBereich(uebersichtSST);
						HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_SummierteErgebnisseGesamtbereich_AG);
		
					}
					
					//Kalenderwochenübersicht Gruppenleiter
					if( HauptController.activeUser.checkRecht("Lesen eigene Arbeitsgruppe KW") ){
						this.headline += " für Ihre Arbeitsgruppe '" + HauptController.activeUser.getArbeitsgruppe().getKurzbezeichnung() + "'";
						this.uebersichtWocheGruppenleiter();
						HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_Arbeitsgruppeeinsehen_Gruppenleiter);
					}
				}
				
				//an TabelleView übergeben
				this.viewErg.setTabelle(tabellenspalten, tabellenwerte);
				this.viewErg.setButtonName("Drucken");
				HauptController.hauptfenster.setUeberschrift(headline);
				HauptController.hauptfenster.setContent( this.viewErg );
				
			}
			catch(Exception ex)
			{
				HauptController.hauptfenster.setInfoBox("Bitte mindestens das Kalenderjahr ausfüllen (ausschließlich Ziffern > 0!).");
				this.view.reset();
			}
		}
		
		//Daten ausdrucken
		if( button.equals("Drucken") ) this.drucken();
		
		//Drill-Down in einen Bereich
		if( button.equals("DrillDown") ) this.drilldown();
		
		//Abbrechen-Button
		if( button.equals("Abbrechen") ) this.abbrechen();

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
	private void uebersichtJahrZentralbereichsleiter(){
		
		//hole Daten aus Model-Schicht
		UebersichtSchnittstellenKlasse uebersichtSST = Jahresuebersicht.getAlleJahresuebersichtenZuAllenBereichen( jahr );
		
		try{
			
			//Maximale Anzahl an Zeilen bestimmen
			int maxZeilen = this.bestimmeMaxZeilen(uebersichtSST);
			
			//Jahresübersichten in COllection holen
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
	 * Bestimmt die maximale Anzahl an Zeilen und wirft bei 0 eine Exception
	 * 
	 * @version 1.0
	 * @param uebersichtSST
	 * @return
	 * @throws Exception
	 */
	private int bestimmeMaxZeilen(UebersichtSchnittstellenKlasse uebersichtSST)throws Exception {
		//wenn keine Zeilen zur Ausgabe, Exception werfen
		if(uebersichtSST.anzahlArten == 0) throw new Exception();
		
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
	private void uebersichtJahrGruppenleiter(){
		
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
			HauptController.hauptfenster.setInfoBox(noDS);
			tabellenwerte[0][0] = "#";
		}
		
	}
	
	/**
	 * Generiert die Wochenübersicht für Gruppenleiter und Fachbereichsorganisation
	 * 
	 * @author Johannes
	 * @version 1.0
	 * 
	 */
	private void uebersichtWocheZentralbereichsleiter(){
		
		//hole Uebersicht aus Model-Schicht
		UebersichtSchnittstellenKlasse uebersichtSST = Wochenuebersicht.getAlleWochenuebersichtenZuAllenBereichen(jahr, kw);
		
		try{
			
			//Maximale Anzahl an Zeilen bestimmen
			int maxZeilen = bestimmeMaxZeilen(uebersichtSST);
			
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
	private void uebersichtWocheGruppenleiter(){
		
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
			HauptController.hauptfenster.setInfoBox(noDS);
			tabellenwerte[0][0] = "#";
		}
		
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
		this.viewErg.setButtonName("Drucken");
		
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
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	private void drucken(){
		
		String[][] tabwerte = new String[this.tabellenwerte.length][this.tabellenwerte[0].length];
		
		for( int i = 0; i < this.tabellenwerte.length; i++){
			for( int j = 0; j < this.tabellenwerte[i].length; j++){
				String w = "";
				if( this.tabellenwerte[i][j] != null ) w = (String) this.tabellenwerte[i][j];
				tabwerte[i][j] = w;
			}
		}

		try {
			//CSVExport.exportCSV(tabwerte, this.tabellenspalten);
			PDFDruck.generateTablePDF(tabwerte, this.headline, this.tabellenspalten);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	
	private void generiereJahresuebersichtenZuBereich(UebersichtSchnittstellenKlasse uebersichtSST){
		
		try{
			//Maximale Anzahl an Zeilen bestimmen
			int maxZeilen = bestimmeMaxZeilen(uebersichtSST);
			
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
		
	}
	
	private void generiereWochenuebersichtenZuBereich(UebersichtSchnittstellenKlasse uebersichtSST){
		
		try{
			
			//Maximale Anzahl an Zeilen bestimmen
			int maxZeilen = bestimmeMaxZeilen(uebersichtSST);
			
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
	
	@Override
	public View getView() {
		return this.view;
	}

	@Override
	public void fortsetzen() {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
