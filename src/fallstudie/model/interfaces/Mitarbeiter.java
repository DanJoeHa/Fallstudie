package fallstudie.model.interfaces;

public interface Mitarbeiter {
	
	/**
	 * Konstruktor
	 * @param Benutzername
	 * @return
	 */

	public String Mitarbeiter (String Benutzername);
	
	/**
	 * Einloggen und Ausloggen
	 * @param Benutzername
	 * @param Passwort
	 * @return boolean
	 */
		public boolean einloggen (String Benutzername, String Passwort);
		public boolean ausloggen ();

	/**
	 * Rolle zuordnen
	 * @param Rolle
	 * @return
	 */
		
		public boolean setRolle (Rolle Rolle);
	
	/**
	 * Rolle finden
	 * @return
	 */
		public Rolle getRolle ();
		
	/**
	 * Benutzername ändern und bekommen
	 * @return
	 */
		public String getBenutzername();
		public boolean setBenutzername(String newBenutzername);
		
	 /**
	 * Passwort bekommen
	 * @return String Passwort
	 */
		public String getPasswort();
		
	/**
	 * Passwort ändern
	 * @param newPasswort
	 * @return boolean
	 */
		public boolean setPasswort(String newPasswort);
		
     /**
	 * Arbeitsgruppen Methoden
	 * @return String Arbeitsgruppe
	 */
		public boolean setArbeitsgruppe(Arbeitsgruppe Arbeitsgruppe);
		public Arbeitsgruppe getArbeitsgruppe();
		
	/**
	 * Mitarbeiter deaktivieren oder aktivieren
	 * @return boolean
	 */
		public boolean setAktiv(boolean aktiv);
		public boolean getAktiv();
		
	/**
	 * Nachnamen- Methoden
	 * @return String Nachname
	 */
		public String getNachname();
		public void setNachname(String Nachname);
	/**
	 * Vorname-Methoden
	 * @param Vorname
	 * @reutn String Vorname
	 */
		public String getVorname();
		public void setVorname(String Vorname);
		
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
		
	/**
	 * Bereich festlegen
	 * @param Bereich
	 * @return
	 */
		public boolean setBereich(Bereich Bereich);
	
	/**
	 * Bereich holen
	 * @return
	 */
		public Bereich getBereich();
}
