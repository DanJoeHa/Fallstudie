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
public class RolleImpl  {

	String rollenbeschreibung;
	String rollenbezeichnung;
	
	//--------------------KONSTRUKTOR--------------------
	
	/**
	 * Rolleninstanz
	 * @param rollenbezeichnung
	 * @return 
	 * @return
	 */
	public RolleImpl(String rollenbezeichnung) {
		// TODO Auto-generated method stub
		
	}
	
	//--------------------KONSTRUKTOR--------------------
	
	public Collection<RolleImpl> getAlleRolleImpl() {
		Collection<RolleImpl> RollenCollection = null;
		return RollenCollection;
	}

	public Collection<RechteImpl> getBerechtigungenzuRolle() {

		Collection<RechteImpl> RechteCollection = null;
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
