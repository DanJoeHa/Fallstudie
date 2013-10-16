package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import com.sun.jmx.snmp.daemon.CommunicationException;

import fallstudie.model.mysql.connector.RemoteConnection;
/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht m�glich ist im Interface
 * @author Phil 11.10.2013
 * @author Phil, 12.10.2013
 * @version 1.1
 * @change Alle Methoden implementiert.
 */
public class Arbeitsgruppe {

	private  String beschreibung;
	private  String kurzbezeichnung;
	private  int arbeitsgruppeID;
	private  Bereich bereich;
	private  boolean aktiv;
	private  Mitarbeiter leiter;
	public  ResultSet resultSet;
	private String leiterBenutzername;
	private int bereichID;
	
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	/**
	 * Neue Arbeitsgruppe anlegen Konstruktor, alle Parameter werden von GUI �bergeben	
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
		
		RemoteConnection Connection = new RemoteConnection();
		String benutzername;
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
	
	int bereichID =bereich.getID();
	try {
		ResultSet checkObVorhanden = Connection.executeQueryStatement(
				"SELECT Kurzbezeichnung From Arbeitsgruppe");
		
		while (checkObVorhanden.next()) 
		{

				String value = checkObVorhanden.getString("Kurzbezeichnung");
				
				if (kurzbezeichnung.equals(value)) throw new Exception ("Arbeitgsuppe mit der selben Kurzbezeichnung existiert schon!");

		}
		if (leiter.getBenutzername()!=null)
		{
			benutzername = leiter.getBenutzername();
			
			System.out.println("INSERT INTO Arbeitsgruppe (Kurzbezeichnung, Beschreibung, Bereich, Leiter) VALUES ('"+kurzbezeichnung+"','"+beschreibung+"','"+bereichID+"','"+benutzername+"')");
			
			int RowsAffected = RemoteConnection.sql.executeUpdate(
				"INSERT INTO Arbeitsgruppe (Kurzbezeichnung, Beschreibung, Bereich, Leiter) VALUES ('"+kurzbezeichnung+"','"+beschreibung+"','"+bereichID+"','"+benutzername+"')");
							if (RowsAffected==1)System.out.println("Es wurde "+RowsAffected+" Datensatz gespeichert.");
		}
		else
		{			
			System.out.println("INSERT INTO Arbeitsgruppe (Kurzbezeichnung, Beschreibung, Bereich) VALUES ('"+kurzbezeichnung+"','"+beschreibung+"','"+bereichID+"')");
			
			int RowsAffected = RemoteConnection.sql.executeUpdate(
				"INSERT INTO Arbeitsgruppe (Kurzbezeichnung, Beschreibung, Bereich) VALUES ('"+kurzbezeichnung+"','"+beschreibung+"','"+bereichID+"')");
							if (RowsAffected==1)System.out.println("Es wurde "+RowsAffected+" Datensatz gespeichert.");
		
		}
		
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
	 * Durch �berreichen des Resultsets werden die Objekte vom Typ ArbeitsgruppeImpl erzeugt
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
			//ID der Arbeitsgruppe
			this.arbeitsgruppeID = resultSet.getInt("ArbeitsgruppeID");
			
			//Mitarbeiterobjekt aus der ID
				this.leiterBenutzername = resultSet.getString("Leiter");
			
				//Mitarbeiter Resultset holen
			//Bereichobjekt aus der BereichsID
			this.bereichID = resultSet.getInt("Bereich");
			//Beschreibung der Arbeitsgruppe
			this.beschreibung = resultSet.getString("Beschreibung");
			//Kurzbezeichnung der Arbeitsgruppe
			this.kurzbezeichnung = resultSet.getString("Kurzbezeichnung");
			//Status der Arbeitsgruppe
			this.aktiv = resultSet.getBoolean("Aktiv");
			//Bereich aus der ID generieren
			
			
			
			
		
		}
	catch (SQLException e)
	{
		System.err.println("Dieser Fehler ist aufgetretn in Arbeitsgruppe (ResultSet):");
		System.err.println(e.getMessage());
	}
	
	}
	/**
	 * ArbeitsgruppeObjekt per ID (Prim�rschl�ssel) erzeugen
	 * @param arbeitsgruppeid
	 */
	
