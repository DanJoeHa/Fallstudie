package fallstudie.model.interfaces;

import java.util.Collection;

import fallstudie.model.impl.RechteImpl;
import fallstudie.model.impl.RolleImpl;
public interface Rolle {
	
	/**
	 * Rolle
	 * @param rollenbezeichnung
	 * @return 
	 * @return
	 */
		public void RolleImpl(String rollenbezeichnung);
	
		
	/**
	 * �berladener Konstruktor von Rolle
	 * Bei Auswahlmaske Mitarbeiter bearbeiten oder Anlegen 
	 * Liste von allen verf�gbaren Rollen
	 * @return
	 */
		public Collection<RolleImpl> getAlleRollen();
	/**
	 * Collection der Berechtigungen pro Rolle
	 * @return
	 */
		public  Collection<RechteImpl> getBerechtigung();
	/**
	 * liefert Rollenbezeichnung
	 * @return
	 */
		public String getRollenbezeichnung ();
	/**
	 * liefert Rollenbeschreibung	
	 * @return
	 */
		public String getRollenbeschreibung();
}
