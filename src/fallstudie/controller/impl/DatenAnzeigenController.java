package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import fallstudie.controller.interfaces.Controller;
import fallstudie.view.interfaces.View;

public class DatenAnzeigenController implements Controller {
	
	private DatenAnzeigenAuswahlView view;
	private DatenAnzeigenArbeitsgruppeView viewAnzeigenAG;
	private DatenAnzeigenBereich  viewAnzeigenBereich;
	private String suchdomain;
	
	public DatenAnzeigenController(){
	}
	
	public void setSuchdomain(String suchdomain){
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
