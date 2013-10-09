package fallstudie.model.impl;

import java.util.Collection;

import fallstudie.model.interfaces.Arbeitsgruppe;
import fallstudie.model.interfaces.Bereich;
/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @author 
 */
public class WochenuebersichtImpl {

	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	/**
	 * Konstruktor beim auslesen von Wochenuebersichten einer arbeitsgruppe
	 * @param kalenderjahr
	 * @param kalenderwoche
	 * @param Arbeitsgruppe
	 * @return 
	 * @return
	 */
	public WochenuebersichtImpl(int kalenderjahr, int kalenderwoche,
			Arbeitsgruppe Arbeitsgruppe) {
		// TODO Auto-generated method stub

	}

	/**
	 * 	Konstruktor beim auslesen von Wochenuebersichten eines Bereichs
	 * @param kalenderjahr
	 * @param kalenderwoche
	 * @param Bereich
	 * @return
	 */
	public WochenuebersichtImpl(int kalenderjahr, int kalenderwoche,
			Bereich Bereich) {
		// TODO Auto-generated method stub

	}
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	public int getErstattungenSumme() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int getSchriftwechselSumme() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int getKalenderjahr() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int getKalenderwoche() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public Collection<WochenuebersichtImpl> getAlleWocheneuebersichten() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public BereichImpl getBereich() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArbeitsgruppeImpl getArbeitsgruppe() {
		// TODO Auto-generated method stub
		return null;
	}

}
