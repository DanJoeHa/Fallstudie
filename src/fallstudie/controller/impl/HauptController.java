package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Mitarbeiter;
import fallstudie.view.impl.HauptView;
import fallstudie.view.interfaces.View;


/**
 * Hauptsteuerung des Programm, steuert das Programm anhand des Menuebaums und hoert auf Buttons "Logout" und "Hilfe"
 *  
 * @author Marc, Johannes, Angelos, Katrin, Ralf
 * @version 0.1
 *
 */
public class HauptController implements Controller, TreeSelectionListener {

	private Controller activeController;
	protected View view;
	public static HauptView hauptfenster;
	public static Mitarbeiter activeUser;
	
	/**
	 * Ruft das Hauptfenster der Anwendung auf und läd die LoginView hinein
	 */
	public HauptController(){
		
		//aktiven Controller auf LoginController setzen
		activeController = new LoginController();
		
		//Hauptfenster
		hauptfenster = new HauptView();
		hauptfenster.setController( this );
		hauptfenster.setUeberschrift("Login");
		hauptfenster.setContent( activeController.getView() );
	}
	
	/**
	 * Hoert auf Buttons "Logout" und "Hilfe"
	 * 
	 * @author Marc
	 * @version 0.1 - Aktionen für Buttons "Logout" und "Hilfe" gesetzt
	 * 
	 * @author Johannes
	 * @version 0.2 - Nach Klärung mt Philip: Mitarbeiter erst ausloggen (Timestamp in DB gesetzt), dann View auf login
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Aktion auslesen
		String button = e.getActionCommand();
		
		//Wenn Button "Logout" gedrückt wurde
		if(button == "Logout")
		{
			System.out.println("Logout");
			
			//Mitarbeiter ausloggen
			if( activeUser.ausloggen() ){
				
				//LoginController erstellen und Sicht auf Login wechseln
				activeController = new LoginController();
				hauptfenster.setUeberschrift("Login");
				hauptfenster.setLoggedOut();
				hauptfenster.setInfoBox("Sie haben sich erfolgreich ausgeloggt.");
				hauptfenster.setContent(activeController.getView());
			}
				
		}
		
		//Wenn Button "Hilfe gedrückt wurde"
		if(button == "Hilfe?")
		{
			System.out.println("Hilfe");
			//internalFrame oder Popup? 
		}

	}
	
	/**
	 * Hoert auf Menuebaum
	 * 
	 * @author Marc
	 * @version 0.1 - Aktion für Passwort ändern
	 * 
	 * @author Johannes
	 * @version 0.2 - Kommentierung
	 */
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		//Aktion bestimmen
		TreePath path = e.getNewLeadSelectionPath();		
		Object[] pfad = path.getPath();
		int pfadlaenge = pfad.length;
		String action = pfad[pfadlaenge - 1].toString();
		String folder = pfad[pfadlaenge - 2].toString();
		System.out.println("Pfad ist: " + folder + " > " + action);
		
		//Aktionen im Root
		if( folder.equals("Aktionen") ){
			
			switch(action){
				case "Daten erfassen":
					activeController = new ErfassenController();
					break;
				case "Daten anzeigen":
					break;
				case "Job-Einstellungen":
					break;
				case "Passwort ändern":	
					activeController = new PasswortController();
					break;
			}
			
		}
		
		//Aktionen für Arbeitsgruppe
		if( folder.equals("Arbeitsgruppe") ){
			
			switch(action){
				case "anlegen":
					ArbeitsgruppenController accreate = new ArbeitsgruppenController();
					accreate.setOperation("anlegen");
					this.activeController = accreate;
					break;
				case "bearbeiten":
					ArbeitsgruppenController acedit = new ArbeitsgruppenController();
					acedit.setOperation("bearbeiten");
					this.activeController = acedit;
					break;
				case "loeschen":
					SuchController ascdelete = new SuchController();
					ascdelete.setSuchdomain("Arbeitsgruppe");
					ascdelete.setOperation("loeschen");
					this.activeController = ascdelete;
					break;
			}
			
		}
		
		//Aktionen für Art
		if( folder.equals("Art") ){
			
			switch(action){
				case "anlegen":
					ArtController artccreate = new ArtController();
					artccreate.setOperation("anlegen");
					this.activeController = artccreate;
					break;
				case "loeschen":
					ArtController artcdelete = new ArtController();
					artcdelete.setOperation("loeschen");
					this.activeController = artcdelete;
					break;
			}
			
		}
		
		//Aktionen für Bereich
		if( folder.equals("Bereich") ){
			
			switch(action){
				case "anlegen":
					BereichController bccreate = new BereichController();
					bccreate.setOperation("anlegen");
					this.activeController = bccreate;
					break;
				case "bearbeiten":
					BereichController bcedit = new BereichController();
					bcedit.setOperation("bearbeiten");
					this.activeController = bcedit;
					break;
				case "loeschen":
					BereichController bcdelete = new BereichController();
					bcdelete.setOperation("loeschen");
					this.activeController = bcdelete;
					break;
			}
			
		}
		
		//Aktionen für Mitarbeiter
		if( folder.equals("Mitarbeiter") ){
			
			switch(action){
				case "anlegen":
					MitarbeiterController mccreate = new MitarbeiterController();
					mccreate.setOperation("anlegen");
					this.activeController = mccreate;
					break;
				case "bearbeiten":
					MitarbeiterController mcedit = new MitarbeiterController();
					mcedit.setOperation("bearbeiten");
					this.activeController = mcedit;
					break;
				case "loeschen":
					SuchController mscdelete = new SuchController();
					mscdelete.setSuchdomain("Mitarbeiter");
					mscdelete.setOperation("loeschen");
					this.activeController = mscdelete;
					break;
			}
			
		}
				
		//Anzeigen
		hauptfenster.setContent( activeController.getView() );
	}
	
	/**
	 * Liefert den derzeit angezeigten Content zurück
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	@Override
	public View getView() {
		return this.view;
	}

}
