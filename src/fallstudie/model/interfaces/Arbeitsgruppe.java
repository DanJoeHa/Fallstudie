package fallstudie.model.interfaces;

import java.util.Collection;

import fallstudie.model.impl.BereichImpl;
import fallstudie.model.impl.MitarbeiterImpl;
import fallstudie.model.impl.ArbeitsgruppeImpl;
public interface Arbeitsgruppe {
	
	
	/**
	 * Konstruktor wenn nur die Kurzbezeichnung �bergeben wird, alles andere wird in der Datenbank geholt mit SELECT
	 * @param kurzbezeichnung
	 * @return 
	 * @return
	 */
		public void ArbeitsgruppeImpl(String kurzbezeichnung, String Dummy);
		
	/**
	 * Neue Arbeitsgruppe anlegen Konstruktor, alle Parameter werden von GUI �bergeben	
	 * @param kurzbezeichnung
	 * @param beschreibung
	 * @param bereich
	 * @param mitarbeiter
	 * @return 
	 * @return
	 */
		public void ArbeitsgruppeImpl(String kurzbezeichnung, String beschreibung, BereichImpl bereich,
				MitarbeiterImpl mitarbeiter);
	/**
	 * Arbeitsgruppe Suchen Konstruktor	in Suchenmaske
	 * @param suchbegriff
	 * @return 
	 * @return
	 */
		public void ArbeitsgruppeImpl(String suchbegriff);
		
	/**
	 * Beschreibung �ndern	
	 * @param beschreibung
	 * @return
	 */
		public boolean setBeschreibung(String beschreibung);
		
	/**
	 * Holt beschreibung der arbeitsgruppe
	 * @return String
	 */
		public String getBeschreibung();
	
	/**
	 * �ndert Kurzbezeichnung einer Arbeitsgruppe
	 * @param kurzbezeichnung
	 * @return
	 */
		public boolean setKurzbezeichnung(String kurzbezeichnung);

	
	/**
	 * Holt kurzbezeichnung einer Arbeitsgruppe
	 * @return
	 */
		public String getKurzbezeichnung();
	
	/**
	 * 
	 * @param bereichID wird durch Bereich.getID herausgeholt
	 * @return 
	 */
		public boolean setBereich(BereichImpl bereich);
	
	/**
	 * Gibt den Bereich der arbeitsgruppe zur�ck
	 * @return Bereich
	 */
		public BereichImpl getBereich();
	
	/**
	 * �ndert den Leiter der arbeitsgruppe zum Mitarbeiter - mitarbeiter.getBenutzername gibt den Benutzernamen PK zur�ck.
	 * @param mitarbeiter
	 * @return
	 */
		public boolean setLeiter(MitarbeiterImpl mitarbeiter);
	
	/**
	 * Holt den Leiter der Arbeitsgruppe
	 * @return
	 */
		public MitarbeiterImpl getLeiter();
	
	/**
	 * �ndert aktivit�tsstatus beim l�schen einer Arbeitsgruppe
	 * @param aktiv
	 * @return
	 */
		public boolean setAktiv(boolean aktiv);
	
		
	/**
	 * Erh�lt status der Arbeitsgruppe
	 * @return
	 */
		public boolean getAktiv();
	
	/**
	 * Erh�lt anhand der Kurzbezeichnung die dazugeh�rige ID (prim�rschl�ssel) in der Datenbank
	 * @param kurzbezeichnung
	 * @return
	 */
		public int getID(String kurzbezeichnung);
	
		/**
	 * Methode gibt in einer Collection alle Arbeitsgruppen aus (evtl. Dropdown in Mitarbeiter bearbeiten?)	
	 * @return
	 */
		public Collection<ArbeitsgruppeImpl> getAlleArbeitsgruppen();
}

