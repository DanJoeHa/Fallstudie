package fallstudie.model.impl;

import java.sql.ResultSet;
import java.util.Collection;
/**
 * 11.10.13 Klasse erstellt
 * @author Phil
 *@version 1.0
 */
public class ArtImpl {

	private String name;
	private boolean aktiv;
	
	/**
	 * Konstruktor beim Anlegen einer neuen Art 
	 * @param name
	 */
	public ArtImpl(String name)
	{
		
	}
	/**
	 * Methode liefert aus resultset ein ArtObjekt
	 * @param resultSet
	 */
	public ArtImpl(ResultSet resultSet)
	{
		
	}
	/**
	 * Liefert alle Arten zurück, die es gibt
	 * @return
	 */
	public static Collection<ArtImpl> getAlleArten()
	{
		return null;
		
	}
	
	/**
	 * 
	 * @param aktiv
	 * @return
	 */
	public boolean loeschen()
	{
		return false;
		
	}
	/**
	 * Liefert Status der Art
	 * @return
	 */
	public boolean getAktiv()
	{
		return aktiv;
		
	}
	
}
