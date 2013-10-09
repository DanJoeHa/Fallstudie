package mysql.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RemoteConnection {
	
	protected static Connection connection = null;
	protected static Statement sql = null;
	
	public static void connect(){
		try{
			//DB-Verbindung aufbauen inkl. DB auswählen
			RemoteConnection.connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/fallstudie", "wwi2012h", "wwi2012h");
			
			//Statement erstellen
			RemoteConnection.sql = connection.createStatement();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
