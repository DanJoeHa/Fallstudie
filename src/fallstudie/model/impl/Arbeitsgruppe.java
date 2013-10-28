package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import fallstudie.model.mysql.connector.RemoteConnection;

/**
 * @author Phil,
 * @date 12.10.2013
 * @version 1.1
 * @change Alle Methoden implementiert.
 */
public class Arbeitsgruppe {
	// Attribute werden gesetzt
	private String beschreibung;
	private String kurzbezeichnung;
	private int arbeitsgruppeID;
	private Bereich bereich;
	private boolean aktiv;
	private Mitarbeiter leiter;
	private String leiterBenutzername;
	private int bereichID;

	// -----------------------------------------------------------
	// ---------------------KONSTRUKTOREN-------------------------
	// -----------------------------------------------------------

	/**
	 * @author Phil Neue Arbeitsgruppe wird in der Datenbank angelegt.
	 * @param kurzbezeichnung
	 * @param beschreibung
	 * @param bereich
	 * @param leiter
	 * @throws Exception
	 *             , wenn keine Kurzbezeichnung angegeben wurde oder die
	 *             Arbeitsgruppe schon existiert.
	 */
	public Arbeitsgruppe(String kurzbezeichnung, String beschreibung,
			Bereich bereich, Mitarbeiter leiter) throws Exception {
		// DB Connection herstellen
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

			kurzbezeichnung = kurzbezeichnung.replace('\'', ' ');
			beschreibung = beschreibung.replace('\'', ' ');

			// Abfangen auf Kurzbezeichnung ist leer
			if (kurzbezeichnung.equals("")) {
				throw new Exception("Kurzbezeichnung muss angegeben werden.");
			}

			// Prüfung auf Redundanz der Kurzbezeichnung der Arbeitsgruppe
			ResultSet checkObVorhanden = Connection
					.executeQueryStatement("SELECT Kurzbezeichnung From Arbeitsgruppe WHERE Aktiv=1");

			while (checkObVorhanden.next()) {
				// Bekommt die Kurzbezeichnung aus dem Resultset
				String value = checkObVorhanden.getString("Kurzbezeichnung");
				// Prüfung
				if (kurzbezeichnung.equals(value)) {
					throw new Exception(
							"Arbeitgsuppe mit der selben Kurzbezeichnung existiert schon.");
				}

			}
			// Prüfung auf Redundanz ENDE
			// Prüfung ob Leiter angegeben wurde
			if (leiter != null) {

				/*
				 * System.out .println(
				 * "INSERT INTO Arbeitsgruppe (Kurzbezeichnung, Beschreibung, Bereich, Leiter) VALUES ('"
				 * + kurzbezeichnung + "','" + beschreibung + "','" + bereichID
				 * + "','" + leiter.getBenutzername() + "')");
				 */
				// Insert auf die Datenbank
				int RowsAffected = RemoteConnection.sql
						.executeUpdate("INSERT INTO Arbeitsgruppe (Kurzbezeichnung, Beschreibung, Bereich, Leiter) VALUES ('"
								+ kurzbezeichnung
								+ "','"
								+ beschreibung
								+ "','"
								+ bereich.getID()
								+ "','"
								+ leiter.getBenutzername() + "')");

				// Setzen der Instanzattribute
				this.beschreibung = beschreibung;
				this.kurzbezeichnung = kurzbezeichnung;
				this.bereich = bereich;
				this.leiter = leiter;

				// Exception beim Anlegen der Arbeitsgruppe
				if (RowsAffected == 1) {
					throw new Exception("Arbeitsgruppe erfolgreich angelegt.");
				}
				// END-IF

			}
			// Else wenn der Leiter NULL ist, dann wird die Arbeitsgruppe ohne
			// Leiter angelegt.
			else {

				/*
				 * System.out .println(
				 * "INSERT INTO Arbeitsgruppe (Kurzbezeichnung, Beschreibung, Bereich) VALUES ('"
				 * + kurzbezeichnung + "','" + beschreibung + "','" + bereichID
				 * + "')");
				 */
				// Insert auf die Datenbank
				int RowsAffected = RemoteConnection.sql
						.executeUpdate("INSERT INTO Arbeitsgruppe (Kurzbezeichnung, Beschreibung, Bereich) VALUES ('"
								+ kurzbezeichnung
								+ "','"
								+ beschreibung
								+ "','" + bereich.getID() + "')");

				// Setzen der Instanzattribute
				this.beschreibung = beschreibung;
				this.kurzbezeichnung = kurzbezeichnung;
				this.bereich = bereich;
				this.leiter = null;

				// Exception beim Anlegen der Arbeitsgruppe
				if (RowsAffected == 1) {
					throw new Exception("Arbeitsgruppe erfolgreich angelegt.");
				}
				// END-IF

			}// END IF Leiter abfrage
		}// END TRY

