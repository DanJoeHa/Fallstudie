package fallstudie.model.interfaces;

import java.sql.ResultSet;
import java.util.Collection;

public interface Wochenuebersicht {

	/**
	 * Konstruktor beim auslesen von Wochenuebersichten einer arbeitsgruppe
	 * @param kalenderjahr
	 * @param kalenderwoche
	 * @param Arbeitsgruppe
	 * @return
	 */
		public Wochenuebersicht Wochenuebersicht(int kalenderjahr, int kalenderwoche,
									Arbeitsgruppe Arbeitsgruppe);
		
	/**
	 * 	Konstruktor beim auslesen von Wochenuebersichten eines Bereichs
	 * @param kalenderjahr
	 * @param kalenderwoche
	 * @param Bereich
	 * @return
	 */
		public Wochenuebersicht Wochenuebersicht(int kalenderjahr, int kalenderwoche,
												Bereich Bereich);
	/**
	 * Erstattungensumme holen (je nach Bereichs oder arbeitsgruppenobjekt)
	 * @return
	 */
		public int getErstattungenSumme();
		
	/**
	 * Schriftwechselsumme holen (je nach Bereichs oder arbeitsgruppenobjekt)
	 * @return
	 */
		public int getSchriftwechselSumme();
		
	/**
	 * Kalenderjahr holen 
	 * @return
	 */
		public int getKalenderjahr();
		
	/**
	 * Kalenderwoche holen 
	 * @return
	 */
		public int getKalenderwoche();
		
	/** 
	 * Bereich holen Beim Arbeitsgruppenobjekt
	 * @return
	 */
		public Bereich getBereich();
		
	/**
	 * Arbeitsgruppe holen bei einem Bereichsobjekt
	 * @return
	 */
		public Arbeitsgruppe getArbeitsgruppe();
	/**
	 * Methode liefert alle Wochenübersichten für die Bereichsleiterübersicht	
	 * @return
	 */
		public Collection<Wochenuebersicht> getAlleWocheneuebersichten();
}