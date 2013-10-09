package fallstudie.model.interfaces;

public interface Rechte {

	
	/**
	 * Konstruktor durch Übergabe vom Recht
	 * @param Name
	 * @return
	 */
		public Rechte Rechte(String Name);
		
	/**
	 * Name des Rechts holen
	 * @return
	 */
		public String getName();
		
	/**
	 * Beschreibung des Rechts holen	
	 * @return
	 */
		public String getBeschreibung();
	
	
}
