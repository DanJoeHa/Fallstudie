package fallstudie.model.interfaces;

public interface Jahresuebersicht {
	
	
	/**
	 * Konstruktor beim auslesen von Jahresübersichten einer bestimmten arbeitsgruppe
	 * @param kalenderjahr
	 * @param Arbeitsgruppe
	 * @return
	 */
		public Jahresuebersicht Jahresuebersicht(int kalenderjahr, Arbeitsgruppe Arbeitsgruppe);
		
		
	/**
	 * Konstruktor beim auslesen von Jahresübersichten eines ganzen Bereichs	
	 * @param kalenderjahr
	 * @param Bereich
	 * @return
	 */
		public Jahresuebersicht Jahresuebersicht (int kalenderjahr, Bereich Bereich);
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
		public Bereich getBereich();
		
	/**
	 * Arbeitsgruppe holen bei Bereichsobjekt
	 * @return
	 */
		public Arbeitsgruppe getArbeitsgruppe();
		



}
