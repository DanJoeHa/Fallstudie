package fallstudie.model.interfaces;
import fallstudie.model.impl.*;
public interface Jahresuebersicht {
	
	
	/**
	 * Konstruktor beim auslesen von Jahresübersichten einer bestimmten arbeitsgruppe
	 * @param kalenderjahr
	 * @param Arbeitsgruppe
	 * @return 
	 * @return
	 */
		public void JahresuebersichtImpl(int kalenderjahr, ArbeitsgruppeImpl Arbeitsgruppe);
		
		
	/**
	 * Konstruktor beim auslesen von Jahresübersichten eines ganzen Bereichs	
	 * @param kalenderjahr
	 * @param Bereich
	 * @return 
	 * @return
	 */
		public void JahresuebersichtImpl(int kalenderjahr, BereichImpl Bereich);
	/**
	 * Erstattungensumme holen (je nach Objekt geht es dabei um die arbeitsgruppe oder den Bereich)
	 * @return
	 */
		public int getErstattungenSumme();
		
	/**
	 * Schriftwechselsumme holen (je nach Objekt geht es dabei um die arbeitsgruppe oder den Bereich)
	 * @return
	 */
		public int getSchriftwechselSumme();
		
	/**
	 * Kalenderjahr holen (je nach Objekt geht es dabei um die arbeitsgruppe oder den Bereich)
	 * @return
	 */
		public int getKalenderjahr();
		
	/**
	 * Bereich holen bei Arbeitsgruppenobjekt
	 * @return
	 */
		public BereichImpl getBereich();
		
	/**
	 * Arbeitsgruppe holen bei Bereichsobjekt
	 * @return
	 */
		public ArbeitsgruppeImpl getArbeitsgruppe();
		



}
