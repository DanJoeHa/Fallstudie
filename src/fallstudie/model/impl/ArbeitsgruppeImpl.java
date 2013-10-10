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
	public ArbeitsgruppeImpl(String suchbegriff) {


		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		

	}

	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	/**
	 * Methode wenn nur die Kurzbezeichnung übergeben wird, 
	 * alles andere wird in der Datenbank geholt mit SELECT
	 * @param kurzbezeichnung
	 * @return 
	 * @return
	 * @throws SQLException 
	 */
	public static ArbeitsgruppeImpl getArbeitsgruppeByKurzbezeichnung
								(String kurzbezeichnung) throws SQLException {

		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		ResultSet resultSet = RemoteConnection.sql.executeQuery
								("SELECT * FROM Arbeitsgruppe WHERE Kurzbezeichnung = '"+kurzbezeichnung+"'");
		
		//Variablen für den späteren Konstruktoraufruf
		int arbeitsgruppeID= 0;
		String kurzbezeichnungUebergabe= null;
		MitarbeiterImpl mitarbeiter= null;
		BereichImpl bereich = null;
		String beschreibung = null;
		boolean aktiv = false;
		
		
		// Obtain the number of columns in the returned table
		int columnCount = resultSet.getMetaData().getColumnCount();
		
		kurzbezeichnungUebergabe = kurzbezeichnung;

		// Iterate over each row of the resulting table
		
		while (resultSet.next())
		{

			// Output the value of each column of the current row
			for (int i = 1; i <= columnCount; ++i) 
			{

				// Get the name of the current column
				String columName = resultSet.getMetaData().getColumnLabel(i);
				
				if (columName=="ArbeitsgruppeID")
				{
					 arbeitsgruppeID = resultSet.getInt(i);
				}
				
				if (columName == "Leiter")
				{
					// Get the value of this column of the current row
					String leiter = resultSet.getString(i);
					//Mitarbeiter wird generiert aus dem Suchbegriff-Konstruktor
					 mitarbeiter = new MitarbeiterImpl(leiter);
					
				}
				if (columName=="Bereich")
				{
					//Get the value of this column of the current row
					String bereichBezeichnung = resultSet.getString(i);
					//Bereich wird generiert aus dem Suchbegriff-Konstruktor
					 bereich = new BereichImpl(bereichBezeichnung);
				}
				if (columName=="Beschreibung")
				{
					//Get the value of this column of the current row
					 beschreibung = resultSet.getString(i);
				}
				if (columName=="aktiv")
				{
					//Get the value of this column of the current row
					 aktiv = resultSet.getBoolean(i);
				}
				
			}
		}
		
		ArbeitsgruppeImpl returnGruppe = 
						new ArbeitsgruppeImpl(kurzbezeichnung, beschreibung, bereich, mitarbeiter);
		return returnGruppe;
		
		

	}

	
	
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
		
		return null;
	}

	
	public boolean setBereich(BereichImpl bereich) {
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		return false;
	}

	
	public BereichImpl getBereich() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean setLeiter(MitarbeiterImpl mitarbeiter) {
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		return false;
	}

	
	public MitarbeiterImpl getLeiter() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean setAktiv(boolean aktiv) {
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		return false;
	}

	
	public boolean getAktiv() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public int getID(String kurzbezeichnung) {
		// TODO Auto-generated method stub
		return this.arbeitsgruppeID;
	}

	
	public Collection<ArbeitsgruppeImpl> getAlleArbeitsgruppen() {
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		return null;
	}

}
