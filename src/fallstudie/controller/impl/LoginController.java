package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Mitarbeiter;
import fallstudie.view.impl.LoginView;
import fallstudie.view.interfaces.View;

public class LoginController implements Controller
{
	private LoginView view;
	
	public LoginController()
	{
		//View laden
		this.view = new LoginView();
		this.view.setController( this );
	}
	
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		if(button == "Login")
		{
			try{
				HauptController.activeUser = Mitarbeiter.einloggen(this.view.getBenutzername(), this.view.getPasswort());
				
				if( HauptController.activeUser.getLogin() == null ){
					PasswortController pc = new PasswortController();
					HauptController.hauptfenster.setContent( pc.getView() );
				}else{
					WelcomeController wc = new WelcomeController();
					HauptController.hauptfenster.setContent( wc.getView() );
				}
			}catch(Exception ex){
				HauptController.hauptfenster.setInfoBox( ex.getMessage() );
				this.view.reset();
			}
		}
		
		if(button == "Passwort vergessen"){
			/*PopupSchliessenView popup = new PopupSchliessenView();
			popup.setHinweis("Bitte kontaktieren Sie Ihren IT-Support");
			popup.setButtonName("Schließen");
			popup.setUeberschrift("Passwort vergessen");*/
		}
	}

	@Override
	public View getView() {
		return this.view;
	}
	
}
