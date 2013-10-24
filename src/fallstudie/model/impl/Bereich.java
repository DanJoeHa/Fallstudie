package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import fallstudie.model.mysql.connector.RemoteConnection;

/**
 * @author Phil
 * @date 09.10.2013 
 * @version 1.0 
 * @change Attribute aktualisiert
 * @author Phil, 11.10.2013
 * @version 1.1 
 * @change Methoden implementiert
 */
public class Bereich {

	private String beschreibung;
	private String kurzbezeichnung;
	private boolean aktiv;
	private Mitarbeiter leiter;
	private int bereichID;
	private String leiterBenutzername;

	// -----------------------------------------------------------
	// ---------------------KONSTRUKTOREN-------------------------
	// -----------------------------------------------------------
	/**
	 * @author Phil
	 * Neuer Bereich wird mit Leiter in der Datenbank angelegt.
	 * @param String kurzbezeichnung
	 * @param String beschreibung
	 * @param Mitarbeiter leiter
	 * @throws Exception, wenn keine Kurzbezeichnung angegeben wird oder der Bereich schon existiert.
	 */

	public Bereich(String kurzbezeichnung, String beschreibung,
			Mitarbeiter leiter) throws Exception {
		String leiterBenutzername = null;
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

			if (kurzbezeichnung.equals(""))
				throw new Exception("Kurzbezeichnung muss angegeben werden.");
			if (leiter != null) {

				// ResultSet existiertMitarbeiter =
				// Connection.executeQueryStatement("SELECT Benutzername FROM Mitarbeiter WHERE Benutzername='"+leiter.getBenutzername()+"'");
				// if(!existiertMitarbeiter.next()) throw new
				// Exception("Bereichsleiter wurde nicht gefunden. Bitte jemanden anderen suchen.");
				//

				leiterBenutzername = leiter.getBenutzername();
				// System.out.println("SELECT Kurzbezeichnung From Arbeitsgruppe");
				ResultSet checkObVorhanden = RemoteConnection.sql
						.executeQuery("SELECT Kurzbezeichnung From Bereich WHERE Aktiv=1");

				while (checkObVorhanden.next()) {

					String value = checkObVorhanden
							.getString("Kurzbezeichnung");

					if (kurzbezeichnung.equals(value))
						throw new Exception(
								"Bereich mit selber Kurzbezeichnung existiert schon.");

				}
				/*
				System.out
						.println("INSERT INTO Bereich (Kurzbezeichnung, Beschreibung, Leiter)"
								+ "VALUES ('"
								+ kurzbezeichnung
								+ "', '"
								+ beschreibung
								+ "', '"
								+ leiterBenutzername
								+ "')");
				 */
				int RowsAffected = RemoteConnection.sql
						.executeUpdate("INSERT INTO Bereich (Kurzbezeichnung, Beschreibung, Leiter)"
								+ "VALUES ('"
								+ kurzbezeichnung
								+ "', '"
								+ beschreibung
								+ "', '"
								+ leiterBenutzername
								+ "')");

				this.kurzbezeichnung = kurzbezeichnung;
				this.beschreibung = beschreibung;
				this.leiter = leiter;
				if (RowsAffected == 1)
					throw new Exception("Bereich wurde erfolgreich angelegt.");
			} else if (leiter == null) {
				ResultSet checkObVorhanden = RemoteConnection.sql
						.executeQuery("SELECT Kurzbezeichnung From Bereich");

				while (checkObVorhanden.next()) {

					String value = checkObVorhanden
							.getString("Kurzbezeichnung");
					if (kurzbezeichnung.equals(value))
						throw new Exception(
								"Bereich mit selber Kurzbezeichnung existiert schon.");

				}
				/*
				System.out
						.println("INSERT INTO Bereich (Kurzbezeichnung, Beschreibung)"
								+ " VALUES ('"
								+ kurzbezeichnung
								+ "', '"
								+ beschreibung + "')");
				*/
				int RowsAffected = RemoteConnection.sql
						.executeUpdate("INSERT INTO Bereich (Kurzbezeichnung, Beschreibung) "
								+ "VALUES ('"
								+ kurzbezeichnung
								+ "', '"
								+ beschreibung + "')");

				this.kurzbezeichnung = kurzbezeichnung;
				this.beschreibung = beschreibung;
				this.leiter = null;

				if (RowsAffected == 1)
					throw new Exception("Bereich wurde erfolgreich angelegt.");

			}
		}

