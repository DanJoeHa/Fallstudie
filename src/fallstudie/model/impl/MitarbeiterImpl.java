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
	
	public MitarbeiterImpl(ResultSet resultSet){
		// TODO Auto-generated method stub
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
	}
	/**
	 * holt aus dem Primärschlüssen einen Mitarbeiter aus der Datenbank
	 * @param leiterID
	 */
	
	//-----------------------------------------------------------
	//---------------------Methoden-------------------------
	//-----------------------------------------------------------
	
	

	public static Mitarbeiter einloggen(String benutzername, String passwort) {
		// TODO Auto-generated method stub
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		return null;
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

	
	public boolean loesche(boolean aktiv) {
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

	
	public boolean setNachname(String nachname) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public String getVorname() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean setVorname(String vorname) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public String getLogin() {
		// TODO Auto-generated method stub
		return null;
	}

	
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
