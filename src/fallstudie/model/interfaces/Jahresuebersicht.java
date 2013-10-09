package fallstudie.model.interfaces;

public interface Jahresuebersicht {

	/**
	 * Konstruktor
	 * @param kalenderjahr
	 * @param Arbeitsgruppe
	 * @param Bereich
	 * @return
	 */
		public Jahresuebersicht Jahresuebersicht(int kalenderjahr, Arbeitsgruppe Arbeitsgruppe, Bereich Bereich);
		
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
		
	/**
	 * Uebersicht 
	 * @param schriftwechselsumme
	 * @param erstattungensumme
	 * @return
	 */
		public boolean insertUebersicht(int schriftwechselsumme, int erstattungensumme);


}
