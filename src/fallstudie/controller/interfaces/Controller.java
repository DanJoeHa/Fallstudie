package fallstudie.controller.interfaces;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import fallstudie.view.interfaces.View;

/**
 * Verbindliche Schnittstellendefinition für alle Klassen des Package fallstudie.controller.impl
 * 
 * @author Johannes Haag
 * @version 1.0
 *
 */
public interface Controller extends ActionListener, MouseListener, KeyListener {
	
	/**
	 * Schnittstelle zur Rückgabe der View des Controllers
	 * 
	 * @return View
	 */
	public View getView();
	
	/**
	 * Schnittstelle zwischen SuchController und aufrufenden Controllern zur Rückgabe des gewählten Objektes.
	 */
	public void fortsetzen();
	
}
