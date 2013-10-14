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

		Arbeitsgruppe arbeitsgruppe = new Arbeitsgruppe(2);
		Jahresuebersicht a = new Jahresuebersicht(2013, arbeitsgruppe);
		Collection<Zeile> a1 = new LinkedList<>();
		a1 = a.getZeile();
		for(Iterator j=a1.iterator();j.hasNext();)
		{
			
			Zeile z = (Zeile) j.next();
			System.out.println(a.getKalenderjahr());
			System.out.println(z.getSumme());
			
			Art p = z.getArt();
			System.out.println(p.getName());
		}
	
	
	}
}
