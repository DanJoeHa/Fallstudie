/**
 * 
 */
package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.regex.Pattern;

import fallstudie.model.mysql.connector.RemoteConnection;

/** 
 * @author Phil
 * @date 09.10.2013
 * @change Klasse erstellt und Attribute ergänzt
 * @version 1.0
 * @author Phil
 * @date 14.10.2013
 * @version 1.1
 * @change Methoden implementiert
 */

public class Rolle  {

	private String rollenbezeichnung;
	
	//--------------------KONSTRUKTOR--------------------
	
	/**
	 * @author Phil
	 * Methode liefert zur Rollenbezeichnung ein Rollenobjekt welches anhand der Informationen aus der Datenbank befüllt wird.
	 * @param String rollenbezeichnung
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
		//System.out.println("SELECT * FROM Rolle WHERE Rollenbezeichnung='"+rollenbezeichnung+"'");
		
		ResultSet resultSet = Connection.executeQueryStatement("SELECT * FROM Rolle WHERE Rollenbezeichnung='"+rollenbezeichnung+"'");
		resultSet.next();
		this.rollenbezeichnung = resultSet.getString("Rollenbezeichnung");

	}
	catch (SQLException e)
	{   System.err.println("Dieser Fehler ist aufgetreten in Rolle (String):");
		System.err.println(e.getMessage());
		
	}

		
	}
	
	//--------------------KONSTRUKTOR--------------------
	
	/**
	 * @author Phil
	 * Methode liefert alle Rollen, welche in der Datenbank existieren.
	 * @return Collection<Rolle> alleRollenAusDatenbank
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
			//System.out.println("SELECT * FROM Rolle");
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
	 * @author Phil
	 * Methode liefert alle Berechtigungen welche eine Rolle hat.
	 * @return Collection<Rechte> rechteZuRolle
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
			//System.out.println("SELECT Berechtigungsname FROM Rollenberechtigungen WHERE Rollenbezeichnung ='"+this.rollenbezeichnung+"'");
			
			resultSet = Connection.executeQueryStatement("SELECT Berechtigungsname FROM Rollenberechtigungen WHERE Rollenbezeichnung ='"+this.rollenbezeichnung+"'");
			boolean lesen =	false;
			while (resultSet.next()) 
				{	
					String rechteName = resultSet.getString("Berechtigungsname");
					
					if (Pattern.matches("Lesen.*", rechteName)) lesen = true;
					result.add(new Rechte(rechteName));
					
				}
				resultSet.close();
			if (lesen) result.add(new Rechte("Lesen", "Dumy-Recht f. Menubaum"));
			
		}
		catch (SQLException e) 
		{	System.err.println("Dieser Fehler ist aufgetreten in getAlleRollen():");
			System.err.println(e.getMessage());
		}
		return result;
	}
	/**
	 * Methode liefert Rollenbezeichnung der aktuellen Rolle.
	 * @return String Rollenbezeichnung
	 */
	public String getRollenbezeichnung() {
		return this.rollenbezeichnung;
	}

}
