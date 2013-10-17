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
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht m�glich ist im Interface
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
	private String leiterBenutzername;
	
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
		if(leiter!=null)
		{
			
		
			leiterBenutzername = leiter.getBenutzername();
		 	//System.out.println("SELECT Kurzbezeichnung From Arbeitsgruppe");
			ResultSet checkObVorhanden = RemoteConnection.sql.executeQuery(
					"SELECT Kurzbezeichnung From Bereich");
			
			while (checkObVorhanden.next()) 
			{

					String value = checkObVorhanden.getString("Kurzbezeichnung");
					
					if (kurzbezeichnung.equals(value)) throw new Exception ("Bereich mit selber Kurzbezeichnung existiert schon.");

			}
			
			System.out.println("INSERT INTO Bereich (Kurzbezeichnung, Beschreibung, Leiter)" +
					"VALUES ('"+kurzbezeichnung+"', '"+beschreibung+"', '"+leiterBenutzername+"')");
		
			int RowsAffected= RemoteConnection.sql.executeUpdate("INSERT INTO Bereich (Kurzbezeichnung, Beschreibung, Leiter)" +
					"VALUES ('"+kurzbezeichnung+"', '"+beschreibung+"', '"+leiterBenutzername+"')");
			
			
		
			this.kurzbezeichnung = kurzbezeichnung;
			this.beschreibung = beschreibung;
			this.leiter = leiter;
			if (RowsAffected==1)throw new Exception("Bereich wurde erfolgreich angelegt.");
		}
		else if(leiter==null)
		{
			ResultSet checkObVorhanden = RemoteConnection.sql.executeQuery(
					"SELECT Kurzbezeichnung From Bereich");
			
			while (checkObVorhanden.next()) 
			{

					String value = checkObVorhanden.getString("Kurzbezeichnung");
					if (kurzbezeichnung.equals(value)) throw new Exception ("Bereich mit selber Kurzbezeichnung existiert schon.");

			}
			
			//System.out.println("INSERT INTO Bereich (Kurzbezeichnung, Beschreibung)" +
			//		"VALUES ('"+kurzUp+"', '"+beschreibung+"'");
		
			int RowsAffected= RemoteConnection.sql.executeUpdate("INSERT INTO Bereich (Kurzbezeichnung, Beschreibung)" +
					"VALUES ('"+kurzbezeichnung+"', '"+beschreibung+"'");
			
			this.kurzbezeichnung = kurzbezeichnung;
			this.beschreibung = beschreibung;
			this.leiter = null;
			
			if (RowsAffected==1)throw new Exception("Bereich wurde erfolgreich angelegt.");
		
		}
		} 
		
		
		catch (SQLException e) {
			System.err.println("Fehler in Bereich anlegen Konstruktor");
			System.err.println(e.getMessage());
			
		}
		}
	/**
	 * Alle bereiche mit dem Suchbegriff werden zur�ckgegeben
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
			//System.out.println("SELECT * FROM Bereich WHERE BereichID LIKE '%"+suchbegriff+"' OR Leiter LIKE '%"+suchbegriff+"' OR" +
			//		" Beschreibung LIKE '%"+suchbegriff+"' OR Kurzbezeichnung LIKE '%"+suchbegriff+"'");
				resultSet = Connection.executeQueryStatement(
						"SELECT * FROM Bereich WHERE BereichID LIKE '%"+suchbegriff+"' OR Leiter LIKE '%"+suchbegriff+"' OR" +
								" Beschreibung LIKE '%"+suchbegriff+"' OR Kurzbezeichnung LIKE '%"+suchbegriff+"'");
				//Abfrage ob �berhaupt Datens�tze gefunden worden sind
				resultSet.last();
				int resultLength = resultSet.getRow();
				resultSet.beforeFirst();
				if (resultLength==0) throw new NullPointerException("Keine Datens�tze gefunden");
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
			System.err.println("SELECT Statement ist fehlerhaft. Bitte �berpr�en.");
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
			
			//System.out.println("SELECT * FROM Bereich WHERE Kurzbezeichnung = '"+kurzbezeichnung+"'");
			
			ResultSet resultSet = Connection.executeQueryStatement(
					"SELECT * FROM Bereich WHERE Kurzbezeichnung = '"+kurzbezeichnung+"'");
			//Variablen f�r den sp�teren Konstruktoraufruf
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

	public Bereich(int bereichID) {
		
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
		{	//System.out.println("SELECT * FROM Bereich WHERE BereichID='"+bereichID+"'");
		
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
			this.leiterBenutzername = resultSet.getString("Leiter");
			
				
				//Mitarbeiter Resultset holen
			
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
	 * Beschreibung eines bereichs �ndern
	 * @param beschreibung
	 * @return
	 */
	
	public boolean setBeschreibung(String beschreibung) {
		boolean erfolgreich = false;
		int RowsAffected;
		try 
		{
				System.out.println("UPDATE Bereich SET Beschreibung='"+beschreibung+"' WHERE BereichID='"+this.bereichID+"'");
				RowsAffected = RemoteConnection.sql.executeUpdate(
					"UPDATE Bereich SET Beschreibung='"+beschreibung+"' WHERE BereichID='"+this.bereichID+"'");	
				erfolgreich=true;
		
			if(RowsAffected==0)	erfolgreich= false;
		}
			catch (SQLException e) {
			System.err.println("Fehler in setBeschreibung in Bereich:");
			System.err.println(e.getMessage());
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
	 * Kurzbezeichnung wird ge�ndert
	 * @param kurzbezeichnung
	 * @return
	 * @throws Exception 
	 */
	public boolean setKurzbezeichnung(String kurzbezeichnung) throws Exception {
		boolean erfolgreich = false;
		
		try 
		{
			
				//System.out.println("UPDATE Bereich SET Kurzbezeichnung='"+kurzbezeichnung+"' WHERE BereichID='"+this.bereichID+"'");
				int RowsAffected = RemoteConnection.sql.executeUpdate(
						"UPDATE Bereich SET Kurzbezeichnung='"+kurzbezeichnung+"' WHERE BereichID='"+this.bereichID+"'");
	
				
				if (RowsAffected==1)System.out.println("Es wurde "+RowsAffected+" Datens�tze ge�ndert.");
				
				
				erfolgreich=true;
		
		}
			
			catch (SQLException e) {
			System.err.println("Fehler in setKurzbezeichnung: ");
			System.err.println(e.getMessage());
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
	 * L�schen von einem Bereich, mus abgefangen werden
	 * ob irgendwo in einer Arbeitsgruppe noch ein
	 * Bereich zugeordnet ist
	 * @return
	 * @throws Exception 
	 */
	public boolean loeschen() throws Exception {
		boolean erfolgreich = false;
		boolean aktuellerStatus = this.getAktiv();
		RemoteConnection Connection = new RemoteConnection();
		boolean darfdeleteArbeitsgruppe = false;
		boolean darfdeleteMitarbeiter=false;
		boolean darfdelteLeiter=false;
		try 
		{		//Check ob mitarbeiter noch bereich zugeordnet sind
				
			System.out.println("SELECT * FROM Mitarbeiter WHERE Bereich='"+this.bereichID+"'");
			ResultSet mitarbeiterdrancheck = Connection.executeQueryStatement("SELECT * FROM Mitarbeiter WHERE Bereich='"+this.bereichID+"'");
				if(mitarbeiterdrancheck.next())
					{
						darfdeleteMitarbeiter=false; 
						
					}
				else if(!mitarbeiterdrancheck.next()) 
					{
					darfdeleteMitarbeiter=true;
					
					
					}
				//check ob Leiter noch dran ist
				System.out.println("SELECT * FROM Bereich WHERE BereichID='"+this.bereichID+"'");
				ResultSet leiterdranCheck = Connection.executeQueryStatement("SELECT * FROM Bereich WHERE BereichID='"+this.bereichID+"'");
				leiterdranCheck.next();
				if(!leiterdranCheck.next()) darfdelteLeiter=true;
				if(leiterdranCheck.next()) darfdelteLeiter=false;
				
				//Check ob Arbeitsgruppe dran ist
				System.out.println("SELECT * FROM Arbeitsgruppe WHERE Bereich='"+this.bereichID+"'");
				ResultSet arbeitsgruppeCheck = Connection.executeQueryStatement(
						"SELECT * FROM Arbeitsgruppe WHERE Bereich='"+this.bereichID+"'");
				
				if(arbeitsgruppeCheck.next()){
					darfdeleteArbeitsgruppe=false;
				}
				else if(!arbeitsgruppeCheck.next()){
					darfdeleteArbeitsgruppe=true;
				}
				
				
				if (darfdeleteArbeitsgruppe==true && darfdelteLeiter==true && darfdeleteMitarbeiter==true)
				{
				
					if(aktuellerStatus==true)
					{
						//System.out.println("UPDATE Bereich SET Aktiv='0' WHERE BereichID='"+this.bereichID+"'");
					
						int RowsAffect = RemoteConnection.sql.executeUpdate(
						"UPDATE Bereich SET Aktiv ='0' WHERE BereichID='"+this.bereichID+"'");
						
						
						erfolgreich=true;
					
						if (RowsAffect==1) throw new Exception("Es wurde 1 Datensatz gelöscht.");
					}
					else if (aktuellerStatus==false)
					{
						erfolgreich=false;
					}
				}
				else
				{
					throw new Exception("Bereich darf nicht gelöscht werden. Es sind noch Arbeitsgruppen oder Mitarbeiter dran. Bitte prüfen.");
				}

		}
		
		catch (SQLException e) {
System.err.println("Fehler in Bereich löschen:");
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
			//System.out.println("SELECT BereichID FROM Bereich WHERE Kurzbezeichnung='"+kurzbezeichnung+"'");
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
	
		try 
		{	
			System.out.println("SELECT * FROM Bereich WHERE Aktiv='1'");
				ResultSet resultSet = Connection.executeQueryStatement(
					"Select * From Bereich WHERE Aktiv='1'");
				
				while (resultSet.next()) 
				{	
					//System.out.println(resultSet.getString("Kurzbezeichnung"));
					result.add(new Bereich(resultSet));
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
			try 
		{	
				if(!(mitarbeiter==null)){
					String neuerLeiterBenutzername = mitarbeiter.getBenutzername();
					
					System.out.println("UPDATE Bereich SET Leiter ='"+neuerLeiterBenutzername+"' WHERE BereichID='"+this.bereichID+"'");
				
				int RowsAffect = RemoteConnection.sql.executeUpdate(
				"UPDATE Bereich SET Leiter ='"+neuerLeiterBenutzername+"' WHERE BereichID='"+this.bereichID+"'");
				RemoteConnection.sql.executeUpdate("UPDATE Mitarbeiter SET Bereich='"+this.bereichID+"' WHERE Benutzername='"+neuerLeiterBenutzername+"'");
				
				if (RowsAffect==1)System.out.println("Es wurde "+RowsAffect+" Datensatz ge�ndert.");
				erfolgreich=true;
			this.leiter  = mitarbeiter;
				}
				else if(mitarbeiter==null)
				{
					System.out.println("UPDATE Bereich SET Leiter =NULL WHERE BereichID='"+this.bereichID+"'");
					
					int RowsAffect = RemoteConnection.sql.executeUpdate(
					"UPDATE Bereich SET Leiter =NULL WHERE BereichID='"+this.bereichID+"'");
					
					erfolgreich=true;
					this.leiter  = null;
				}
				
		}
		
		catch (SQLException e) {
			System.err.println("Fehler in SetLeiter (Mitarbeiter) in Bereich:");
			System.err.println(e.getMessage());
		}
		return erfolgreich;
	}

	/**
	 * Leiter eines Bereichs bekommen
	 * @return
	 * @throws SQLException 
	 */
	public Mitarbeiter getLeiter()  {
		try
		{
			if(this.leiter!=null)
			{
				return this.leiter;
			}
			if(this.leiterBenutzername!=null)
			{
			//System.out.println("SELECT * FROM Mitarbeiter WHERE Benutzername ='"+this.leiterBenutzername+"'");
			ResultSet mitarbeiterResult = RemoteConnection.sql.executeQuery("SELECT * FROM Mitarbeiter WHERE Benutzername ='"+this.leiterBenutzername+"'");
			
			mitarbeiterResult.next();
			
			this.leiter= new Mitarbeiter(mitarbeiterResult);;
			}
			else
			{
				this.leiter=null;
			}
		}
		catch (SQLException e)
		{
			System.err.println("fehler in getLeiter():");
			System.err.println(e.getMessage());
		}
		return this.leiter;
		
	}
	
}
