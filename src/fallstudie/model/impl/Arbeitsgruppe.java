package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import fallstudie.model.mysql.connector.RemoteConnection;

/**
 * CHANGELOG
 * 
 * @author Phil, 09.10.2013 generiert + implements (Interface) wurde entfernt,
 *         da Konstruktor nicht m�glich ist im Interface
 * @author Phil 11.10.2013
 * @author Phil, 12.10.2013
 * @version 1.1
 * @change Alle Methoden implementiert.
 */
public class Arbeitsgruppe {
	//Attribute werden gesetzt
	private String beschreibung;
	private String kurzbezeichnung;
	private int arbeitsgruppeID;
	private Bereich bereich;
	private boolean aktiv;
	private Mitarbeiter leiter;
	public ResultSet resultSet;
	private String leiterBenutzername;
	private int bereichID;

	// -----------------------------------------------------------
	// ---------------------KONSTRUKTOREN-------------------------
	// -----------------------------------------------------------

	/**
	 * Neue Arbeitsgruppe anlegen Konstruktor mit allen Parametern
	 * 
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
		//DB Connection herstellen
		RemoteConnection Connection = new RemoteConnection();
		try {
			if (RemoteConnection.connection == null
					|| RemoteConnection.sql == null) {
				RemoteConnection.connect();
			}
			;

		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		
		try {	
				//Abfangen auf Kurzbezeichnung ist leer
				if (kurzbezeichnung.equals(""))
				{
					throw new Exception("Kurzbezeichnung muss angegeben werden.");
				}
				
				//Prüfung auf Redundanz der Kurzbezeichnung der Arbeitsgruppe
				ResultSet checkObVorhanden = Connection
						.executeQueryStatement("SELECT Kurzbezeichnung From Arbeitsgruppe WHERE Aktiv=1");

				while (checkObVorhanden.next()) 
				{
					//Bekommt die Kurzbezeichnung aus dem Resultset
					String value = checkObVorhanden.getString("Kurzbezeichnung");
					//Prüfung
					if (kurzbezeichnung.equals(value))
					{
						throw new Exception(
								"Arbeitgsuppe mit der selben Kurzbezeichnung existiert schon.");
					}
	
				}
				//Prüfung auf Redundanz ENDE
				//Prüfung ob Leiter angegeben wurde
				if (leiter != null) {
					
				/*	
					System.out
							.println("INSERT INTO Arbeitsgruppe (Kurzbezeichnung, Beschreibung, Bereich, Leiter) VALUES ('"
									+ kurzbezeichnung
									+ "','"
									+ beschreibung
									+ "','"
									+ bereichID
									+ "','"
									+ leiter.getBenutzername()
									+ "')");
				 */
				//Insert auf die Datenbank
					int RowsAffected = RemoteConnection.sql
							.executeUpdate("INSERT INTO Arbeitsgruppe (Kurzbezeichnung, Beschreibung, Bereich, Leiter) VALUES ('"
									+ kurzbezeichnung
									+ "','"
									+ beschreibung
									+ "','"
									+ bereich.getID()
									+ "','"
									+ leiter.getBenutzername()
									+ "')");
					
				//Setzen der Instanzattribute
					this.beschreibung = beschreibung;
					this.kurzbezeichnung = kurzbezeichnung;
					this.bereich = bereich;
					this.leiter = leiter;
				
