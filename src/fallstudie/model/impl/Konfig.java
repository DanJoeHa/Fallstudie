
package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import fallstudie.model.mysql.connector.RemoteConnection;
/**
 * @author Phil
 * @date 14.10.2013 -
 * @change Erstellt und Implementiert
 * @version 1.0
 */
public class Konfig {
	
	/**
	 * @author Phil
	 * Methode ändert das Zeitintervall für das Löschen von Einträgen von Sachbearbeitern aus der Datenbank.
	 * @param int intervall (als Monatsangabe)
	 * @return boolean (erfolgreich in DB geändert = true, sonst = false).
	 * @throws Exception, falls das UPDATE in der Datenbak nicht erfolgreich war.
	 */
	public static boolean setJobIntervall(int intervall) throws Exception
	{	
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
		boolean erfolgreich = false;
		
		//System.out.println("UPDATE Config SET Jobintervall='"+intervall+"'");
		
		int rowsAffect = RemoteConnection.sql.executeUpdate("UPDATE Config SET Jobintervall='"+intervall+"'");
		
		if (rowsAffect==1)erfolgreich =true;
		if (rowsAffect==0)throw new Exception("Datensatz konnte nicht gespeichert werden.");
		return erfolgreich;
		
		
	}
	/**
	 * @author Phil
	 * Methode liefert das aktuelle Jobintervall aus der Datenbank
	 * @return String jobIntervall
	 */
	public static String getJobIntervall()
	{	RemoteConnection Connection = new RemoteConnection();
		String jobIntervall = null;
		try
		{
			if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
				RemoteConnection.connect();
			};
		
		
			//System.out.println("SELECT * FROM Config");
		
		ResultSet resultSet = Connection.executeQueryStatement("SELECT * FROM Config");
		resultSet.next();
		
		jobIntervall=resultSet.getString("Jobintervall");
		
		}
		catch (NullPointerException e)
		{
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		catch (SQLException e)
		{
			System.err.println(e.getMessage());
		}

		return jobIntervall;
	}
}
