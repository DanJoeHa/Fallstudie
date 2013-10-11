package fallstudie.model.impl;

import java.sql.ResultSet;
import java.util.Collection;
/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @version 1.0 Attribute aktualisiert
 * @author Phil, 11.10.2013, aktualisiert
 * @version 1.1
 */
public class BereichImpl {

	private String beschreibung;
	private String kurzbezeichnung;
	private boolean aktiv;
	private MitarbeiterImpl leiter;
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	/**
	 * Konstruktor beim Anlegen eines neuen Bereichs (fehlende Parameter werden NULL gesetzt)
	 * abfangen im programm selber
	 * @param BereichID
	 * @return 
	 * @return
	 */
	
	public BereichImpl(String kurzbezeichnung, String beschreibung,
			MitarbeiterImpl leiter) {
		// TODO Auto-generated method stub

	}


	/**
	 * Bereichrückgabe bei Suche von Bereichen	
	 * @param suchbegriff
	 * @return 
	 * @return
	 */
		
	public BereichImpl(String suchbegriff) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * findet anhand der BereichsID den Bereich
	 * @param bereichID
	 */

	public BereichImpl(int bereichID) {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Bereich wird anhand eines Resultsets erstellt
	 * @param resultSet
	 */
	public BereichImpl(ResultSet resultSet)
	{
		
	}
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	/**
	 * Beschreibung eines bereichs ändern
	 * @param beschreibung
	 * @return
	 */
	
	public boolean setBeschreibung(String beschreibung) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Beschreibung eines Bereichs erhalten
	 * @return
	 */
	public String getBeschreibung() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Kurzbezeichnung wird geändert
	 * @param kurzbezeichnung
	 * @return
	 */
	public boolean setKurzbezeichnung(String kurzbezeichnung) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Kurzbezeichnung wird erhalten von Bereich
	 * @return
	 */
	public String getKurzbezeichnung() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Löschen von einem Bereich, mus abgefangen werden
	 * ob irgendwo in einer Arbeitsgruppe noch ein
	 * Bereich zugeordnet ist
	 * @return
	 */
	public boolean loeschen() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Status erhalten
	 * @return
	 */
	public boolean getAktiv() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Id anhand von Kurzbezeichnung kriegen (PK)
	 * @param kurzbezeichnung
	 * @return
	 */
	public int getID(String kurzbezeichnung) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Alle Bereiche werden ausgegeben
	 * wird gebraucht bei Combobox
	 * @return
	 */
	
	public Collection<BereichImpl> getAlleBereiche() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Leiter eines Bereichs setzen
	 * @param Mitarbeiter
	 * @return
	 */
	
	public boolean setLeiter(MitarbeiterImpl Mitarbeiter) {
		// TODO Auto-generated method stub
		return false;
	}


	/**
	 * Leiter eines Bereichs bekommen
	 * @return
	 */
	public MitarbeiterImpl getMitarbeiter() {
		// TODO Auto-generated method stub
		return null;
	}

}
