package fallstudie.model.impl;

/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @author variablen eingefügt
 */
public class JahresuebersichtImpl {
	
	private int schriftwechselSumme;
	private int erstattungenSumme;
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
	
	public int getErstattungenSumme() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int getSchriftwechselSumme() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int getKalenderjahr() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public BereichImpl getBereich() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArbeitsgruppeImpl getArbeitsgruppe() {
		// TODO Auto-generated method stub
		return null;
	}

}
