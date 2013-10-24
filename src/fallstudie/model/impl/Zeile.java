package fallstudie.model.impl;


/**
 *@date 11.10.2013 
 *@author Phil
 *@version 1.0 - erstellt
 *@author Jenny
 *@date 14.10.13
 *@change Methode implementiert
 */
public class Zeile {

	private int summe;
	private Art art;
	
	
	/**
	 * @author Jenny
	 * Methode instanziiert Zeilenobjekt durch übergabe einer Summe und einer Art einer Übersichtszeile aus der Datenbank.
	 * @param summe
	 * @param art
	 */
	public Zeile (int summe, Art art)
	{
		this.art = art;
		this.summe = summe;
	}
	/**
	 * @author Jenny
	 * Methode liefert Art der gewählten Zeile.
	 * @return Art art
	 */
	public Art getArt() {
		
		return this.art;
	}
	/**
	 * @author Jenny
	 * Methode liefert Summe der gewählten Zeile
	 * @return int summe
	 */
	public int getSumme() {
		
		return this.summe;
	}
}
