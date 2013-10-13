package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import com.sun.jmx.snmp.daemon.CommunicationException;

import fallstudie.model.mysql.connector.RemoteConnection;
/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @version 1.0 Attribute aktualisiert
 * @author Phil, 11.10.2013, aktualisiert
 * @version 1.1
 */
public class Bereich {

	private String beschreibung;
	private String kurzbezeichnung;
	private boolean aktiv;
	private Mitarbeiter leiter;
	private int bereichID;
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	/**
	 * Konstruktor beim Anlegen eines neuen Bereichs (fehlende Parameter werden NULL gesetzt)
	 * abfangen im programm selber
	 * @param BereichID
	 * @return 
	 * @return
	 * @throws Exception 
	 */
	
	public Bereich(String kurzbezeichnung, String beschreibung,
			Mitarbeiter leiter) throws Exception {
		
		String leiterBenutzername=null;
		
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
		 leiterBenutzername = leiter.getBenutzername();
		 
			ResultSet checkObVorhanden = RemoteConnection.sql.executeQuery(
					"SELECT Kurzbezeichnung From Arbeitsgruppe");
			
			while (checkObVorhanden.next()) 
			{

					String value = checkObVorhanden.getString("Kurzbezeichnung");
					
					if (kurzbezeichnung.equals(value)) throw new Exception ("Bereich mit selber Kurzbezeichnung existiert schon.");

			}
			
			System.out.println("INSERT INTO Bereich (Kurzbezeichnung, Beschreibung, Leiter)" +
					"VALUES ('"+kurzbezeichnung+"', '"+beschreibung+"', '"+leiterBenutzername+"'");
		
			int RowsAffected= RemoteConnection.sql.executeUpdate("INSERT INTO Bereich (Kurzbezeichnung, Beschreibung, Leiter)" +
					"VALUES ('"+kurzbezeichnung+"', '"+beschreibung+"', '"+leiterBenutzername+"'");
			
			if (RowsAffected==1)System.out.println("Es wurde "+RowsAffected+" Datensätze eingefügt.");
			
		} 
		
		
		catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL Statement ist fehlerhaft!");
		}
		catch(NullPointerException e)
		{
			System.err.println("Der Leiter hat keinen Benutzernamen. Bitte prüfen sie.");
			
		}
		this.kurzbezeichnung = kurzbezeichnung;
		this.beschreibung = beschreibung;
		this.leiter = leiter;
	}

	/**
	 * Fügt einen Bereich ohne Leiter in die Datenbank ein.
	 * @param kurzbezeichnung
	 * @param beschreibung
	 * @throws Exception 
	 */
	public Bereich(String kurzbezeichnung, String beschreibung) throws Exception {
		
		String kurzUp = kurzbezeichnung.toUpperCase();
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
		 
			ResultSet checkObVorhanden = RemoteConnection.sql.executeQuery(
					"SELECT Kurzbezeichnung From Arbeitsgruppe");
			
			while (checkObVorhanden.next()) 
			{

					String value = checkObVorhanden.getString("Kurzbezeichnung");
					String valueUp = value.toUpperCase();
					if (kurzbezeichnung.equals(valueUp)) throw new Exception ("Bereich mit selber Kurzbezeichnung existiert schon.");

			}
			
			System.out.println("INSERT INTO Bereich (Kurzbezeichnung, Beschreibung)" +
					"VALUES ('"+kurzUp+"', '"+beschreibung+"'");
		
			int RowsAffected= RemoteConnection.sql.executeUpdate("INSERT INTO Bereich (Kurzbezeichnung, Beschreibung)" +
					"VALUES ('"+kurzUp+"', '"+beschreibung+"'");
			
			if (RowsAffected==1)System.out.println("Es wurde "+RowsAffected+" Datensätze eingefügt.");
			
		} 
		
		
		catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL Statement ist fehlerhaft!");
		}
		this.kurzbezeichnung = kurzbezeichnung;
		this.beschreibung = beschreibung;
		this.leiter = null;
	}
	/**
	 * Alle bereiche mit dem Suchbegriff werden zurückgegeben
	 * @param suchbegriff
	 * @return 
	 * @return
	 */
		
	public static Collection<Bereich> suche(String suchbegriff) {
		Collection<Bereich> result = new LinkedList<>();
		
		if( RemoteConnection.connection == null || RemoteConnection.sql == null )
			{
				RemoteConnection.connect();
			};
		ResultSet resultSet = null;
		try 
		{	
			System.out.println("SELECT * FROM Bereich WHERE BereichID LIKE '%"+suchbegriff+"' OR Leiter LIKE '%"+suchbegriff+"' OR" +
					" Beschreibung LIKE '%"+suchbegriff+"' OR Kurzbezeichnung LIKE '%"+suchbegriff+"'");
				resultSet = RemoteConnection.sql.executeQuery(
						"SELECT * FROM Bereich WHERE BereichID LIKE '%"+suchbegriff+"' OR Leiter LIKE '%"+suchbegriff+"' OR" +
								" Beschreibung LIKE '%"+suchbegriff+"' OR Kurzbezeichnung LIKE '%"+suchbegriff+"'");
				//Abfrage ob überhaupt Datensätze gefunden worden sind
				resultSet.last();
				int resultLength = resultSet.getRow();
				resultSet.beforeFirst();
				if (resultLength==0) throw new NullPointerException("Keine Datensätze gefunden");
				while (resultSet.next()) 
				{
					result.add(new Bereich(resultSet));
				}
				resultSet.close();
		}
		catch (SQLException e) 
		{
			System.err.println("SELECT Statement ist fehlerhaft. Bitte überprüfen.");
		}
		return result;

	}
	
	/**
	 * findet anhand der BereichsID den Bereich
	 * @param bereichID
	 * @throws SQLException 
	 */

	public Bereich(int bereichID) throws SQLException {
		
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
		{	System.out.println("SELECT * FROM Bereich WHERE BereichID='"+bereichID+"'");
		
		ResultSet BereichResult = Connection.executeQueryStatement("SELECT * FROM Bereich WHERE BereichID='"+bereichID+"'");
			
			BereichResult.next();
		
			Bereich bereich = new Bereich(BereichResult);
			this.bereichID = bereich.getID();
			this.aktiv = bereich.getAktiv();
			this.beschreibung = bereich.getBeschreibung();
			this.kurzbezeichnung = bereich.getKurzbezeichnung();
			this.leiter = bereich.getLeiter();
			BereichResult.close();
			
		}
		catch (SQLException e)
		{   System.err.println("Dieser Fehler ist aufgetreten in Bereich (id):");
			System.err.println(e.getMessage());
			
		}
	}
	
	/**
	 * Bereich wird anhand eines Resultsets erstellt
	 * @param resultSet
	 * @throws SQLException 
	 */
	public Bereich(ResultSet resultSet) throws SQLException
	{	
		RemoteConnection Connection = new RemoteConnection();
		try
		{
			if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
				RemoteConnection.connect();
			}
			else
			{
				
			}
		}
		catch (NullPointerException e)
		{
			System.err.println(e.getMessage());
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		try
		{	
			// Obtain the number of columns in the returned table
			
			int columnCount = resultSet.getMetaData().getColumnCount();
			//System.out.println(columnCount);
			//Mitarbeiterobjekt aus der ID
				String leiterBenutzername = resultSet.getString("Leiter");
			
				//Mitarbeiter Resultset holen
			if (leiterBenutzername!=null)
			{	System.out.println("SELECT * FROM Mitarbeiter WHERE Benutzername ='"+leiterBenutzername+"'");
				ResultSet mitarbeiterResult = Connection.executeQueryStatement("SELECT * FROM Mitarbeiter WHERE Benutzername ='"+leiterBenutzername+"'");
				//LEITER SETZEn
				this.leiter = new Mitarbeiter(mitarbeiterResult);
				mitarbeiterResult.close();
			}
			//checken
			else
			{
				this.leiter=null;
			}
			
			//Bereichobjekt aus der BereichsID
			this.bereichID= resultSet.getInt("BereichID");
			//Beschreibung der Arbeitsgruppe
			this.beschreibung = resultSet.getString("Beschreibung");
			//Kurzbezeichnung der Arbeitsgruppe
			this.kurzbezeichnung = resultSet.getString("Kurzbezeichnung");
			//Status der Arbeitsgruppe
			this.aktiv = resultSet.getBoolean("Aktiv");
			
		}
	catch (SQLException e)
	{
		System.err.println("Dieser Fehler ist in Bereich(ResultSet) aufgetreten:");
		System.err.println(e.getMessage());
	}
		
	}
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	/**
	 * Beschreibung eines bereichs ändern
	 * @param beschreibung
	 * @return
	 */
	
	public boolean setBeschreibung(String beschreibung) {
		boolean erfolgreich = false;
		try 
		{
			String alteBeschreibung = this.getBeschreibung();
			if (!alteBeschreibung.equals(beschreibung))
			{
				System.out.println("UPDATE Bereich SET Beschreibung='"+beschreibung+"' WHERE BereichID='"+this.bereichID+"'");
				int RowsAffected = RemoteConnection.sql.executeUpdate(
					"UPDATE Bereich SET Beschreibung='"+beschreibung+"' WHERE BereichID='"+this.bereichID+"'");
			
				if (RowsAffected==1)System.out.println("Es wurde "+RowsAffected+" Datensätze geändert.");
				
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
	 * Beschreibung eines Bereichs erhalten
	 * @return
	 */
	public String getBeschreibung() {
		
		return this.beschreibung;
	}

	/**
	 * Kurzbezeichnung wird geändert
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
				System.out.println("UPDATE Bereich SET Kurzbezeichnung='"+kurzbezeichnung+"' WHERE BereichID='"+this.bereichID+"'");
				int RowsAffected = RemoteConnection.sql.executeUpdate(
						"UPDATE Bereich SET Kurzbezeichnung='"+kurzbezeichnung+"' WHERE BereichID='"+this.bereichID+"'");
	
				
				if (RowsAffected==1)System.out.println("Es wurde "+RowsAffected+" Datensätze geändert.");
				
				
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
	 * Kurzbezeichnung wird erhalten von Bereich
	 * @return
	 */
	public String getKurzbezeichnung() {
		
		return this.kurzbezeichnung;
	}

	/**
	 * Löschen von einem Bereich, mus abgefangen werden
	 * ob irgendwo in einer Arbeitsgruppe noch ein
	 * Bereich zugeordnet ist
	 * @return
	 */
	public boolean loeschen() {
		boolean erfolgreich = false;
		boolean aktuellerStatus = this.getAktiv();
		
		try 
		{	if(aktuellerStatus==true)
			{
				System.out.println("UPDATE Bereich SET Aktiv='0' WHERE BereichID='"+this.bereichID+"'");
			
				int RowsAffect = RemoteConnection.sql.executeUpdate(
				"UPDATE Bereich SET Aktiv ='0' WHERE BereichID='"+this.bereichID+"'");
				
				if (RowsAffect==1)System.out.println("Es wurde "+RowsAffect+" Datensatz gelöscht.");
				
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
	 * Status erhalten
	 * @return
	 */
	public boolean getAktiv() {
	
		return this.aktiv;
	}

	/**
	 * Id anhand von Kurzbezeichnung kriegen (PK)
	 * @param kurzbezeichnung
	 * @return
	 */
	public static int getIDByKurzbezeichnung(String kurzbezeichnung) {
		int id = 0;
		RemoteConnection Connection = new RemoteConnection();
		
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		
		try {
			System.out.println("SELECT BereichID FROM Bereich WHERE Kurzbezeichnung='"+kurzbezeichnung+"'");
			ResultSet resultSet = Connection.executeQueryStatement("SELECT BereichID FROM Bereich WHERE Kurzbezeichnung='"+kurzbezeichnung+"'");
			resultSet.next();
			id = resultSet.getInt("BereichID");
		
		} 
		
		
		catch (SQLException e) {
			System.err.println("Fehler ist in Methode getIDByKurzbezeichnung(String) aufgetreten:");
			System.err.println(e.getMessage());
		}
		catch (NullPointerException e1)
		{
			System.err.println("Konnte keinen Bereich mit dieser Kurzbezeichnung finden.");
		}
		catch (CommunicationException e)
		{
			System.err.println("keine Connection zur Db");
		}
		
		return id;
	}
	
	/**
	 * Liefert ID des Bereichs ohne parameter.
	 * @return
	 */
	public int getID()
	{
		return this.bereichID;
	}
	/**
	 * Alle Bereiche werden ausgegeben
	 * wird gebraucht bei Combobox
	 * @return
	 */
	
	public Collection<Bereich> getAlleBereiche() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Leiter eines Bereichs setzen
	 * @param Mitarbeiter
	 * @return
	 */
	
	public boolean setLeiter(Mitarbeiter Mitarbeiter) {
		// TODO Auto-generated method stub
		return false;
	}


	/**
	 * Leiter eines Bereichs bekommen
	 * @return
	 */
	public Mitarbeiter getLeiter() {
		// TODO Auto-generated method stub
		return this.leiter;
	}

}
