package fallstudie.model.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;





public class TesterKlasse {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		Collection<Jahresuebersicht> a = new LinkedList<>();
		Collection<Zeile> blabla = new LinkedList<>();
		a = Jahresuebersicht.getAlleJahresuebersichtenZuAllenBereichen(2013);
		
		for (Iterator i = a.iterator();i.hasNext();)
		{
			Jahresuebersicht jahr = (Jahresuebersicht) i.next();
			
			Collection<Zeile> zeile = jahr.getZeileBereich();
			
			for (Iterator x = zeile.iterator(); x.hasNext();)
			{
				Zeile d = (Zeile) x.next();
				System.out.println("Bereich: "+jahr.getBereich().getKurzbezeichnung()+"Art: "+d.getArt().getName()+" + "+d.getSumme());
			}
		}
		}
		
	
	
		
	}
