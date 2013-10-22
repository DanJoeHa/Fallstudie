package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Mitarbeiter;
import fallstudie.view.impl.HauptView;
import fallstudie.view.impl.HilfeTexte;
import fallstudie.view.impl.SchliessenPopup;
import fallstudie.view.interfaces.View;


/**
 * Hauptsteuerung des Programm, steuert das Programm anhand des Menuebaums und hoert auf Buttons "Logout" und "Hilfe"
 *  
 * @author Marc, Johannes, Angelos, Katrin, Ralf
 * @version 0.1
 *
 */
public class HauptController implements Controller, TreeSelectionListener, KeyListener {

	private Controller activeController;
	protected View view;
	public static HauptView hauptfenster;
	public static Mitarbeiter activeUser;
	public static SchliessenPopup hilfefenster;
	private static HauptController hc;
	
	/**
	 * Ruft das Hauptfenster der Anwendung auf und läd die LoginView hinein
	 */
	public HauptController() {
		
		//Instanz sichern
		HauptController.hc = this;
		
		//Schließen-Popup vorbereiten
		hilfefenster = new SchliessenPopup();
		hilfefenster.setController(this);
		
		//aktiven Controller auf LoginController setzen
		activeController = new LoginController();
		hilfefenster.setHinweis( HilfeTexte.LoginView);
		hilfefenster.setTitle("Hilfe - Login");
		
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
			
			//Mitarbeiter ausloggen
			if( activeUser.ausloggen() ){
				
				//LoginController erstellen und Sicht auf Login wechseln
				activeController = new LoginController();
				hauptfenster.setUeberschrift("Login");
				hauptfenster.setLoggedOut();
				hauptfenster.setInfoBox("Sie haben sich erfolgreich ausgeloggt.");
				hauptfenster.setContent(activeController.getView());
				
				hilfefenster.setHinweis( HilfeTexte.LoginView);
				hilfefenster.setTitle("Hilfe - Login");
			}
				
		}
		
