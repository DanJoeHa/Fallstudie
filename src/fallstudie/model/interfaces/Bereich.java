package fallstudie.model.interfaces;

import java.util.Collection;
import fallstudie.model.impl.*;

public interface Bereich {

	/**
	 * Konstruktor beim Anlegen eines neuen Bereichs (fehlende Parameter werden NULL gesetzt)
	 * abfangen im programm selber
	 * @param BereichID
	 * @return 
	 * @return
	 */
		public void BereichImpl(String kurzbezeichnung, String beschreibung, MitarbeiterImpl leiter);
	
	/**
	 * Bereichrückgabe bei Suche von Bereichen	
	 * @param suchbegriff
	 * @return 
	 * @return
	 */
		
		public void BereichImpl(String suchbegriff);
		
	/**
	 * Rückgabe aller verfügbaren Bereiche bei Suche/Dropdown
	 * @return
	 */
		
		public Collection <BereichImpl> getAlleBereiche();
		
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
		public boolean setLeiter(MitarbeiterImpl Mitarbeiter);
		
	/**
	 * Mitarbeiter bekommen
	 * @return
	 */
		public MitarbeiterImpl getMitarbeiter();
		
	/**
	 * Holt anhand der Kurzbezeichnung eines Bereichs die dazugehörige ID (PK in Datenbank)	
	 * @param kurzbezeichnung
	 * @return
	 */
		public int getID (String kurzbezeichnung);
}