				//Exception beim Anlegen der Arbeitsgruppe
					if (RowsAffected == 1)
					{
						throw new Exception("Arbeitsgruppe erfolgreich angelegt.");
					}
				//END-IF
					
				}
				//Else wenn der Leiter NULL ist, dann wird die Arbeitsgruppe ohne Leiter angelegt.
				else
				{
					
					/*
					System.out
							.println("INSERT INTO Arbeitsgruppe (Kurzbezeichnung, Beschreibung, Bereich) VALUES ('"
									+ kurzbezeichnung
									+ "','"
									+ beschreibung
									+ "','" + bereichID + "')");
					 */
					//Insert auf die Datenbank
					int RowsAffected = RemoteConnection.sql
							.executeUpdate("INSERT INTO Arbeitsgruppe (Kurzbezeichnung, Beschreibung, Bereich) VALUES ('"
									+ kurzbezeichnung
									+ "','"
									+ beschreibung
									+ "','" + bereich.getID() + "')");
					
					//Setzen der Instanzattribute
					this.beschreibung = beschreibung;
					this.kurzbezeichnung = kurzbezeichnung;
					this.bereich = bereich;
					this.leiter = null;
	
					//Exception beim Anlegen der Arbeitsgruppe
					if (RowsAffected == 1)
					{
						throw new Exception("Arbeitsgruppe erfolgreich angelegt.");
					}
					//END-IF

			}//END IF Leiter abfrage

		}//END TRY

		catch (SQLException e) {
			System.err.println("Fehler im Arbeitsgruppe-Konstruktor mit Leiter:");
			System.err.println(e.getMessage());
			
		}

	}

	/**
	 * Durch überreichen des Resultsets werden die Objekte vom Typ Arbeitsgruppe erzeugt
	 * 
	 * @param resultSet
	 */
	public Arbeitsgruppe(ResultSet resultSet){
		//DB Connection abfragen
		try 
		{
				if (RemoteConnection.connection == null || RemoteConnection.sql == null)
				{
				RemoteConnection.connect();
				}

		}
		catch (NullPointerException e)
		{
			System.err.println(e.getMessage());
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		//END TRY DB Connection
		
		
		//START TRY Befüllen der Instanzobjekte
		try 
		{
			
			// ID der Arbeitsgruppe
			this.arbeitsgruppeID = resultSet.getInt("ArbeitsgruppeID");
			// Mitarbeiterobjekt aus der ID
			this.leiterBenutzername = resultSet.getString("Leiter");
			// Bereichobjekt aus der BereichsID
			this.bereichID = resultSet.getInt("Bereich");
			// Beschreibung der Arbeitsgruppe
			this.beschreibung = resultSet.getString("Beschreibung");
			// Kurzbezeichnung der Arbeitsgruppe
			this.kurzbezeichnung = resultSet.getString("Kurzbezeichnung");
			// Status der Arbeitsgruppe
			this.aktiv = resultSet.getBoolean("Aktiv");
			// Bereich aus der ID generieren

		}//END TRY 
		catch (SQLException e)
		{
			System.err.println("Dieser Fehler ist aufgetretn in Arbeitsgruppe (ResultSet):");
			System.err.println(e.getMessage());
		}

	}

	/**
	 * ArbeitsgruppeObjekt per ID (Primärschlüssel) erzeugen
	 * 
	 * @param arbeitsgruppeid
	 */

	public Arbeitsgruppe(int arbeitsgruppeid) {
		//Instanziieren der DatenbankConnection
		RemoteConnection Connection = new RemoteConnection();
		//DB Connection abfragen
		try 
		{
				if (RemoteConnection.connection == null || RemoteConnection.sql == null)
				{
				RemoteConnection.connect();
				}

		}
		catch (NullPointerException e)
		{
			System.err.println(e.getMessage());
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		//END TRY DB Connection
		try
		{ 
			// System.out.println("SELECT * FROM Arbeitsgruppe WHERE ArbeitsgruppeID='"+arbeitsgruppeid+"'");
			//ResultSet aus der Datenbank anhand der ID
			ResultSet resultSet = Connection.executeQueryStatement("SELECT * FROM Arbeitsgruppe WHERE ArbeitsgruppeID='"+ arbeitsgruppeid + "'");
				resultSet.next();
			//Neue Arbeitsgruppe
			Arbeitsgruppe ag = new Arbeitsgruppe(resultSet);
			//ID der Arbeitsgruppe
			this.arbeitsgruppeID = arbeitsgruppeid;
			//Aktiv der Arbeitsgruppe
			this.aktiv = ag.getAktiv();
			//Bereich ID
			this.bereich = ag.getBereich();
			//Beschreibung
			this.beschreibung = ag.getBeschreibung();
			//Kurzbezeichnung
			this.kurzbezeichnung = ag.getKurzbezeichnung();
			//Leiter wird gesetzt
			this.leiter = ag.getLeiter();

		} 
		catch (SQLException e) 
		{
			System.err.println("Dieser Fehler ist aufgetreten in Arbeitsgruppe (id)");
			System.err.println(e.getMessage());
		}
	}

	
	// -----------------------------------------------------------
	// ---------------------KONSTRUKTOREN-------------------------
	// -----------------------------------------------------------

	/**
	 * Mehode ändert die Beschreibung einer Arbeitsgruppe.
	 * @param String
	 * @return boolean
	 */
	public boolean setBeschreibung(String beschreibung) {
		boolean erfolgreich = false;
		
		try 
		{	/*
			System.out.println("UPDATE Arbeitsgruppe SET Beschreibung='"
					+ beschreibung + "' WHERE ArbeitsgruppeID='"
					+ this.arbeitsgruppeID + "'");
			*/
			//Beschreibung wird auf der Datenbank geändert
			
			RemoteConnection.sql
					.executeUpdate("UPDATE Arbeitsgruppe SET Beschreibung='"
							+ beschreibung + "' WHERE ArbeitsgruppeID='"
							+ this.arbeitsgruppeID + "'");

			erfolgreich = true;

		}	//END TRY 
		catch (SQLException e)
		{
			System.err.println("Fehler in SetBeschreibung Arbeitsgrupppe");
			System.err.println(e.getMessage());
		}
		return erfolgreich;
	}

	/**
	 * Methode liefert Beschreibung des Arbeitsgruppenobjekts
	 * 
	 * @return String
	 */
	public String getBeschreibung() {

		return this.beschreibung;
	}

	/**
	 * Methode ändert Kurzbezeichnung der Arbeitsgruppe
	 * 
	 * @param kurzbezeichnung
	 * @return
	 * @throws Exception
	 */
	public boolean setKurzbezeichnung(String kurzbezeichnung) throws Exception {
		RemoteConnection Connection = new RemoteConnection();
		
		boolean erfolgreich = true;
		try{			
				//Abfangen auf Kurzbezeichnung ist leer
				if (kurzbezeichnung.equals(""))
				{
					erfolgreich=false;
				}
				
				//Prüfung auf Redundanz der Kurzbezeichnung der Arbeitsgruppe
				ResultSet checkObVorhanden = Connection
						.executeQueryStatement("SELECT Kurzbezeichnung From Arbeitsgruppe WHERE Aktiv=1");
	
				while (checkObVorhanden.next()) 
				{	
					//Bekommt die Kurzbezeichnung aus dem Resultset
					String value = checkObVorhanden.getString("Kurzbezeichnung");
					//Prüfung auf gleichheit
					if (kurzbezeichnung.equals(value))
					{
						erfolgreich=false;
					}
				}
			
				/*	System.out.println("UPDATE Arbeitsgruppe SET Kurzbezeichnung='"
						+ kurzbezeichnung + "' WHERE ArbeitsgruppeID='"
						+ this.arbeitsgruppeID + "'");
				*/
				//Wenn keine Redundanzen vorliegen
				if(erfolgreich==true)
				{
					RemoteConnection.sql
							.executeUpdate("UPDATE Arbeitsgruppe SET Kurzbezeichnung='"
									+ kurzbezeichnung
									+ "' WHERE ArbeitsgruppeID='"
									+ this.arbeitsgruppeID + "'");
				}

		
		} 
		catch (SQLException e) 
		{
			System.err.println("SQL ERROR in setKurzbezeichnung bei Arbeitsgruppe:");
			System.err.println(e.getMessage());
		}
		return erfolgreich;
	}

	/**
	 * Methode liefert Kurzbezeichnung der Arbeitsgruppe
	 * 
	 * @return
	 */

	public String getKurzbezeichnung() {

		return this.kurzbezeichnung;
	}

	/**
	 * Methode �ndert Bereich der arbeitsgruppe
	 * 
	 * @param bereich
	 * @return
	 */

	public boolean setBereich(Bereich bereich) {
		boolean erfolgreich = false;
		//Update in der Datenbank
		try 
		{
			
			//System.out.println("UPDATE Arbeitsgruppe SET Bereich ='"+bereich.getID()+"' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");

			RemoteConnection.sql
					.executeUpdate("UPDATE Arbeitsgruppe SET Bereich ='"
							+ bereich.getID() + "' WHERE ArbeitsgruppeID='"
							+ this.arbeitsgruppeID + "'");
			erfolgreich = true;

		}

		catch (SQLException e) {
			System.err.println("Fehler bei setBereich: Arbeitsgruppe");
			System.err.println(e.getMessage());
		}
		return erfolgreich;
	}

	/**
	 * Methode liefert Bereich der Arbeitsgruppe als Objekt zurück
	 * 
	 * @return Bereich
	 */

	public Bereich getBereich() {
		//Wenn bereits ein Bereich instanziiert wurde
		if (this.bereich != null)
			return this.bereich;
		//Wenn bei der Instanziierung der Arbeitsgruppe kein Bereich gefunden wurde, wurde das Attribut 
		//BereichID=0 gesetzt und somit wird ein leerer Bereich zurückgegeben. Ansonsten wird der Bereich 
		//aus der ID generiert.
		if (bereichID != 0) {
			this.bereich = new Bereich(bereichID);
		} else {
			this.bereich = null;
		}

		return this.bereich;

	}

	/**
	 * Methode �ndert Leiter der ARbeitsgruppe, �bergeben wird ein
	 * Mitarbeiterobjekt
	 * 
	 * @param leiter
	 * @return boolean ob erfolgreich
	 * @throws Exception
	 */
	public boolean setLeiter(Mitarbeiter leiter) {
		boolean erfolgreich = false;

		try {

			if (!(leiter == null)) {

				String neuerLeiterBenutzername = leiter.getBenutzername();
				System.out.println("UPDATE Arbeitsgruppe SET Leiter ='"
						+ neuerLeiterBenutzername + "' WHERE ArbeitsgruppeID='"
						+ this.arbeitsgruppeID + "'");
				RemoteConnection.sql
						.executeUpdate("UPDATE Arbeitsgruppe SET Leiter ='"
								+ neuerLeiterBenutzername
								+ "' WHERE ArbeitsgruppeID='"
								+ this.arbeitsgruppeID + "'");
				System.out.println("UPDATE Mitarbeiter SET Arbeitsgruppe='"
						+ this.arbeitsgruppeID + "' WHERE Benutzername='"
						+ neuerLeiterBenutzername + "'");
				RemoteConnection.sql
						.executeUpdate("UPDATE Mitarbeiter SET Arbeitsgruppe='"
								+ this.arbeitsgruppeID
								+ "' WHERE Benutzername='"
								+ neuerLeiterBenutzername + "'");
				erfolgreich = true;
				this.leiter = leiter;

			} else if (leiter == null) {
				System.out
						.println("UPDATE Arbeitsgruppe SET Leiter=NULL WHERE ArbeitsgruppeID='"
								+ this.arbeitsgruppeID + "'");

				RemoteConnection.sql
						.executeUpdate("UPDATE Arbeitsgruppe SET Leiter=NULL WHERE ArbeitsgruppeID='"
								+ this.arbeitsgruppeID + "'");
				erfolgreich = true;
				this.leiter = null;
			}
		} catch (SQLException e) {
			System.err.println("fehler in setLeiter");
			System.err.println(e.getMessage());
		}

		return erfolgreich;
	}

	/**
	 * Methode liefert Leiter der Arbeitsgruppe anhand des Benutzernamens der in
	 * der Tabelle Mitarbeiter generiert wird
	 * 
	 * @return
	 */
	public Mitarbeiter getLeiter() {

		try {
			if (this.leiter != null) {
				return this.leiter;
			}
			if (this.leiterBenutzername != null) {
				// System.out.println("SELECT * FROM Mitarbeiter WHERE Benutzername ='"+this.leiterBenutzername+"'");
				ResultSet mitarbeiterResult = RemoteConnection.sql
						.executeQuery("SELECT * FROM Mitarbeiter WHERE Benutzername ='"
								+ this.leiterBenutzername + "'");

				mitarbeiterResult.next();

				this.leiter = new Mitarbeiter(mitarbeiterResult);
				;
			} else {
				this.leiter = null;
			}
		} catch (SQLException e) {
			System.err.println("fehler in Arbeitsgruppe.getLeiter():");
			System.err.println(e.getMessage());
		}
		return this.leiter;

	}

	/**
	 * Methode l�scht die Arbeitsgruppe -> wird auf Inaktiv gesetzt in der DB
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean loeschen() throws Exception {
		boolean erfolgreich = false;
		boolean aktuellerStatus = this.aktiv;

		boolean darfDeleteLeiter = false;
		boolean darfDeleteMitarbeiter = false;
		RemoteConnection Connection = new RemoteConnection();

		if (aktuellerStatus == true) {
			try {
				// System.out.println("SELECT * FROM Arbeitsgruppe WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");

				ResultSet checkLeiter = Connection
						.executeQueryStatement("SELECT * FROM Arbeitsgruppe WHERE ArbeitsgruppeID='"
								+ this.arbeitsgruppeID + "'");
				checkLeiter.next();
				if (!checkLeiter.next()) {
					darfDeleteLeiter = true;
				}
				if (checkLeiter.next()) {
					darfDeleteLeiter = false;
				}
				// System.out.println("SELECT * FROM Mitarbeiter WHERE Arbeitsgruppe='"+this.arbeitsgruppeID+"'");

				ResultSet mitarbeiterdrancheck = Connection
						.executeQueryStatement("SELECT * FROM Mitarbeiter WHERE Arbeitsgruppe='"
								+ this.arbeitsgruppeID + "'");
				if (mitarbeiterdrancheck.next()) {
					darfDeleteMitarbeiter = false;
				} else {

					darfDeleteMitarbeiter = true;
				}

				if (darfDeleteLeiter == false || darfDeleteMitarbeiter == false) {
					throw new Exception(
							"Bitte alle Mitarbeiter aus dieser Arbeitsgruppe entfernen oder einer Anderen zuordnen.");
				}
				checkLeiter.close();
				mitarbeiterdrancheck.close();

				// Erst fragen ob kein Leiter mehr da ist.
				if (darfDeleteLeiter == true && darfDeleteMitarbeiter == true) {

					System.out
							.println("UPDATE Arbeitsgruppe SET Aktiv='0' WHERE ArbeitsgruppeID='"
									+ this.arbeitsgruppeID + "'");

					int RowsAffect = RemoteConnection.sql
							.executeUpdate("UPDATE Arbeitsgruppe SET Aktiv ='0' WHERE ArbeitsgruppeID='"
									+ this.arbeitsgruppeID + "'");
					this.aktiv = false;
					erfolgreich = true;
					if (RowsAffect == 1)
						throw new Exception("Es wurde 1 Datensatz gelöscht.");

				}

			} catch (SQLException e) {
				System.err.println("Fehler beim löschen der Arbeitsgruppe:");
				System.err.println(e.getMessage());
			}
		}
		return erfolgreich;
	}

	/**
	 * Methode liefert den Status der Arbeitsgruppe 1: aktiv, 0: gel�scht
	 * 
	 * @return
	 */
	public boolean getAktiv() {
		// TODO Auto-generated method stub
		return this.aktiv;
	}

	/**
	 * Methode liefert zur Kurzbezeichnung die dazugeh�rige ID der Arbeitsgruppe
	 * 
	 * @param kurzbezeichnung
	 * @return
	 * @throws Exception 
	 */
	public static int getIDbyKurzbezeichnung(String kurzbezeichnung) throws Exception {
		int id = 0;
		RemoteConnection Connection = new RemoteConnection();
		if (RemoteConnection.connection == null || RemoteConnection.sql == null) {
			RemoteConnection.connect();
		}
		;

		try {
			// System.out.println("SELECT ArbeitsgruppeID FROM Arbeitsgruppe WHERE Kurzbezeichnung='"+kurzbezeichnung+"'");
			ResultSet resultSet = Connection
					.executeQueryStatement("SELECT ArbeitsgruppeID FROM Arbeitsgruppe WHERE Kurzbezeichnung='"
							+ kurzbezeichnung + "'");
			if(resultSet.next())
			{
				id = resultSet.getInt("ArbeitsgruppeID");
				resultSet.close();
			}
			else if(!resultSet.next())
			{
				throw new Exception("Arbeitsgruppe mit dieser Kurzbezeichnung existiert nicht.");
			}
		}

		catch (SQLException e) {
			System.err
					.println("Fehler aufgetreten in der Methode getIDByKurzbezeichnung in Arbeitsgruppe:");
			System.err.println(e.getMessage());
		} 
		return id;
	}

	/**
	 * Methode liefert ID zur Arbeitsgruppe
	 * 
	 * @return
	 */
	public int getID() {
		return this.arbeitsgruppeID;
	}

	/**
	 * Wird gebraucht um die Comboboxen zu bef�llen wo ein Mitarbeiter einer
	 * Arbeitsgruppe hinzugef�gt wird oder ge�ndert wird
	 * 
	 * @return
	 */
	public static Collection<Arbeitsgruppe> getAlleArbeitsgruppen() {

		Collection<Arbeitsgruppe> result = new LinkedList<>();
		RemoteConnection Connection = new RemoteConnection();
		if (RemoteConnection.connection == null || RemoteConnection.sql == null) {
			RemoteConnection.connect();
		}
		;
		ResultSet resultSet = null;
		try {
			// //System.out.println("SELECT * FROM Arbeitsgruppe");
			resultSet = Connection
					.executeQueryStatement("Select * From Arbeitsgruppe");

			while (resultSet.next()) {

				boolean aktiv = resultSet.getBoolean("Aktiv");
				if (aktiv == true) {
					result.add(new Arbeitsgruppe(resultSet));
				}

			}
			resultSet.close();
		} catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist aufgetreten in getAlleArbeitsgruppen():");
			System.err.println(e.getMessage());
		}
		return result;
	}

	/**
	 * Methode liefert anhand des Suchbegriffs eine Collection von
	 * �bereinstimmungen zur�ck (In Tabellenform in der Gui auszugeben)
	 * 
	 * @param suchbegriff
	 * @return
	 * @throws Exception
	 */
	public static Collection<Arbeitsgruppe> suche(String suchbegriff)
			throws Exception {
		Collection<Arbeitsgruppe> result = new LinkedList<>();
		RemoteConnection Connection = new RemoteConnection();
		if (RemoteConnection.connection == null || RemoteConnection.sql == null) {
			RemoteConnection.connect();
		}
		;
		ResultSet resultSet = null;
		try {
			// System.out.println("SELECT * FROM Arbeitsgruppe WHERE ArbeitsgruppeID LIKE '%"+suchbegriff+"%' OR Leiter LIKE '%"+suchbegriff+"%' OR"
			// +
			// " Bereich LIKE '%"+suchbegriff+"%' OR Beschreibung LIKE '%"+suchbegriff+"%' OR"
			// +
			// " Kurzbezeichnung LIKE '%"+suchbegriff+"%'");
			resultSet = Connection
					.executeQueryStatement("SELECT * FROM Arbeitsgruppe WHERE ArbeitsgruppeID LIKE '%"
							+ suchbegriff
							+ "%' OR Leiter LIKE '%"
							+ suchbegriff
							+ "%' OR"
							+ " Bereich LIKE '%"
							+ suchbegriff
							+ "%' OR Beschreibung LIKE '%"
							+ suchbegriff
							+ "%' OR"
							+ " Kurzbezeichnung LIKE '%" + suchbegriff + "%'");
			// Abfrage ob �berhaupt Datens�tze gefunden worden sind
			resultSet.last();
			int resultLength = resultSet.getRow();
			resultSet.beforeFirst();
			if (resultLength == 0)
				throw new NullPointerException("Keine Datensätze gefunden");

			while (resultSet.next()) {
				// NuR Aktive werden ausgegeben
				boolean aktiv = resultSet.getBoolean("Aktiv");
				if (aktiv == true) {
					result.add(new Arbeitsgruppe(resultSet));
				}
			}
			resultSet.close();
		} catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist aufgetreten in suche Arbeitsgruppe (suchbegriff):");
			System.err
					.println("Select Statement ist fehlerhaft. Bitte �berpr�fen.");
		}
		return result;
	}

}
