package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.interfaces.Mitarbeiter;
import fallstudie.view.interfaces.View;

public class HauptController implements Controller, TreeSelectionListener {

	private Controller activeController;
	protected View activeView;
	protected static Hauptfenster hauptfenster;
	protected static Mitarbeiter activeUser;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String button = e.getActionCommand();
		if(button == "Logout")
		{
			activeController = new LoginController();
			//ausloggen() Mitarbeiter in DB?
			hauptfenster.setContent(activeController.getView());
		}
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
			case "Passwort ändern":
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
				System.out.println("Keine definierte Aktion ausgewählt!");
		}
	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return null;
	}

}
