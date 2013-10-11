package fallstudie.model.impl;
import java.util.Date;

/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @author Phil
 * @date 11.10.2013
 * @version 1.1 - Aktualisiert
 */

public class Eintrag {

	
	private Date datum;
	private Arbeitsgruppe arbeitsgruppe;
	private int anzahl;
	private Art art;
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
	public Eintrag(int kalenderjahr, int kalenderwoche, int anzahl, 
			Arbeitsgruppe arbeitsgruppe, Art art) {
		// TODO Auto-generated method stub
		// datum genereiren
	}

}
