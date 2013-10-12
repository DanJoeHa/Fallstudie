package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import com.sun.jmx.snmp.daemon.CommunicationException;

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
	 * @throws Exception 
	 */
	public Arbeitsgruppe(String kurzbezeichnung, String beschreibung,
			Bereich bereich, Mitarbeiter leiter) throws Exception {

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
		ResultSet checkObVorhanden = RemoteConnection.sql.executeQuery(
				"SELECT Kurzbezeichnung From Arbeitsgruppe");
		
		while (checkObVorhanden.next()) 
		{

				String value = checkObVorhanden.getString("Kurzbezeichnung");
				
				if (kurzbezeichnung.equals(value)) throw new Exception ("Arbeitgsuppe mit der selben Kurzbezeichnung existiert schon!");

		}
		System.out.println("INSERT INTO Arbeitsgruppe (Kurzbezeichnung, Beschreibung, Bereich, Leiter)"  +
				"VALUES ('"+kurzbezeichnung+"','"+beschreibung+"','"+bereichID+"','"+benutzername+"'");

			int RowsAffected = RemoteConnection.sql.executeUpdate(
"INSERT INTO Arbeitsgruppe (Kurzbezeichnung, Beschreibung, Bereich, Leiter) VALUES ('"+kurzbezeichnung+"','"+beschreibung+"','"+bereichID+"','"+benutzername+"'");
			System.out.println("Rows Affected: "+RowsAffected+"");
		
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
			{	System.out.println("SELECT * FROM Mitarbeiter WHERE Benutzername ='"+leiterBenutzername+"'");
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
		System.err.println("ResultSet ist Leer. Bitte SQL Statement überprüfen!");
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
		{	System.out.println("SELECT * FROM Arbeitsgruppe WHERE ArbeitsgruppeID='"+arbeitsgruppeid+"'");
		
			ResultSet resultSet = RemoteConnection.sql.executeQuery
						("SELECT * FROM Arbeitsgruppe WHERE ArbeitsgruppeID='"+arbeitsgruppeid+"'");
			Arbeitsgruppe ag = new Arbeitsgruppe(resultSet);
			
			this.arbeitsgruppeID = arbeitsgruppeid;
			this.aktiv = ag.getAktiv();
			this.bereich = ag.getBereich();
			this.beschreibung = ag.getBeschreibung();
			this.kurzbezeichnung = ag.getKurzbezeichnung();
			this.leiter = ag.getLeiter();
			
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
	public static Arbeitsgruppe getArbeitsgruppeByName(String kurzbezeichnung){
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
		catch (NullPointerException e)
		{
			System.err.println("Konnte kein Ergebnis mit der Kurzbezeichnung finden.");
		}
		
		return ag;
	}
		
		

	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	
	
	/**
	 * Mehode ändert die Beschreibung einer Arbeitsgruppe
	 * Abfrage ob die alte Beschreibung identisch mit der Neuen ist, 
	 * wenn ja dann wird das Setzen nicht vollzogen.
	 * @param beschreibung
	 * @return boolean ob erfolgreich 
	 */
	public boolean setBeschreibung(String beschreibung) {
			
		boolean erfolgreich = false;
		try 
		{
			String alteBeschreibung = this.getBeschreibung();
			if (!alteBeschreibung.equals(beschreibung))
			{
				int RowsAffected = RemoteConnection.sql.executeUpdate(
					"UPDATE Arbeitsgruppe SET Beschreibung='"+beschreibung+"' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");
	
				System.out.println("UPDATE Arbeitsgruppe SET Beschreibung='"+beschreibung+"' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'"
					+" Rows Affected: "+RowsAffected);
				
				erfolgreich=true;
		
			}
			else
			{
				System.err.println("Alte und Neue Beschreibung sind Identisch! Bitte andere Beschreibung wählen.");
				erfolgreich= false;
			}
		}
			catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("------SQL ERROR-------");
			System.err.println(e.getErrorCode());
			System.err.println(e.getCause());
			System.err.println(e.getMessage());
		}
		catch(NullPointerException e)
		{
			System.err.println("Fehler beim Suchen der alten Beschreibung.");
		}
		return erfolgreich;
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
		
		boolean erfolgreich = false;
		try 
		{
			String alteKurzbezeichnung = this.getKurzbezeichnung();
			if (!alteKurzbezeichnung.equals(kurzbezeichnung))
			{
				int RowsAffected = RemoteConnection.sql.executeUpdate(
					"UPDATE Arbeitsgruppe SET Kurzbezeichnung='"+kurzbezeichnung+"' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");
	
				System.out.println("UPDATE Arbeitsgruppe SET Kurzbezeichnung='"+kurzbezeichnung+"' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'"
					+" Rows Affected: "+RowsAffected);
				
				erfolgreich=true;
		
			}
			else
			{
				System.err.println("Alte und Neue Kurzbezeichnung sind Identisch! Bitte andere Beschreibung wählen.");
				erfolgreich= false;
			}
		}
			catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("------SQL ERROR-------");
			System.err.println(e.getErrorCode());
			System.err.println(e.getCause());
			System.err.println(e.getMessage());
		}
			catch(NullPointerException e)
			{
				System.err.println("Fehler beim Suchen der alten Kurzbezeichnung.");
			}
		return erfolgreich;
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
		boolean erfolgreich = false;
		//Mitgegebener Bereich ID 
		int bereichID = bereich.getID();
		//Aktueller Bereich ID
		int alterBereichID = this.bereich.getID();
		
		try 
		{	//VERGLEICH DER BEIDEN
			if(!(alterBereichID==bereichID))
			{
				int RowsAffect = RemoteConnection.sql.executeUpdate(
				"UPDATE Arbeitsgruppe SET Bereich ='"+bereichID+"' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");
				
				System.out.println("UPDATE Arbeitsgruppe SET Bereich ='"+bereichID+"' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'"+ "Rows Affected: "+RowsAffect);
				erfolgreich=true;
			}
			else
			{
				System.err.println("Alter und Neuer Bereich sind Identisch! Bitte anderen Bereich wählen.");
				erfolgreich= false;
			}
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("------SQL ERROR-------");
			System.err.println(e.getErrorCode());
			System.err.println(e.getCause());
			System.err.println(e.getMessage());
		}
			catch(NullPointerException e)
			{
				System.err.println("Fehler beim Suchen des alten Bereichs.");
			}
		return erfolgreich;
	}
	
	/**
	 * Methode liefert Bereichsobjekt des dazugehörigen Bereichs der
	 * Arbeitsgruppe
	 * @return
	 */
	
	public Bereich getBereich() {
		
		return this.bereich;
	}

	/**
	 * Methode ändert Leiter der ARbeitsgruppe, 
	 * übergeben wird ein Mitarbeiterobjekt
	 * @param mitarbeiter
	 * @return boolean ob erfolgreich
	 */
	public boolean setLeiter(Mitarbeiter mitarbeiter) {
		
		boolean erfolgreich = false;
		//Mitgegebener Bereich ID 
		String neuerLeiterBenutzername = mitarbeiter.getBenutzername();
		//Aktueller Bereich ID
		String alterLeiterBenutzername = this.leiter.getBenutzername();
		
		try 
		{	//VERGLEICH DER BEIDEN
			if(!alterLeiterBenutzername.equals(neuerLeiterBenutzername))
			{
				int RowsAffect = RemoteConnection.sql.executeUpdate(
				"UPDATE Arbeitsgruppe SET Leiter ='"+neuerLeiterBenutzername+"' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");
				
				System.out.println("UPDATE Arbeitsgruppe SET Leiter ='"+neuerLeiterBenutzername+"' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'"+ "Rows Affected: "+RowsAffect);
				erfolgreich=true;
			}
			else
			{
				System.err.println("Alter und Neuer Leiter sind Identisch! Bitte anderen Leiter wählen.");
				erfolgreich= false;
			}
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("------SQL ERROR-------");
			System.err.println(e.getErrorCode());
			System.err.println(e.getCause());
			System.err.println(e.getMessage());
		}
			catch(NullPointerException e)
			{
				System.err.println("Fehler beim Suchen des alten Leiters.");
			}
		return erfolgreich;
	}

	/**
	 * Methode liefert Leiter der Arbeitsgruppe anhand des Benutzernamens
	 * der in der Tabelle Mitarbeiter generiert wird
	 * @return
	 */
	public Mitarbeiter getLeiter() {
	
		return this.leiter;
	}

	/**
	 * Methode löscht die Arbeitsgruppe
	 * -> wird auf Inaktiv gesetzt in der DB
	 * @return
	 */
	public boolean loeschen() {
		boolean erfolgreich = false;
		boolean aktuellerStatus = this.getAktiv();
		
		try 
		{	if(aktuellerStatus==true)
			{
				int RowsAffect = RemoteConnection.sql.executeUpdate(
				"UPDATE Arbeitsgruppe SET Aktiv ='0' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");
				
				System.out.println("UPDATE Arbeitsgruppe SET Aktiv='0' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'"+ "Rows Affected: "+RowsAffect);
				erfolgreich=true;
			}
			
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("------SQL ERROR-------");
			System.err.println(e.getErrorCode());
			System.err.println(e.getCause());
			System.err.println(e.getMessage());
		}
		return erfolgreich;
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
	public static int getIDbyKurzbezeichnung(String kurzbezeichnung) {
		int id = 0;
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		
		try {
			System.out.println("SELECT ArbeitsgruppeID FROM Arbeitsgruppe WHERE Kurzbezeichnung='"+kurzbezeichnung+"'");
			ResultSet resultSet = RemoteConnection.sql.executeQuery("SELECT ArbeitsgruppeID FROM Arbeitsgruppe WHERE Kurzbezeichnung='"+kurzbezeichnung+"'");
			resultSet.next();
			id = resultSet.getInt("ArbeitsgruppeID");
		
		} 
		
		
		catch (SQLException e) {
			System.err.println("Fehler beim Suchen. Keine Arbeitsgruppe mit dieser Kurzbezeichnung vorhanden.");
		}
		catch (NullPointerException e1)
		{
			System.err.println("Konnte keine Arbeitsgruppe mit dieser Kurzbezeichnung finden.");
		}
		catch (CommunicationException e)
		{
			System.err.println("keine Connection zur Db");
		}
		
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
