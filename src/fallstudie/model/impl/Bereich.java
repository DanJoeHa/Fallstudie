package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.sun.jmx.snmp.daemon.CommunicationException;

import fallstudie.model.mysql.connector.RemoteConnection;
/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht mï¿½glich ist im Interface
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
		 	System.out.println("SELECT Kurzbezeichnung From Arbeitsgruppe");
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
			
			if (RowsAffected==1)System.out.println("Es wurde "+RowsAffected+" Datensï¿½tze eingefï¿½gt.");
			
		
			this.kurzbezeichnung = kurzbezeichnung;
			this.beschreibung = beschreibung;
			this.leiter = leiter;
		
		} 
		
		
		catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL Statement ist fehlerhaft!");
		}
		catch(NullPointerException e)
		{
			System.err.println("Der Leiter hat keinen Benutzernamen. Bitte prüen sie.");
			
		}
		}

	/**
	 * Fï¿½gt einen Bereich ohne Leiter in die Datenbank ein.
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
			
			if (RowsAffected==1)System.out.println("Es wurde "+RowsAffected+" Datensï¿½tze eingefï¿½gt.");
			
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
	 * Alle bereiche mit dem Suchbegriff werden zurï¿½ckgegeben
	 * @param suchbegriff
	 * @return 
	 * @return
	 */
		
	public static Collection<Bereich> suche(String suchbegriff) {
		Collection<Bereich> result = new LinkedList<>();
		RemoteConnection Connection = new RemoteConnection();
		if( RemoteConnection.connection == null || RemoteConnection.sql == null )
			{
				RemoteConnection.connect();
			};
		ResultSet resultSet = null;
		try 
		{	
			System.out.println("SELECT * FROM Bereich WHERE BereichID LIKE '%"+suchbegriff+"' OR Leiter LIKE '%"+suchbegriff+"' OR" +
					" Beschreibung LIKE '%"+suchbegriff+"' OR Kurzbezeichnung LIKE '%"+suchbegriff+"'");
				resultSet = Connection.executeQueryStatement(
						"SELECT * FROM Bereich WHERE BereichID LIKE '%"+suchbegriff+"' OR Leiter LIKE '%"+suchbegriff+"' OR" +
								" Beschreibung LIKE '%"+suchbegriff+"' OR Kurzbezeichnung LIKE '%"+suchbegriff+"'");
				//Abfrage ob ï¿½berhaupt Datensï¿½tze gefunden worden sind
				resultSet.last();
				int resultLength = resultSet.getRow();
				resultSet.beforeFirst();
				if (resultLength==0) throw new NullPointerException("Keine Datensï¿½tze gefunden");
				while (resultSet.next()) 
				{
					boolean aktiv = resultSet.getBoolean("Aktiv");
					if (aktiv==true)
					{
						result.add(new Bereich(resultSet));
					}
				}
				resultSet.close();
		}
		catch (SQLException e) 
		{
			System.err.println("SELECT Statement ist fehlerhaft. Bitte ï¿½berprüen.");
		}
		return result;

	}
	/**
	 * Findet Bereich anhand der Kurzbezeichnung.
	 * @param kurzbezeichnung
	 * @return
	 */
	public static Bereich getBereichByName(String kurzbezeichnung){
		
		RemoteConnection Connection = new RemoteConnection();
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
		
		Bereich bereich = null;
		
		try
		{
			
			System.out.println
			("SELECT * FROM Bereich WHERE Kurzbezeichnung = '"+kurzbezeichnung+"'");
		
			ResultSet resultSet = Connection.executeQueryStatement(
					"SELECT * FROM Bereich WHERE Kurzbezeichnung = '"+kurzbezeichnung+"'");
			//Variablen für den späteren Konstruktoraufruf
			resultSet.next();
			bereich = new Bereich(resultSet);
			
			
			resultSet.close();
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
		
		return bereich;
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
		}
		catch (NullPointerException e)
		{
			System.err.println(e.getMessage());
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		try
		{	
	
			//Mitarbeiterobjekt aus der ID
				String leiterBenutzername = resultSet.getString("Leiter");
			
				//Mitarbeiter Resultset holen
			if (leiterBenutzername!=null)
			{	
				System.out.println("SELECT * FROM Mitarbeiter WHERE Benutzername ='"+leiterBenutzername+"'");
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
	 * Beschreibung eines bereichs ï¿½ndern
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
			
				if (RowsAffected==1)System.out.println("Es wurde "+RowsAffected+" Datensï¿½tze geï¿½ndert.");
				
				erfolgreich=true;
		
			}
			else
			{
				System.err.println("Alte und Neue Beschreibung sind Identisch! Bitte andere Beschreibung wï¿½hlen.");
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
		this.beschreibung = beschreibung;
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
	 * Kurzbezeichnung wird geï¿½ndert
	 * @param kurzbezeichnung
	 * @return
	 * @throws Exception 
	 */
	public boolean setKurzbezeichnung(String kurzbezeichnung) throws Exception {
		boolean erfolgreich = false;
		String NeukurzbezeichnungUP = kurzbezeichnung.toUpperCase();
		String AltkurzbezeichnungUP = this.kurzbezeichnung.toUpperCase();
		
		try 
		{
			ResultSet checkObVorhanden = RemoteConnection.sql.executeQuery(
					"SELECT Kurzbezeichnung From Bereich");
			
			while (checkObVorhanden.next()) 
			{

					String value = checkObVorhanden.getString("Kurzbezeichnung").toUpperCase();
					
					if (NeukurzbezeichnungUP.equals(value)) throw new Exception ("Bereich mit der selben Kurzbezeichnung existiert schon!");

			}
			checkObVorhanden.close();
			
			if (!AltkurzbezeichnungUP.equals(NeukurzbezeichnungUP))
			{
				System.out.println("UPDATE Bereich SET Kurzbezeichnung='"+kurzbezeichnung+"' WHERE BereichID='"+this.bereichID+"'");
				int RowsAffected = RemoteConnection.sql.executeUpdate(
						"UPDATE Bereich SET Kurzbezeichnung='"+kurzbezeichnung+"' WHERE BereichID='"+this.bereichID+"'");
	
				
				if (RowsAffected==1)System.out.println("Es wurde "+RowsAffected+" Datensï¿½tze geï¿½ndert.");
				
				
				erfolgreich=true;
		
			}
			else
			{
				System.err.println("Alte und Neue Kurzbezeichnung sind Identisch! Bitte andere Beschreibung wï¿½hlen.");
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
		this.kurzbezeichnung = kurzbezeichnung;
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
	 * Lï¿½schen von einem Bereich, mus abgefangen werden
	 * ob irgendwo in einer Arbeitsgruppe noch ein
	 * Bereich zugeordnet ist
	 * @return
	 * @throws Exception 
	 */
	public boolean loeschen() throws Exception {
		boolean erfolgreich = false;
		boolean aktuellerStatus = this.getAktiv();
		RemoteConnection Connection = new RemoteConnection();
		Collection<Arbeitsgruppe> arbeitsgruppeMitBereich = new LinkedList<>();
		
		try 
		{	
				ResultSet arbeitsgruppeCheck = Connection.executeQueryStatement(
						"SELECT * FROM Arbeitsgruppe WHERE Bereich='"+this.bereichID+"'");
				
				arbeitsgruppeCheck.last();
				int rows = arbeitsgruppeCheck.getRow();
				//Abfrage ob der Bereich noch einer Arbeitsgruppe zugeordnet ist.
				arbeitsgruppeCheck.beforeFirst();
				if(rows>0)
				{
					while (arbeitsgruppeCheck.next()) 
					{	
						//Nur aktive werden ausgegeben
						boolean aktiv = arbeitsgruppeCheck.getBoolean("Aktiv");
						if (aktiv==true)
						{
							arbeitsgruppeMitBereich.add(new Arbeitsgruppe(arbeitsgruppeCheck));
						}
						
						for (Iterator j=arbeitsgruppeMitBereich.iterator();j.hasNext();)
						{
							Arbeitsgruppe ag = (Arbeitsgruppe) j.next();
							throw new Exception("Bereich ist noch zur Arbeitsgruppe "+ag.getKurzbezeichnung()+" zugeordnet. " +
									"Bitte zuvor diese Zuordnung löschen dann erneut versuchen");
							
						}
						
					}
					arbeitsgruppeCheck.close();
					
					
					
				}
				else if (rows==0)
				{
					
				
					if(aktuellerStatus==true)
					{
						System.out.println("UPDATE Bereich SET Aktiv='0' WHERE BereichID='"+this.bereichID+"'");
					
						int RowsAffect = RemoteConnection.sql.executeUpdate(
						"UPDATE Bereich SET Aktiv ='0' WHERE BereichID='"+this.bereichID+"'");
						
						if (RowsAffect==1)System.out.println("Es wurde "+RowsAffect+" Datensatz gelöscht.");
						
						erfolgreich=true;
					
					}
					else if (aktuellerStatus==false)
					{
						throw new Exception("Dieser Bereich ist bereits gelöscht.");
					}
				}

		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("------SQL ERROR-------");
			System.err.println(e.getErrorCode());
			System.err.println(e.getCause());
			System.err.println(e.getMessage());
		}
		this.aktiv=false;
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
	
	public static Collection<Bereich> getAlleBereiche() {

		Collection<Bereich> result = new LinkedList<>();
		RemoteConnection Connection = new RemoteConnection();
		
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		ResultSet resultSet = null;
		try 
		{	
			System.out.println("SELECT * FROM Bereich");
				resultSet = Connection.executeQueryStatement(
					"Select * From Bereich");
				
				while (resultSet.next()) 
				{	
					//Nur aktive werden ausgegeben
					boolean aktiv = resultSet.getBoolean("Aktiv");
					if (aktiv==true)
					{
					result.add(new Bereich(resultSet));
					}
				}
				resultSet.close();
		}
		catch (SQLException e) 
		{	System.err.println("Dieser Fehler ist aufgetreten in getAlleBereiche():");
			System.err.println(e.getMessage());
		}
		return result;
	}
	/**
	 * Leiter eines Bereichs setzen
	 * @param Mitarbeiter
	 * @return
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
				System.out.println("UPDATE Bereich SET Leiter ='"+neuerLeiterBenutzername+"' WHERE BereichID='"+this.bereichID+"'");
			
				int RowsAffect = RemoteConnection.sql.executeUpdate(
				"UPDATE Bereich SET Leiter ='"+neuerLeiterBenutzername+"' WHERE BereichID='"+this.bereichID+"'");
				
				if (RowsAffect==1)System.out.println("Es wurde "+RowsAffect+" Datensatz geändert.");
				erfolgreich=true;
			}
			else
			{
				System.err.println("Alter und Neuer Leiter sind Identisch! Bitte anderen Leiter wählen.");
				erfolgreich= false;
			}
			this.leiter  = mitarbeiter;
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
	 * Leiter eines Bereichs bekommen
	 * @return
	 */
	public Mitarbeiter getLeiter() {
	
		return this.leiter;
	}

}
