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
		Bereich bereich = Bereich.getBereichByName("c");
		a = Jahresuebersicht.getAlleJahresuebersichtenZumBereich(2013, bereich);
		
		for (Iterator i = a.iterator();i.hasNext();)
		{
			Jahresuebersicht jahr = (Jahresuebersicht) i.next();
			
			Collection<Zeile> zeile = jahr.getZeileArbeitsgruppe();
			
			for (Iterator x = zeile.iterator(); x.hasNext();)
			{
				Zeile d = (Zeile) x.next();
				System.out.println("Arbeitsgruppe: "+jahr.getArbeitsgruppe().getKurzbezeichnung()+"Art: "+d.getArt().getName()+" + "+d.getSumme());
			}
		}
		}
		
	
	
		
	}
