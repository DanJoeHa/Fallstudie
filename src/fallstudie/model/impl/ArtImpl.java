package fallstudie.model.impl;

import java.util.Collection;

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
	public boolean setAktiv(boolean aktiv)
	{
		return aktiv;
		
	}
	
	
}
