package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import fallstudie.model.mysql.connector.RemoteConnection;

public class UebersichtSchnittstellenKlasse {
	
	public int anzahlArten;
	public Collection<Jahresuebersicht> Jahresuebersichten;
	public Collection<Wochenuebersicht> Wochenuebersichten;

	
	public UebersichtSchnittstellenKlasse() {
	
		try {
			ResultSet resultSet = RemoteConnection.sql.executeQuery("SELECT Count(DISTINCT Art) AS Anzahl FROM Jahresuebersicht");
			
			anzahlArten=resultSet.getInt("Anzahl");	
		} 
		
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
	
	}
	public UebersichtSchnittstellenKlasse(String woche)
	{
		try {
			ResultSet resultSet = RemoteConnection.sql.executeQuery("SELECT Count(DISTINCT Art) AS Anzahl FROM Wochenuebersicht");
			
			anzahlArten=resultSet.getInt("Anzahl");	
		} 
		
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
}
