package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import fallstudie.model.impl.Mitarbeiter;
import fallstudie.view.impl.LoginView;
import fallstudie.view.impl.PopupSchliessenView;
import fallstudie.view.interfaces.View;

public class LoginController extends HauptController
{
	private LoginView view;
	
	public LoginController()
	{
		this.view = new LoginView();
	}
	
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		if(button == "Login")
		{
			activeUser = Mitarbeiter.einloggen(this.view.getBenutzername(), this.view.getPasswort());
		}
		if(button == "Passwort vergessen"){
			PopupSchliessenView popup = new PopupSchliessenView();
			popup.setHinweis("Bitte kontaktieren Sie Ihren IT-Support");
			popup.setButtonName("Schließen");
			popup.setUeberschrift("Passwort vergessen");
		}
	}
	
}
