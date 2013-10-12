package fallstudie.model.impl;

import java.util.Collection;
import java.util.Iterator;

public class TesterKlasse {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Collection<Arbeitsgruppe> alleGruppen;
		try {
			alleGruppen = Arbeitsgruppe.suche("5");
		 
		for (Iterator<Arbeitsgruppe> i = alleGruppen.iterator(); i.hasNext();)
		{
			Arbeitsgruppe s = i.next();
			System.out.println(s.getKurzbezeichnung());
		}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
