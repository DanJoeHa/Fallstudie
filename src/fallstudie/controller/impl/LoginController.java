package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import fallstudie.model.impl.Mitarbeiter;
import fallstudie.view.impl.LoginView;
import fallstudie.view.interfaces.View;

public class LoginController extends HauptController
{
	private LoginView activeView;
	
	public LoginController()
	{
		this.activeView = new LoginView();
	}
	
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		if(button == "Login")
		{
			activeUser = Mitarbeiter.einloggen(this.activeView.getBenutzername(), this.activeView.getPasswort());
		}
		if(button == "Passwort vergessen"){
			PopupSchließen popup = new PopupSchließen();
			popup.setHinweis("Bitte kontaktieren Sie Ihren IT-Support");
			popup.setButtonName("Schließen");
			popup.setUeberschrift("Passwort vergessen");
		}
	}
	
}
