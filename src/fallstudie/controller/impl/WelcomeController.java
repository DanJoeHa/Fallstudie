package fallstudie.controller.impl;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

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

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
