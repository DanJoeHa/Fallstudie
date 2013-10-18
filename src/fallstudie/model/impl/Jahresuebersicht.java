package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import fallstudie.model.mysql.connector.RemoteConnection;

/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht m�glich ist im Interface
 * @author variablen eingef�gt
 * @autor Phil
 * @date 11.10.2013
 * @author Jenny
 * @date 14.10.13
 * @change Methoden implementiert
 * @version 1.1 - aktualisiert mit technisches Db modell
 */
public class Jahresuebersicht {
	
	private int summe;
	private Collection<Zeile> zeile;
	private int kalenderjahr;
	private Arbeitsgruppe arbeitsgruppe;
	private Bereich bereich;
	private Collection<Arbeitsgruppe> agZuBereich;
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	/**
	 * Konstruktor beim auslesen von Jahres�bersichten einer bestimmten arbeitsgruppe
	 * @param kalenderjahr
	 * @param Arbeitsgruppe
	 * @return 
	 * @return
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
	 * Konstruktor beim auslesen von Jahres�bersichten eines ganzen Bereichs	
	 * @param kalenderjahr
	 * @param Bereich
	 * @return 
	 * @return
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
				//	+ kalenderjahr + "' AND  Bereich='" + bereichID + "'");
			
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
	 * Gibt eine Collection von Jahresübersichten zu Bereichen
	 * Für den Zentralbereichsleiter+ Fachbereichsorganisation komplette Übersicht zu allen Bereichen
	 * @param jahr
	 * @return
	 */
	public static Collection<Jahresuebersicht> getAlleJahresuebersichtenZuAllenBereichen(int jahr)
	{
		RemoteConnection Connection = new RemoteConnection();
		Collection<Jahresuebersicht> alleJahresuebersichten = new LinkedList<>();
		
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
		} 
		catch (SQLException e) {
			System.err.println("Dieser Fehler ist in getAlleJahresuebersichtenZuAllenbereichen(String) aufgetreten:");
			System.err.println(e.getMessage());
		}
		return alleJahresuebersichten;
		
		
	}
	/**
	 * Liefert für den Bereichsleiter eine Übersicht des Bereichs mit allen Arbeitsgruppen
	 * @param jahr
	 * @param bereich
	 * @return
	 */
	public static Collection<Jahresuebersicht> getAlleJahresuebersichtenZumBereich(int jahr, Bereich bereich)
	{
		RemoteConnection Connection = new RemoteConnection();
		Collection<Jahresuebersicht> alleJahresuebersichten = new LinkedList<>();
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
		} 
		catch (SQLException e) {
			System.err.println("Dieser Fehler ist in getAlleJahresuebersichtenZumBereich(String) aufgetreten:");
			System.err.println(e.getMessage());
		}
		return alleJahresuebersichten;
		
	}
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	
	/**
	 * Kalenderjahr erhalten
	 * @return
	 */
	public int getKalenderjahr() {
		return this.kalenderjahr;
	}

	/**
	 * Bereich der Jahresuebersicht
	 * @return
	 */
	public Bereich getBereich() {
		return this.bereich;
	}

	/**
	 * Arbeitsgruppe der Jahresuebersicht
	 * @return
	 */
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
			//System.out.println("SELECT Art, Summe FROM Jahresuebersicht WHERE Kalenderjahr ='"+ this.kalenderjahr + 
				//	"' AND Arbeitsgruppe ='"+this.arbeitsgruppe.getID()+"'");
			resultSet = Connection.executeQueryStatement(
					"SELECT Art, Summe FROM Jahresuebersicht WHERE Kalenderjahr ='"+ this.kalenderjahr + 
					"' AND Arbeitsgruppe ='"+this.arbeitsgruppe.getID()+"'");
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
			//System.out.println("SELECT Art, Summe FROM Jahresuebersicht WHERE Kalenderjahr ='"+ this.kalenderjahr + 
				//	"' AND Bereich ='"+this.bereich.getID()+"'");
			resultSet = Connection.executeQueryStatement(
					"SELECT Art, Summe FROM Jahresuebersicht WHERE Kalenderjahr ='"+ this.kalenderjahr + 
					"' AND Bereich ='"+this.bereich.getID()+"'");
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
