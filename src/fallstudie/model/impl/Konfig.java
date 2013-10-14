
package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import fallstudie.model.mysql.connector.RemoteConnection;

public class Konfig {

	int jobIntervall;
	
	/**
	 * Standartkonstruktor
	 * @throws SQLException 
	 */
	public Konfig() throws SQLException
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
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		
		System.out.println("SELECT * FROM Config");
		
		ResultSet resultSet = Connection.executeQueryStatement("SELECT * FROM Config");
		resultSet.next();
		this.jobIntervall=resultSet.getInt("Jobintervall");
		
	}
	
	public boolean setJobIntervall(int intervall) throws Exception
	{
		boolean erfolgreich = false;
		
		System.out.println("UPDATE Config SET Jobintervall='"+intervall+"'");
		
		int rowsAffect = RemoteConnection.sql.executeUpdate("UPDATE Config SET Jobintervall='"+intervall+"'");
		
		if (rowsAffect==1)erfolgreich =true;System.out.println("Es wurde "+rowsAffect+" Datensatz gespeichert.");
		if (rowsAffect==0)throw new Exception("Datensatz konnte nicht gespeichert werden!");
		this.jobIntervall=intervall;
		return erfolgreich;
		
		
	}
	public int getJobIntervall()
	{
		return this.jobIntervall;
	}
}
