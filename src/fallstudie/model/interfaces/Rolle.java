package fallstudie.model.interfaces;

import java.util.Collection;

public interface Rolle {
	
	/**
	 * Rolle
	 * @param rollenbezeichnung
	 * @return
	 */
		public Rolle Rolle(String rollenbezeichnung);
	
	/**
	 * Collection der Berechtigungen pro Rolle
	 * @return
	 */
		public  Collection<Rechte> getBerechtigung();
	
	
	
}
