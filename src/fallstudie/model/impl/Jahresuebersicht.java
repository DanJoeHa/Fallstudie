package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import fallstudie.model.mysql.connector.RemoteConnection;

/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @author variablen eingefügt
 * @autor Phil
 * @date 11.10.2013
 * @author Jenny
 * @date 14.10.13
 * @change Methoden implementiert
 * @version 1.1 - aktualisiert mit technisches Db modell
 */
public class Jahresuebersicht {
	
	private int summe;
	private Art art;
	private Collection<Zeile> zeile;
	private int kalenderjahr;
	private Arbeitsgruppe arbeitsgruppe;
	private Bereich bereich;
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	/**
	 * Konstruktor beim auslesen von Jahresübersichten einer bestimmten arbeitsgruppe
	 * @param kalenderjahr
	 * @param Arbeitsgruppe
	 * @return 
	 * @return
	 */
	public Jahresuebersicht(int kalenderjahr,
			Arbeitsgruppe arbeitsgruppe) {
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
		int arbeitsgruppeID = arbeitsgruppe.getID();
		
		try
		{
			ResultSet jahresuebersichtResult = Connection.executeQueryStatement(
					"SELECT * FROM Jahresuebersicht WHERE Kalenderjahr='"+ kalenderjahr +"' AND  Arbeitsgruppe='"+ arbeitsgruppeID +"'");
			
			jahresuebersichtResult.next();
			
			this.kalenderjahr = kalenderjahr;
			this.arbeitsgruppe = arbeitsgruppe;
			int bereich = jahresuebersichtResult.getInt("Bereich");
			this.bereich = new Bereich(bereich);
		

		}
		catch (SQLException e)
		{   
			System.err.println("Dieser Fehler ist aufgetreten in Jahresuebersicht():");
			System.err.println(e.getMessage());
			
		}

	
	}

	/**
	 * Konstruktor beim auslesen von Jahresübersichten eines ganzen Bereichs	
	 * @param kalenderjahr
	 * @param Bereich
	 * @return 
	 * @return
	 */
	public Jahresuebersicht(int kalenderjahr, Bereich Bereich) {
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
		
		//ABereichsID harausbekommen
		int bereichID = bereich.getID();
		
		try
		{
			//System.out.println("SELECT * FROM Rolle WHERE Rollenbezeichnung='"+rollenbezeichnung+"'");
			
			ResultSet jahresuebersichtResult = Connection.executeQueryStatement(
					"SELECT * FROM Jahresuebersicht WHERE Kalenderjahr='"
					+ kalenderjahr + "' AND  Bereich='" + bereichID + "'");
			jahresuebersichtResult.next();
			this.kalenderjahr = kalenderjahr;
			this.bereich = bereich;
			int arbeitsgruppe = jahresuebersichtResult.getInt("Arbeitsgruppe");
			this.arbeitsgruppe = new Arbeitsgruppe(arbeitsgruppe);
			// TODO 

		}
		catch (SQLException e)
		{   
			System.err.println("Dieser Fehler ist aufgetreten in Rolle (String):");
			System.err.println(e.getMessage());
			
		}
		
	}
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	
	/**
	 * Kalenderjahr erhalten
	 * @return
	 */
	public int getKalenderjahr() {
		// TODO Auto-generated method stub
		return this.kalenderjahr;
	}

	/**
	 * Bereich der Jahresuebersicht
	 * @return
	 */
	public Bereich getBereich() {
		// TODO Auto-generated method stub
		return this.bereich;
	}

	/**
	 * Arbeitsgruppe der Jahresuebersicht
	 * @return
	 */
	public Arbeitsgruppe getArbeitsgruppe() {
		// TODO Auto-generated method stub
		return this.arbeitsgruppe;
	}
	
	public Collection<Zeile> getZeile() throws Exception
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
			resultSet = Connection.executeQueryStatement(
					"SELECT Art, Summe FROM Jahresuebersicht WHERE Kalenderjahr ='" + this.kalenderjahr + 
					"' AND Arbeitsgruppe ='" + this.arbeitsgruppe +"'");
				while (resultSet.next()) //Die Ausgelesenen ERgebnisse in die Collection bringen
				{	
					int Zeilensumme = resultSet.getInt("Summe");
					String Zeilenart = resultSet.getString("Art");
					this.art = new Art(Zeilenart);
					result.add(new Zeile(Zeilensumme,art));
							
				}
				resultSet.close();
		}
		catch (SQLException e) 
		{	
			System.err.println("Dieser Fehler ist aufgetreten in getAlleRollen():");
			System.err.println(e.getMessage());
		}
		return result;
		
	}
}
