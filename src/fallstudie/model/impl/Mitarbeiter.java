package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import fallstudie.model.mysql.connector.RemoteConnection;
/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht m�glich ist im Interface
 * @version 1.0 Attribute erg�nzt
 * @author 
 */
public class Mitarbeiter {
	
	private String benutzername;
	private String passwort;
	private String vorname;
	private String nachname;
	private Rolle rolle;
	private Arbeitsgruppe arbeitsgruppe;
	private Bereich bereich;
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
	
	public Mitarbeiter(String benutzername, String passwort) {
		// TODO Auto-generated method stub
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
	}

	/**
	 * Muss auch Mitarbeiter hei�en, �berladener Konstruktor
	 * wird verwendet um anhand eines Suchbegriffes einen Mitarbeiter zu finden (Volltextsuche!)
	 * @param suchBegriff
	 * @return MitarbeiterObjekt
	 */
	public Mitarbeiter(String suchBegriff) {
		// TODO Auto-generated method stub
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
	}

	/**
	 * �berladener Konstruktor #2, wird verwendet wenn ein Mitarbeiter neu angelegt wird
	 * @param benutzername
	 * @param passwort
	 * @param vorname
	 * @param nachname
	 * @param rolle
	 * @param bereich
	 */
	
	public Mitarbeiter(String benutzername, String passwort,
			String vorname, String nachname, Rolle rolle, Bereich bereich) {
		// TODO Auto-generated method stub
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
	}
	
	/**
	 * �berladener Konstruktor #3, wird verwendet wenn ein Mitarbeiter neu angelegt wird
	 * @param benutzername
	 * @param passwort
	 * @param vorname
	 * @param nachname
	 * @param rolle
	 * @param arbeitsgruppe
	 */
	public Mitarbeiter(String benutzername, String passwort,
			String vorname, String nachname, Rolle rolle, Arbeitsgruppe arbeitsgruppe) {
		// TODO Auto-generated method stub
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
	}
	
	/**
	 * �berladener Konstruktor #4, wird verwendet wenn ein Mitarbeiter neu angelegt wird
	 * @param resultSet
	 */
	public Mitarbeiter(ResultSet resultSet){
		// TODO Auto-generated method stub
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		try
		{
			// Obtain the number of columns in the returned table
			@SuppressWarnings("unused")
			int columnCount = resultSet.getMetaData().getColumnCount();
			//Nachname
			this.nachname = resultSet.getString("Nachname");
			
			//Vorname
			this.vorname = resultSet.getString("Vorname");
			
			//Benutzername
			this.benutzername = resultSet.getString("Benutzername");
			//Passwort
			this.passwort = resultSet.getString("Passwort");
			//Letzter Login
			this.letzterLogin = resultSet.getString("LetzterLogin");
			//Rolle
			String Rolle = resultSet.getString("Rollenbezeichnung");
			this.rolle = new Rolle(Rolle);
			//Arbeitsgruppe Konstruktor anpassen?? F�r int ??? @philipp
			int Arbeitsgruppe = resultSet.getInt("ArbeitsgruppeID");
			this.arbeitsgruppe = new Arbeitsgruppe(Arbeitsgruppe);
			//Bereich
			int Bereich = resultSet.getInt("BereichID");
			this.bereich = new Bereich(Bereich);
		}
	catch (SQLException e)
	{
		System.err.println(e.getErrorCode());
		System.err.println(e.getMessage());
		System.err.println(e.getCause());
	}
	}
	
	
	//-----------------------------------------------------------
	//---------------------Methoden-------------------------
	//-----------------------------------------------------------
	
	
	/**
	 * Mitarbeiter einloggen und pr�fen ob er schon mal angemeldet war
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
		// LastLogin wird gesetzt
		return false;
	}

	/**
	 * wei�t den Mitarbeiter einem Bereich zu
	 * @param bereich
	 * @return
	 */
	public boolean setBereich(Bereich bereich) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * bekommt den Bereich des Mitarbeiters
	 * @return
	 */
	public Bereich getBereich() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * wei�t Mitarbeiter Rolle zu
	 * @param rolle
	 * @return
	 */
	public boolean setRolle(Rolle rolle) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * bekommt die Rolle des Mitarbeiters
	 * @return
	 */
	public Rolle getRolle() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * wei�t dem Mitarbeiter Benutzername zu
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
	 * Methode pr�ft ob eingegebenes Passwort bei Passwort vergessen View,
	 * das ist welches in der Datenbank abgelegt ist. Dient zur Identifizierung des Mitarbeiters
	 * @param altesPasswort
	 * @return boolean
	 */
	public boolean checkPasswort(String altesPasswort)
	{
		return aktiv;
		//Passwort verschl�sseln und in der Datenbank abfragen
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
	 * ver�ndert Passwort des Mitarbeiters
	 * @param newPasswort
	 * @return
	 */
	public boolean setPasswort(String newPasswort) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * wei�t Arbeitsgruppe zu
	 * @param arbeitsgruppe
	 * @return
	 */
	public boolean setArbeitsgruppe(Arbeitsgruppe arbeitsgruppe) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * bekommt die Arbeitsgruppe
	 * @return
	 */
	public Arbeitsgruppe getArbeitsgruppe() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * l�scht Mitarbeiter
	 * @param aktiv
	 * @return
	 */
	public boolean loesche(boolean aktiv) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * schaut ob der Mitarbeiter aktiv ist, im Sinne von nicht gel�scht
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
	 * wei�t Nachnamen zu
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
	 * wei�t den Vornamen zu
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
	 * schaut ob der Mitarbeiter das ben�tigte Recht hat
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