
package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import fallstudie.model.mysql.connector.RemoteConnection;
/**
 * @date 14.10.2013 - Erstellt und IMplementiert
 * @author Phil
 *
 */
public class Konfig {
	
	/**
	 * Ändert das Jobintervall, Standard ist 3= 3 Monate
	 * @param intervall MONATSANGABE
	 * @return
	 * @throws Exception
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
		
		if (rowsAffect==1)erfolgreich =true;System.out.println("Es wurde "+rowsAffect+" Datensatz gespeichert.");
		if (rowsAffect==0)throw new Exception("Datensatz konnte nicht gespeichert werden!");
		return erfolgreich;
		
		
	}
	/**
	 * Returnt das JobIntervall
	 * @return
	 * @throws SQLException
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
