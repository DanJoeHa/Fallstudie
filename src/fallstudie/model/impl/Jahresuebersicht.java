package fallstudie.model.impl;

import java.util.Collection;

/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @author variablen eingefügt
 * @autor Phil
 * @date 11.10.2013
 * @version 1.1 - aktualisiert mit technisches Db modell
 */
public class Jahresuebersicht {
	
	private int summe;
	private Art art;
	private Collection<Zeile> zeile;
	private int kalenderjahr;
	private Arbeitsgruppe arbeitsgruppe;
	private Bereich bereich;
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	/**
	 * Konstruktor beim auslesen von Jahresübersichten einer bestimmten arbeitsgruppe
	 * @param kalenderjahr
	 * @param Arbeitsgruppe
	 * @return 
	 * @return
	 */
	public Jahresuebersicht(int kalenderjahr,
			Arbeitsgruppe Arbeitsgruppe) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Konstruktor beim auslesen von Jahresübersichten eines ganzen Bereichs	
	 * @param kalenderjahr
	 * @param Bereich
	 * @return 
	 * @return
	 */
	public Jahresuebersicht(int kalenderjahr, Bereich Bereich) {
		// TODO Auto-generated method stub
		
	}
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	/**
	 * Summe erhalten
	 * @return
	 */
	public int getSumme()
	{
		return summe;
		
	}

	/**
	 * Kalenderjahr erhalten
	 * @return
	 */
	public int getKalenderjahr() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Bereich der Jahresuebersicht
	 * @return
	 */
	public Bereich getBereich() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Arbeitsgruppe der Jahresuebersicht
	 * @return
	 */
	public Arbeitsgruppe getArbeitsgruppe() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Collection<Zeile> getZeile()
	{
		return null;
	}
}
