package fallstudie.model.interfaces;

public interface Rechte {

	
	/**
	 * Konstruktor durch �bergabe vom Recht
	 * @param Name
	 * @return
	 */
		public void RechteImpl(String Name);
		
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