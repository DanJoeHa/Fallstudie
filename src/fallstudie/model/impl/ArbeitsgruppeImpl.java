package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import fallstudie.model.mysql.connector.RemoteConnection;
/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @author 
 */
public class ArbeitsgruppeImpl {

	private  String beschreibung;
	private  String kurzbezeichnung;
	private  int arbeitsgruppeID;
	private  BereichImpl bereich;
	private  boolean aktiv;
	private  MitarbeiterImpl mitarbeiter;
	
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	/**
	 * Neue Arbeitsgruppe anlegen Konstruktor, alle Parameter werden von GUI übergeben	
	 * @param kurzbezeichnung
	 * @param beschreibung
	 * @param bereich
	 * @param mitarbeiter
	 * @return 
	 * @return
	 */
	public ArbeitsgruppeImpl(String kurzbezeichnung, String beschreibung,
			BereichImpl bereich, MitarbeiterImpl leiter) {


		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		this.beschreibung = beschreibung;
		this.kurzbezeichnung = kurzbezeichnung;
		this.bereich = bereich;
		this.mitarbeiter = leiter;

	}

	/**
	 * Arbeitsgruppe Suchen Konstruktor	in Suchenmaske
	 * @param suchbegriff
	 * @return 
	 * @return
	 */

	/**
	 * Durch überreichen des Resultsets werden die Objekte vom Typ ArbeitsgruppeImpl erzeugt
	 * @param resultSet
	 * @throws SQLException 
	 */
	public ArbeitsgruppeImpl (ResultSet resultSet) throws SQLException
	{
		try
		{
			// Obtain the number of columns in the returned table
			int columnCount = resultSet.getMetaData().getColumnCount();
			//ID der Arbeitsgruppe
			this.arbeitsgruppeID = resultSet.getInt("ArbeitgsuppeID");
			
			//Mitarbeiterobjekt aus der ID
				int leiterID = resultSet.getInt("Leiter");
			
			this.mitarbeiter = new MitarbeiterImpl(leiterID);
			
			//Bereichobjekt aus der BereichsID
				int bereichID = resultSet.getInt("Bereich");
			//Bereich aus der ID generieren
			this.bereich = new BereichImpl(bereichID);
			//Beschreibung der Arbeitsgruppe
			this.beschreibung = resultSet.getString("Beschreibung");
			//Kurzbezeichnung der Arbeitsgruppe
			this.kurzbezeichnung = resultSet.getString("Kurzbezeichnung");
			//Status der Arbeitsgruppe
			this.aktiv = resultSet.getBoolean("Aktiv");
		}
	catch (SQLException e)
	{
		System.err.println(e.getErrorCode());
	}
	}
	
	/**
	 * Methode wenn nur die Kurzbezeichnung übergeben wird, 
	 * alles andere wird in der Datenbank geholt mit SELECT
	 * @param kurzbezeichnung
	 * @return 
	 * @return
	 * @throws SQLException 
	 */
	public static ArbeitsgruppeImpl getArbeitsgruppeImplByName
											(String kurzbezeichnung){

		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		ArbeitsgruppeImpl ag = null;
		try
		{
			
		
			ResultSet resultSet = RemoteConnection.sql.executeQuery
				("SELECT * FROM Arbeitsgruppe WHERE Kurzbezeichnung = '"+kurzbezeichnung+"'");
			System.out.println
				("SELECT * FROM Arbeitsgruppe WHERE Kurzbezeichnung = '"+kurzbezeichnung+"'");
			//Variablen für den späteren Konstruktoraufruf
	
			ag = new ArbeitsgruppeImpl(resultSet);
			
			
		}
		catch (SQLException e)
		{
			
		}
		return ag;
	}
		

	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	
	
	
	public boolean setBeschreibung(String beschreibung) {

		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		return false;
	}

	
	public String getBeschreibung() {
		
		
		return this.beschreibung;
	}

	
	public boolean setKurzbezeichnung(String kurzbezeichnung) {
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		return false;
	}

	
	public String getKurzbezeichnung() {
		
		return this.kurzbezeichnung;
	}

	
	public boolean setBereich(BereichImpl bereich) {
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		return false;
	}

	
	public BereichImpl getBereich() {
		// TODO Auto-generated method stub
		return this.bereich;
	}

	
	public boolean setLeiter(MitarbeiterImpl mitarbeiter) {
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		return false;
	}

	
	public MitarbeiterImpl getLeiter() {
		// TODO Auto-generated method stub
		return this.mitarbeiter;
	}

	
	public boolean setAktiv(boolean aktiv) {
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		return false;
	}

	
	public boolean getAktiv() {
		// TODO Auto-generated method stub
		return this.aktiv;
	}

	
	public int getIDbyKurzbezeichnung(String kurzbezeichnung) {
		// TODO Auto-generated method stub
		int id = 0;
		return id;
	}
	
	public int getID()
	{
		return this.arbeitsgruppeID;
	}
	
	public Collection<ArbeitsgruppeImpl> getAlleArbeitsgruppen() {
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		return null;
	}

}
