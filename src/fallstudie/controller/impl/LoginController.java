package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import fallstudie.model.impl.Mitarbeiter;

public class LoginController extends HauptController
{
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
			PopupSchlieﬂen popup = new PopupSchlieﬂen();
			popup.setHinweis("Bitte kontaktieren Sie Ihren IT-Support");
			popup.setButtonName("Schlieﬂen");
			popup.setUeberschrift("Passwort vergessen");
		}
	}
}
