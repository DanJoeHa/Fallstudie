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

		Bereich bereich = Bereich.getBereichByName("c");
		Jahresuebersicht a = new Jahresuebersicht(2013, bereich);
		Collection<Zeile> a1 = new LinkedList<>();
		
		a1 = a.getZeileBereich();
		for(Iterator j=a1.iterator();j.hasNext();)
		{
			
			Zeile z = (Zeile) j.next();
			
			Art p = z.getArt();
			System.out.println("Bereich: "+a.getBereich().getKurzbezeichnung()+" Summe: "+z.getSumme()+" Art: "+p.getName()+"");
		}
	
	
	}
}