		//Wenn Button "Hilfe gedrückt wurde"
		if(button == "Hilfe?")
		{
			
			hilfefenster.setVisible(true);
		}
		if(button == "Schließen"){
			hilfefenster.setVisible(false);
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
		
		//InfoBox leeren, wenn andere Navigation ausgewählt wird
		hauptfenster.setInfoBox("");
		
		//verhindern, dass User sein erstes Passwort beibehält
		if( activeUser.passwortIsChanged() ){
		
			//Aktion bestimmen
			TreePath path = e.getNewLeadSelectionPath();		
			Object[] pfad = path.getPath();
			int pfadlaenge = pfad.length;
			String action = pfad[pfadlaenge - 1].toString();
			String folder = "";
			
			//Aktionen-Root Knoten
			if( action.equals("Aktionen") || action.equals("Arbeitsgruppe") || action.equals("Art") || action.equals("Bereich") || action.equals("Mitarbeiter") ){
				activeController = new WelcomeController();
				hilfefenster.setHinweis( HilfeTexte.WelcomeView);
				hilfefenster.setTitle("Hilfe - Startseite");
			}else{
				folder = pfad[pfadlaenge - 2].toString();
			}
			
			//Aktionen im Root
			if( folder.equals("Aktionen") ){
				
				switch(action){
					case "Daten erfassen":
						activeController = new ErfassenController();
						hauptfenster.setUeberschrift("Eintrag erfassen");
						hilfefenster.setHinweis( HilfeTexte.ErfassenView);
						hilfefenster.setTitle("Hilfe - Daten erfassen");
						break;
					case "Daten anzeigen":
						startDatenAnzeigen();
						break;
					case "Job-Einstellungen":
						activeController = new KonfigController();
						hauptfenster.setUeberschrift("Job Einstellungen ändern");
						hilfefenster.setHinweis( HilfeTexte.KonfigurationView);
						hilfefenster.setTitle("Hilfe - Job Konfiguration");
						break;
					case "Passwort ändern":	
						activeController = new PasswortController();
						hauptfenster.setUeberschrift("Passwort ändern");
						hilfefenster.setHinweis( HilfeTexte.PasswortaendernView);
						hilfefenster.setTitle("Hilfe - Passwort ändern");
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
						hauptfenster.setUeberschrift("Arbeitsgruppe anlegen");
						hilfefenster.setHinweis( HilfeTexte.ArbeitsgruppeBearbeitenAnlegenView);
						hilfefenster.setTitle("Hilfe - AG anlegen");
						break;
					case "bearbeiten":
						startArbeitsgruppeBearbeiten();
						break;
					case "löschen":
						SuchController ascdelete = new SuchController();
						ascdelete.setSuchdomain("Arbeitsgruppe");
						ascdelete.setOperation("loeschen");
						this.activeController = ascdelete;
						hauptfenster.setUeberschrift("Arbeitsgruppe löschen");
						hilfefenster.setHinweis( HilfeTexte.SuchenView);
						hilfefenster.setTitle("Hilfe - AG löschen");
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
						hauptfenster.setUeberschrift("Art anlegen");
						hilfefenster.setHinweis( HilfeTexte.ArtAnlegenView);
						hilfefenster.setTitle("Hilfe - Art anlegen");
						break;
					case "löschen":
						ArtController artcdelete = new ArtController();
						artcdelete.setOperation("loeschen");
						this.activeController = artcdelete;
						hauptfenster.setUeberschrift("Art löschen");
						hilfefenster.setHinweis( HilfeTexte.ArtLoeschenView);
						hilfefenster.setTitle("Hilfe - Art löschen");
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
						hauptfenster.setUeberschrift("Bereich anlegen");
						hilfefenster.setHinweis( HilfeTexte.BereichBearbeitenAnlegenView);
						hilfefenster.setTitle("Hilfe - Bereich anlegen");
						break;
					case "bearbeiten":
						BereichController bcedit = new BereichController();
						bcedit.setOperation("bearbeiten");
						this.activeController = bcedit;
						hauptfenster.setUeberschrift("Bereich bearbeiten");
						hilfefenster.setHinweis( HilfeTexte.BereichBearbeiten);
						hilfefenster.setTitle("Hilfe - Bereich bearbeiten");
						break;
					case "löschen":
						BereichController bcdelete = new BereichController();
						bcdelete.setOperation("loeschen");
						this.activeController = bcdelete;
						hauptfenster.setUeberschrift("Bereich löschen");
						hilfefenster.setHinweis( HilfeTexte.BereichLoeschenView);
						hilfefenster.setTitle("Hilfe - Bereich löschen");
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
						hauptfenster.setUeberschrift("Mitarbeiter anlegen");
						hilfefenster.setHinweis( HilfeTexte.MitarbeiterAnlegenView);
						hilfefenster.setTitle("Hilfe - Mitarbeiter anlegen");
						break;
					case "bearbeiten":
						startMitarbeiterBearbeiten();
						break;
					case "löschen":
						SuchController mscdelete = new SuchController();
						mscdelete.setSuchdomain("Mitarbeiter");
						mscdelete.setOperation("loeschen");
						this.activeController = mscdelete;
						hauptfenster.setUeberschrift("Mitarbeiter löschen");
						hilfefenster.setHinweis( HilfeTexte.SuchenView);
						hilfefenster.setTitle("Hilfe - Mitarbeiter löschen");
						break;
				}
				
			}
					
			//Anzeigen
			hauptfenster.setContent(activeController.getView());
		}else{
			hauptfenster.setInfoBox("Bitte ändern Sie Ihr Initialpasswort!");
		}
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

	@Override
	public void fortsetzen() {}
	/**
	 * Startet Daten Anzeigen neu
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	public static void startDatenAnzeigen(){
		hc.activeController = new DatenAnzeigenController();
		hauptfenster.setUeberschrift("Daten anzeigen");
		hilfefenster.setHinweis( HilfeTexte.DatenAnzeigenAuswahlView);
		hilfefenster.setTitle("Hilfe - Daten anzeigen");
		hauptfenster.setContent(hc.activeController.getView());
	}
	
	/**
	 * Startet Arbeitsgruppe bearbeiten neu
	 * 
	 * @autor Johannes
	 * @version 1.0
	 */
	public static void startArbeitsgruppeBearbeiten(){
		ArbeitsgruppenController acedit = new ArbeitsgruppenController();
		acedit.setOperation("bearbeiten");
		HauptController.hc.activeController = acedit;
		hauptfenster.setUeberschrift("Arbeitsgruppe bearbeiten");
		hilfefenster.setHinweis( HilfeTexte.SuchenView);
		hilfefenster.setTitle("Hilfe - AG bearbeiten");
	}
	
	
	/**
	 * Startet Mitarbeiter bearbeiten neu
	 * 
	 * @author Johannes
	 * @version 1.0
	 */
	public static void startMitarbeiterBearbeiten(){
		MitarbeiterController mcedit = new MitarbeiterController();
		mcedit.setOperation("bearbeiten");
		HauptController.hc.activeController = mcedit;
		hauptfenster.setUeberschrift("Mitarbeiter bearbeiten");
		hilfefenster.setHinweis( HilfeTexte.MitarbeiterBearbeitenView);
		hilfefenster.setTitle("Hilfe - Mitarbeiter bearbeiten");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_ENTER) hilfefenster.setVisible(false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
