package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import fallstudie.controller.interfaces.Controller;
import fallstudie.exportieren.PDFDruck;
import fallstudie.model.impl.Bereich;
import fallstudie.model.impl.Jahresuebersicht;
import fallstudie.model.impl.Wochenuebersicht;
import fallstudie.model.impl.Zeile;
import fallstudie.view.impl.DatenAnzeigenAuswahlView;
import fallstudie.view.impl.HilfeTexte;
import fallstudie.view.impl.TabelleView;
import fallstudie.view.interfaces.View;

public class DatenAnzeigenController implements Controller {
	
	private DatenAnzeigenAuswahlView view;
	private TabelleView viewErg;
	
	private String headline;
	private String[] tabellenspalten = new String[1];
	private Object[][] tabellenwerte = new Object[1][1];
	private String[][] aArtPos;
	private int artName = 0;
	private int artZeile = 1;
	private int kw, jahr, sumcol;
	private Collection<Bereich> bereiche;
	
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
	 * ActionListener
	 * 
	 * @author Johannes
	 * @version 1.0
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
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
				kw = this.view.getWoche();
				jahr = this.view.getJahr();
			
			
				//Jahresübersicht
				if( kw == 0 ){
					
					this.headline = "Daten anzeigen Jahr " + jahr;
					
					//Jahresübersicht Zentralbereichsleiter/Fachbereichsorganisation
					if( HauptController.activeUser.checkRecht("Lesen alle Bereiche Jahr") ){
						this.uebersichtJahrZentralbereichsleiter();
						HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_SummierteErgebnisseGesamtbereich);
					}
					
