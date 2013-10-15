package fallstudie.controller.impl;

import java.util.Collection;
import java.util.Iterator;

import fallstudie.model.impl.Arbeitsgruppe;
import fallstudie.model.impl.Art;
import fallstudie.model.impl.Bereich;
import fallstudie.model.impl.Mitarbeiter;
import fallstudie.model.impl.Rolle;

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
	
	public static String[] BereicheCollection2Array(Collection<Bereich> bereiche){
		
		Iterator<Bereich> i = bereiche.iterator();
		
		String[] sBereiche = new String[ bereiche.size() ];
		int x = 0;
		while( i.hasNext() ){
			sBereiche[x] = i.next().getKurzbezeichnung();
			x++;
		}
		
		return sBereiche;
	}
	
	public static String[] RollenCollection2Array(Collection<Rolle> rollen){
		
		Iterator<Rolle> i = rollen.iterator();
		
		String[] sRollen = new String[ rollen.size() ];
		int x = 0;
		while( i.hasNext() ){
			sRollen[x] = i.next().getRollenbezeichnung();
			x++;
		}
		
		return sRollen;
	}

	
	public static Object[][] MitarbeiterCollection2ArraySuche(Collection<Mitarbeiter> m){
		
		Iterator<Mitarbeiter> i = m.iterator();
		
		Object[][] sMitarbeiter = new Object[ m.size() ][6];
		int x = 0;
		while( i.hasNext() ){
			
			Mitarbeiter ma = (Mitarbeiter ) i.next();
			Object[] aMitarbeiter = new Object[6];
			
			sMitarbeiter[x][0] = ma.getBenutzername();
			sMitarbeiter[x][1] = ma.getVorname();
			sMitarbeiter[x][2] = ma.getNachname();
			sMitarbeiter[x][3] = ma.getArbeitsgruppe().getKurzbezeichnung();
			sMitarbeiter[x][4] = ma.getBereich().getKurzbezeichnung();
			sMitarbeiter[x][5] = ma.getRolle().getRollenbezeichnung();
			x++;
		} 
		return sMitarbeiter;
	}
	
	public static Object[][] ArbeitsgruppeCollection2ArraySuche(Collection<Arbeitsgruppe> a){
		
		Iterator<Arbeitsgruppe> i = a.iterator();
		
		Object[][] sAG = new Object[ a.size() ][4];
		int x = 0;
		while( i.hasNext() ){
			
			Arbeitsgruppe AG = (Arbeitsgruppe ) i.next();
			Object[] aArbeitsgruppe = new Object[3];
			
			sAG[x][0] = AG.getKurzbezeichnung();
			sAG[x][1] = AG.getBeschreibung();
			sAG[x][2] = AG.getLeiter().getFullName();
			sAG[x][3] = AG.getBereich().getKurzbezeichnung();
			
			x++;
		
		} 
		return sAG;
	}
}
