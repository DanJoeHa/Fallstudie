package fallstudie.model.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;




public class TesterKlasse {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		Collection<Arbeitsgruppe> a =  new LinkedList<>();
				a = Arbeitsgruppe.suche("e");
		
		for (Iterator i = a.iterator(); i.hasNext();)
		{
			Arbeitsgruppe c = (Arbeitsgruppe) i.next();
			System.out.println(c.getBeschreibung());
		}
}
}
