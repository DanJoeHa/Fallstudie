package fallstudie.model.interfaces;

import fallstudie.model.impl.*;
import java.util.Collection;

public interface Wochenuebersicht {

	/**
	 * Konstruktor beim auslesen von Wochenuebersichten einer arbeitsgruppe
	 * @param kalenderjahr
	 * @param kalenderwoche
	 * @param Arbeitsgruppe
	 * @return 
	 * @return
	 */
		public void WochenuebersichtImpl(int kalenderjahr, int kalenderwoche,
									Arbeitsgruppe Arbeitsgruppe);
		
	/**
	 * 	Konstruktor beim auslesen von Wochenuebersichten eines Bereichs
	 * @param kalenderjahr
	 * @param kalenderwoche
	 * @param Bereich
	 * @return
	 */
		public void WochenuebersichtImpl(int kalenderjahr, int kalenderwoche,
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
		public BereichImpl getBereich();
		
	/**
	 * Arbeitsgruppe holen bei einem Bereichsobjekt
	 * @return
	 */
		public ArbeitsgruppeImpl getArbeitsgruppe();
	/**
	 * Methode liefert alle Wochenübersichten für die Bereichsleiterübersicht	
	 * @return
	 */
		public Collection<WochenuebersichtImpl> getAlleWocheneuebersichten();
}