	public Arbeitsgruppe(int arbeitsgruppeid) {
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
		{	//System.out.println("SELECT * FROM Arbeitsgruppe WHERE ArbeitsgruppeID='"+arbeitsgruppeid+"'");
		
			ResultSet resultSet = Connection.executeQueryStatement
						("SELECT * FROM Arbeitsgruppe WHERE ArbeitsgruppeID='"+arbeitsgruppeid+"'");
			resultSet.next();
			Arbeitsgruppe ag = new Arbeitsgruppe(resultSet);
			
			this.arbeitsgruppeID = arbeitsgruppeid;
			this.aktiv = ag.getAktiv();
			this.bereich = ag.getBereich();
			this.beschreibung = ag.getBeschreibung();
			this.kurzbezeichnung = ag.getKurzbezeichnung();
			this.leiter = ag.getLeiter();
			
		}
		catch (SQLException e)
		{	System.err.println("Dieser Fehler ist aufgetreten in Arbeitsgruppe (id)");
			System.err.println(e.getMessage());
		}
	}



	/**
	 * Methode wenn nur die Kurzbezeichnung �bergeben wird, 
	 * alles andere wird in der Datenbank geholt mit SELECT
	 * @param kurzbezeichnung
	 * @return 
	 * @return
	 * @throws SQLException 
	 */
	public static Arbeitsgruppe getArbeitsgruppeByName(String kurzbezeichnung){
		
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
		
		Arbeitsgruppe ag = null;
		
		try
		{
			
		
			ResultSet resultSet = Connection.executeQueryStatement(
					"SELECT * FROM Arbeitsgruppe WHERE Kurzbezeichnung = '"+kurzbezeichnung+"'");
			System.out.println	("SELECT * FROM Arbeitsgruppe WHERE Kurzbezeichnung = '"+kurzbezeichnung+"'");
			//Variablen f�r den sp�teren Konstruktoraufruf
			resultSet.next();
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
	 * Mehode �ndert die Beschreibung einer Arbeitsgruppe
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
				//System.out.println("UPDATE Arbeitsgruppe SET Beschreibung='"+beschreibung+"' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");
					
				int RowsAffected = RemoteConnection.sql.executeUpdate(
					"UPDATE Arbeitsgruppe SET Beschreibung='"+beschreibung+"' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");
				if (RowsAffected==1)System.out.println("Es wurde "+RowsAffected+" Datensatz ge�ndert.");
				
				erfolgreich=true;
		
			}
			else
			{
				System.err.println("Alte und Neue Beschreibung sind Identisch! Bitte andere Beschreibung w�hlen.");
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
	 * Methode liefert Beschreibung zur Arbeitsgruppe zur�ck
	 * @return
	 */
	public String getBeschreibung() {
	
		return this.beschreibung;
	}

	/**
	 * Methode �ndert Kurzbezeichnung der Arbeitsgruppe
	 * @param kurzbezeichnung
	 * @return
	 * @throws Exception 
	 */
	public boolean setKurzbezeichnung(String kurzbezeichnung) throws Exception {
		
		boolean erfolgreich = false;
		try 
		{
			
			{
				//System.out.println("UPDATE Arbeitsgruppe SET Kurzbezeichnung='"+kurzbezeichnung+"' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");
						
				int RowsAffected = RemoteConnection.sql.executeUpdate(
					"UPDATE Arbeitsgruppe SET Kurzbezeichnung='"+kurzbezeichnung+"' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");
	
				if (RowsAffected==1)System.out.println("Es wurde "+RowsAffected+" Datensatz ge�ndert.");
				erfolgreich=true;
		
			}
		}
			catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("------SQL ERROR------- in setKurzbezeichnung bei Arbeitsgruppe");
			System.err.println(e.getMessage());
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
	 * Methode �ndert Bereich der arbeitsgruppe
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
				//System.out.println("UPDATE Arbeitsgruppe SET Bereich ='"+bereichID+"' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");
				
				int RowsAffect = RemoteConnection.sql.executeUpdate(
				"UPDATE Arbeitsgruppe SET Bereich ='"+bereichID+"' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");
				if (RowsAffect==1)System.out.println("Es wurde "+RowsAffect+" Datensatz ge�ndert.");
				erfolgreich=true;
			}
			else
			{
				System.err.println("Alter und Neuer Bereich sind Identisch! Bitte anderen Bereich w�hlen.");
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
	 * Methode liefert Bereichsobjekt des dazugeh�rigen Bereichs der
	 * Arbeitsgruppe
	 * @return
	 */
	
	public Bereich getBereich() {
		
		if (this.bereich != null)
			return this.bereich;
		

		
		if (bereichID!=0)
		{
			this.bereich = new Bereich(bereichID);
		}
		else
		{
			this.bereich = null;
		}
		
		return this.bereich;
		
	}

	/**
	 * Methode �ndert Leiter der ARbeitsgruppe, 
	 * �bergeben wird ein Mitarbeiterobjekt
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
				//System.out.println("UPDATE Arbeitsgruppe SET Leiter ='"+neuerLeiterBenutzername+"' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");
			
				int RowsAffect = RemoteConnection.sql.executeUpdate(
				"UPDATE Arbeitsgruppe SET Leiter ='"+neuerLeiterBenutzername+"' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");
				
				if (RowsAffect==1)System.out.println("Es wurde "+RowsAffect+" Datensatz ge�ndert.");
				erfolgreich=true;
			}
			else
			{
				System.err.println("Alter und Neuer Leiter sind Identisch! Bitte anderen Leiter w�hlen.");
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
		System.err.println("fehler in Arbeitsgruppe.getLeiter():");
		System.err.println(e.getMessage());
	}
	return this.leiter;
	
	}

	/**
	 * Methode l�scht die Arbeitsgruppe
	 * -> wird auf Inaktiv gesetzt in der DB
	 * @return
	 * @throws Exception 
	 */
	public boolean loeschen() {
		boolean erfolgreich = false;
		boolean aktuellerStatus = this.getAktiv();
		boolean darfdeletedWerden=false;
		RemoteConnection Connection = new RemoteConnection();
		try 
		{	
			//System.out.println("SELECT Leiter FROM Arbeitsgruppe WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");
			
			ResultSet checkMitarbeiter = Connection.executeQueryStatement("SELECT Leiter FROM Arbeitsgruppe WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");
			checkMitarbeiter.next();
			String leiter = checkMitarbeiter.getString("Leiter");
			if (leiter==null) darfdeletedWerden=true;
			if (leiter!=null) 
				{
					darfdeletedWerden=false;
				}
			checkMitarbeiter.close();
		
		//Erst fragen ob kein Leiter mehr da ist.
		if(darfdeletedWerden==true)
		{
			if(aktuellerStatus==true)
			{	
				//System.out.println("UPDATE Arbeitsgruppe SET Aktiv='0' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");
			
				int RowsAffect = RemoteConnection.sql.executeUpdate(
				"UPDATE Arbeitsgruppe SET Aktiv ='0' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");
				
				if (RowsAffect==1)System.out.println("Es wurde "+RowsAffect+" Datensatz gelöscht.");
				erfolgreich=true;
			}
			if(aktuellerStatus==false)
			{
				
				erfolgreich=false;
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
		return erfolgreich;
	}

	/**
	 * Methode liefert den Status der Arbeitsgruppe
	 * 1: aktiv, 0: gel�scht
	 * @return
	 */
	public boolean getAktiv() {
		// TODO Auto-generated method stub
		return this.aktiv;
	}

	/**
	 * Methode liefert zur Kurzbezeichnung die dazugeh�rige ID
	 * der Arbeitsgruppe
	 * @param kurzbezeichnung
	 * @return
	 */
	public static int getIDbyKurzbezeichnung(String kurzbezeichnung) {
		int id = 0;
		RemoteConnection Connection = new RemoteConnection();
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		
		try {
			//System.out.println("SELECT ArbeitsgruppeID FROM Arbeitsgruppe WHERE Kurzbezeichnung='"+kurzbezeichnung+"'");
			ResultSet resultSet = Connection.executeQueryStatement("SELECT ArbeitsgruppeID FROM Arbeitsgruppe WHERE Kurzbezeichnung='"+kurzbezeichnung+"'");
			resultSet.next();
			id = resultSet.getInt("ArbeitsgruppeID");
			resultSet.close();
		
		} 
		
		
		catch (SQLException e) {
			System.err.println("Fehler aufgetreten in der Methode getIDByKurzbezeichnung in Arbeitsgruppe:");
			System.err.println(e.getMessage());
		}
		catch (NullPointerException e1)
		{
			System.err.println("Konnte keine Arbeitsgruppe mit dieser Kurzbezeichnung finden.");
		}
		catch (CommunicationException e2)
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
	 * Wird gebraucht um die Comboboxen zu bef�llen wo ein Mitarbeiter
	 * einer Arbeitsgruppe hinzugef�gt wird oder ge�ndert wird
	 * @return
	 */
	public static Collection<Arbeitsgruppe> getAlleArbeitsgruppen() {

		Collection<Arbeitsgruppe> result = new LinkedList<>();
		RemoteConnection Connection = new RemoteConnection();
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		ResultSet resultSet = null;
		try 
		{	
			////System.out.println("SELECT * FROM Arbeitsgruppe");
				resultSet = Connection.executeQueryStatement(
					"Select * From Arbeitsgruppe");
				
				while (resultSet.next()) 
				{	
					
					boolean aktiv = resultSet.getBoolean("Aktiv");
					if (aktiv==true)
					{
					result.add(new Arbeitsgruppe(resultSet));
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
	/**
	 * Methode liefert anhand des Suchbegriffs eine Collection
	 * von �bereinstimmungen zur�ck (In Tabellenform in der Gui auszugeben)
	 * @param suchbegriff
	 * @return
	 * @throws Exception 
	 */
	public static Collection<Arbeitsgruppe> suche(String suchbegriff) throws Exception
	{
		Collection<Arbeitsgruppe> result = new LinkedList<>();
		RemoteConnection Connection = new RemoteConnection();
		if( RemoteConnection.connection == null || RemoteConnection.sql == null )
			{
				RemoteConnection.connect();
			};
		ResultSet resultSet = null;
		try 
		{	
			System.out.println("SELECT * FROM Arbeitsgruppe WHERE ArbeitsgruppeID LIKE '%"+suchbegriff+"%' OR Leiter LIKE '%"+suchbegriff+"%' OR" +
					" Bereich LIKE '%"+suchbegriff+"%' OR Beschreibung LIKE '%"+suchbegriff+"%' OR" +
							" Kurzbezeichnung LIKE '%"+suchbegriff+"%'");
				resultSet = Connection.executeQueryStatement(
						"SELECT * FROM Arbeitsgruppe WHERE ArbeitsgruppeID LIKE '%"+suchbegriff+"%' OR Leiter LIKE '%"+suchbegriff+"%' OR" +
								" Bereich LIKE '%"+suchbegriff+"%' OR Beschreibung LIKE '%"+suchbegriff+"%' OR" +
										" Kurzbezeichnung LIKE '%"+suchbegriff+"%'");
				//Abfrage ob �berhaupt Datens�tze gefunden worden sind
				resultSet.last();
				int resultLength = resultSet.getRow();
				resultSet.beforeFirst();
				if (resultLength==0) throw new NullPointerException("Keine Datens�tze gefunden");
				else
				{
					//System.out.println("Es wurden "+resultLength+" Datens�tze gefunden. Die Gel�schten Eintr�ge werden nicht angezeigt.");
				}
				while (resultSet.next()) 
				{
					//NuR Aktive werden ausgegeben
					boolean aktiv = resultSet.getBoolean("Aktiv");
					if (aktiv==true)
					{
					result.add(new Arbeitsgruppe(resultSet));
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

}