		catch (SQLException e) {
			System.err.println("Fehler in Bereich anlegen Konstruktor");
			System.err.println(e.getMessage());

		}
	}

	/**
	 * @author Phil
	 * Methode ist eine Volltextsuche durch alle Bereiche, welche als aktiv in der Datenbank existieren.
	 * @param String suchbegriff
	 * @return Collection<Bereich> alleBereicheNachSuchbegriff
	 * @throws Exception, falls keine Datensätze gefunden werden die dem Suchbegriff entsprechen.
	 */

	public static Collection<Bereich> suche(String suchbegriff) {
		Collection<Bereich> result = new LinkedList<>();
		RemoteConnection Connection = new RemoteConnection();
		if (RemoteConnection.connection == null || RemoteConnection.sql == null) {
			RemoteConnection.connect();
		}
		;
		ResultSet resultSet = null;
		try {
			// System.out.println("SELECT * FROM Bereich WHERE BereichID LIKE '%"+suchbegriff+"' OR Leiter LIKE '%"+suchbegriff+"' OR"
			// +
			// " Beschreibung LIKE '%"+suchbegriff+"' OR Kurzbezeichnung LIKE '%"+suchbegriff+"'");
			resultSet = Connection
					.executeQueryStatement("SELECT * FROM Bereich WHERE BereichID LIKE '%"
							+ suchbegriff
							+ "' OR Leiter LIKE '%"
							+ suchbegriff
							+ "' OR"
							+ " Beschreibung LIKE '%"
							+ suchbegriff
							+ "' OR Kurzbezeichnung LIKE '%"
							+ suchbegriff
							+ "'");
			// Abfrage ob �berhaupt Datens�tze gefunden worden sind
			resultSet.last();
			int resultLength = resultSet.getRow();
			resultSet.beforeFirst();
			if (resultLength == 0)
				throw new NullPointerException("Keine Datens�tze gefunden");
			while (resultSet.next()) {
				boolean aktiv = resultSet.getBoolean("Aktiv");
				if (aktiv == true) {
					result.add(new Bereich(resultSet));
				}
			}
			resultSet.close();
		} catch (SQLException e) {
			System.err
					.println("SELECT Statement ist fehlerhaft. Bitte �berpr�en.");
		}
		return result;

	}

	/**
	 * @author Phil
	 * Methode liefert anhand der Kurzbezeichnung eines Bereichs ein gefülltes Bereichsobjekt mit Informationen aus der Datenbank.
	 * @param String kurzbezeichnung
	 * @return Bereich bereich
	 */
	public static Bereich getBereichByName(String kurzbezeichnung) {

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

		Bereich bereich = null;

		try {

			// System.out.println("SELECT * FROM Bereich WHERE Kurzbezeichnung = '"+kurzbezeichnung+"'");

			ResultSet resultSet = Connection
					.executeQueryStatement("SELECT * FROM Bereich WHERE Kurzbezeichnung = '"
							+ kurzbezeichnung + "'");
			// Variablen f�r den sp�teren Konstruktoraufruf
			resultSet.next();
			bereich = new Bereich(resultSet);

			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Fehler in Bereich getBereichbyName");
			System.err.println(e.getMessage());
		}

		return bereich;
	}

	/**
	 * @author Phil
	 * Methode liefert ein befülltes Bereichsobjekt anhand der BereichsID(Primärschlüssel in der Datenbank).
	 * @param int bereichID
	 */

	public Bereich(int bereichID) {

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
		try { // System.out.println("SELECT * FROM Bereich WHERE BereichID='"+bereichID+"'");

			ResultSet BereichResult = Connection
					.executeQueryStatement("SELECT * FROM Bereich WHERE BereichID='"
							+ bereichID + "'");

			BereichResult.next();

			Bereich bereich = new Bereich(BereichResult);
			this.bereichID = bereich.getID();
			this.aktiv = bereich.getAktiv();
			this.beschreibung = bereich.getBeschreibung();
			this.kurzbezeichnung = bereich.getKurzbezeichnung();
			this.leiter = bereich.getLeiter();
			BereichResult.close();

		} catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist aufgetreten in Bereich (id):");
			System.err.println(e.getMessage());

		}
	}

	/**
	 * @author Phil
	 * Anhand eines ResultSets aus einer SELECT- Abfrage wird das Bereichsobjekt befüllt. 
	 * @param ResultSet resultSet
	 */
	public Bereich(ResultSet resultSet){

		try {
			if (RemoteConnection.connection == null
					|| RemoteConnection.sql == null) {
				RemoteConnection.connect();
			}
		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		try {

			// Mitarbeiterobjekt aus der ID
			this.leiterBenutzername = resultSet.getString("Leiter");

			// Mitarbeiter Resultset holen

			// Bereichsobjekt aus der BereichsID
			this.bereichID = resultSet.getInt("BereichID");
			// Beschreibung der Arbeitsgruppe
			this.beschreibung = resultSet.getString("Beschreibung");
			// Kurzbezeichnung der Arbeitsgruppe
			this.kurzbezeichnung = resultSet.getString("Kurzbezeichnung");
			// Status der Arbeitsgruppe
			this.aktiv = resultSet.getBoolean("Aktiv");

		} catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist in Bereich(ResultSet) aufgetreten:");
			System.err.println(e.getMessage());
		}

	}

	// -----------------------------------------------------------
	// ---------------------KONSTRUKTOREN-------------------------
	// -----------------------------------------------------------
	/**
	 * @author Phil
	 * Methode zum ändern der Beschreibung des gewählten Bereichs.
	 * @param String beschreibung
	 * @return boolean (erfolgreich in DB geändert = true, sonst = false).
	 */

	public boolean setBeschreibung(String beschreibung){
		boolean erfolgreich = false;
		int RowsAffected;
		try {
			//System.out.println("UPDATE Bereich SET Beschreibung='"
				//	+ beschreibung + "' WHERE BereichID='" + this.bereichID
					//+ "'");
			RowsAffected = RemoteConnection.sql
					.executeUpdate("UPDATE Bereich SET Beschreibung='"
							+ beschreibung + "' WHERE BereichID='"
							+ this.bereichID + "'");
			erfolgreich = true;

			if (RowsAffected == 0)
				erfolgreich = false;
		} catch (SQLException e) {
			System.err.println("Fehler in setBeschreibung in Bereich:");
			System.err.println(e.getMessage());
		}
		this.beschreibung = beschreibung;
		return erfolgreich;
	}

	/**
	 * @author Phil
	 * Methode liefert die Beschreibung zum aktuell gewählten Bereich
	 * @return String beschreibung
	 */
	public String getBeschreibung() {

		return this.beschreibung;
	}

	/**
	 * @author Phil
	 * Methode zum ändern der Kurzbezeichnung des gewählten Bereiches.
	 * @param kurzbezeichnung
	 * @return boolean (erfolgreich in DB geändert = true, sonst = false).
	 */
	public boolean setKurzbezeichnung(String kurzbezeichnung){
		boolean erfolgreich = true;
		RemoteConnection Connection = new RemoteConnection();
		try {
			if(kurzbezeichnung.equals(""))
			{
				erfolgreich=false;
			}
			//System.out.println("UPDATE Bereich SET Kurzbezeichnung='"
				//	+ kurzbezeichnung + "' WHERE BereichID='" + this.bereichID
					//+ "'");
			
			
			ResultSet checkObVorhanden = Connection
					.executeQueryStatement("SELECT BereichID From Bereich WHERE Aktiv=1 AND Kurzbezeichnung='"+kurzbezeichnung+"'");

			
			while (checkObVorhanden.next()) 
			{	
				//Bekommt die Kurzbezeichnung aus dem Resultset
				int ID = checkObVorhanden.getInt("BereichID");
				//System.out.println("ID: "+ID+"Aktuelle: "+this.bereichID);
				//Prüfung auf gleichheit
				if (this.bereichID==ID)
				{
					erfolgreich=true;
				}
				else
				{
					erfolgreich=false;
				}
			}
			
			if(erfolgreich==true)
			{
			
			RemoteConnection.sql
					.executeUpdate("UPDATE Bereich SET Kurzbezeichnung='"
							+ kurzbezeichnung + "' WHERE BereichID='"
							+ this.bereichID + "'");
			this.kurzbezeichnung=kurzbezeichnung;
			}
		}

		catch (SQLException e) {
			System.err.println("Fehler in setKurzbezeichnung: ");
			System.err.println(e.getMessage());
		}
		return erfolgreich;
	}

	/**
	 * @author Phil
	 * Methode liefert Kurzbezeichnung des gewählten Bereichs.
	 * @return String kurzbezeichnung
	 */
	public String getKurzbezeichnung() {

		return this.kurzbezeichnung;
	}

	/**
	 * @author Phil
	 * Methode zum löschen eines Bereichs.
	 * @return boolean (erfolgreich in DB geändert = true, sonst = false).
	 * @throws Exception, falls der Bereich noch Mitarbeiter zugeordnet hat/ein Leiter gesetzt ist.
	 */
	
	public boolean loeschen() throws Exception {
		boolean erfolgreich = false;
		boolean aktuellerStatus = this.getAktiv();
		RemoteConnection Connection = new RemoteConnection();
		boolean darfdeleteArbeitsgruppe = false;
		boolean darfdeleteMitarbeiter = false;
		boolean darfdelteLeiter = false;
		try { // Check ob mitarbeiter noch bereich zugeordnet sind

			// System.out.println("SELECT * FROM Mitarbeiter WHERE Bereich='"+this.bereichID+"'");
			ResultSet mitarbeiterdrancheck = Connection
					.executeQueryStatement("SELECT * FROM Mitarbeiter WHERE Bereich='"
							+ this.bereichID + "'");
			if (mitarbeiterdrancheck.next()) {
				darfdeleteMitarbeiter = false;

			} else if (!mitarbeiterdrancheck.next()) {
				darfdeleteMitarbeiter = true;

			}
			// check ob Leiter noch dran ist
			// System.out.println("SELECT * FROM Bereich WHERE BereichID='"+this.bereichID+"'");
			ResultSet leiterdranCheck = Connection
					.executeQueryStatement("SELECT * FROM Bereich WHERE BereichID='"
							+ this.bereichID + "'");
			leiterdranCheck.next();
			if (!leiterdranCheck.next())
				darfdelteLeiter = true;
			if (leiterdranCheck.next())
				darfdelteLeiter = false;

			// Check ob Arbeitsgruppe dran ist
			// System.out.println("SELECT * FROM Arbeitsgruppe WHERE Bereich='"+this.bereichID+"'");
			ResultSet arbeitsgruppeCheck = Connection
					.executeQueryStatement("SELECT * FROM Arbeitsgruppe WHERE Bereich='"
							+ this.bereichID + "'");

			if (arbeitsgruppeCheck.next()) {
				darfdeleteArbeitsgruppe = false;
			} else if (!arbeitsgruppeCheck.next()) {
				darfdeleteArbeitsgruppe = true;
			}

			if (darfdeleteArbeitsgruppe == true && darfdelteLeiter == true
					&& darfdeleteMitarbeiter == true) {

				if (aktuellerStatus == true) {
					// System.out.println("UPDATE Bereich SET Aktiv='0' WHERE BereichID='"+this.bereichID+"'");

					int RowsAffect = RemoteConnection.sql
							.executeUpdate("UPDATE Bereich SET Aktiv ='0' WHERE BereichID='"
									+ this.bereichID + "'");

					erfolgreich = true;

					if (RowsAffect == 1)
						throw new Exception("Es wurde 1 Datensatz gelöscht.");
				} else if (aktuellerStatus == false) {
					erfolgreich = false;
				}
			} else {
				throw new Exception(
						"Bereich darf nicht gelöscht werden. Es sind noch Arbeitsgruppen oder Mitarbeiter dran. Bitte prüfen.");
			}

		}

		catch (SQLException e) {
			System.err.println("Fehler in Bereich löschen:");
			System.err.println(e.getMessage());
		}
		this.aktiv = false;
		return erfolgreich;
	}

	/**
	 * @author Phil
	 * Methode liefert den Status des Bereichs in der Datenbank.
	 * @return boolean (true bedeutet Arbeitsgruppe ist aktiv, false bedeutet Arbeitsgruppe wird als gelöscht angezeigt).
	 */
	public boolean getAktiv() {

		return this.aktiv;
	}

	/**
	 * @author Phil
	 * Methode liefert zur Kurzbezeichnung die dazugehörige ID des Bereichs
	 * @param String kurzbezeichnung
	 * @return int bereichID
	 * @throws Exception, wenn die angegebene Kurzbezeichnung nicht in der Datenbank gefunden wurde.
	 */
	public static int getIDByKurzbezeichnung(String kurzbezeichnung) {
		int id = 0;
		RemoteConnection Connection = new RemoteConnection();

		if (RemoteConnection.connection == null || RemoteConnection.sql == null) {
			RemoteConnection.connect();
		}
		;

		try {
			// System.out.println("SELECT BereichID FROM Bereich WHERE Kurzbezeichnung='"+kurzbezeichnung+"'");
			ResultSet resultSet = Connection
					.executeQueryStatement("SELECT BereichID FROM Bereich WHERE Kurzbezeichnung='"
							+ kurzbezeichnung + "'");
			resultSet.next();
			id = resultSet.getInt("BereichID");

		}

		catch (SQLException e) {
			System.err
					.println("Fehler ist in Methode getIDByKurzbezeichnung(String) aufgetreten:");
			System.err.println(e.getMessage());
		} 
		return id;
	}

	/**
	 * @author Phil
	 * Methode liefert BereichID zum Bereich (BereichID ist der Primärschlüssel in der Datenbank).
	 * @return int bereichID
	 */
	public int getID() {
		return this.bereichID;
	}

	/**
	 * @author Phil
	 * Methode liefert alle Bereiche, welche in der Datenbank existieren und aktiv sind, als Collection von Bereichsobjekten.
	 * @return Collection<Bereich> alleBereiche
	 */
	public static Collection<Bereich> getAlleBereiche() {

		Collection<Bereich> result = new LinkedList<>();
		RemoteConnection Connection = new RemoteConnection();

		if (RemoteConnection.connection == null || RemoteConnection.sql == null) {
			RemoteConnection.connect();
		}
		;

		try {
			// System.out.println("SELECT * FROM Bereich WHERE Aktiv='1'");
			ResultSet resultSet = Connection
					.executeQueryStatement("Select * From Bereich WHERE Aktiv='1'");

			while (resultSet.next()) {
				// System.out.println(resultSet.getString("Kurzbezeichnung"));
				result.add(new Bereich(resultSet));
			}

			resultSet.close();
		} catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist aufgetreten in getAlleBereiche():");
			System.err.println(e.getMessage());
		}
		return result;
	}

	/**
	 * @author Phil
	 * Methode ändert den Leiter eines Bereichs.
	 * @param Mitarbeiter leiter(Objekt)
	 * @return boolean (erfolgreich in DB geändert = true, sonst = false).
	 */
	public boolean setLeiter(Mitarbeiter mitarbeiter) throws Exception {

		boolean erfolgreich = false;
		// Mitgegebener Bereich ID
		try {
			if (!(mitarbeiter == null)) {
				String neuerLeiterBenutzername = mitarbeiter.getBenutzername();
/*
				System.out.println("UPDATE Bereich SET Leiter ='"
						+ neuerLeiterBenutzername + "' WHERE BereichID='"
						+ this.bereichID + "'");
*/
				int RowsAffect = RemoteConnection.sql
						.executeUpdate("UPDATE Bereich SET Leiter ='"
								+ neuerLeiterBenutzername
								+ "' WHERE BereichID='" + this.bereichID + "'");
				RemoteConnection.sql
						.executeUpdate("UPDATE Mitarbeiter SET Bereich='"
								+ this.bereichID + "' WHERE Benutzername='"
								+ neuerLeiterBenutzername + "'");

				erfolgreich = true;
				this.leiter = mitarbeiter;
				if (RowsAffect == 1)
					throw new Exception("Bereich wurde erfolgreich bearbeitet.");

			} else if (mitarbeiter == null) {
				//System.out
					//	.println("UPDATE Bereich SET Leiter =NULL WHERE BereichID='"
						//		+ this.bereichID + "'");

				int RowsAffect = RemoteConnection.sql
						.executeUpdate("UPDATE Bereich SET Leiter =NULL WHERE BereichID='"
								+ this.bereichID + "'");

				erfolgreich = true;
				this.leiter = null;
				if (RowsAffect == 1)
					throw new Exception("Bereich wurde erfolgreich bearbeitet.");

			}

		}

		catch (SQLException e) {
			System.err.println("Fehler in SetLeiter (Mitarbeiter) in Bereich:");
			System.err.println(e.getMessage());
		}
		return erfolgreich;
	}

	/**
	 * @author Phil
	 * Methode liefert den Leiter des gewählten Bereichs als Mitarbeiterobjekt.
	 * @return Mitarbeiter leiter
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
			System.err.println("fehler in getLeiter():");
			System.err.println(e.getMessage());
		}
		return this.leiter;

	}

}
