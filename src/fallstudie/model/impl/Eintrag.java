package fallstudie.model.impl;
import java.sql.SQLException;

import fallstudie.model.mysql.connector.RemoteConnection;

/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @author Phil
 * @date 11.10.2013
 * @version 1.1 - Aktualisiert
 */

public class Eintrag {

	
	private Date datum;
	private Arbeitsgruppe arbeitsgruppe;
	private int anzahl;
	private Art art;
	private int kalenderwoche;
	private int kalenderjahr;
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOR--------------------------
	//-----------------------------------------------------------
	/**
	 * Eintrag wird INSERTERT in die Datenbank
	 * @param kalenderjahr
	 * @param kalenderwoche
	 * @param anzahl
	 * @param arbeitsgruppe
	 * @param art
	 */
	public Eintrag(int kalenderjahr, int kalenderwoche, int anzahl, 
			Arbeitsgruppe arbeitsgruppe, Art art) {
		
		// TODO Auto-generated method stub
		
		try //Kontrolle ob Datenbankverbindung da
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
		
		// TODO Datum generieren
		datum = today;
		
		try
		{
			System.out.println("INSERT INTO Eintrag (Kalenderjahr, Kalenderwoche, Summe, Arbeitsgruppe, Art, Datum)"  +
					"VALUES ('"+kalenderjahr+"','"+kalenderwoche+"','"+anzahl+"','"+arbeitsgruppe+"','"+art+"','"+datum+"'");

				int RowsAffected = RemoteConnection.sql.executeUpdate(
						"INSERT INTO Eintrag (Kalenderjahr, Kalenderwoche, Summe, Arbeitsgruppe, Art, Datum) VALUES ('"+kalenderjahr+"','"+kalenderwoche+"','"+anzahl+"','"+arbeitsgruppe+"','"+art+"','"+datum+"'");
				System.out.println("Rows Affected: "+RowsAffected+"");
			
		}
			
			
		catch (SQLException e) 
		{
				System.err.println(e.getMessage());
				System.err.println("SQL Statement ist fehlerhaft!");
		}
	}

}
