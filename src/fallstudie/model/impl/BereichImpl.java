package fallstudie.model.impl;

import java.util.Collection;
/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @version 1.0 Attribute aktualisiert
 * @author 
 */
public class BereichImpl {

	private String beschreibung;
	private String kurzbezeichnung;
	private boolean aktiv;
	private MitarbeiterImpl Mitarbeiter;
	
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


	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	public boolean setBeschreibung(String beschreibung) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public String getBeschreibung() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean setKurzbeschreibung(String kurzbezeichnung) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public String getKurzbezeichnung() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean setAktiv(boolean aktiv) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean getAktiv() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public int getID(String kurzbezeichnung) {
		// TODO Auto-generated method stub
		return 0;
	}


	
	public Collection<BereichImpl> getAlleBereiche() {
		// TODO Auto-generated method stub
		return null;
	}


	
	public boolean setLeiter(MitarbeiterImpl Mitarbeiter) {
		// TODO Auto-generated method stub
		return false;
	}


	
	public MitarbeiterImpl getMitarbeiter() {
		// TODO Auto-generated method stub
		return null;
	}

}
