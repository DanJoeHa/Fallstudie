package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import fallstudie.model.mysql.connector.RemoteConnection;
/**
 * @date 11.10.13 
 * @author Phil
 * @version 1.0
 * @change Klasse erstellt
 */

public class Art {

	private String name;
	private boolean aktiv;
	
	/**
	 * Methode zum Anlegen einer neuen Art in der Datenbank.
	 * @param String artName
	 * @throws Exception, falls Datensatz erfolgreich gespeichert wurde, oder eine selbe Art schon in der Datenbank existiert.
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
				"SELECT Name From Art;");
		
		
		while (checkObVorhanden.next()) 
		{

				String value = checkObVorhanden.getString("Name");
				
				if (name.equals(value)) 
				{
					//System.out.println("SELECT Aktiv From Art Where Name='"+this.name+"'");	
					ResultSet checkObInaktiv = Connection.executeQueryStatement(
							"SELECT Aktiv From Art Where Name='"+this.name+"'");
					checkObInaktiv.next();
					Boolean inaktiv = checkObInaktiv.getBoolean("Aktiv");
						
					if (inaktiv == false) 
					{
						//System.out.println("UPDATE Art SET Aktiv ='1' WHERE Name='"+this.name+"'");
						int RowsAffect = RemoteConnection.sql.executeUpdate(
								"UPDATE Art SET Aktiv ='1' WHERE Name='"+this.name+"'");
									
						if (RowsAffect==1)
							{
								throw new Exception("Datensatz erfolgreich gespeichert");
							}
					}
					
					else throw new Exception ("Art mit dem selben Namen existiert schon.");
				}
							
				
		}
		
		//System.out.println("INSERT INTO Art (Name) VALUES ('"+this.name+"')");
		int rowsAffected = RemoteConnection.sql.executeUpdate("INSERT INTO Art (Name) VALUES ('"+this.name+"')");
	
		if (rowsAffected==1)
			{
			throw new Exception("Datensatz erfolgreich gespeichert");
			}
		checkObVorhanden.close();
	}
	catch (SQLException e) {
		System.err.println("Fehler in Art Anlegen:");
		System.err.println(e.getMessage());
	}
	/**
	 * @author Phil
	 * Liefert anhand des Namens der Art das befüllte Artobjekt mit Informationen aus der Datenbank.
	 * @return Art art
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
			//System.out.println("SELECT * FROM Art WHERE Name='"+name+"'");
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
	 * @author Phil
	 * Anhand eines ResultSets aus einer SELECT- Abfrage wird das Artobjekt befüllt. 
	 * @param ResultSet resultSet
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
		System.err.println("Dieser Fehler ist aufgetretn in Art(ResultSet):");
		System.err.println(e.getMessage());
	}
	}
	/**
	 * @author Phil
	 * Liefert alle Arten zurück, die in der Datenbank aktiv existieren.
	 * @return Collection<Art> alleArtenausDatenbank
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
	 * @author Phil
	 * @return boolean  (erfolgreich in DB geändert = true, sonst = false).
	 */
	public boolean loeschen()
	{
		boolean erfolgreich = false;
		boolean aktuellerStatus = this.getAktiv();
		try 
		{	
			
			if(aktuellerStatus==true)
			{	
				//System.out.println("UPDATE Arbeitsgruppe SET Aktiv='0' WHERE ArbeitsgruppeID='"+this.arbeitsgruppeID+"'");
			
				int RowsAffect = RemoteConnection.sql.executeUpdate(
				"UPDATE Art SET Aktiv ='0' WHERE Name='"+this.name+"'");
				
				if (RowsAffect==1) erfolgreich=true;
			}
			if(aktuellerStatus==false)
			{
			erfolgreich=false;	
			}
			
		}
		catch (SQLException e) {
			System.err.println("Fehler in löschen in Art:");
			System.err.println(e.getMessage());
		}
		return erfolgreich;
		}
	/**
	 * @author Phil
	 * Liefert Status der Art aus der Datenbank
	 * @return boolean ( true= aktiv, false=gelöscht)
	 */
	public boolean getAktiv()
	{
		return this.aktiv;
		
	}
	/**
	 * @author Phil
	 * Liefert Name der Art
	 * @return String artName
	 */
	public String getName()
	{
		return this.name;
	}
	
}
