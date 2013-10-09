package fallstudie.model.impl;

import fallstudie.model.interfaces.*;
/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @author 
 */
public class MitarbeiterImpl {
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	/**
	 * Konstruktor wenn sich Mitarbeiter einloggt
	 * @param Benutzername
	 * @return Objekt Mitarbeiter
	 */
	
	public MitarbeiterImpl(String benutzername, String passwort) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Muss auch Mitarbeiter heißen, Überladener Konstruktor
	 * wird verwendet um anhand eines Suchbegriffes einen Mitarbeiter zu finden (Volltextsuche!)
	 * @param suchBegriff
	 * @return MitarbeiterObjekt
	 */
	public MitarbeiterImpl(String suchBegriff) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Überladener Konstruktor #2 , wird verwendet wenn ein Mitarbeiter neu angelegt wird.
	 * @param Benutzername
	 * @param Passwort
	 * @param Vorname
	 * @param Nachname
	 * @param Rolle
	 * @return MitarbeiterObjekt
	 */
	
	public MitarbeiterImpl(String benutzername, String passwort,
			String vorname, String nachname, Rolle tolle) {
		// TODO Auto-generated method stub
		
	}

	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	public boolean einloggen(String benutzername, String passwort) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean ausloggen() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean setBereich(Bereich bereich) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public Bereich getBereich() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean setRolle(Rolle rolle) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public Rolle getRolle() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean setBenutzername(String newBenutzername) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public String getBenutzername() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getPasswort() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean setPasswort(String newPasswort) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean setArbeitsgruppe(Arbeitsgruppe arbeitsgruppe) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public Arbeitsgruppe getArbeitsgruppe() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean setAktiv(boolean aktiv) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public int getAktiv() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getNachname() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setNachname(String nachname) {
		// TODO Auto-generated method stub
		
	}

	
	public String getVorname() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setVorname(String vorname) {
		// TODO Auto-generated method stub
		
	}

	
	public String getLogin() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean checkRecht(String recht) {
		// TODO Auto-generated method stub
		return false;
	}





	
}
