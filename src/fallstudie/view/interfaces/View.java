package fallstudie.view.interfaces;
import fallstudie.controller.interfaces.Controller;

/**
 * Verbindliche Schnittstellendefinition f�r alle Klassen des Package fallstudie.view.impl
 * 
 * @author Johannes Haag
 * @version 1.0
 *
 */
public interface View {
	
	/**
	 * Schnittstelle zur �bergabe des ActionListeners in alle View-Klassen
	 * @param c
	 */
	public void setController( Controller c );
	
}
