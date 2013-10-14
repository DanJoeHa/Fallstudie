package fallstudie.model.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import fallstudie.model.mysql.connector.RemoteConnection;
/** CHANGELOG
 * @author Phil, 09.10.2013
 *	 	generiert + implements (Interface) wurde entfernt, da Konstruktor nicht m�glich ist im Interface
 *		 @version 1.0 Attribute erg�nzt
 * @author Phil
 * 		@date 11.10.2013
 *		@version 1.1
 *		@change checkPasswort() eingef�gt und ausloggen kommentiert
 *@author Phil
 *		@date 13.10.2013
 *		@version 1.2
 *		@change alle Methoden implementiert
 *@author Patrick
 *@author Phil
 *	@change getFullName() hinzugefügt.
 *
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
	
	public Mitarbeiter(String benutzername) {
		
		RemoteConnection Connection = new RemoteConnection();
		try
		{
			if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
				RemoteConnection.connect();
			};
		}
		catch (NullPointerException e)
		{
			System.err.println(e.getMessage());
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		
		try
		{
			System.out.println("SELECT * FROM Mitarbeiter WHERE Benutzername ='"+benutzername+"'");
			
			ResultSet mitarbeiterResult = Connection.executeQueryStatement(
					"SELECT * FROM Mitarbeiter WHERE Benutzername ='"+benutzername+"'");
			
			mitarbeiterResult.next();
			int bereichID = mitarbeiterResult.getInt("Bereich");
			//Wenn Bereich zugeordnet wird ein Objekt erzeugt
			if (bereichID!=0)
			{
				this.bereich = new Bereich(bereichID);
			}
			else
			{
				this.bereich = null;
			}
			
			//Wenn Arbeitsgruppe zugeordnet wird ein Objekt erzeugt
			int arbeitsgruppeID = mitarbeiterResult.getInt("Arbeitsgruppe");
			
			if (arbeitsgruppeID!=0)
			{
				this.arbeitsgruppe = new Arbeitsgruppe(arbeitsgruppeID);
			}
			else
			{
				this.arbeitsgruppe = null;
			}
		//Benutzername wird zugeordnet
		this.benutzername = benutzername;
		//ROLLE
		String rolle = mitarbeiterResult.getString("Rolle");
		this.rolle = new Rolle(rolle);
		//PASSWORT
		this.passwort =  mitarbeiterResult.getString("Passwort");;
		//AKTIV
		this.aktiv =  mitarbeiterResult.getBoolean("Aktiv");;
		//Vorname
		this.vorname = mitarbeiterResult.getString("Vorname");
		//NACHNAME
		this.nachname = mitarbeiterResult.getString("Nachname");
		//Last Login
		String komplettletzterLogin = mitarbeiterResult.getString("LetzterLogin");
		if(komplettletzterLogin!=null)
		{
		String jahr = komplettletzterLogin.substring(0, 4);
		String monat = komplettletzterLogin.substring(5,7);
		String tag = komplettletzterLogin.substring(8,10);
		
		this.letzterLogin = tag+"."+monat+"."+jahr;
		}
		else
		{
			this.letzterLogin = null;
		}
		
		mitarbeiterResult.close();
		
		
		}
		catch (SQLException e)
		{   System.err.println("Dieser Fehler ist aufgetreten in Bereich (id):");
			System.err.println(e.getMessage());
			
		}
	
	}

	/**
	 * �berladener Konstruktor #2, wird verwendet wenn ein Mitarbeiter neu angelegt wird
	 * @param benutzername
	 * @param passwort
	 * @param vorname
	 * @param nachname
	 * @param rolle
	 * @param bereich
	 * @throws Exception 
	 */
	
	public Mitarbeiter(String benutzername, String passwort,
			String vorname, String nachname, Rolle rolle, Bereich bereich) throws Exception {
		
		try
		{
			if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
				RemoteConnection.connect();
			};
			
		
		//IDs und Namen herauskriegen
		String rollenName = rolle.getRollenbezeichnung();
		int bereichID = bereich.getID();
		
		System.out.println("SELECT Benutzername From Mitarbeiter");
		//Checken obs den Mitarbeiter schon gibt.
		ResultSet checkObVorhanden = RemoteConnection.sql.executeQuery(
				"SELECT Benutzername From Mitarbeiter");
		
		
		while (checkObVorhanden.next()) 
		{

				String value = checkObVorhanden.getString("Benutzername");
				
				if (benutzername.equals(value)) throw new Exception ("Mitarbeiter mit selben Benutzername existiert schon.");
				checkObVorhanden.close();
		}
		//passwort wird verschl�sselt in die DB geschrieben
		String verschluesseltPasswort = VerschluesselungSHA1.getEncodedSha1Sum(passwort);
		
		
		System.out.println("INSERT INTO Mitarbeiter (Benutzername, Passwort, Vorname, Nachname, Rolle, Bereich)" +
				"	VALUES ('"+benutzername+"','"+verschluesseltPasswort+"','"+vorname+"','"+nachname+"','"+rollenName+"''"+bereichID+"'");
		
		int affectedRows = RemoteConnection.sql.executeUpdate("INSERT INTO Mitarbeiter (Benutzername, Passwort, Vorname, Nachname, Rolle, Bereich)" +
				"	VALUES ('"+benutzername+"','"+verschluesseltPasswort+"','"+vorname+"','"+nachname+"','"+rollenName+"''"+bereichID+"'");
		
		if (affectedRows==1)System.out.println("Es wurde "+affectedRows+" Datensatz eingef�gt.");
		
		this.benutzername = benutzername;
		this.passwort = verschluesseltPasswort;
		this.vorname = vorname;
		this.nachname = nachname;
		this.rolle = rolle;
		this.bereich = bereich;
		
		}
		catch (SQLException e) {
			System.err.println("SQL Statement ist fehlerhaft!: ");
			System.err.println(e.getMessage());
			
		}
	}
	
	/**
	 * �berladener Konstruktor #3, wird verwendet wenn ein Mitarbeiter neu angelegt wird
	 * @param benutzername
	 * @param passwort
	 * @param vorname
	 * @param nachname
	 * @param rolle
	 * @param arbeitsgruppe
	 * @throws Exception 
	 */
	public Mitarbeiter(String benutzername, String passwort,
			String vorname, String nachname, Rolle rolle, Arbeitsgruppe arbeitsgruppe) throws Exception {
		try
		{
			if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
				RemoteConnection.connect();
			};
			
		
		//IDs und Namen herauskriegen
		String rollenName = rolle.getRollenbezeichnung();
		int arbeitsgruppeID = arbeitsgruppe.getID();
		
		System.out.println("SELECT Benutzername From Mitarbeiter");
		//Checken obs den Mitarbeiter schon gibt.
		ResultSet checkObVorhanden = RemoteConnection.sql.executeQuery(
				"SELECT Benutzername From Mitarbeiter");
		
		
		while (checkObVorhanden.next()) 
		{

				String value = checkObVorhanden.getString("Benutzername");
				
				if (benutzername.equals(value)) throw new Exception ("Mitarbeiter mit selben Benutzername existiert schon.");
				checkObVorhanden.close();
		}
		
		String verschluesseltPasswort = VerschluesselungSHA1.getEncodedSha1Sum(passwort);
		
		
		System.out.println("INSERT INTO Mitarbeiter (Benutzername, Passwort, Vorname, Nachname, Rolle, Arbeitsgruppe)" +
				"	VALUES ('"+benutzername+"','"+verschluesseltPasswort+"','"+vorname+"','"+nachname+"','"+rollenName+"''"+arbeitsgruppeID+"'");
		
		int affectedRows = RemoteConnection.sql.executeUpdate("INSERT INTO Mitarbeiter (Benutzername, Passwort, Vorname, Nachname, Rolle, Bereich)" +
				"	VALUES ('"+benutzername+"','"+verschluesseltPasswort+"','"+vorname+"','"+nachname+"','"+rollenName+"''"+arbeitsgruppeID+"'");
		
		if (affectedRows==1)System.out.println("Es wurde "+affectedRows+" Datensatz eingef�gt.");
		
		this.benutzername = benutzername;
		this.passwort = verschluesseltPasswort;
		this.vorname = vorname;
		this.nachname = nachname;
		this.rolle = rolle;
		this.arbeitsgruppe = arbeitsgruppe;
		
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL Statement ist fehlerhaft!");
		}
	}
	
	/**
	 * �berladener Konstruktor #4, wird verwendet wenn ein Mitarbeiter neu angelegt wird
	 * @param resultSet
	 */
	public Mitarbeiter(ResultSet resultSet){
		RemoteConnection Connection = new RemoteConnection();
		try
		{
			if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
				RemoteConnection.connect();
			}
		
		
		
		int bereichID = resultSet.getInt("Bereich");
		if (bereichID!=0)
		{
			this.bereich = new Bereich(bereichID);
		}
		else
		{
			this.bereich = null;
		}
		
		//Wenn Arbeitsgruppe zugeordnet wird ein Objekt erzeugt
		int arbeitsgruppeID = resultSet.getInt("Arbeitsgruppe");
		
		if (arbeitsgruppeID!=0)
		{
			this.arbeitsgruppe = new Arbeitsgruppe(arbeitsgruppeID);
		}
		else
		{
			this.arbeitsgruppe = null;
		}
	//Benutzername wird zugeordnet
	this.benutzername = resultSet.getString("Benutzername");
	//ROLLE
	String rolle = resultSet.getString("Rolle");
	this.rolle = new Rolle(rolle);
	//PASSWORT
	this.passwort =  resultSet.getString("Passwort");;
	//AKTIV
	this.aktiv =  resultSet.getBoolean("Aktiv");;
	//Vorname
	this.vorname = resultSet.getString("Vorname");
	//NACHNAME
	this.nachname = resultSet.getString("Nachname");
	//Last Login
	String komplettletzterLogin = resultSet.getString("LetzterLogin");
	if(komplettletzterLogin!=null)
	{
	String jahr = komplettletzterLogin.substring(0, 4);
	String monat = komplettletzterLogin.substring(5,7);
	String tag = komplettletzterLogin.substring(8,10);
	
	this.letzterLogin = tag+"."+monat+"."+jahr;
	}
	else
	{
		this.letzterLogin = null;
	}
	
	}//END TRY
	catch (SQLException e)
	{
		System.err.println(e.getErrorCode());
		System.err.println(e.getMessage());
		System.err.println(e.getCause());
	}
	catch (NullPointerException e)
	{
		System.err.println(e.getMessage());
		System.err.println("Konnte keine Datenbankverbindung herstellen!");
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
	 * @throws Exception 
	 */
	public static Mitarbeiter einloggen(String benutzername, String passwort) throws Exception {
		RemoteConnection Connection = new RemoteConnection();
		Mitarbeiter mitarbeiter = null;
		try
		{
			if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
				RemoteConnection.connect();
			}
		}
		catch (NullPointerException e)
		{
			System.err.println(e.getMessage());
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		
		try {
			String verschluesseltesPW = VerschluesselungSHA1.getEncodedSha1Sum(passwort);
			
			System.out.println("SELECT * FROM Mitarbeiter WHERE Benutzername='"+benutzername+"'");
			//anhand des Benutzernamens ResultSet kriegen
			ResultSet mitarbeiterResult = Connection.executeQueryStatement("SELECT * FROM Mitarbeiter WHERE Benutzername='"+benutzername+"'");
			mitarbeiterResult.last();
			int zeilen = mitarbeiterResult.getRow();
			mitarbeiterResult.beforeFirst();
			
			if(zeilen == 0) throw new Exception("Benutzername ist falsch");
			mitarbeiterResult.next();
			
			//Passwort muss abgefragt werden
			String passwortInDatabase = mitarbeiterResult.getString("Passwort");
//			System.out.println(passwortInDatabase);
//			System.out.println(verschluesseltesPW);


			
			
			//Wenn Passw�rter �bereinstimmen
			if (verschluesseltesPW.equals(passwortInDatabase))
			{	
				
				mitarbeiter= new Mitarbeiter(mitarbeiterResult);
				
			}
			else
			{
				
				mitarbeiter = null;
				throw new Exception("Passwort ist falsch.");
			}
			mitarbeiterResult.close();
			//System.out.println(verschluesseltesPW);
			//System.out.println(passwortInDatabase);
			
			
		}
		
		catch (NoSuchAlgorithmException e)
		{
			System.err.println("FEHLER beim Verschl�sseln:");
			System.err.println(e.getMessage());
		}
		
		return mitarbeiter;
	}

	/**
	 * loggt den Benutzer aus
	 * LastLogin wird gesetzt
	 * @return
	 */
	public boolean ausloggen() {
		
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String heutigesDatum =simpleFormat.format(date);
		this.letzterLogin=heutigesDatum;
		boolean erfolgreich = false;
		System.out.println("UPDATE Mitarbeiter SET LetzterLogin='"+heutigesDatum+"' WHERE Benutzername ='"+this.benutzername+"'");
		try
		{
		int rowsAffected = RemoteConnection.sql.executeUpdate("UPDATE Mitarbeiter SET LetzterLogin='"+heutigesDatum+"' WHERE Benutzername ='"+this.benutzername+"'");
		
		if (rowsAffected==0) erfolgreich =false;
		if (rowsAffected==1) erfolgreich =true;
		
		
		}
		catch (SQLException e)
		{
			System.err.println("Dieser Fehler ist in ausloggen() aufgetreten:");
			
			System.err.println(e.getMessage());
		}
		return erfolgreich;
		
		
	}	

	/**
	 * wei�t den Mitarbeiter einem Bereich zu
	 * @param bereich
	 * @return
	 */
	public boolean setBereich(Bereich bereich) {
		boolean erfolgreich = false;
try
{	
		
		int bereichID = bereich.getID();
		
		System.out.println("UPDATE Mitarbeiter SET Bereich ='"+bereichID+"' WHERE Benutzername='"+this.benutzername+"'");
		
		int affectedRows = RemoteConnection.sql.executeUpdate("UPDATE Mitarbeiter SET Bereich ='"+bereichID+"' WHERE Benutzername='"+this.benutzername+"'");
		

		if (affectedRows==0) erfolgreich =false; 
		if (affectedRows==1) erfolgreich =true;this.bereich=bereich;
}
catch (SQLException e)
{
	System.err.println("Dieser Fehler ist in ausloggen() aufgetreten:");
	
	System.err.println(e.getMessage());
}
		return erfolgreich;
	}

	/**
	 * bekommt den Bereich des Mitarbeiters
	 * @return
	 */
	public Bereich getBereich() {
		
		return this.bereich;
	}

	/**
	 * wei�t Mitarbeiter Rolle zu
	 * @param rolle
	 * @return
	 */
	public boolean setRolle(Rolle rolle) {
		boolean erfolgreich = false;
		try
		{	
				
				String rollenName = rolle.getRollenbezeichnung();
				
				System.out.println("UPDATE Mitarbeiter SET Rolle ='"+rollenName+"' WHERE Benutzername='"+this.benutzername+"'");
				
				int affectedRows = RemoteConnection.sql.executeUpdate("UPDATE Mitarbeiter SET Bereich ='"+rollenName+"' WHERE Benutzername='"+this.benutzername+"'");
				

				if (affectedRows==0) erfolgreich =false; 
				if (affectedRows==1) erfolgreich =true;this.rolle=rolle;
		}
		catch (SQLException e)
		{
			System.err.println("Dieser Fehler ist in setRolle(rolle) aufgetreten:");
			
			System.err.println(e.getMessage());
		}
				return erfolgreich;
			}

	/**
	 * bekommt die Rolle des Mitarbeiters
	 * @return
	 */
	public Rolle getRolle() {
	
		return this.rolle;
	}

	/**
	 * wei�t dem Mitarbeiter Benutzername zu
	 * @param newBenutzername
	 * @return
	 * @throws Exception 
	 */
	public boolean setBenutzername(String benutzername) throws Exception {
		boolean erfolgreich = false;
		try
		{	
			ResultSet checkObVorhanden = RemoteConnection.sql.executeQuery(
					"SELECT Benutzername From Mitarbeiter");
			
			while (checkObVorhanden.next()) 
			{

					String value = checkObVorhanden.getString("Benutzername");
					
				if (benutzername.equals(value)) throw new Exception ("Mitarbeiter mit dem selben Benutzernamen existiert schon!");

			}
			checkObVorhanden.close();
			
			if (!this.benutzername.equals(benutzername))
			{
			
			
				System.out.println("UPDATE Mitarbeiter SET Benutzername ='"+benutzername+"' WHERE Benutzername='"+this.benutzername+"'");
				
				int affectedRows = RemoteConnection.sql.executeUpdate("UPDATE Mitarbeiter SET Benutzername ='"+benutzername+"' WHERE Benutzername='"+this.benutzername+"'");
				

				if (affectedRows==0) erfolgreich =false; 
				if (affectedRows==1) erfolgreich =true;this.benutzername=benutzername;
			}
			else
			{
				throw new Exception ("Benutzernamen sind identisch.");
			}
		}
		catch (SQLException e)
		{
			System.err.println("Dieser Fehler ist in setBenutzername(String) aufgetreten:");
			
			System.err.println(e.getMessage());
		}
				return erfolgreich;
			}


	/**
	 * bekommt den Benutzername des Mitarbeiters
	 * @return
	 */
	public String getBenutzername() {
		return this.benutzername;
	}
	/**
	 * Methode pr�ft ob eingegebenes Passwort bei Passwort vergessen View,
	 * das ist welches in der Datenbank abgelegt ist. Dient zur Identifizierung des Mitarbeiters
	 * @param altesPasswort
	 * @return boolean
	 */
	public boolean checkPasswort(String altesPasswort)
	{
		boolean erfolgreich= false;
try
{
		String altesVerschluesselt = VerschluesselungSHA1.getEncodedSha1Sum(altesPasswort);
		
		System.out.println("SELECT Passwort FROM Mitarbeiter WHERE Benutzername='"+this.benutzername+"'");
		
		ResultSet resultSet = RemoteConnection.sql.executeQuery("SELECT Passwort FROM Mitarbeiter WHERE Benutzername='"+this.benutzername+"'");
		resultSet.next();
		String aktuellesPasswort = resultSet.getString("Passwort");
		
		if(altesVerschluesselt.equals(aktuellesPasswort)) erfolgreich=true; 
		if(!altesVerschluesselt.equals(aktuellesPasswort)) erfolgreich=false;
		resultSet.close();
}
catch (NoSuchAlgorithmException e)
{
	System.err.println("FEHLER beim Verschl�sseln:");
	System.err.println(e.getMessage());
}
catch (SQLException e)
{
	System.err.println("Dieser Fehler ist in checkPasswort(String) aufgetreten:");
	
	System.err.println(e.getMessage());
}
		
		return erfolgreich;

	}
	/**
	 * bekommt das Passwort des Mitarbeiters
	 * VERSCHL�SSELT
	 * @return
	 */
	public String getPasswort() {
		
		return this.passwort;
	}

	/**
	 * ver�ndert Passwort des Mitarbeiters
	 * @param newPasswort
	 * @return
	 */
	public boolean setPasswort(String newPasswort) {
		boolean erfolgreich= false;
		try
		{	
			String newPasswortVerschluesselt = VerschluesselungSHA1.getEncodedSha1Sum(newPasswort);
			System.out.println("UPDATE Mitarbeiter SET Passwort ='"+newPasswortVerschluesselt+"' WHERE Benutzername='"+this.benutzername+"'");
				
			int rowsAffect = RemoteConnection.sql.executeUpdate("UPDATE Mitarbeiter SET Passwort ='"+newPasswortVerschluesselt+"' WHERE Benutzername='"+this.benutzername+"'");
			if (rowsAffect==0) erfolgreich =false;
			if (rowsAffect==1) erfolgreich =true; this.passwort=newPasswortVerschluesselt;
			
		}
		
		catch (NoSuchAlgorithmException e)
		{
			System.err.println("FEHLER beim Verschl�sseln:");
			System.err.println(e.getMessage());
		}
		catch (SQLException e)
		{
			System.err.println("Dieser Fehler ist in setpasswort(String) aufgetreten:");
			
			System.err.println(e.getMessage());
		}
				
				return erfolgreich;

			}

	/**
	 * wei�t Arbeitsgruppe zu
	 * @param arbeitsgruppe
	 * @return
	 */
	public boolean setArbeitsgruppe(Arbeitsgruppe arbeitsgruppe) {
		boolean erfolgreich= false;
		try
		{	
			int arbeitsgrupepID = arbeitsgruppe.getID();
			System.out.println("UPDATE Mitarbeiter SET Arbeitsgruppe ='"+arbeitsgrupepID+"' WHERE Benutzername='"+this.benutzername+"'");
				
			int rowsAffect = RemoteConnection.sql.executeUpdate("UPDATE Mitarbeiter SET Arbeitsgruppe ='"+arbeitsgrupepID+"' WHERE Benutzername='"+this.benutzername+"'");
			
			if (rowsAffect==0) erfolgreich =false;
			if (rowsAffect==1) erfolgreich =true; this.arbeitsgruppe=arbeitsgruppe;
			
		}
		catch (SQLException e)
		{
			System.err.println("Dieser Fehler ist in setpasswort(String) aufgetreten:");
			
			System.err.println(e.getMessage());
		}
				
				return erfolgreich;

			}

	/**
	 * bekommt die Arbeitsgruppe
	 * @return
	 */
	public Arbeitsgruppe getArbeitsgruppe() {
		return this.arbeitsgruppe;
	}

	/**
	 * l�scht Mitarbeiter
	 * @param aktiv
	 * @return
	 * @throws Exception 
	 */
	public boolean loeschen() throws Exception {
		boolean erfolgreich = false;
		boolean aktuellerStatus = this.getAktiv();
		boolean darfdeletedWerdenBereich=false;
		boolean aktiv;
		boolean darfdeletedWerdenArbeitsgruppe = false;
		String leiter,bereich,arbeitsgruppe;
		RemoteConnection Connection = new RemoteConnection();
		
		try 
		{	//IN Bereich pr�fen
			System.out.println("SELECT * FROM Bereich WHERE Leiter='"+this.benutzername+"'");
			
			ResultSet checkMitarbeiterInBereich = Connection.executeQueryStatement("SELECT * FROM Bereich WHERE Leiter='"+this.benutzername+"'");
			while(checkMitarbeiterInBereich.next())
			{
				 leiter = checkMitarbeiterInBereich.getString("Leiter");
				 bereich = checkMitarbeiterInBereich.getString("Kurzbezeichnung");
				 if (leiter==null) darfdeletedWerdenBereich=true;
					if (leiter!=null) 
						{
							throw new Exception("Diesem Bereich ist der Mitarbeiter noch als Leiter zugeordnet: "+bereich+" Bitte zuordnung l�schen.");
						
						}
			}
			checkMitarbeiterInBereich.close();
		
			//IN Arbeitsgruppe pr�fen!
			
			System.out.println("SELECT * FROM Arbeitsgruppe WHERE Leiter='"+this.benutzername+"'");
			
			ResultSet checkMitarbeiterInArbeitsgruppe = Connection.executeQueryStatement("SELECT * FROM Arbeitsgruppe WHERE Leiter='"+this.benutzername+"'");
			while(checkMitarbeiterInArbeitsgruppe.next())
			{
				 leiter = checkMitarbeiterInArbeitsgruppe.getString("Leiter");
				 arbeitsgruppe = checkMitarbeiterInArbeitsgruppe.getString("Kurzbezeichnung");
				 if (leiter==null) darfdeletedWerdenArbeitsgruppe=true;
					if (leiter!=null) 
						{
							throw new Exception("Dieser Arbeitsgruppe ist der Mitarbeiter noch als Leiter zugeordnet: "+arbeitsgruppe+" Bitte zuordnung l�schen.");
						
						}
			}
			checkMitarbeiterInArbeitsgruppe.close();
			
		//abfrage ob weder im Bereich noch in der Arbeitsgruppe noch als Leiter t�tig
		if(darfdeletedWerdenBereich==true && darfdeletedWerdenArbeitsgruppe==true)
		{
			if(aktuellerStatus==true)
			{	
			
			
			System.out.println("UPDATE Mitarbeiter SET Aktiv ='0' WHERE Benutzername='"+this.benutzername+"'");
				
			int rowsAffect = RemoteConnection.sql.executeUpdate("UPDATE Mitarbeiter SET Aktiv ='0' WHERE Benutzername='"+this.benutzername+"'");
			
			if (rowsAffect==0) erfolgreich =false;
			if (rowsAffect==1) erfolgreich =true; aktiv =false; this.aktiv=aktiv;
			}
			
		}
		}
		catch (SQLException e)
		{
			System.err.println("Dieser Fehler ist in loeschen() aufgetreten:");
			
			System.err.println(e.getMessage());
		}
				
				return erfolgreich;

			}

	/**
	 * schaut ob der Mitarbeiter aktiv ist, im Sinne von nicht gel�scht
	 * @return
	 */
	public boolean getAktiv() {
		
		return this.aktiv;
	}

	/**
	 * holt den Nachnamen
	 * @return
	 */
	public String getNachname() {
		
		return this.nachname;
	}
	/**
	 * Gibt kompletten Namen aus
	 */
	public String getFullName()
	{
		String fullname = this.vorname+""+this.nachname;
		return fullname;
	}
	/**
	 * wei�t Nachnamen zu
	 * @param nachname
	 * @return
	 */
	public boolean setNachname(String nachname) {
		boolean erfolgreich= false;
		try
		{	
			
			System.out.println("UPDATE Mitarbeiter SET Nachname ='"+nachname+"' WHERE Benutzername='"+this.benutzername+"'");
				
			int rowsAffect = RemoteConnection.sql.executeUpdate("UPDATE Mitarbeiter SET Nachname ='"+nachname+"' WHERE Benutzername='"+this.benutzername+"'");
			
			if (rowsAffect==0) erfolgreich =false;
			if (rowsAffect==1) erfolgreich =true; this.nachname=nachname;
			
		}
		catch (SQLException e)
		{
			System.err.println("Dieser Fehler ist in setNachname(String) aufgetreten:");
			
			System.err.println(e.getMessage());
		}
				
				return erfolgreich;

			}

	/**
	 * holt den Vornamen
	 * @return
	 */
	public String getVorname() {
	
		return this.vorname;
	}

	/**
	 * wei�t den Vornamen zu
	 * @param vorname
	 * @return
	 */
	public boolean setVorname(String vorname) {
		boolean erfolgreich= false;
		try
		{	
			
			System.out.println("UPDATE Mitarbeiter SET Vorname ='"+vorname+"' WHERE Benutzername='"+this.benutzername+"'");
				
			int rowsAffect = RemoteConnection.sql.executeUpdate("UPDATE Mitarbeiter SET Vorname ='"+vorname+"' WHERE Benutzername='"+this.benutzername+"'");
			
			if (rowsAffect==0) erfolgreich =false;
			if (rowsAffect==1) erfolgreich =true; this.vorname=vorname;
			
		}
		catch (SQLException e)
		{
			System.err.println("Dieser Fehler ist in setVorname(String) aufgetreten:");
			
			System.err.println(e.getMessage());
		}
				
				return erfolgreich;

			}


	/**
	 * holt den letzten Login
	 * @return
	 */
	public String getLogin() {
		return this.letzterLogin;
	}

	/**
	 * schaut ob der Mitarbeiter das ben�tigte Recht hat
	 * @param recht
	 * @return
	 */
	public boolean checkRecht(String recht) {
		boolean erfolgreich= false;

		Rolle rolle = this.rolle;
		
		Collection<Rechte> alleRechteVonRolle = new LinkedList<>();
		
		//alle Berechtigungen von Rolle geben, Collection festlegen
		alleRechteVonRolle = rolle.getBerechtigungenzuRolle();
		//Druch alle Rechte einer Rolle durchiterieren
		for (Iterator i = alleRechteVonRolle.iterator(); i.hasNext();)
		{
			Rechte rechte = (Rechte) i.next();
			String rechtName = rechte.getName();
			if(rechtName.equals(recht)) erfolgreich=true;
		}
		
			
			return erfolgreich;

		}



	public static Collection<Mitarbeiter> suche(String suchbegriff){
		
			Collection<Mitarbeiter> result = new LinkedList<>();
			RemoteConnection Connection = new RemoteConnection();
			if( RemoteConnection.connection == null || RemoteConnection.sql == null )
				{
					RemoteConnection.connect();
				};
			ResultSet resultSet = null;
			try 
			{	
				System.out.println("SELECT * FROM Mitarbeiter WHERE Benutzername LIKE '%"+suchbegriff+"%' OR Arbeitsgruppe LIKE '%"+suchbegriff+"%' OR" +
						" Rolle LIKE '%"+suchbegriff+"%' OR Vorname LIKE '%"+suchbegriff+"%' OR Nachname LIKE '%"+suchbegriff+"%'");
					resultSet = Connection.executeQueryStatement(
							"SELECT * FROM Mitarbeiter WHERE Benutzername LIKE '%"+suchbegriff+"%' OR Arbeitsgruppe LIKE '%"+suchbegriff+"%' OR" +
									" Rolle LIKE '%"+suchbegriff+"%' OR Vorname LIKE '%"+suchbegriff+"%' OR Nachname LIKE '%"+suchbegriff+"%'");
					//Abfrage ob �berhaupt Datens�tze gefunden worden sind
					resultSet.last();
					int resultLength = resultSet.getRow();
					resultSet.beforeFirst();
					if (resultLength==0) throw new NullPointerException("Keine Datens�tze gefunden");
					else
					{
						System.out.println("Es wurden "+resultLength+" Datens�tze gefunden. Die Gel�schten Eintr�ge werden nicht angezeigt.");
					}
					while (resultSet.next()) 
					{
						//NuR Aktive werden ausgegeben
						boolean aktiv = resultSet.getBoolean("Aktiv");
						if (aktiv==true)
						{
						result.add(new Mitarbeiter(resultSet));
						}
					}
					resultSet.close();
			}
			catch (SQLException e) 
			{	System.err.println("Dieser Fehler ist aufgetreten in suche Arbeitsgruppe (suchbegriff):");
				System.err.println("Select Statement ist fehlerhaft. Bitte �berpr�fen.");
			}
			return result;
		}
		

	/**
	 * Gibt alle mitarbeiter zur�ck
	 * @return
	 */
	public static Collection<Mitarbeiter> getAlleMitarbeiter()
	{
	Collection<Mitarbeiter> result = new LinkedList<>();
	RemoteConnection Connection = new RemoteConnection();
	if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
		RemoteConnection.connect();
	};
	ResultSet resultSet = null;
	try 
	{	
		System.out.println("SELECT * FROM Mitarbeiter");
			resultSet = Connection.executeQueryStatement(
				"Select * From Mitarbeiter");
			
			while (resultSet.next()) 
			{	
				
				boolean aktiv = resultSet.getBoolean("Aktiv");
				if (aktiv==true)
				{
				result.add(new Mitarbeiter(resultSet));
				}
				
			}
			resultSet.close();
	}
	catch (SQLException e) 
	{	System.err.println("Dieser Fehler ist aufgetreten in getAlleArbeitsgruppen():");
		System.err.println(e.getMessage());
	}
	return result;
}

}
