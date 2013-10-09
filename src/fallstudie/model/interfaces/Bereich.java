package fallstudie.model.interfaces;

import java.util.Collection;

public interface Bereich {

	/**
	 * Konstruktor beim Anlegen eines neuen Bereichs (fehlende Parameter werden NULL gesetzt)
	 * abfangen im programm selber
	 * @param BereichID
	 * @return
	 */
		public Bereich Bereich(String kurzbezeichnung, String beschreibung, Mitarbeiter leiter);
	
	/**
	 * Bereichrückgabe bei Suche von Bereichen	
	 * @param suchbegriff
	 * @return
	 */
		
		public Bereich Bereich1 (String suchbegriff);
		
	/**
	 * Rückgabe aller verfügbaren Bereiche bei Suche/Dropdown
	 * @return
	 */
		
		public Collection <Bereich> Bereich2();
		
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
		
	/**
	 * Holt anhand der Kurzbezeichnung eines Bereichs die dazugehörige ID (PK in Datenbank)	
	 * @param kurzbezeichnung
	 * @return
	 */
		public int getID (String kurzbezeichnung);
}
