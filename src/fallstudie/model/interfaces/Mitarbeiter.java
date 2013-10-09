package fallstudie.model.interfaces;

public interface Mitarbeiter {
	
	/**
	 * Konstruktor wenn sich Mitarbeiter einloggt
	 * @param Benutzername
	 * @return Objekt Mitarbeiter
	 */

		public void MitarbeiterImpl(String benutzername, String passwort);
	
	/**
	 * Muss auch Mitarbeiter hei�en, �berladener Konstruktor
	 * wird verwendet um anhand eines Suchbegriffes einen Mitarbeiter zu finden (Volltextsuche!)
	 * @param suchBegriff
	 * @return MitarbeiterObjekt
	 */
	
		public void MitarbeiterImpl(String suchBegriff);
	
	/**
	 * �berladener Konstruktor #2 , wird verwendet wenn ein Mitarbeiter neu angelegt wird.
	 * @param Benutzername
	 * @param Passwort
	 * @param Vorname
	 * @param Nachname
	 * @param Rolle
	 * @return MitarbeiterObjekt
	 */
	
		public void MitarbeiterImpl(String benutzername, String passwort,  String vorname, 
											String nachname, Rolle tolle);
	
	
	/**
	 * MUSS STATIC SEIN -> Ohne Mitarbeiterobjekt aufrufbar
	 * Einloggen und Ausloggen
	 * @param Benutzername
	 * @param Passwort
	 * @return boolean
	 */
		public boolean einloggen (String benutzername, String passwort);
		
		
	/**
	 * Mitarbeiter ausloggen	
	 * @return
	 */
		public boolean ausloggen ();

	/**
	 * Bereich �ndern
	 * @param Bereich
	 * @return boolean
	 */
		public boolean setBereich(Bereich bereich);
		
	/**
	 * Bereich holen
	 * @return
	 */
		public Bereich getBereich();
	
	
	/**
	 * Rolle �ndern
	 * @param Rolle
	 * @return
	 */
		
		public boolean setRolle (Rolle rolle);
	
	/**
	 * Rolle finden
	 * @return
	 */
		public Rolle getRolle ();
		
	/**
	 * Benutzername �ndern
	 * @return
	 */
		
		public boolean setBenutzername(String newBenutzername);
		
	/**
	 * Benutzernamen bekommen
	 * @return
	 */
		public String getBenutzername();
		
	 /**
	 * Passwort bekommen
	 * @return String Passwort
	 */
		public String getPasswort();
		
	/**
	 * Passwort �ndern
	 * @param newPasswort
	 * @return boolean
	 */
		public boolean setPasswort(String newPasswort);
		
     /**
	 * Arbeitsgruppen �ndern
	 * @return String Arbeitsgruppe
	 */
		public boolean setArbeitsgruppe(Arbeitsgruppe arbeitsgruppe);
		
		
	/**
	 * Arbeitsgruppe bekommen
	 * @return
	 */
		public Arbeitsgruppe getArbeitsgruppe();
		
	/**
	 * Mitarbeiter status �ndern
	 * @return boolean
	 */
		public boolean setAktiv(boolean aktiv);
		
	/**
	 * Status erfragen
	 * @return 0 oder 1, da Datenbank TINYINT
	 */
		public int getAktiv();
		
	/**
	 * Nachnamen- Methoden
	 * @return String Nachname
	 */
		public String getNachname();
		
		
	/**
	 * Nachname bekommen
	 * @param nachname
	 */
		public void setNachname(String nachname);
	/**
	 * Vorname bekommen
	 * @param Vorname
	 * @reutn String Vorname
	 */
		public String getVorname();
		
		
	/**
	 * Vorname �ndern
	 * @param Vorname
	 */
		public void setVorname(String vorname);
		
	/**
	 * Letzter Login
	 * @return
	 */
		public String getLogin();
	
	/**
	 * Check der Rechte
	 * @param recht
	 * @return
	 */
		public boolean checkRecht(String recht);
		
	
}
