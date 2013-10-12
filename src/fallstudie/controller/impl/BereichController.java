package fallstudie.controller.impl;

import java.awt.event.ActionEvent;

import fallstudie.controller.interfaces.Controller;
import fallstudie.model.impl.Bereich;
import fallstudie.view.interfaces.View;

public class BereichController implements Controller {
	
	private String operation;
	private BereichBearbeitenAnlegenView view;
	private BereichLoeschenView viewLoesch;
	
	public BereichController(){
		
	}
	
	public void setOperation(String operation){
		this.operation = operation;
		if( this.operation.equals("anlegen") || this.operation.equals("bearbeiten") )
		{
			this.view = new BereichBearbeitenAnlegenView();
			this.view.setController( this );
		}
		else
		{
			if(this.operation.equals("loeschen"))
			{
				this.viewLoesch = new BereichLoeschenView();
				this.viewLoesch.setController( this );
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return null;
	}
}
