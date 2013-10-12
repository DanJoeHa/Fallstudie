package fallstudie.model.impl;

import java.sql.SQLException;

public class TesterKlasseFuerMethoden {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		int id=2;
		Arbeitsgruppe ag;
		ag = new Arbeitsgruppe(id);
		String beschreibung = ag.getBeschreibung();
		System.err.println(beschreibung);
}
}
