package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import fallstudie.model.mysql.connector.RemoteConnection;

/**
 *@date 11.10.2013 
 *@author Phil
 *@version 1.0 - erstellt
 *@author Jenny
 *@date 14.10.13
 *@change Methode implementiert
 */
public class Zeile {

	private int summe;
	private Art art;
	
	
	/**
	 * Konstruktor für die Zeile einer Woche/Jahresuebersicht
	 * @param summe
	 * @param art
	 */
	public Zeile (int summe, Art art)
	{
		this.art = art;
		this.summe = summe;
	}
	
	public Art getArt() {
		
		return this.art;
	}
	
	public int getSumme() {
		
		return this.summe;
	}
}
