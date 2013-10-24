package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import fallstudie.model.mysql.connector.RemoteConnection;

/** 
 * @author Phil, 09.10.2013
 * @change Klasse erstellt und Variablen erstellt.
 * @version 1.0
 * @author Jenny
 * @date 14.10.13
 * @change Methoden implementiert
 * @version 1.1
 */
public class Jahresuebersicht {
	
	private int kalenderjahr;
	private Arbeitsgruppe arbeitsgruppe;
	private Bereich bereich;
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	/**
	 * @author Jenny
	 * Methode liefert zum Kalenderjahr und einer Arbeitsgruppe die Jahresübersicht aus der Datenbank.
	 * @param int kalenderjahr
	 * @param Arbeitsgruppe arbeitsgruppe
	 */
	public Jahresuebersicht(int kalenderjahr,
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
			//System.out.println("SELECT * FROM Jahresuebersicht WHERE Kalenderjahr='"+ kalenderjahr +"' AND  Arbeitsgruppe='"+arbeitsgruppeID +"'");
			ResultSet jahresuebersichtResult = Connection.executeQueryStatement(
					"SELECT * FROM Jahresuebersicht WHERE Kalenderjahr='"+ kalenderjahr +"' AND  Arbeitsgruppe='"+arbeitsgruppeID +"'");
			
			if(jahresuebersichtResult.next())
					{
						int bereich = jahresuebersichtResult.getInt("Bereich");
						this.bereich = new Bereich(bereich);
				
					}
			else
			{
				this.bereich=null;
			}
			
			this.kalenderjahr = kalenderjahr;
			this.arbeitsgruppe = arbeitsgruppe;
			

		}
		catch (SQLException e)
		{   
			System.err.println("Dieser Fehler ist aufgetreten in Jahresuebersicht(Arbeitsgruppe):");
			System.err.println(e.getMessage());
			
		}

	
	}

	/**
	 * @author Jenny
	 * Methode liefert zum Kalenderjahr und einem Bereich die Jahresübersicht aus der Datenbank. 
	 * @param int kalenderjahr
	 * @param Bereich bereich
	 */
	public Jahresuebersicht(int kalenderjahr, Bereich bereich) {
		
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
		
		//ABereichsID harausbekommen
		int bereichID = bereich.getID();
		
		try
		{
			//System.out.println("SELECT DISTINCT Arbeitsgruppe FROM Jahresuebersicht WHERE Kalenderjahr='"
					//+ kalenderjahr + "' AND  Bereich='" + bereichID + "'");
			
			ResultSet jahresuebersichtResult = Connection.executeQueryStatement(
					"SELECT DISTINCT Arbeitsgruppe FROM Jahresuebersicht WHERE Kalenderjahr='"
					+ kalenderjahr + "' AND  Bereich='" + bereichID + "'");
			jahresuebersichtResult.next();
			this.kalenderjahr = kalenderjahr;
			this.bereich = bereich;
			/*
			this.agZuBereich = new LinkedList<>();
			
			while(jahresuebersichtResult.next())
			{
				int arbeitsgruppe = jahresuebersichtResult.getInt("Arbeitsgruppe");
				this.agZuBereich.add(new Arbeitsgruppe(arbeitsgruppe));
			}
			 */
			int arbeitsgruppe = jahresuebersichtResult.getInt("Arbeitsgruppe");
			this.arbeitsgruppe = new Arbeitsgruppe(arbeitsgruppe);

		}
		catch (SQLException e)
		{   
			System.err.println("Dieser Fehler ist aufgetreten in Jahresuebersicht(Bereich):");
			System.err.println(e.getMessage());
			
		}
		
	}
	/**
	 * @author Phil
	 * Methode liefert zu einem Kalenderjahr alle Jahresübersichten zu allen Bereichen.
	 * @param int jahr
	 * @return UebersichtSchnittstellenKlasse (enthält Collection<Jahresuebersicht> alleJahresuebersichten und andere Attribute)
	 */
	public static UebersichtSchnittstellenKlasse getAlleJahresuebersichtenZuAllenBereichen(int jahr)
	{
		RemoteConnection Connection = new RemoteConnection();
		Collection<Jahresuebersicht> alleJahresuebersichten = new LinkedList<>();
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
		
		//System.out.println("SELECT DISTINCT Bereich FROM Jahresuebersicht WHERE Kalenderjahr='"+jahr+"'");
		
		try {
			ResultSet jahresUebersicht = Connection.executeQueryStatement("SELECT DISTINCT Bereich FROM Jahresuebersicht WHERE Kalenderjahr='"+jahr+"'");
			
			while(jahresUebersicht.next())
			{	
				int bereichID = jahresUebersicht.getInt("Bereich");
				Bereich bereich = new Bereich(bereichID);
				alleJahresuebersichten.add(new Jahresuebersicht(jahr, bereich));
			}
			
		jahresUebersicht.close();
		uS.Jahresuebersichten = alleJahresuebersichten;
		ResultSet artenZahl = Connection.executeQueryStatement("SELECT COUNT(DISTINCT Art) AS Anzahl FROM Jahresuebersicht WHERE Kalenderjahr='"+jahr+"'");
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
	 * @author Phil
	 * Methode liefert zu einem Kalenderjahr alle Jahresübersichten zu einem Bereichen.
	 * @param int jahr
	 * @return UebersichtSchnittstellenKlasse (enthält Collection<Jahresuebersicht> alleArbeitsgruppenZumBereich und andere Attribute)
	 */
	public static UebersichtSchnittstellenKlasse getAlleJahresuebersichtenZumBereich(int jahr, Bereich bereich) {
		RemoteConnection Connection = new RemoteConnection();
		Collection<Jahresuebersicht> alleJahresuebersichten = new LinkedList<>();
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
		
		//System.out.println("SELECT Bereich FROM Jahresuebersicht WHERE Kalenderjahr='"+jahr+"'");
		
		try {
			ResultSet jahresUebersicht = Connection.executeQueryStatement("SELECT DISTINCT Arbeitsgruppe FROM Jahresuebersicht WHERE Kalenderjahr='"+jahr+"' AND Bereich='"+bereichID+"'");
			
			while(jahresUebersicht.next())
			{	
				int arbeitsgruppeID = jahresUebersicht.getInt("Arbeitsgruppe");
				Arbeitsgruppe gruppe = new Arbeitsgruppe(arbeitsgruppeID);
				
				alleJahresuebersichten.add(new Jahresuebersicht(jahr, gruppe));
			}
			
		jahresUebersicht.close();
		us.Jahresuebersichten = alleJahresuebersichten;
		ResultSet artenZahl = Connection.executeQueryStatement("SELECT Count(DISTINCT Art) AS Anzahl FROM Jahresuebersicht WHERE Kalenderjahr='"+jahr+"' AND Bereich='"+bereichID+"'");
		artenZahl.next();
		us.anzahlArten = artenZahl.getInt("Anzahl");
		artenZahl.close();
		
		} 
		catch (SQLException e) {
			System.err.println("Dieser Fehler ist in getAlleJahresuebersichtenZumBereich(String) aufgetreten:");
			System.err.println(e.getMessage());
		}
		return us;
		
	}
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	
	/**
	 * @author Jenny
	 * Methode liefert Kalenderjahr der gewählten Jahresübersicht.
	 * @return int kalenderjahr
	 */
	public int getKalenderjahr() {
		return this.kalenderjahr;
	}

	/**
	 * @author Jenny
	 * Methode liefert den Bereich zur Jahresübersicht.
	 * @return Bereich bereich
	 */
	public Bereich getBereich() {
		return this.bereich;
	}

	/**
	 * @author Jenny
	 * Methode liefert die Arbeitsgruppe zur Jahresübersicht.
	 * @return Arbeitsgruppe arbeitsgruppe
	 */
	public Arbeitsgruppe getArbeitsgruppe() {
		return this.arbeitsgruppe;
	}
	/**
	 * @author Phil
	 * Methode liefert alle Zeilen aus der Tabelle in der Datenbank, für die es Jahresübersichten zu Arbeitsgruppen gibt.
	 * @return Collection<Zeile> alleZeilenzurArbeitsgruppe
	 */
	public Collection<Zeile> getZeileArbeitsgruppe()
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
			//System.out.println("SELECT Art, SUM(Summe) FROM Jahresuebersicht WHERE Kalenderjahr ='"+ this.kalenderjahr + 
				//	"' AND Arbeitsgruppe ='"+this.arbeitsgruppe.getID()+"'");
			resultSet = Connection.executeQueryStatement(
					"SELECT Art, SUM(Summe) as Summe FROM Jahresuebersicht WHERE Kalenderjahr ='"+ this.kalenderjahr + 
					"' AND Arbeitsgruppe ='"+this.arbeitsgruppe.getID()+"' GROUP BY Art");
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
	 * @author Phil
	 * Methode liefert alle Zeilen aus der Tabelle in der Datenbank, für die es Jahresübersichten zu Bereichen gibt.
	 * @return Collection<Zeile> alleZeilenzuBereichen
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
			//System.out.println("SELECT Art, SUM(Summe) FROM Jahresuebersicht WHERE Kalenderjahr ='"+ this.kalenderjahr + 
				//	"' AND Bereich ='"+this.bereich.getID()+"' GROUP BY Art");
			resultSet = Connection.executeQueryStatement(
					"SELECT Art, SUM(Summe) as Summe FROM Jahresuebersicht WHERE Kalenderjahr ='"+ this.kalenderjahr + 
					"' AND Bereich ='"+this.bereich.getID()+"' GROUP BY Art");
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
}
