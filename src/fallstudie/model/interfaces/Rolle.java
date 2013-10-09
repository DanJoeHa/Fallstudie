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
	 * �berladener Konstruktor von Rolle
	 * Bei Auswahlmaske Mitarbeiter bearbeiten oder Anlegen 
	 * Liste von allen verf�gbaren Rollen
	 * @return
	 */
		public Collection<Rolle> Rolle1();
	/**
	 * Collection der Berechtigungen pro Rolle
	 * @return
	 */
		public  Collection<Rechte> getBerechtigung();
	
	
	
}
