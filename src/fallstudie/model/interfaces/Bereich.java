package fallstudie.model.interfaces;

public interface Bereich {

	/**
	 * Konstruktor
	 * @param BereichID
	 * @return
	 */
		public Bereich Bereich(int BereichID);
		
	/**
	 * Beschreibung ändern
	 * @param beschreibung
	 * @return
	 */
		public boolean setBeschreibung(String beschreibung);
	
	/**
	 * Beschreibung holen
	 * @return
	 */
		public String getBeschreibung();
	
	/**
	 * Kurzbeschreibung ändern
	 * @param kurzbezeichnung
	 * @return
	 */
		public boolean setKurzbeschreibung(String kurzbezeichnung);
		
	/**
	 * Kurzbeschreibung holen
	 * @return
	 */
		public String getKurzbezeichnung();
	
	/**
	 * Ativ setzen
	 * @param aktiv
	 * @return
	 */
		public boolean setAktiv(boolean aktiv);
	
	/**
	 * Status holen
	 * @return
	 */
		public boolean getAktiv();
	
	/**
	 * Mitarbeiter setzen
	 * @param Mitarbeiter
	 * @return
	 */
		public boolean setLeiter(Mitarbeiter Mitarbeiter);
		
	/**
	 * Mitarbeiter bekommen
	 * @return
	 */
		public Mitarbeiter getMitarbeiter();
}
