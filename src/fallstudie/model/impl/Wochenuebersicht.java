package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import fallstudie.model.mysql.connector.RemoteConnection;

/** 
 * @author Phil
 * @date 09.10.2013
 * @change Klasse erstellt und Attribute ergänzt
 * @version 1.0
 * @author Jenny
 * @date 14.10.2013
 * @version 1.1
 * @change Methoden implementiert
 */

public class Wochenuebersicht {

	private int kalenderjahr;
	private int kalenderwoche;
	private Arbeitsgruppe arbeitsgruppe;
	private Bereich bereich;

	// -----------------------------------------------------------
	// ---------------------KONSTRUKTOREN-------------------------
	// -----------------------------------------------------------
	/**
	 * @author Jenny
	 * Methode liefert zum Kalenderjahr und einer Kalenderwoche und einer Arbeitsgruppe die Wochenübersicht aus der Datenbank.
	 * @param int kalenderjahr
	 * @param int kalenderwoche
	 * @param Arbeitsgruppe arbeitsgruppe
	 */
	public Wochenuebersicht(int kalenderjahr, int kalenderwoche,
			Arbeitsgruppe arbeitsgruppe) {

		RemoteConnection Connection = new RemoteConnection();

		try {
			if (RemoteConnection.connection == null
					|| RemoteConnection.sql == null) {
				RemoteConnection.connect();
			}
			;
		} catch (NullPointerException e) {
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}

		// ArbeitsgruppenID harausbekommen
		int arbeitsgruppeID = arbeitsgruppe.getID();

		try {
			ResultSet jahresuebersichtResult = Connection
					.executeQueryStatement("SELECT * FROM Wochenuebersicht WHERE Kalenderjahr='"
							+ kalenderjahr
							+ "' AND Kalenderwoche='"
							+ kalenderwoche
							+ "' AND Arbeitsgruppe='"
							+ arbeitsgruppeID + "'");
			if (jahresuebersichtResult.next()) {
				jahresuebersichtResult.next();

				this.kalenderjahr = kalenderjahr;
				this.kalenderwoche = kalenderwoche;
				this.arbeitsgruppe = arbeitsgruppe;
				int bereich = jahresuebersichtResult.getInt("Bereich");
				this.bereich = new Bereich(bereich);
			}

		} catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist aufgetreten in Wochenuebersicht(Arbeitsgruppe):");
			System.err.println(e.getMessage());

		}

	}
	/**
	 * @author Jenny
	 * Methode liefert zum Kalenderjahr und der Kalenderwoche und einem Bereich die Wochenübersicht aus der Datenbank. 
	 * @param int kalenderjahr
	 * @param int kalenderwoche
	 * @param Bereich bereich
	 */
	public Wochenuebersicht(int kalenderjahr, int kalenderwoche, Bereich bereich) {
		RemoteConnection Connection = new RemoteConnection();

		try {
			if (RemoteConnection.connection == null
					|| RemoteConnection.sql == null) {
				RemoteConnection.connect();
			}
			;
		} catch (NullPointerException e) {
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}

		// ArbeitsgruppenID harausbekommen
		int bereichID = bereich.getID();

		try {
			ResultSet jahresuebersichtResult = Connection
					.executeQueryStatement("SELECT * FROM Wochenuebersicht WHERE Kalenderjahr='"
							+ kalenderjahr
							+ "' AND Kalenderwoche='"
							+ kalenderwoche
							+ "' AND Bereich='"
							+ bereichID
							+ "'");

			jahresuebersichtResult.next();

			this.kalenderjahr = kalenderjahr;
			this.kalenderwoche = kalenderwoche;
			this.bereich = bereich;
			int arbeitsgruppe = jahresuebersichtResult.getInt("Arbeitsgruppe");
			this.arbeitsgruppe = new Arbeitsgruppe(arbeitsgruppe);

		} catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist aufgetreten in Wochenuebersicht(Bereich):");
			System.err.println(e.getMessage());

		}

	}

	// -----------------------------------------------------------
	// ---------------------KONSTRUKTOREN-------------------------
	// -----------------------------------------------------------
	/**
	 * @author Jenny
	 * Methode liefert Kalenderjahr der gewählten Übersicht.
	 * @return int kalenderjahr
	 */
	public int getKalenderjahr() {
		return this.kalenderjahr;
	}
	/**
	 * @author Jenny
	 * Methode liefert Kalendewoche der gewählten Übersicht.
	 * @return int kalenderwoche
	 */
	public int getKalenderwoche() {
		return this.kalenderwoche;
	}
	/**
	 * @author Jenny
	 * Methode liefert Bereich der gewählten Übersicht
	 * @return Bereich bereich
	 */
	public Bereich getBereich() {
		return this.bereich;
	}
	/**
	 * @author Jenny
	 * Methode liefert Arbeitsgruppe der gewählten Übersicht.
	 * @return Arbeitsgruppe arbeitsgruppe
	 */
	public Arbeitsgruppe getArbeitsgruppe() {
		return this.arbeitsgruppe;
	}
	/**
	 * @author Phil
	 * Methode liefert alle Zeilen aus der Tabelle in der Datenbank, für die es Wochenübersichten zur Arbeitsgruppe gibt.
	 * @return Collection<Zeile> alleZeilenzurArbeitsgruppe
	 */
	public Collection<Zeile> getZeileArbeitsgruppe() throws Exception {

		// Collection Zeile zu Uebersicht
		Collection<Zeile> result = new LinkedList<>();
		// Attribute festlegen
		RemoteConnection Connection = new RemoteConnection();
		try {
			if (RemoteConnection.connection == null
					|| RemoteConnection.sql == null) {
				RemoteConnection.connect();
			}
			;
		} catch (NullPointerException e) {
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		// Initialisieren
		ResultSet resultSet = null;
		try {
			// System.out.println("SELECT Art, SUM(Summe) as Summe FROM Wochenuebersicht WHERE Kalenderjahr ='"+
			// this.kalenderjahr +
			// "' AND Kalenderwoche ='"+ this.kalenderwoche
			// +"' AND Arbeitsgruppe ='"+this.arbeitsgruppe.getID()+"'");
			resultSet = Connection
					.executeQueryStatement("SELECT Art, SUM(Summe) as Summe FROM Wochenuebersicht WHERE Kalenderjahr ='"
							+ this.kalenderjahr
							+ "' AND Kalenderwoche ='"
							+ this.kalenderwoche
							+ "' AND Arbeitsgruppe ='"
							+ this.arbeitsgruppe.getID() + "' GROUP BY Art");
			while (resultSet.next()) // Die Ausgelesenen ERgebnisse in die
										// Collection bringen
			{
				int Zeilensumme = resultSet.getInt("Summe");
				String Zeilenart = resultSet.getString("Art");
				Art art = Art.getArtByName(Zeilenart);
				result.add(new Zeile(Zeilensumme, art));

			}
			resultSet.close();
		} catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist aufgetreten in getZeileArbeitsgruppe():");
			System.err.println(e.getMessage());
		}
		return result;

	}

	/**
	 * @author Phil
	 * Methode liefert alle Zeilen aus der Tabelle in der Datenbank, für die es Jahresübersichten zum Bereich gibt.
	 * @return Collection<Zeile> alleZeilenzumBereich
	 */
	public Collection<Zeile> getZeileBereich() throws Exception {

		// Collection Zeile zu Uebersicht
		Collection<Zeile> result = new LinkedList<>();
		// Attribute festlegen
		RemoteConnection Connection = new RemoteConnection();
		try {
			if (RemoteConnection.connection == null
					|| RemoteConnection.sql == null) {
				RemoteConnection.connect();
			}
			;
		} catch (NullPointerException e) {
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		// Initialisieren
		ResultSet resultSet = null;
		try {
			// System.out.println("SELECT Art, SUM(Summe) as Summe FROM Wochenuebersicht WHERE Kalenderjahr ='"+
			// this.kalenderjahr +
			// "' AND Kalenderwoche ='"+ this.kalenderwoche
			// +"' AND Bereich ='"+this.bereich.getID()+"'");
			resultSet = Connection
					.executeQueryStatement("SELECT Art, SUM(Summe) as Summe FROM Wochenuebersicht WHERE Kalenderjahr ='"
							+ this.kalenderjahr
							+ "' AND Kalenderwoche ='"
							+ this.kalenderwoche
							+ "' AND Bereich ='"
							+ this.bereich.getID() + "' GROUP BY Art");
			while (resultSet.next()) // Die Ausgelesenen ERgebnisse in die
										// Collection bringen
			{
				int Zeilensumme = resultSet.getInt("Summe");
				String Zeilenart = resultSet.getString("Art");
				Art art = Art.getArtByName(Zeilenart);
				result.add(new Zeile(Zeilensumme, art));

			}
			resultSet.close();
		} catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist aufgetreten in getZeileBereich():");
			System.err.println(e.getMessage());
		}
		return result;

	}

	/**
	 * @author Phil
	 * Methode liefert zu einem Kalenderjahr und einer Kalenderwoche alle Wochenübersichten zu allen Bereichen.
	 * @param int jahr
	 * @param int woche
	 * @return UebersichtSchnittstellenKlasse (enthält Collection<Wochenübersicht> alleWochenübersichtens und andere Attribute)
	 */
	public static UebersichtSchnittstellenKlasse getAlleWochenuebersichtenZuAllenBereichen(
			int jahr, int woche) {
		RemoteConnection Connection = new RemoteConnection();
		Collection<Wochenuebersicht> alleWochenuebersichten = new LinkedList<>();
		UebersichtSchnittstellenKlasse uS = new UebersichtSchnittstellenKlasse();
		try {
			if (RemoteConnection.connection == null
					|| RemoteConnection.sql == null) {
				RemoteConnection.connect();
			}
			;
		} catch (NullPointerException e) {
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}

		// System.out.println("SELECT Bereich FROM Wochenuebersicht WHERE Kalenderjahr='"+jahr+"'"
		// AND Kalenderwoche='"+woche+"'");

		try {
			ResultSet wochenUebersicht = Connection
					.executeQueryStatement("SELECT DISTINCT Bereich FROM Wochenuebersicht WHERE Kalenderjahr='"
							+ jahr + "' AND Kalenderwoche='" + woche + "'");

			while (wochenUebersicht.next()) {
				int bereichID = wochenUebersicht.getInt("Bereich");
				Bereich bereich = new Bereich(bereichID);

				alleWochenuebersichten.add(new Wochenuebersicht(jahr, woche,
						bereich));
			}

			wochenUebersicht.close();
			uS.Wochenuebersichten = alleWochenuebersichten;
			ResultSet artenZahl = Connection
					.executeQueryStatement("SELECT Count(DISTINCT Art) AS Anzahl FROM Wochenuebersicht WHERE Kalenderjahr='"
							+ jahr + "' AND Kalenderwoche='" + woche + "'");
			artenZahl.next();
			uS.anzahlArten = artenZahl.getInt("Anzahl");
			artenZahl.close();

		} catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist in getAlleJahresuebersichtenZuAllenbereichen(String) aufgetreten:");
			System.err.println(e.getMessage());
		}
		return uS;

	}

	/**
	 * @author Phil
	 * Methode liefert zu einem Kalenderjahr und einer Kalenderwoche alle Wochenübersichten zu einem bestimmten Bereichen.
	 * @param int jahr
	 * @param int woche
	 * @param Bereich bereich
	 * @return UebersichtSchnittstellenKlasse (enthält Collection<Wochenübersicht> alleWochenübersichtens und andere Attribute)
	 */
	public static UebersichtSchnittstellenKlasse getAlleWochenuebersichtenZumBereich(
			int jahr, int woche, Bereich bereich) {
		RemoteConnection Connection = new RemoteConnection();
		Collection<Wochenuebersicht> alleWochenuebersichten = new LinkedList<>();
		UebersichtSchnittstellenKlasse us = new UebersichtSchnittstellenKlasse();
		int bereichID = bereich.getID();

		try {
			if (RemoteConnection.connection == null
					|| RemoteConnection.sql == null) {
				RemoteConnection.connect();
			}
			;
		} catch (NullPointerException e) {
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}

		// System.out.println("SELECT DISTINCT Arbeitsgruppe FROM Wochenuebersicht WHERE Kalenderjahr='"+jahr+"' AND Kalenderwoche='"+woche+"' AND Bereich='"+bereichID+"'");

		try {
			ResultSet wochenUebersicht = Connection
					.executeQueryStatement("SELECT DISTINCT Arbeitsgruppe FROM Wochenuebersicht WHERE Kalenderjahr='"
							+ jahr
							+ "' AND Kalenderwoche='"
							+ woche
							+ "' AND Bereich='" + bereichID + "'");

			while (wochenUebersicht.next()) {
				int arbeitsgruppeID = wochenUebersicht.getInt("Arbeitsgruppe");
				Arbeitsgruppe gruppe = new Arbeitsgruppe(arbeitsgruppeID);

				alleWochenuebersichten.add(new Wochenuebersicht(jahr, woche,
						gruppe));
			}

			wochenUebersicht.close();
			us.Wochenuebersichten = alleWochenuebersichten;
			ResultSet artenZahl = Connection
					.executeQueryStatement("SELECT Count(DISTINCT Art) AS Anzahl FROM Wochenuebersicht WHERE Kalenderjahr='"
							+ jahr
							+ "' AND Bereich='"
							+ bereichID
							+ "' AND Kalenderwoche='" + woche + "'");
			artenZahl.next();
			us.anzahlArten = artenZahl.getInt("Anzahl");
			artenZahl.close();

		} catch (SQLException e) {
			System.err
					.println("Dieser Fehler ist in getAlleWochenuebersichtenZumBereich(String) aufgetreten:");
			System.err.println(e.getMessage());
		}
		return us;

	}

}
