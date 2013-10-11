package fallstudie.controller.interfaces;

import java.awt.event.ActionListener;
import fallstudie.view.interfaces.View;

/**
 * Verbindliche Schnittstellendefinition für alle Klassen des Package fallstudie.controller.impl
 * 
 * @author Johannes Haag
 * @version 1.0
 *
 */
public interface Controller extends ActionListener {
	
	/**
	 * Schnittstelle zur Rückgabe der View des Controllers
	 * 
	 * @return View
	 */
	public View getView();
	
}
