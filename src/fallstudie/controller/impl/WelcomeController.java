package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.impl.WelcomeView;
import fallstudie.view.interfaces.View;

public class WelcomeController implements Controller {
	
	private WelcomeView view;
	
	public WelcomeController(){
		this.view = new WelcomeView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public View getView() {
		return this.view;
	}

}
