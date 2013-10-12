package fallstudie.model.impl;

public class TesterKlasse {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Arbeitsgruppe ag = new Arbeitsgruppe(2);
		System.out.println(ag.getAktiv()+"+"+ag.getID()+""+ag.getKurzbezeichnung());
		}

}
