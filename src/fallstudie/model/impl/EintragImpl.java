package fallstudie.model.impl;
import java.util.Date;

/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @author Phil
 * @date 11.10.2013
 * @version 1.1 - Aktualisiert
 */

public class EintragImpl {

	
	private Date datum;
	private ArbeitsgruppeImpl arbeitsgruppe;
	private int anzahl;
	private ArtImpl art;
	private int kalenderwoche;
	private int kalenderjahr;
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOR--------------------------
	//-----------------------------------------------------------
	/**
	 * Eintrag wird INSERTERT in die Datenbank
	 * @param kalenderjahr
	 * @param kalenderwoche
	 * @param anzahl
	 * @param arbeitsgruppe
	 * @param art
	 */
	public EintragImpl(int kalenderjahr, int kalenderwoche, int anzahl, 
			ArbeitsgruppeImpl arbeitsgruppe, ArtImpl art) {
		// TODO Auto-generated method stub
		// datum genereiren
	}

}
