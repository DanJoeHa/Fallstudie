package fallstudie.model.impl;

import java.util.Collection;
import java.util.Iterator;

public class TesterKlasse {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
	
		Collection<Arbeitsgruppe> a = Arbeitsgruppe.suche("5");
		
		for (Iterator i = a.iterator(); i.hasNext();)
		{
			Arbeitsgruppe b = (Arbeitsgruppe) i.next();
			System.out.println(b.getKurzbezeichnung());

		}
		

}
}
