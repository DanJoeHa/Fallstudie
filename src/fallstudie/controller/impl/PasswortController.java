package fallstudie.controller.impl;
import java.awt.event.ActionEvent;

import fallstudie.view.impl.PasswortAendernView;
import fallstudie.view.interfaces.View;

public class PasswortController extends HauptController {

		public PasswortController(){
			this.activeView = new PasswortAendernView();
		}
		
		public void actionPerformed(ActionEvent e) {
			String button = e.getActionCommand();
			if(button == "Speichern")
			{
				String neuesPasswort = this.activeView.getNeuesPasswort();
				String wdhPasswort = this.activeView.getWdhPasswort();
				String altesPasswort =this.activeView.getAltesPasswort();
				
				if( altesPasswort.equals(activeUser.getPasswort() ){
					//�ndern aufgrund Verschl�sselung
					if(wdhPasswort.equals(neuesPasswort)){
						activeUser.setPasswort(neuesPasswort);
						hauptfenster.setInfoBox("Passwort erfolgreich ge�ndert");
					}
					else
					{
						hauptfenster.setInfoBox("Passw�rter stimmen nicht �berein");
					}
				}
				else
				{
					hauptfenster.setInfoBox("Altes Passwort falsch");
				}
			}
			
		}
}
