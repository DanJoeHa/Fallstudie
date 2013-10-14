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
		
		Collection<Rechte> alleRechteVonRolle = new LinkedList<>();
		Rolle r = new Rolle("Sachbearbeiter");
		alleRechteVonRolle = r.getBerechtigungenzuRolle();
		for (Iterator i = alleRechteVonRolle.iterator(); i.hasNext();)

		{

		Rechte rechte = (Rechte) i.next();

		String rechtName = rechte.getName();
		System.out.println(rechtName);


		}
}
}
