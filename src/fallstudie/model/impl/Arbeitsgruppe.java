package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import fallstudie.model.mysql.connector.RemoteConnection;
/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @author Phil 11.10.2013
 * 
 */
public class Arbeitsgruppe {

	private  String beschreibung;
	private  String kurzbezeichnung;
	private  int arbeitsgruppeID;
	private  Bereich bereich;
	private  boolean aktiv;
	private  Mitarbeiter leiter;
	
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	/**
	 * Neue Arbeitsgruppe anlegen Konstruktor, alle Parameter werden von GUI übergeben	
	 * @param kurzbezeichnung
	 * @param beschreibung
	 * @param bereich
	 * @param mitarbeiter
	 * @return 
	 * @return
	 */
	public Arbeitsgruppe(String kurzbezeichnung, String beschreibung,
			Bereich bereich, Mitarbeiter leiter) {

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
		
	String benutzername = leiter.getBenutzername();
	int bereichID = bereich.getID(bereich.getKurzbezeichnung());
	
		try {
			
			
			int RowsAffected = RemoteConnection.sql.executeUpdate(
					"INSERT INTO Arbeitsgruppe (Kurzbezeichnung, Beschreibung, Bereich, Leiter)"  +
					"VALUES ('"+kurzbezeichnung+"','"+beschreibung+"','"+bereichID+"','"+benutzername+"'");
			
		
			System.out.println("INSERT INTO Arbeitsgruppe (Kurzbezeichnung, Beschreibung, Bereich, Leiter)"  +
					"VALUES ('"+kurzbezeichnung+"','"+beschreibung+"','"+bereichID+"','"+benutzername+"'"
																		+"||"+"Rows Affected: "+RowsAffected+"");
		}
		
		
		catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL Statement ist fehlerhaft!");
		}
		
