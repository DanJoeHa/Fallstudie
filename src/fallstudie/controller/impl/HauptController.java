package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

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

	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return null;
	}

}
