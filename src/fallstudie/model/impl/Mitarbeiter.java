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

/**
 * @author Phil
 * @date 09.10.2013
 * @change erstellt und Attribute ergänzt
 * @version 1.0
 * @author Phil
 * @date 11.10.2013
 * @version 1.1
 * @change checkPasswort() eingefügt und Kommentare aktualisiert
 * @author Phil
 * @date 13.10.2013
 * @version 1.2
 * @change alle Methoden implementiert
 * @author Phil
 * @change getFullName() hinzugefügt.
 * @version 1.3
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
	private int bereichID;
	// -----------------------------------------------------------
	// ---------------------KONSTRUKTOREN-------------------------
	// -----------------------------------------------------------
	private int arbeitsgruppeID;
	private String rollenName;

	/**
	 * @author Phil Methode liefert zum Benutzernamen ein befülltes
	 *         Mitarbeiterobjekt befüllt aus der Datenbank.
	 * @param String
	 *            benutzername
	 * @throws Exception
	 *             , falls es den Benutzernamen in der Datenbank nicht gibt.
	 */
	public Mitarbeiter(String benutzername) throws Exception {
		if (!benutzername.equals("")) {
			benutzername = benutzername.replace('\'', ' ');
			RemoteConnection Connection = new RemoteConnection();
			try {
				if (RemoteConnection.connection == null
						|| RemoteConnection.sql == null) {
					RemoteConnection.connect();
				}
				;
			} catch (NullPointerException e) {
				System.err.println(e.getMessage());
				System.err
						.println("Konnte keine Datenbankverbindung herstellen!");
			}

			try {
				// System.out.println("SELECT * FROM Mitarbeiter WHERE Benutzername ='"+benutzername+"'");

				ResultSet mitarbeiterResult = Connection
						.executeQueryStatement("SELECT * FROM Mitarbeiter WHERE Benutzername ='"
								+ benutzername + "'");
				if (mitarbeiterResult.next()) {
					int bereichID = mitarbeiterResult.getInt("Bereich");
					// Wenn Bereich zugeordnet wird ein Objekt erzeugt
					if (bereichID != 0) {
						this.bereich = new Bereich(bereichID);
					} else {
						this.bereich = null;
					}

					// Wenn Arbeitsgruppe zugeordnet wird ein Objekt erzeugt
					int arbeitsgruppeID = mitarbeiterResult
							.getInt("Arbeitsgruppe");

					if (arbeitsgruppeID != 0) {
						this.arbeitsgruppe = new Arbeitsgruppe(arbeitsgruppeID);
					} else {
						this.arbeitsgruppe = null;
					}
					// Benutzername wird zugeordnet
					this.benutzername = benutzername;
					// ROLLE
					String rolle = mitarbeiterResult.getString("Rolle");
					this.rolle = new Rolle(rolle);
					// PASSWORT
					this.passwort = mitarbeiterResult.getString("Passwort");
					;
					// AKTIV
					this.aktiv = mitarbeiterResult.getBoolean("Aktiv");
					;
					// Vorname
					this.vorname = mitarbeiterResult.getString("Vorname");
					// NACHNAME
					this.nachname = mitarbeiterResult.getString("Nachname");
					// Last Login
					String komplettletzterLogin = mitarbeiterResult
							.getString("LetzterLogin");
					if (komplettletzterLogin != null) {
						String jahr = komplettletzterLogin.substring(0, 4);
						String monat = komplettletzterLogin.substring(5, 7);
						String tag = komplettletzterLogin.substring(8, 10);

						this.letzterLogin = tag + "." + monat + "." + jahr;
					} else {
						this.letzterLogin = null;
					}

					mitarbeiterResult.close();

				} else {

					throw new Exception("Mitarbeiter existiert nicht.");
				}
			} catch (SQLException e) {
				System.err
						.println("Dieser Fehler ist aufgetreten in Mitarbeiter (String benutzername):");
				System.err.println(e.getMessage());

			}
		}

	}

	/**
	 * @author Phil Methode legt neuen Mitarbeiter in der Datenbank an, mit
	 *         Bereichszuordnung.
	 * @param String
	 *            benutzername
	 * @param String
	 *            passwort
	 * @param String
	 *            vorname
	 * @param String
	 *            nachname
	 * @param Rolle
	 *            rolle
	 * @param Bereich
	 *            bereich
	 * @throws Exception
	 *             , falls der angegebe Benutzername bereis existiert.
	 */
	public Mitarbeiter(String benutzername, String passwort, String vorname,
			String nachname, Rolle rolle, Bereich bereich) throws Exception {
		try {
			if (RemoteConnection.connection == null
					|| RemoteConnection.sql == null) {
				RemoteConnection.connect();
			}
			;

			if (benutzername.length() > 44 || passwort.length() > 44
					| vorname.length() > 45 || nachname.length() > 45)
				throw new Exception("Maximal 44 Zeichen erlaubt.");

			// IDs und Namen herauskriegen
			String rollenName = rolle.getRollenbezeichnung();
			int bereichID = bereich.getID();
			benutzername = benutzername.replace('\'', ' ');
			passwort = benutzername.replace('\'', ' ');
			vorname = benutzername.replace('\'', ' ');
			nachname = benutzername.replace('\'', ' ');

			// System.out.println("SELECT * From Mitarbeiter");
			// Checken obs den Mitarbeiter schon gibt.
			ResultSet checkObVorhanden = RemoteConnection.sql
					.executeQuery("SELECT * From Mitarbeiter");
			String verschluesseltPasswort = VerschluesselungSHA1
					.getEncodedSha1Sum(passwort);

			if (!benutzername.equals("")) {

				while (checkObVorhanden.next()) {
					boolean aktiv = checkObVorhanden.getBoolean("Aktiv");
					String value = checkObVorhanden.getString("Benutzername");
					// System.out.println(value);
					if (aktiv == true) {
						if (benutzername.toUpperCase().equals(
								value.toUpperCase()))
							throw new Exception(
									"Mitarbeiter mit selben Benutzername existiert schon.");
					} else if (aktiv == false) {
						if (benutzername.toUpperCase().equals(
								value.toUpperCase())) {
							this.benutzername = benutzername;
							this.passwort = verschluesseltPasswort;
							this.vorname = vorname;
							this.nachname = nachname;
							this.rolle = rolle;
							this.bereich = bereich;
							/*
							 * System.out.println("UPDATE Mitarbeiter SET Rolle='"
							 * + rolle.getRollenbezeichnung() + "', Passwort='"
							 * + verschluesseltPasswort +
							 * "', Aktiv='1', Vorname='" + vorname +
							 * "', Nachname='" + nachname +
							 * "', LetzterLogin=NULL, Bereich='" +
							 * bereich.getID() +
							 * "', PWChanged=0 WHERE Benutzername='" +
							 * benutzername + "'");
							 */
							RemoteConnection.sql
									.executeUpdate("UPDATE Mitarbeiter SET Rolle='"
											+ rolle.getRollenbezeichnung()
											+ "', Passwort='"
											+ verschluesseltPasswort
											+ "', Aktiv='1', Vorname='"
											+ vorname
											+ "', Nachname='"
											+ nachname
											+ "', LetzterLogin=NULL, Bereich='"
											+ bereich.getID()
											+ "', PWChanged=0 WHERE Benutzername='"
											+ benutzername + "'");
							throw new Exception(
									"Mitarbeiter wurde erfolgreich angelegt.");
						}
					}
				}
				checkObVorhanden.close();

				// passwort wird verschl�sselt in die DB geschrieben
				/*
				 * System.out .println(
				 * "INSERT INTO Mitarbeiter (Benutzername, Passwort, Vorname, Nachname, Rolle, Bereich)"
				 * + " VALUES ('" + benutzername + "','" +
				 * verschluesseltPasswort + "','" + vorname + "','" + nachname +
				 * "','" + rollenName + "','" + bereichID + "')");
				 */
				int affectedRows = RemoteConnection.sql
						.executeUpdate("INSERT INTO Mitarbeiter (Benutzername, Passwort, Vorname, Nachname, Rolle, Bereich)"
								+ "	VALUES ('"
								+ benutzername
								+ "','"
								+ verschluesseltPasswort
								+ "','"
								+ vorname
								+ "','"
								+ nachname
								+ "','"
								+ rollenName
								+ "','"
								+ bereichID + "')");
				// System.out.println("UPDATE Bereich SET Leiter='" +
				// benutzername
				// + "' WHERE BereichID='" + bereich.getID() + "'");
				RemoteConnection.sql
						.executeUpdate("UPDATE Bereich SET Leiter='"
								+ benutzername + "' WHERE BereichID='"
								+ bereich.getID() + "'");

				this.benutzername = benutzername;
				this.passwort = verschluesseltPasswort;
				this.vorname = vorname;
				this.nachname = nachname;
				this.rolle = rolle;
				this.bereich = bereich;

				if (affectedRows == 1)
					throw new Exception("Mitarbeiter erfolgreich angelegt.");
			}
		}

		catch (SQLException e) {
			System.err
					.println("Fehler im Konstruktor von Mitarbeiter mit Bereich");
			System.err.println(e.getMessage());

		}
	}

	/**
	 * @author Phil Methode legt neuen Mitarbeiter in der Datenbank an, mit
	 *         Arbeitsgruppenzuordnung.
	 * @param String
	 *            benutzername
	 * @param String
	 *            passwort
	 * @param String
	 *            vorname
	 * @param String
	 *            nachname
	 * @param Rolle
	 *            rolle
	 * @param Arbeitsgruppe
	 *            arbeitsgruppe
	 * @throws Exception
	 *             , falls der angegebe Benutzername bereis existiert.
	 */
	public Mitarbeiter(String benutzername, String passwort, String vorname,
			String nachname, Rolle rolle, Arbeitsgruppe arbeitsgruppe)
			throws Exception {
		try {
			if (RemoteConnection.connection == null
					|| RemoteConnection.sql == null) {
				RemoteConnection.connect();
			}
			;

			if (benutzername.length() > 44 || passwort.length() > 44
					| vorname.length() > 45 || nachname.length() > 45)
				throw new Exception("Maximal 44 Zeichen erlaubt.");
			// IDs und Namen herauskriegen
			String rollenName = rolle.getRollenbezeichnung();
			int arbeitsgruppeID = arbeitsgruppe.getID();
			benutzername = benutzername.replace('\'', ' ');
			passwort = benutzername.replace('\'', ' ');
			vorname = benutzername.replace('\'', ' ');
			nachname = benutzername.replace('\'', ' ');

			// System.out.println("SELECT Benutzername From Mitarbeiter");
			// Checken obs den Mitarbeiter schon gibt.

			String verschluesseltPasswort = VerschluesselungSHA1
					.getEncodedSha1Sum(passwort);

			if (!benutzername.equals("")) {
				ResultSet checkObVorhanden = RemoteConnection.sql
						.executeQuery("SELECT * From Mitarbeiter");
				while (checkObVorhanden.next()) {
					boolean aktiv = checkObVorhanden.getBoolean("Aktiv");
					String value = checkObVorhanden.getString("Benutzername");
					// System.out.println(value);
					if (aktiv == true) {
						if (benutzername.toUpperCase().equals(
								value.toUpperCase()))
							throw new Exception(
									"Mitarbeiter mit selben Benutzername existiert schon.");
					} else if (aktiv == false) {
						if (benutzername.toUpperCase().equals(
								value.toUpperCase())) {
							this.benutzername = benutzername;
							this.passwort = verschluesseltPasswort;
							this.vorname = vorname;
							this.nachname = nachname;
							this.rolle = rolle;
							this.arbeitsgruppe = arbeitsgruppe;
							/*
							 * System.out.println("UPDATE Mitarbeiter SET Rolle='"
							 * + rolle.getRollenbezeichnung() + "', Passwort='"
							 * + verschluesseltPasswort +
							 * "', Aktiv='1', Vorname='" + vorname +
							 * "', Nachname='" + nachname +
							 * "', LetzterLogin=NULL, Arbeitsgruppe='" +
							 * arbeitsgruppe.getID() +
							 * "', PWChanged=0 WHERE Benutzername='" +
							 * benutzername + "'");
							 */
							RemoteConnection.sql
									.executeUpdate("UPDATE Mitarbeiter SET Rolle='"
											+ rolle.getRollenbezeichnung()
											+ "', Passwort='"
											+ verschluesseltPasswort
											+ "', Aktiv='1', Vorname='"
											+ vorname
											+ "', Nachname='"
											+ nachname
											+ "', LetzterLogin=NULL, Arbeitsgruppe='"
											+ arbeitsgruppe.getID()
											+ "', PWChanged=0 WHERE Benutzername='"
											+ benutzername + "'");
							throw new Exception(
									"Mitarbeiter wurde erfolgreich angelegt.");
						}
					}
				}
				checkObVorhanden.close();
				/*
				 * System.out .println(
				 * "INSERT INTO Mitarbeiter (Benutzername, Passwort, Vorname, Nachname, Rolle, Arbeitsgruppe)"
				 * + "	VALUES ('" + benutzername + "','" +
				 * verschluesseltPasswort + "','" + vorname + "','" + nachname +
				 * "','" + rollenName + "','" + arbeitsgruppeID + "')");
				 */
				int affectedRows = RemoteConnection.sql
						.executeUpdate("INSERT INTO Mitarbeiter (Benutzername, Passwort, Vorname, Nachname, Rolle, Arbeitsgruppe)"
								+ "	VALUES ('"
								+ benutzername
								+ "','"
								+ verschluesseltPasswort
								+ "','"
								+ vorname
								+ "','"
								+ nachname
								+ "','"
								+ rollenName
								+ "','"
								+ arbeitsgruppeID + "')");

				this.benutzername = benutzername;
				this.passwort = verschluesseltPasswort;
				this.vorname = vorname;
				this.nachname = nachname;
				this.rolle = rolle;
				this.arbeitsgruppe = arbeitsgruppe;
				/*
				 * System.out.println("UPDATE Arbeitsgruppe SET Leiter='" +
				 * benutzername + "' WHERE ArbeitsgruppeID='" +
				 * arbeitsgruppe.getID() + "'"); RemoteConnection.sql
				 * .executeUpdate("UPDATE Arbeitsgruppe SET Leiter='" +
				 * benutzername + "' WHERE ArbeitsgruppeID='" +
				 * arbeitsgruppe.getID() + "'");
				 */
				if (affectedRows != 1)
					throw new Exception("Mitarbeiter konnte nicht angelegt werden.");

			}
		} catch (SQLException e) {
			System.err.println("Fehler im Konstruktor mit Arbeitsgruppe:");
			System.err.println(e.getMessage());

		}
	}

	/**
	 * @author Phil Methode legt neuen Mitarbeiter in der Datenbank an, ohne
	 *         zuordnung zu Bereich oder Arbeitsgruppe.
	 * @param String
	 *            benutzername
	 * @param String
	 *            passwort
	 * @param String
	 *            vorname
	 * @param String
	 *            nachname
	 * @param Rolle
	 *            rolle
	 * @throws Exception
	 *             , falls der angegebe Benutzername bereis existiert.
	 */
	public Mitarbeiter(String benutzername, String passwort, String vorname,
			String nachname, Rolle rolle) throws Exception {
		try {
			if (RemoteConnection.connection == null
					|| RemoteConnection.sql == null) {
				RemoteConnection.connect();
			}
			;

			if (benutzername.length() > 44 || passwort.length() > 44
					| vorname.length() > 45 || nachname.length() > 45)
				throw new Exception("Maximal 44 Zeichen erlaubt.");

			// IDs und Namen herauskriegen
			String rollenName = rolle.getRollenbezeichnung();
			benutzername = benutzername.replace('\'', ' ');
			passwort = benutzername.replace('\'', ' ');
			vorname = benutzername.replace('\'', ' ');
			nachname = benutzername.replace('\'', ' ');
			// System.out.println("SELECT Benutzername From Mitarbeiter");
			// Checken obs den Mitarbeiter schon gibt.

			if (!benutzername.equals("")) {
				String verschluesseltPasswort = VerschluesselungSHA1
						.getEncodedSha1Sum(passwort);

				ResultSet checkObVorhanden = RemoteConnection.sql
						.executeQuery("SELECT * From Mitarbeiter");
				while (checkObVorhanden.next()) {
					boolean aktiv = checkObVorhanden.getBoolean("Aktiv");
					String value = checkObVorhanden.getString("Benutzername");
					// System.out.println(value);
					if (aktiv == true) {
						if (benutzername.toUpperCase().equals(
								value.toUpperCase()))
							throw new Exception(
									"Mitarbeiter mit selben Benutzername existiert schon.");
					} else if (aktiv == false) {
						if (benutzername.toUpperCase().equals(
								value.toUpperCase())) {
							this.benutzername = benutzername;
							this.passwort = verschluesseltPasswort;
							this.vorname = vorname;
							this.nachname = nachname;
							this.rolle = rolle;
							this.arbeitsgruppe = null;
							this.bereich = null;
							/*
							 * System.out
							 * .println("UPDATE Mitarbeiter SET Rolle='" +
							 * rolle.getRollenbezeichnung() + "', Passwort='" +
							 * verschluesseltPasswort +
							 * "', Aktiv='1', Vorname='" + vorname +
							 * "', Nachname='" + nachname +
							 * "', LetzterLogin=NULL, Arbeitsgruppe=NULL, Bereich=NULL, PWChanged=0 WHERE Benutzername='"
							 * + benutzername + "')");
							 */
							RemoteConnection.sql
									.executeUpdate("UPDATE Mitarbeiter SET Rolle='"
											+ rolle.getRollenbezeichnung()
											+ "', Passwort='"
											+ verschluesseltPasswort
											+ "', Aktiv='1', Vorname='"
											+ vorname
											+ "', Nachname='"
											+ nachname
											+ "', LetzterLogin=NULL, Arbeitsgruppe=NULL, Bereich=NULL, PWChanged=0 WHERE Benutzername='"
											+ benutzername + "')");
							throw new Exception(
									"Mitarbeiter wurde erfolgreich angelegt.");
						}
					}
				}
				checkObVorhanden.close();
				/*
				 * System.out .println(
				 * "INSERT INTO Mitarbeiter (Benutzername, Passwort, Vorname, Nachname, Rolle)"
				 * + "	VALUES ('" + benutzername + "','" +
				 * verschluesseltPasswort + "','" + vorname + "','" + nachname +
				 * "','" + rollenName + "')");
				 */
				int affectedRows = RemoteConnection.sql
						.executeUpdate("INSERT INTO Mitarbeiter (Benutzername, Passwort, Vorname, Nachname, Rolle)"
								+ "	VALUES ('"
								+ benutzername
								+ "','"
								+ verschluesseltPasswort
								+ "','"
								+ vorname
								+ "','" + nachname + "','" + rollenName + "')");

				if (affectedRows == 1)
					// System.out.println("Es wurde " + affectedRows
					// + " Datensatz eingef�gt.");

					this.benutzername = benutzername;
				this.passwort = verschluesseltPasswort;
				this.vorname = vorname;
				this.nachname = nachname;
				this.rolle = rolle;
				this.arbeitsgruppe = null;
				this.bereich = null;
				checkObVorhanden.close();
				if (affectedRows != 1)
					throw new Exception("Mitarbeiter konnte nicht angelegt werden.");

			}
		} catch (SQLException e) {
			System.err
					.println("SQL Statement ist fehlerhaft! In Mitarbeiter(ohne AG+Bereich)");
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @author Phil Anhand eines ResultSets aus einer SELECT- Abfrage wird das
	 *         Mitarbeiterobjekt befüllt.
	 * @param ResultSet
	 *            resultSet
	 */
	public Mitarbeiter(ResultSet resultSet) {
		try {
			this.bereichID = resultSet.getInt("Bereich");
			this.arbeitsgruppeID = resultSet.getInt("Arbeitsgruppe");

			// Benutzername wird zugeordnet
			this.benutzername = resultSet.getString("Benutzername");
			// ROLLE
			this.rollenName = resultSet.getString("Rolle");

			// PASSWORT
			this.passwort = resultSet.getString("Passwort");
			// AKTIV
			this.aktiv = resultSet.getBoolean("Aktiv");

			// Vorname
			this.vorname = resultSet.getString("Vorname");
			// NACHNAME
			this.nachname = resultSet.getString("Nachname");
			// Last Login
			String komplettletzterLogin = resultSet.getString("LetzterLogin");
			if (komplettletzterLogin != null) {
				String jahr = komplettletzterLogin.substring(0, 4);
				String monat = komplettletzterLogin.substring(5, 7);
				String tag = komplettletzterLogin.substring(8, 10);

				this.letzterLogin = tag + "." + monat + "." + jahr;
			} else {
				this.letzterLogin = null;
			}

		}// END TRY
		catch (SQLException e) {
			System.err.println("Fehler in Mitarbeiter(ResultSet)");
			System.err.println(e.getMessage());
		}
	}

	// -----------------------------------------------------------
	// ---------------------Methoden-------------------------
	// -----------------------------------------------------------

	/**
	 * @author Phil Methode zum einloggen in das Programm.
	 * @param String
	 *            benutzername
	 * @param String
	 *            passwort
	 * @return Mitarbeiter mitarbeiter (befülltes Objekt)
	 * @throws Exception
	 *             , falls die eingegeben Daten nicht stimmen.
	 */
	public static Mitarbeiter einloggen(String benutzername, String passwort)
			throws Exception {
		RemoteConnection Connection = new RemoteConnection();
		Mitarbeiter mitarbeiter = null;

		try {
			if (RemoteConnection.connection == null
					|| RemoteConnection.sql == null) {
				RemoteConnection.connect();
			}
		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		if (benutzername.equals("") && passwort.equals(""))
			throw new Exception("Bitte Login Daten eingeben.");
		try {
			String verschluesseltesPW = VerschluesselungSHA1
					.getEncodedSha1Sum(passwort);

			// System.out.println("SELECT * FROM Mitarbeiter WHERE Benutzername='"+benutzername+"'");
			// anhand des Benutzernamens ResultSet kriegen
			ResultSet mitarbeiterResult = Connection
					.executeQueryStatement("SELECT * FROM Mitarbeiter WHERE Benutzername='"
							+ benutzername + "'");
			mitarbeiterResult.last();
			int zeilen = mitarbeiterResult.getRow();
			mitarbeiterResult.beforeFirst();

			if (zeilen == 0)
				throw new Exception("Benutzername ist falsch.");
			mitarbeiterResult.next();

			// Passwort muss abgefragt werden
			String passwortInDatabase = mitarbeiterResult.getString("Passwort");
			boolean aktiv = mitarbeiterResult.getBoolean("Aktiv");

			if (aktiv == true) {

				// Wenn Passw�rter �bereinstimmen
				if (verschluesseltesPW.equals(passwortInDatabase)) {

					mitarbeiter = new Mitarbeiter(mitarbeiterResult);

				} else {

					mitarbeiter = null;
					throw new Exception("Passwort ist falsch.");
				}
				mitarbeiterResult.close();
				// System.out.println(verschluesseltesPW);
				// System.out.println(passwortInDatabase);
			} else {
				throw new Exception("Benutzername existiert nicht.");
			}

		}

		catch (NoSuchAlgorithmException e) {
			System.err.println("FEHLER beim Verschlüsseln:");
			System.err.println(e.getMessage());
		}

		return mitarbeiter;
	}

	/**
	 * @author Phil Methode zum ausloggen eines Mitarbeiters.
	 * @return boolean (erfolgreich in DB geändert = true, sonst = false).
	 */
	public boolean ausloggen() {
		// Heutiges Datum generieren
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String heutigesDatum = simpleFormat.format(date);
		this.letzterLogin = heutigesDatum;
		boolean erfolgreich = false;
		// System.out.println("UPDATE Mitarbeiter SET LetzterLogin='"+heutigesDatum+"' WHERE Benutzername ='"+this.benutzername+"'");
		try {
			int rowsAffected = RemoteConnection.sql
					.executeUpdate("UPDATE Mitarbeiter SET LetzterLogin='"
							+ heutigesDatum + "' WHERE Benutzername ='"
							+ this.benutzername + "'");

			if (rowsAffected == 0)
				erfolgreich = false;
			if (rowsAffected == 1)
				erfolgreich = true;

		} catch (SQLException e) {
			System.err.println("Dieser Fehler ist in ausloggen() aufgetreten:");

			System.err.println(e.getMessage());
		}
		return erfolgreich;

	}

	/**
	 * @author Phil Methode ändert den Bereich des gewählten Mitarbeiters.
	 * @param Bereich
	 *            bereich
	 * @return boolean (erfolgreich in DB geändert = true, sonst = false).
	 */
	public boolean setBereich(Bereich bereich) {
		boolean erfolgreich = false;
		try {

			int bereichID = bereich.getID();

			// System.out.println("UPDATE Mitarbeiter SET Bereich ='"+bereichID+"' WHERE Benutzername='"+this.benutzername+"'");

			int affectedRows = RemoteConnection.sql
					.executeUpdate("UPDATE Mitarbeiter SET Bereich ='"
							+ bereichID + "' WHERE Benutzername='"
							+ this.benutzername + "'");

			if (affectedRows == 0)
				erfolgreich = false;
			if (affectedRows == 1)
				erfolgreich = true;
			this.bereich = bereich;
		} catch (SQLException e) {
			System.err.println("Dieser Fehler ist in ausloggen() aufgetreten:");

			System.err.println(e.getMessage());
		}
		return erfolgreich;
	}

	/**
	 * @author Phil Methode ändert Rolle des gewählten Mitarbeiters.
	 * @param Rolle
	 *            rolle
	 * @return boolean (erfolgreich in DB geändert = true, sonst = false).
	 */
	public boolean setRolle(Rolle rolle) {
		boolean erfolgreich = false;
		try {
			if (rolle.getRollenbezeichnung().equals("Sachbearbeiter")
					|| rolle.getRollenbezeichnung().equals(
							"Zentralbereichsleiter")
					|| rolle.getRollenbezeichnung().equals(
							"Fachbereichsorganisation")) {
				RemoteConnection.sql
						.executeUpdate("UPDATE Arbeitsgruppe SET Leiter=NULL WHERE Leiter='"
								+ this.benutzername + "'");
				RemoteConnection.sql
						.executeUpdate("UPDATE Bereich SET Leiter=NULL WHERE Leiter='"
								+ this.benutzername + "'");
			}
			String rollenName = rolle.getRollenbezeichnung();

			// System.out.println("UPDATE Mitarbeiter SET Rolle ='"+rollenName+"' WHERE Benutzername='"+this.benutzername+"'");

			int affectedRows = RemoteConnection.sql
					.executeUpdate("UPDATE Mitarbeiter SET Rolle ='"
							+ rollenName + "' WHERE Benutzername='"
							+ this.benutzername + "'");

			if (affectedRows == 0)
				erfolgreich = false;
			if (affectedRows == 1)
				erfolgreich = true;
			this.rolle = rolle;
		} catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist in setRolle(rolle) aufgetreten:");

			System.err.println(e.getMessage());
		}
		return erfolgreich;
	}

	/**
	 * @author Phil Methode liefert die aktuelle Rolle des gewählten
	 *         Mitarbeiters.
	 * @return Rolle rolle
	 */
	public Rolle getRolle() {
		if (this.rolle != null) {
			return this.rolle;
		}
		if (this.rollenName != null) {
			this.rolle = new Rolle(this.rollenName);

		} else {
			this.rolle = null;
		}
		return this.rolle;

	}

	/*
	 * public boolean setBenutzername(String benutzername) throws Exception {
	 * boolean erfolgreich = false; try { ResultSet checkObVorhanden =
	 * RemoteConnection.sql
	 * .executeQuery("SELECT Benutzername From Mitarbeiter");
	 * 
	 * while (checkObVorhanden.next()) {
	 * 
	 * String value = checkObVorhanden.getString("Benutzername");
	 * 
	 * if (benutzername.equals(value)) throw new Exception(
	 * "Mitarbeiter mit dem selben Benutzernamen existiert schon!");
	 * 
	 * } checkObVorhanden.close();
	 * 
	 * if (!this.benutzername.equals(benutzername)) {
	 * 
	 * //
	 * System.out.println("UPDATE Mitarbeiter SET Benutzername ='"+benutzername
	 * +"' WHERE Benutzername='"+this.benutzername+"'");
	 * 
	 * int affectedRows = RemoteConnection.sql
	 * .executeUpdate("UPDATE Mitarbeiter SET Benutzername ='" + benutzername +
	 * "' WHERE Benutzername='" + this.benutzername + "'");
	 * 
	 * if (affectedRows == 0) erfolgreich = false; if (affectedRows == 1)
	 * erfolgreich = true; this.benutzername = benutzername; } else { throw new
	 * Exception("Benutzernamen sind identisch."); } } catch (SQLException e) {
	 * System.err
	 * .println("Dieser Fehler ist in setBenutzername(String) aufgetreten:");
	 * 
	 * System.err.println(e.getMessage()); } return erfolgreich; }
	 */
	/**
	 * @author Phil Methode liefert den Benutzernamen des gewählten
	 *         Mitarbeiters.
	 * @return String benutzername
	 */
	public String getBenutzername() {
		return this.benutzername;
	}

	/**
	 * @author Phil Methode prüft beim Passwort ändern ob das eingegeben
	 *         Passwort mit dem aktuellen übereinstimmt.
	 * @param String
	 *            altesPasswort
	 * @return boolean (wenn Passwort stimmt =true , wenn nicht =false)
	 */
	public boolean checkPasswort(String altesPasswort) {
		boolean erfolgreich = false;
		try {
			String altesVerschluesselt = VerschluesselungSHA1
					.getEncodedSha1Sum(altesPasswort);

			// System.out.println("SELECT Passwort FROM Mitarbeiter WHERE Benutzername='"+this.benutzername+"'");

			ResultSet resultSet = RemoteConnection.sql
					.executeQuery("SELECT Passwort FROM Mitarbeiter WHERE Benutzername='"
							+ this.benutzername + "'");
			resultSet.next();
			String aktuellesPasswort = resultSet.getString("Passwort");

			if (altesVerschluesselt.equals(aktuellesPasswort))
				erfolgreich = true;
			if (!altesVerschluesselt.equals(aktuellesPasswort))
				erfolgreich = false;
			resultSet.close();
		} catch (NoSuchAlgorithmException e) {
			System.err.println("FEHLER beim Verschl�sseln:");
			System.err.println(e.getMessage());
		} catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist in checkPasswort(String) aufgetreten:");

			System.err.println(e.getMessage());
		}

		return erfolgreich;

	}

	/**
	 * @author Phil Methode liefert verschlüsseltes Passwort zum gewählten
	 *         Mitarbeiter
	 * @return String passwort
	 */
	public String getPasswort() {

		return this.passwort;
	}

	/**
	 * @author Phil Methode zum ändern des Passworts des gewählten Mitarbeiters.
	 * @param String
	 *            newPasswort
	 * @return boolean (erfolgreich in DB geändert = true, sonst = false).
	 */
	public boolean setPasswort(String newPasswort) {
		boolean erfolgreich = false;
		try {
			newPasswort = newPasswort.replace('\'', ' ');
			String newPasswortVerschluesselt = VerschluesselungSHA1
					.getEncodedSha1Sum(newPasswort);
			// System.out.println("UPDATE Mitarbeiter SET Passwort ='"
			// + newPasswortVerschluesselt + "' WHERE Benutzername='"
			// + this.benutzername + "'");

			int rowsAffect = RemoteConnection.sql
					.executeUpdate("UPDATE Mitarbeiter SET Passwort ='"
							+ newPasswortVerschluesselt
							+ "',PWCHanged='1' WHERE Benutzername='"
							+ this.benutzername + "'");
			if (rowsAffect == 0)
				erfolgreich = false;
			if (rowsAffect == 1)
				erfolgreich = true;
			this.passwort = newPasswortVerschluesselt;

		}

		catch (NoSuchAlgorithmException e) {
			System.err.println("FEHLER beim Verschl�sseln:");
			System.err.println(e.getMessage());
		} catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist in setpasswort(String) aufgetreten:");

			System.err.println(e.getMessage());
		}

		return erfolgreich;

	}

	/**
	 * @author Phil Methode ändert die aktuelle Arbeitsgruppe des gewählten
	 *         Mitarbeiters.
	 * @param Arbeitsgruppe
	 *            arbeitsgruppe
	 * @return boolean (erfolgreich in DB geändert = true, sonst = false).
	 */
	public boolean setArbeitsgruppe(Arbeitsgruppe arbeitsgruppe) {
		boolean erfolgreich = false;
		try {
			if (arbeitsgruppe != null) {
				int arbeitsgrupepID = arbeitsgruppe.getID();
				// System.out.println("UPDATE Mitarbeiter SET Arbeitsgruppe ='"+arbeitsgrupepID+"' WHERE Benutzername='"+this.benutzername+"'");

				int rowsAffect = RemoteConnection.sql
						.executeUpdate("UPDATE Mitarbeiter SET Arbeitsgruppe ='"
								+ arbeitsgrupepID
								+ "' WHERE Benutzername='"
								+ this.benutzername + "'");

				if (rowsAffect == 0)
					erfolgreich = false;
				if (rowsAffect == 1)
					erfolgreich = true;
				this.arbeitsgruppe = arbeitsgruppe;
			} else {
				int rowsAffect = RemoteConnection.sql
						.executeUpdate("UPDATE Mitarbeiter SET Arbeitsgruppe =NULL WHERE Benutzername='"
								+ this.benutzername + "'");

				if (rowsAffect == 0)
					erfolgreich = false;
				if (rowsAffect == 1)
					erfolgreich = true;
				this.arbeitsgruppe = arbeitsgruppe;
			}
		} catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist in setpasswort(String) aufgetreten:");

			System.err.println(e.getMessage());
		}

		return erfolgreich;

	}

	/**
	 * @author Phil Methode liefert die aktuelle Arbeitsgruppe zum gewählten
	 *         Mitarbeiter.
	 * @return
	 */
	public Arbeitsgruppe getArbeitsgruppe() {
		if (this.arbeitsgruppe != null) {
			return this.arbeitsgruppe;
		}
		if (arbeitsgruppeID != 0) {
			this.arbeitsgruppe = new Arbeitsgruppe(this.arbeitsgruppeID);
		} else {
			this.arbeitsgruppe = null;
		}
		return this.arbeitsgruppe;

	}

	/**
	 * @author Phil Methode zum löschen des gewählten Mitarbeiters.
	 * @return boolean (erfolgreich in DB geändert = true, sonst = false).
	 * @throws Exception
	 *             , wenn der Mitarbeiter noch Leiter eines Bereichs oder einer
	 *             Arbeitsgruppe ist.
	 */
	public boolean loeschen() throws Exception {
		boolean erfolgreich = false;
		boolean aktuellerStatus = this.getAktiv();
		boolean darfdeletedWerdenBereich = false;
		boolean aktiv;
		boolean darfdeletedWerdenArbeitsgruppe = false;
		RemoteConnection Connection = new RemoteConnection();

		try { // IN Bereich pr�fen
				// System.out.println("SELECT * FROM Bereich WHERE Leiter='"
				// + this.benutzername + "'");

			ResultSet checkMitarbeiterInBereich = Connection
					.executeQueryStatement("SELECT * FROM Bereich WHERE Leiter='"
							+ this.benutzername + "' AND Aktiv='1'");
			if (!checkMitarbeiterInBereich.next())
				darfdeletedWerdenBereich = true;
			if (checkMitarbeiterInBereich.next())
				darfdeletedWerdenBereich = false;

			checkMitarbeiterInBereich.close();

			// IN Arbeitsgruppe pr�fen!

			// System.out.println("SELECT * FROM Arbeitsgruppe WHERE Leiter='"
			// + this.benutzername + "'");

			ResultSet checkMitarbeiterInArbeitsgruppe = Connection
					.executeQueryStatement("SELECT * FROM Arbeitsgruppe WHERE Leiter='"
							+ this.benutzername + "' AND Aktiv='1'");
			if (!checkMitarbeiterInArbeitsgruppe.next())
				darfdeletedWerdenArbeitsgruppe = true;
			if (checkMitarbeiterInArbeitsgruppe.next())
				darfdeletedWerdenArbeitsgruppe = false;
			checkMitarbeiterInArbeitsgruppe.close();

			// abfrage ob weder im Bereich noch in der Arbeitsgruppe noch als
			// Leiter t�tig
			if (darfdeletedWerdenBereich == true
					&& darfdeletedWerdenArbeitsgruppe == true) {
				if (aktuellerStatus == true) {

					// System.out
					// .println("UPDATE Mitarbeiter SET Aktiv ='0' WHERE Benutzername='"
					// + this.benutzername + "'");

					int rowsAffect = RemoteConnection.sql
							.executeUpdate("UPDATE Mitarbeiter SET Aktiv ='0' WHERE Benutzername='"
									+ this.benutzername + "'");

					if (rowsAffect == 0)
						erfolgreich = false;
					if (rowsAffect == 1)
						erfolgreich = true;
					aktiv = false;
					this.aktiv = aktiv;
					throw new Exception("Mitarbeiter wurde gelöscht.");
				}

			} else {
				throw new Exception(
						"Mitarbeiter ist noch als Leiter eines Bereichs oder einer Arbeitsgruppe eingetragen. Löschen nicht möglich.");
			}
		} catch (SQLException e) {
			System.err.println("Dieser Fehler ist in loeschen() aufgetreten:");

			System.err.println(e.getMessage());
		}

		return erfolgreich;

	}

	/**
	 * @author Phil Methode liefert den aktuellen Status des Mitarbeiters.
	 * @return boolean (true = aktiv, false= wird als gelöscht angezeigt)
	 */
	public boolean getAktiv() {

		return this.aktiv;
	}

	/**
	 * @author Phil Methode liefert den Nachnamen des gewählten Mitarbeiters
	 * @return String nachname
	 */
	public String getNachname() {

		return this.nachname;
	}

	/**
	 * @author Phil Methode liefert den Vornamen und Nachnamen des gewählten
	 *         Mitarbeiters in einem String.
	 * @return String vollerName
	 */
	public String getFullName() {
		String fullname = this.vorname + " " + this.nachname;
		return fullname;
	}

	/**
	 * @author Phil Methode ändert den Nachnamen des gewählten Mitarbeiters.
	 * @param nachname
	 * @return boolean (erfolgreich in DB geändert = true, sonst = false).
	 */
	public boolean setNachname(String nachname) {
		boolean erfolgreich = false;
		try {
			nachname = nachname.replace('\'', ' ');
			// System.out.println("UPDATE Mitarbeiter SET Nachname ='"+nachname+"' WHERE Benutzername='"+this.benutzername+"'");

			int rowsAffect = RemoteConnection.sql
					.executeUpdate("UPDATE Mitarbeiter SET Nachname ='"
							+ nachname + "' WHERE Benutzername='"
							+ this.benutzername + "'");

			if (rowsAffect == 0)
				erfolgreich = false;
			if (rowsAffect == 1)
				erfolgreich = true;
			this.nachname = nachname;

		} catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist in setNachname(String) aufgetreten:");

			System.err.println(e.getMessage());
		}

		return erfolgreich;

	}

	/**
	 * @author Phil Methode liefert den Vornamen des gewählten Mitarbeiters.
	 * @return String vorname
	 */
	public String getVorname() {

		return this.vorname;
	}

	/**
	 * @author Phil Methode ändert den Vornamen des gewählten Mitarbeiters
	 * @param String
	 *            vorname
	 * @return boolean (erfolgreich in DB geändert = true, sonst = false).
	 */
	public boolean setVorname(String vorname) {
		boolean erfolgreich = false;
		try {

			// System.out.println("UPDATE Mitarbeiter SET Vorname ='"+vorname+"' WHERE Benutzername='"+this.benutzername+"'");
			vorname = vorname.replace('\'', ' ');
			int rowsAffect = RemoteConnection.sql
					.executeUpdate("UPDATE Mitarbeiter SET Vorname ='"
							+ vorname + "' WHERE Benutzername='"
							+ this.benutzername + "'");

			if (rowsAffect == 0)
				erfolgreich = false;
			if (rowsAffect == 1)
				erfolgreich = true;
			this.vorname = vorname;

		} catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist in setVorname(String) aufgetreten:");

			System.err.println(e.getMessage());
		}

		return erfolgreich;

	}

	/**
	 * @author Phil Methode liefert den letzten Login des gewählten
	 *         Mitarbeiters.
	 * @return String letzterLogin
	 */
	public String getLogin() {
		return this.letzterLogin;
	}

	/**
	 * @author Phil Methode prüft ob Mitarbeiter das nötige Recht hat, auf etwas
	 *         zuzugreifen.
	 * @param String
	 *            recht
	 * @return boolean (hat das Recht=true, sonst =false)
	 */
	public boolean checkRecht(String recht) {
		boolean erfolgreich = false;

		Rolle rolle = getRolle();

		Collection<Rechte> alleRechteVonRolle = new LinkedList<>();
		// alle Berechtigungen von Rolle geben, Collection festlegen
		alleRechteVonRolle = rolle.getBerechtigungenzuRolle();
		// Druch alle Rechte einer Rolle durchiterieren
		for (Iterator<Rechte> i = alleRechteVonRolle.iterator(); i.hasNext();) {
			Rechte rechte = (Rechte) i.next();
			String rechtName = rechte.getName();
			if (rechtName.equals(recht))
				erfolgreich = true;
		}

		return erfolgreich;

	}

	/**
	 * @author Phil Methode ist eine Volltextsuche durch alle Mitarbeiter in der
	 *         Datenbank, bei denen der Suchbegriff enthalten ist.
	 * @param String
	 *            suchbegriff
	 * @param String
	 *            suchdomain (zusätzlich mit Einschränkung der Rolle
	 *            [Gruppenleiter oder Bereichsleiter])
	 * @return Collection<Mitarbeiter> alleMitarbeiterMitSuchbegriff
	 */
	public static Collection<Mitarbeiter> suche(String suchbegriff,
			String suchdomain) {

		Collection<Mitarbeiter> result = new LinkedList<>();
		RemoteConnection Connection = new RemoteConnection();
		if (RemoteConnection.connection == null || RemoteConnection.sql == null) {
			RemoteConnection.connect();
		}
		;
		ResultSet resultSet = null;
		try {
			suchbegriff = suchbegriff.replace('\'', ' ');
			// System.out.println("SELECT * FROM Mitarbeiter WHERE Benutzername LIKE '%"+suchbegriff+"%' OR Arbeitsgruppe LIKE '%"+suchbegriff+"%' OR"
			// +
			// " Rolle LIKE '%"+suchbegriff+"%' OR Vorname LIKE '%"+suchbegriff+"%' OR Nachname LIKE '%"+suchbegriff+"%'");
			String query = "SELECT * FROM Mitarbeiter WHERE (Benutzername LIKE '%"
					+ suchbegriff
					+ "%' OR Vorname LIKE '%"
					+ suchbegriff
					+ "%' OR Nachname LIKE '%"
					+ suchbegriff
					+ "%' OR Arbeitsgruppe LIKE (SELECT ArbeitsgruppeID FROM Arbeitsgruppe WHERE Kurzbezeichnung='"
					+ suchbegriff
					+ "') OR Bereich LIKE (SELECT BereichID FROM Bereich WHERE Kurzbezeichnung='"
					+ suchbegriff + "') )";
			// System.out.println("Suchdomain: "+suchdomain);
			if (suchdomain.equals("Bereichsleiter")
					|| suchdomain.equals("Gruppenleiter"))
				query += " AND (Rolle LIKE '" + suchdomain + "')";

			// System.out.println(query);
			resultSet = Connection.executeQueryStatement(query);
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
					result.add(new Mitarbeiter(resultSet));
				}
			}
			resultSet.close();
		} catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist aufgetreten in suche Arbeitsgruppe (suchbegriff):");
			System.err.println(e.getMessage());
		}
		return result;
	}

	/**
	 * @author Phil Methode liefert alle Mitarbeiter aus der Datenbank.
	 * @return Collection<Mitarbeiter> alleMitarbeiterAusDatenbank
	 */
	public static Collection<Mitarbeiter> getAlleMitarbeiter() {
		Collection<Mitarbeiter> result = new LinkedList<>();
		RemoteConnection Connection = new RemoteConnection();
		if (RemoteConnection.connection == null || RemoteConnection.sql == null) {
			RemoteConnection.connect();
		}
		;
		ResultSet resultSet = null;
		try {
			// System.out.println("SELECT * FROM Mitarbeiter");
			resultSet = Connection
					.executeQueryStatement("Select * From Mitarbeiter");

			while (resultSet.next()) {

				boolean aktiv = resultSet.getBoolean("Aktiv");
				if (aktiv == true) {
					result.add(new Mitarbeiter(resultSet));
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
	 * @author Phil Methode liefert boolean ob das Initialpasswort schon
	 *         geändert wurde.
	 * @return boolean (true wenn Passwort bereits geändert wurde, sonst false)
	 */
	public boolean passwortIsChanged() {
		boolean erfolgreich = false;
		try {
			RemoteConnection Connection = new RemoteConnection();
			// System.out.println("SELECT PWCHanged FROM Mitarbeiter WHERE Benutzername='"+this.benutzername+"'");
			ResultSet resultSet = Connection
					.executeQueryStatement("SELECT PWCHanged FROM Mitarbeiter WHERE Benutzername='"
							+ this.benutzername + "'");
			resultSet.next();
			erfolgreich = resultSet.getBoolean("PWCHanged");

		} catch (SQLException e) {
			System.err.println("------SQL ERROR-------");

			System.err.println(e.getMessage());
		}
		return erfolgreich;
	}

	/**
	 * @author Phil Methode liefert den Bereich des gewählten Mitarbeiters.
	 * @return Bereich bereich
	 */
	public Bereich getBereich() {
		if (this.bereich != null)
			return this.bereich;

		if (bereichID != 0) {
			this.bereich = new Bereich(bereichID);
		} else {
			this.bereich = null;
		}

		return this.bereich;

	}
}
