package fallstudie.model.impl;
import java.util.Date;

/** CHANGELOG
 * @author Phil, 09.10.2013
 * generiert + implements (Interface) wurde entfernt, da Konstruktor nicht möglich ist im Interface
 * @author 
 */

public class EintragImpl {

	
	private Date datum;
	private ArbeitsgruppeImpl arbeitsgruppe;
	private int schriftwechsel;
	private int erstattungen;
	
	//-----------------------------------------------------------
	//---------------------KONSTRUKTOR--------------------------
	//-----------------------------------------------------------
	
	public EintragImpl(ArbeitsgruppeImpl arbeitsgruppe,
			int schriftwechsel, int erstattungen, int kalenderjahr, int kalenderwoche) {
		// TODO Auto-generated method stub
		//Datum generieren
	}

}
