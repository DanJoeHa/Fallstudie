/**
 * 
 */
package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import fallstudie.model.mysql.connector.RemoteConnection;

/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @version 1.0 Attribute ergänzt
 * 
 * @author Phil
 * @date 14.10.2013
 * @version 1.1
 * @change Methoden implementiert
 */
public class Rechte {

	String name;
	String beschreibung;
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOR-------------------------
	//-----------------------------------------------------------
	
	/**
	 * Konstruktor durch Übergabe vom Recht
	 * @param Name
	 * @return
	 */
	public Rechte(String name) {
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
			System.out.println("SELECT * FROM Berechtigung WHERE Name='"+name+"'");
			
			ResultSet resultSet = Connection.executeQueryStatement("SELECT * FROM Berechtigung WHERE Name='"+name+"'");
			
			this.name = resultSet.getString("Name");
			this.beschreibung = resultSet.getString("Beschreibung");
		}
		catch (SQLException e)
		{   System.err.println("Dieser Fehler ist aufgetreten in Rolle (String):");
			System.err.println(e.getMessage());
			
		}

			
		}

	//-----------------------------------------------------------
	//---------------------KONSTRUKTOR-------------------------
	//-----------------------------------------------------------
	public String getName() {
		return this.name;
	}

	
	public String getBeschreibung() {
		return this.beschreibung;
	}

}
