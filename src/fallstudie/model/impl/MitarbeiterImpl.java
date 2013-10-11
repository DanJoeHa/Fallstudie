package fallstudie.model.impl;

import fallstudie.model.interfaces.*;
/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @version 1.0 Attribute ergänzt
 * @author 
 */
public class MitarbeiterImpl {
	
	private String benutzername;
	private String passwort;
	private String vorname;
	private String nachname;
	private ArbeitsgruppeImpl arbeitsgruppe;
	private BereichImpl bereich;
	private String letzterLogin;
	private boolean aktiv;
	
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
			String vorname, String nachname, RolleImpl tolle) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * holt aus dem Primärschlüssen einen Mitarbeiter aus der Datenbank
	 * @param leiterID
	 */
	public MitarbeiterImpl(int leiterID) {
		
	}
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	

	public static boolean einloggen(String benutzername, String passwort) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean ausloggen() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean setBereich(BereichImpl bereich) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public BereichImpl getBereich() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean setRolle(RolleImpl rolle) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public RolleImpl getRolle() {
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

	
	public boolean setArbeitsgruppe(ArbeitsgruppeImpl arbeitsgruppe) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public ArbeitsgruppeImpl getArbeitsgruppe() {
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