					//Jahresübersicht Bereichsleiter
					if( HauptController.activeUser.checkRecht("Lesen alle Arbeitsgruppen eines Bereichs Jahr") ){
						Collection<Jahresuebersicht> coJahresuebersichten = Jahresuebersicht.getAlleJahresuebersichtenZumBereich( jahr, HauptController.activeUser.getBereich() );
						
						this.generiereJahresuebersichtenZuBereich(coJahresuebersichten);
						HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_SummierteErgebnisseGesamtbereich_AG);
					}
					
					//Jahresübersicht Gruppenleiter
					if( HauptController.activeUser.checkRecht("Lesen eigene Arbeitsgruppe Jahr") ){
						this.uebersichtJahrGruppenleiter();
						HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_Arbeitsgruppeeinsehen_Gruppenleiter);
					}
					
				}else{
				//Kalenderwochenübersicht
					
					this.headline = "Daten anzeigen Jahr " + jahr + "KW " + kw + "/" + jahr;
					
					//Kalenderwochenübersicht Zentralbereichsleiter/Fachbereichsorganisation
					if( HauptController.activeUser.checkRecht("Lesen alle Bereiche KW") ){
						this.uebersichtWocheZentralbereichsleiter();
						HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_SummierteErgebnisseGesamtbereich);
					}
					
					//Kalenderwochenübersicht Bereichsleiter
					if( HauptController.activeUser.checkRecht("Lesen alle Arbeitsgruppen eines Bereichs KW") ){
						Collection<Wochenuebersicht> coWochenuebersichten = Wochenuebersicht.getAlleWochenuebersichtenZumBereich( jahr, kw, HauptController.activeUser.getBereich() );
						
						this.generiereWochenuebersichtenZuBereich(coWochenuebersichten);
						HauptController.hilfefenster.setHinweis(HilfeTexte.Tabelle_SummierteErgebnisseGesamtbereich_AG);
					}
					
					//Kalenderwochenübersicht Gruppenleiter
					if( HauptController.activeUser.checkRecht("Lesen eigene Arbeitsgruppe KW") ){
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
				HauptController.hauptfenster.setInfoBox("Bitte ausschließlich Ziffern eintragen");
			}
		}
		
		//Daten ausdrucken
		if( button.equals("Drucken") ) this.drucken();
		
		//Drill-Down in einen Bereich
		if( button.equals("DrillDown") ) this.drilldown();
		
		//Abbrechen-Button
		if( button.equals("Abbrechen") ){
			HauptController.hauptfenster.zurueck();
			HauptController.hauptfenster.setUeberschrift("Daten anzeigen");
			HauptController.hilfefenster.setHinweis(HilfeTexte.DatenAnzeigenAuswahlView);
		}

	}
	
	/**
	 * Generierte Jahresübersicht für den Zentralbereichsleiter/Fachbereichsorganisation
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	private void uebersichtJahrZentralbereichsleiter(){
		
		Collection<Jahresuebersicht> coJahresuebersichten = Jahresuebersicht.getAlleJahresuebersichtenZuAllenBereichen( jahr );
		
		try{
			
			//bestimme max. Anzahl an Zeilen anhand der gespeicherten Arten
			Iterator<Jahresuebersicht> itJahre = coJahresuebersichten.iterator();
			int maxZeilen = 0;
			while( itJahre.hasNext() ){
				Jahresuebersicht oJahr = itJahre.next();
				
				int anzZeilen = oJahr.getZeileBereich().size();
				if( anzZeilen > maxZeilen ) maxZeilen = anzZeilen;
				if( oJahr.getBereich() != null ) this.bereiche.add(oJahr.getBereich());
				
			}
			
			//DrillDown-Button und ComboBox anzeigen
			this.viewErg.setBereiche(Funktionen.BereicheCollection2Array(this.bereiche));
			this.viewErg.setDrillDown(true);
			
			sumcol = coJahresuebersichten.size() + 1;
			tabellenspalten = new String[ coJahresuebersichten.size() + 2 ];
			tabellenwerte = new Object[ maxZeilen ][ coJahresuebersichten.size() + 2 ];
			tabellenspalten[0] = "Art\\Bereich";
			tabellenspalten[sumcol] = "Summe";
			
			//Schleifenvars
			int spalte = 1;
			aArtPos = new String[maxZeilen][2]; 
			for(int s = 0; s < maxZeilen; s++){
				aArtPos[s][0] = "";
				aArtPos[s][1] = "";
			}
			
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
					
					//Zeile hinzufügen
					this.addZeile(itZeile, i, spalte, true);
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
			HauptController.hauptfenster.setInfoBox("Keine Datensätze gefunden.");
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
		
		Collection<Wochenuebersicht> coWochenuebersichten = Wochenuebersicht.getAlleWochenuebersichtenZuAllenBereichen(jahr, kw);
		
		try{
			
			//bestimme max. Anzahl an Zeilen anhand der gespeicherten Arten
			Iterator<Wochenuebersicht> itWoche = coWochenuebersichten.iterator();
			int maxZeilen = 0;
			while( itWoche.hasNext() ){
				Wochenuebersicht oWoche = itWoche.next();
				
				int anzZeilen = oWoche.getZeileBereich().size();
				if( anzZeilen > maxZeilen ) maxZeilen = anzZeilen;
				if( oWoche.getBereich() != null ) this.bereiche.add(oWoche.getBereich());
			}
			
			//DrillDown-Button und ComboBox anzeigen
			this.viewErg.setBereiche(Funktionen.BereicheCollection2Array(this.bereiche));
			this.viewErg.setDrillDown(true);
			
			sumcol = coWochenuebersichten.size() + 1;
			tabellenspalten = new String[ coWochenuebersichten.size() + 2 ];
			tabellenwerte = new Object[ maxZeilen ][ coWochenuebersichten.size() + 2 ];
			tabellenspalten[0] = "Art\\Bereich";
			tabellenspalten[sumcol] = "Summe";
			
			//Schleifenvars
			int spalte = 1;
			aArtPos = new String[maxZeilen][2]; 
			for(int s = 0; s < maxZeilen; s++){
				aArtPos[s][0] = "";
				aArtPos[s][1] = "";
			}
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
					
					//Zeile hinzufügen
					this.addZeile(itZeile, i, spalte, true);
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
	
	/**
	 * Wechselt von Bereichsebene auf Arbeitsgruppenebene
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	private void drilldown(){
		
		//DrillDown ausblenden
		this.viewErg.setDrillDown(false);
		
		//Bereichsobjekt zur Auswahl finden
		Iterator<Bereich> i = this.bereiche.iterator();
		Bereich oBereich = null;
		while( i.hasNext() ){
			oBereich = i.next();
			if(oBereich.getKurzbezeichnung().equals( this.viewErg.getDrillDownBereich() ) ) break;
		}
		
		//Headline anpassen
		this.headline += " > Arbeitsgruppen des Bereich " + oBereich.getKurzbezeichnung();
		
		//Jahr
		if( kw == 0){
			
			//Daten holen
			Collection<Jahresuebersicht> coJahresuebersichten = Jahresuebersicht.getAlleJahresuebersichtenZumBereich( jahr, oBereich );
			
			//Tabelle neu generieren
			this.generiereJahresuebersichtenZuBereich(coJahresuebersichten);

		//Woche
		}else{
			
			//Daten holen
			Collection<Wochenuebersicht> coWochenuebersichten = Wochenuebersicht.getAlleWochenuebersichtenZumBereich( jahr, kw, oBereich );
			
			//Tabelle neu generieren
			this.generiereWochenuebersichtenZuBereich(coWochenuebersichten);

		}
		
		//an TabelleView übergeben
		HauptController.hauptfenster.setUeberschrift( this.headline );
		this.viewErg.setTabelle(tabellenspalten, tabellenwerte);
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
	
	private void generiereJahresuebersichtenZuBereich(Collection<Jahresuebersicht> coJahresuebersichten){
		
		try{
			
			//bestimme max. Anzahl an Zeilen anhand der gespeicherten Arten
			Iterator<Jahresuebersicht> itJahre = coJahresuebersichten.iterator();
			int maxZeilen = 0;
			while( itJahre.hasNext() ){
				Jahresuebersicht oJahr = itJahre.next();
				
				int anzZeilen = oJahr.getZeileBereich().size();
				if( anzZeilen > maxZeilen ) maxZeilen = anzZeilen;
				if( oJahr.getBereich() != null ) this.bereiche.add(oJahr.getBereich());
				
			}
			
			
			sumcol = coJahresuebersichten.size() + 1;
			tabellenspalten = new String[ coJahresuebersichten.size() + 2 ];
			tabellenwerte = new Object[ maxZeilen ][ coJahresuebersichten.size() + 2 ];
			tabellenspalten[0] = "Art\\Arbeitsgruppe";
			tabellenspalten[sumcol] = "Summe";
			
			//Schleifenvars
			int spalte = 1;
			aArtPos = new String[maxZeilen][2]; 
			for(int s = 0; s < maxZeilen; s++){
				aArtPos[s][0] = "";
				aArtPos[s][1] = "";
			}

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
				
				//alle Zeilen durchlaufen
				while( itZeile.hasNext() ){
					
					//Zeile hinzufügen
					this.addZeile(itZeile, i, spalte, true);
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
	
	private void generiereWochenuebersichtenZuBereich(Collection<Wochenuebersicht> coWochenuebersichten){
		
		try{
			
			//bestimme max. Anzahl an Zeilen anhand der gespeicherten Arten
			Iterator<Wochenuebersicht> itWoche = coWochenuebersichten.iterator();
			int maxZeilen = 0;
			while( itWoche.hasNext() ){
				Wochenuebersicht oJahr = itWoche.next();
				
				int anzZeilen = oJahr.getZeileBereich().size();
				if( anzZeilen > maxZeilen ) maxZeilen = anzZeilen;
				if( oJahr.getBereich() != null ) this.bereiche.add(oJahr.getBereich());
			}
			
			sumcol = coWochenuebersichten.size() + 1;
			tabellenspalten = new String[ coWochenuebersichten.size() + 2 ];
			tabellenwerte = new Object[ maxZeilen ][ coWochenuebersichten.size() + 2 ];
			tabellenspalten[0] = "Art\\Arbeitsgruppe";
			tabellenspalten[sumcol] = "Summe";
			
			//Schleifenvars
			int spalte = 1;
			aArtPos = new String[maxZeilen][2]; 
			for(int s = 0; s < maxZeilen; s++){
				aArtPos[s][0] = "";
				aArtPos[s][1] = "";
			}
			
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
					
					//Zeile hinzufügen
					this.addZeile(itZeile, i, spalte, true);
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
	
	/**
	 * Fügt dem Bericht eine Zeile hinzu
	 * 
	 * @param itZeile
	 * @param i
	 * @param spalte
	 */
	private void addZeile(Iterator<Zeile> itZeile, int i, int spalte, boolean summierung){
		
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
		
		//wenn Summierung erforderlich, Summenspalte befüllen
		if(summierung){
			int sum = 0;
			if( tabellenwerte[zeile][sumcol] != null ){				
				sum += Integer.parseInt( tabellenwerte[zeile][sumcol].toString() ) + oZeile.getSumme();
			}else{
				sum += oZeile.getSumme();
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
}
