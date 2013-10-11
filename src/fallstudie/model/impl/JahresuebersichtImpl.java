package fallstudie.model.impl;

/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @author variablen eingefügt
 * @autor Phil
 * @date 11.10.2013
 * @version 1.1 - aktualisiert mit technisches Db modell
 */
public class JahresuebersichtImpl {
	
	private int summe;
	private ArtImpl art;
	private Collection<ZeileImpl> zeile;
	private int kalenderjahr;
	private ArbeitsgruppeImpl arbeitsgruppe;
	private BereichImpl bereich;
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	/**
	 * Konstruktor beim auslesen von Jahresübersichten einer bestimmten arbeitsgruppe
	 * @param kalenderjahr
	 * @param Arbeitsgruppe
	 * @return 
	 * @return
	 */
	public JahresuebersichtImpl(int kalenderjahr,
			ArbeitsgruppeImpl Arbeitsgruppe) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Konstruktor beim auslesen von Jahresübersichten eines ganzen Bereichs	
	 * @param kalenderjahr
	 * @param Bereich
	 * @return 
	 * @return
	 */
	public JahresuebersichtImpl(int kalenderjahr, BereichImpl Bereich) {
		// TODO Auto-generated method stub
		
	}
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOREN-------------------------
	//-----------------------------------------------------------
	
	/**
	 * Summe erhalten
	 * @return
	 */
	public int getSumme()
	{
		
	}

	/**
	 * Kalenderjahr erhalten
	 * @return
	 */
	public int getKalenderjahr() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Bereich der Jahresuebersicht
	 * @return
	 */
	public BereichImpl getBereich() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Arbeitsgruppe der Jahresuebersicht
	 * @return
	 */
	public ArbeitsgruppeImpl getArbeitsgruppe() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Collection<ZeileImpl> getZeile()
	{
		return null;
	}
}
