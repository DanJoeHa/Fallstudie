package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Mitarbeiter;
import fallstudie.view.impl.HilfeTexte;
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
			//InfoBox leeren, wenn andere Navigation ausgewählt wird
			HauptController.hauptfenster.setInfoBox(" ");
			
			try{
				HauptController.activeUser = Mitarbeiter.einloggen(this.view.getBenutzername(), this.view.getPasswort());
				
				//Navigations-Baum entsprechend Rechten von User aufbauen
				HauptController.hauptfenster.createNavTree(HauptController.activeUser.checkRecht("Daten erfassen"), HauptController.activeUser.checkRecht("Lesen"), HauptController.activeUser.checkRecht("Arbeitsgruppe anlegen"), HauptController.activeUser.checkRecht("Eintragsart anlegen"), HauptController.activeUser.checkRecht("Bereich anlegen"), HauptController.activeUser.checkRecht("Mitarbeiter anlegen"), HauptController.activeUser.checkRecht("Jobintervall festlegen"));
				
				if( !HauptController.activeUser.passwortIsChanged() ){
					PasswortController pc = new PasswortController();
					HauptController.hauptfenster.setContent( pc.getView() );
					HauptController.hilfefenster.setHinweis(HilfeTexte.LoginView);
					HauptController.hilfefenster.setTitle("Hilfe - Login");
				}else{
					WelcomeController wc = new WelcomeController();
					HauptController.hauptfenster.setContent( wc.getView() );
					HauptController.hilfefenster.setHinweis(HilfeTexte.WelcomeView);
					HauptController.hilfefenster.setTitle("Hilfe - Startseite");
				}
			}catch(Exception ex){
				HauptController.hauptfenster.setInfoBox( ex.getMessage() );
				this.view.reset();
			}
		}
		
		
	}

	@Override
	public View getView() {
		return this.view;
	}
	
}
