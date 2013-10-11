/**
 * 
 */
package fallstudie.model.impl;

import java.util.Collection;

/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @version 1.0
 * @author 
 */
public class Rolle  {

	String rollenbeschreibung;
	String rollenbezeichnung;
	
	//--------------------KONSTRUKTOR--------------------
	
	/**
	 * Rolleninstanz
	 * @param rollenbezeichnung
	 * @return 
	 * @return
	 */
	public Rolle(String rollenbezeichnung) {
		// TODO Auto-generated method stub
		
	}
	
	//--------------------KONSTRUKTOR--------------------
	
	public Collection<Rolle> getAlleRollen() {
		Collection<Rolle> RollenCollection = null;
		return RollenCollection;
	}

	public Collection<Rechte> getBerechtigungenzuRolle() {

		Collection<Rechte> RechteCollection = null;
		return RechteCollection;
	}

	public String getRollenbezeichnung() {
		// TODO Auto-generated method stub
		return null;
	}

	public String setRollenbeschreibung() {
		// TODO Auto-generated method stub
		return null;
	}



}
