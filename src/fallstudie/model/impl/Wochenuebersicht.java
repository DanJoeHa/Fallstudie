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
 * @author Jenny
 * @date 14.10.13
 * @change Methoden implementiert
 * @version 1.1 - aktualisiert mit technisches Db modell
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
	 * Konstruktor beim auslesen von Wochenuebersichten einer arbeitsgruppe
	 * 
	 * @param kalenderjahr
	 * @param kalenderwoche
	 * @param arbeitsgruppe
	 * @return
	 * @return
	 */
	public Wochenuebersicht(int kalenderjahr, int kalenderwoche,
			Arbeitsgruppe arbeitsgruppe) {
		
		RemoteConnection Connection = new RemoteConnection();
		
		try
		{
			if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
				RemoteConnection.connect();
			};
		}
		catch (NullPointerException e)
		{
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		
		//ArbeitsgruppenID harausbekommen
		int arbeitsgruppeID = arbeitsgruppe.getID();
		
		try
		{
			ResultSet jahresuebersichtResult = Connection.executeQueryStatement(
					"SELECT * FROM Wochenuebersicht WHERE Kalenderjahr='"+ kalenderjahr +"' AND Kalenderwoche='"
							+kalenderwoche+"' AND Arbeitsgruppe='"+ arbeitsgruppeID +"'");
			if(jahresuebersichtResult.next())
			{
			jahresuebersichtResult.next();
			
			this.kalenderjahr = kalenderjahr;
			this.kalenderwoche = kalenderwoche;
			this.arbeitsgruppe = arbeitsgruppe;
			int bereich = jahresuebersichtResult.getInt("Bereich");
			this.bereich = new Bereich(bereich);
			}

		}
		catch (SQLException e)
		{   
			System.err.println("Dieser Fehler ist aufgetreten in Wochenuebersicht(Arbeitsgruppe):");
			System.err.println(e.getMessage());
			
		}
	
	}

	/**
	 * Konstruktor beim auslesen von Wochenuebersichten eines Bereichs
	 * 
	 * @param kalenderjahr
	 * @param kalenderwoche
	 * @param Bereich
	 * @return
	 */
	public Wochenuebersicht(int kalenderjahr, int kalenderwoche, Bereich bereich) {
		// TODO Auto-generated method stub
		RemoteConnection Connection = new RemoteConnection();
		
		try
		{
			if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
				RemoteConnection.connect();
			};
		}
		catch (NullPointerException e)
		{
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		
		//ArbeitsgruppenID harausbekommen
		int bereichID = bereich.getID();
		
		try
		{
			ResultSet jahresuebersichtResult = Connection.executeQueryStatement(
					"SELECT * FROM Wochenuebersicht WHERE Kalenderjahr='"+ kalenderjahr +"' AND Kalenderwoche='"
							+kalenderwoche+"' AND Bereich='"+ bereichID +"'");
			
			jahresuebersichtResult.next();
			
			this.kalenderjahr = kalenderjahr;
			this.kalenderwoche = kalenderwoche;
			this.bereich = bereich;
			int arbeitsgruppe = jahresuebersichtResult.getInt("Arbeitsgruppe");
			this.arbeitsgruppe = new Arbeitsgruppe(arbeitsgruppe);
		

		}
		catch (SQLException e)
		{   
			System.err.println("Dieser Fehler ist aufgetreten in Wochenuebersicht(Bereich):");
			System.err.println(e.getMessage());
			
		}

	}

	// -----------------------------------------------------------
	// ---------------------KONSTRUKTOREN-------------------------
	// -----------------------------------------------------------

	
	public int getKalenderjahr() {
		return this.kalenderjahr;
	}

	public int getKalenderwoche() {
		return this.kalenderwoche;
	}

	public Bereich getBereich() {
		return this.bereich;
	}

	public Arbeitsgruppe getArbeitsgruppe() {
		return this.arbeitsgruppe;
	}
	
	public Collection<Zeile> getZeileArbeitsgruppe() throws Exception
	{
		
		//Collection Zeile zu Uebersicht
		Collection<Zeile> result = new LinkedList<>();
		//Attribute festlegen
		RemoteConnection Connection = new RemoteConnection();
		try
		{
			if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
				RemoteConnection.connect();
			};
		}
		catch (NullPointerException e)
		{
					System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		//Initialisieren
		ResultSet resultSet = null;
		try 
		{			
			//System.out.println("SELECT Art, SUM(Summe) as Summe FROM Wochenuebersicht WHERE Kalenderjahr ='"+ this.kalenderjahr + 
				//	"' AND Kalenderwoche ='"+ this.kalenderwoche +"' AND Arbeitsgruppe ='"+this.arbeitsgruppe.getID()+"'");
			resultSet = Connection.executeQueryStatement(
					"SELECT Art, SUM(Summe) as Summe FROM Wochenuebersicht WHERE Kalenderjahr ='"+ this.kalenderjahr + 
					"' AND Kalenderwoche ='"+ this.kalenderwoche +"' AND Arbeitsgruppe ='"+this.arbeitsgruppe.getID()+"' GROUP BY Art");
				while (resultSet.next()) //Die Ausgelesenen ERgebnisse in die Collection bringen
				{	
					int Zeilensumme = resultSet.getInt("Summe");
					String Zeilenart = resultSet.getString("Art");
					Art art = Art.getArtByName(Zeilenart);
					result.add(new Zeile(Zeilensumme,art));
							
				}
				resultSet.close();
		}
		catch (SQLException e) 
		{	
			System.err.println("Dieser Fehler ist aufgetreten in getZeileArbeitsgruppe():");
			System.err.println(e.getMessage());
		}
		return result;
		
	}
	
	/**
	 * Alle Zeilen einer Jahresuebersicht wenn man nach einem Bereich sucht Jahresuebersicht(bereich)
	 * @return
	 * @throws Exception
	 */
	public Collection<Zeile> getZeileBereich() throws Exception
	{
		
		//Collection Zeile zu Uebersicht
		Collection<Zeile> result = new LinkedList<>();
		//Attribute festlegen
		RemoteConnection Connection = new RemoteConnection();
		try
		{
			if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
				RemoteConnection.connect();
			};
		}
		catch (NullPointerException e)
		{
					System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		//Initialisieren
		ResultSet resultSet = null;
		try 
		{			
			//System.out.println("SELECT Art, SUM(Summe) as Summe FROM Wochenuebersicht WHERE Kalenderjahr ='"+ this.kalenderjahr + 
			//		"' AND Kalenderwoche ='"+ this.kalenderwoche +"' AND Bereich ='"+this.bereich.getID()+"'");
			resultSet = Connection.executeQueryStatement(
					"SELECT Art, SUM(Summe) as Summe FROM Wochenuebersicht WHERE Kalenderjahr ='"+ this.kalenderjahr + 
					"' AND Kalenderwoche ='"+ this.kalenderwoche +"' AND Bereich ='"+this.bereich.getID()+"' GROUP BY Art");
				while (resultSet.next()) //Die Ausgelesenen ERgebnisse in die Collection bringen
				{	
					int Zeilensumme = resultSet.getInt("Summe");
					String Zeilenart = resultSet.getString("Art");
					Art art = Art.getArtByName(Zeilenart);
					result.add(new Zeile(Zeilensumme,art));
							
				}
				resultSet.close();
		}
		catch (SQLException e) 
		{	
			System.err.println("Dieser Fehler ist aufgetreten in getZeileBereich():");
			System.err.println(e.getMessage());
		}
		return result;
	
	}
	/**
	 * Liefert alle Wochenuebersichten zu allen Bereichen,
	 * @param jahr
	 * @param woche
	 * @return Collection von Wochenuebersichten welche einem Bereich zugeordnet sind
	 */
	public static UebersichtSchnittstellenKlasse getAlleWochenuebersichtenZuAllenBereichen(int jahr, int woche)
	{
		RemoteConnection Connection = new RemoteConnection();
		Collection<Wochenuebersicht> alleWochenuebersichten = new LinkedList<>();
		UebersichtSchnittstellenKlasse uS = new UebersichtSchnittstellenKlasse();
		try
		{
			if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
				RemoteConnection.connect();
			};
		}
		catch (NullPointerException e)
		{
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		
		//System.out.println("SELECT Bereich FROM Wochenuebersicht WHERE Kalenderjahr='"+jahr+"'" AND Kalenderwoche='"+woche+"'");
		
		try {
			ResultSet wochenUebersicht = Connection.executeQueryStatement("SELECT DISTINCT Bereich FROM Wochenuebersicht WHERE Kalenderjahr='"+jahr+"' AND Kalenderwoche='"+woche+"'");
			
			while(wochenUebersicht.next())
			{	
				int bereichID = wochenUebersicht.getInt("Bereich");
				Bereich bereich = new Bereich(bereichID);
				
				alleWochenuebersichten.add(new Wochenuebersicht(jahr,woche, bereich));
			}
			
		wochenUebersicht.close();
		uS.Wochenuebersichten = alleWochenuebersichten;
		ResultSet artenZahl = Connection.executeQueryStatement("SELECT Count(DISTINCT Art) AS Anzahl FROM Wochenuebersicht WHERE Kalenderjahr='"+jahr+"' AND Kalenderwoche='"+woche+"'");
		artenZahl.next();
		uS.anzahlArten = artenZahl.getInt("Anzahl");
		artenZahl.close();
		
		
		} 
		catch (SQLException e) {
			System.err.println("Dieser Fehler ist in getAlleJahresuebersichtenZuAllenbereichen(String) aufgetreten:");
			System.err.println(e.getMessage());
		}
		return uS;
		
		
	}
	/**
	 * Liefert für den Bereichsleiter eine Übersicht aller Wochenübersichten eines Bereichs(ale Arbitsgruppen)
	 * @param jahr
	 * @param woche
	 * @param bereich
	 * @return
	 */
	public static UebersichtSchnittstellenKlasse getAlleWochenuebersichtenZumBereich(int jahr, int woche, Bereich bereich)
	{
		RemoteConnection Connection = new RemoteConnection();
		Collection<Wochenuebersicht> alleWochenuebersichten = new LinkedList<>();
		UebersichtSchnittstellenKlasse us = new UebersichtSchnittstellenKlasse();
		int bereichID = bereich.getID();
		
		try
		{
			if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
				RemoteConnection.connect();
			};
		}
		catch (NullPointerException e)
		{
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		
		//System.out.println("SELECT DISTINCT Arbeitsgruppe FROM Wochenuebersicht WHERE Kalenderjahr='"+jahr+"' AND Kalenderwoche='"+woche+"' AND Bereich='"+bereichID+"'");
		
		try {
			ResultSet wochenUebersicht = Connection.executeQueryStatement("SELECT DISTINCT Arbeitsgruppe FROM Wochenuebersicht WHERE Kalenderjahr='"+jahr+"' AND Kalenderwoche='"+woche+"' AND Bereich='"+bereichID+"'");
			
			while(wochenUebersicht.next())
			{	
				int arbeitsgruppeID = wochenUebersicht.getInt("Arbeitsgruppe");
				Arbeitsgruppe gruppe = new Arbeitsgruppe(arbeitsgruppeID);
				
				alleWochenuebersichten.add(new Wochenuebersicht(jahr, woche,gruppe));
			}
			
		wochenUebersicht.close();
		us.Wochenuebersichten = alleWochenuebersichten;
		ResultSet artenZahl = Connection.executeQueryStatement("SELECT Count(DISTINCT Art) AS Anzahl FROM Wochenuebersicht WHERE Kalenderjahr='"+jahr+"' AND Bereich='"+bereichID+"' AND Kalenderwoche='"+woche+"'");
		artenZahl.next();
		us.anzahlArten = artenZahl.getInt("Anzahl");
		artenZahl.close();
		
		} 
		catch (SQLException e) {
			System.err.println("Dieser Fehler ist in getAlleWochenuebersichtenZumBereich(String) aufgetreten:");
			System.err.println(e.getMessage());
		}
		return us;
		
	}
	
}

