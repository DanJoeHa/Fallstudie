package fallstudie.model.mysql.connector;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

/**
 * Klasse zum setzen und erzeugen der Datenbankverbindung.
 *
 */
public class RemoteConnection {
	/**
	 * Verbindung zur Datenbank
	 */
	public static Connection connection = null;
	/**
	 * Ausführbarer Query
	 */
	public static Statement sql = null;
	
	/**
	 * @author Johannes
	 * Methode zum Verbinden zur Datenbank.
	 */
	public static void connect(){
		try{
			//DB-Verbindung aufbauen inkl. DB auswählen
			RemoteConnection.connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/fallstudie", "wwi2012h", "wwi2012h");
			
			//Statement erstellen
			RemoteConnection.sql = connection.createStatement();
		}catch(CommunicationsException e){
			System.err.println("Konnte keine DB Verbindung herstellen!");
			System.err.println(e.getStackTrace());
			System.err.println(e.getErrorCode());
			System.err.println(e.getCause());
			
		} catch (SQLException e) {
			System.err.println("SQL-Error");
			System.err.println(e.getCause());
			System.err.println(e.getMessage());
			
		}
		
	}
	
	/**
	 * @author Phil
	 * @param statement, Übergebenes Statement welches ausgeführt werden soll. 
	 * @return Gibt ein ResultSet zurück mit den Daten aus der SQL-Abfrage. 
	 * @throws SQLException, Falls SQL-Statement nicht ausgeführt werden kann. 
	 * Ausführen des übergebenen SQL Statements.
	 */
	public ResultSet executeQueryStatement(String statement) throws SQLException {
		Statement sqlStmt = connection.createStatement();

		//Ausführen des Statements und returnen.
		return sqlStmt.executeQuery(statement);
	}
}
