package fallstudie.model.interfaces;

import java.util.Date;
import fallstudie.model.impl.*;
public interface Eintrag {

	/**
	 * Eintrag wird abgeschickt durch den Mitarbeiter
	 * @param datum
	 * @param arbeitsgruppe wird anhand des eingeloggten Mitarbeiter (Mitarbeiter.getArbeitsgruppe) herausgefunden
	 * und dann mit Arbeitsgruppe.getID() die dazugehörige ArbeitsgruppenID
	 * @param schriftwechsel
	 * @param erstattungen
	 */
		public void EintragImpl(Date datum, ArbeitsgruppeImpl arbeitsgruppe, int schriftwechsel, int erstattungen);
}
