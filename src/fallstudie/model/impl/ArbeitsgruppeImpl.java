package fallstudie.model.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import fallstudie.model.mysql.connector.RemoteConnection;
/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht m�glich ist im Interface
 * @author Phil 11.10.2013
 * 
 */
public class ArbeitsgruppeImpl {

	private  String beschreibung;
	private  String kurzbezeichnung;
	private  int arbeitsgruppeID;
	private  BereichImpl bereich;
	private  boolean aktiv;
	private  MitarbeiterImpl leiter;
	
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	/**
	 * Neue Arbeitsgruppe anlegen Konstruktor, alle Parameter werden von GUI �bergeben	
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
		this.leiter = leiter;

	}



	/**
	 * Durch �berreichen des Resultsets werden die Objekte vom Typ ArbeitsgruppeImpl erzeugt
	 * @param resultSet
	 * @throws SQLException 
	 */
	public ArbeitsgruppeImpl (ResultSet resultSet) throws SQLException
	{

		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		try
		{
			// Obtain the number of columns in the returned table
			@SuppressWarnings("unused")
			int columnCount = resultSet.getMetaData().getColumnCount();
			//ID der Arbeitsgruppe
			this.arbeitsgruppeID = resultSet.getInt("ArbeitgsuppeID");
			
			//Mitarbeiterobjekt aus der ID
				String leiterID = resultSet.getString("Leiter");
			//checken
			this.leiter = new MitarbeiterImpl(leiterID);
			
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
		System.err.println(e.getMessage());
		System.err.println(e.getCause());
	}
	}
	/**
	 * ArbeitsgruppeObjekt per ID (Prim�rschl�ssel) erzeugen
	 * @param arbeitsgruppeid
	 */
	
	public ArbeitsgruppeImpl(int arbeitsgruppeid) {
		// TODO Auto-generated constructor stub
	}



	/**
	 * Methode wenn nur die Kurzbezeichnung �bergeben wird, 
	 * alles andere wird in der Datenbank geholt mit SELECT
	 * @param kurzbezeichnung
	 * @return 
	 * @return
	 * @throws SQLException 
	 */
	public static ArbeitsgruppeImpl getArbeitsgruppeImplByName(String kurzbezeichnung){
		try
		{
			if( RemoteConnection.connection == null || RemoteConnection.sql == null )
			{
				RemoteConnection.connect();
			};
		}
		catch (NullPointerException e)
		{
			System.err.println(e.getMessage());
			System.err.println("Konnte keine Datenbankverbindung herstellen!");
		}
		
		ArbeitsgruppeImpl ag = null;
		
		try
		{
			
		
			ResultSet resultSet = RemoteConnection.sql.executeQuery
				("SELECT * FROM Arbeitsgruppe WHERE Kurzbezeichnung = '"+kurzbezeichnung+"'");
			System.out.println
				("SELECT * FROM Arbeitsgruppe WHERE Kurzbezeichnung = '"+kurzbezeichnung+"'");
			//Variablen f�r den sp�teren Konstruktoraufruf
	
			ag = new ArbeitsgruppeImpl(resultSet);
			
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("------SQL ERROR-------");
			System.err.println(e.getErrorCode());
			System.err.println(e.getCause());
			System.err.println(e.getMessage());
		}

		return ag;
	}
		
		

	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	
	
	/**
	 * Mehode �ndert die Beschreibung einer Arbeitsgruppe
	 * @param beschreibung
	 * @return boolean ob erfolgreich 
	 */
	public boolean setBeschreibung(String beschreibung) {
		
		return false;
	}

	/**
	 * Methode liefert Beschreibung zur Arbeitsgruppe zur�ck
	 * @return
	 */
	public String getBeschreibung() {
		
		
		return this.beschreibung;
	}

	/**
	 * Methode �ndert Kurzbezeichnung der Arbeitsgruppe
	 * @param kurzbezeichnung
	 * @return
	 */
	public boolean setKurzbezeichnung(String kurzbezeichnung) {
		
		return false;
	}

	/**
	 * Methode liefert Kurzbezeichnung der Arbeitsgruppe
	 * @return
	 */
	
	public String getKurzbezeichnung() {
		
		return this.kurzbezeichnung;
	}

	/**
	 * Methode �ndert Bereich der arbeitsgruppe
	 * @param bereich
	 * @return
	 */
	public boolean setBereich(BereichImpl bereich) {
		
		return false;
	}
	
	/**
	 * Methode liefert Bereichsobjekt des dazugeh�rigen Bereichs der
	 * Arbeitsgruppe
	 * @return
	 */
	
	public BereichImpl getBereich() {
		// TODO Auto-generated method stub
		return this.bereich;
	}

	/**
	 * Methode �ndert Leiter der ARbeitsgruppe, 
	 * �bergeben wird ein Mitarbeiterobjekt
	 * @param mitarbeiter
	 * @return boolean ob erfolgreich
	 */
	public boolean setLeiter(MitarbeiterImpl mitarbeiter) {
		
		return false;
	}

	/**
	 * Methode liefert Leiter der Arbeitsgruppe anhand des Benutzernamens
	 * der in der Tabelle Mitarbeiter generiert wird
	 * @return
	 */
	public MitarbeiterImpl getLeiter() {
		// TODO Auto-generated method stub
		return this.leiter;
	}

	/**
	 * Methode l�scht die Arbeitsgruppe
	 * -> wird auf Inaktiv gesetzt in der DB
	 * @return
	 */
	public boolean loeschen() {

		return false;
	}

	/**
	 * Methode liefert den Status der Arbeitsgruppe
	 * 1: aktiv, 0: gel�scht
	 * @return
	 */
	public boolean getAktiv() {
		// TODO Auto-generated method stub
		return this.aktiv;
	}

	/**
	 * Methode liefert zur Kurzbezeichnung die dazugeh�rige ID
	 * der Arbeitsgruppe
	 * @param kurzbezeichnung
	 * @return
	 */
	public int getIDbyKurzbezeichnung(String kurzbezeichnung) {
		// TODO Auto-generated method stub
		int id = 0;
		return id;
	}
	
	/**
	 * Methode liefert ID zur Arbeitsgruppe
	 * @return
	 */
	public int getID()
	{
		return this.arbeitsgruppeID;
	}
	/**
	 * Wird gebraucht um die Comboboxen zu bef�llen wo ein Mitarbeiter
	 * einer Arbeitsgruppe hinzugef�gt wird oder ge�ndert wird
	 * @return
	 */
	public Collection<ArbeitsgruppeImpl> getAlleArbeitsgruppen() {

		
		return null;
	}
	/**
	 * Methode liefert anhand des Suchbegriffs eine Collection
	 * von �bereinstimmungen zur�ck (In Tabellenform in der Gui auszugeben)
	 * @param suchbegriff
	 * @return
	 */
	public static Collection<ArbeitsgruppeImpl> suche(String suchbegriff)
	{

		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		return null;
		
	}
}