		this.beschreibung = beschreibung;
		this.kurzbezeichnung = kurzbezeichnung;
		this.bereich = bereich;
		this.leiter = leiter;

	}



	/**
	 * Durch überreichen des Resultsets werden die Objekte vom Typ ArbeitsgruppeImpl erzeugt
	 * @param resultSet
	 * @throws SQLException 
	 */
	public Arbeitsgruppe (ResultSet resultSet) throws SQLException
	{	
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
		
			resultSet.next();
		
			// Obtain the number of columns in the returned table
			@SuppressWarnings("unused")
			int columnCount = resultSet.getMetaData().getColumnCount();
			//ID der Arbeitsgruppe
			this.arbeitsgruppeID = resultSet.getInt("ArbeitsgruppeID");
			
			//Mitarbeiterobjekt aus der ID
				String leiterBenutzername = resultSet.getString("Leiter");
			
				//Mitarbeiter Resultset holen
			if (leiterBenutzername!=null)
			{
				ResultSet mitarbeiterResult = RemoteConnection.sql.executeQuery(
					"SELECT * FROM Mitarbeiter WHERE Benutzername ='"+leiterBenutzername+"'");
				this.leiter = new Mitarbeiter(mitarbeiterResult);
			}
			//checken
			else
			{
				this.leiter=null;
			}
			
			//Bereichobjekt aus der BereichsID
			int bereichID = resultSet.getInt("Bereich");
			//Bereich aus der ID generieren
			this.bereich = new Bereich(bereichID);
			//Beschreibung der Arbeitsgruppe
			this.beschreibung = resultSet.getString("Beschreibung");
			//Kurzbezeichnung der Arbeitsgruppe
			this.kurzbezeichnung = resultSet.getString("Kurzbezeichnung");
			//Status der Arbeitsgruppe
			this.aktiv = resultSet.getBoolean("Aktiv");
		
		}
	catch (SQLException e)
	{

		System.err.println(e.getMessage());
	}
	}
	/**
	 * ArbeitsgruppeObjekt per ID (Primärschlüssel) erzeugen
	 * @param arbeitsgruppeid
	 */
	
	public Arbeitsgruppe(int arbeitsgruppeid) {
		
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
			ResultSet resultSet = RemoteConnection.sql.executeQuery
						("SELECT * FROM Arbeitsgruppe WHERE ArbeitsgruppeID='"+arbeitsgruppeid+"'");
			System.out.println("SELECT * FROM Arbeitsgruppe WHERE ArbeitsgruppeID='"+arbeitsgruppeid+"'");
			this.arbeitsgruppeID = arbeitsgruppeid;
			Arbeitsgruppe ag = new Arbeitsgruppe(resultSet);
			
			
		}
		catch (SQLException e)
		{;
			System.err.println(e.getMessage());
		}
	}



	/**
	 * Methode wenn nur die Kurzbezeichnung übergeben wird, 
	 * alles andere wird in der Datenbank geholt mit SELECT
	 * @param kurzbezeichnung
	 * @return 
	 * @return
	 * @throws SQLException 
	 */
	public static Arbeitsgruppe getArbeitsgruppeImplByName(String kurzbezeichnung){
		try
		{
			if( RemoteConnection.connection == null || RemoteConnection.sql == null )
			{
				RemoteConnection.connect();
			};
		}
		catch (NullPointerException e)
		{
			System.err.println(e.getMessage());
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		
		Arbeitsgruppe ag = null;
		
		try
		{
			
		
			ResultSet resultSet = RemoteConnection.sql.executeQuery
				("SELECT * FROM Arbeitsgruppe WHERE Kurzbezeichnung = '"+kurzbezeichnung+"'");
			System.out.println
				("SELECT * FROM Arbeitsgruppe WHERE Kurzbezeichnung = '"+kurzbezeichnung+"'");
			//Variablen für den späteren Konstruktoraufruf
	
			ag = new Arbeitsgruppe(resultSet);
			
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("------SQL ERROR-------");
			System.err.println(e.getErrorCode());
			System.err.println(e.getCause());
			System.err.println(e.getMessage());
		}

		return ag;
	}
		
		

	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	
	
	/**
	 * Mehode ändert die Beschreibung einer Arbeitsgruppe
	 * @param beschreibung
	 * @return boolean ob erfolgreich 
	 */
	public boolean setBeschreibung(String beschreibung) {
		
		return false;
	}

	/**
	 * Methode liefert Beschreibung zur Arbeitsgruppe zurück
	 * @return
	 */
	public String getBeschreibung() {
	
		return this.beschreibung;
	}

	/**
	 * Methode ändert Kurzbezeichnung der Arbeitsgruppe
	 * @param kurzbezeichnung
	 * @return
	 */
	public boolean setKurzbezeichnung(String kurzbezeichnung) {
		
		return false;
	}

	/**
	 * Methode liefert Kurzbezeichnung der Arbeitsgruppe
	 * @return
	 */
	
	public String getKurzbezeichnung() {
		
		return this.kurzbezeichnung;
	}

	/**
	 * Methode ändert Bereich der arbeitsgruppe
	 * @param bereich
	 * @return
	 */
	public boolean setBereich(Bereich bereich) {
		
		return false;
	}
	
	/**
	 * Methode liefert Bereichsobjekt des dazugehörigen Bereichs der
	 * Arbeitsgruppe
	 * @return
	 */
	
	public Bereich getBereich() {
		// TODO Auto-generated method stub
		return this.bereich;
	}

	/**
	 * Methode ändert Leiter der ARbeitsgruppe, 
	 * übergeben wird ein Mitarbeiterobjekt
	 * @param mitarbeiter
	 * @return boolean ob erfolgreich
	 */
	public boolean setLeiter(Mitarbeiter mitarbeiter) {
		
		return false;
	}

	/**
	 * Methode liefert Leiter der Arbeitsgruppe anhand des Benutzernamens
	 * der in der Tabelle Mitarbeiter generiert wird
	 * @return
	 */
	public Mitarbeiter getLeiter() {
		// TODO Auto-generated method stub
		return this.leiter;
	}

	/**
	 * Methode löscht die Arbeitsgruppe
	 * -> wird auf Inaktiv gesetzt in der DB
	 * @return
	 */
	public boolean loeschen() {

		return false;
	}

	/**
	 * Methode liefert den Status der Arbeitsgruppe
	 * 1: aktiv, 0: gelöscht
	 * @return
	 */
	public boolean getAktiv() {
		// TODO Auto-generated method stub
		return this.aktiv;
	}

	/**
	 * Methode liefert zur Kurzbezeichnung die dazugehörige ID
	 * der Arbeitsgruppe
	 * @param kurzbezeichnung
	 * @return
	 */
	public int getIDbyKurzbezeichnung(String kurzbezeichnung) {
		// TODO Auto-generated method stub
		int id = 0;
		return id;
	}
	
	/**
	 * Methode liefert ID zur Arbeitsgruppe
	 * @return
	 */
	public int getID()
	{
		return this.arbeitsgruppeID;
	}
	/**
	 * Wird gebraucht um die Comboboxen zu befüllen wo ein Mitarbeiter
	 * einer Arbeitsgruppe hinzugefügt wird oder geändert wird
	 * @return
	 */
	public Collection<Arbeitsgruppe> getAlleArbeitsgruppen() {

		
		return null;
	}
	/**
	 * Methode liefert anhand des Suchbegriffs eine Collection
	 * von übereinstimmungen zurück (In Tabellenform in der Gui auszugeben)
	 * @param suchbegriff
	 * @return
	 */
	public static Collection<Arbeitsgruppe> suche(String suchbegriff)
	{

		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		return null;
		
	}
}
