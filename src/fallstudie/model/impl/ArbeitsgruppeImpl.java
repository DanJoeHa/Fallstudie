package fallstudie.model.impl;

import java.util.Collection;
/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @author 
 */
public class ArbeitsgruppeImpl {


	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	/**
	 * Konstruktor wenn nur die Kurzbezeichnung übergeben wird, alles andere wird in der Datenbank geholt mit SELECT
	 * @param kurzbezeichnung
	 * @return 
	 * @return
	 */
	public ArbeitsgruppeImpl(String kurzbezeichnung, String Dummy) {
		// TODO Auto-generated method stub

	}

	/**
	 * Neue Arbeitsgruppe anlegen Konstruktor, alle Parameter werden von GUI übergeben	
	 * @param kurzbezeichnung
	 * @param beschreibung
	 * @param bereich
	 * @param mitarbeiter
	 * @return 
	 * @return
	 */
	public ArbeitsgruppeImpl(String kurzbezeichnung, String beschreibung,
			BereichImpl bereich, MitarbeiterImpl mitarbeiter) {
		// TODO Auto-generated method stub

	}

	/**
	 * Arbeitsgruppe Suchen Konstruktor	in Suchenmaske
	 * @param suchbegriff
	 * @return 
	 * @return
	 */
	public ArbeitsgruppeImpl(String suchbegriff) {
		// TODO Auto-generated method stub

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

	
	public boolean setKurzbezeichnung(String kurzbezeichnung) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public String getKurzbezeichnung() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean setBereich(BereichImpl bereich) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public BereichImpl getBereich() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean setLeiter(MitarbeiterImpl mitarbeiter) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public MitarbeiterImpl getLeiter() {
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

	
	public Collection<ArbeitsgruppeImpl> getAlleArbeitsgruppen() {
		// TODO Auto-generated method stub
		return null;
	}

}
