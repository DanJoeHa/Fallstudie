package fallstudie.model.impl;

import java.sql.ResultSet;
import java.util.Collection;

import fallstudie.model.interfaces.*;
import fallstudie.model.mysql.connector.RemoteConnection;
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
	private RolleImpl rolle;
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
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
	}

	/**
	 * Muss auch Mitarbeiter heißen, Überladener Konstruktor
	 * wird verwendet um anhand eines Suchbegriffes einen Mitarbeiter zu finden (Volltextsuche!)
	 * @param suchBegriff
	 * @return MitarbeiterObjekt
	 */
	public MitarbeiterImpl(String suchBegriff) {
		// TODO Auto-generated method stub
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
	}

	/**
	 * Überladener Konstruktor #2, wird verwendet wenn ein Mitarbeiter neu angelegt wird
	 * @param benutzername
	 * @param passwort
	 * @param vorname
	 * @param nachname
	 * @param rolle
	 * @param bereich
	 */
	
	public MitarbeiterImpl(String benutzername, String passwort,
			String vorname, String nachname, RolleImpl rolle, BereichImpl bereich) {
		// TODO Auto-generated method stub
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
	}
	
	/**
	 * Überladener Konstruktor #3, wird verwendet wenn ein Mitarbeiter neu angelegt wird
	 * @param benutzername
	 * @param passwort
	 * @param vorname
	 * @param nachname
	 * @param rolle
	 * @param arbeitsgruppe
	 */
	public MitarbeiterImpl(String benutzername, String passwort,
			String vorname, String nachname, RolleImpl rolle, ArbeitsgruppeImpl arbeitsgruppe) {
		// TODO Auto-generated method stub
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
	}
	
	/**
	 * Überladener Konstruktor #4, wird verwendet wenn ein Mitarbeiter neu angelegt wird
	 * @param resultSet
	 */
	public MitarbeiterImpl(ResultSet resultSet){
		// TODO Auto-generated method stub
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
	}
	
	
	//-----------------------------------------------------------
	//---------------------Methoden-------------------------
	//-----------------------------------------------------------
	
	
	/**
	 * Mitarbeiter einloggen und prüfen ob er schon mal angemeldet war
	 * @param benutzername
	 * @param passwort
	 * @return
	 */
	public static Mitarbeiter einloggen(String benutzername, String passwort) {
		// TODO Auto-generated method stub
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		return null;
	}

	/**
	 * loggt den Benutzer aus
	 * @return
	 */
	public boolean ausloggen() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * weißt den Mitarbeiter einem Bereich zu
	 * @param bereich
	 * @return
	 */
	public boolean setBereich(BereichImpl bereich) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * bekommt den Bereich des Mitarbeiters
	 * @return
	 */
	public BereichImpl getBereich() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * weißt Mitarbeiter Rolle zu
	 * @param rolle
	 * @return
	 */
	public boolean setRolle(RolleImpl rolle) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * bekommt die Rolle des Mitarbeiters
	 * @return
	 */
	public RolleImpl getRolle() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * weißt dem Mitarbeiter Benutzername zu
	 * @param newBenutzername
	 * @return
	 */
	public boolean setBenutzername(String newBenutzername) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * bekommt den Benutzername des Mitarbeiters
	 * @return
	 */
	public String getBenutzername() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * bekommt das Passwort des Mitarbeiters
	 * @return
	 */
	public String getPasswort() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * verändert Passwort des Mitarbeiters
	 * @param newPasswort
	 * @return
	 */
	public boolean setPasswort(String newPasswort) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * weißt Arbeitsgruppe zu
	 * @param arbeitsgruppe
	 * @return
	 */
	public boolean setArbeitsgruppe(ArbeitsgruppeImpl arbeitsgruppe) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * bekommt die Arbeitsgruppe
	 * @return
	 */
	public ArbeitsgruppeImpl getArbeitsgruppe() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * löscht Mitarbeiter
	 * @param aktiv
	 * @return
	 */
	public boolean loesche(boolean aktiv) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * schaut ob der Mitarbeiter aktiv ist, im Sinne von nicht gelöscht
	 * @return
	 */
	public int getAktiv() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * holt den Nachnamen
	 * @return
	 */
	public String getNachname() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * weißt Nachnamen zu
	 * @param nachname
	 * @return
	 */
	public boolean setNachname(String nachname) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * holt den Vornamen
	 * @return
	 */
	public String getVorname() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * weißt den Vornamen zu
	 * @param vorname
	 * @return
	 */
	public boolean setVorname(String vorname) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * holt den letzten Login
	 * @return
	 */
	public String getLogin() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * schaut ob der Mitarbeiter das benötigte Recht hat
	 * @param recht
	 * @return
	 */
	public boolean checkRecht(String recht) {
		// TODO Auto-generated method stub
		return false;
	}


	public static Collection<Mitarbeiter> suche(String suchbegriff){
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		return null;
	}


	
}
