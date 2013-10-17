package fallstudie.model.impl;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import fallstudie.model.mysql.connector.RemoteConnection;

/**
 * CHANGELOG
 * 
 * @author Phil, 09.10.2013 generiert + implements (Interface) wurde entfernt,
 *         da Konstruktor nicht m�glich ist im Interface
 * @author Phil
 * @date 11.10.2013
 * @author Jenny
 * @version 1.1 - Aktualisiert
 */

public class Eintrag {

	private Date datum;
	private Arbeitsgruppe arbeitsgruppe;
	private int anzahl;
	private Art art;
	private int kalenderwoche;
	private int kalenderjahr;

	// -----------------------------------------------------------
	// ---------------------KONSTRUKTOR--------------------------
	// -----------------------------------------------------------
	/**
	 * Eintrag wird INSERTERT in die Datenbank
	 * 
	 * @param kalenderjahr
	 * @param kalenderwoche
	 * @param anzahl
	 * @param arbeitsgruppe
	 * @param art
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
			System.out
					.println("INSERT INTO Eintrag (Kalenderjahr, Kalenderwoche, Summe, Arbeitsgruppe, Art, Datum)"
							+ "VALUES ('" + kalenderjahr + "','" + kalenderwoche + "','" + anzahl
							+ "','" + arbeitsgruppeID + "','" + art.getName() + "','" + datum + "')");

			int RowsAffected = RemoteConnection.sql
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
