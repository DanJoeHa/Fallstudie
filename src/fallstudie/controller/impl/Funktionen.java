package fallstudie.controller.impl;

import java.util.Collection;
import java.util.Iterator;

import fallstudie.model.impl.Arbeitsgruppe;
import fallstudie.model.impl.Art;
import fallstudie.model.impl.Mitarbeiter;

public abstract class Funktionen {
	
	/**
	 * Mitarbeiter Collection in String Array verwandeln
	 * @param m
	 * @return
	 */
	public static String[] MitarbeiterCollection2Array(Collection<Mitarbeiter> m){
				
		Iterator<Mitarbeiter> i = m.iterator();
		
		String[] sMitarbeiter = new String[ m.size() ];
		int x = 0;
		while( i.hasNext() ){
			sMitarbeiter[x] = i.next().getBenutzername();
			x++;
		} 
		return sMitarbeiter;
	}
	
	/**
	 * Arbeitsgruppen Collection in String Array verwandeln
	 * @param a
	 * @return
	 */
	public static String[] ArbeitsgruppeCollection2Array(Collection<Arbeitsgruppe> a){
				
		Iterator<Arbeitsgruppe> i = a.iterator();
		
		String[] sArbeitsgruppe = new String[ a.size() ];
		int x = 0;
		while( i.hasNext() ){
			sArbeitsgruppe[x] = i.next().getKurzbezeichnung();
			x++;
		} 
		return sArbeitsgruppe;
	}

}