		catch (SQLException e) {
			System.err
					.println("Fehler im Arbeitsgruppe-Konstruktor mit Leiter:");
			System.err.println(e.getMessage());

		}

	}

	/**
	 * @author Phil Anhand eines ResultSets aus einer SELECT- Abfrage wird das
	 *         Arbeitsgruppenobjekt befüllt.
	 * @param ResultSet
	 *            resultSet
	 */
	public Arbeitsgruppe(ResultSet resultSet) {
		// DB Connection abfragen
		try {
			if (RemoteConnection.connection == null
					|| RemoteConnection.sql == null) {
				RemoteConnection.connect();
			}

		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		// END TRY DB Connection

		// START TRY Befüllen der Instanzobjekte
		try {

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

		}// END TRY
		catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist aufgetretn in Arbeitsgruppe (ResultSet):");
			System.err.println(e.getMessage());
		}

	}

	/**
	 * @author Phil Aus der Datenbank die Attribute der Arbeitsgruppe erhalten,
	 *         zu der mitgegebenen ArbeitsgruppenID (Primärschlüssel in
	 *         Datenbank).
	 * @param arbeitsgruppeid
	 */

	public Arbeitsgruppe(int arbeitsgruppeid) {
		// Instanziieren der DatenbankConnection
		RemoteConnection Connection = new RemoteConnection();
		// DB Connection abfragen
		try {
			if (RemoteConnection.connection == null
					|| RemoteConnection.sql == null) {
				RemoteConnection.connect();
			}

		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		// END TRY DB Connection
		try {
			// System.out.println("SELECT * FROM Arbeitsgruppe WHERE ArbeitsgruppeID='"+arbeitsgruppeid+"'");
			// ResultSet aus der Datenbank anhand der ID
			ResultSet resultSet = Connection
					.executeQueryStatement("SELECT * FROM Arbeitsgruppe WHERE ArbeitsgruppeID='"
							+ arbeitsgruppeid + "'");
			resultSet.next();
			// Neue Arbeitsgruppe
			Arbeitsgruppe ag = new Arbeitsgruppe(resultSet);
			// ID der Arbeitsgruppe
			this.arbeitsgruppeID = arbeitsgruppeid;
			// Aktiv der Arbeitsgruppe
			this.aktiv = ag.getAktiv();
			// Bereich ID
			this.bereich = ag.getBereich();
			// Beschreibung
			this.beschreibung = ag.getBeschreibung();
			// Kurzbezeichnung
			this.kurzbezeichnung = ag.getKurzbezeichnung();
			// Leiter wird gesetzt
			this.leiter = ag.getLeiter();

		} catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist aufgetreten in Arbeitsgruppe (id)");
			System.err.println(e.getMessage());
		}
	}

	// -----------------------------------------------------------
	// ---------------------KONSTRUKTOREN-------------------------
	// -----------------------------------------------------------

	/**
	 * @author Phil Methoden zum ändern der Beschreibung einer Arbeitsgruppe.
	 * @param beschreibung
	 * @return boolean (erfolgreich in DB geändert = true, sonst = false).
	 */
	public boolean setBeschreibung(String beschreibung) {
		boolean erfolgreich = false;
		beschreibung = beschreibung.replace('\'', ' ');
		try {
			if (beschreibung.contains("'")) {
				/*
				 * System.out.println("UPDATE Arbeitsgruppe SET Beschreibung='"
				 * + beschreibung + "' WHERE ArbeitsgruppeID='" +
				 * this.arbeitsgruppeID + "'");
				 */
				// Beschreibung wird auf der Datenbank geändert

				RemoteConnection.sql
						.executeUpdate("UPDATE Arbeitsgruppe SET Beschreibung='"
								+ beschreibung
								+ "' WHERE ArbeitsgruppeID='"
								+ this.arbeitsgruppeID + "'");

				erfolgreich = true;
			}
		} // END TRY
		catch (SQLException e) {
			System.err.println("Fehler in SetBeschreibung Arbeitsgrupppe");
			System.err.println(e.getMessage());
		}
		return erfolgreich;
	}

	/**
	 * @author Phil Methode liefert Beschreibung der Arbeitsgruppe.
	 * @return String beschreibung
	 */
	public String getBeschreibung() {

		return this.beschreibung;
	}

	/**
	 * @author Phil Methode zum ändern der Kurzbezeichnung der gewählten
	 *         Arbeitsgruppe.
	 * @param kurzbezeichnung
	 * @return boolean (erfolgreich in DB geändert = true, sonst = false).
	 */
	public boolean setKurzbezeichnung(String kurzbezeichnung) {
		RemoteConnection Connection = new RemoteConnection();
		kurzbezeichnung = kurzbezeichnung.replace('\'', ' ');
		boolean erfolgreich = true;
		try {
			// Abfangen auf Kurzbezeichnung ist leer
			if (kurzbezeichnung.equals("")) {
				erfolgreich = false;
			}
			// System.out.println("SELECT ArbeitsgruppeID From Arbeitsgruppe WHERE Aktiv=1 AND Kurzbezeichnung='"+kurzbezeichnung+"'");
			// Prüfung auf Redundanz der Kurzbezeichnung der Arbeitsgruppe
			ResultSet checkObVorhanden = Connection
					.executeQueryStatement("SELECT ArbeitsgruppeID From Arbeitsgruppe WHERE Aktiv=1 AND Kurzbezeichnung='"
							+ kurzbezeichnung + "'");

			while (checkObVorhanden.next()) {
				// Bekommt die Kurzbezeichnung aus dem Resultset
				int ID = checkObVorhanden.getInt("ArbeitsgruppeID");
				// System.out.println("ID: "+ID+"Aktuelle: "+this.arbeitsgruppeID);
				// Prüfung auf gleichheit
				if (this.arbeitsgruppeID == ID) {
					erfolgreich = true;
				} else {
					erfolgreich = false;
				}
			}

			/*
			 * System.out.println("UPDATE Arbeitsgruppe SET Kurzbezeichnung='" +
			 * kurzbezeichnung + "' WHERE ArbeitsgruppeID='" +
			 * this.arbeitsgruppeID + "'");
			 */
			// Wenn keine Redundanzen vorliegen
			if (erfolgreich == true) {
				RemoteConnection.sql
						.executeUpdate("UPDATE Arbeitsgruppe SET Kurzbezeichnung='"
								+ kurzbezeichnung
								+ "' WHERE ArbeitsgruppeID='"
								+ this.arbeitsgruppeID + "'");
				this.kurzbezeichnung = kurzbezeichnung;
			}

		} catch (SQLException e) {
			System.err
					.println("SQL ERROR in setKurzbezeichnung bei Arbeitsgruppe:");
			System.err.println(e.getMessage());
		}
		return erfolgreich;
	}

	/**
	 * @author Phil Methode liefert Kurzbezeichnung der gewählten Arbeitsgruppe.
	 * @return String kurzbezeichnung
	 */

	public String getKurzbezeichnung() {

		return this.kurzbezeichnung;
	}

	/**
	 * @author Phil Methode zum ändern des Bereichs einer gewählten
	 *         Arbeitsgruppe.
	 * @param bereich
	 * @return boolean (erfolgreich in DB geändert = true, sonst = false).
	 */

	public boolean setBereich(Bereich bereich) {
		boolean erfolgreich = false;
		// Update in der Datenbank
		try {

			// System.out.println("UPDATE Arbeitsgruppe SET Bereich ='"+bereich.getID()+"' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");

			RemoteConnection.sql
					.executeUpdate("UPDATE Arbeitsgruppe SET Bereich ='"
							+ bereich.getID() + "' WHERE ArbeitsgruppeID='"
							+ this.arbeitsgruppeID + "'");
			RemoteConnection.sql.executeUpdate("UPDATE Wochenuebersicht SET Bereich='"+bereich.getID()+"' WHERE Arbeitsgruppe='"+this.arbeitsgruppeID+"'");
			RemoteConnection.sql.executeUpdate("UPDATE Jahresuebersicht SET Bereich='"+bereich.getID()+"' WHERE Arbeitsgruppe='"+this.arbeitsgruppeID+"'");
			
			
			erfolgreich = true;

		}

		catch (SQLException e) {
			System.err.println("Fehler bei setBereich: Arbeitsgruppe");
			System.err.println(e.getMessage());
		}
		return erfolgreich;
	}

	/**
	 * @author Phil Methode liefert Bereich der ausgewählten Arbeitsgruppe
	 * @return Bereich bereich(Objekt)
	 */

	public Bereich getBereich() {
		// Wenn bereits ein Bereich instanziiert wurde
		if (this.bereich != null)
			return this.bereich;
		// Wenn bei der Instanziierung der Arbeitsgruppe kein Bereich gefunden
		// wurde, wurde das Attribut
		// BereichID=0 gesetzt und somit wird ein leerer Bereich zurückgegeben.
		// Ansonsten wird der Bereich
		// aus der ID generiert.
		if (bereichID != 0) {
			this.bereich = new Bereich(bereichID);
		} else {
			this.bereich = null;
		}

		return this.bereich;

	}

	/**
	 * @author Phil Methode ändert den Leiter einer Arbeitsgruppe.
	 * @param Mitarbeiter
	 *            leiter(Objekt)
	 * @return boolean (erfolgreich in DB geändert = true, sonst = false).
	 */
	public boolean setLeiter(Mitarbeiter leiter) {
		boolean erfolgreich = false;

		try {
			// Falls der Übergebene Leiter nicht
			if (!(leiter == null)) {

				// System.out.println("UPDATE Arbeitsgruppe SET Leiter ='"
				// + leiter.getBenutzername() + "' WHERE ArbeitsgruppeID='"
				// + this.arbeitsgruppeID + "'");
				RemoteConnection.sql
						.executeUpdate("UPDATE Arbeitsgruppe SET Leiter ='"
								+ leiter.getBenutzername()
								+ "' WHERE ArbeitsgruppeID='"
								+ this.arbeitsgruppeID + "'");
				// System.out.println("UPDATE Mitarbeiter SET Arbeitsgruppe='"
				// + this.arbeitsgruppeID + "' WHERE Benutzername='"
				// + leiter.getBenutzername() + "'");
				RemoteConnection.sql
						.executeUpdate("UPDATE Mitarbeiter SET Arbeitsgruppe='"
								+ this.arbeitsgruppeID
								+ "' WHERE Benutzername='"
								+ leiter.getBenutzername() + "'");
				erfolgreich = true;
				this.leiter = leiter;

			} else if (leiter == null) {
				// System.out
				// .println("UPDATE Arbeitsgruppe SET Leiter=NULL WHERE ArbeitsgruppeID='"
				// + this.arbeitsgruppeID + "'");

				RemoteConnection.sql
						.executeUpdate("UPDATE Arbeitsgruppe SET Leiter=NULL WHERE ArbeitsgruppeID='"
								+ this.arbeitsgruppeID + "'");
				erfolgreich = true;
				this.leiter = null;
			}
		} catch (SQLException e) {
			System.err.println("fehler in setLeiter Arbeitsgruppe");
			System.err.println(e.getMessage());
		}

		return erfolgreich;
	}

	/**
	 * @author Phil Methode liefert Leiter der ausgewählten Arbeitsgruppe.
	 * @return Mitarbeiter leiter(Objekt)
	 */
	public Mitarbeiter getLeiter() {

		try {
			// Wenn der aktuelle Leiter schon gesetzt ist, wird dieser
			// zurückgegeben
			if (this.leiter != null) {
				return this.leiter;
			}
			// Wenn der aktuelle Leitername nicht leer ist (wird beim
			// Instanziieren gesetzt)
			if (this.leiterBenutzername != null) {
				// System.out.println("SELECT * FROM Mitarbeiter WHERE Benutzername ='"+this.leiterBenutzername+"'");
				ResultSet mitarbeiterResult = RemoteConnection.sql
						.executeQuery("SELECT * FROM Mitarbeiter WHERE Benutzername ='"
								+ this.leiterBenutzername + "'");

				mitarbeiterResult.next();

				this.leiter = new Mitarbeiter(mitarbeiterResult);

			}
			// Wenn der aktuelle Leiter leer ist, wird NULL geliefert
			else {
				this.leiter = null;
			}
		} catch (SQLException e) {
			System.err.println("fehler in Arbeitsgruppe.getLeiter():");
			System.err.println(e.getMessage());
		}
		return this.leiter;

	}

	/**
	 * @author Phil Methode zum löschen einer Arbeitsgruppe.
	 * @return boolean (erfolgreich in DB geändert = true, sonst = false).
	 * @throws Exception
	 *             , falls die Arbeitsgruppe noch Mitarbeiter zugeordnet hat/ein
	 *             Leiter gesetzt ist.
	 */
	public boolean loeschen() throws Exception {
		boolean erfolgreich = false;
		// Boolean zum Abfragen ob in der DB noch zusammenhänge bestehen
		boolean aktuellerStatus = this.aktiv;
		boolean darfDeleteLeiter = false;
		boolean darfDeleteMitarbeiter = false;

		RemoteConnection Connection = new RemoteConnection();
		// Wenn der aktuelle Status = 1
		if (aktuellerStatus == true) {
			try {
				// System.out.println("SELECT * FROM Arbeitsgruppe WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");

				ResultSet checkLeiter = Connection
						.executeQueryStatement("SELECT * FROM Arbeitsgruppe WHERE ArbeitsgruppeID='"
								+ this.arbeitsgruppeID + "' AND Aktiv='1'");
				checkLeiter.next();
				if (!checkLeiter.next()) {
					darfDeleteLeiter = true;
				}
				if (checkLeiter.next()) {
					darfDeleteLeiter = false;
				}

				// Sycstem.out.println("SELECT * FROM Mitarbeiter WHERE Arbeitsgruppe='"+this.arbeitsgruppeID+"'");

				ResultSet mitarbeiterdrancheck = Connection
						.executeQueryStatement("SELECT * FROM Mitarbeiter WHERE Arbeitsgruppe='"
								+ this.arbeitsgruppeID + "' AND Aktiv='1'");
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

					// System.out
					// .println("UPDATE Arbeitsgruppe SET Aktiv='0' WHERE ArbeitsgruppeID='"
					// + this.arbeitsgruppeID + "'");

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
	 * @author Phil Methode liefert den Status der Arbeitsgruppe in der
	 *         Datenbank.
	 * @return boolean (true bedeutet Arbeitsgruppe ist aktiv, false bedeutet
	 *         Arbeitsgruppe wird als gelöscht angezeigt).
	 */
	public boolean getAktiv() {
		// TODO Auto-generated method stub
		return this.aktiv;
	}

	/**
	 * @author Phil Methode liefert zur Kurzbezeichnung die dazugehörige ID der
	 *         Arbeitsgruppe
	 * @param String
	 *            kurzbezeichnung
	 * @return int ArbeitsgruppeID
	 * @throws Exception
	 *             , wenn die angegebene Kurzbezeichnung nicht in der Datenbank
	 *             gefunden wurde.
	 */
	public static int getIDbyKurzbezeichnung(String kurzbezeichnung)
			throws Exception {
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
			if (resultSet.next()) {
				id = resultSet.getInt("ArbeitsgruppeID");
				resultSet.close();
			} else if (!resultSet.next()) {
				throw new Exception(
						"Arbeitsgruppe mit dieser Kurzbezeichnung existiert nicht.");
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
	 * @author Phil Methode liefert ArbeitsgruppeID zur Arbeitsgruppe
	 *         (ArbeitsgruppeID ist der Primärschlüssel in der Datenbank).
	 * @return int ArbeitsgruppeID
	 */
	public int getID() {
		return this.arbeitsgruppeID;
	}

	/**
	 * @author Phil Methode liefert alle Arbeitsgruppen, welche in der Datenbank
	 *         existieren und aktiv sind, als Collection von
	 *         Arbeitsgruppenobjekten.
	 * @return Collection<Arbeitsgruppe> alleArbeitsgruppen
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
	 * @author Phil Methode ist eine Volltextsuche durch alle Arbeitsgruppen,
	 *         welche als aktiv in der Datenbank existieren.
	 * @param String
	 *            suchbegriff
	 * @return Collection<Arbeitsgruppe> alleArbeitsgruppenNachSuchbegriff
	 * @throws Exception
	 *             , falls keine Datensätze gefunden werden die dem Suchbegriff
	 *             entsprechen.
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
			suchbegriff = suchbegriff.replace('\'', ' ');
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
