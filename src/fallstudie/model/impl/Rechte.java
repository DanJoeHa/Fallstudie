
package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

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
public class Rechte {

	private String name;
	private String beschreibung;
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOR-------------------------
	//-----------------------------------------------------------
	
	/**
	 * @author Phil
	 * Methode liefert ein Rechtsobjekt anhand des Namens aus der Datenbank.
	 * @param String name
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
			//System.out.println("SELECT * FROM Berechtigung WHERE Name='"+name+"'");
			
			ResultSet resultSet = Connection.executeQueryStatement("SELECT * FROM Berechtigung WHERE Name='"+name+"'");
			resultSet.next();
			this.name = resultSet.getString("Name");
			this.beschreibung = resultSet.getString("Beschreibung");
		}
		catch (SQLException e)
		{   System.err.println("Dieser Fehler ist aufgetreten in Rechte (String):");
			System.err.println(e.getMessage());
			
		}

			
		}
	/**
	 * @author Phil
	 * Methode erzeugt ein Rechteobjekt anhand der Übergabeparameter
	 * @param String name
	 * @param String beschreibung
	 */
	public Rechte(String name, String beschreibung){
		this.name = name;
		this.beschreibung = beschreibung;
	}
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOR-------------------------
	//-----------------------------------------------------------
	/**
	 * @author Phil
	 * Methode liefert den Namen des Rechts
	 * @return String name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @author Phil
	 * Methode liefert die Beschreibung des Rechts
	 * @return
	 */
	public String getBeschreibung() {
		return this.beschreibung;
	}

}
