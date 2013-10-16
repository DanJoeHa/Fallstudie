package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.impl.WelcomeView;
import fallstudie.view.interfaces.View;

public class WelcomeController implements Controller {
	
	private WelcomeView view;
	
	public WelcomeController(){
		this.view = new WelcomeView();
		
		String headline = "Herzlich Willkommen " + HauptController.activeUser.getFullName();
		HauptController.hauptfenster.setUeberschrift(headline);
		
		String msg = headline + ", \n \n bitte wählen Sie eine Aktion im Menübaum.";
		this.view.setHinweis(msg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {}

	@Override
	public View getView() {
		return this.view;
	}

	@Override
	public void fortsetzen() {
		// TODO Auto-generated method stub
		
	}

}
