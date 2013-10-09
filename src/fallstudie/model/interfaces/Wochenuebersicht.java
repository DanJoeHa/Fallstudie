package fallstudie.model.interfaces;

public interface Wochenuebersicht {

	/**
	 * Konstruktor
	 * @param kalenderjahr
	 * @param Arbeitsgruppe
	 * @param Bereich
	 * @return
	 */
		public Wochenuebersicht Wochenuebersicht(int kalenderjahr, int kalenderwoche, Arbeitsgruppe Arbeitsgruppe, Bereich Bereich);
		
	/**
	 * Erstattungensumme holen
	 * @return
	 */
		public int getErstattungenSumme();
		
	/**
	 * Schriftwechselsumme holen
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
		
	/*
	 * Bereich holen
	 * @return
	 */
		public Bereich getBereich();
		
	/**
	 * Arbeitsgruppe holen
	 * @return
	 */
		public Arbeitsgruppe getArbeitsgruppe();
}