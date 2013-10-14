package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import fallstudie.model.mysql.connector.RemoteConnection;
/**
 * 11.10.13 Klasse erstellt
 * @author Phil
 *@version 1.0
 */
public class Art {

	private String name;
	private boolean aktiv;
	
	/**
	 * Konstruktor beim Anlegen einer neuen Art 
	 * @param name
	 * @throws Exception 
	 */
	public Art(String name) throws Exception
	{
		RemoteConnection Connection = new RemoteConnection();
	try
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
	
	this.name=name;
	try
	{	
		ResultSet checkObVorhanden = Connection.executeQueryStatement(
				"SELECT Name From Art");
		
		
		while (checkObVorhanden.next()) 
		{

				String value = checkObVorhanden.getString("Name");
				
				if (name.equals(value)) throw new Exception ("Art mit dem selben Namen existiert schon.");
				checkObVorhanden.close();
		}
		
		System.out.println("INSERT INTO Art (Name) VALUES ('"+this.name+"')");
		int rowsAffected = RemoteConnection.sql.executeUpdate("INSERT INTO Art (Name) VALUES ('"+this.name+"')");
	
		if (rowsAffected==1)
			{
			System.out.println("Es wurde "+rowsAffected+" Datensatz gespeichert.");
			throw new Exception("Datensatz erfolgreich gespeichert");
			}
		
	}
	catch (SQLException e) {
		System.err.println(e.getMessage());
		System.err.println("SQL Statement ist fehlerhaft!");
	}
	/**
	 * Liefert Art nach dem Namen
	 */
	}
	public static Art getArtByName(String name)
	{
		Art art = null;
		RemoteConnection Connection = new RemoteConnection();
		ResultSet resultSet = null;
		try
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
		
		try
		{
			System.out.println("SELECT * FROM Art WHERE Name='"+name+"'");
			resultSet = Connection.executeQueryStatement("SELECT * FROM Art WHERE Name='"+name+"'");
			resultSet.next();
			art = new Art(resultSet);
			resultSet.close();
			
		}
		
		catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL Statement ist fehlerhaft!");
		}
		return art;
		
	}
	
	/**
	 * Methode liefert aus resultset ein ArtObjekt
	 * @param resultSet
	 */
	public Art(ResultSet resultSet)
	{
		try
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
		try
		{	
			this.name = resultSet.getString("Name");
			this.aktiv = resultSet.getBoolean("Aktiv");
			
		}
	catch (SQLException e)
	{
		System.err.println("Dieser Fehler ist aufgetretn in Arbeitsgruppe (ResultSet):");
		System.err.println(e.getMessage());
	}
	}
	/**
	 * Liefert alle Arten zur�ck, die es gibt
	 * @return
	 */
	public static Collection<Art> getAlleArten()
	{
		Collection<Art> result = new LinkedList<>();
		RemoteConnection Connection = new RemoteConnection();
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		ResultSet resultSet = null;
		try 
		{	
			//System.out.println("SELECT * FROM Art");
				resultSet = Connection.executeQueryStatement(
						"SELECT * FROM Art");
				
				while (resultSet.next()) 
				{	
					
					boolean aktiv = resultSet.getBoolean("Aktiv");
					if (aktiv==true)
					{
					result.add(new Art(resultSet));
					}
					
				}
				resultSet.close();
		}
		catch (SQLException e) 
		{	System.err.println("Dieser Fehler ist aufgetreten in getAlleArbeitsgruppen():");
			System.err.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 
	 * @param aktiv
	 * @return
	 * @throws Exception 
	 */
	public boolean loeschen() throws Exception
	{
		boolean erfolgreich = false;
		boolean aktuellerStatus = this.getAktiv();
		RemoteConnection Connection = new RemoteConnection();
		try 
		{	
			
			if(aktuellerStatus==true)
			{	
				//System.out.println("UPDATE Arbeitsgruppe SET Aktiv='0' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");
			
				int RowsAffect = RemoteConnection.sql.executeUpdate(
				"UPDATE Art SET Aktiv ='0' WHERE Name='"+this.name+"'");
				
				if (RowsAffect==1)System.out.println("Es wurde "+RowsAffect+" Datensatz gelöscht.");erfolgreich=true;
			}
			if(aktuellerStatus==false)
			{
				
			throw new Exception("Diese Arbeitsgruppe ist bereits gelöscht.");
			}
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("------SQL ERROR-------");
			System.err.println(e.getErrorCode());
			System.err.println(e.getCause());
			System.err.println(e.getMessage());
		}
		return erfolgreich;
		}
	/**
	 * Liefert Status der Art
	 * @return
	 */
	public boolean getAktiv()
	{
		return this.aktiv;
		
	}
	/**
	 * Liefert Name der Art
	 * @return
	 */
	public String getName()
	{
		return this.name;
	}
	
}
