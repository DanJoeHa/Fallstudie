package fallstudie.model.impl;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

import fallstudie.model.mysql.connector.RemoteConnection;




public class TesterKlasse {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Mitarbeiter a = new Mitarbeiter("Sachbearbeiter");
		System.out.println(a.passwortIsChanged());
}
}
