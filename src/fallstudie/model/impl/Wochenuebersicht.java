package fallstudie.model.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import fallstudie.model.mysql.connector.RemoteConnection;

/**
 * CHANGELOG
 * 
 * @author Phil, 09.10.2013 generiert + implements (Interface) wurde entfernt,
 *         da Konstruktor nicht möglich ist im Interface
 * @version 1.1 - aktualisiert mit technisches Db modell
 */
public class Wochenuebersicht {

	private int summe;
	private Art art;
	private Collection<Zeile> zeile;
	private int kalenderjahr;
	private int kalenderwoche;
	private Arbeitsgruppe arbeitsgruppe;
	private Bereich bereich;

	// -----------------------------------------------------------
	// ---------------------KONSTRUKTOREN-------------------------
	// -----------------------------------------------------------
	/**
	 * Konstruktor beim auslesen von Wochenuebersichten einer arbeitsgruppe
	 * 
	 * @param kalenderjahr
	 * @param kalenderwoche
	 * @param arbeitsgruppe
	 * @return
	 * @return
	 */
	public Wochenuebersicht(int kalenderjahr, int kalenderwoche,
			Arbeitsgruppe arbeitsgruppe) {
		// TODO Auto-generated method stub
	
	}

	/**
	 * Konstruktor beim auslesen von Wochenuebersichten eines Bereichs
	 * 
	 * @param kalenderjahr
	 * @param kalenderwoche
	 * @param Bereich
	 * @return
	 */
	public Wochenuebersicht(int kalenderjahr, int kalenderwoche, Bereich Bereich) {
		// TODO Auto-generated method stub

	}

	// -----------------------------------------------------------
	// ---------------------KONSTRUKTOREN-------------------------
	// -----------------------------------------------------------

	public int getSumme() {
		// TODO Auto-generated method stub
		return summe;
	}

	public int getKalenderjahr() {
		// TODO Auto-generated method stub
		return kalenderjahr;
	}

	public int getKalenderwoche() {
		// TODO Auto-generated method stub
		return kalenderwoche;
	}

	public Bereich getBereich() {
		// TODO Auto-generated method stub
		return this.bereich;
	}

	public Arbeitsgruppe getArbeitsgruppe() {
		// TODO Auto-generated method stub
		return this.arbeitsgruppe;
	}

	public Collection<Zeile> getZeile() {
		return null;
	}

}
