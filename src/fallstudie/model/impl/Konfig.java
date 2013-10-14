
package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import fallstudie.model.mysql.connector.RemoteConnection;

public class Konfig {

	String jobIntervall;
	
	/**
	 * Ändert das Jobintervall, Standard ist 3= 3 Monate
	 * @param intervall MONATSANGABE
	 * @return
	 * @throws Exception
	 */
	public boolean setJobIntervall(String intervall) throws Exception
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
		
		System.out.println("UPDATE Config SET Jobintervall='"+intervall+"'");
		
		int rowsAffect = RemoteConnection.sql.executeUpdate("UPDATE Config SET Jobintervall='"+intervall+"'");
		
		if (rowsAffect==1)erfolgreich =true;System.out.println("Es wurde "+rowsAffect+" Datensatz gespeichert.");
		if (rowsAffect==0)throw new Exception("Datensatz konnte nicht gespeichert werden!");
		return erfolgreich;
		
		
	}
	public static String getJobIntervall() throws SQLException
	{	RemoteConnection Connection = new RemoteConnection();
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
		
		System.out.println("SELECT * FROM Config");
		
		ResultSet resultSet = Connection.executeQueryStatement("SELECT * FROM Config");
		resultSet.next();
		String jobIntervall=resultSet.getString("Jobintervall");


		return jobIntervall;
	}
}
