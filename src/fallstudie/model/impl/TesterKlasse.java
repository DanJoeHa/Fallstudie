package fallstudie.model.impl;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

import fallstudie.model.mysql.connector.RemoteConnection;




public class TesterKlasse {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
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
		String jobIntervall=resultSet.getString("Jobintervall");

	}
}
