/**
 * 
 */
package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import fallstudie.model.mysql.connector.RemoteConnection;

/** CHANGELOG
 * @author Phil
 * @version 1.0
 * @date 09.10.2013
 * @change generiert + implements (Interface) wurde entfernt, da Konstruktor nicht mï¿½glich ist im Interface
 * 
 * 
 * @author Phil
 * @version 1.1
 * @date 14.10.2013
 * @change Methoden implementiert
 */
public class Rolle  {

	String rollenbezeichnung;
	
	//--------------------KONSTRUKTOR--------------------
	
	/**
	 * Rolleninstanz aus einer Rollenbezeichnung kriegen
	 * @param rollenbezeichnung
	 * @return 
	 * @return
	 */
	public Rolle(String rollenbezeichnung) {
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
			
	try
	{
		System.out.println("SELECT * FROM Rolle WHERE Rollenbezeichnung='"+rollenbezeichnung+"'");
		
		ResultSet resultSet = Connection.executeQueryStatement("SELECT * FROM Rolle WHERE Rollenbezeichnung='"+rollenbezeichnung+"'");
		
		this.rollenbezeichnung = resultSet.getString("Rollenbezeichnung");
	}
	catch (SQLException e)
	{   System.err.println("Dieser Fehler ist aufgetreten in Rolle (String):");
		System.err.println(e.getMessage());
		
	}

		
	}
	
	//--------------------KONSTRUKTOR--------------------
	/**
	 * Erhält alle Rollen als Collection
	 * @return
	 */
	public static Collection<Rolle> getAlleRollen() {
		Collection<Rolle> result = new LinkedList<>();
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
		//INitialisieren
		ResultSet resultSet = null;
		try 
		{	
			System.out.println("SELECT * FROM Rolle");
				resultSet = Connection.executeQueryStatement(
					"Select * From Rolle");
				
				while (resultSet.next()) 
				{	
					String rollenbezeichnung = resultSet.getString("Rollenbezeichnung");
					result.add(new Rolle(rollenbezeichnung));
					
				}
				resultSet.close();
		}
		catch (SQLException e) 
		{	System.err.println("Dieser Fehler ist aufgetreten in getAlleRollen():");
			System.err.println(e.getMessage());
		}
		return result;
	}
	/**
	 * Erhält alle Rechte der Rolle
	 * @return
	 */
	public Collection<Rechte> getBerechtigungenzuRolle() {
		//COllection von Rechten zur ROlle
		Collection<Rechte> result = new LinkedList<>();
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
		//INitialisieren
		ResultSet resultSet = null;
		try 
		{	
			System.out.println("SELECT Berechtigungsname FROM Rollenberechtigungen WHERE Rollenbezeichnung ='"+this.rollenbezeichnung+"'");
			
			resultSet = Connection.executeQueryStatement("SELECT Berechtigungsname FROM Rollenberechtigungen WHERE Rollenbezeichnung ='"+this.rollenbezeichnung+"'");
				
				while (resultSet.next()) 
				{	
					String rechteName = resultSet.getString("Berechtigungsname");
					result.add(new Rechte(rechteName));
					
				}
				resultSet.close();
		}
		catch (SQLException e) 
		{	System.err.println("Dieser Fehler ist aufgetreten in getAlleRollen():");
			System.err.println(e.getMessage());
		}
		return result;
	}

	public String getRollenbezeichnung() {
		return this.rollenbezeichnung;
	}

}
