package fallstudie.controller.impl;
import java.awt.event.ActionEvent;

import fallstudie.view.impl.PasswortAendernView;
import fallstudie.view.interfaces.View;

public class PasswortController extends HauptController {

		public PasswortController(){
			this.activeView = (View) new PasswortAendernView();
		}
		
		public void actionPerformed(ActionEvent e) {
			String button = e.getActionCommand();
			if(button == "Speichern")
			{
				String neuesPasswort = this.activeView.getNeuesPasswort();
				String wdhPasswort = this.activeView.getWdhPasswort();
				String altesPasswort =this.activeView.getAltesPasswort();
				
				if( altesPasswort.equals(activeUser.getPasswort() ){
					//ändern aufgrund Verschlüsselung
					if(wdhPasswort.equals(neuesPasswort)){
						activeUser.setPasswort(neuesPasswort);
						hauptfenster.setInfoBox("Passwort erfolgreich geändert");
					}
					else
					{
						hauptfenster.setInfoBox("Passwörter stimmen nicht überein");
					}
				}
				else
				{
					hauptfenster.setInfoBox("Altes Passwort falsch");
				}
			}
			
		}
}
