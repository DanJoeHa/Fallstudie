package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Mitarbeiter;
import fallstudie.view.interfaces.View;


/**
 * Hauptsteuerung des Programm, steuert das Programm anhand des Menuebaums und hoert auf Buttons "Logout" und "Hilfe"
 *  
 * @author Marc, Johannes, Angelos, Katrin, Ralf
 * @version 0.1
 *
 */
public class HauptController implements Controller, TreeSelectionListener {

	private Controller activeController;
	protected View activeView;
	protected static Hauptfenster hauptfenster;
	protected static Mitarbeiter activeUser;
	
	/**
	 * Hoert auf Buttons "Logout" und "Hilfe"
	 * 
	 * @author Marc
	 * @version 0.1 - Aktionen für Buttons "Logout" und "Hilfe" gesetzt
	 * 
	 * @author Johannes
	 * @version 0.2 - Nach Klärung mt Philip: Mitarbeiter erst ausloggen (Timestamp in DB gesetzt), dann View auf login
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//Aktion auslesen
		String button = e.getActionCommand();
		
		//Wenn Button "Logout" gedrückt wurde
		if(button == "Logout")
		{
			//Mitarbeiter ausloggen
			if( activeUser.ausloggen() ){
				
				//LoginController erstellen und Sicht auf Login wechseln
				activeController = new LoginController();
				hauptfenster.setContent(activeController.getView());
			}
				
		}
		
		//Wenn Button "Hilfe gedrückt wurde"
		if(button == "Hilfe")
		{
			//internalFrame oder Popup? 
		}

	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		TreePath path = e.getNewLeadSelectionPath();
		String aktion = path.getLastPathComponent().toString();
		switch(aktion)
		{
			case "Passwort �ndern":
					activeController = new PasswortAendernController();
					hauptfenster.setContent(activeController.getView());
					break;
			case "Test":
					break;
			case "a":
					break;
			case "b":
					break;
			case "c":
					break;
			case "d":
					break;
			case "e":
					break;
			case "f":
					break;
			case "g":
					break;
			case "Th":
					break;
			default:
				System.out.println("Keine definierte Aktion ausgew�hlt!");
		}
	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return null;
	}

}
