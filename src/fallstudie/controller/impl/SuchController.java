package fallstudie.controller.impl;

public class SuchController extends HauptController {
	
	private SuchenView view;
	private TabelleView viewErg;
	private String suchdomain;
	private Collection<Object> suchergebnisse;
	private String operation;
	private Object auswahl;
	
	public SuchController(){
	
	}
	
	public void setSuchdomain (String suchdomain){
	}
	
	public Object getAuswahl(){
		
		
		
		return this.auswahl;
	}

	public void setOperation(String operation){
		
	}
}
