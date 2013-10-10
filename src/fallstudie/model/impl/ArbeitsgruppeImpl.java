package fallstudie.model.impl;

import java.util.Collection;

import fallstudie.model.mysql.connector.RemoteConnection;
/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @author 
 */
public class ArbeitsgruppeImpl {

	private String beschreibung;
	private String kurzbezeichnung;
	private BereichImpl bereich;
	private boolean aktiv;
	private MitarbeiterImpl mitarbeiter;
	
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	/**
	 * Konstruktor wenn nur die Kurzbezeichnung übergeben wird, 
	 * alles andere wird in der Datenbank geholt mit SELECT
	 * @param kurzbezeichnung
	 * @return 
	 * @return
	 */
	public ArbeitsgruppeImpl(String kurzbezeichnung, String Dummy) {



		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		

	}

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
	
	public boolean setBeschreibung(String beschreibung) {

		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		return false;
	}

	
	public String getBeschreibung() {
		
		
		return null;
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
		return 0;
	}

	
	public Collection<ArbeitsgruppeImpl> getAlleArbeitsgruppen() {
		if( RemoteConnection.connection == null || RemoteConnection.sql == null ){
			RemoteConnection.connect();
		};
		
		return null;
	}

}
