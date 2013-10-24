package fallstudie.model.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fallstudie.model.mysql.connector.RemoteConnection;

/**
 * @author Phil
 * @date 09.10.2013 Klasse erstellt
 * @author Jenny
 * @date 11.10.2013
 * @version 1.1
 * @change Methoden implementiert und aktualisiert
 */

public class Eintrag {


	// -----------------------------------------------------------
	// ---------------------KONSTRUKTOR--------------------------
	// -----------------------------------------------------------
	/**
	 * @author Jenny
	 * Eintrag wird auf der Datenbank angelegt in die Datenbank
	 * @param int kalenderjahr
	 * @param int kalenderwoche
	 * @param int anzahl
	 * @param Arbeitsgruppe arbeitsgruppe
	 * @param Art art
	 */
	public Eintrag(int kalenderjahr, int kalenderwoche, int anzahl,
			Arbeitsgruppe arbeitsgruppe, Art art) {

		int arbeitsgruppeID = arbeitsgruppe.getID();

		try // Kontrolle ob Datenbankverbindung da
		{
			if (RemoteConnection.connection == null
					|| RemoteConnection.sql == null) {
				RemoteConnection.connect();
			}
			;

		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}

		// heutiges Datum vom System �bergeben
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String datum = simpleFormat.format(date);

		try {
			/*System.out
					.println("INSERT INTO Eintrag (Kalenderjahr, Kalenderwoche, Summe, Arbeitsgruppe, Art, Datum)"
							+ "VALUES ('" + kalenderjahr + "','" + kalenderwoche + "','" + anzahl
							+ "','" + arbeitsgruppeID + "','" + art.getName() + "','" + datum + "')");
			 */
			RemoteConnection.sql
					.executeUpdate("INSERT INTO Eintrag (Kalenderjahr, Kalenderwoche, Summe, Arbeitsgruppe, Art, Datum) VALUES ('"
							+ kalenderjahr + "','" + kalenderwoche + "','" + anzahl + "','" + arbeitsgruppeID
							+ "','" + art.getName() + "','" + datum + "')");
			
		}

		catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL Statement ist fehlerhaft!");
		}
	}

}
