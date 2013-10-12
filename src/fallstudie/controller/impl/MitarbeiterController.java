package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;

public class MitarbeiterController implements Controller {

	private MitarbeiterBearbeitenView view;
	private MitarbeiterAnlegenView viewAnlagen;
	private String operation;
	
	public MitarbeiterController(){
		
	}
	
	public void setOperation(String operation){
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return null;
	}
}
