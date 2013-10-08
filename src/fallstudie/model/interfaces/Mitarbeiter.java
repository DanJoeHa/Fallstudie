package fallstudie.model.interfaces;

public interface Mitarbeiter {
	
	/**
	 * Konstruktor
	 * @param Benutzername
	 * @param Passwort
	 * @param Vorname
	 * @param Nachname
	 * @return MitarbeiterObjekt
	 */
	
	public String Mitarbeiter(String Benutzername, String Passwort, String Vorname, String Nachname, String Rolle);
	
	/**
	 * Einloggen und Ausloggen
	 * @param Benutzername
	 * @param Passwort
	 * @return boolean
	 */
		public boolean einloggen (String Benutzername, String Passwort);
		public boolean ausloggen ();
	/**
	 * RolleZuordnenMethode
	 * @param Rolle
	 * @return boolean
	 */
		public boolean zuordnenRolle (Rolle Rolle);
	/**
	 * Benutzernamenverwaltung
	 * @return
	 */
		public String getBenutzername();
		public void setBenutzername(String newBenutzername);
	 /**
	 * Passwort verwaltung
	 * @return String Passwort
	 */
		public String getPasswort();
	/**
	 * 
	 * @param newPasswort
	 * @return boolean
	 */
		public boolean setPasswort(String newPasswort);
     /**
	 * Arbeitsgruppen Methoden
	 * @return String Arbeitsgruppe
	 */
		public String getArbeitsgruppe();
		public void setArbeitsgruppe(String arbeitsgruppe);
	/**
	 * Mitarbeiter deaktivieren
	 * @return boolean
	 */
		public boolean mitarbeiterDeaktivieren();
	
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
	 * LetzterLogin
	 * @return String LetzterLogin
	 */
		public String getLetzterLogin();
	
	
}
